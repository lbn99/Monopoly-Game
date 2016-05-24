import java.util.*;
import java.awt.Point;

public class AddCommand extends ClientListenerAdapter
{
	public static String COMMAND = "ADD";

	HashMap<String, Point> listofPlayersOnline;
	public AddCommand(HashMap<String, Point> s){
		listofPlayersOnline = s;
	}
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			message = message.substring(4);
			String[] todo = message.split(" ");
			listofPlayersOnline.put(todo[0],new Point(Integer.parseInt(todo[1]),Integer.parseInt(todo[2])));
			client.process("UPDATEBOARD");
		}
	}
}