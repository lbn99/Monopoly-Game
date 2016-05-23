import java.util.*;
import java.awt.Point;

public class MoveCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "MOVE";
	HashMap<String, Point> listofPlayersOnline;
	public MoveCommand(HashMap<String, Point> s){
		listofPlayersOnline = s;
	}
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			System.out.println("@move commnad the message s " + message);
			String[] s = message.split(" ");
			String name = s[1];
			int x = Integer.parseInt(s[2]);
			int y = Integer.parseInt(s[3]);
			System.out.println("@move command the name is " + s[1] + " " + x + " " + y);
			listofPlayersOnline.put(name, new Point(x,y));
			client.process("UPDATEBOARD");
		}
	}
		
}



/*import javax.swing.JTextArea;

class SayCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "SAY";
	private JTextArea j;
	
	public SayCommand(JTextArea j)
	{
		this.j = j;
	}
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			//process the Say comment
			//SAY # handle message
			//Example: 
			//		SAY 5 MrMayComputer Science!
			
			String[] split = message.split(" ", 3);
			int length = Integer.parseInt(split[1]);
			String name = split[2].substring(0, length);
			String say = split[2].substring(length);
			j.append(name + ": " + say + "\n");	
		}
	}
}*/
