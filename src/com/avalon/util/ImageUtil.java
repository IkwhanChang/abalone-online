package com.avalon.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class ImageUtil {
	public Image getImage(String ref) {
		
		BufferedImage sourceImage = null;
		
		try {
			
			URL url = this.getClass().getClassLoader().getResource(ref);
			
			if(url == null) {
				fail("Can't find ref : "+ref);
			}
			sourceImage = ImageIO.read(url);
		}catch(IOException e) {
			fail("Failed to load :" + ref);
		}
		
		
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
		
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		
		return image;
	}

	public Image getImage(String ref,int width, int height) {
		
		BufferedImage sourceImage = null;
		
		try {
			
			 URL url = new URL(ref);
			
			if(url == null) {
				fail("Can't find ref : "+ref);
			}
			sourceImage = ImageIO.read(url);
		}catch(IOException e) {
			fail("Failed to load :" + ref);
		}
		
		float w = new Float(width) ;
		  float h = new Float(height) ;
		  
		  if ( w <= 0 && h <= 0 ) {
		   w = sourceImage.getWidth();
		   h = sourceImage.getHeight();
		  } else if ( w <= 0 ) {
		   w = sourceImage.getWidth() * ( h / sourceImage.getHeight() ); 
		  } else if ( h <= 0 ) {
		   h = sourceImage.getHeight() * ( w / sourceImage.getWidth() ); 
		  }
		  
		  int wi = (int) w;
		  int he = (int) h;
		  
		  BufferedImage resizedImage = new BufferedImage(wi,he,BufferedImage.TYPE_INT_RGB);
		 
		  resizedImage.getGraphics().drawImage(
				  sourceImage.getScaledInstance(wi,he,Image.SCALE_AREA_AVERAGING),
		    0,0,wi,he,null
		  );
		  
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(resizedImage.getWidth(), resizedImage.getHeight(), Transparency.BITMASK);
		
		image.getGraphics().drawImage(resizedImage, 0, 0, null);
		
		return image;
	}
	
	private void fail(String string) {
		System.err.println(string);
		//System.exit(0);		
	}
	
	public static Image resizeImage(BufferedImage image, int width, int height) {
		BufferedImage sourceImage = null;
		
		  float w = new Float(width) ;
		  float h = new Float(height) ;
		  
		  if ( w <= 0 && h <= 0 ) {
		   w = image.getWidth();
		   h = image.getHeight();
		  } else if ( w <= 0 ) {
		   w = image.getWidth() * ( h / image.getHeight() ); 
		  } else if ( h <= 0 ) {
		   h = image.getHeight() * ( w / image.getWidth() ); 
		  }
		  
		  int wi = (int) w;
		  int he = (int) h;
		  
		  BufferedImage resizedImage = new BufferedImage(wi,he,BufferedImage.TYPE_INT_RGB);
		 
		  resizedImage.getGraphics().drawImage(
		    image.getScaledInstance(wi,he,Image.SCALE_AREA_AVERAGING),
		    0,0,wi,he,null
		  );
		  
		  GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
			Image retImage = gc.createCompatibleImage(resizedImage.getWidth(), resizedImage.getHeight(), Transparency.BITMASK);
			
			image.getGraphics().drawImage(resizedImage, 0, 0, null);
		 
		  return retImage;
		 }
}
