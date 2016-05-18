import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Monopoly extends JFrame
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		//Create a Frame to hold everything
		Monopoly frame = new Monopoly();
		
		//Clicking the [X] button stops your program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//how big should hte JFrame be?
		frame.setSize(750, 700);
		
		//Do you want to be able to see the Frame?
		frame.setVisible(true);
	}
	
	public Monopoly()
	{
		//Set the layout of this Frame
		setLayout(new BorderLayout());
		
		JPanel panel2 = new JPanel();
		
		State state = new State(new Point(436, 485));
		
		panel2.setLayout(new BorderLayout());
		
		this.add(panel2, BorderLayout.CENTER);
		
		BoardPanel board = new BoardPanel(state);
		panel2.add(board, BorderLayout.CENTER);
		
		JTextArea players = new JTextArea(50, 15);
		this.add(new JScrollPane(players), BorderLayout.WEST);
		players.setEditable(false);
		
		//Create a Panel that has its own layout and can have components added to it
		JPanel bottom = new JPanel();
	
		//Set the layout of the sidepanel JPanel
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		
		//Add the sidepanel to this Frame (on the left side)
		panel2.add(bottom, BorderLayout.SOUTH);
		
		JTextArea cardMessage = new JTextArea(3, 3);
		panel2.add(cardMessage, BorderLayout.NORTH);
		cardMessage.setEditable(false);
		
		ImageIcon b = new ImageIcon("../blank.png");
		JLabel dice1 = new JLabel(b);
	
		ImageIcon c = new ImageIcon("../blank.png");
		JLabel dice2 = new JLabel(c);
		
		JButton roll = new FancyButton("Roll", dice1, dice2, cardMessage, board, state);
		bottom.add(roll);
		
		bottom.add(dice1);
		bottom.add(dice2);
		
		JButton buy = new JButton("Buy");
		bottom.add(buy);
		
		JButton endTurn = new JButton("End Turn");
		bottom.add(endTurn);
	}
}
