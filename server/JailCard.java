import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.Point;

public class JailCard extends PropertyCard
{
	private List<IPlayer> playersInJail;
	public JailCard(String n, int p, Color c, boolean a, int r, Point x, int s_x, int s_y, int[] house_p, String t, int i){
		super( n,  p,  c,  a,  r,  x,  s_x,  s_y, house_p,  t,  i);
		playersInJail = new ArrayList<IPlayer>();
	}

	public void putInJail(IPlayer p){
		playersInJail.add(p);
	}

	public void removeFromJail(IPlayer p){
		for(int i = 0; i < playersInJail.size(); i++){
			if(playersInJail.get(i).getId()==p.getId())
				playersInJail.remove(i);
		}
	}
}