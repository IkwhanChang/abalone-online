package com.avalon.packet;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

public class CPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4498887824473906148L;
	private String ip; // User's IP
	private String id; // User's ID
	private String port; // User's Socket Port
	private String no; // User's Number
	private String token; // User's Facebook Token
	private String task_type; // Packet's Task. [RECV / SEND]
	private String task_title; // Task's name. [CHAT / WAIT / GAME]
	
	private String action; // TASK's Action
	private  String message; //  JSON  으로 가지고 있다.
	
	private ArrayList<String> omessage; // Object
	
	private String getLocalServerIp()
	{
	        try
	        {
	            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
	            {
	                NetworkInterface intf = en.nextElement();
	                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
	                {
	                    InetAddress inetAddress = enumIpAddr.nextElement();
	                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
	                    {
	                        return inetAddress.getHostAddress().toString();
	                    }
	                }
	            }
	        }
	        catch (SocketException ex) {}
	        return null;
	}
	
	public  CPacket() {
		this.ip = getLocalServerIp();
		this.id = "";
		this.port = "";
		this.no = "";
		this.token = "";
		this.task_title = "";
		this.task_type = "";
		
		this.action = "";
		this.message = "";
		omessage = new ArrayList<String>();
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getTask_title() {
		return task_title;
	}

	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CPacket(String token, String task_type,String task_title,String action,String message) {
		this.ip = getLocalServerIp();
		this.token = token;
		this.task_type = task_type;
		this.task_title = task_title;
		
		this.action = action;
		this.message = message;
		
		this.id = "";
		
		omessage = new ArrayList<String>();
	}
	
	public void setOmessage(String msg) {
		this.omessage.add(msg);
	}

	public ArrayList<String> getOmessage() {
		return omessage;
	}

	public void setOmessage(ArrayList<String> omessage) {
		this.omessage = omessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
