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
	//long parent.birdTime=30000000000L;
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
	public synchronized void reverse_bird_taken()
	{
		if(forwardbirdtaken) forwardbirdtaken=false;
		else forwardbirdtaken=true;
		if(backwardbirdtaken) backwardbirdtaken=false;
		else backwardbirdtaken=true;
	}
	public synchronized void reverse_inframe_status()
	{
		if(inframe) 
		{
			//System.out.println(Thread.currentThread().getId()+"out of frame");
			inframe=false;
		}
		else 
		{
			//System.out.println(Thread.currentThread().getId()+" is inframe");
			inframe=true;
			
		}
	}
	
	public synchronized void reverse_birdshot_status()
	{
		//System.out.println(Thread.currentThread().getId()+" shot");
		if(birdshot)
		{
			//System.out.println(Thread.currentThread().getId()+" shot false");
			birdshot=false;
		}
		else
		{
			//System.out.println(Thread.currentThread().getId()+" shot true");
			birdshot=true;
		}
	}
	public void takeforwardbird()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is taking forward bird");
		reverse_bird_taken();
		recentbird1=bf1;
		recentbird2=bf2;
	}
	public void takebackwardbird()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is taking backward bird");
		reverse_bird_taken();
		recentbird1=bb1;
		recentbird2=bb2;
	}
	public void flyForward()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward");
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
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward");
		time=System.nanoTime();
		//System.out.println("comes in flyBackward : "+System.nanoTime());
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
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward Up");
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
				//System.out.println(i+" "+j);
				// //System.out.println(i+" "+j);
				try{ Thread.sleep(sleepvalue); }
			catch(Exception e) { } 
				
				
				if(birdshot) dropBird();
				
				i+=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardDown();
				
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
				//else flyForwardDown();
				
				
				
				
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardDown();
			
		}
	}
	public void flyForwardDown()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward down");
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
				//System.out.println(i+" "+j);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i+=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardUp();
				
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
				
				i+=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardUp();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardUp();
		}
		
	}
	
	public void flyBackwardUp()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward Up");
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
				//System.out.println(i+" "+j);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardDown();
				
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
				
				i-=jump; j-=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardDown();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardDown();
		}
	}
	public void flyBackwardDown()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward Down");
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
				//System.out.println(i+" "+j);
				try{ Thread.sleep(sleepvalue); }
				catch(Exception e) { }
				
				if(birdshot) dropBird();
				
				i-=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardUp();
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
				
				i-=jump; j+=jump;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardUp();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardUp();
		}
	}
	void isbackwardbirdShot(int x,int y)
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is checking shot");
		
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
		//System.out.println("thread : "+Thread.currentThread().getId()+" is checking");
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
		//System.out.println("id "+Thread.currentThread().getId()+" "+(parent.elapsedTime+System.nanoTime()-parent.startTime)+" "+parent.runningThread);
		//System.out.println("thread : "+Thread.currentThread().getId()+" checking in frame");
		if(currentPositionX<-270 || currentPositionX>gameWidith+10 || currentPositionY<-240 || currentPositionY>gameHeight+10)
		{
			
			//System.out.println("bird 1 out of frame");
			reverse_inframe_status();
			if((System.nanoTime()-parent.startTime+parent.elapsedTime)>=parent.birdTime)
			{
				if(parent.player_level==1)
				{
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					parent.runningThread++;
					
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					System.out.println("opening t2");
					parent.t2.start();
					System.out.println("t2 is on");
					
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
				}
				else if(parent.player_level==2)
				{
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					
					
					parent.startTime=System.nanoTime();
					System.out.println("opening t3");
					parent.t3.start();
					System.out.println("t3 is on");
					Thread.currentThread().stop();
					
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
				}
				else if(parent.player_level==3)
				{
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
					parent.player_level++;
					parent.currentLevel.setText("Level "+parent.player_level);
					parent.runningThread++;
					parent.startTime=System.nanoTime();
					parent.elapsedTime=0;
					System.out.println("opening t4");
					parent.t4.start();
					System.out.println("t4 is on");
					
					//System.out.println("level : "+parent.player_level+" bird : "+parent.runningThread);
				}
				else if(parent.player_level==4)
				{
					
				
					//System.out.println("bird1 time out");
					//Thread.currentThread().running=false;
				
					parent.decrease_runningThread();
				
					if(parent.runningThread==0)
					{
						//System.out.println("bird 1 stop");
						//System.out.println("all thread stopped");
				
						try{
							Thread.sleep(1000);
						}
						catch(Exception e){}
					
						//System.out.println(parent.runningThread);
						
						//parent.gamePanel.setVisible(false);
						//parent.add(parent.completePanel);
						parent.updateScore();
						parent.initCompletePanel();
						parent.gamePanel.setVisible(false);
						//new Completed(player_level,scorelabel);
						parent.completeGame();
						Thread.currentThread().stop();
					
					}
					//System.out.println("bird 1 stop");
					Thread.currentThread().stop();
					////System.out.println("stop exception");
					//System.out.println(parent.runningThread);
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
			//System.out.println("head shot");		
			parent.hshot.setVisible(true);
			scorelabel.updateScore(player_level*200);
		}
		else scorelabel.updateScore(player_level*100);
		scorelabel.updateText();
		//System.out.println("thread : "+Thread.currentThread().getId()+" dropping");
		recentbird1=fall;
		setIcon(fall);
		//System.out.println("dropping");
		for(;currentPositionY<gameHeight+100;currentPositionY+=2)
		{
			//System.out.println(currentPositionX+" "+j);
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
		//System.out.println("thread : "+Thread.currentThread().getId()+" "+"clicked in x : "+me.getX()+" y : "+me.getY());
		Sound.click.play();
		
		//System.out.println("current x : "+currentPositionX+" y : "+currentPositionY);
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