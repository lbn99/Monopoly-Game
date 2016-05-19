import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
	private IClient client;
	
	public BoardPanel(IClient c)
	{
		client = c;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		ImageIcon icon = new ImageIcon("../monopolyboard.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, this);
		
		g.setColor(Color.ORANGE);
		                   //436                         485
		g.fillOval(0,0, 11, 11);
		
		g.setColor(Color.RED);
		g.fillOval(444, 485, 11, 11);
		
		g.setColor(Color.CYAN);
		g.fillOval(452, 485, 11, 11);
		
		g.setColor(Color.MAGENTA);
		g.fillOval(460, 485, 11, 11);
	}
}