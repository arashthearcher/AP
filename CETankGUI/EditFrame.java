package CETankGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CETankGame.Block;
import CETankGame.Game;
import CETankGame.GameMode;
import CETankGame.Ground;
import CETankGame.Map;
import CETankGame.Tank;

public class EditFrame extends JFrame {
	JMenuBar menuBar ;
	JMenuItem newMenu ,saveMenu , undoMenu , doneMenu ;
	MainFrame mainFrame;
	EditPopUpMenu menu ;
	
	
	public EditFrame(MainFrame mainFrame) {
		this.setLayout(null);
		
		
		
		this.mainFrame = mainFrame;
		
		menuBar = new JMenuBar();
		
		menuBar.setBounds(0, 0, 1000, 20);
		
		newMenu = new JMenuItem("new");
		saveMenu = new JMenuItem("save");
		undoMenu = new JMenuItem("undo");
		doneMenu = new JMenuItem("done");
		
		menuBar.add(newMenu);
		menuBar.add(saveMenu);
		menuBar.add(undoMenu);
		menuBar.add(doneMenu);
		
		this.add(menuBar);
		createBlocks();
		addActionListenertoBlocks();
		createMenuActionListener();
		this.setBounds(0, 0, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	private void createMenuActionListener()
	{
		newMenuActionListener();
		saveMenuActionListener();
		undoMenuActionListener();
		doneMenuActionListener();
	}
	private void createBlocks()
	{

		
		for (int i = 0; i < mainFrame.map.getRows(); i++) 
			for (int j = 0; j < mainFrame.map.getCols(); j++){
				this.add(mainFrame.map.getCell(i, j));
				
		}
	}
	private void addActionListenertoBlocks(){
		
		for (int i = 0; i < mainFrame.map.getRows(); i++) 
			for (int j = 0; j < mainFrame.map.getCols(); j++)
			{
				
				mainFrame.newBlockActionListener(mainFrame.map.getCell(i, j));
			}
	}
	private void resetEditFrameBlocks()
	{
		this.removeAll();
		this.add(menuBar);
		createBlocks();
		addActionListenertoBlocks();
		
	}
	private void  newMenuActionListener()
	{
		newMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
				JLabel rowLB = new JLabel("rows");
				JLabel colLB = new JLabel("cols");
				JTextField rowtxt = new JTextField();
				JTextField coltxt = new JTextField();
				panel.setLayout(new GridLayout(2, 2));
				panel.add(rowLB);
				panel.add(coltxt);
				panel.add(colLB);
				panel.add(rowtxt);
				
				JOptionPane.showMessageDialog(null, panel);
				
				int row = Integer.parseInt(rowtxt.getText());
				int col = Integer.parseInt(coltxt.getText());
				
				
				mainFrame.map = new Map(col, row);
				
//				resetEditFrameBlocks();
				mainFrame.editFrame.setVisible(false);
				mainFrame.editFrame = new EditFrame(mainFrame);
				mainFrame.editFrame.setVisible(true);
				
			}
		});
	}
	private void saveMenuActionListener()
	{
		saveMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("maps"));
				 int returnVal = fc.showSaveDialog(mainFrame.editFrame);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            saveMap(file,false);
			            
			            
				 }
				
				
			}
		});
	}
	private void undoMenuActionListener()
	{
		undoMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				mainFrame.map = mainFrame.LoadMap(mainFrame.savedMaps.removeLast());
				mainFrame.editFrame.setVisible(false);
				mainFrame.editFrame = new EditFrame(mainFrame);
				mainFrame.editFrame.setVisible(true);
			}
		});
	}
	private void doneMenuActionListener()
	{
		doneMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				mainFrame.playerNum = Integer.parseInt(JOptionPane.showInputDialog("Number of players"));
//				mainFrame.playerNum++;
//				mainFrame.tanks = new Tank[mainFrame.playerNum];
//				JPanel panel = new JPanel();
//				JLabel[] labels = new JLabel[mainFrame.playerNum];
//				JTextField[] textField = new JTextField[mainFrame.playerNum];
//				panel.setLayout(new GridLayout(mainFrame.playerNum, 2));
//				for (int i = 1; i < labels.length; i++) {
//					labels[i] = new JLabel("player number "+i+":");
//					panel.add(labels[i]);
//					textField[i] = new JTextField();
//					panel.add(textField[i]);
//					
//				}
//				
//				JOptionPane.showMessageDialog(null, panel);
//				
//				for (int j = 1; j < textField.length; j++) {
//					mainFrame.tanks[j] = new Tank(j,textField[j].getText() );
//				}
//				
//				mainFrame.game = new Game(mainFrame.tanks, mainFrame.map , mainFrame,GameMode.singlePlayer);
//				
//				for (int j = 1; j < textField.length; j++) {
//					mainFrame.tanks[j].setGame(mainFrame.game);
//				}
//				mainFrame.gameFrame = new GameFrame(mainFrame);
//				mainFrame.gameFrame.setVisible(true);
//				mainFrame.game.setFrame();

				new GameModeSelectionFrame(mainFrame);
				EditFrame.this.dispose();
			}
		});
	}
	
	public void saveMap(File file,boolean append)
	{
		FileWriter outf;
		try {
			outf = new FileWriter(file,append);
			outf.write(mainFrame.map.getRows()+ " " +mainFrame.map.getCols()+" \n" + mainFrame.map.toString());
			outf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}









}
