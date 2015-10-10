package CETankGame;
import java.io.Serializable;
import java.util.Random;
/**
 * the map that the game will be played on
 * @author Arash
 *
 */
public class Map {

	private int rows;
	private int cols;
	private Block[][] map;
	
	
	public Map(int col,int row)
	{
		rows = row;
		cols = col;
		map = new Block[row][col];
		
		for (int i = 0 ; i<row ; i++)
			for (int j = 0 ; j<col ; j++)
				map[i][j] = new Ground(i ,j);
	}
	
	public void edit(int col, int row, Block block)
	{
		map[row][col] = block ;
	}
	
	public String toString()
	{
		String result="";
		
		for (int i = 0 ; i<rows ; i++)
		{
			for (int j = 0 ; j<cols ; j++){
				result = result + map[i][j].getCharacter();
				if(j!=cols-1)
					result = result +"\t";
				
			}
			result = result + "\r\n";
			
		}
		return result;
		
	}
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	/**
	 * initialize random blocks
	 */
	public void randomBlocks()
	{
		 Random randomGenerator = new Random();

		for (int i = 0 ; i<rows ; i++)
			for (int j = 0 ; j<cols ; j++)
				if(map[i][j].getCharacter() == 'R')
				{
					int randomInt = randomGenerator.nextInt(5);
					switch(randomInt)
					{
						case 0 :
							map[i][j] = new Ground(i, j);
							break;
						case 1 :
							map[i][j] = new Wall(i, j);
							break;
						case 2 :
							map[i][j] = new Box(i, j);
							break;
						case 3 :
							map[i][j] = new Ice(i, j);
							break;
						case 4 :
							map[i][j] = new Trap(i, j);
							break;
							
					}
				}
	}
	
	public Block getCell(int row ,int col)
	{
		
		return map[row][col];
	}
}
