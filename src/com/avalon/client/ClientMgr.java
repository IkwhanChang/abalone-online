package com.avalon.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.avalon.packet.CPacket;

public class ClientMgr {

   Socket nSocket;
   
   PrintWriter streamOut;
   BufferedReader streamIn;
   
   ObjectOutputStream ToServer;
   ObjectInputStream FromServer;
   
   
   
   public ClientMgr(String ServerIp, int port)
   {
	   nSocket=null;
	   
	   
	   
	   connect(ServerIp, port);
   }
   public void SendMessage(CPacket pkt)
   {
	   try{
		   if(pkt == null)	return;
		   
		   ToServer.writeObject(pkt);
		   ToServer.flush();
		   //streamOut.println(msg);
	   }catch(IOException e)
	   {
		   System.out.println("SendMessage:"+e);
	   }
	   
   }
   
   public CPacket GetMessage() throws IOException, ClassNotFoundException
   {
	   
	  
	   CPacket tPacket = (CPacket)FromServer.readObject();
	   //FromServer.close();
	   return tPacket;
   }
   public static String byteArrayToHexString(byte b[]) {
       StringBuffer s = new StringBuffer();
 
       for (int j = 0; j < b.length; j++) {
           s.append(Integer.toHexString((int)((b[j]>>4)&0x0f)));
           s.append(Integer.toHexString((int)(b[j]&0x0f)));
       }
       
       return new String(s);
   }
   
   public void connect( String ServerIp, int port) {
      try {
         nSocket = new Socket(ServerIp, port);  
         if(nSocket != null)	System.out.println("서버 연결 성공!");
         
         try {
  		   FromServer = new ObjectInputStream(nSocket.getInputStream());
  		   ToServer = new ObjectOutputStream(nSocket.getOutputStream());
  		
  	} catch (IOException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
         
      } catch(UnknownHostException e) {
         System.out.println(e);
         return;
      } catch(IOException e) {
         System.out.println(e);
         return;
      }
   }

   public void disconnect() {
      try {
    	  FromServer.close();
    	  ToServer.close();
         
         nSocket.close();
      } catch(IOException e) {
         System.out.println(e);
      }
   }
}
