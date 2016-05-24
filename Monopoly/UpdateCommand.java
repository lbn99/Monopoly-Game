import java.util.*;
import javax.swing.*;
public class UpdateCommand extends ClientListenerAdapter
{
	public static String COMMAND = "UPDATE";
	private JTextArea textArea;
	public UpdateCommand(JTextArea t){
		textArea = t;
	}
	
	
	public void process(String message, IClient client)
	{
		System.out.println("message in update command is " + message);
		if(isCommand(message, COMMAND))
		{
			System.out.println("In the Update command");
			textArea.setText(message.substring(7) + "\n");
		}
	}
}