import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Presentation extends JFrame{
	
	JPanel panel;
	JLabel questions; // Poses a question in each round
	JButton[] options; // An array of size 4 that holds a button for each of the 4 possible answers
	JButton next;
	JButton keepMoney;
	JButton lastOptionPressed; // The last button from the options that were pressed
	JButton[] referanceSavor;
	
	// ---- Get help -----
	JButton callFriend;  
	JButton statistics;
	JButton fiftyFifty;
	
	JLabel answers; // Poses the answer to either the callFriendsAns or statAnswer 
	String[] callFriendAns = {"Didn't you take chemistry? The answer is 1", "WW1 started at 1914", "I am not sure but I think it's the ear", 
	"I don't know. 300 maybe?", "Oh, 1945", "Da Vinci I think", "Diamond", "Who? by the name I think German...", 
	"It is definitely the Thames", "I don't know but good luck!"}; // Place 0 in answers
	String[] statAnswer = {"80% Sais it is 1", "55% picked 1914", "90% chosed ear", "40% picked 300", "61% sais 1945", "33% picked Da Vinci", 
	"100% chose diamond", "50% chose German and 50% chose Italian", "88% picked the Thames", "33% chose blue, 33% chose red and 33% chose yellow"}; // Place 1 in answers
	
	//----- Text for labels and buttons ----
	//---------------------
	String[] quesStrings = {"How many electrons does a Hydrogen atom have?", "When did the First World War start?",
	"Where is the smallest bone in the body?", "What does the roman numeral C represent?", "When did the Second World War end?", 
	"Who painted the Sistine Chapel?", "What is the hardest rock?", "What nationality was Marco Polo?", 
	"Which river goes through London?", "What color is Conalt?"};
	String[] opt1Str =  {"0", "1914", "Node", "50", "1944", "Michelangelo", "Crystal", "Spenish", "Havel", "Blue"};
	String[] opt2Str =  {"1", "1913", "Finger", "100", "1946", "Da Vinci", "Salt Rock", "Italian", "Rhine", "Red"};
	String[] opt3Str =  {"2", "1912", "Ear", "300", "1945", "Van Gogh", "Lime Stone", "French", "Seine", "Green"};
	String[] opt4Str =  {"3", "1916", "Baby Finger", "500", "1947", "Dali", "Diamond", "German", "Thames", "Yellow"};
	String[] answersArray = {"Good Job! You Won ", "Awesome! You got ", "Great! Earned ", "Good work! You now have ", 
			"Excellent! You have been upgraded to ", "Nice! The current balance is ", 
			"Keep up the good work! Total amount earned is ", "Wow! You might get all the way to the top! Currently got ",
			"Just one last step to go. Do you continue or back up?", "Amazing!! You won "};
	//---------------------
	String[][] optStrArray = {opt1Str, opt2Str, opt3Str, opt4Str}; // 2D array to hold all String options for 4 buttons
	//---------------------
	
	final int[] numericAns = {1, 0, 2, 1, 2, 0, 3, 1, 3, 0}; // the number in the row correlates to the correct answer in 
															 // optStrArray[numericAns][j]
	public final int[] earnings = {100, 500, 1500, 10000, 50000, 100000, 250000, 500000, 750000, 1000000};
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	//---------------------

	public Presentation (ActionListener l) {
		super("Who wants to be a millionaire");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(Presentation.WIDTH, Presentation.HEIGHT));
       // this.setLayout(new GridLayout(6, 1, 1, 1));
		panel = new JPanel(new GridLayout(6,1,1,1));
		createButtons(l);
        setLabel();
        addToPanel(0); // Adds the buttons and the labels to a panel
        referanceSavor = new JButton[2];
	} 
	
	/**
	 * Initializes and sets the buttons and button-strings of this class
	 * */
	private void createButtons (ActionListener l) {
		this.lastOptionPressed = new JButton();
		
		callFriend = new JButton("Call a friend");
		callFriend.addActionListener(l);
		callFriend.setBackground(Color.RED);
		callFriend.setOpaque(true);
		callFriend.setBorderPainted(false);
		
		statistics = new JButton("Statistics");
		statistics.setBackground(Color.RED);
		statistics.addActionListener(l);
		statistics.setOpaque(true);
		statistics.setBorderPainted(false);
		
		
		fiftyFifty = new JButton("50 / 50");
		fiftyFifty.addActionListener(l);
		fiftyFifty.setBackground(Color.RED);
		fiftyFifty.setOpaque(true);
		fiftyFifty.setBorderPainted(false);
		
		next = new JButton("Next Question");
		next.addActionListener(l);
		next.setForeground(Color.WHITE);
		next.setBackground(Color.BLACK);
		next.setOpaque(true);
		next.setBorderPainted(false);
		
		keepMoney = new JButton("Keep Money and Quit");
		keepMoney.addActionListener(l);
		keepMoney.setForeground(Color.WHITE);
		keepMoney.setBackground(Color.BLACK);
		keepMoney.setOpaque(true);
		keepMoney.setBorderPainted(false);
		
		
		options = new JButton[4];
		for (int i = 0; i < options.length; i ++) {
			options[i] = new JButton();
			options[i].addActionListener(l);
			options[i].setBackground(Color.LIGHT_GRAY);
			options[i].setOpaque(true);
		}
		
		setButtons(0);
	}
	
	/**
	 * Sets the text of the buttons based on the the index 'place'
	 * The 'i' is the row of optStrArray that specify one of the 4 opt#Str
	 * The 'place is the column of optStrArray that specify the exact location
	 * */
	public void setButtons (int place) {
		for (int i = 0; i < options.length; i ++) {
			options[i].setText(optStrArray[i][place]);
		}
	}
	
	/**
	 * Initializes the string of the labels of this class
	 * */
	private void setLabel () {
		questions = new JLabel();
		answers = new JLabel();
	} 
	
	/**
	 * Adds the buttons and the labels in place 'place' of their array to the panel, then to the JFrame 
	 * */
	private void addToPanel(int place) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.setLayout(new GridLayout(6,1,1,1));
		answers.setText("");
		
		JPanel helper = new JPanel(new GridLayout(1,1,1,1)); // First row, 1 column
		questions.setText(quesStrings[place]);
		helper.add(questions);
		panel.add(helper);

		helper = new JPanel (new GridLayout(1,2,1,1)); // Second row, 2 columns
		helper.add(options[0]);
		helper.add(options[1]);
		panel.add(helper);

		helper = new JPanel (new GridLayout(1,2,1,1)); // third row, 2 columns
		helper.add(options[2]);
		helper.add(options[3]);
		panel.add(helper);

		helper = new JPanel (new GridLayout(1,3,1,1)); // fourth row, 3 columns
		helper.add(callFriend);
		helper.add(statistics);
		helper.add(fiftyFifty);
		panel.add(helper);

		helper = new JPanel(new GridLayout(1,2,1,1)); // 5th
		next.setText("Next Question");
		helper.add(next);
		keepMoney.setText("Keep Money and Quit");
		helper.add(keepMoney);
		panel.add(helper);

		helper = new JPanel(new GridLayout(1,1,1,1)); // 6th
		answers = new JLabel("");
		helper.add(answers);
		panel.add(helper);

		this.add(panel);
	}
	
	/**
	 * Checks if the button pressed 'b' in the round number numRound is correct.
	 * Returns true if correct and false otherwise
	 * */
	public boolean checkAnswer(JButton b, int numRound) {
		int ans = numericAns[numRound];
		if (optStrArray[ans][numRound].equals(b.getText()))
			return true;
		return false;
	}
	
	/**
	 * Updates the view of the panel based on the current question problem 'place'
	 * Sets the buttons and labels accordingly
	 * */
	public void updatePanel (int place) {
		setButtons(place);
		questions.setText(quesStrings[place]);
	}
	
	/**
	 * The answer is correct so the method paints the background of the correct button to green,
	 * waits 2 seconds and moves on to the next question.
	 * */
	public void correct1 (JButton b, int round) {
		b.setBackground(Color.GREEN);
		b.setOpaque(true);
		b.setBorderPainted(false);
		answers.setText(answersArray[round] + "$" + earnings[round]);
		lastOptionPressed = b;
	}
	
	/**
	 * Prepares the panel for next question
	 * */
	public void correct2 () {
		lastOptionPressed.setBackground(Color.LIGHT_GRAY);
		lastOptionPressed.setBorderPainted(true);
		answers.setText("");
	}
	
	/**
	 * Reactive the buttons that their action listener was removed when player played 50 / 50
	 * */
	public void correct3 (ActionListener l) {
		for (int i = 0; i < referanceSavor.length; i ++) {
			referanceSavor[i].addActionListener(l);
			referanceSavor[i].setBackground(Color.LIGHT_GRAY);
			referanceSavor[i].setBorderPainted(true);
		}
	}
	
	/**
	 * Changes the current display of the panel. 
	 * Show the player the amount won and adds a button to exit the game.
	 * The panel uses GridLayout 
	 * */
	public void keepMoneyChooseMove(int earn){
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		panel.setLayout(new GridLayout(5,1,1,1));
		answers.setText("          Thank you for playing! You won $" + earn );
		answers.setFont(new Font("Arial", Font.PLAIN, 20));
		answers.setAlignmentX(CENTER_ALIGNMENT);
		keepMoney.setText("Exit");
		next.setText("Restart");

		JPanel helper = new JPanel(new GridLayout(1,1,1,1));
		helper.add(new JLabel (""));
		panel.add(helper);
		
		helper = new JPanel(new GridLayout(1,1,1,1));
		helper.add(answers);
		panel.add(helper);
	
		helper = new JPanel(new GridLayout(1,3,1,1));
		helper.add(new JLabel (""));
		helper.add(next);
		helper.add(new JLabel (""));
		panel.add(helper);
	
		
		helper = new JPanel(new GridLayout(1,3,1,1));
		helper.add(new JLabel (""));
		helper.add(keepMoney);
		helper.add(new JLabel (""));
		panel.add(helper);
	
		this.add(panel);
	}
	
	/**
	 * Sets the panel display to show a label "Sorry, you lost!, Better luck next time!", an exit bottun and a restart button
	 * */
	public void userLost () {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		
		panel.setLayout(new GridLayout(5,1,1,1));
		answers.setText("          Sorry, you lost!, Better luck next time!");
		answers.setFont(new Font("Arial", Font.PLAIN, 20));
		answers.setAlignmentX(CENTER_ALIGNMENT);
		keepMoney.setText("Exit");
		next.setText("Restart");

		JPanel helper = new JPanel(new GridLayout(1,1,1,1));
		helper.add(new JLabel (""));
		panel.add(helper);
		
		helper = new JPanel(new GridLayout(1,1,1,1));
		helper.add(answers);
		panel.add(helper);
		
		helper = new JPanel(new GridLayout(1,3,1,1));
		helper.add(new JLabel (""));
		helper.add(next);
		helper.add(new JLabel (""));
		panel.add(helper);
	
		helper = new JPanel(new GridLayout(1,3,1,1));
		helper.add(new JLabel (""));
		helper.add(keepMoney);
		helper.add(new JLabel (""));
		panel.add(helper);
		
		this.add(panel);
	}
	
	/**
	 * Resets the panel of this game to its initial position
	 * */
	public void reset (ActionListener l) {
		addToPanel(0);
		setButtons(0);
		resetHelpButtons(l);
	}
	
	/**
	 * Sets the text when a help of a friend is called.
	 * In addition, removes the action listener from the button and colors it in a different color
	 * */
	public void friendCalled (int place, ActionListener l) {
		answers.setText(callFriendAns[place]);
		callFriend.removeActionListener(l);
		callFriend.setBackground(Color.GRAY);
	}
	
	/**
	 * Sets the text when statistics is being asked for
	 * */
	public void stats(int place, ActionListener l) {
		answers.setText(this.statAnswer[place]);
		statistics.removeActionListener(l);
		statistics.setBackground(Color.GRAY);
	}
	
	/**
	 * The played chose the 50/ 50 help
	 * Half the buttons will be disabled for the round 'place'
	 * */
	public void pressedFiftyHelp (ActionListener l, int place) {
		
		int right = numericAns[place];
		int count = 0;
		for (int i=0; i < options.length && count < 2; i++) {
			if (i != right) {
				options[i].removeActionListener(l);
				options[i].setText("");
				options[i].setBackground(Color.WHITE);
				referanceSavor[count] = options[i];
				count ++;
			}
				
		} 
		this.fiftyFifty.removeActionListener(l);
		fiftyFifty.setBackground(Color.GRAY);
	}
	
	/**
	 * Resets the helping buttons to their original state and adds an action listener to them
	 * */
	public void resetHelpButtons(ActionListener l) {
		callFriend.addActionListener(l);
		callFriend.setBackground(Color.RED);
		statistics.setBackground(Color.RED);
		statistics.addActionListener(l);
		fiftyFifty.addActionListener(l);
		fiftyFifty.setBackground(Color.RED);
	}	
	
}

