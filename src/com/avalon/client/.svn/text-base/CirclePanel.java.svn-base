package com.avalon.client;
import javax.swing.*;

import com.avalon.client.entry.StoneEntry;

import java.awt.*;

class CirclePanel extends JPanel{
	
	private Image image;
	public StoneEntry[] stones = new StoneEntry[100];
	public CirclePanel() {
		
		image = new javax.swing.ImageIcon(getClass().getResource("/images/game/game_background.png")).getImage();
	
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public StoneEntry[] getStones() {
		return stones;
	}

	public void setStones(StoneEntry[] stones) {
		this.stones = stones;
	}
	public Color getColor(StoneEntry stone) {
		if(stone == null)	return Color.gray;
		if("BLACK".equals(stone.getColor_type())){
			return Color.black;
		}
		if("WHTE".equals(stone.getColor_type())){
			return Color.white;
		}
		if("GRAY".equals(stone.getColor_type())){
			return Color.gray;
		}
		
		return null;
	}
	
	public void changeColor(int i) {
		stones[i].setColor_type("BLACK");
		this.repaint();
	}

	public void paint(Graphics g) {
		g.drawImage(image,50,0, 513,410,this);
		
		int i=0;
		int k = 0;
		for(i=0;i<5;i++) {
			int xx = 102+100+40*i;
			int yy = 49;
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원 색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<6;i++) {
			int xx = 102+80+40*i;
			int yy = 84;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<7;i++) {
			int xx = 102+60+40*i;
			int yy = 119;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<8;i++) {
			int xx = 102+40+40*i;
			int yy = 154;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<9;i++) {
			
			int xx = 102+20+40*i;
			int yy = 189;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<8;i++) {
			int xx = 102+40+40*i;
			int yy = 224;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<7;i++) {
			int xx = 102+60+40*i;
			int yy = 259;
			
		  g.setColor(Color.BLACK); //테두리 색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<6;i++) {
			int xx = 102+80+40*i;
			int yy = 294;
			
		  g.setColor(Color.BLACK); //테두리색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		
		for(i=0;i<5;i++) {
			int xx = 102+100+40*i;
			int yy = 329;
			
		  g.setColor(Color.BLACK); //테두리 색상
		  g.drawOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  g.setColor(getColor(stones[k])); //원색상
		  g.fillOval(xx,yy,30,30); //원 그리기 x1,x2 , x3,x4 x1,x2 원위치 x3,x4 원좌우크기
		  
		  if(stones[k] == null) stones[k] = new StoneEntry(xx,yy,30,30,"GRAY");
		  
		  k++;
		}
		this.setOpaque(false);
		super.paint(g);
	}
}



