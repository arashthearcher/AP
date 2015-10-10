package CETankGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameModeSelectionFrame extends JFrame {
	
	JButton singlePlayer , multiPlayer ;
	final MainFrame mainFrame;
	
	public GameModeSelectionFrame(final MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		multiPlayer = new  JButton("Host Game");
		singlePlayer =new JButton("Single player");
		
		
		this.setLayout(new GridLayout());
		
		this.add(multiPlayer);
		this.add(singlePlayer);
		
		multiPlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameModeSelectionFrame.this.dispose();
				mainFrame.initializeServerGame();
				
			}
		});
		
		singlePlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				GameModeSelectionFrame.this.dispose();
				mainFrame.initializeSingleGame();
				
			}
		});
		
		
		
		this.setBounds(400, 300, 300, 150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
