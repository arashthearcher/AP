package CETankGame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import CETankGUI.MainFrame;

public class Ground extends Block {

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ground at (" + this.getRow() +","+this.getCol()+")";
	}
	public  char getCharacter()
	{
		return 'G';
	}

	public Ground(int row  , int col)
	{
		super(row , col);
		
		
		
		
		
		try {
			image = ImageIO.read(getClass().getResource("/images/Ground.GIF"));
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
