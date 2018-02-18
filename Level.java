import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.sql.*;
import java.io.*;
public class Level extends JFrame implements MouseListener,ActionListener
{
	Container c;
	JButton yes,no;
	JPanel gamePanel,overPanel,completePanel;
	ImageIcon backgroundimage,pauseIcon,resumeIcon;
	JLabel backgroundlabel,scorebackground,pauseresume,over,stoplabel,currentLevel,completelavel,currentScore,highestScore;
	JButton requestbutton,exit,playagain;
	int WIDITH=1800,HEIGHT=900;
	Random r=new Random();
	long time,diff=5000000000L,birdTime=60000000000L,elapsedTime,startTime;
	Score scorelabel;
	int player_level,formerhighestscore;
	BirdFly1 b1_1,b1_2;
	BirdFly2 b2;
	BirdFly3 b3;
	Headshot hshot;
	Thread t1,t2,t3,t4;
	int runningThread=0;
	public Level(int l,Score sc)
	{
		System.out.println("level called for "+l);
		scorelabel=sc;
		player_level=l;
		c=getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setBounds(0,0,WIDITH,HEIGHT);
		
		//initOverPanel();
		//initCompletePanel();
		initGamePanel();
		initThread();
		
		//this.add()
		this.add(gamePanel);
		setVisible(true);
		
		
		if(player_level==1)
		{
			startTime=System.nanoTime();
			elapsedTime=0;
			t1.start();
			runningThread++;
		}
		else if(player_level==2)
		{
			
			startTime=System.nanoTime();
			elapsedTime=0;
			t1.start();
			runningThread++;
			elapsedTime=0;
			startTime=System.nanoTime();
			t2.start();
			runningThread++;
		}
		else if(player_level==3)
		{
			startTime=System.nanoTime();
			elapsedTime=0;
			t1.start();
			runningThread++;
			
			elapsedTime=0;
			startTime=System.nanoTime();
			t3.start();
			runningThread++;
		}
		else if(player_level==4)
		{
			elapsedTime=0;
			startTime=System.nanoTime();
			t1.start();
			runningThread++;
			
			elapsedTime=0;
			startTime=System.nanoTime();
			t3.start();
			runningThread++;
			
			elapsedTime=0;
			startTime=System.nanoTime();
			t4.start();
			runningThread++;
		}
	}
	
	public void initGamePanel()
	{
		/*.................game gamePanel..............*/
		gamePanel=new JPanel();
		gamePanel.setLayout(null);
		
		gamePanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		
		hshot=new Headshot("HEAD SHOT!!!");
		hshot.setFont(new Font("ARIAL",Font.BOLD,30));
		hshot.setForeground(Color.GREEN);
		hshot.setBounds(WIDITH-250,100,200,50);
		hshot.setVisible(false);
		gamePanel.add(hshot);
		
		scorelabel.setBounds(WIDITH-250,50,200,50);
		gamePanel.add(scorelabel);
		
		
		//smallbird=new JLabel(new ImageIcon("small.png"));
		//smallbird.setBounds(1500,800,50,42);
		//gamePanel.add(smallbird);
		
		pauseIcon=new ImageIcon("pause.png");
		resumeIcon=new ImageIcon("resume.png");
		pauseresume=new JLabel(pauseIcon);
		pauseresume.setBounds(1550,200,100,100);
		pauseresume.addMouseListener(this);
		gamePanel.add(pauseresume);
		
		
		
		stoplabel=new JLabel(new ImageIcon("stop.png"));
		stoplabel.setBounds(1550,350,100,100);
		stoplabel.addMouseListener(this);
		stoplabel.setToolTipText("STOP");
		gamePanel.add(stoplabel);
		
		currentLevel=new JLabel("Level "+player_level);
		currentLevel.setFont(new Font("ARIAL",Font.BOLD,30));
		currentLevel.setBounds(1550,500,150,100);
		currentLevel.setForeground(Color.WHITE);
		gamePanel.add(currentLevel);
		
		
		
		scorebackground=new JLabel(new ImageIcon("black.png"));
		scorebackground.setBounds(1500,0,300,900);
		scorebackground.addMouseListener(this);
		gamePanel.add(scorebackground);
		
		
		b1_1=new BirdFly1(player_level,scorelabel,this);
		gamePanel.add(b1_1);
		
		b1_2=new BirdFly1(player_level,scorelabel,this);
		gamePanel.add(b1_2);
		
		b2=new BirdFly2(player_level,scorelabel,this);
		gamePanel.add(b2);
		b3=new BirdFly3(player_level,scorelabel,this);
		gamePanel.add(b3);
		
		//backgroundlabel=new JLabel(new ImageIcon("background.png"));
		//backgroundlabel.setBounds(0,0,WIDITH-300,HEIGHT);
		//backgroundlabel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		backgroundlabel=new JLabel(new ImageIcon("background.png"));
		backgroundlabel.setBounds(0,0,WIDITH,HEIGHT);
		backgroundlabel.addMouseListener(this);
		gamePanel.add(backgroundlabel);
		
		
	}
	public void initCompletePanel()
	{
		System.out.println("complete panel added");
		completePanel=new JPanel();
		completePanel.setLayout(null);
		
		completelavel=new JLabel("LEVEL ACOMPLISHED");
		completelavel.setBounds(90,200,1700,200);
		completelavel.setFont(new Font("Consolas",Font.BOLD,170));
		completePanel.add(completelavel);
		
		currentScore=new JLabel("Current Score : "+scorelabel.scorevalue);
		currentScore.setFont(new Font("ARIAL",Font.BOLD,30));
		currentScore.setBounds(400,450,350,50);
		completePanel.add(currentScore);
		
		
		highestScore=new JLabel("Highest Score : "+formerhighestscore);
		highestScore.setFont(new Font("ARIAL",Font.BOLD,30));
		highestScore.setBounds(400,520,350,50);
		completePanel.add(highestScore);
		
		playagain=new JButton("playagain");
		playagain.setBounds(400,700,200,40);
		playagain.setFont(new Font("Consolas",Font.BOLD,30));
		playagain.addActionListener(this);
		completePanel.add(playagain);
			
			
		exit=new JButton("EXIT");
		exit.setBounds(900,700,200,40);
		exit.setFont(new Font("Consolas",Font.BOLD,30));
		exit.addActionListener(this);
		completePanel.add(exit);
		
		backgroundlabel=new JLabel(new ImageIcon("background.png"));
		backgroundlabel.setBounds(0,0,WIDITH,HEIGHT);
		completePanel.add(backgroundlabel);
	}
	public void completeGame()
	{
		this.gamePanel.setVisible(false);
		System.out.println("game invisible");
		highestScore.setText("Highest Score : "+formerhighestscore);
		this.add(completePanel);
		System.out.println("complete added\nhigh score should be : "+formerhighestscore);
			
	}
	
