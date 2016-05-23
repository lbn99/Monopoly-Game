import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
	private IClient client;
	private Graphics g;
	public void paintComponent(Graphics gg)
	{
		super.paintComponent(gg);
		g = gg;
		ImageIcon icon = new ImageIcon("../monopolyboard.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, this);
		
		g.setColor(Color.ORANGE);
		                   //436                         485
		g.fillOval(0,0, 11, 11);
		
//		g.setColor(Color.RED);
//		g.fillOval(444, 485, 11, 11);
		
//		g.setColor(Color.CYAN);
//		g.fillOval(452, 485, 11, 11);
		
//		g.setColor(Color.MAGENTA);
//		g.fillOval(460, 485, 11, 11);
	}

	public void paintNewPlayer(int x, int y, int n)
	{
		if(n==0){g.setColor(Color.RED);}
		if(n==1){g.setColor(Color.CYAN);}
		if(n==2){g.setColor(Color.YELLOW);}
		if(n==3){g.setColor(Color.MAGENTA);}
		g.fillOval(x,y,11,11);
	}
}