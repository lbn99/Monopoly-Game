import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Point;

public class EndTurnButton 
	extends JButton 			//FancyButton is-a JButton
	implements ActionListener	//FancyButton performs an action when a button is clicked
{
	private IClient client;
	
    public EndTurnButton(String label, IClient cc) 
   {
    	super(label);	//Give the label to the JButton constructor to deal with
    	
    	//make this button listen to itself when it is clicked
    	addActionListener(this);
    	
    	//initialize the area instance variable 
   
    	client = cc;
    }
    
    //This is called when anything that *this* is listening to triggers an action.
    //	ie: when a button is clicked, anything that is "listening" to that button will have its actionPerformed method called
    //		or when a checkbox is checked, etc...
    public void actionPerformed(ActionEvent e)
    {
    		client.send("ENDTURN");
    }
}