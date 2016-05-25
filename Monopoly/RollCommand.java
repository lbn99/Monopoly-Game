import java.awt.*;
class RollCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "ROLL";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND)&& player.getTurn()&&!player.getOnceRolled())
		{
			System.out.println("@RollCommand");
			//roll and move process code goes here
			int x = (int)(player.getLocation().getX());
			int y = (int)(player.getLocation().getY());
			int roll1 = Integer.parseInt(message.substring(5,6));
			int roll2 = Integer.parseInt(message.substring(7,8));
			int move =  roll1 + roll2;
			int newPosition  = player.getCardOn() + move;
			if(newPosition>39){ newPosition -=40;}
			if(roll1!=roll2){player.setOnceRolled(true);}
			player.setCardOn(newPosition);
			IPropertyCard landedOn = server.getCardAt(newPosition);
			int newx = 0;
			int newy = 0;
			//process for horizontal and verticals sides and assigning new positions
	/*		if(!(landedOn.getType().equals("Corner"))) {
				if((newPosition/10)%2==0){
					newx = (landedOn.getX()) + (player.getId() * 10 + 1);
					newy = (landedOn.getY()) + 30;
				}
				else if((newPosition/10)%2==1){
					newx = (landedOn.getX()) + 5;
					newy = (landedOn.getY()) + (player.getId() * 10 + 1);
				}
				else{
					System.out.println("@RollCommand Cannot process newPosition variable.");
				}
			}
			*/
			if(!landedOn.getType().equals("Monopoly")){
				while(move > 0){
		    		if((int)player.getLocation().getX() > 106 && (int)player.getLocation().getY() == 485)
		    			player.setLocation(new Point((int)player.getLocation().getX() - 41, (int)player.getLocation().getY()));
		    		else if((int)player.getLocation().getX() < 106 && (int)player.getLocation().getX() > 65 && (int)player.getLocation().getY() == 485)
		    			player.setLocation(new Point((int)player.getLocation().getX() - 65, (int)player.getLocation().getY()));
		    		else if((int)player.getLocation().getX() < 65 && (int)player.getLocation().getY() == 485)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() - 65));
		    		else if((int)player.getLocation().getX() < 65 && (int)player.getLocation().getY() > 106)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() - 41));
		    		else if((int)player.getLocation().getX() < 65 && (int)player.getLocation().getY() < 106 && (int)player.getLocation().getY() > 65)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() - 90));
		    		else if((int)player.getLocation().getX() < 65 && (int)player.getLocation().getY() < 65)
		    			player.setLocation(new Point((int)player.getLocation().getX() + 65, (int)player.getLocation().getY()));
		    		else if((int)player.getLocation().getX() > 65 && (int)player.getLocation().getY() < 65 && (int)player.getLocation().getX() < 393)
		    			player.setLocation(new Point((int)player.getLocation().getX() + 41, (int)player.getLocation().getY()));
		    		else if((int)player.getLocation().getX() > 394 && (int)player.getLocation().getX() < 436 && (int)player.getLocation().getY() < 65)
		    			player.setLocation(new Point((int)player.getLocation().getX() + 65, (int)player.getLocation().getY()));
		    		else if((int)player.getLocation().getX() > 436 && (int)player.getLocation().getY() < 65)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() + 65));
		    		else if((int)player.getLocation().getX() > 436 && (int)player.getLocation().getY() < 393)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() + 41));
		    		else if((int)player.getLocation().getX() > 436 && (int)player.getLocation().getY() < 393)
		    			player.setLocation(new Point((int)player.getLocation().getX(), (int)player.getLocation().getY() + 41));
		    		else if((int)player.getLocation().getX() > 436 && (int)player.getLocation().getY() > 393 && (int)player.getLocation().getY() < 434)
		    			player.setLocation(new Point(436, 485));
		    		System.out.println((int)player.getLocation().getX() + "," + (int)player.getLocation().getY());
		    		move--;
				}
			}
			//SEND THE PROPERTY CARD INFOS TO THE GUI CLINET HERE:
			if(landedOn.getAvailable()&&landedOn.getType().equals("Monopoly")){
				player.send("UPDATE " + landedOn.getName() + " Cost - " + landedOn.getCost() + " Rent - " + landedOn.getRent());
				System.out.println("@RollCommand Sending the card info to the playerClient");
			}
			else if(landedOn.getAvailable()){
				player.send("UPDATE " + landedOn.getData());
			}
			IPlayer[] listOfPlayers = server.getClients();
			//TO DO: COLLECT THE RENT FROM THE PLAYER LANDED IF THE CARD IS ALREADY BOUGHT.
			if(!landedOn.getAvailable()){
				player.transact((landedOn.getRent())*-1);
				player.send("UPDATE " + player.getHandle() + " pays rent to " + landedOn.getOwner());
				for(int i = 0; i < server.getClients().length; i++){
					if(landedOn.getOwner().equals(listOfPlayers[i].getHandle())){
						listOfPlayers[i].transact(landedOn.getRent());
					}
				}
			}
			/*
			Here is the plan to update the moves:
			When someone rolls, it will come to this command and process
			the data and create two points. Then it will send the id
			of the player that moved and the points to all the players.
			Each player, the client, has its own haspmap list that
			has key as the player id and value as the point.
			As soon as the move command in the client recievves the message
			from this, it will update the map. Then it will the update
			board command in the client. That will update the board using the
			points from the hashmap.
			The hasp map is also updated when someone joins the game (addCommand).
			The addcaommand also calls the update board, which will make new player
			appear on the board.
			*/
		//	String retbroad = "UPDATEBOARD";
		//	for(int i = 0; i < listOfPlayers.length; i++){
		//		retbroad += " "+listOfPlayers[i].getId() + " " + (int)(listOfPlayers[i].getLocation().getX()) + " " + (int)(listOfPlayers[i].getLocation().getY());
		//	}
		//	server.broadcast(retbroad);
		//	server.broadcast("BOARD ");
			String ret = "MOVE " + player.getId() + " " + (int)player.getLocation().getX() + " " + (int)player.getLocation().getY();
			player.send(ret);
			server.broadcast(ret);
			System.out.println("@RollCommand The new position being sent to player #" + player.getId() + " is " + newx + ", " + newy);
			System.out.println("@RollCommand The ret is " + ret);
		}
	}
}
