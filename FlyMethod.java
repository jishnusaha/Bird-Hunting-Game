import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class FlyMethod extends JLabel implements MouseListener
{
	ImageIcon recentbird1,recentbird2,bf1,bf2,bb1,bb2,fall;
	int gameWidith=1500,gameHeight=900;
	int currentPositionX,currentPositionY;
	Random r=new Random();
	long time,diff=4000000000L;
	boolean inframe=false,forwardbirdtaken=false,backwardbirdtaken=false,birdshot=false,headshot=false;
	int player_level=1;
	Score scorelabel;
	int sleepvalue=0,jump=2;
	Level parent;
	public FlyMethod()
	{
		sleepvalue=100;
		jump=20;
	}
	
	public synchronized void reverse_inframe_status()
	{
		if(inframe) 
		{
			inframe=false;
		}
		else 
		{
			inframe=true;
			
		}
	}
	
	public synchronized void reverse_birdshot_status()
	{
		if(birdshot)
		{
			birdshot=false;
		}
		else
		{
			birdshot=true;
		}
	}
	public void takeforwardbird()
	{
		forwardbirdtaken=true;
		backwardbirdtaken=false;
		
		recentbird1=bf1;
		recentbird2=bf2;
	}
	public void takebackwardbird()
	{
		
		forwardbirdtaken=false;
		backwardbirdtaken=true;
		recentbird1=bb1;
		recentbird2=bb2;
	}
	public void flyForward()
	{
		time=System.nanoTime();
		takeforwardbird();
		if(inframe)
		{
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForwardUp();
			else flyForwardDown();
		}
		reverse_inframe_status();
		currentPositionX=r.nextInt(1400);
		if(currentPositionX==0)
		{
			//position x in left border (x=0) recentbird should appera from left border
			//position y may be in full up to down (0,upper)/(0,0) to (0,lower)/(0,900)
			currentPositionY=r.nextInt(900);
			
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForwardDown();
			else flyForwardUp();
			
		}
		else 
		{
			//position of x is not in  border, recentbird should arrive from lower border of upper border
			if(r.nextBoolean()) 
			{
				//appera in lower border
				//can fly up not down
				currentPositionY=900;
				flyForwardUp();
			}
			else
			{
				//appera in upper border
				//can fly down not up
				currentPositionY=0;
				flyForwardDown();
			}
		}	
	}
	public void flyBackward()
	{
		time=System.nanoTime();
		takebackwardbird();
		if(inframe)
		{
			if(!r.nextBoolean() && System.nanoTime()-time>diff) flyBackwardUp();
			else flyBackwardDown();
		}
		reverse_inframe_status();
		currentPositionX=401+r.nextInt(1400);
		if(currentPositionX==1800)
		{
			//position x in right border (x=1800) recentbird should appera from right border
			//position y may be in full up to down (1800,upper)/(1800,0) to (1800,lower)/(1800,900)
			currentPositionY=r.nextInt(900);
			
			if(!r.nextBoolean() && System.nanoTime()-time>diff) flyBackwardDown();
			else flyBackwardUp();
			
		}
		else 
		{
			//position of x is not in border, recentbird should arrive from lower border of upper border
			if(!r.nextBoolean()) 
			{
				//appera in lower border
				//can fly up not down
				currentPositionY=900;
				flyBackwardUp();
			}
			else
			{
				//appera in upper border
				//can fly down not up
				currentPositionY=0;
				flyBackwardDown();
			}
		}
			
	}
	public void flyForwardUp()
	{
		for(int i=currentPositionX;i<gameWidith+250;)
		{
			for(int j=currentPositionY;j>-100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { } 
				
				
				if(birdshot) dropBird();
				
				i+=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird2);
				setBounds(i,j,200,168);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i+=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				
				
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardDown();
			
		}
	}
	public void flyForwardDown()
	{
		for(int i=currentPositionX;i<gameWidith+250;)
		{
			
			for(int j=currentPositionY;j<gameHeight+100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i+=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird2);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i+=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardUp();
		}
		
	}
	
	public void flyBackwardUp()
	{
		for(int i=currentPositionX;i>-100;)
		{
			for(int j=currentPositionY;j>-100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird1);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird2);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i-=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardDown();
		}
	}
	public void flyBackwardDown()
	{
		for(int i=currentPositionX;i>-100;)
		{
			for(int j=currentPositionY;j<gameHeight+100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i-=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird2);
				setBounds(i,j,200,168);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i-=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardUp();
		}
	}
	void isbackwardbirdShot(int x,int y)
	{
		
		if(x>=3 && x <=67  && y>=36 && y<=105)
		{
			headshot=true;
			reverse_birdshot_status();		
		}
		else if(x>=60 && x <=110  && y>=45 && y<=130)
		{
			reverse_birdshot_status();
		}
		else if(x>=100 && x <=130  && y>=60 && y<=132)
		{
			reverse_birdshot_status();
		}
	}
	void isforwardbirdShot(int x,int y)
	{
		if(x>=148 && x <=197  && y>=40 && y<=100)
		{
			reverse_birdshot_status();
			headshot=true;
		}
		else if(x>=106 && x <=155  && y>=40 && y<=124)
		{
			reverse_birdshot_status();
		}
		else if(x>=75 && x <=120 && y>=50 && y<=133)
		{
			reverse_birdshot_status();
		}
	}
	public boolean is_inframe()
	{
		if(currentPositionX<-270 || currentPositionX>gameWidith+10 || currentPositionY<-240 || currentPositionY>gameHeight+10)
		{
			
			reverse_inframe_status();
			if((System.nanoTime()-parent.startTime+parent.elapsedTime)>=parent.birdTime)
			{
				if(parent.player_level==1)
				{
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					parent.runningThread++;
					
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					parent.t2.start();
				}
				else if(parent.player_level==2)
				{
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					
					
					parent.startTime=System.nanoTime();
					parent.t3.start();
					Thread.currentThread().stop();
					
				}
				else if(parent.player_level==3)
				{
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					parent.runningThread++;
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					parent.t4.start();
				}
				else if(parent.player_level==4)
				{
					
				
					parent.decrease_runningThread();
				
					if(parent.runningThread==0)
					{
						
						try{
							Thread.sleep(1000);
						}
						catch(Exception e){}
					
						parent.updateScore();
						parent.initCompletePanel();
						parent.gamePanel.setVisible(false);
						parent.completeGame();
						Thread.currentThread().stop();
					
					}
					Thread.currentThread().stop();
				}
				
			}
			return false;
		}
		else return true;
	}
	
	public void dropBird()
	{
		if(headshot)
		{
			parent.hshot.setVisible(true);
			scorelabel.updateScore(player_level*200);
		}
		else scorelabel.updateScore(player_level*100);
		scorelabel.updateText();
		recentbird1=fall;
		setIcon(fall);
		for(;currentPositionY<gameHeight+100;currentPositionY+=2)
		{
			setBounds(currentPositionX,currentPositionY,200,168);
			try{ Thread.sleep(2); }
			catch(Exception e) { }
				
		}
		is_inframe();
		reverse_birdshot_status();
		headshot=false;
		parent.hshot.setVisible(false);
		if(r.nextBoolean()) flyForward();
		else flyBackward();
	}
	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me)
	{
		Sound.click.play();
		int x=me.getX();
		int y=me.getY();
		//bird1
		if(forwardbirdtaken) //when forward bird visible
		{
			isforwardbirdShot(x,y);
		}
		else
		{
			isbackwardbirdShot(x,y);
		}
		
		
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}	
}
