package pokemon.views;

import javax.swing.*;

/**
 * this class generates the player information and holds methods to change name and score of a player
 * @author Laura
 *
 */
public class PlayerView extends JPanel {
	private JLabel scoreLabel = new JLabel(" Your Score is :  0");
	private JLabel playerLabel = new JLabel(" You are :  ");
	PlayerView(String name){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setSize(100, 50);
		setPlayerName(name);
		
		this.add(playerLabel);
		this.add(scoreLabel);

		this.setVisible(true);
	}

	public void setScoreLabel(int i) {
		scoreLabel.setText(" Your Score is :   " + i );
	}
	public void setPlayerName(String n) {
		playerLabel.setText(" You are :   " + n );
	}

}