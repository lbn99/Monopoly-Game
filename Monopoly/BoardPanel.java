import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BoardPanel extends JPanel
{
	private IClient client;
	private Graphics g;
	private HashMap<String, Point> players;
	public BoardPanel(HashMap<String, Point> p){
		players = p;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("../monopolyboard.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, this);
		if(players.size()==1){
			g.setColor(Color.ORANGE);
			g.fillOval((int)players.get("0").getX(),(int)players.get("0").getY(), 11, 11);
		}
//		g.setColor(Color.RED);
//		g.fillOval((int)players.get("1").getX(),(int)players.get("1").getY(), 11, 11);
		
//		g.setColor(Color.CYAN);
//		g.fillOval((int)players.get("2").getX(), (int)players.get("2").getY(), 11, 11);
		
//		g.setColor(Color.MAGENTA);
//		g.fillOval((int)players.get("3").getX(), (int)players.get("3").getY(), 11, 11);
	}
	public void getPlayers(HashMap<String, Point> p){
		players = p;
	}
	public void paintNewPlayer(int x, int y, int n)
	{
		if(n==0){g.setColor(Color.RED);}
		if(n==1){g.setColor(Color.CYAN);}
		if(n==2){g.setColor(Color.YELLOW);}
		if(n==3){g.setColor(Color.MAGENTA);}
		g.fillOval(x,y,11,11);
		this.repaint();
		this.validate();
	}
}