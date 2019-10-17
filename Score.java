import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class Score extends JLabel 
{
	Headshot hshot;
	int scorevalue=0;
	public Score(String s)
	{
		setText(s);	
	}
	public void updateScore(int l)
	{
		scorevalue+=l;
	}
	public void updateText()
	{
		setText("Score : "+scorevalue);
	}
}