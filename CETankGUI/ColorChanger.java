package CETankGUI;

import java.awt.image.BufferedImage;


public class ColorChanger {

	MainFrame mainFrame;
	
	public ColorChanger(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		changeColorU();
		changeColorR();
		changeColorD();
		changeColorL();
		
	}
	private void changeColorU()
	{
		BufferedImage image = mainFrame.game.tanks[2].imageU;
		int RGB,RGB2 , R,G,B,a;
		for (int i = 0; i < mainFrame.game.tanks[2].imageU.getWidth(); i++) 
			for (int j = 0; j < mainFrame.game.tanks[2].imageU.getHeight(); j++) {
				RGB = image.getRGB(i, j);
				a = (RGB >>> 24) & 255;
				R = (RGB >>> 16) & 255;
		        G = (RGB >>> 8) & 255;
		        B = RGB & 255;
		        
		        RGB = (a<<24) | (B << 16) | (G<< 8) | R;		        
		        image.setRGB(i, j, RGB);
				
				
			}
		try{
			image = mainFrame.game.tanks[3].imageU;
			for (int i = 0; i < mainFrame.game.tanks[3].imageU.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[3].imageU.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        
			        RGB = (a<<24) | (B << 16) | (R<< 8) | G ;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			image = mainFrame.game.tanks[4].imageU;
			for (int i = 0; i < mainFrame.game.tanks[4].imageU.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[4].imageU.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        
			        R = 3/4 * R ;
			        RGB = (a<<24) | (G << 16) | (R<< 8) | B;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	
	
	private void changeColorR()
	{
		BufferedImage image = mainFrame.game.tanks[2].imageR;
		int RGB,RGB2 , R,G,B,a;
		for (int i = 0; i < mainFrame.game.tanks[2].imageR.getWidth(); i++) 
			for (int j = 0; j < mainFrame.game.tanks[2].imageR.getHeight(); j++) {
				RGB = image.getRGB(i, j);
				a = (RGB >>> 24) & 255;
				R = (RGB >>> 16) & 255;
		        G = (RGB >>> 8) & 255;
		        B = RGB & 255;
		        
		        RGB = (a<<24) | (B << 16) | (G<< 8) | R;		        
		        image.setRGB(i, j, RGB);
				
				
			}
		try{
			image = mainFrame.game.tanks[3].imageR;
			for (int i = 0; i < mainFrame.game.tanks[3].imageR.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[3].imageR.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        
			        RGB = (a<<24) | (B << 16) | (R<< 8) | G ;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			image = mainFrame.game.tanks[4].imageR;
			for (int i = 0; i < mainFrame.game.tanks[4].imageR.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[4].imageR.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        R = 3/4 * R ;
			        RGB = (a<<24) | (G << 16) | (R<< 8) | B;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	
	
	private void changeColorD()
	{
		BufferedImage image = mainFrame.game.tanks[2].imageD;
		int RGB,RGB2 , R,G,B,a;
		for (int i = 0; i < mainFrame.game.tanks[2].imageD.getWidth(); i++) 
			for (int j = 0; j < mainFrame.game.tanks[2].imageD.getHeight(); j++) {
				RGB = image.getRGB(i, j);
				a = (RGB >>> 24) & 255;
				R = (RGB >>> 16) & 255;
		        G = (RGB >>> 8) & 255;
		        B = RGB & 255;
		        
		        RGB = (a<<24) | (B << 16) | (G<< 8) | R;		        
		        image.setRGB(i, j, RGB);
				
				
			}
		try{
			image = mainFrame.game.tanks[3].imageD;
			for (int i = 0; i < mainFrame.game.tanks[3].imageD.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[3].imageD.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        
			        RGB = (a<<24) | (B << 16) | (R<< 8) | G ;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			image = mainFrame.game.tanks[4].imageD;
			for (int i = 0; i < mainFrame.game.tanks[4].imageD.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[4].imageD.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        R = 3/4 * R ;
			        RGB = (a<<24) | (G << 16) | (R<< 8) | B;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	
	
	
	
	private void changeColorL()
	{
		BufferedImage image = mainFrame.game.tanks[2].imageL;
		int RGB,RGB2 , R,G,B,a;
		for (int i = 0; i < mainFrame.game.tanks[2].imageL.getWidth(); i++) 
			for (int j = 0; j < mainFrame.game.tanks[2].imageL.getHeight(); j++) {
				RGB = image.getRGB(i, j);
				a = (RGB >>> 24) & 255;
				R = (RGB >>> 16) & 255;
		        G = (RGB >>> 8) & 255;
		        B = RGB & 255;
		        
		        RGB = (a<<24) | (B << 16) | (G<< 8) | R;		        
		        image.setRGB(i, j, RGB);
				
				
			}
		try{
			image = mainFrame.game.tanks[3].imageL;
			for (int i = 0; i < mainFrame.game.tanks[3].imageL.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[3].imageL.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        
			        RGB = (a<<24) | (B << 16) | (R<< 8) | G ;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			image = mainFrame.game.tanks[4].imageL;
			for (int i = 0; i < mainFrame.game.tanks[4].imageL.getWidth(); i++) 
				for (int j = 0; j < mainFrame.game.tanks[4].imageL.getHeight(); j++) {
					RGB = image.getRGB(i, j);
					a = (RGB >>> 24) & 255;
					R = (RGB >>> 16) & 255;
			        G = (RGB >>> 8) & 255;
			        B = RGB & 255;
			        R = 3/4 * R ;
			        RGB = (a<<24) | (G << 16) | (R<< 8) | B;		        
			        image.setRGB(i, j, RGB);
					
					
				}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}







}
