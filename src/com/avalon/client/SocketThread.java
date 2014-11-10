package com.avalon.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.avalon.client.entry.MessageEntry;
import com.avalon.client.entry.RoomEntry;
import com.avalon.client.entry.UserEntry;
import com.avalon.client.social.FBManager;
import com.avalon.packet.CPacket;
import com.avalon.util.GameMode;
import com.avalon.util.ImageUtil;

public class SocketThread extends Thread {
	// 게임 모드
		static GameMode  MODE = new GameMode();
		
		
		// 내부에서 사용하는 클라이언트 메니저 및 스래드(자기자신)
		
		static UIThread cthread;
		static SocketThread sthread;
		private Thread theThread = new Thread(this);
		public GameMain mClient;

		public ClientMgr cMgr;
		 
		public static String 	ServerIP = "123.143.154.42";
		public static int		port = 3333;
		
		// 기타 사용되는 변수들
		static final ImageUtil util = new ImageUtil();
		static CPacket pkt;
		
		public SocketThread(GameMain mainClient){
			cMgr = new ClientMgr(ServerIP, port);
			this.sthread = this;	   
			mClient = mainClient;
		}
		
		public void setCThread(UIThread socket) {
			this.cthread = socket;
		}
		
		public void run() {
			  
			while(true){
				System.out.println("Receiving..");
				Receive();
				
					 try {
						this.sleep(1000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			 
				
					//LoginDlg.repaint();  
			}
		}
		
		//  서버 통신
		 public void Receive() {
			 //cMgr.GetMessage();
			 try {
				 if((pkt = cMgr.GetMessage()) != null ) {
					 if("RECV".equals(pkt.getTask_type())){
						 String task = pkt.getTask_title();
						 
						 if("LOGIN".equals(task)){
							 UserEntry tuser = new UserEntry();
							 tuser.setFromJSON2(pkt.getMessage());
							 
								 System.out.println(tuser.getName()+"  유저가 접속함.");
								 cthread.addUser(tuser);
							 
							 //mClient.getcThread()
						 }else if("USERS".equals(task)){
							 ArrayList<String> temp = pkt.getOmessage();
							 ArrayList<UserEntry> users = new ArrayList<UserEntry>();
							 
							 for(String t : temp) {
								 UserEntry tu = new UserEntry();
								 tu.setFromJSON2(t);
								 users.add(tu);
							 }
							 
							 cthread.setFriends(users);
							 cthread.updateUsers();
						 }else if("MAKEROOM".equals(task)){
							 RoomEntry room = new RoomEntry(cthread.getMe());
							 room.setFromJSON(pkt.getMessage());

							 cthread.roFrame.setRoomInfo(room);
							 cthread.setGamemode(MODE.ROOM);
							 cthread.setCwindow(true);
						 }else if("ENTERROOM".equals(task)){
							 RoomEntry room = new RoomEntry();
							 room.setFromJSON(pkt.getMessage());
							 
							 if(!room.getOwner().equals(cthread.me.getId())){
								 cthread.roFrame.setRoomInfo(room);
							 }
							 cthread.enemy = room.getEnemy();
							 cthread.roFrame.setEnemy(room.getEnemy());
							 
							 cthread.setGamemode(MODE.ROOM);
							 cthread.setCwindow(true);
						 }else if("ROOMS".equals(task)){
							 ArrayList<String> temp = pkt.getOmessage();
							 ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
							 
							 for(String t : temp) {
								 RoomEntry tr = new RoomEntry();
								 tr.setFromJSON(t);
								 rooms.add(tr);
							 }
							 
							 cthread.Rooms = rooms;
							 cthread.updateRooms();
						 }else if("DELROOM".equals(task)){
							 ArrayList<String> temp = pkt.getOmessage();
							 ArrayList<UserEntry> users = new ArrayList<UserEntry>();
							 
							 for(String t : temp) {
								 UserEntry tu = new UserEntry();
								 tu.setFromJSON2(t);
								 users.add(tu);
							 }
							 
							 cthread.Friends = users;
							 cthread.updateUsers();
							 
					         cthread.setCwindow(true);
						 }else if("DELUSER".equals(task)){
							 UserEntry tuser = new UserEntry();
							 tuser.setFromJSON2(pkt.getMessage());
							 for(UserEntry user : cthread.Friends){
								 if(user.getId() == tuser.getId()){
									 cthread.Friends.remove(tuser);
									 break;
								 }
							 }
							 
							 if(tuser.getId().equals(cthread.me.getId())){
								 System.exit(0);
							 }
							 
						 }
						 else if("CHAT".equals(task)){
							 MessageEntry tmsg = new MessageEntry();
							 tmsg.setFromJSON(pkt.getMessage());
							 cthread.roFrame.setMessage(tmsg);
						 }
					 }
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(ClassNotFoundException ee) {
                 ee.printStackTrace();
             }
			
		 }
	
}
