import java.util.*;
import java.awt.Point;
public class UpdateBoardCommand extends NetworkListenerAdapter
{
	public static String COMMAND = "UPDATEBORAD";
	private BoardPanel panel;

	HashMap<String, Point> listofPlayersOnline;

	public UpdateBoardCommand(HashMap<String, Point> s,BoardPanel t){
		listofPlayersOnline = s;
			panel = t;
	}
	
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			for(int i = 0; i < listofPlayersOnline.size(); i++){
				Point temp = listofPlayersOnline.get(""+i);
				int x = (int)(temp.getX());
				int y = (int)(temp.getY());
				panel.paintNewPlayer(x,y,i);
			}
		}
	}
}