package CETankGame;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CETankGUI.GameFrame;
import CETankGUI.MainFrame;

/**
 * Operates whole Game's processes
 * @author Arash Vahabzadeh 
 *
 */

public class Game  {
	public Tank[] tanks;
	public Map map;
	int playerNum;
	private int turn = 0 ;
	private boolean gameFinished = false;
	MainFrame mainFrame;
	GameFrame frame;
	GameMode gameMode;
	
	/**
	 * Contractor for Game class takes tank and map
	 * @param tanks player's tanks
	 * @param map  Game map that game is played in it!!
	 */
	public Game(Tank[] tanks, Map map ,MainFrame mainFrame ,GameMode gameMode)
	{
		
		this.mainFrame = mainFrame;
		this.tanks = tanks;
		this.map = map;
		this.map.randomBlocks();
		this.playerNum = tanks.length;
		this.randomizetanklocations();
		this.gameMode = gameMode;
		
		
		
		for (int i = 1; i < tanks.length; i++) {
			tanks[i].updateTankLocation();
		}
	}
	public void setFrame()
	{
		if(gameMode == GameMode.singlePlayer)
			frame = this.mainFrame.gameFrame ;
		if(gameMode == GameMode.server)
			frame = this.mainFrame.serverGameFrame ;
		if(gameMode == GameMode.client)
			frame = this.mainFrame.clientGameFrame ;
	}
	/**
	 * 
	 * @return The tank that is its turn
	 */
	@Deprecated
	public Tank getTank()
	{
		if(tanks[turn] != null && (!tanks[turn].isLoser()))
			return tanks[turn];
		else
		{
			turnPP();
			return getTank();
			
		}
	}
	/**
	 * changes the turn
	 */
	@Deprecated
	private void turnPP()
	{
		turn++;
		turn = turn % playerNum;
	}
	
	/**
	 * command movements
	 * @param move could be u l d r
	 * @return true if movement was successful
	 */
	@Deprecated
	public boolean cmdMove(char move)
	{
		boolean flag;
		BulletIsotopicCheck();
		checkBulletsOutofBound();
		bulletVSBoxandTrap();
		bulletVSwall();
		
		
//		flag =move(move);
//		
//		
//		
//		if(!flag && move != 'f')
//			{
//				System.out.println("No Move.");
//			}
		
		
		
		checkBulletsOutofBound();
		bulletVSBoxandTrap();
		bulletVSwall();
		
		tankVStrap();
		tankVSbullet();
		
		turnPP();
		
		
		if(countAlliveTanks() == 1)
		{
			gameFinished = true;
			
			System.out.println("Tank "+tanks[detectWinner()].getName()+" wins.");
		}
		
		return true;
//		return flag;
	}
	

	
	
	
	public Map getMap()
	{
		return map;
		
	}
	public boolean isGameFinished() {
		return gameFinished;
	}
	/**
	 * move's tank forward
	 * @return true if successful
	 */
	
	
	
	/**
	 * checks that if two bullets are in one block destroy them if were isotope
	 */
	void  BulletIsotopicCheck()
	{
		for (int i = 1; i < tanks.length; i++) {
			for (int j = i+1; j < tanks.length; j++) {
				if(		   tanks[i] != null 
						&& tanks[i].bullet != null 
						&& tanks[j].bullet != null 
						&& tanks[i].bullet.getRow() == tanks[j].bullet.getRow() 
						&& tanks[i].bullet.getCol()==tanks[j].bullet.getCol() )
				{
					this.frame.layeredPane.remove(tanks[i].bullet);
					this.frame.layeredPane.remove(tanks[j].bullet);
					this.frame.repaint();
					tanks[i].bullet.setAlive(false);
					tanks[j].bullet.setAlive(false);
					tanks[i].bullet = null;
					tanks[j].bullet = null;
				}
			}
		}
	}
	/**
	 * moves bullets on step forward
	 */
	@Deprecated
	void BulletsMoveForward()
	{
		for (int i = 1; i < tanks.length; i++) 
		{
			if (tanks[i].bullet != null && tanks[i] != null)
				tanks[i].bullet.moveForward();
			
			
		}
	}
	/**
	 * destroys the bullets that are out of bound
	 */
	void checkBulletsOutofBound()
	{
		for (Tank tank : tanks) 
		{
			if (tank != null &&	tank.bullet != null  )
				if (!isInBound(tank.bullet.getRow(),tank.bullet.getCol()))
				{
					this.frame.layeredPane.remove(tank.bullet);
					this.frame.repaint();
					tank.bullet.setAlive(false);
					tank.bullet=null;
				}
		}
	}
	/**
	 * destroy bullet and trap or box if they were isotope
	 */
	void bulletVSBoxandTrap()
	{
		for (Tank tank : tanks) 
		{	if(tank != null && tank.bullet != null)
			{
				int Brow = tank.bullet.getRow();
				int Bcol = tank.bullet.getCol();
				Block block = map.getCell(Brow,Bcol);
				if (block.getCharacter() == 'B' || block.getCharacter() == 'T')
				{
					this.frame.layeredPane.remove(tank.bullet);
					tank.bullet.setAlive(false);
					tank.bullet = null;
					Block temp = new Ground(Brow,Bcol);
					this.frame.layeredPane.remove(map.getCell(Brow, Bcol));
					this.frame.layeredPane.add(temp);
					map.edit(Bcol, Brow, temp);
					this.frame.repaint();
				}
			
			}
		}
	}
	/**
	 * 
	 * destroy bullet if it is facing the wall
	 */
	void bulletVSwall()
	{
		for (Tank tank : tanks) 
		{
			if(tank != null && tank.bullet != null)
			{
				int Brow = tank.bullet.getRow();
				int Bcol = tank.bullet.getCol();
				Block block = map.getCell(Brow,Bcol);
				if (block.getCharacter() == 'W')
				{
					this.frame.layeredPane.remove(tank.bullet);
					this.frame.repaint();
					tank.bullet.setAlive(false);
					tank.bullet = null;
				}
				
			}
		}
	}
	/**
	 * moves the tank one step forward or backward 
	 * @param move u for forward and d for backward
	 * @return true if moving was successful
	 */
	
