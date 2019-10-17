import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
public class player_interface extends JFrame implements ActionListener
{
	JButton level1,level2,level3,level4,logout,label,currentLevel;
	JPanel panelselect;
	JLabel l,l2,backgroundlabel;
	int WIDITH=1800,HEIGHT=900;
	String player_status="valid";
	int max_level,high_score;
	Score scorelabel;
	public player_interface()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(10,10,WIDITH,HEIGHT);
		
		panelselect=new JPanel();
		panelselect.setLayout(null);
		
		level1=new JButton("level1");
		level1.setBounds(800,100,100,30);
		level1.setFont(new Font("arial",Font.BOLD,15));
		level1.addActionListener(this);
		level1.setEnabled(false);
		panelselect.add(level1);
		
		level2=new JButton("level2");
		level2.setBounds(800,200,100,30);
		level2.setFont(new Font("arial",Font.BOLD,15));
		level2.addActionListener(this);
		level2.setEnabled(false);
		panelselect.add(level2);
		
		
		level3=new JButton("level3");
		level3.setBounds(800,300,100,30);
		level3.setFont(new Font("arial",Font.BOLD,15));
		level3.addActionListener(this);
		level3.setEnabled(false);
		panelselect.add(level3);
		
		level4=new JButton("level4");
		level4.setBounds(800,400,100,30);
		level4.setFont(new Font("arial",Font.BOLD,15));
		level4.addActionListener(this);
		level4.setEnabled(false);
		panelselect.add(level4);
		
		backgroundlabel=new JLabel(new ImageIcon("pictures/background.png"));
		backgroundlabel.setBounds(0,0,WIDITH,HEIGHT);
		panelselect.add(backgroundlabel);
		
		
		scorelabel=new Score("Score : 0");
		scorelabel.setFont(new Font("ARIAL",Font.BOLD,30));
		scorelabel.setForeground(Color.WHITE);
		
		
		
		readFile();
		
		
		
		if(max_level==0){}
		else if(max_level==1) level1.setEnabled(true);
		else if(max_level==2)
		{
			level1.setEnabled(true);
			level2.setEnabled(true);
		}
		else if(max_level==3)
		{
			level1.setEnabled(true);
			level2.setEnabled(true);
			level3.setEnabled(true);
		}
		else if(max_level==4)
		{
			level1.setEnabled(true);
			level2.setEnabled(true);
			level3.setEnabled(true);
			level4.setEnabled(true);
		}
		
		this.add(panelselect);
		setVisible(true);
	}
	public void readFile()
	{
		try{
			File file = new File("ScoreTable.txt");
			FileReader reader = new FileReader(file);
			BufferedReader bfl = new BufferedReader(reader);
			String scor=bfl.readLine();
			String l=bfl.readLine();
			
			high_score=Integer.parseInt(scor);
			max_level=Integer.parseInt(l);
			
			System.out.println("initial : score : "+scor+" level : "+l);
			  
			reader.close();
		}
		catch(Exception e){}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(level1))
		{
			this.setVisible(false);
			Level l=new Level(1,scorelabel);
		}
		else if(e.getSource().equals(level2))
		{
			this.setVisible(false);
			Level l=new Level(2,scorelabel);
		}
		else if(e.getSource().equals(level3))
		{
			this.setVisible(false);
			Level l=new Level(3,scorelabel);
		}
		else if(e.getSource().equals(level4))
		{
			this.setVisible(false);
			Level l=new Level(4,scorelabel);
		}	
	}
}