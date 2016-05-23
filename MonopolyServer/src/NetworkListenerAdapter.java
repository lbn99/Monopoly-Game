import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class NetworkListenerAdapter implements INetworkListener 
{
	public boolean isCommand(String message, String cmd)
	{
		String[] parts = message.split(" ", 2);
		return parts[0].toUpperCase().equals(cmd.toUpperCase());
	}
	
	//This is called when a player receives a message from the Server
	public void process(String message, IPlayer player) {}
	
	//This is called when a server received a message from a player
	public void process(String message, IPlayer player, IServer server) {}
	
	//This is called before a player sends a message to the Server
	public void send(String message, IPlayer player) {}
	
}
