package CETankGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import CETankGUI.MainFrame;

public class Server  {

	MainFrame mainFrame;
	ServerSocket serverSocket ;
	Socket socket;
	int portNumber;
	public ServerRead serverRead;
	public ServerWrite serverWrite;
	InputStream inputStream ;
	OutputStream outputStream ;
	
	
	Scanner sc;
	Formatter formatter ;
	
	
	
	
	
	public Server(MainFrame mainFrame , int portNumber) {
	
	this.mainFrame = mainFrame;
	this.portNumber = portNumber;
	
	
	try {
		
		
		serverSocket = new ServerSocket(portNumber);
		socket = serverSocket.accept();
		
		JOptionPane.showMessageDialog(null, "client connected!!");
		System.out.println("client connected!!");
				
		inputStream = socket.getInputStream();
		
		outputStream = socket.getOutputStream();
		formatter = new Formatter(outputStream);
		
		sc = new Scanner(inputStream);
		
		
		
		serverRead = new ServerRead();
		serverWrite = new ServerWrite();
		
		
		
		
		
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void initializeConnection()
	{
		
		serverRead.recievePlayersName();
		serverWrite.sendPlayersName();
		
		
		serverRead.start();
		
	}
	
	
	
	public class ServerRead extends Thread {
		@Override
		public void run() {
			
			while(sc.hasNext())
			{
				int tankNum = sc.nextInt();
				String dir = sc.next();
				mainFrame.game.tanks[tankNum].move(dir.charAt(0));
				mainFrame.serverGameFrame.updateServerGameFrame();
				
			}
			
			
			
			
		}
		public void recievePlayersName()
		{
			mainFrame.tanks[3] = new Tank(3, sc.next());
			
			mainFrame.tanks[4] = new Tank(4, sc.next());
			
		}
	}

	public class ServerWrite extends Thread
	{
		
		public void sendMovement(int tankNumber ,char dir)
		{
			
			
			formatter.format("%d %c \n", tankNumber,dir);
			formatter.flush();
			
			
		}
		
		public void sendMap()
		{
			formatter.format("%d %d %s", mainFrame.map.getRows(),mainFrame.map.getCols(), mainFrame.map.toString());
			formatter.flush();
		}
		
		public void sendPlayersName()
		{
			formatter.format("%s\n", mainFrame.tanks[1].getName());
			formatter.flush();
			
			formatter.format("%s\n", mainFrame.tanks[2].getName());
			formatter.flush();
//			
		}
		
		public void sendTanksInitialLocation()
		{
			formatter.format("%d %d \n", mainFrame.tanks[1].getRow(),mainFrame.tanks[1].getCol());
			formatter.flush();
			
			formatter.format("%d %d \n", mainFrame.tanks[2].getRow(),mainFrame.tanks[2].getCol());
			formatter.flush();
			
			formatter.format("%d %d \n", mainFrame.tanks[3].getRow(),mainFrame.tanks[3].getCol());
			formatter.flush();
			
			formatter.format("%d %d \n", mainFrame.tanks[4].getRow(),mainFrame.tanks[4].getCol());
			formatter.flush();
			
			
			
			
		}
		
	}


}
