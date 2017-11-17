package pokemon.views;

import java.awt.*;

import javax.swing.*;

import pokemon.models.PokemonModel;
import pokemon.models.PokemonModel.pokeStyle;

public class GameView extends JFrame {

	private BoardView board;
	private PlayerView player;

	public GameView(int height, int width) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel background = new JPanel();
		background.setLayout(new BoxLayout(background, BoxLayout.X_AXIS));
		background.setVisible(true);

		this.setSize(width * 200, height * 200);
		player = new PlayerView("Laura");
		background.add(player);

		board = new BoardView(height, width);
		//breach of ettiquette... if you have a better idea go ahead :)
		board.addPokemon(2, 1, pokeStyle.PIKACHU);
		background.add(board);

		this.getContentPane().add(background);
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

}