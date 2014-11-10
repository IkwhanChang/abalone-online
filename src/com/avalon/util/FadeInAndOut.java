package com.avalon.util;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.avalon.client.ImageInfo;
 
public class FadeInAndOut extends JPanel implements ActionListener {
   private Image img = null;
   private static final float DELTA = 0.07f;
   private static final int 	THETA = 20;
   public static final Timer timer = new Timer(100, null);
   private float alpha = 0f;
   private int position = 0;
   private int cnt = 0;
   
   ArrayList<ImageInfo> images;
 
   public FadeInAndOut(ArrayList<ImageInfo> images) {
	   this.images = images;
	   
	   
	   this.setPreferredSize(new Dimension(1024,600));
       this.setOpaque(true);
       //this.setBackground(Color.black);
       timer.setInitialDelay(500);
       timer.addActionListener(this);
       timer.start();
       
       if(images.get(0).getType() == 2)			position = images.get(0).getWidth();
       else if(images.get(0).getType() == 3)	position = images.get(0).getHeight();
   }
    
    
   public void actionPerformed(ActionEvent e) {
	   int tType = images.get(cnt).getType();
	   if(tType == -1) {
		   alpha = 1f;
		   repaint();
	   }else if(tType == 0){ // 이미지 타입이 Fade-in 의 경우
		   if(alpha < 0.9) {
			   
		       alpha += DELTA;
		       repaint();
		   }else{
			   alpha=1f;
	           //alpha = 0;
	    	   
	           timer.stop();//.restart();
	           if(images.size()-1 > cnt){
	        	   cnt++;
	        	   alpha = 0f;
	        	   timer.start();
	           }
		   }
	   }else if(tType == 2) { // 이미지 타입이 위->원위치 이동일 경우 
		   if(position < images.get(cnt).getX()-THETA) {
			   
		       position += THETA;
		       repaint();
		   }else{
			   position=images.get(cnt).getX();
	           //alpha = 0;
	    	   
	           timer.stop();//.restart();
	           if(images.size()-1 > cnt){
	        	   cnt++;
	        	   alpha = 0f;
	        	   position = 0;
	        	   timer.start();
	           }
		   }
	   }else if(tType == 3) { // 이미지 타입이 위->원위치 이동일 경우 
		   if(position < images.get(cnt).getY()-THETA) {
			   
		       position += THETA;
		       repaint();
		   }else{
			   position=images.get(cnt).getY();
	           //alpha = 0;
	    	   
	           timer.stop();//.restart();
	           if(images.size()-1 > cnt){
	        	   cnt++;
	        	   alpha = 0f;
	        	   position = 0;
	        	   timer.start();
	           }
		   }
	   }
   }
   
   public void paintComponent(Graphics g) {
   	super.paintComponent(g);
   	ImageInfo img = images.get(cnt);
   	Graphics2D g2d = (Graphics2D) g;
   	if(cnt != 0) {
   		for(int i = 0 ; i < cnt ; i++) {
   			ImageInfo timg = images.get(i);
   			g.drawImage(timg.getImage(), timg.getX(), timg.getY(), timg.getWidth(), timg.getHeight(), null);
   		}
   	}
   	
   	if(img.getType() <= 0){
   		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
   		g.drawImage(img.getImage(), img.getX(), img.getY(), img.getWidth(), img.getHeight(), null);
   	}else if(img.getType() == 2){
   		g.drawImage(img.getImage(), position, img.getY(), img.getWidth(), img.getHeight(), null);
   	}else if(img.getType() == 3){
   		g.drawImage(img.getImage(), img.getX(), position, img.getWidth(), img.getHeight(), null);
   	}
   	
   }
 
}
