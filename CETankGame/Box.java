package CETankGame;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends Block {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Box at (" + this.getRow() +","+this.getCol()+")";
	}

	@Override
	public char getCharacter() {
		// TODO Auto-generated method stub
		return 'B';
	}

	public Box(int row  , int col)
	{
		super(row , col); 
		try {
			image = ImageIO.read(getClass().getResource("/images/Box.GIF"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(image, 0, 0, null);
		
	}
}