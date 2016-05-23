import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;

public class Monopoly extends JFrame
{
	private HashMap<String, Point> playerLocation;
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		//Create a Frame to hold everything
	//	Monopoly frame = new Monopoly("Monopoly!");
	}
	
	public Monopoly(String title, String ip, int port, String name)
	{
		//Set the title for the window
		super(title);
		
		//close when [x] is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//how big should hte JFrame be?
		setSize(750, 700);
		
		//Do you want to be able to see the Frame?
		setVisible(true);
		
		//Do not allow window size to be changed
		setResizable(false);
		
		//Set the layout of this Frame
		setLayout(new BorderLayout());
		try{
			GameClient playerClient =  new GameClient(ip, port, name);
			playerLocation = new HashMap<String, Point>();
			//panel for board
			JPanel panel2 = new JPanel();
			
			//NOTE: makeshift server for use in GUI (mostly fancybutton)
			State state = new State(new Point(436, 485));
			
			panel2.setLayout(new BorderLayout());
			
			this.add(panel2, BorderLayout.CENTER);
			
			//panel that displays board and draws player icons
			BoardPanel board = new BoardPanel(playerLocation);
			panel2.add(board, BorderLayout.CENTER);
			
			//textarea to display player info (name, amount of money, and property)
			JTextArea players = new JTextArea(50, 15);
			this.add(new JScrollPane(players), BorderLayout.WEST);
			players.setEditable(false);
			
			//Create a Panel that has its own layout and can have components added to it
			JPanel bottom = new JPanel();
		
			//Set the layout of the sidepanel JPanel
			bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
			
			//Add the sidepanel to this Frame (on the left side)
			panel2.add(bottom, BorderLayout.SOUTH);
			
			//textarea to display actions in game (such as "buy card?" or message of a chance/community chest card)
			JTextArea cardMessage = new JTextArea(3, 3);
			panel2.add(cardMessage, BorderLayout.NORTH);
			cardMessage.setEditable(false);
			
			//dice 1 image for roll
			ImageIcon b = new ImageIcon("../blank.png");
			JLabel dice1 = new JLabel(b);
			
			//dice 2 image for roll
			ImageIcon c = new ImageIcon("../blank.png");
			JLabel dice2 = new JLabel(c);
			
			//button to control movement of *this* player
			JButton roll = new FancyButton("Roll", dice1, dice2, cardMessage, board, state, playerClient);
			bottom.add(roll);
			
			bottom.add(dice1);
			bottom.add(dice2);
			
			//button to control buying of property for *this* player
			JButton buy = new JButton("Buy");
			bottom.add(buy);
			
			//button to control end turn for *this* player
			JButton endTurn = new JButton("End Turn");
			bottom.add(endTurn);
			
			this.repaint();
			this.validate();
			
			
			playerClient.addNetworkListener(new ListCommand(players));
			playerClient.addNetworkListener(new MoveCommand(playerLocation));
			playerClient.addNetworkListener(new UpdateBoardCommand(playerLocation,board));
			playerClient.addNetworkListener(new AddCommand(playerLocation));
			playerClient.addNetworkListener(new UpdateCommand(cardMessage));
			Thread t = new Thread(playerClient);
			t.start();
			
		}catch (Exception e){
			System.out.println("@Monopoly catch: Error is " + e.getMessage());
		}
	}
}