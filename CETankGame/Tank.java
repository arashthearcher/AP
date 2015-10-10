package CETankGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tank extends JLabel {
	public BufferedImage imageU;
	public BufferedImage imageR, imageL, imageD;
	private int number;
	private String name;
	private char dir ='u';
	private int col;
	private int row;
	private int newRow;
	private int newCol;
	private boolean isLoser = false;
	public Bullet bullet;
	public Game game; 
	
	
	
	public boolean isLoser() {
		return isLoser;
	}

	public void setLoser(boolean isLoser) {
		this.isLoser = isLoser;
	}

	public int getNewRow() {
		return newRow;
	}

	public void setNewRow(int newRow) {
		this.newRow = newRow;
	}

	public int getNewCol() {
		return newCol;
	}

	public void setNewCol(int newCol) {
		this.newCol = newCol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCol() {
		return col;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}


	public Tank(int number,String name )
	{
		
		this.number = number;
		this.name = name;
		
		
		try {
			imageU = ImageIO.read(getClass().getResource("/images/tank-r-u.png"));
			imageD = ImageIO.read(getClass().getResource("/images/tank-r-d.png"));
			imageR = ImageIO.read(getClass().getResource("/images/tank-r-r.png"));
			imageL = ImageIO.read(getClass().getResource("/images/tank-r-l.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public Tank(int number , int row ,int col)
	{
		this.row = row ;
		this.col = col ;
	
	}
	public void setDirection(char dir)
	{
		this.dir = dir;
	}
	public char getDirection()
	{
		return dir;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String result = "Tank No "+number+" "+name +" at ("+row+","+col+") "+ dir;
		return result;
	}
	

	/**
	 * move one step forward or backward
	 * @param move u as forward and d as backward
	 */
	void moveOneStep(char move)
	{
		if(move=='u')
		{
			switch(dir)
			{
			case 'u' :
				newRow --;
				break;
			case 'd' :
				newRow ++;
				break;
			case 'l' :
				newCol --;
				break;
			case 'r' :
				newCol ++;
				break;
					
			}
			
		}
		if(move == 'd')
		{
			switch(dir)
			{
			case 'u' :
				newRow ++;
				break;
			case 'd' :
				newRow --;
				break;
			case 'l' :
				newCol ++;
				break;
			case 'r' :
				newCol --;
				break;
						
			}
			
			
		}
	}
	/**
	 * copy row and col to newRow and newCol
	 */
	void oldsTonews()
	{
		newRow = row;
		newCol = col;
	}
	/**
	 * copy newRow and newCol to row and col
	 */
	void newsToolds()
	{
		row = newRow;
		col = newCol;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(dir == 'u')
			g.drawImage(imageU, 0, 0, null);
		if(dir == 'l')
			g.drawImage(imageL, 0, 0, null);
		if(dir == 'r')
			g.drawImage(imageR, 0, 0, null);
		if(dir == 'd')
			g.drawImage(imageD, 0, 0, null);
		
	}
	
	public void updateTankLocation()
	{
		this.setBounds(60*col+236, 60*row+54, 60, 60);
	}
	public synchronized void rotateR()
	{
		Graphics g = this.getGraphics();
		
		
		if(dir == 'u')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(i*9),25,25);
				
				g.drawImage(imageU, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'l')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(i*9),25,25);
				g.drawImage(imageL, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'r')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(i*9),25,25);
				g.drawImage(imageR, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'd')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(i*9),25,25);
				g.drawImage(imageD, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		
	}
	public synchronized void rotateL()
	{
		Graphics g = this.getGraphics();
		
		
		
		if(dir == 'u')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(-1*i*9),25,25);
				g.drawImage(imageU, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'l')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(-1*i*9),25,25);
				g.drawImage(imageL, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'r')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(-1*i*9),25,25);
				g.drawImage(imageR, 0, 0, null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		if(dir == 'd')
		{
			for (int i = 0; i < 5; i++) {
				((Graphics2D) g).rotate(Math.toRadians(-1*i*9),25,25);
				super.paint(g);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(imageD, 0, 0, null);
			}
			game.map.getCell(this.getRow(), this.getCol()).repaint();
		}
		
	}
	/**
	 * moves the tank
	 * @param move could be u l d r
	 * @return true if movement was successful
	 */
	public synchronized boolean move(char move)
	{
		
		
		
		switch(move)
		{
		case 'u' :
			return moveU();
		case 'd' :
			return moveD();
		case 'r' :
			return moveR();
			
		case 'l' :
			return moveL();
		case 'f' :
			return moveF();
		default	:
			System.out.println("U just can use( u d r l f ) as input ");
			break;
			
		
		}
		
		
		
		
		
		
		
		return false;
	}

	private boolean moveU()
	{
		return moveForwardorBackward('u');
	}
	/**
	 * move's tank backward
	 * @return true if successful
	 */
	private boolean moveD()
	{
		return moveForwardorBackward('d');
	}
	/**
	 * rotates tank to right
	 * @return
	 */
	private boolean moveR()
	{
		switch(this.getDirection())
		{
		case 'u' :
			this.setDirection('r');
			break;
		case 'r' :
			this.setDirection('d');
			break;
		case 'd' :
			this.setDirection('l');
			break;
		case 'l' :
			this.setDirection('u');
			break;
			
			
		}
		return true;
	}
	/**
	 * rotates tank to left
	 * @return
	 */
	private boolean moveL()
	{
		
		switch(this.getDirection())
		{
		case 'u' :
			this.setDirection('l');
			break;
		case 'r' :
			this.setDirection('u');
			break;
		case 'd' :
			this.setDirection('r');
			break;
		case 'l' :
			this.setDirection('d');
			break;
			
			
		}
		
		return true;
	}
	/**
	 * fires the bullet
	 * @return true if successful
	 */
	private boolean moveF()
	{
		if (this.bullet != null){
			try {
				throw new CanNotFireException() ;
				
			} catch (Exception e) {

			game.mainFrame.gameFrame.changeExceptionInformerText("slow down!! you have already fired one.");
			}
//			System.out.println("No Fire.");
			return false;
		}
		else
		{
			this.bullet = new Bullet(this,game);
			return true;
		}
			
	}
	public boolean moveForwardorBackward(char move)
	{
		
		this.oldsTonews();
		this.moveOneStep(move);
		
		
		if(isNextMoveOnIce(move))
		{
			this.newsToolds();
			 moveOnIce(move);
			 game.tankVStrap();
			 return true;
		}
		
		if (canMove(move))
		{
			this.newsToolds();
			game.tankVStrap();
			return true;
		}
		game.tankVStrap();
		return false;
	}
	/**
	 * 
	 * @return true if tank can move false ...
	 */
	private boolean canMove(char move)
	{

		
		
			
		
		
			int newRow = this.getNewRow();
			int newCol = this.getNewCol();

		
		try{
			
			if(!game.isInBound(newRow , newCol))
			{
				throw new MapOutOfBoundException();
	}
		
			if(game.map.getCell(newRow, newCol).getCharacter() == 'W')
			{
					throw new ToWallException();
			}
			if(game.map.getCell(newRow, newCol).getCharacter() == 'B')
			{
					throw new ToBoxException();
			}
			
} catch (MapOutOfBoundException e) {
	
	game.mainFrame.gameFrame.changeExceptionInformerText("U can not retreat!!");
	
}
 catch (ToWallException e) {
	
	 game.mainFrame.gameFrame.changeExceptionInformerText( "it can not enter wall blocks");
	
}
catch (ToBoxException e) {
		
	game.mainFrame.gameFrame.changeExceptionInformerText( "its tank, but it can not enter box blocks");
		
	}
			
			
			
			
			
			if (	
				!game.isInBound(newRow , newCol)
			||	game.map.getCell(newRow, newCol).getCharacter() == 'W' 
			|| 	game.map.getCell(newRow, newCol).getCharacter() == 'B'
			||	hasTank(newRow,newCol)  	)
			return false;
		
		
		return true;
		
	}
	/**
	 * checks if the next move is on ice 
	 * must be used after moveOneStep() method
	 * @param move u as forward and d for backward
	 * @return true if a ice block is next move 
	 */
	boolean isNextMoveOnIce(char move)
	{
		Tank thisTank = this; 
				
		int newRow = thisTank.getNewRow();
		int newCol = thisTank.getNewCol();
		if(game.isInBound(newRow,newCol) &&	game.map.getCell(newRow, newCol).getCharacter() == 'I')
			return true;
		else
			return false;
	}
	/**
	 * checks that the input block has tank 
	 * @param row
	 * @param col
	 * @return true if the block has tank
	 */
	boolean hasTank(int row ,int col )
	{
		for (Tank tank : game.tanks) 
		{
			if(tank != null && (!tank.isLoser()) && tank.getRow()==row && tank.getCol() == col)
				return true;
			
		} 
		return false;
	}
	/**
	 * checks that the input block has bullet 
	 * @param row
	 * @param col
	 * @return true if the block has bullet
	 */
	boolean hasBullet(int row ,int col)
	{

		for (Tank tank : game.tanks) 
		{
			if(tank != null && tank.bullet != null && tank.getRow()==row && tank.getCol() == col)
				return true;
			
		} 
		return false;
	}
	boolean moveOnIce(char move)
	{
		this.moveOneStep(move);
		
		if(isNextMoveOnIce(move) && canMove(move))
		{
			 this.newsToolds();
			 moveOnIce(move);
		}
		
		if (canMove(move))
		{
			this.newsToolds();
			return true;
		}
		return false;
	}
	
	
	
	
}

