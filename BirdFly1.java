import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class BirdFly1 extends FlyMethod
{
	public BirdFly1(int l,Score sc,Level p)
	{
		parent=p;
		scorelabel=sc;
		player_level=l;
		
		bf1=new ImageIcon("pictures/bird1forward1.png");
		bf2=new ImageIcon("pictures/bird1forward2.png");
		bb1=new ImageIcon("pictures/bird1backward1.png");
		bb2=new ImageIcon("pictures/bird1backward2.png");
		fall=new ImageIcon("pictures/bird1fall.png");
		
		
		
		time=System.nanoTime();
		setIcon(bf1);
		
		this.addMouseListener(this);
	}
	
}