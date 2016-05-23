import javax.swing.*;
import java.awt.*;

public class ListCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "LIST";
	private JTextArea playerInfos;

	public ListCommand(JTextArea t){
		playerInfos = t;
	}
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			//Here is the format of the recieved string:
			//LIST [Player0,money,name,cost,rent]-[]-[]...
			/* THIS  IS HOW IT SHOULD LIKE IN THE TEXT AREA
			Player0: money
				nameOfCard Cost:__ Rent:__
				.
				.
				.
			Player1: money
				nameOfCard ...
				.
				.
				.

			*/
//			String[] str = message.split(" ", 4);
//			String list = getName(str[3]);
//			j.replaceRange(list, 0, j.getText().length());
			message = message.substring(5);
			System.out.println("@ListCommand The unprocessed string is " + message);
			String[] todo = message.split("-");
			String list = "";
			for(int i = 0; i < todo.length; i++) {
				todo[i] = todo[i].substring(1,todo[i].length()-1);
				String[] finaltodo = todo[i].split(",");
				String name = finaltodo[0];
				String money = finaltodo[1];
				list += name + ": " + money + " \n";
				int x = 2;
				int lineChanger = 0;
				while(x<finaltodo.length){
					if(lineChanger==3){
						lineChanger = 0;
						list += " \n";
					}
					if(lineChanger==1){
						list += "Cost: " + finaltodo[x];
					}
					if(lineChanger==2){
						list += "Rent: " + finaltodo[x];
					}
					list += finaltodo[x];
					x++;
					lineChanger++;
				}
			}
		}
	}
/*	
	public String getName(String str)
	{
		String name = "";
		if(str.length() > 0)
		{
			String[] s = str.split(" ", 2);
			String[] prop = s[1].substring(0, Integer.parseInt(s[0])).split("_");
			int x = prop.length();
			while(x > 0)
			{
				
				x--;
			}
			s[1] = s[1].substring(Integer.parseInt(s[0]));
			name += getName(s[1]);
			return name;
		}
		return "";
	}
	*/
}