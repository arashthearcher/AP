package CETankGame;
import java.util.Scanner;

/**
 * environment that is text base and is the Editing environment of the game 
 * @author Arash Vahabzadeh
 *
 */
public class cmdEditEnvironment {


	 int  cols , rows , playerNum; ;
 	 Game game;
	 Map map;
	 Tank[] tanks;
	
	 Scanner sc = new Scanner(System . in);
	 
	 
	 
	 public Scanner getSc() {
		return sc;
	}

	 /**
	  * initializes the game 
	  * gets rows and columns of game  
	  */
	public void  gameInit()
	{
		
		
		
		rows = Integer.parseInt(sc.nextLine());
		cols = Integer.parseInt(sc.nextLine());
		playerNum = Integer.parseInt(sc.nextLine());
		
		tanks = new Tank[playerNum+1];
		map = new Map(cols , rows);
	}
	
	
	/**
	 * processes the Editing commands
	 * @return true if Game starts
	 */
	 boolean cmdEditPro()
	{
		
		String cmd = sc.nextLine();
		Scanner scS = new Scanner(cmd);
		
		String token;
		
		token = scS.next();
		
		
		
		if (token.equals("edit"))
		{
			int col , row ;
			char block;
			row = Integer.parseInt(scS.next());
			col = Integer.parseInt(scS.next());
			block = scS.next().charAt(0);
			cmdEdit(row , col , block);
			
			return false;
		}
		
		
		
		if (token.equals("print"))
		{
			System.out.print(map);
			return false ;
		}
		
		
		
		if (token.equals("setStart"))
		{
		int col , row ,number;
		row = Integer.parseInt(scS.next());
		col = Integer.parseInt(scS.next());
		number = Integer.parseInt(scS.next());
		
		tanks[number] = new Tank(number ,row , col);
		return false;
		}
		if (token.equals("Game"))
		{
			int num = 1;
			while(scS.hasNext())
			{
				tanks[num].setName(scS.next());
				tanks[num].setNumber(num);
				num++;
			}
			
//			game = new Game(tanks , map);
			
			return true;
		}
		System.out.println("cmd did not recognised");
		return false;
	}
	/**
	 * processes the "edit row col char" command
	 * @param row
	 * @param col
	 * @param ch
	 */
	 void cmdEdit(int row , int col ,char ch )
	{
		Block block = creatBlock(row,col ,ch);
		
		map.edit(col, row, block);
		
	}
	/**
	 * creates a block and returns its refrence
	 * @param row
	 * @param col
	 * @param ch
	 * @return
	 */
	 Block creatBlock(int row ,int col ,char ch)
	{
		Block block = new Ground(row ,col);
		
		if (ch=='G')
			block = new Ground(row ,col);
		if (ch=='W')
			block = new Wall(row ,col);
		if (ch=='B')
			block = new Box(row ,col);
		if (ch=='I')
			block = new Ice(row ,col);
		if (ch=='T')
			block = new Trap(row ,col);
		if (ch=='R')
			block = new Random(row ,col);
		
		return block;
	}

	 /**
	  * 
	  * @return the created game in edit environment
	  */
	 public Game getGame()
	 {
		 return game;
	 }
}
