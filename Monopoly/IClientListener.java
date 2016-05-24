public interface IClientListener
{
	//This is called when a client receives a message from the Server
	public void process(String message, IClient client);
	
	//This is called before a client sends a message to the Server
	public void send(String message, IClient client);
	
}
