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
		if(isCommand(message, COMMAND))
		{
			System.out.println("In the Update command");
			textArea.setText("");
			textArea.append(message.substring(7) + "\n");
		}
	}
}