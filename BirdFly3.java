import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class BirdFly3 extends FlyMethod
{
	public BirdFly3(int l,Score sc,Level p)
	{
		parent=p;
		//sleepvalue=100;
		//jump=20;
		scorelabel=sc;
		player_level=l;
		//setSize(200,168);
		//System.out.println("in birdfly");
		
		bf1=new ImageIcon("bird3forward1.png");
		bf2=new ImageIcon("bird3forward2.png");
		bb1=new ImageIcon("bird3backward1.png");
		bb2=new ImageIcon("bird3backward2.png");
		fall=new ImageIcon("bird3fall.png");
		
		
		
		time=System.nanoTime();
		setIcon(bf1);
		//System.out.println("in birdfly done");
		
		this.addMouseListener(this);
	}
	
}