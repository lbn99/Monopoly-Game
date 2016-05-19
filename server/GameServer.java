import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.Point;

public class GameServer implements Runnable, IServer
{
	private int port; 
	private int id;
	private boolean running;
	private CircularLinkedList<IPlayer> playerList;
	private List<IPropertyCard> propertyList;
	
	public static void main(String[] args)
	{
		GameServer server = new GameServer(99);
		Thread t = new Thread(server);
		t.start();
		try{
		System.out.println("GameServer started\n" + 
			"Connected to: " + InetAddress.getLocalHost() + " and port 99");
		}
		catch(Exception e) { e.printStackTrace(); }
	}
    public GameServer(int port) 
    {
    	this.port = port;
    	running = true;
    	id = 0;
    	playerList = new CircularLinkedList<IPlayer>();
    	propertyList = new ArrayList<IPropertyCard>();
    	init();
    }
    
    public IPlayer[] getClients()
    {
    	IPlayer[] list = new IPlayer[playerList.size()];
    	for(int i = 0; i < playerList.size(); i++){
    		list[i] = playerList.get(i);
    	}
    	return list;
    }
    
    public void broadcast(String data)
    {
    	for(int i = 0; i < playerList.size(); i++)
    		playerList.get(i).send(data);
    }
    
    public void remove(IPlayer c)
    {
    	for(int i = 0; i < playerList.size(); i++){
    		if(playerList.get(i).getId() == c.getId()){
    			playerList.remove(i);
    			break;
    		}
    	}
    	System.out.println(c.getHandle() + " has disconnected");
    }
    
    public void run()
    {
    	Random rand = new Random();
    	try{
    		ServerSocket listener = new ServerSocket(port);
    		while(running){
    			Socket playerSocket = listener.accept();
    			//GET THE X AND Y FROM THE GUI TO KEEP TRACK OF THE POSITION...
    			IPlayer player = new ServerSidePlayer(nextID(), this, playerSocket,0,0);
    			playerList.add(player);
    			this.addListeners(player);
    			System.out.println(player.getHandle() + " connected");
    		}
    	}
    	catch(Exception e){ e.printStackTrace(); }
    }
    
    public void stop()
    {
    	running = false;
    }
    public int nextID(){
    	 return id++; 
   	}
   	
   	public void addListeners(IPlayer p){
   		p.addNetworkListener(new BuyCommand());
   	}

