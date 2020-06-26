import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{// Creation of a subclass of the Jpanel class that implements two interfaces
									 // (New Jpanel like class)
	

	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	// Variables for movement
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	// images for all four directions
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthofsnake = 3;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakebody;
	
	private int[] foodxpos = {50, 75, 100, 125, 150, 175, 200, 225,
			250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525,
			550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int[] foodypos = {100, 125, 150, 175, 200, 225,
			250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525,
			550, 575, 600, 625};
	
	private ImageIcon foodimage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(33);
	private int ypos = random.nextInt(20);

	private int score = 0;
	
	private int moves = 0;
	
	private boolean boolGameOver = true;
	
	private ImageIcon titleImage;
	
	public Gameplay() {				 // Creation of a new constructor for this class
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {	 // Creation of the paint function that will be using a Graphics object
			
		if(moves == 0) { 	//definition of the start position of the snake
							// moves are only equal to 0 if the game is starting 
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
			
			rightmouth = new ImageIcon("rightmouth.png");
			rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		
		// drawing of the title image border
		g.setColor(Color.white);
		g.drawRect(21, 10, 851, 55);
		
		// Drawing of the title image
		titleImage = new ImageIcon("snaketitle.png");
		titleImage.paintIcon(this, g, 22, 11);	//this as the component? g as the graphics context, x and y for pos
		
		// drawing of the border for the gameplay
		g.setColor(Color.WHITE);
		g.drawRect(20, 74, 852, 577);
		
		// drawing of the background for the gameplay
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(21, 75, 851, 575);
		
		// drawing of the score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " + lengthofsnake, 780, 30);
		
		// drawing of the length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 50);
		
		for(int i = 0; i < lengthofsnake; i++) {
			
			if(i==0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			if(i!=0) {
				snakebody =new ImageIcon("snakebody.png");
				snakebody.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}
		
		foodimage = new ImageIcon("food.png");
		
		if((foodxpos[xpos] == snakexlength[0]) && foodypos[ypos] == snakeylength[0]) 
		{
			score++;
			lengthofsnake++;
			xpos = random.nextInt(33);
			ypos = random.nextInt(20);
		}
		
		foodimage.paintIcon(this, g, foodxpos[xpos], foodypos[ypos]);
		
		for (int b = 1; b < lengthofsnake; b++) 
		{
			if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) 
			{
				right = false;
				left = false;
				up = false;
				down = false;
				boolGameOver = true;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) 
		{
			for(int r = lengthofsnake-1; r>=0; r--) 
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r-- ) 
			{
				if(r==0) 
				{
					snakexlength[r] = snakexlength[r] + 25;
				}
				else 
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] > 850) 
				{
					snakexlength[r] = 25;
				}
			}
			
			repaint();
		}
		if(left) 
		{
			for(int r = lengthofsnake-1; r>=0; r--) 
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r-- ) 
			{
				if(r==0) 
				{
					snakexlength[r] = snakexlength[r] - 25;
				}
				else 
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] < 25) 
				{
					snakexlength[r] = 850;
				}
			}
			
			repaint();
		}
		if(up) 
		{
			for(int r = lengthofsnake-1; r>=0; r--) 
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r-- ) 
			{
				if(r==0) 
				{
					snakeylength[r] = snakeylength[r] - 25;
				}
				else 
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 75) 
				{
					snakeylength[r] = 625;
				}
			}
			
			repaint();
		}
		if(down) 
		{
			for(int r = lengthofsnake-1; r>=0; r--) 
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r-- ) 
			{
				if(r==0) 
				{
					snakeylength[r] = snakeylength[r] + 25;
				}
				else 
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] > 625) 
				{
					snakeylength[r] = 75;
				}
			}
			
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			moves++;
//			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
//			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
//			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			
			right = false;
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
//			down = true;
			if(!up) {
				down = true;
			}
			else {
				up = false;
				down = true;
			}
			
			right = false;
			left = false;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
