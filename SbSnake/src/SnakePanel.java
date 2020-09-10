import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonListener;

public class SnakePanel extends JPanel implements KeyListener, ActionListener{
	boolean isDead;
	boolean isStart=true; 
	int score;
	int len=3;
	int foodx,foody;
	Random random=new Random();
	int[] snakex=new int[750];
	int[] snakey=new int[750];
	enum Direction {L,U,R,D}
	Direction snakeDirection=Direction.R;
	Timer timer=new Timer(150, this);
	ImageIcon titleIcon=new ImageIcon("title.jpg");
	ImageIcon upIcon=new ImageIcon("up.png");
	ImageIcon downIcon=new ImageIcon("down.png");
	ImageIcon leftIcon=new ImageIcon("left.png");
	ImageIcon rightIcon=new ImageIcon("right.png");
	ImageIcon bodyIcon=new ImageIcon("body.png");
	ImageIcon foodIcon=new ImageIcon("food.png");
	
	public SnakePanel() {
		snakex[0]=100;
		snakey[0]=150;
		foodx=random.nextInt(34)*25+25;
		foody=random.nextInt(24)*25+75;
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.gray);
		g.setColor(Color.black);
		g.fillRect(25,75, 850, 600);
		titleIcon.paintIcon(this, g, 25, 11);
		foodIcon.paintIcon(this, g, foodx, foody);
		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Score:"+score, 775, 40);
		g.drawString("Length:"+len, 775, 60);
		if(isStart==false) {
			g.setColor(Color.red);
			g.setFont(new Font("arial",Font.BOLD,20));
			g.drawString("press space to start", 200, 200);
		}
		if(isDead==true) {
			g.setColor(Color.red);
			g.setFont(new Font("arial",Font.BOLD,20));
			g.drawString("you are dead", 200, 200);
		}
		
		switch (snakeDirection) {
		case L:
			leftIcon.paintIcon(this, g, snakex[0],snakey[0] );
			break;
		case U:
			upIcon.paintIcon(this, g, snakex[0],snakey[0] );
			break;
		case R:
			rightIcon.paintIcon(this, g, snakex[0],snakey[0] );
			break;
		case D:
			downIcon.paintIcon(this, g, snakex[0],snakey[0] );
			break;
			
		default:
			break;
		}
		for (int i = 1; i <len; i++) {
			bodyIcon.paintIcon(this, g, snakex[i], snakey[i]);
		}
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_SPACE:
			isStart=!isStart;
			repaint();
			break;
		case KeyEvent.VK_LEFT:
			if(snakeDirection!=Direction.R) {
				snakeDirection=Direction.L;
			}
			break;
		case KeyEvent.VK_UP:
			if(snakeDirection!=Direction.D) {
				snakeDirection=Direction.U;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(snakeDirection!=Direction.L) {
				snakeDirection=Direction.R;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(snakeDirection!=Direction.U) {
				snakeDirection=Direction.D;
			}
			break;
			
		}
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {	
	}
	public void actionPerformed(ActionEvent arg0) {
		for (int i = 1; i < len; i++) {
			if(snakex[i]==snakex[0]&&snakey[i]==snakey[0]) {
				isDead=true;
				repaint();
			}
		}
		
		if(isDead==false&&isStart==true) {
			if(snakex[0]==foodx&&snakey[0]==foody) {
			len++;
			score+=10;
			foodx=random.nextInt(34)*25+25;
			foody=random.nextInt(24)*25+75;
		}
		for (int i = len-1; i >0; i--) {
			snakex[i]=snakex[i-1];
			snakey[i]=snakey[i-1];
		}
		
		switch (snakeDirection) {
		case R:
			snakex[0]+=25;
			if(snakex[0]>850) {
				snakex[0]=25;
			}
			break;
		case L:
			snakex[0]-=25;
			if(snakex[0]<25) {
				snakex[0]=850;
			}
			break;
		case U:
			snakey[0]-=25;
			if(snakey[0]<75) {
				snakey[0]=700;
			}
			break;
		case D:
			snakey[0]+=25;
			if(snakey[0]>700) {
				snakey[0]=75;
			}
			break;
			
		default:
			break;
		}
		repaint();
		}
		timer.start();
	}

}
