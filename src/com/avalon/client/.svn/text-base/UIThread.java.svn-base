package com.avalon.client;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import com.avalon.client.entry.RoomEntry;
import com.avalon.client.entry.UserEntry;
import com.avalon.client.social.FBManager;
import com.avalon.client.social.GetAccessToken;
import com.avalon.packet.CPacket;
import com.avalon.util.FadeInAndOut;
import com.avalon.util.GameMode;
import com.avalon.util.ImageUtil;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;

public class UIThread extends Thread {

	// 게임 모드
	static GameMode  MODE = new GameMode();
	
	//  서버 주소
	public String 	ServerIP = "123.143.154.42";
	public int		port = 3333;
	
	// 내부에서 사용하는 클라이언트 메니저 및 스래드(자기자신)
	static ClientMgr cMgr;
	static UIThread cthread;
	static SocketThread sthread;
	private Thread theThread = new Thread(this);
	public GameMain mClient;
	public ArrayList<UserEntry> Friends;
	public ArrayList<RoomEntry> Rooms;
	 
	// 창들 초기화
	public LoginFrame loFrame;
	public LobbyFrame lbFrame;
	public RoomFrame roFrame;
	public mroomFrame mrFrame;
	public RuleFrame rlFrame;
	
	JFrame fbFrame = null;
	
	// 프로그램 상태
	static int   GAME_MODE;
	static int 	LG_MODE;
	static boolean cwindow;
	
	// 페이스북 연동에 필요한 토큰 및 객체
	private static String ACCESS_TOKEN ;
	static FBManager fbManager; // My Facebook Manager;
	static FBManager fbManager2; // Enemy's Facebook Manager;
	
	// 사용자 정보
	static UserEntry me; //  내 객체
	static UserEntry enemy; // 상대방 객체
	static ArrayList<UserEntry> friends = new ArrayList<UserEntry>(); //  친구 목록. 걍 서버에 있는 사람 죄다 있음.
	
	// 기타 사용되는 변수들
	static final ImageUtil util = new ImageUtil();
	static CPacket pkt;
	
	UIThread(GameMain mainClient,SocketThread socket) {
		
		   this.cthread = this;
		   this.sthread = socket;
		   
			this.cMgr = sthread.cMgr;

		   loFrame =new LoginFrame(cMgr,this);
		   lbFrame =new LobbyFrame(cMgr,this);
		   roFrame = new RoomFrame(cMgr, this);
		   mrFrame = new mroomFrame(cMgr, this);
		   rlFrame = new RuleFrame(cMgr, this);
		   
		   Friends = new ArrayList<UserEntry>();

		   GAME_MODE = MODE.LOGIN;
		   LG_MODE = MODE.LOGIN;
		   cwindow = true;
		   
		   mClient = mainClient;
		   
	   }
	
		public void run() {
		  
			while(true){
				 
				Dialog();
				if(LG_MODE == MODE.LOGIN && fbManager != null) {
					me = fbManager.matchUser();
					
					if(me != null){
						
						if(me.fbuser != null){
							me.setName(me.fbuser.getName());
							me.setId(me.fbuser.getId());
							lbFrame.setUser(me);
						//  서버에 로그인 통지
					        CPacket tpkt = new CPacket();
					        tpkt.setToken(ACCESS_TOKEN);
					        tpkt.setId(me.getId());
					        tpkt.setTask_type("SEND");
					        tpkt.setTask_title("LOGIN");
					        tpkt.setMessage(me.getJSON(fbManager));
					        cMgr.SendMessage(tpkt);
					        LG_MODE = MODE.LOBBY;
					        setGamemode(MODE.LOBBY);
					   }
					}
				}
				try {
					 this.sleep(1000L);
				 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					loFrame.repaint();
				}
					//LoginDlg.repaint();  
			}
		}