	/**
	 * destroy the tank if it is on a trap
	 */
	void tankVStrap()
	{
		for (int i = 1; i < tanks.length; i++)		 
		{	if(tanks[i] != null && (!tanks[i].isLoser()))
			{
				int Trow = tanks[i].getRow();
				int Tcol = tanks[i].getCol();
				Block block = map.getCell(Trow,Tcol);
				if (block.getCharacter() == 'T')
				{
					mainFrame.gameFrame.changeExceptionInformerText("Tank "+tanks[i].getName()+" loses.");
					tanks[i].setLoser(true);
					this.frame.layeredPane.remove(tanks[i]);
					Block temp = new Ground(Trow,Tcol);
					this.frame.layeredPane.remove(map.getCell(Trow, Tcol));
					this.frame.layeredPane.add(temp);
					map.edit(Tcol, Trow, temp);
					this.frame.repaint();
					if(countAlliveTanks()==1)
						JOptionPane.showMessageDialog(mainFrame.gameFrame, "Tank "+tanks[detectWinner()].getName()+" wins");
				}
			
			}
		}
		
	}
	/**
	 * destroy the tank and bullet if they were in a same block
	 */
	void tankVSbullet()
	{
		for (int i = 1; i < tanks.length; i++) {
			for (int j = 1; j < tanks.length; j++) {
				if(			tanks[i] != null
						&&	tanks[j] != null
						&&	tanks[j].bullet != null
						&&	!tanks[i].isLoser()
						&&	tanks[i].getRow()==tanks[j].bullet.getRow()
						&&	tanks[i].getCol()==tanks[j].bullet.getCol()	)
				{
					mainFrame.gameFrame.changeExceptionInformerText("Tank "+tanks[i].getName()+" loses.");
					tanks[i].setLoser(true);
					this.frame.layeredPane.remove(tanks[i]);
					this.frame.layeredPane.remove(tanks[j].bullet);
					this.frame.repaint();
					tanks[j].bullet.setAlive(false);
					tanks[j].bullet = null;
					if(countAlliveTanks()==1)
						JOptionPane.showMessageDialog(mainFrame.gameFrame, "Tank "+tanks[detectWinner()].getName()+" wins");
				}
			}
		}
	}
	/**
	 * counts  alive tanks
	 * @return number of tanks that are alive
	 */
	int countAlliveTanks()
	{
		int counter=0;
		for (Tank tank : tanks) 
		{
			if(tank != null && (!tank.isLoser()))
					counter++;
		}
		return counter;
	}
	
	/**
	 * 
	 * @return winner's number
	 */
	int detectWinner()
	{
		for (Tank tank : tanks) {
			if (tank != null && !tank.isLoser())
				return tank.getNumber();
		
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return true if the input block is in bound
	 */
	boolean isInBound(int row , int col)
	{
		if (	
				row>=0
			&&	col>=0
			&&	row<map.getRows()
			&&	col<map.getCols())
			return true;
		else 
			return false;
	}
	/**
	 * moves until the ice is over or a obstacle is reached
	 * @param move
	 * @return
	 */
	
	
	public void randomizetanklocations()
	{
		boolean flag = true;
		Random randomGenerator = new Random(System.currentTimeMillis());
		int row ,col ;
		for (int i = 1; i < tanks.length; i++) {
			row =randomGenerator.nextInt(map.getRows());
			col = randomGenerator.nextInt(map.getCols());
			flag = true;
			while(flag){
				
				row =randomGenerator.nextInt(map.getRows());
				col = randomGenerator.nextInt(map.getCols());
				
				if (map.getCell(row, col).getCharacter() == 'G')
					flag=false;

				for (int j = 0; j < i; j++) {
					if (tanks[i].getRow()==row && tanks[i].getCol() == col)
						{
						flag = true;
						continue;
						}
					
				}
				
			}
				tanks[i].setRow(row);
				tanks[i].setCol(col);
		}
	}
	
	}
