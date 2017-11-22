package pokemon;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pokemon.controllers.GameClient;
import pokemon.controllers.Player;
import pokemon.controllers.PokeCtrl;
import pokemon.models.*;
import pokemon.views.*;

/**
 * This class is the entrance point to launch the game
 * 
 * @author Laura
 *
 */
public class Start {
	//TODO remove 
	public static void main(String[] args) {

		//dummy data for displaying other players 
		Player p1 = new Player("Tom", 5);
		Player p2 = new Player("Tom2", 4);
		Player p3 = new Player("Tom3", 7);
		Player p4 = new Player("Tom4", 1);
		Player p5 = new Player("Tom5", 4);
		ArrayList<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		
		Player pl1 = new Player("Anna", 5);
		Player pl2 = new Player("Paul", 4);
		Player pl3 = new Player("Mia", 7);
		Player pl4 = new Player("Nils", 1);
		Player pl5 = new Player("Felix", 4);
		ArrayList<Player> players2 = new ArrayList<>();
		players2.add(pl1);
		players2.add(pl2);
		players2.add(pl3);
		players2.add(pl4);
		players2.add(pl5);
		
		
		BoardModel boardMod = new BoardModel(3, 5);
		GameView game = new GameView(BoardModel.getHeight(),
				boardMod.getWidth());
		game.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		game.addPlayers(players);
		game.addPlayers(players2);
		game.setVisible(true);
		PlayerModel playerMod = new PlayerModel(game.getName());
		PokeCtrl ctrl = new PokeCtrl(game, boardMod, playerMod);

	}
}