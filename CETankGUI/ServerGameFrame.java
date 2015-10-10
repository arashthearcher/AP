package CETankGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import CETankGame.Tank;

public class ServerGameFrame extends GameFrame {
	public ServerGameFrame(MainFrame mainFrame) {
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
					if(!mainFrame.game.tanks[1].isLoser())
					mainFrame.game.tanks[1].move('u');
					mainFrame.server.serverWrite.sendMovement(1, 'u');
					break;
				case KeyEvent.VK_DOWN:
					if(!mainFrame.game.tanks[1].isLoser())
					mainFrame.game.tanks[1].move('d');
					mainFrame.server.serverWrite.sendMovement(1, 'd');
					break;
				case KeyEvent.VK_LEFT:
					if(!mainFrame.game.tanks[1].isLoser())
					new Thread(){
						public void run()
						{
							
//							mainFrame.game.tanks[1].rotateL();
							mainFrame.game.tanks[1].move('l');
							mainFrame.server.serverWrite.sendMovement(1, 'l');
						}
					}.start();
					break;
				case KeyEvent.VK_RIGHT:
					if(!mainFrame.game.tanks[1].isLoser())
					new Thread(){
						public void run()
						{
							
//							mainFrame.game.tanks[1].rotateR();
							mainFrame.game.tanks[1].move('r');
							mainFrame.server.serverWrite.sendMovement(1, 'r');
						}
					}.start();
					break;
				case KeyEvent.VK_ENTER:

					if(!mainFrame.game.tanks[1].isLoser())
					{
					Tank tempTank = mainFrame.game.tanks[1];

					if (mainFrame.game.tanks[1].move('f'))
						
						mainFrame.server.serverWrite.sendMovement(1, 'f');
//						try {
//							ServerGameFrame.this.layeredPane.add(tempTank.bullet,
//									1, -1);
//							
//						} catch (Exception e) {
//							System.out.println("it was an nullPointerException but do not worry it is normal!!");
//						}
					}
					break;
					
					
					
				case KeyEvent.VK_W:
					if(!mainFrame.game.tanks[2].isLoser())
					mainFrame.game.tanks[2].move('u');
					mainFrame.server.serverWrite.sendMovement(2, 'u');
					break;
				case KeyEvent.VK_S:
					if(!mainFrame.game.tanks[2].isLoser())
					mainFrame.game.tanks[2].move('d');
					mainFrame.server.serverWrite.sendMovement(2, 'd');
					break;
				case KeyEvent.VK_A:
					if(!mainFrame.game.tanks[2].isLoser())
					new Thread(){
						public void run()
						{
//							mainFrame.game.tanks[2].rotateL();
							mainFrame.game.tanks[2].move('l');
							mainFrame.server.serverWrite.sendMovement(2, 'l');
							
						}
					}.start();
					break;
				case KeyEvent.VK_D:
					if(!mainFrame.game.tanks[2].isLoser())
					new Thread(){
						public void run()
						{
//							mainFrame.game.tanks[2].rotateR();
							mainFrame.game.tanks[2].move('r');
							mainFrame.server.serverWrite.sendMovement(2, 'r');
							
						}
					}.start();
					break;
				case KeyEvent.VK_Q:
					if(!mainFrame.game.tanks[2].isLoser())
					{
					Tank tempTank = mainFrame.game.tanks[2];

					if (mainFrame.game.tanks[2].move('f'))
						mainFrame.server.serverWrite.sendMovement(2, 'f');
//						try {
//							ServerGameFrame.this.layeredPane.add(tempTank.bullet,
//									1, -1);
//							
//						} catch (Exception e) {
//							System.out.println("it was an nullPointerException but do not worry it is normal!!");
//						}
					}
					break;
					
				}

			updateServerGameFrame();
				
			}

		});
		

	}
	public void updateServerGameFrame()
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
		ServerGameFrame.this.repaint();
	}
}
