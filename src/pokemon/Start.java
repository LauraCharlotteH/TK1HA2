package pokemon;

import pokemon.controllers.PokeCtrl;
import pokemon.models.*;
import pokemon.views.*;

/**
 * This class is the entrance point to launch the game
 * @author Laura
 *
 */
public class Start {
	 public static void main(String[] args) {
		 BoardModel boardMod = new BoardModel(3,5);
		 PlayerModel playerMod = new PlayerModel("Laura");
		 GameView game = new GameView(boardMod.getHeight(), boardMod.getWidth());
		 game.setVisible(true);
		 PokeCtrl ctrl = new PokeCtrl(game, boardMod, playerMod);
		 
	 }
}