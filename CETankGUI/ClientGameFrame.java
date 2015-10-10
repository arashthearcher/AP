package CETankGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import CETankGame.Tank;

public class ClientGameFrame extends GameFrame {
		
	public ClientGameFrame(MainFrame mainFrame) {
		super(mainFrame,1);
		this.addKeyListener();
	}



	private void addKeyListener() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

				int keyCode = arg0.getKeyCode();

				switch (keyCode) {
				case KeyEvent.VK_UP:
					if(!mainFrame.game.tanks[3].isLoser())
					mainFrame.game.tanks[3].move('u');
					mainFrame.client.clientWrite.sendMovement(3, 'u');
					break;
				case KeyEvent.VK_DOWN:
					if(!mainFrame.game.tanks[3].isLoser())
					mainFrame.game.tanks[3].move('d');
					mainFrame.client.clientWrite.sendMovement(3, 'd');
					break;
				case KeyEvent.VK_LEFT:
					if(!mainFrame.game.tanks[3].isLoser())
					new Thread(){
						public void run()
						{
							
//							mainFrame.game.tanks[3].rotateL();
							mainFrame.game.tanks[3].move('l');
							mainFrame.client.clientWrite.sendMovement(3, 'l');
						}
					}.start();
					break;
				case KeyEvent.VK_RIGHT:
					if(!mainFrame.game.tanks[3].isLoser())
					new Thread(){
						public void run()
						{
							
//							mainFrame.game.tanks[3].rotateR();
							mainFrame.game.tanks[3].move('r');
							mainFrame.client.clientWrite.sendMovement(3, 'r');
						}
					}.start();
					break;
				case KeyEvent.VK_ENTER:

					if(!mainFrame.game.tanks[3].isLoser())
					{
					Tank tempTank = mainFrame.game.tanks[3];

					if (mainFrame.game.tanks[3].move('f'))
						
						mainFrame.client.clientWrite.sendMovement(3, 'f');
//						try {
//							ClientGameFrame.this.layeredPane.add(tempTank.bullet,
//									1, -1);
//							
//						} catch (Exception e) {
//							System.out.println("it was an nullPointerException but do not worry it is normal!!");
//						}
					}
					break;
					
					
					
				case KeyEvent.VK_W:
					if(!mainFrame.game.tanks[4].isLoser())
					mainFrame.game.tanks[4].move('u');
					mainFrame.client.clientWrite.sendMovement(4, 'u');
					break;
				case KeyEvent.VK_S:
					if(!mainFrame.game.tanks[4].isLoser())
					mainFrame.game.tanks[4].move('d');
					mainFrame.client.clientWrite.sendMovement(4, 'd');
					break;
				case KeyEvent.VK_A:
					if(!mainFrame.game.tanks[4].isLoser())
					new Thread(){
						public void run()
						{
//							mainFrame.game.tanks[4].rotateL();
							mainFrame.game.tanks[4].move('l');
							mainFrame.client.clientWrite.sendMovement(4, 'l');
							
						}
					}.start();
					break;
				case KeyEvent.VK_D:
					if(!mainFrame.game.tanks[4].isLoser())
					new Thread(){
						public void run()
						{
//							mainFrame.game.tanks[4].rotateR();
							mainFrame.game.tanks[4].move('r');
							mainFrame.client.clientWrite.sendMovement(4, 'r');
							
						}
					}.start();
					break;
				case KeyEvent.VK_Q:
					if(!mainFrame.game.tanks[4].isLoser())
					{
					Tank tempTank = mainFrame.game.tanks[2];

					if (mainFrame.game.tanks[4].move('f'))
						mainFrame.client.clientWrite.sendMovement(4, 'f');
//						try {
//							ClientGameFrame.this.layeredPane.add(tempTank.bullet,
//									1, -1);
//							
//						} catch (Exception e) {
//							System.out.println("it was an nullPointerException but do not worry it is normal!!");
//						}
					}
					break;
					
				}

			updateClientGameFrame();
				
			}

		});
		

	}
	public void updateClientGameFrame()
	{
		for (int i = 1; i < mainFrame.game.tanks.length; i++) {

			try {
				mainFrame.game.tanks[i].updateTankLocation();

			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				mainFrame.game.tanks[i].bullet.updateBulletLocation();

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
//		GameFrame.this.updateTankTurnInformerInit();
		ClientGameFrame.this.repaint();
	}
	
}
