import javax.swing.JOptionPane;

public class GameLauncher{
	public static void main(String[] args){
		int option = JOptionPane.showConfirmDialog(null, "Host the game?", "Monopoly!", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION){
			GameServer game = new GameServer(99);
			Monopoly frame = new Monopoly("Monopoly!", "", 0);
		}
		else{
			String IP = JOptionPane.showInputDialog(null, "Enter the IP Address of the host");
			int port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the port of the host"));
			String name = (String)JOptionPane.showInputDialog(null,"Enter the name");
			Monopoly frame = new Monopoly("Monopoly!", IP, port);
		}
	}
}