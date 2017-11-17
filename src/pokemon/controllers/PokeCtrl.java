package pokemon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pokemon.models.BoardModel;
import pokemon.models.PlayerModel;
import pokemon.views.GameView;

public class PokeCtrl {
	private GameView gameView;
	private BoardModel board;
	private PlayerModel player;
	
	public PokeCtrl(GameView agame, BoardModel aboard, PlayerModel aplayer){
		this.gameView = agame;
		this.board = aboard;
		this.player = aplayer;
		
		this.gameView.getBoard().addCatchListener(new CatchListener());
	}
	
	//what acutally happens when pokebutton is clicked:
	class CatchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//update the model player
			player.plusScore();
			//update the display of the score and remove caught pokemon
			gameView.getPlayer().setScoreLabel(player.getScore());
			gameView.getBoard().removeCatchListener();
			gameView.getBoard().removePokemon();
			gameView.getBoard().displayMessage("Yay you got one. Now onto the next!");
			//generate new random Poke on board model this automatically deletes the old one
			board.generateNewPoke();
			//display the new random Poke on board view
			gameView.getBoard().addPokemon(board.getPoke().getPosX(), board.getPoke().getPosY(), board.getPoke().getStyle());
			//and enable the new catch button
			gameView.getBoard().addCatchListener(new CatchListener());
		}
		
	}
}