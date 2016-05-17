
class RollCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "ROLL";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND))
		{
			//buy process code goes here
			int x = (int)(player.getLocation().getX());
			int y = (int)(player.getLocation().getY());
			int rolled = Integer.parseInt(message.substring(5,6)) + Integer.parseInt(message.substring(7,8));
			
		}
	}
}