   	public void init(){
 		propertyList.add(new PropertyCard("Go",200,Color.WHITE, false, 0, new Point(435,435), 65,65, new int[0], "Corner",0));
 		propertyList.add(new PropertyCard("Mediterranean Ave.",60,Color.MAGENTA, true, 2, new Point(394,435), 41,65, new int[]{10,30,90,160,250}, "Monopoly",1));
 		propertyList.add(new PropertyCard("Community Chest",0,Color.WHITE, false, 0, new Point(353,435), 41,65, new int[0], "Other",2));
 		propertyList.add(new PropertyCard("Baltic Ave.",60,Color.MAGENTA, true, 4, new Point(312,435), 41,65, new int[]{20,60,180,320,450}, "Monopoly",3));
 		propertyList.add(new PropertyCard("Income Tax",-200,Color.WHITE, false, 0, new Point(271,435), 41,65, new int[0], "Other",4));
 		propertyList.add(new PropertyCard("Reading Railroad",200,Color.BLACK, true, 0, new Point(230,435), 41,65, new int[]{25,50,100,200}, "Railroad",5));
 		propertyList.add(new PropertyCard("Oriental Ave.",100,Color.CYAN , true , 6 , new Point(189,435), 41,65, new int[]{30,90,270,400,550}, "Monopoly",6));
 		propertyList.add(new PropertyCard("Chance",0,Color.WHITE, false, 0, new Point(148,435), 41,65, new int[0], "Other",7));
 		propertyList.add(new PropertyCard("Vermont Ave.",100,Color.CYAN, true, 6, new Point(107,435), 41,65, new int[]{30,90,270,400,500}, "Monopoly",8));
 		propertyList.add(new PropertyCard("Connecticut Ave.",120,Color.CYAN, true, 8, new Point(66,435), 41,65, new int[]{40,100,300,450,600}, "Monopoly",9));
 		propertyList.add(new JailCard("Jail",0,Color.WHITE, false, 0, new Point(0,435), 65,65, new int[0], "Jail",10));  //I have changed this to a JailCard which has some extra functions in it on top of the normal property card functions
 		propertyList.add(new PropertyCard("St. Charles Place",140,Color.PINK, true, 10, new Point(0,394), 65,41, new int[]{50,150,450,625,750}, "Monopoly",11));
 		propertyList.add(new PropertyCard("Electric Company",150,Color.WHITE, true, 0, new Point(0,353), 65,41, new int[0], "Utility",12));
 		propertyList.add(new PropertyCard("States Ave.",140,Color.PINK, true, 10, new Point(0,312), 65,41, new int[]{50,150,450,625,750}, "Monopoly",13));
 		propertyList.add(new PropertyCard("Virginia Ave.",160,Color.PINK, true, 12, new Point(0,271), 65,41, new int[]{60,180,500,700,900}, "Monopoly",14));
 		propertyList.add(new PropertyCard("Pennsylvania Railroad",200,Color.BLACK, true, 0, new Point(0,230), 65,41, new int[]{25,50,100,200}, "Railroad",15));
 		propertyList.add(new PropertyCard("St. James Place",180,Color.ORANGE, true, 14, new Point(0,189), 65,41, new int[]{70,200,550,750,950}, "Monopoly",16));
 		propertyList.add(new PropertyCard("Community Chest",0,Color.WHITE, false, 0, new Point(0,148), 65,41, new int[0], "Other",17));
 		propertyList.add(new PropertyCard("Tennessee Ave.",180,Color.ORANGE, true, 14, new Point(0,107), 65,41, new int[]{70,200,550,750,950}, "Monopoly",18));
 		propertyList.add(new PropertyCard("New York Ave.",200,Color.ORANGE, true, 16, new Point(0,66), 65,41, new int[]{80,220,600,800,1000}, "Monopoly",19));
 		propertyList.add(new PropertyCard("Free Parking",0,Color.WHITE, false, 0, new Point(0,0), 65,65, new int[0], "Other",20));
 		propertyList.add(new PropertyCard("Kentucky Ave.",220,Color.RED, true, 18, new Point(65,0), 41,65, new int[]{90,250,700,875,1050}, "Monopoly",21));
 		propertyList.add(new PropertyCard("Chance",0,Color.WHITE, false, 0, new Point(106,0), 41,65, new int[0], "Other",22));
 		propertyList.add(new PropertyCard("Indiana Ave.",220,Color.RED, true, 18, new Point(147,0), 41,65, new int[]{90,250,700,875,1050}, "Monopoly",23));
 		propertyList.add(new PropertyCard("Illinois Ave.",240,Color.RED, true, 20, new Point(188,0), 41,65, new int[]{100,300,750,925,1100}, "Monopoly",24));
 		propertyList.add(new PropertyCard("B & O Railroad",200,Color.BLACK, true, 0, new Point(229,0), 41,65, new int[]{25,50,100,200}, "Railroad",25)); 
 		propertyList.add(new PropertyCard("Atlantic Ave.",260,Color.YELLOW, true, 22, new Point(270,0), 41,65, new int[]{110,330,800,975,1150}, "Monopoly",26));
 		propertyList.add(new PropertyCard("Ventnor Ave.",260,Color.YELLOW, true, 22, new Point(311,0), 41,65, new int[]{110,330,800,975,1150}, "Monopoly",27));
 		propertyList.add(new PropertyCard("Water Works",150,Color.WHITE, true, 0, new Point(352,0), 41,65, new int[0], "Utility",28));
 		propertyList.add(new PropertyCard("Marvin Gardens",280,Color.YELLOW, true, 24, new Point(393,0), 41,65, new int[]{120,360,850,1025,1200}, "Monopoly",29));
 		propertyList.add(new PropertyCard("Go To Jail",0,Color.WHITE, false, 0, new Point(435,0), 65,65, new int[0], "GoToJail",30));
 		propertyList.add(new PropertyCard("Pacific Ave.",300,Color.GREEN, true, 26, new Point(435,65), 65,41, new int[]{130,390,900,1100,1275}, "Monopoly",31));
 		propertyList.add(new PropertyCard("North Carolina Ave.",300,Color.GREEN, true, 26, new Point(435,106), 65,41, new int[]{130,390,900,1100,1275}, "Monopoly",32));
 		propertyList.add(new PropertyCard("Community Chest",0,Color.WHITE, false, 0, new Point(435,147), 65,41, new int[0], "Other",33));
 		propertyList.add(new PropertyCard("Pennsylvania Ave.",320,Color.GREEN, true, 28, new Point(435,188), 65,41, new int[]{150,450,1000,1200,1400}, "Monopoly",34));
 		propertyList.add(new PropertyCard("Short Line",200,Color.BLACK, true, 0, new Point(435,229), 65,41, new int[]{25,50,100,200}, "Railroad",35));
 		propertyList.add(new PropertyCard("Chance",0,Color.WHITE, false, 0, new Point(435,270), 65,41, new int[0], "Other",36));
 		propertyList.add(new PropertyCard("Park Place",350,Color.BLUE, true, 35, new Point(435,311), 65,41, new int[]{175,500,1100,1300,1500}, "Monopoly",37));
 		propertyList.add(new PropertyCard("Chance",0,Color.WHITE, false, 0, new Point(435,352), 65,41, new int[0], "Other",38));
 		propertyList.add(new PropertyCard("Boardwalk",400,Color.BLUE, true, 50, new Point(435,393), 65,41, new int[]{200,600,1400,1700,2000}, "Monopoly",39));  	
 	}

    public IPropertyCard getCardAt(int i){
        return propertyList.get(i);
    }
	
	public IPlayer getNext(){
		return playerList.getNext();
	}
}