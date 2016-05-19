public class MoveCommand() extends NetworkListenerAdapter
{
	public static String COMMAND = "MOVE";
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			String[] s = message.split(" ")
			String name = s[1];
			int x = Integer.parseInt(s[2]);
			int y = Integer.parseInt(s[3]);
			client.setPlayerLocation(name, x, y);
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