class PlayerListCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "LIST";
	
	//receive from server
	public void process(String message, IPlayer player, IServer server)
	{
		if(isCommand(message, COMMAND))
		{
			System.out.println("SSP> @playerlist command");
			String ret = "LIST ";
			IPlayer[] playerList = server.getClients();
			for(int i = 0; i < playerList.length; i++){
				ret +="["+playerList[i].getHandle()+","+playerList[i].getMoney();
				IPropertyCard[] cards = playerList[i].getCards();
				for(int j = 0; j < playerList[i].getCards().length; j++){
					System.out.println(">>>>> RET in the Playerlist command is " + ret);
					ret+=","+cards[j].getName()+","+cards[j].getCost()+","+cards[j].getRent();
				}
				ret+= "]-";
			}
			System.out.println("@player list command in the  server side is " + ret);
			player.send(ret);
			server.broadcast(ret);	
		}
	}
}