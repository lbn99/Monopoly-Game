public class StartTurnCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "STARTTURN";
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
		//	String[] s = message.split(" ");
		//	boolean turn = boolean.parseBoolean(s[1]);
		//	client.setTurn(turn);
		}
	}
		
}