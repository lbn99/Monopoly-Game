public class StartTurnCommand extends ClientListenerAdapter
{
	//THIS IS USED TO END TURN THE CLIENT
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