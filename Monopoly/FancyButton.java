import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Point;

public class FancyButton 
	extends JButton 			//FancyButton is-a JButton
	implements ActionListener	//FancyButton performs an action when a button is clicked
{
	private JLabel diceA; //a pointer to a TextArea that this button can modify when clicked
	private JLabel diceB;
	private ImageIcon a1;
	private ImageIcon a2;
	private ImageIcon a3;
	private ImageIcon a4;
	private ImageIcon a5;
	private ImageIcon a6;
	private JTextArea message;
	private BoardPanel board;
	private IClient client;
	
    public FancyButton(String label, JLabel a, JLabel b, JTextArea c, BoardPanel d, IClient cc) 
   {
    	super(label);	//Give the label to the JButton constructor to deal with
    	
    	//make this button listen to itself when it is clicked
    	addActionListener(this);
    	
    	//initialize the area instance variable 
    	diceA = a;
    	diceB = b;
    	a1 = new ImageIcon("../dice1.jpg");
    	a2 = new ImageIcon("../dice2.jpg");
    	a3 = new ImageIcon("../dice3.jpg");
    	a4 = new ImageIcon("../dice4.jpg");
    	a5 = new ImageIcon("../dice5.jpg");
    	a6 = new ImageIcon("../dice6.jpg");
    	message = c;
    	board = d;
    	client = cc;
    }
    
    //This is called when anything that *this* is listening to triggers an action.
    //	ie: when a button is clicked, anything that is "listening" to that button will have its actionPerformed method called
    //		or when a checkbox is checked, etc...
    public void actionPerformed(ActionEvent e)
    {
    	int roll = (int)(Math.random() * 5) + 1;
    	switch(roll){
    		case 1: diceA.setIcon(a1); break;
       		case 2: diceA.setIcon(a2); break;
	    	case 3: diceA.setIcon(a3); break;
	    	case 4: diceA.setIcon(a4); break;
	    	case 5: diceA.setIcon(a5); break;
	    	case 6: diceA.setIcon(a6); break;
    	}
    	int roll2 = (int)(Math.random() * 5) + 1;
    	switch(roll2){
    		case 1: diceB.setIcon(a1); break;
    		case 2: diceB.setIcon(a2); break;
	    	case 3: diceB.setIcon(a3); break;
	    	case 4: diceB.setIcon(a4); break;
	    	case 5: diceB.setIcon(a5); break;
	    	case 6: diceB.setIcon(a6); break;
    	}
    	int move = roll + roll2;
        System.out.println("PC> @FancyButton the roll"+roll+ "and roll2"+roll2);
    	client.send("ROLL " + roll + " " + roll2);
    	
    	if(roll == roll2)
    	{
    		message.setText("");
    	}
    }
}