import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.Point;

public class GameClient implements Runnable, IClient
{
	//Instance Variables	
	private Socket socket;						//Object that represents the connection to the Server
	private BufferedReader in;					//Object used to read data send from the Server (if connected)
	private PrintWriter out;					//Object used to send data to the Server (if connected)
	private boolean running;					//is the Thread is currently running?
	private List<INetworkListener> listeners;	//List of all INetworkListener objects that are listening to this client
	private String handle;
	private HashMap<String, Point> playerLocation;
	
	public GameClient(String ip, int port) throws UnknownHostException, IOException
	{
		//Initialize the instance variables
		socket = new Socket(ip, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
		listeners = new ArrayList<INetworkListener>();
		playerLocation = new HashMap<String, Point>();
	}

	//Methods (defined in interfaces: IClient and Runnable)
	
	//This is called when you want to send data to the server
	//Before sending data to the server, you should call the send method on all INetworkListener objects that are listening to this client
	public void send(String data){
		out.println(data);
	}
	
	//This is called when data is received from the server
	//This data should be passed to all of the INetworkListener objects that are listening to this client
	public void process(String str){
		for(INetworkListener l : listeners)
			l.process(str, this);
	}

	//This adds the paramter INetworkListener to a list of INetworkListeners.
	//Everything in that List is considered to be "listening" to this Client
	//Objects that are listening to this Client are able to respond to send and process events
	public void addNetworkListener(INetworkListener listener){
		listeners.add(listener);
	}
	
	//This has an "infinite" loop that listens for data from the server
	public void run(){
		while(running){
			try{
				process(in.readLine());
				System.out.println("Message: " + in.readLine());
			}
			catch(Exception e){
				System.out.println("ERROR: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	//This makes the run method stop it's "infinite" loop
	public  void stop(){
		running = false;
	}
	
	public String getHandle(){
		return handle;
	}
	
	public void setHandle(String s){
		handle = s;
	}
	
	public int getId(){ return 0; }
	
	public void setPlayerLocation(String pName, int x, int y){
//		playerLocation.
	}
	public void setTurn(boolean isTurn){
		
	}
}

