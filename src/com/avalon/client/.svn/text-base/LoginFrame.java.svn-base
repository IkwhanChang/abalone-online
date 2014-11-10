package com.avalon.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.avalon.client.social.FBManager;
import com.avalon.client.social.GetAccessToken;
import com.avalon.packet.CPacket;
import com.avalon.util.FadeInAndOut;
import com.avalon.util.GameMode;
import com.avalon.util.ImageUtil;

public class LoginFrame extends JFrame{
	
	// 게임 모드
	static GameMode  MODE = new GameMode();
	
	private static final long serialVersionUID = -7166298966233420883L;
	static ClientMgr cMgr;
	
	private ArrayList<ImageInfo> images = new ArrayList<ImageInfo>();
	static final ImageUtil util = new ImageUtil();
	
	private JFrame fbFrame;
	static GetAccessToken fbLogin;
	
	UIThread thread;
	
	// 패널들
	LoginPanel loPanel;
	
	LoginFrame(ClientMgr tMgr,UIThread thr) {
		this.thread = thr;
		cMgr = tMgr;
		
		setTitle("아발론 온라인 - 로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,600);
		pack();
		setBounds(0,0,1024,600);
		setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize =  getSize();
		if(frameSize.height > monitorSize.height){frameSize.height = monitorSize.height;}
		if(frameSize.width > monitorSize.width) {frameSize.width = monitorSize.width;}
		setLocation((monitorSize.width - frameSize.width)/2, (monitorSize.height - frameSize.height)/2);
		
		loPanel = new  LoginPanel(cMgr,thread);
		this.setLayout(new BorderLayout());
		add(loPanel);
		repaint();
		setVisible(false);
		
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame temp = (JFrame)e.getSource();
				temp.setVisible(false);
			}
		});
		MouseListener ml = new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				// 강제 로그인. 
				//thread.setACCESS_TOKEN("AAAGRZBeVzhokBACtR7SfdqwtNeTnTU2EEJEuaKkurrFm68DXXpAWYuGg6K7IuOqPv4rA2kYWRHjBxaAnpKyXhUc94YsS5QE0gyFqZBygZDZD");
		        //thread.setGamemode(MODE.LOBBY);
		        //public CPacket(String token, String task_type,String task_title,String message) {
		       
				//  강제 로그인 되게 임시로 막아둠.
				/*
				if(fbFrame == null) {
					SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                    	fbFrame = new JFrame("FACEBOOK LOGIN");
	    					fbLogin = new GetAccessToken(fbFrame,thread);
	    					
	    					fbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    			        fbFrame.getContentPane().add(fbLogin, BorderLayout.CENTER);
	    			        fbFrame.setSize(800, 600);
	    			        fbFrame.setLocationByPlatform(true);
	    			        fbFrame.setVisible(true);
	                    }
	                });
					
					System.out.println("clicked");
				}
				*/
				
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
	/*
	public void changePanel(String bPanel, String nPanel) {
		if(bPanel.equals("LOGIN") && nPanel.equals("LOBBY")){
			this.remove(loPanel);
			if(lbPanel == null)	{
				lbPanel = new LobbyFrame(cMgr,thread);
				System.out.println("Change");
			}
			
			setVisible(false);
			this.add(lbPanel);
			thread.setCwindow(false);
			
		}
	}
	*/
	
}

