
class RollCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "ROLL";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND)&&player.getTurn())
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
			
			//TO DO: COLLECT THE RENT FROM THE PLAYRE LANDED IF THE CARD IS ALREADY BOUGHT.

			//WE NEED A FORMAT THROUGH WHICH WE WILL RETURN THIS THING.
			String ret = "MOVE " + player.getId() + " " + newx + " " + newy;
			System.out.println("@RollCommand The new position being sent to player #" + player.getId() + " is " + newx + ", " + newy);
			System.out.println("@RollCommand The ret is " + ret);
		}
	}
}