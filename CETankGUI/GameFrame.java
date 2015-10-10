package CETankGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import CETankGame.Tank;

public class GameFrame extends JFrame
{

    MainFrame mainFrame;
    OptionFrame optionFrame;
    public JLayeredPane layeredPane;
    @Deprecated
    JLabel tankTurnInformer;
    JLabel exceptionInformer;

    public GameFrame(MainFrame mainFrame)
    {

	this.setLayout(null);

	this.mainFrame = mainFrame;

	layeredPane = new JLayeredPane();

	layeredPane.setBounds(0, 0, 1000, 600);

	this.setLayeredPane(layeredPane);

	this.addKeyListener();

	exceptionInformerInit();
	// this.tankTurnInformerInit();
	new ColorChanger(mainFrame);
	this.addTanksToFrame();
	this.createBlocks();
	this.setFocusable(true);
	this.requestFocus();
	this.setBounds(0, 0, 1000, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameFrame(MainFrame mainFrame, int a)
    {

	this.setLayout(null);

	this.mainFrame = mainFrame;

	layeredPane = new JLayeredPane();

	layeredPane.setBounds(0, 0, 1000, 600);
	this.setLayeredPane(layeredPane);

	exceptionInformerInit();
	new ColorChanger(mainFrame);
	this.addTanksToFrame();
	this.createBlocks();
	this.setFocusable(true);
	this.requestFocus();
	this.setBounds(0, 0, 1000, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void exceptionInformerInit()
    {
	exceptionInformer = new JLabel("");

	exceptionInformer.setBounds(720, 50, 300, 40);

	this.layeredPane.add(exceptionInformer);
    }

    public void changeExceptionInformerText(final String message)
    {
	new Thread() {
	    public void run()
	    {

		exceptionInformer.setText(message);
		exceptionInformer.repaint();
		try
		{
		    Thread.sleep(500);
		} catch (InterruptedException e)
		{
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		exceptionInformer.setText("");
		exceptionInformer.repaint();

	    };
	}.start();

    }

    private void createBlocks()
    {

	for (int i = 0; i < mainFrame.map.getRows(); i++)
	    for (int j = 0; j < mainFrame.map.getCols(); j++)
	    {
		this.layeredPane.add(mainFrame.map.getCell(i, j), 0, -1);

	    }
    }

    private void addKeyListener()
    {
	this.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent arg0)
	    {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void keyReleased(KeyEvent arg0)
	    {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void keyPressed(KeyEvent arg0)
	    {

		int keyCode = arg0.getKeyCode();

		switch (keyCode)
		{
		case KeyEvent.VK_UP:
		    if (!mainFrame.game.tanks[1].isLoser())
			mainFrame.game.tanks[1].move('u');
		    break;
		case KeyEvent.VK_DOWN:
		    if (!mainFrame.game.tanks[1].isLoser())
			mainFrame.game.tanks[1].move('d');
		    break;
		case KeyEvent.VK_LEFT:
		    if (!mainFrame.game.tanks[1].isLoser())
			new Thread() {
			    public void run()
			    {

				// mainFrame.game.tanks[1].rotateL();
				mainFrame.game.tanks[1].move('l');
			    }
			}.start();
		    break;
		case KeyEvent.VK_RIGHT:
		    if (!mainFrame.game.tanks[1].isLoser())
			new Thread() {
			    public void run()
			    {

				// mainFrame.game.tanks[1].rotateR();
				mainFrame.game.tanks[1].move('r');
			    }
			}.start();
		    break;
		case KeyEvent.VK_ENTER:

		    if (!mainFrame.game.tanks[1].isLoser())
		    {
			Tank tempTank = mainFrame.game.tanks[1];

			if (mainFrame.game.tanks[1].move('f'))
			    try
			    {
				GameFrame.this.layeredPane.add(tempTank.bullet, 1, -1);

			    } catch (Exception e)
			    {
				System.out.println("it was an nullPointerException but do not worry it is normal!!");
			    }
		    }
		    break;

		case KeyEvent.VK_W:
		    if (!mainFrame.game.tanks[2].isLoser())
			mainFrame.game.tanks[2].move('u');
		    break;
		case KeyEvent.VK_S:
		    if (!mainFrame.game.tanks[2].isLoser())
			mainFrame.game.tanks[2].move('d');
		    break;
		case KeyEvent.VK_A:
		    if (!mainFrame.game.tanks[2].isLoser())
			new Thread() {
			    public void run()
			    {
				// mainFrame.game.tanks[2].rotateL();
				mainFrame.game.tanks[2].move('l');

			    }
			}.start();
		    break;
		case KeyEvent.VK_D:
		    if (!mainFrame.game.tanks[2].isLoser())
			new Thread() {
			    public void run()
			    {
				// mainFrame.game.tanks[2].rotateR();
				mainFrame.game.tanks[2].move('r');

			    }
			}.start();
		    break;
		case KeyEvent.VK_Q:
		    if (!mainFrame.game.tanks[2].isLoser())
		    {
			Tank tempTank = mainFrame.game.tanks[2];

			if (mainFrame.game.tanks[2].move('f'))
			    try
			    {
				GameFrame.this.layeredPane.add(tempTank.bullet, 1, -1);

			    } catch (Exception e)
			    {
				System.out.println("it was an nullPointerException but do not worry it is normal!!");
			    }
		    }
		    break;
		case KeyEvent.VK_ESCAPE:
		    optionFrame = new OptionFrame(mainFrame);
		    break;

		}

		updateGameFrame();

	    }

	});

    }

    public void updateGameFrame()
    {
	for (int i = 1; i < mainFrame.game.tanks.length; i++)
	{

	    try
	    {
		mainFrame.game.tanks[i].updateTankLocation();

	    } catch (Exception e)
	    {
		// TODO: handle exception
	    }

	    try
	    {
		mainFrame.game.tanks[i].bullet.updateBulletLocation();

	    } catch (Exception e)
	    {
		// TODO: handle exception
	    }

	}
	// GameFrame.this.updateTankTurnInformerInit();
	GameFrame.this.repaint();
    }

    @Deprecated
    private void tankTurnInformerInit()
    {
	JLabel turnLabel = new JLabel("Turn:");
	turnLabel.setBounds(10, 10, 50, 30);
	this.layeredPane.add(turnLabel);
	tankTurnInformer = new JLabel();
	tankTurnInformer.setBounds(10, 30, 100, 30);
	tankTurnInformer.setText(mainFrame.game.getTank().getName());
	this.layeredPane.add(tankTurnInformer);
    }

    @Deprecated
    private void updateTankTurnInformerInit()
    {
	tankTurnInformer.setText(mainFrame.game.getTank().getName());
    }

    public void addTanksToFrame()
    {
	for (int i = 1; i < mainFrame.game.tanks.length; i++)
	{
	    this.layeredPane.add(mainFrame.game.tanks[i], 50 + i, -1);
	    mainFrame.game.tanks[i].updateTankLocation();

	}
	mainFrame.repaint();
    }

    public void updateBullets()
    {
	for (int i = 1; i < mainFrame.game.tanks.length; i++)
	{
	    if (mainFrame.game.tanks[i] != null)
	    {

	    }
	}
    }

}
