package pokemon.views;
import java.util.ArrayList;

import javax.swing.*;

import pokemon.controllers.Player;

/**
 * this class simply renders all Players on screen
 * @author Laura
 *
 */
public class AllPlayersView extends JPanel {
	private JLabel scoreLabel = new JLabel(" Your Score is :  0");
	private JLabel playerLabel = new JLabel(" You are :  ");
	
	AllPlayersView(ArrayList<Player> players){
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setSize(100, 50);
		for (Player p : players) {
			JLabel nameLabel = new JLabel(p.getName()+" :");
			JLabel playerInfoLabel = new JLabel("caught "+p.getScore()+" pokemon");
			this.add(nameLabel);
			this.add(playerInfoLabel);
		}

		this.setVisible(true);
	}

}