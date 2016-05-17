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
	private State state;
	
    public FancyButton(String label, JLabel a, JLabel b, JTextArea c, BoardPanel d, State s) 
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
    	state = s;
    }
    
    public void setPlayers()
    {
    	
    }
    
    //This is called when anything that *this* is listening to triggers an action.
    //	ie: when a button is clicked, anything that is "listening" to that button will have its actionPerformed method called
    //		or when a checkbox is checked, etc...
    public void actionPerformed(ActionEvent e)
    {
    	Random rand = new Random();
    	int roll = rand.nextInt(6) + 1;
    	switch(roll){
    		case 1: diceA.setIcon(a1); break;
    		case 2: diceA.setIcon(a2); break;
	    	case 3: diceA.setIcon(a3); break;
	    	case 4: diceA.setIcon(a4); break;
	    	case 5: diceA.setIcon(a5); break;
	    	case 6: diceA.setIcon(a6); break;
    	}
    	int roll2 = rand.nextInt(6) + 1;
    	switch(roll2){
    		case 1: diceB.setIcon(a1); break;
    		case 2: diceB.setIcon(a2); break;
	    	case 3: diceB.setIcon(a3); break;
	    	case 4: diceB.setIcon(a4); break;
	    	case 5: diceB.setIcon(a5); break;
	    	case 6: diceB.setIcon(a6); break;
    	}
    	int move = roll + roll2, moveX = 0, moveY = 0;
    	while(move > 0){
			System.out.println(moveX + " " + moveY);
    		if((int)state.getPoint().getX() > 106 && (int)state.getPoint().getY() == 485)
    			moveX -= 41;
    		else if((int)state.getPoint().getX() < 106 && (int)state.getPoint().getX() > 65 && (int)state.getPoint().getY() == 485)
    			moveX -= 65;
    		else if((int)state.getPoint().getX() < 65 && (int)state.getPoint().getY() == 485)
    			moveY -= 65;
    		else if((int)state.getPoint().getX() < 65 && (int)state.getPoint().getY() > 106)
    			moveY -= 41;
    		else if((int)state.getPoint().getX() < 65 && (int)state.getPoint().getY() < 106 && (int)state.getPoint().getY() > 65)
    			moveY -= 100;
    		move--;
    	}
    	state.setPoint(new Point((int)state.getPoint().getX() + moveX, (int)state.getPoint().getY() + moveY));
    	board.repaint();
    	if(roll == roll2)
    	{
    		message.setText("");
    	}
    		
    }
}