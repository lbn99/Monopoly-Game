
class BuyCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "BUY";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND))
		{
			//buy process code goes here
		}
	}
}