import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class ServerSidePlayer implements Runnable, IPlayer
{
	private String handle;
	private int id;
	private IServer server;
	private Socket socket;
	private boolean running;
	private ArrayList<INetworkListener> listenerList;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<IPropertyCard> properties;
	private int money;
	private boolean turn;
	private int xpos;
	private int ypos;
    private int cardOn;
	
    public ServerSidePlayer(int id, IServer server, Socket socket, int x, int y) throws IOException
    {
    	Random rand = new Random();
    	this.id = id;
    	this.server = server;
    	this.socket = socket;
    	running = true;
    	money = 1500;
    	turn = false;
    	xpos = x;
    	ypos = y;
        cardOn = 0;
    	in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    	out = new PrintWriter(socket.getOutputStream(), true);
    	listenerList = new ArrayList<INetworkListener>();
    	Thread t = new Thread(this);
    	t.start();
    	
    	handle = "Player" + (rand.nextInt(900000)+100000);
        IPlayer[] tempList = server.getClients();
        //Sending all the players the id of this player.
        for(int i = 0; i < tempList.length; i++){        
                tempList[i].send("ADD " + this.getId()+ " " + xpos + " " + ypos);
        }
    	this.send("MOVE "+ id+ " " + xpos + " " + ypos);
//		addNetworkListener();
    }

    public String getHandle()
    {
    	return handle;
    }
    
    public void setHandle(String h)
    {
    	handle = h;
    }
    
    public void addNetworkListener(INetworkListener listener)
	{
		listenerList.add(listener);
	}
    
    public int getId()
    {
    	return id;
    }
    
    public void run()
    {
    	while(running){
    		try{
    			process(in.readLine());
    		}
    		catch(Exception e) { 
    			stop();
    		//	server.remove(this);
    		}
    	}
    }
    
    public void stop()
    {
    	running = false;
    }
    
    //data from server (to client)
    public void send(String data)
    {
    	System.out.println("SSP> send "+ data);
    	out.println(data);
    }
    
    //data from client (to server)
    public void process(String message)
    {
    	System.out.println("SSP>" + message);
        if(message.contains("SETHANDLE")){
            this.setHandle(message.substring(10));
        }else{
    	   for(INetworkListener l : listenerList)
    		  l.process(message, this, server);
        }
    }
    
    //this would add the property to the list
	public void add(IPropertyCard p){
		properties.add(p);
	}

	//this returns the list of the IPropCard that the player has
	public IPropertyCard[] getCards(){
		return (IPropertyCard[])properties.toArray(new IPropertyCard[]{});
	}

	//this removes certain card from the list 
	public IPropertyCard remove(IPropertyCard p){
		for(int i = 0; i < properties.size(); i++)
		{
			if(properties.get(i).getName().equals(p.getName()))
				return properties.remove(i);
		}
		return null;
	}

	//this transacts money in or out of the account and returns the new amount
	public void transact(int d){
		this.money += d;
	}

	//this returns the current amount of money
	public int getMoney(){
		return this.money;
	}

	//this keeps track whehter it's this player's turn
	public void setTurn(boolean b){
		turn = b;
	}

	//this returns if it's his turn
	public boolean getTurn(){
		return turn;
	}

	//this lets palyer buy and it adds to the list
	public void buy(IPropertyCard p){
		properties.add(p);
	}
	
	public void setLocation(Point p){
		
		this.xpos = (int) p.getX();
		this.ypos = (int) p.getY();
	}
	
	public Point getLocation(){
		return new Point(this.xpos, this.ypos);
	}

    //this returns the number of the card that player is on.
    public int getCardOn(){
        return this.cardOn;
    }

    //this sets the new card number
    public void setCardOn(int i){
        this.cardOn = i;
    }
}