import java.util.*;
import java.awt.Point;
public class UpdateBoardCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "UPDATEBOARD";
	private BoardPanel panel;

	HashMap<String, Point> listofPlayersOnline;

	public UpdateBoardCommand(HashMap<String, Point> s,BoardPanel t){
		listofPlayersOnline = s;
			panel = t;
	}
	
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			System.out.println("In the updateboard command");
			panel.repaint();
			panel.validate();
		}
	}
}