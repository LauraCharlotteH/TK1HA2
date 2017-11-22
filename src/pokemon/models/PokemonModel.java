package pokemon.models;

/**
 * The Pokemon Model knows its style and position and nothing else
 * @author Laura
 *
 */
public class PokemonModel {
	//TODO remove: die poke style sachen werden direkt in board view gemacht
	public enum pokeStyle{
		PIKACHU , EEVEE , CHARMANDER , SQUIRTLE , BULBASAUR
	}

	private int posX;
	private int posY;
	private pokeStyle style;
	private boolean isCaught; 
	
	/**
	 * generates a new pokemon of random type at a given position.
	 * The isCaught boolean is false
	 * @param x x-Value of position
	 * @param y y-value of position
	 */
	public PokemonModel(int x, int y){
		this.posX = x;
		this.posY = y;
		this.isCaught = false;
		int random =(int)(Math.random()*(5));
		style = pokeStyle.values()[random];
	}
	
	//getter and setter 
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public pokeStyle getStyle() {
		return style;
	}

	public void setStyle(pokeStyle style) {
		this.style = style;
	}

	public boolean isCaught() {
		return isCaught;
	}

	public void setCaught(boolean isCaught) {
		this.isCaught = isCaught;
	}
}
