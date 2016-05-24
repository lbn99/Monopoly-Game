import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class ClientListenerAdapter implements IClientListener 
{
	public boolean isCommand(String message, String cmd)
	{
		String[] parts = message.split(" ", 2);
		return parts[0].toUpperCase().equals(cmd.toUpperCase());
	}
	
	//This is called when a client receives a message from the Server
	public void process(String message, IClient client) {}
	
	//This is called before a client sends a message to the Server
	public void send(String message, IClient client) {}
	
}
