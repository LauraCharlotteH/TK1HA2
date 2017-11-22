package pokemon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import pokemon.controllers.PokeCtrl.CatchListener;
import pokemon.models.BoardModel;
import pokemon.views.GameView;

public class GameClient implements IGameClient {
	int score;
	String name;
	ArrayList<Player> players;
	Position pokemanPos;
	IGameServer gameServer;
	Position boardOffset;
	// stuff you need for GUI
	GameView gameView;

	public static void main(String[] args) {

		// first the client as server
		GameClient obj = new GameClient();
	}

	public GameClient() {
		score = 0;
		name = "";
		players = new ArrayList<Player>();
		pokemanPos = new Position(0, 0);

		try {
			// obtain a stub for the remote object from the GameServer's
			// registry
			Registry registry = LocateRegistry.getRegistry(null);
			gameServer = (IGameServer) registry.lookup("GameServer");
			players = gameServer.getPlayers();
			setName();

			// now set this client as server, too
			IGameClient gameClient = (IGameClient) UnicastRemoteObject
					.exportObject(this, 0);
			Registry regClient = LocateRegistry.getRegistry();
			regClient.bind(this.name, gameClient);

			// now login in the Gameserver
			gameServer.login(this, this.name);

			// setup the game
			this.boardOffset = gameServer.getBoardOffset();
			this.pokemanPos = gameServer.getPokemanPos();
			// setup the game GUI and render it
			// this also generates the local player
			gameView = new GameView(boardOffset.x, boardOffset.y);
			gameView.getBoard().addCatchListener(new CatchListener());// enable
																		// catch
																		// button
			name = gameView.getPlayerName();
			// making sure to log out before closing the window
			gameView.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					logout();
					System.exit(0);
				}
			});
			gameView.setVisible(true);

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	// just call this function when the user wants to quit the game.
	// The server has to know that to inform the other clients that this client
	// has logged out
	public void logout() {
		try {
			gameServer.logout(this.name);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	// Here the player is asked for his name; the name should be unique and not
	// used by another player
	// This is important because every client must also be registered as an
	// server
	// and for binding we need unique names
	void setName() {
		String tempname = gameView.getPlayerName();
		if (players != null && players.size() > 0) {
			for (Player player : players) {
				if (tempname.equals(player.getName())) {
					gameView.askNewPlayerName();
					setName();
				}
			}
		}
		// the name is unique so it is set to the member of this object
		this.name = tempname;

	}

	@Override
	public void incrementScore() {
		this.score++;
		gameView.getPlayer().setScoreLabel(this.score);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public void receiveActivePlayers(ArrayList<Player> players) {
		if (players != null && players.size() > 0) {

			for (Player player : players) {
				if (!player.getName().equals(this.name)) {
					System.out.println(player.getName());
					gameView.addPlayers(players);// display other players
				}
			}
		}
	}

	// Here you inform the player that the pokeman has been caught by someone
	// else
	// and remove the caught poke from the board
	@Override
	public void receivePokemonCaught(Player player) {
		gameView.getBoard().removeCatchListener();
		gameView.getBoard().removePokemon();
		gameView.getBoard().displayMessage(
				player + " caught it first. try to be faster next time!");
	}

	@Override
	public void receiveNewPokemonPosition(Position pos) {
		pokemanPos = pos;
		gameView.getBoard().addPokemon(pos.y, pos.x);
		// and enable the new catch button
		gameView.getBoard().addCatchListener(new CatchListener());
	}

	class CatchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// inform Server that you caught it
			try {
				gameServer.catchPokemon(name);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			// update player and its score is done from GameServer
			gameView.getBoard().removeCatchListener();
			gameView.getBoard().removePokemon();
			gameView.getBoard().displayMessage(
					"Yay you got one. Now onto the next!");
			
			//generating new Poke is also done from GameServer

		}

	}

}
