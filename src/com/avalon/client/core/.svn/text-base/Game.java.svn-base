package com.avalon.client.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends Canvas {
	
	private BufferStrategy strategy;
	private boolean gameRunning = true;
	
	private Entity background;
	
	private ArrayList entities = new ArrayList();

	public Game() {
		JFrame container = new JFrame("아발론 온라인");
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		initEntities();
	}
	
	private void initEntities() {
		background = new BackgroundEntity(this, "images/game/background.png",0,0);
		entities.add(background);
	}
	
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		while(gameRunning) {
			
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			
			for (int i=0;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				
				entity.draw(g);
			}
			
			g.dispose();
			
			
			strategy.show();
			
			try{
				Thread.sleep(10);
			}catch(Exception e){}
		}
	}
	
}
