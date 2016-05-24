import java.awt.Color;
import java.awt.Point;

public interface IPropertyCard
{
	//it returns the name of the card
	public String getName();

	//it returns the cost of the card
	public int getCost();

	//it sets the boolean available to true or false accordingly
	public void setAvailable(boolean b);

	//it returns the status of available
	public boolean getAvailable();

	//it returns the rent
	public int getRent();

	//it returns the color of the card
	public Color getColor();

	//it returns the Y size of the card
	public int getY();

	//it returns the X size of the card
	public int getX();

	//it returns the type of the card
	public String getType();

	//it sets the owner
	public void setOwner(String s);

	//it returns the owner
	public String getOwner();
	
	//check the cordinate
	public boolean checkInside(Point p);
}