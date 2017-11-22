package pokemon.views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import pokemon.models.PokemonModel.pokeStyle;

/**
 * this class displays the board everything on it (including the pokemon) and
 * holds the methods to add and remove a pokemon on a position
 * 
 * @author Laura
 *
 */
public class BoardView extends JPanel {
	public enum pokeStyle{
		PIKACHU , EEVEE , CHARMANDER , SQUIRTLE , BULBASAUR
	}
	int nrOfStyles = 5;
	JButton[][] buttons;
	JButton pokeButton;

	/**
	 * generates a chess board like board of buttons in different shades of
	 * green
	 * 
	 * @param height
	 *            of board
	 * @param width
	 *            of board
	 */
	public BoardView(int height, int width) {
		// Colors of the field
		Color firstColor = new Color(102, 255, 102);
		Color secondColour = new Color(153, 255, 153);

		JButton catchButton = null;
		buttons = new JButton[height][width];

		for (int i = 0; i < height; i++) {// i über höhe |
			for (int j = 0; j < width; j++) {// j über breite __
				catchButton = new JButton();
				if ((j + i) % 2 == 0) { // weird colour swapps
					catchButton.setBackground(firstColor);
				} else {
					catchButton.setBackground(secondColour);
				}
				buttons[i][j] = catchButton;
				add(catchButton);

			}
			if (i % width == 0) { // swapping the color around

				Color temp = firstColor;
				firstColor = secondColour;
				secondColour = temp;
			}
		}

		// GridLayout will arange elements in Grid...we have to swap height and
		// width to get even squares and a button order that corresponds to the
		// button array
		this.setLayout(new GridLayout(height, width));
		this.setSize(width * 80, height * 80); // Size of the board
		this.setVisible(true);

	}

	/**
	 * adding a random Poke to a certain button of the board
	 * 
	 * @param posY
	 *            how far down the poke should sit
	 * @param posX
	 *            how far to the right the poke should sit both params start
	 *            counting at 0!
	 */
	public void addPokemon(int posY, int posX) {
		//generate random Style
		int random =(int)(Math.random()*(nrOfStyles));
		pokeStyle style = pokeStyle.values()[random];
		// display the different pokemon
		String path;
		switch (style) {
		case PIKACHU:
			path = "src/resources/pikachu.png";
			break;
		case BULBASAUR:
			path = "src/resources/bulbasaur.png";
			break;
		case CHARMANDER:
			path = "src/resources/charmander.png";
			break;
		case EEVEE:
			path = "src/resources/eevee.png";
			break;
		case SQUIRTLE:
			path = "src/resources/squirtle.png";
			break;
		default:
			path = "src/resources/eevee.png";
			break;
		}

		Icon image = new ImageIcon(path);
		// scale image to fit button
		Image img = ((ImageIcon) image).getImage();
		Image newimg = img.getScaledInstance(80, 80,
				java.awt.Image.SCALE_SMOOTH);
		// add image of poke to button
		buttons[posX][posY].setIcon(new ImageIcon(newimg));
		// remember the button
		pokeButton = buttons[posX][posY];
	}

	/**
	 * removes the pokemon image from the grid and removes the memory of where
	 * it sat from the button
	 */
	public void removePokemon() {
		pokeButton.setIcon(null);
		pokeButton = null;
	}

	/**
	 * enable the Button the poke sits on to be clicked
	 * @param listenForPokeButton what to do when button clicked
	 */
	public void addCatchListener(ActionListener listenForPokeButton) {
		pokeButton.addActionListener(listenForPokeButton);
	}

	/**
	 * removes all action listners from the pokeButton...
	 * not fancy but this gives removeActionListener the needed argument
	 */
	public void removeCatchListener() {
		for (ActionListener al : pokeButton.getActionListeners()) {
			pokeButton.removeActionListener(al);
		}
	}

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	// getter and setter

	public JButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}

	public JButton getPokeButton() {
		return pokeButton;
	}

	public void setPokeButton(JButton pokeButton) {
		this.pokeButton = pokeButton;
	}
}
