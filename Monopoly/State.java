//NOTE: This is only a makeshift Server-like class for use with the GUI
import java.awt.Point;

public class State{
	private Point player1;
	
	public State(Point p1){
		player1 = p1;
	}
	public void setPoint(Point p){ player1 = p; }
	public Point getPoint(){ return player1; }
}