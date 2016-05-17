import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
	private State state;
	
	public BoardPanel(State s)
	{
		state = s;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		ImageIcon icon = new ImageIcon("../monopolyboard.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, this);
		
		g.setColor(Color.ORANGE);
		g.fillOval((int)state.getPoint().getX(), (int)state.getPoint().getY(), 11, 11);
		
		g.setColor(Color.RED);
		g.fillOval(403, 485, 11, 11);
		
		g.setColor(Color.CYAN);
		g.fillOval(411, 485, 11, 11);
		
		g.setColor(Color.MAGENTA);
		g.fillOval(419, 485, 11, 11);
	}
}