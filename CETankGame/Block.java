package CETankGame;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.JLabel;

public abstract class Block extends JLabel {
	
	 int row;
	 int col;
	 BufferedImage image;
	 
	public abstract String toString();
	public abstract char getCharacter();
	
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

	public Block(int row ,int col)
	{
		this. row = row ;
		this .col = col ; 
		
		
		this.setBounds(60*col+230, 60*row+50, 60, 60);
		this.setVisible(true);
	}
}
