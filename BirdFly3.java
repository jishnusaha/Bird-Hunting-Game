import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class BirdFly3 extends FlyMethod
{
	public BirdFly3(int l,Score sc,Level p)
	{
		parent=p;
		
		scorelabel=sc;
		player_level=l;
		
		bf1=new ImageIcon("pictures/bird3forward1.png");
		bf2=new ImageIcon("pictures/bird3forward2.png");
		bb1=new ImageIcon("pictures/bird3backward1.png");
		bb2=new ImageIcon("pictures/bird3backward2.png");
		fall=new ImageIcon("pictures/bird3fall.png");
		
		
		
		time=System.nanoTime();
		setIcon(bf1);
		
		this.addMouseListener(this);
	}
	
}