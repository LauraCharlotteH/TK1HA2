package pokemon.views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import pokemon.controllers.Player;
import pokemon.models.PokemonModel;
import pokemon.models.PokemonModel.pokeStyle;

public class GameView extends JFrame {

	private BoardView board;
	private PlayerView player;
	private AllPlayersView allPlayers;
	private String localPlayerName;
	private JPanel background = new JPanel();
	private JPanel playersPanel;

	public GameView(int height, int width) {
		// needed to do the logout method before closing, acutal close will be
		// called from pokeCtrl
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		background.setLayout(new BoxLayout(background, BoxLayout.X_AXIS));
		background.setVisible(true);
		this.setSize(width * 200, height * 200);

		// get player name
		Random rn = new Random();
		int val = rn.nextInt(100);
		UIManager.put("OptionPane.cancelButtonText", "generate one for me");
		localPlayerName = JOptionPane.showInputDialog(this, "Please enter your unique user name!",
				"enter a name to begin the game", JOptionPane.INFORMATION_MESSAGE);
		if (localPlayerName == null || localPlayerName.equals("")) {
			localPlayerName = "Player" + val;
		}
		// setup you player
		player = new PlayerView(localPlayerName);
		background.add(player);

		board = new BoardView(height, width);
		// breach of ettiquette... if you have a better idea go ahead :)
		board.addPokemon(2, 1);
		background.add(board);
		
		playersPanel = new JPanel();
		playersPanel.setSize(100, this.HEIGHT);
		background.add(playersPanel);

		this.getContentPane().add(background);
	}

	public void askNewPlayerName(){
		Random rn = new Random();
		int val = rn.nextInt(100);
		localPlayerName = JOptionPane.showInputDialog(this,"Please try a different user name!",
				"Your name is already in use!", JOptionPane.INFORMATION_MESSAGE);
		if (localPlayerName == null || localPlayerName.equals("")) {
			localPlayerName = "Player" + val;
		}

	}
	//render a list of Players 
	public void addPlayers(ArrayList<Player> players){
		playersPanel.removeAll();
		allPlayers = new AllPlayersView(players);
		playersPanel.add(allPlayers);
		this.repaint();
		this.revalidate();
	}
	// getter and setter

	
	public BoardView getBoard() {
		return board;
	}

	public void setBoard(BoardView board) {
		this.board = board;
	}

	public PlayerView getPlayer() {
		return player;
	}

	public void setPlayer(PlayerView player) {
		this.player = player;
	}
	public String getPlayerName(){
		return localPlayerName;
	}

}