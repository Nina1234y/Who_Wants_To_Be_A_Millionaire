import javax.swing.JButton;

public class Mod {

	public static int numRoundsPlayed; 
	public int amountEarned;
	
	/**
	 * Initializes class's fields
	 * */
	public Mod () {
		Mod.numRoundsPlayed = 0;
		amountEarned = 0;
	}
	
	/**
	 * One play of the player. The player pressed a button.
	 * This method checks if the player won or not
	 * */
	public boolean move (JButton b, Presentation p) {
		if (p.checkAnswer(b, numRoundsPlayed)) {
			amountEarned = p.earnings[Mod.numRoundsPlayed];
			Mod.numRoundsPlayed ++;
			System.out.println(Mod.numRoundsPlayed);
			return true;
		}
		
		amountEarned = 0;
		return false;
	}
	
	/**
	 * Return the current rounds played
	 * */
	public int getNumRounds () {
		return Mod.numRoundsPlayed;
	}
	
	/**
	 * Returns the current winning amount
	 * */
	public int getAmountEarned () {
		return amountEarned;
	}
	
	public void setNumRounds (int num) {
		Mod.numRoundsPlayed = num;
	}
}
