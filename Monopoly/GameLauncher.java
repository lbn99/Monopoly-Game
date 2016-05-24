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
			Monopoly frame = new Monopoly("Monopoly!", "localhost", 99, name, true);
			try{
				int start = -1;
				while(start != JOptionPane.OK_OPTION){
					start = JOptionPane.showConfirmDialog(null, "GameServer started\n" + 
						"Connected to: " + InetAddress.getLocalHost() + " and port 99.\n Click OK to start, cancel to update\n" + 
						"Players Connected: " + server.getClients().length, "Monopoly!", JOptionPane.OK_CANCEL_OPTION);
					if(start == JOptionPane.OK_OPTION){
						System.out.println("@gamelauncher send start true");
						server.setStart(true);
					}
				}
			}
			catch(Exception e) { e.printStackTrace(); }
		}
		else{
			String IP = JOptionPane.showInputDialog(null, "Enter the IP Address of the host");
			int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the port of the host"));
			Monopoly frame = new Monopoly("Monopoly!", IP, port, name, false);
		}
	}
}