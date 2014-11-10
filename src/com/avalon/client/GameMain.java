package com.avalon.client;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;



public class GameMain {

	   static GameMain Client;
	   static UIThread cthread;
	   static SocketThread sthread;
	   

	   ArrayList<UIThread> ClientList;
	   ArrayList<SocketThread> SocketList;
	   
	//  서버 주소
		public static String 	ServerIP = "123.143.154.42";
		public static int		port = 4444;
		
		static ClientMgr cMgr;

	   public GameMain() {
	      ClientList = new ArrayList<UIThread>();
	      SocketList = new ArrayList<SocketThread>();
	      
	      
	   }

	   public void addClient(UIThread client,SocketThread socket) {
	      ClientList.add(client);
	      SocketList.add(socket);
	   }

	   public void removeClient(UIThread client,SocketThread socket) {
	      ClientList.remove(client);
	      SocketList.remove(socket);
	   }
	    public UIThread getcThread() {
	    	return (UIThread) ClientList.get(0);
	    }
	    
	    public SocketThread getsThread() {
	    	return (SocketThread) SocketList.get(0);
	    }

	   public static void main(String[] args) throws IOException, InterruptedException {
		   Client = new GameMain();
		   UIUtils.setPreferredLookAndFeel();
		    NativeInterface.open();
		    
		   
		    
		    sthread = new SocketThread(Client);
			cthread = new UIThread(Client,sthread);
			
			
			cthread.start();
			sthread.start();
			
			sthread.setCThread(cthread);
			
			
		    NativeInterface.runEventPump();
			
			Client.addClient(cthread,sthread);				 
	   }
	}


