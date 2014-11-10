package com.avalon.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.avalon.client.social.GetAccessToken;
import com.avalon.packet.CPacket;
import com.avalon.util.FadeInAndOut;
import com.avalon.util.GameMode;
import com.avalon.util.ImageUtil;

public class LoginPanel extends JPanel{
	// 게임 모드
	static GameMode  MODE = new GameMode();
	
	private static final long serialVersionUID = -7166298966233420883L;
	static ClientMgr cMgr;
	
	private ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
	static final ImageUtil util = new ImageUtil();
	
	private JFrame fbFrame;
	static GetAccessToken fbLogin;
	
	UIThread thread;
	
	LoginPanel(ClientMgr tMgr,UIThread thr) {
		this.thread = thr;
		cMgr = tMgr;
		
		images.add(new ImageInfo(util.getImage("images/login/background.png"),0,0,1024,600,0));
		images.add(new ImageInfo(util.getImage("images/login/logo.png"),343,286,370,136,3));
		images.add(new ImageInfo(util.getImage("images/login/facebook_button.png"),443,403,200,24,0));
		FadeInAndOut p1 = new FadeInAndOut(images);
		//JPanel p2 = new JPanel().add(new JButton("Sdfsfsd"));
		this.add(p1);
		
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1024,600));
		MouseListener ml = new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				// 강제 로그인. 
				//thread.setACCESS_TOKEN("AAAGRZBeVzhokBACtR7SfdqwtNeTnTU2EEJEuaKkurrFm68DXXpAWYuGg6K7IuOqPv4rA2kYWRHjBxaAnpKyXhUc94YsS5QE0gyFqZBygZDZD");
		        //thread.setGamemode(MODE.LOBBY);
		        //public CPacket(String token, String task_type,String task_title,String message) {
		        
				//  강제 로그인 되게 임시로 막아둠.
				
				// TODO Auto-generated method stub
				if(fbFrame == null) {
					SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                    	fbFrame = new JFrame("FACEBOOK LOGIN");
	    					fbLogin = new GetAccessToken(fbFrame,thread);
	    					
	    					fbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    			        fbFrame.getContentPane().add(fbLogin, BorderLayout.CENTER);
	    			        fbFrame.setSize(830, 370);
	    			        fbFrame.setLocationByPlatform(true);
	    			        fbFrame.setVisible(true);
	                    }
	                });
					
					System.out.println("clicked");
				}
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		
		};
		addMouseListener(ml);
	}
	
}

