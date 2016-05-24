public interface INetworkListener
{	
	//This is called when a server received a message from a Client
	public void process(String message, IPlayer player, IServer server);	
}
