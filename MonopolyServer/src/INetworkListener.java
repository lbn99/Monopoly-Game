public interface INetworkListener 
{
	//This is called when a player receives a message from the Server
	public void process(String message, IPlayer player);
	
	//This is called when a server received a message from a player
	public void process(String message, IPlayer player, IServer server);
	
	//This is called before a player sends a message to the Server
	public void send(String message, IPlayer player);
	
}
