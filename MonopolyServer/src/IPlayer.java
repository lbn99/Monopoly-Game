import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public interface IPlayer
{
	//This player would have a constructor that will take "name" for input
	//also inti propertyList in the const.

	//this would add the property to the list
	public void add(IPropertyCard p);

	//this returns the list of the IPropCard that the player has
	public IPropertyCard[] getCards();

	//this removes certain card from the list 
	public IPropertyCard remove(IPropertyCard p);

	//this transacts money in or out of the account and returns the new amount
	public void transact(int d);

	//this returns the current amount of money
	public int getMoney();

	//this keeps track whehter it's this player's turn
	public void setTurn(boolean b);

	//this returns if it's his turn
	public boolean getTurn();

	//this lets palyer buy and it adds to the list
	public void buy(IPropertyCard p);
	
	//This is called when you want to send data to the server
	//Before sending data to the server, you should call the send method on all INetworkListener objects that are listening to this client
	public void send(String data);
	
	//This is called when data is received from the server
	//This data should be passed to all of the INetworkListener objects that are listening to this client
	public void process(String str);

	//This adds the parameter INetworkListener to a list of INetworkListeners.
	//Everything in that List is considered to be "listening" to this Client
	//Objects that are listening to this Client are able to respond to send and process events
	public void addNetworkListener(INetworkListener listener);
	
	//This has an "infinite" loop that listens for data from the server
	public void run();
	
	//This makes the run method stop it's "infinite" loop
	public  void stop();
	
	//Return the handle of this client
	public String getHandle();
	
	//Set the handle of this client
	public void setHandle(String s);
	
	//Return the ID of this client
	public int getId();
	
	//this sets the position of the player
	public void setLocation(Point p);
	
	//this returns the position of the player
	public Point getLocation();

	//this returns the number of the card that player is on.
	public int getCardOn();

	//this sets the new card number
	public void setCardOn(int i);

	//each player would have a IPiece which would repesent each player on the board and will have the same position
}