	   //  창이 없으면 창을 만들어주는 다이얼로그.
	   public void Dialog() {
		   
		   if(cwindow != false){
			   if(GAME_MODE == MODE.LOGIN){
				   loFrame.setVisible(true);
				   cwindow = false;
				   System.out.println("Login 다이얼로그 생성 성공");
			   }else if(GAME_MODE == MODE.LOBBY) {
				   
				   roFrame.setVisible(false);
				   loFrame.setVisible(false);
		    		
				   lbFrame.setVisible(true);    		
		    		
		    		cwindow = false;
		    		System.out.println("Lobby 다이얼로그 생성 성공");
		    	}else if(GAME_MODE == MODE.ROOM) {
					   loFrame.setVisible(false);
					   lbFrame.setVisible(false);
					   mrFrame.setVisible(false);
					   
					   roFrame.setVisible(true);
			    		
			    		cwindow = false;
			    		System.out.println("Room 다이얼로그 생성 성공");
			    	}else if(GAME_MODE == MODE.MAKE_ROOM) {
			    		loFrame.setVisible(false);
			    		roFrame.setVisible(false);
			    		
			    		lbFrame.setVisible(true);
			    		mrFrame.setVisible(true);
			    		cwindow = false;
			    	}else if(GAME_MODE == MODE.RULE) {	
			    		lbFrame.setVisible(true);			    		
			    		rlFrame.setVisible(true);
			    		cwindow = false;
			    	}
		    	
	    	}
	    
	    }

	public JFrame getFbFrame() {
		return fbFrame;
	}

	public void setFbFrame(JFrame fbFrame) {
		this.fbFrame = fbFrame;
	}


	public int getGamemode() {
		return GAME_MODE;
	}

	public void setGamemode(int avalon_mode) {
		GAME_MODE = avalon_mode;
	}

	public boolean isCwindow() {
		return cwindow;
	}

	public void setCwindow(boolean cwindow) {
		this.cwindow = cwindow;
	}

	public static String getACCESS_TOKEN() {
		return ACCESS_TOKEN;
	}

	public static void setACCESS_TOKEN(String aCCESS_TOKEN) {
		ACCESS_TOKEN = aCCESS_TOKEN;
		System.out.println(ACCESS_TOKEN);
		cwindow = true;
		GAME_MODE = MODE.LOBBY;
		
		//Setting me
		fbManager = new FBManager(ACCESS_TOKEN);
		
		
		/*
		fbManager = new FBManager("AAAGRZBeVzhokBAEdF2yBk0wPk9I4PMJS9bQrhdS221b9qiMHhhU1W5Xfan8u6KGD0jH01360IWYj4nEfkQpOlf5U9TDUOrtqrLGY0JwZDZD");
		me = fbManager.matchUser();
		
		lbFrame.setUser(me);
		
		//Setting Enemy( 임시)
		 fbManager2 = new FBManager("AAAGRZBeVzhokBAFfHZBvLii5PXdimvzZBk6cirWjxJy0tzE2hIiKKTG5pw0nZC8mypqnad35Gr4UU37UIwtIVWD57ssNpu8dBIOsc1KQSAZDZD");
		 enemy = fbManager2.matchUser();
		 roFrame.setUser(me, enemy);
		 */
		
	}
	
	public void addUser(UserEntry tuser) {
		boolean flag = false;
		for(UserEntry user : Friends) {
			if(user.getId().equals(tuser.getId())){
				user = tuser;
				flag = true;
			}
		}
		if(!flag) {
			friends.add(tuser);
		}
		this.lbFrame.addUser(friends);
	}
	
	public void updateUsers() {
		this.lbFrame.addUser(friends);
	}
	

	public void updateRooms() {
		this.lbFrame.addRoom(Rooms);
	}


	public static ImageUtil getUtil() {
		return util;
	}

	public UserEntry getMe() {
		return me;
	}

	public static void setMe(UserEntry me) {
		UIThread.me = me;
	}

	public static UserEntry getEnemy() {
		return enemy;
	}

	public static void setEnemy(UserEntry enemy) {
		UIThread.enemy = enemy;
	}

	public static ArrayList<UserEntry> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<UserEntry> friends) {
		UIThread.friends = friends;
	}
	   
	   
}