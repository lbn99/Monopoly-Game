public class GameStartCommand extends ClientListenerAdapter
{
	public static String COMMAND = "GAMESTART";
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			String[] s = message.split(" ");
			System.out.println(message +  " " + s[1] + " @gamestartcommand");
			if(s[1].equals("true"))
				client.setStart(true);
			else
				client.setStart(false);
		}
	}
}