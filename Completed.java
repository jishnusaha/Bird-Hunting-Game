import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.sql.*;
import java.io.*;
public class Completed extends JFrame implements MouseListener,ActionListener
{
	JPanel completePanel;
	ImageIcon backgroundimage;
	JLabel requestlavel,completelavel,backgroundlabel,currentScore,highestScore;
	JButton exit,playagain,go;
	int WIDITH=1800,HEIGHT=900;
	Random r=new Random();
	long time,diff=5000000000L;
	Score scorelabel;
	int lavel;
	BirdFly1 b1_1,b1_2;
	BirdFly2 b2;
	BirdFly3 b3;
	Headshot hshot;
	
	int player_level,max_level,highestScoreInDB=0;
	Thread t1,t2,t3,t4;
	public Completed(int l,Score sc)
	{
		System.out.println("\ncompleted object created");
		
		player_level=l;
		scorelabel=sc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setBounds(0,0,WIDITH,HEIGHT);
		
		updateScore();
		
		completePanel=new JPanel();
		completePanel.setLayout(null);
		
		
		//completePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		completelavel=new JLabel("LEVEL ACOMPLISHED");
		completelavel.setBounds(90,200,1700,200);
		completelavel.setFont(new Font("Consolas",Font.BOLD,170));
		completePanel.add(completelavel);
		
		currentScore=new JLabel("Current Score : "+scorelabel.scorevalue);
		currentScore.setFont(new Font("ARIAL",Font.BOLD,30));
		currentScore.setBounds(400,450,350,50);
		completePanel.add(currentScore);
		
		highestScore=new JLabel("Highest Score : "+highestScoreInDB);
		highestScore.setFont(new Font("ARIAL",Font.BOLD,30));
		highestScore.setBounds(400,520,350,50);
		completePanel.add(highestScore);
		
		
		if(player_level<max_level)
		{
			requestlavel=new JLabel("play next level?");
			requestlavel.setFont(new Font("ARIAL",Font.BOLD,30));
			requestlavel.setBounds(400,600,350,50);
			completePanel.add(requestlavel);
			
			go=new JButton("GO");
			go.setBounds(800,615,100,30);
			go.setFont(new Font("Consolas",Font.BOLD,30));
			go.addActionListener(this);
			completePanel.add(go);
		
			
		}
		else
		{
			
			playagain=new JButton("playagain");
			playagain.setBounds(400,700,200,40);
			playagain.setFont(new Font("Consolas",Font.BOLD,30));
			playagain.addActionListener(this);
			completePanel.add(playagain);
			
			
		}
		
		
		
		exit=new JButton("EXIT");
		exit.setBounds(900,700,200,40);
		exit.setFont(new Font("Consolas",Font.BOLD,30));
		exit.addActionListener(this);
		completePanel.add(exit);
		
		
		backgroundlabel=new JLabel(new ImageIcon("background.png"));
		backgroundlabel.setBounds(0,0,WIDITH,HEIGHT);
		completePanel.add(backgroundlabel);
		
		
		this.add(completePanel);
		setVisible(true);
		
		
	}
	
	public void updateScore()
	{
		try{
		//reading from file
		File file = new File("ScoreTable.txt");
		FileReader reader = new FileReader(file);
		BufferedReader bfl = new BufferedReader(reader);
		String scor=bfl.readLine();
		String l=bfl.readLine();
		System.out.println("completed. score in file: "+scor+"lavel : "+l);
		int curr_sc=Integer.parseInt(scor);
		if(curr_sc>scorelabel.scorevalue) curr_sc=scorelabel.scorevalue;
		max_level=Integer.parseInt(l);
		reader.close();
		
		String s=Integer.toString(curr_sc)+"\r"+"\n"+l+"\r"+"\n";
		
		
		//writing on file
		//Scanner sc = new Scanner(System.in);
		//InputStreamReader isr = new InputStreamReader(System.in);
		//BufferedReader bfr = new BufferedReader(isr);
		file = new File("ScoreTable.txt");
		FileWriter writer = new FileWriter(file); 
		/*
		while(choice=='y')
		{
			temp = bfr.readLine();
			s=s+temp+"\r"+"\n";
			System.out.println("More? y for yes and n for no");
			choice=sc.next().charAt(0); 
		}
		*/
		writer.write(s); 
		writer.flush();
		writer.close();
		}
		catch(Exception e) {}
		
		
	}
	/*
	public void updateRequest()
	{
		
		System.out.println("\nupdateing level request\n");
		//double prevBalance=0, newBalance=0;
		Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		try
		{
			System.out.println("in updateRequest try");
			String query = "SELECT `userid`,`requestedlevel` FROM `player` where `userid`= '"+player+"';";     
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);
			System.out.println("results received");
			if(rs.next())
			{
				//String high = rs.getString("highscore");
				query = "UPDATE `player` SET requestedlevel="+(player_level+1)+" where `player`.`userid` = '"+player+"'";
				st.executeUpdate(query);
				System.out.println("updated level request and highscore");
				st.close();
				con.close();
				rs.close();
				
				
			}
			else 
			{
				System.out.println("not found");
			}
			System.out.println("done");
			
		}
		
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
	}
	*/
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource().equals(exit))
		{
			
			System.exit(0);
		}
		else if(e.getSource().equals(playagain))
		{
			this.setVisible(false);
			player_interface p=new player_interface();
			p.setVisible(true);
			//new player_interface();
		}
		else if(e.getSource().equals(go))
		{
			this.setVisible(false);
			//new Level(player_level+1,scorelabel);
		}
	}
	
	
	public void mouseEntered(MouseEvent me) //mouse entered
	{
		if(me.getSource().equals(exit)) exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else if(me.getSource().equals(playagain)) playagain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	public void mouseExited(MouseEvent me){} //
	public void mouseClicked(MouseEvent me) {}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	
	
}