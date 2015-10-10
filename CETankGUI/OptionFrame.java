package CETankGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class OptionFrame extends JFrame {
	
	JButton resume , save , load , exit ;
	MainFrame mainFrame;
	
	
	public OptionFrame(MainFrame mainFrame) {

		
		this.mainFrame = mainFrame;
		
		this.setLayout(new GridLayout(4, 1));
		
		resume = new JButton("Resume Game");
		save = new JButton("Save Game ");
		load = new JButton("Load Game");
		exit = new JButton("Exit");
		
		this.add(resume);
		this.add(save);
		this.add(load);
		this.add(exit);
		
		
		this.addOptionFrameActionListener();
		
		this.setBounds(100, 100, 200, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	
	}
	
	
	private void addOptionFrameActionListener ()
	{
		
		resume.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				OptionFrame.this.dispose();
				
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("saveGames"));
				 int returnVal = fc.showSaveDialog(mainFrame.editFrame);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            saveMap(file,false);
			            saveGameStates(file, true);
				 }
		
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionFrame.this.dispose();
				mainFrame.gameFrame.dispose();
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
	public void saveGameStates(File file,boolean append)
	{
		FileWriter outf;
		try {
			outf = new FileWriter(file,append);
			
			
			
			outf.write(mainFrame.tanks.length+"\n");
			
			for (int i = 1; i < mainFrame.tanks.length; i++) {
				
				outf.write(mainFrame.tanks[i].getName()+' '+ mainFrame.tanks[i].getRow()+' '+mainFrame.tanks[i].getCol()+ ' ' +mainFrame.tanks[i].getDirection()+' '+mainFrame.tanks[i].isLoser()+"\n");
			}
			for (int i = 1; i < mainFrame.tanks.length; i++) {
				if (mainFrame.tanks[i].bullet != null)
				outf.write(i + ' ' + mainFrame.tanks[i].bullet.getRow() +' '+mainFrame.tanks[i].bullet.getCol()+' '+mainFrame.tanks[i].bullet.getDirection() );
			
			}
				
			outf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
