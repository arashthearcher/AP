package CETankGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CETankFile.OpenFiles;
import CETankGame.Block;
import CETankGame.Box;
import CETankGame.Bullet;
import CETankGame.Client;
import CETankGame.Game;
import CETankGame.GameMode;
import CETankGame.Ground;
import CETankGame.Ice;
import CETankGame.Main;
import CETankGame.Map;
import CETankGame.Random;
import CETankGame.Server;
import CETankGame.Tank;
import CETankGame.Trap;
import CETankGame.Wall;


public class MainFrame extends JFrame {
	public Tank[] tanks = new Tank[5];
	public Game game;
	public Map map;
	OpenFiles openFiles ;
	JButton startGame , multiPlayerServer , multiPlayerClient ,loadGame ,loadMap ,editMap ,exit ;
	EditFrame editFrame;
	public GameFrame gameFrame;
	public ClientGameFrame clientGameFrame ;
	public ServerGameFrame serverGameFrame ;
	
	public Server server ;
	public Client client ;
	LinkedList<String> savedMaps;
	int playerNum;
	Scanner fileScanner = null;
	
	public MainFrame() {
			
		this.setLayout(new GridLayout(4, 1));
		
		map = new Map(8, 8);
		
		
		savedMaps = new LinkedList<String>();
		
		openFiles = new OpenFiles();
		
		multiPlayerServer = new JButton("Host Game");
		multiPlayerClient = new JButton("Join Game");
		loadMap = new JButton("Load Map");
		editMap = new JButton("Edit Map");
		startGame = new JButton("Local Game");
		loadGame = new JButton("Load Game");
		exit = new JButton("Exit");
		
		this.add(multiPlayerServer);
		this.add(multiPlayerClient);
		this.add(loadMap);
		this.add(editMap);
		this.add(startGame);
		this.add(loadGame);
		this.add(exit);
		this.setBackground(Color.orange);
		
		
		buttonsActionImplementation();
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(250, 250);
		this.setBounds(0, 0, 250, 400);
		this.setVisible(true);
		
	
	}
	
	private void buttonsActionImplementation()
	{
		editMap.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editFrame = new EditFrame(MainFrame.this);
				editFrame.setVisible(true);
				MainFrame.this.setVisible(false);
			}
		});
		
		loadMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new FileChooserFilterMap());
				fc.setCurrentDirectory(new File("maps"));
				 int returnVal = fc.showOpenDialog(MainFrame.this);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            
			            MainFrame.this.map = LoadMap(file);
			            
			            editFrame = new EditFrame(MainFrame.this);
						editFrame.setVisible(true);
						MainFrame.this.setVisible(false);
				
				 }
			}
		});
		
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initializeSingleGame();

			}
		});
		
		multiPlayerServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				initializeServerGame();
				
			}
		});
		multiPlayerClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Panel panel = new Panel();
				panel.setLayout(new GridLayout(4,2) );
//				panel.add(new Label("Enter IP and Port number of Server Side"));
//				panel.add(new Label());
				
				JTextField firstName ,secondName , IP , Port;
				firstName = new JTextField();
				secondName = new JTextField();
				IP = new JTextField();
				Port = new JTextField();
				panel.add(new Label("First Player's Name"));
				panel.add(firstName);
				panel.add(new Label("Second Player's Name"));
				panel.add(secondName);
				panel.add(new Label("IP :"));
				panel.add(IP);
				panel.add(new Label("Port :"));
				panel.add(Port);
				
				JOptionPane.showMessageDialog(null, panel);
				
				
				client = new Client(MainFrame.this, IP.getText(), Integer.parseInt(Port.getText()));
				tanks[3] = new Tank(3, firstName.getText());
				tanks[4] = new Tank(4, secondName.getText());
				client.initializeConnection();
				
				game = new Game(tanks, map , MainFrame.this , GameMode.client);
				
				client.clientRead.recieveTanksInitialLocation();
				client.clientRead.start();
				
				for (int j = 1; j < 5; j++) {
					tanks[j].setGame(game);
				}
				clientGameFrame = new ClientGameFrame(MainFrame.this);
				clientGameFrame.setVisible(true);
				game.setFrame();
			}
		});
		
		loadGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new FileChooserFilter());
				fc.setCurrentDirectory(new File("saveGames"));
				 int returnVal = fc.showOpenDialog(MainFrame.this);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            
			            MainFrame.this.map = LoadMap(file);
			            loadGameStatesphase1();
			            for (int i = 0; i < tanks.length; i++) {
							tanks[i] = new Tank(i,"");
							tanks[i].setGame(game);
						}
			            MainFrame.this.game = new Game(tanks, map, MainFrame.this, GameMode.singlePlayer);
			            for (int i = 0; i < tanks.length; i++) {
							tanks[i].setGame(game);
						}
			            loadGameStatesphase2();
			            MainFrame.this.gameFrame = new GameFrame(MainFrame.this);
			            gameFrame.setVisible(true);
			    		game.setFrame();
				
				 }
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.dispose();
				System.exit(0);
				
			}
		});
	}
	
	public void initializeSingleGame()
	{
		
			playerNum =3;
			tanks = new Tank[playerNum];
			JPanel panel = new JPanel();
			JLabel[] labels = new JLabel[playerNum];
			JTextField[] textField = new JTextField[playerNum];
			panel.setLayout(new GridLayout(playerNum, 2));
			for (int i = 1; i < labels.length; i++) {
				labels[i] = new JLabel("player number "+i+":");
				panel.add(labels[i]);
				textField[i] = new JTextField();
				panel.add(textField[i]);
				
			}
			
			JOptionPane.showMessageDialog(null, panel);
			
			for (int j = 1; j < textField.length; j++) {
				tanks[j] = new Tank(j, textField[j].getText());
			}
			
			game = new Game(tanks, map , this ,GameMode.singlePlayer);
			
			
			for (int j = 1; j < textField.length; j++) {
				tanks[j].setGame(game);
			}
			gameFrame = new GameFrame(this);
			gameFrame.setVisible(true);
			game.setFrame();
		
	}
	
	public void initializeServerGame ()
	{
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(2,2) );
		
		JTextField firstName ,secondName ;
		firstName = new JTextField();
		secondName = new JTextField();
		panel.add(new Label("First Player's Name"));
		panel.add(firstName);
		panel.add(new Label("Second Player's Name"));
		panel.add(secondName);
		
		JOptionPane.showMessageDialog(null, panel);
		
		
		String port =JOptionPane.showInputDialog("Enter Port number u wanna listen on");
		
