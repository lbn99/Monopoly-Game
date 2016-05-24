
class EndTurnCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "ENDTURN";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND))
		{
			if(player.getTurn()){
				//End Turn process code goes here
				player.setTurn(false);
				server.getNext().setTurn(true);
			}
		}
	}
}