	public void updateScore()
	{
		try{
			System.out.println("in updateScore");
		
		File file = new File("ScoreTable.txt");
		FileReader reader = new FileReader(file);
		BufferedReader bfl = new BufferedReader(reader);
		
		String scor=bfl.readLine();
		String l=bfl.readLine();
		
		System.out.println("\nin file: former score : "+scor+" lavel : "+l);
		
		formerhighestscore=Integer.parseInt(scor);
		
		if(formerhighestscore<scorelabel.scorevalue) formerhighestscore=scorelabel.scorevalue;
		
		int max_level=Integer.parseInt(l);
		if(max_level<player_level) max_level=player_level;
		reader.close();
		
		
		String s=Integer.toString(formerhighestscore)+"\r"+"\n"+Integer.toString(max_level)+"\r"+"\n";
		
		
		file = new File("ScoreTable.txt");
		FileWriter writer = new FileWriter(file); 
		
		
		writer.write(s); 
		writer.flush();
		writer.close();
		}
		catch(Exception e) {}
		
		
	}
	
	public void initOverPanel()
	{
		/*.................over gamePanel..............*/
		
		overPanel=new JPanel();
		overPanel.setLayout(null);
		
		//o.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		over=new JLabel("GAME OVER");
		over.setBounds(400,200,1000,200);
		over.setFont(new Font("Consolas",Font.BOLD,200));
		overPanel.add(over);
		
		
		currentScore=new JLabel("Current Score : "+scorelabel.scorevalue);
		currentScore.setFont(new Font("ARIAL",Font.BOLD,30));
		currentScore.setBounds(400,450,350,50);
		overPanel.add(currentScore);
		
		
		highestScore=new JLabel("Highest Score : "+formerhighestscore);
		highestScore.setFont(new Font("ARIAL",Font.BOLD,30));
		highestScore.setBounds(400,520,350,50);
		overPanel.add(highestScore);
		
		
		playagain=new JButton("playagain");
		playagain.setBounds(400,600,200,40);
		playagain.setFont(new Font("Consolas",Font.BOLD,30));
		playagain.addActionListener(this);
		overPanel.add(playagain);
		
		
		exit=new JButton("EXIT");
		exit.setBounds(900,600,200,40);
		exit.setFont(new Font("Consolas",Font.BOLD,30));
		exit.addActionListener(this);
		overPanel.add(exit);
		
		backgroundlabel=new JLabel(new ImageIcon("background.png"));
		backgroundlabel.setBounds(0,0,WIDITH,HEIGHT);
		overPanel.add(backgroundlabel);
		
	}
	public void overGame()
	{
		//suspendThread();
		this.gamePanel.setVisible(false);
		System.out.println("");
		
		this.add(overPanel);
			
	}
	public void initThread()
	{
		t1=new Thread(new Runnable(){
			//boolean running=false;
			
			public void run(){
			try{
				//running=true;
				//runningThread=1;
				b1_1.flyForward();
			}
			catch(Exception e)
			{
				System.out.println("t1 thread broke----------------");
				e.printStackTrace();
				System.out.println(e.getMessage());
				//t1.start();
			}
		}});
		
		t2=new Thread(new Runnable(){
			//boolean running=false;
			
			public void run(){
				//running=true;
				//runningThread=2;
			try{
				b1_2.flyBackward();
			}
			catch(Exception e)
			{
				System.out.println("t2 thread broke----------------");
				e.printStackTrace();
				System.out.println(e.getMessage());
				//t2.start();
			}
		}});
		t3=new Thread(new Runnable(){
			//boolean running=false;
			
			public void run(){
			//running=true;
			//runningThread=2;
			try{
				b2.flyForward();
			}
			catch(Exception e)
			{
				System.out.println("t3 thread broke-----------------");
				e.printStackTrace();
				System.out.println(e.getMessage());
				//t3.start();
			}
		}});
		
		t4=new Thread(new Runnable(){
			//boolean running=false;
			
			public void run(){
				//running=true;
				//runningThread=3;
			try{
				b3.flyForward();
			}
			catch(Exception e)
			{
				System.out.println("t4 thread broke------------------------------");
				e.printStackTrace();
				System.out.println(e.getMessage());
				//t4.start();
			}
		}});
		
	}
	public void suspendThread()
	{
		elapsedTime=System.nanoTime()-startTime;
		System.out.println("system paused");
		if(player_level==1)
		{
			synchronized(this){
			try{
			t1.suspend();
			System.out.println("t1 paused");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==2)
		{
			synchronized(this){
			try{
			t1.suspend();
			System.out.println("t1 paused");
			t2.suspend();
			System.out.println("t2 paused");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==3)
		{
			synchronized(this){
			try{
			t1.suspend();
			System.out.println("t1 paused");
			t3.suspend();
			System.out.println("t3 paused");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==4)
		{
			synchronized(this){
			try{
			t1.suspend();
			System.out.println("t1 paused");
			t4.suspend();
			System.out.println("t4 paused");
			t3.suspend();
			System.out.println("t3 paused");
			}
			catch(Exception e) {}
			}
		}
		
	}
	public void resumeThread()
	{
		b1_1.birdshot=false;
		b1_2.birdshot=false;
		b2.birdshot=false;
		b3.birdshot=false;
		startTime=System.nanoTime();
		System.out.println("system resumed");
		if(player_level==1)
		{
			synchronized(this){
			try{
			t1.resume();
			System.out.println("t1 resumed");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==2)
		{
			synchronized(this){
			try{
			t1.resume();
			System.out.println("t1 resumed");
			t2.resume();
			System.out.println("t2 resumed");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==3)
		{
			synchronized(this){
			try{
			t1.resume();
			System.out.println("t1 resumed");
			t3.resume();
			System.out.println("t3 resumed");
			}
			catch(Exception e) {}
			}
		}
		else if(player_level==4)
		{
			synchronized(this){
			try{
			t1.resume();
			System.out.println("t1 resumed");
			t4.resume();
			System.out.println("t4 resumed");
			t3.resume();
			System.out.println("t3 resumed");
			}
			catch(Exception e) {}
			}
		}
		
	}
	
	public synchronized void decrease_runningThread()
	{
		runningThread--;
	}
		
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource().equals(exit))
		{
			
			System.exit(0);
		}
		else if(e.getSource().equals(playagain))
		{
			this.setVisible(false);
			new player_interface();
		}
	}
	
	
	public void mouseEntered(MouseEvent me) //mouse entered
	{
		//Sound.click.play();
		if(me.getSource().equals(scorebackground)) scorebackground.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		else if(me.getSource().equals(pauseresume)) pauseresume.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(me.getSource().equals(stoplabel)) stoplabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(me.getSource().equals(exit)) exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(me.getSource().equals(playagain)) playagain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(me.getSource().equals(requestbutton)) requestbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	public void mouseExited(MouseEvent me){} //
	public void mouseClicked(MouseEvent me) //mouse clicked
	{
		//System.out.println("in level mouse clicked");
		//Sound.click.play();
		if(me.getSource().equals(pauseresume))
		{
			if(pauseresume.getIcon().equals(resumeIcon))
			{
				pauseresume.setIcon(pauseIcon);
				resumeThread();
			}
			else 
			{
				pauseresume.setIcon(resumeIcon);
				suspendThread();
			}
			
		}
		else if(me.getSource().equals(stoplabel))
		{
			suspendThread();
			int c=JOptionPane.showConfirmDialog(null,"Do you want to stop the game?","",JOptionPane.YES_NO_OPTION);
			if(c==JOptionPane.YES_OPTION)
			{
				updateScore();
				initOverPanel();
				overGame();
			}
			else resumeThread();
		}
		else if(me.getSource().equals(backgroundlabel))
		{
			System.out.println("in level mouse clicked");
			System.out.println("click");
			Sound.click.play();
		}
		
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	
	
}