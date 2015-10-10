package CETankGame;
import java.util.Scanner;

/**
 * environment that is text base and game will run in it 
 * @author Arash
 *
 */
public class cmdGameEnvironment {
	
	Game game;
	
	Scanner sc;
	
	public cmdGameEnvironment(Game game ,Scanner sc)
	{
		this.game = game;
		this.sc	= sc;
	}
	/**
	 * processes the game commands
	 * @return false if game is finished
	 */
	boolean cmdGamePro()
	{
		 
		String cmd = sc.nextLine();
		Scanner scS = new Scanner(cmd);
		
		String token;
		
		token = scS.next();
		
		
		if (token.equals("print"))
		{
			System.out.print(game.map);
		}
		
		
		
		if (token.equals("printAll"))
		{
			for (Tank tank : game.tanks) {
				if(tank != null && !tank.isLoser()){
					System.out.print(tank);
					if(tank.bullet != null)
						System.out.println("\t"+tank.bullet);
					System.out.println();
				}
				
			}
		}
		
		
		if (token.equals("move"))
		{
			
			char dir;
			
			dir = scS.next().charAt(0);
			
			game.cmdMove(dir);
			
//			i will do that in move method
//			if(!game.cmdMove(dir))
//			{
//				System.out.println("No Move.");
//			}
			
		}
		
		
		 if (token.equals("Exit"))
				return false;

		
		
//		System.out.println("cmd did not recognised");
		
		if (game.isGameFinished())
			return false ;
		else
			return true;
		
	}
}
