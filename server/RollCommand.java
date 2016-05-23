
class RollCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "ROLL";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND)&& player.getTurn())
		{
			//roll and move process code goes here
			int x = (int)(player.getLocation().getX());
			int y = (int)(player.getLocation().getY());
			int rolled = Integer.parseInt(message.substring(5,6)) + Integer.parseInt(message.substring(7,8));
			int newPosition  = player.getCardOn() + rolled;
			if(rolled>39){ newPosition -=40;}
			player.setCardOn(newPosition);
			IPropertyCard landedOn = server.getCardAt(newPosition);
			int newx = 0;
			int newy = 0;

			//process for horizontal and verticals sides and assigning new positions
			if(!(landedOn.getType().equals("Corner"))) {
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
			
			//SEND THE PROPERTY CARD INFOS TO THE GUI CLINET HERE:
			if(landedOn.getAvailable()){
				player.send("UPDATE " + landedOn.getName() + " Cost - " + landedOn.getCost() + " Rent - " + landedOn.getRent());
				System.out.println("@RollCommand Sending the card info to the playerClient");
			}
			IPlayer[] listOfPlayers = server.getClients();
			//TO DO: COLLECT THE RENT FROM THE PLAYRE LANDED IF THE CARD IS ALREADY BOUGHT.
			if(!landedOn.getAvailable()){
				player.transact((landedOn.getRent())*-1);
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
			String ret = "MOVE " + player.getId() + " " + newx + " " + newy;
			player.send(ret);
			server.broadcast(ret);
			System.out.println("@RollCommand The new position being sent to player #" + player.getId() + " is " + newx + ", " + newy);
			System.out.println("@RollCommand The ret is " + ret);
		}
	}
}