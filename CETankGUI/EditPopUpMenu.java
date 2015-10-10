package CETankGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import CETankGame.*;

public class EditPopUpMenu extends JPopupMenu {

	
	JMenuItem ground , trap , wall , ice , box ,random ;
	MainFrame mainFrame;
	
	int row , col;
	public EditPopUpMenu(int row , int col ,MainFrame mainFrame) {
		
		this.mainFrame = mainFrame	;
		this.row = row;
		this.col = col;
		
		ground = new JMenuItem("Ground");
		trap = new JMenuItem("Trap");
		wall = new JMenuItem("Wall");
		ice = new JMenuItem("Ice");
		box = new JMenuItem("Box");
		random = new JMenuItem("Random");
		
		this.add(ground);
		this.add(trap);
		this.add(wall);
		this.add(ice);
		this.add(box);
		this.add(random);
		
		
		
		createActionListeners();
	
	}
	
	
	
	
	private void createActionListeners(){
		
		ground.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
				mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
				Ground ground = new Ground(row, col) ;
				mainFrame.newBlockActionListener(ground);
				mainFrame.editFrame.add(ground);
				mainFrame.map.edit(col, row, ground );
				mainFrame.editFrame.repaint();
				
			}
		});
		
		trap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
				mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
				Trap trap = new Trap(row, col) ;
				mainFrame.newBlockActionListener(trap);
				mainFrame.editFrame.add(trap);
				mainFrame.map.edit(col, row, trap );
				mainFrame.editFrame.repaint();
			}
		});
		wall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
				mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
				Wall wall = new Wall(row, col) ;
				mainFrame.newBlockActionListener(wall);
				mainFrame.editFrame.add(wall);
				mainFrame.map.edit(col, row, wall );
				mainFrame.editFrame.repaint();
			}
		});
		ice.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
				mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
				Ice ice = new Ice(row, col) ;
				mainFrame.newBlockActionListener(ice);
				mainFrame.editFrame.add(ice);
				mainFrame.map.edit(col, row, ice );
				mainFrame.editFrame.repaint();
			}
		});
		box.addActionListener(new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
			mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
			Box box = new Box(row, col) ;
			mainFrame.newBlockActionListener(box);
			mainFrame.editFrame.add(box);
			mainFrame.map.edit(col, row, box );
			mainFrame.editFrame.repaint();
		}
		});
		random.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.savedMaps.addLast(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
				mainFrame.editFrame.remove(mainFrame.map.getCell(row, col));
				Random random = new Random(row, col) ;
				mainFrame.newBlockActionListener(random);
				mainFrame.editFrame.add(random);
				mainFrame.map.edit(col, row, random );
				mainFrame.editFrame.repaint();
			}
			});
		
	}
}
