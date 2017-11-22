package pokemon.models;

import java.util.Random;


/**
 * the Board...as usual coordinate (0,0) is top left 
 * here the grid is thought of and the pokemon is put randomly within the grid
 * it contains the game logic
 * @author Laura
 *
 */
public class BoardModel {
	//TODO remove

	private static int height;
	private static int width; 
	
	private PokemonModel poke;
	
	public BoardModel(int h, int w){
		height = h;
		width = w;
		generateNewPoke();
	}
	
	public void generateNewPoke(){
		Random rn = new Random();
		int posX = rn.nextInt(width);
		int posY = rn.nextInt(height);
		poke = new PokemonModel(posX, posY);
	}
	
	//getter and setter

	public static int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public PokemonModel getPoke() {
		return poke;
	}

	
	
}

