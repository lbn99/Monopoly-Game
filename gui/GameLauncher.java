import javax.swing.JOptionPane;
import java.net.InetAddress;

public class GameLauncher{
	public static void main(String[] args){
		String name = JOptionPane.showInputDialog(null, "Enter in your name");
		int option = JOptionPane.showConfirmDialog(null, "Host the game?", "Monopoly!", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION){
			GameServer server = new GameServer(99);
			Thread t = new Thread(server);
			t.start();
			try{
			System.out.println("GameServer started\n" + 
				"Connected to: " + InetAddress.getLocalHost() + " and port 99");
			}
			catch(Exception e) { e.printStackTrace(); }
			Monopoly frame = new Monopoly("Monopoly!", "localhost", 99, name);
		}
		else{
			String IP = JOptionPane.showInputDialog(null, "Enter the IP Address of the host");
			int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the port of the host"));
			Monopoly frame = new Monopoly("Monopoly!", IP, port, name);
		}
	}
}