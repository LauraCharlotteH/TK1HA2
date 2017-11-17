
package pokemon.models;

/**
 * contains Information and functions concerning one player
 * @author Laura
 *
 */
public class PlayerModel {
	
	private String name;
	private int score;
	
	/**
	 * Creats a new player setting the initial score to zero
	 * @param name the name of the new player
	 */
	public PlayerModel(String name){
		this.setName(name);
		this.setScore(0); 
	}
	/**
	 * increments score by one
	 */
	public void plusScore(){
		this.setScore(this.getScore() + 1);
	}

	// all getter and setters
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	private void setScore(int score) {
		this.score = score;
	}
}