import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener{
	Mod model;
	Presentation present;
	boolean clicked = false;
	boolean fiftyPlayed = false;
	//JButton lastOptionPressed;
	
	Controller (){
		model = new Mod();
		present = new Presentation(this);
		
	}
	
	public Presentation getPresentation () {
		return this.present;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand() == "Next Question" && clicked) {
			present.correct2();
			present.updatePanel(Mod.numRoundsPlayed);
			clicked = false;
			if (fiftyPlayed) {
				present.correct3(this);
				fiftyPlayed = false;
			}
		}
		else if (e.getActionCommand() == "Next Question") {} // Does nothing
		else if (e.getActionCommand() == "Keep Money and Quit") {
			present.keepMoneyChooseMove(model.amountEarned);
			if (fiftyPlayed) {
				present.correct3(this);
				fiftyPlayed = false;
			}
		}
		else if (e.getActionCommand() == "Exit") {
			present.setVisible(false);
			present.dispose();
			System.exit(0);
		}
		else if (e.getActionCommand() == "Restart") {
			present.reset(this);
			model.setNumRounds(0);
			present.correct2();
			present.updatePanel(Mod.numRoundsPlayed);
		}
		else if (e.getActionCommand() == "Call a friend" && !clicked) {
			present.friendCalled(Mod.numRoundsPlayed, this);
		}
		else if (e.getActionCommand() == "Statistics" && !clicked) {
			present.stats(Mod.numRoundsPlayed, this);
		}
		else if (e.getActionCommand() == "50 / 50" && !clicked) {
			present.pressedFiftyHelp(this, Mod.numRoundsPlayed);
			fiftyPlayed = true;
		}
		else if (clicked) {}
		// Check if the user is correct
		else {
				if (model.move((JButton) e.getSource(), present)) {
					present.correct1((JButton) e.getSource(), Mod.numRoundsPlayed-1);
					clicked = true;
					if (Mod.numRoundsPlayed == 10) {
						present.keepMoneyChooseMove(model.amountEarned);
					}
				}	
				else {
					present.userLost();
				}
			}
			
	}
}