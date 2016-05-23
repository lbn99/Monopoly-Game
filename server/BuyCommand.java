class BuyCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "BUY";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND))
		{
			if(player.getTurn()){
				//buy process code goes here
				IPropertyCard landedOn = server.getCardAt(player.getCardOn());
				landedOn.setOwner(player.getHandle());
				landedOn.setAvailable(false);
				//Transcation code goes here:

				player.send("UPDATE "+ player.getHandle() + " bought " + landedOn.getName() + " - Cost: " + landedOn.getCost() + " Rent - " + landedOn.getRent());
				//BOUGHT THING NEEDS TO BE BROADCASTED
				
			}
		}
	}
}