package CETankGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import CETankGUI.MainFrame;
import CETankGame.Server.ServerRead;

public class Client {

	MainFrame mainFrame;
	Socket socket;
	int portNumber;
	String IP;
	InputStream inputStream ;
	OutputStream outputStream ;
	Formatter formatter ;
	Scanner sc;
	public ClientRead clientRead;
	public ClientWrite clientWrite;
	
	
	
	
	
	public Client(MainFrame mainFrame ,String IP, int portNumber ) {
	
	this.mainFrame = mainFrame;
	this.portNumber = portNumber;
	this.IP = IP ;
	
	clientWrite = new ClientWrite();
	clientRead = new ClientRead();
	
	try {
		
		
		this.socket = new Socket(IP, portNumber);
		
		
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		
		sc = new Scanner(inputStream);
		formatter = new Formatter(outputStream);
		
		
		JOptionPane.showMessageDialog(null, "Connected to Server!!!");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	public void initializeConnection()
	{
		clientWrite.sendPlayersName();
		clientRead.recievePlayersName();
		mainFrame.map = clientRead.recieveMap();
		
		
		
	}
	
	
	public class ClientRead extends Thread {
		
		public void run() {
			
			while(sc.hasNext())
			{
				int tankNum = sc.nextInt();
				String dir = sc.next();
				mainFrame.game.tanks[tankNum].move(dir.charAt(0));
				mainFrame.clientGameFrame.updateClientGameFrame();
				
			}
		}
		
		public Map recieveMap()
		{
			String temp =""  ;
			int x ,y;
			x = sc.nextInt();
			y = sc.nextInt();
			temp = temp + x +' '+ y;
			for(int i =0 ;i < y;i++)
				temp = temp + sc.nextLine()+"\n";
			
			return mainFrame.LoadMap(temp);
		}
		
		public void recievePlayersName()
		{
			mainFrame.tanks[1] = new Tank(1, sc.next());
			mainFrame.tanks[2] = new Tank(2, sc.next());
		}
			
		public void recieveTanksInitialLocation()
		{
			mainFrame.tanks[1].setRow(sc.nextInt());
			mainFrame.tanks[1].setCol(sc.nextInt());
			
			mainFrame.tanks[2].setRow(sc.nextInt());
			mainFrame.tanks[2].setCol(sc.nextInt());
			
			mainFrame.tanks[3].setRow(sc.nextInt());
			mainFrame.tanks[3].setCol(sc.nextInt());
			
			mainFrame.tanks[4].setRow(sc.nextInt());
			mainFrame.tanks[4].setCol(sc.nextInt());
			
			
		}
			
			
		}
	
	
	public class ClientWrite extends Thread
	{
		public void sendMovement(int tankNumber ,char dir)
		{
			
			
			formatter.format("%d %c \n", tankNumber,dir);
			formatter.flush();
			
			
		}
		
		public void sendPlayersName()
		{
			formatter.format("%s\n", mainFrame.tanks[3].getName());
			formatter.flush();
			formatter.format("%s\n", mainFrame.tanks[4].getName());
			formatter.flush();
		}
		
	}
	
	
}
