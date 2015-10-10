package CETankGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import CETankGUI.ServerGameFrame;

public class Bullet extends JLabel {
	
	BufferedImage image;
	
	private char dir;
	private int row;
	private	int col;
	private Tank tank;
	public boolean isAddedToFrame = false;
	private boolean isAlive = true;
	public boolean isAlive() {
		return isAlive;
	}

	
	
	
	public char getDir() {
		return dir;
	}




	public void setDir(char dir) {
		this.dir = dir;
	}




	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public 	moveBulletForwardThread mBFT ; 
	public Game game;
	
	
	public String toString()
	{
		String result ;
		result = "Bullet at ("+row+","+col+") form Tank no "+ tank.getNumber();
		return result;
	}
	
	public Bullet(Tank tank ,Game game)
	{
		
		this.game = game;
		mBFT = new moveBulletForwardThread(this);
		dir = tank.getDirection();
		this.tank = tank ;
		
		
		row = tank.getRow();
		col = tank.getCol();
		
		try {
			image = ImageIO.read(getClass().getResource("/images/Bullet.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			game.frame.layeredPane.add(this,
					1, -1);
			
		} catch (Exception e) {
			System.out.println("it was an nullPointerException but do not worry it is normal!!");
		}
	
		
		mBFT.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(image, 0, 0, null);
		
	}
	

	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public char getDirection() {
		return dir;
	}
	public Tank getTank() {
		return tank;
	}
	public void moveForward()
	{
		switch(dir)
		{
		case 'u' :
			row --;
			break;
		case 'd' :
			row ++;
			break;
		case 'l' :
			col --;
			break;
		case 'r' :
			col ++;
			break;
		}
	}
	public void updateBulletLocation()
	{
		this.setBounds(60*col+248, 60*row+68, 60, 60);
	}

}
