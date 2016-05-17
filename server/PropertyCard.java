import java.awt.*;

public class PropertyCard implements IPropertyCard
{
	private String name;
	private int price;
	private Color color;
	private boolean available;
	private int rent;
	private Point point;
	private int x_size;
	private int y_size;
	private int[] house_rents;
	private String type;
	private String owner;
	private int id;
	
	public PropertyCard(String n, int p, Color c, boolean a, int r, Point x, int s_x, int s_y, int[] house_p, String t, int i)
	{
		this.name = n;
		this.price = p;
		this.color = c;
		this.available = a;
		this.rent = r;
		this.point = x;
		this.x_size = s_x;
		this.y_size = s_y;
		this.house_rents = house_p;
		this.type = t;
		this.owner = "";
		this.id = i;
	}
	
	public String getName(){
		return this.name;
	}

	//it returns the cost of the card
	public int getCost(){
		return this.price;		
	}

	//it sets the boolean available to true or false accordingly
	public void setAvailable(boolean b){
		this.available = b;
	}

	//it returns the status of available
	public boolean getAvailable(){
		return this.available;
	}

	//it returns the rent
	public int getRent(){
		return this.rent;
	}

	//it returns the color of the card
	public Color getColor(){
		return this.color;
	}

	//it returns the cordinate 
	public boolean checkInside(Point p)
	{
		int min_x = (int)(this.point.getX());
		int max_x = min_x + x_size;
		int min_y = (int)(this.point.getY());
		int max_y = min_y + y_size;
		int p_x = (int)(p.getX());
		int p_y = (int)(p.getY());
		System.out.println("@PropertyCard Range is ( " + min_x + " - " + max_x + ", " + min_y + " - " + max_y + ")");
		if(p_x<=max_x && p_x>=min_x)
			if(p_y<=max_y && p_y >= min_y)
				return true;
		return false;
	}
		
	public Point getPoint()
	{
		return this.point;
	}

	//it returns the type of the card
	public String getType(){
		return this.type;
	}

	//it sets the owner
	public void setOwner(String s){
		this.owner = s;	
	}

	//it returns the owner
	public String getOwner(){
		return this.owner;
	}
	
	//it returns the id of the card... 0 being the GO card
	public int getId(){
		return this.id;
	}
	
	//this returns the x position of the card's top left cornor
	public int getX(){
		return (int)(this.point.getX());
	}
	
	//this returns the y position of the card's top left cornor
	public int getY(){
		return (int)(this.point.getY());
	}
	
}