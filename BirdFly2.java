import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class BirdFly2 extends FlyMethod
{
	public BirdFly2(int l,Score sc,Level p)
	{
		parent=p;
		
		scorelabel=sc;
		player_level=l;
		
		bf1=new ImageIcon("pictures/bird2forward1.png");
		bf2=new ImageIcon("pictures/bird2forward2.png");
		bb1=new ImageIcon("pictures/bird2backward1.png");
		bb2=new ImageIcon("pictures/bird2backward2.png");
		fall=new ImageIcon("pictures/bird2fall.png");
		
		
		
		time=System.nanoTime();
		setIcon(bf1);
		
		this.addMouseListener(this);
	}
	
}