//		JOptionPane.showMessageDialog(null,"please wait for client to connect!!");
		
		
		server = new Server(this, Integer.parseInt(port));
						
		
		tanks = new Tank[5];
		tanks[1] = new Tank(1, firstName.getText());
		tanks[2] = new Tank(2, secondName.getText());
		
		server.initializeConnection();
		
		game = new Game(tanks, map , this , GameMode.server);
		
		server.serverWrite.sendMap();
		server.serverWrite.sendTanksInitialLocation();
		
		for (int j = 1; j < 5; j++) {
			tanks[j].setGame(game);
		}
		serverGameFrame = new ServerGameFrame(this);
		serverGameFrame.setVisible(true);
		game.setFrame();
	}
	
	
	public void newBlockActionListener(Block block)
	{
		block.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				EditPopUpMenu menu = new EditPopUpMenu(((Block)(arg0.getSource())).getRow(), ((Block)(arg0.getSource())).getCol(), MainFrame.this);
				menu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			}
		});
	}
	
	public Map LoadMap(File file)
	{
		Map loadedMap = null;
		
		try {
			 fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rows = Integer.parseInt(fileScanner.next());
		int cols = Integer.parseInt(fileScanner.next());
		
		loadedMap = new Map(cols, rows);
		char ch;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ch = fileScanner.next().charAt(0);
				loadedMap.edit(j, i, creatBlock(i, j, ch));
			}
		}
		
		return loadedMap;
	}
	
	
	public void loadGameStatesphase1()
	{
		int tankNum =fileScanner.nextInt();
		
		this.tanks = new Tank[tankNum];

	}
	
	public void loadGameStatesphase2()
	{
			for (int i = 1; i < tanks.length ; i++) {
			
			tanks[i].setName(fileScanner.next());
			tanks[i].setRow(fileScanner.nextInt());
			tanks[i].setCol(fileScanner.nextInt());
			tanks[i].setDirection(fileScanner.next().charAt(0));
			tanks[i].setLoser(fileScanner.nextBoolean());
		}	
			int tankNumber ;
			while (fileScanner.hasNext())
			{
				tankNumber = fileScanner.nextInt();
				tanks[tankNumber].bullet	= new Bullet(tanks[tankNumber], game);
				tanks[tankNumber].bullet.setRow(fileScanner.nextInt());
				tanks[tankNumber].bullet.setCol(fileScanner.nextInt());
				tanks[tankNumber].bullet.setDir(fileScanner.next().charAt(0));
				
			
			}
		
		}
		
		
		
	
	public Map LoadMap(String str)
	{
		Map loadedMap = null;
		Scanner scanner = new Scanner(str);
		
		int rows = Integer.parseInt(scanner.next());
		int cols = Integer.parseInt(scanner.next());
		
		loadedMap = new Map(cols, rows);
		char ch;
		String s;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				s =  scanner.next();
				ch = s.charAt(0);
				loadedMap.edit(j, i, creatBlock(i, j, ch));
			}
		}
		
		return loadedMap;
	}
	
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
	 
	 
	 
	 
	 
}


