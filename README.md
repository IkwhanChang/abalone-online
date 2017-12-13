# Abalone Online
Online Java version of Abalone board game

## Description
- Avalone Online (https://en.wikipedia.org/wiki/Abalone_(board_game) ) is one of a kind of board game. Our team became interested in the simple, addictive logic of the game and decided to develop it in 2D. We decided to create a multi-play board game that provides a match-up mode like regular online games such as Battle.net. We have developed the game in a genre called SNG (social network game) by combining social networking features, especially Facebook login and social functions, according to recent trends.


## Game Rule
See: https://en.wikipedia.org/wiki/Abalone_(board_game) Section #2

## Configurations
- Frontend: Java (Swing GUI)
- Backend: Java (Socket I/O)
- Database: MySQL
- Open API: Facebook Open API, DJSwingNative

## Project Features
### Facebook Integration
The biggest feature of our program is the Facebook interface. In order to integrate Facebook, you first register your Facebook app on the developer site (http://developer.facebook.com).

![Facebook OAuth](http://tungwaiyip.info/2011/img/facebook_oauth_authentication.png)
 

Above is the screen which registered our Avalon online. And the process for the whole user is done by token, which can be given token by Facebook OAuth.
 
As shown in the figure above, to connect OAuth, you have to go through the session through Facebook server. To do this, you need to use the browser directly or indirectly. To do this, you must use the HttpClient provided by Apache.
We have created a simple OAuth authentication program through JSP, as the process of using Apache HttpClient is too complex and we would rather be developing on the web rather than doing so (below file is not included in this project)

```java

// Step1.jsp

<%@ page language="java" import="java.util.*,java.sql.*" session="true" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String appKey 		= "441977459148425";
	String appSecret 	= "865a7e5d19faf56eb71f9810a71bc5ed";
	String token = request.getParameter("token");
	
	String url = "http://www.facebook.com/dialog/oauth?client_id="+
		appKey+"&redirect_uri=http://avalon.changikhwan.com:9090/fb/step2.jsp&scope=publish_stream,offline_access";
 	%>

```

However, there is no way to implement a web browser on the UI in a program developed with J2SE. All of this in Oracle is made possible by JavaFX (RIA programs like Flash and Silverlight)
The solution we found was to use Swing Native Library, created by The DJ Project, to launch the current user's default browser via the SWT (Standard Widget Toolkit).

```java

//com.avalon.client.social.GetAccessToken

public class GetAccessToken extends JPanel {
	private static JFrame upperFrame;
	private UIThread thread;
	private static String ACCESS_TOKEN = "";
	JPanel webBrowserPanel = new JPanel(new BorderLayout());
  public GetAccessToken(final JFrame frame,final UIThread tClient) {
	  
    super(new BorderLayout());
    upperFrame = frame;
    thread = tClient;
    
    //webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.navigate("http://avalon.changikhwan.com:9090/fb/step1.jsp");
    webBrowser.setStatusBarVisible(false);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    
    add(webBrowserPanel, BorderLayout.CENTER);
    // Create an additional bar allowing to show/hide the menu bar of the web browser.
     
    webBrowser.setMenuBarVisible(false);
   
       
      webBrowser.registerFunction(new WebBrowserFunction("testFunc") {   
        @Override  
        public Object invoke(JWebBrowser webBrowser, Object... args) {   
          //System.out.println(args[0].toString()); // FACEBOOK TOKEN
          ACCESS_TOKEN = args[0].toString();
          //webBrowserPanel.setVisible(false);
          System.out.println("Sdfsdfsdf");
          
          upperFrame.setVisible(false);
          upperFrame.dispose();
          UIThread.setACCESS_TOKEN(ACCESS_TOKEN);
          
          return null;
        }   
      });  
    
  }

}

```
As a result, you can use the function implemented in DJNativeSwing-SWT.jar file in JPanel as a browser through the function called JWebBrowser. In step1.jsp, you can call the Access Token from the web with the actual J2SE program as JavaScript.

![Facebook Login on the Native Browser](https://matthew.kr/wp-content/uploads/2017/12/Screen-Shot-2017-12-12-at-8.43.37-PM.png)

This access token will match user information using the Facebook library for JDK provided by restFB (www.restfb.com). The information you get from this can bring you information such as your name, email, birthday, and your friends' information.

```java

  //com.avalon.client.social.FBManager
  
	private static String ACCESS_TOKEN ;
	static FacebookClient facebookClient;

	static FacebookClient publicOnlyFacebookClient;
	
	public FBManager(String ACCESS_TOKEN){
		this.ACCESS_TOKEN = ACCESS_TOKEN;
		
		facebookClient = new DefaultFacebookClient(ACCESS_TOKEN);
		publicOnlyFacebookClient = new DefaultFacebookClient();
		
		
	}
	
	public void postWall(String post) {
		FacebookType publishMessageResponse = null;
		
		try {
			publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, 
					Parameter.with("message", post));
		} catch(FacebookOAuthException e) {
			//error occur!!
		}
	}

```

![Automatic Share the Game Result (English Trans: Congrats! You win! Kim SungJin is quit the game without say goodbye!)](https://matthew.kr/wp-content/uploads/2017/12/Screen-Shot-2017-12-12-at-8.47.04-PM.png)

### Relay Server & Packet Serialization
![Diagram of Packet Manager](https://matthew.kr/wp-content/uploads/2017/12/Screen-Shot-2017-12-12-at-8.49.00-PM.png)
We have built an echo server for real game configuration and database processing. The server keeps a Thread open for each client, serializes the packet through Serializable, receives it on a per-packet basis, and processes it through ServerMgr.

```java

// CPacket uses for packet include data that share between clients. Part of CPacket.java 

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
	private  String message; //  JSON
	
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

```

By implementing Serializable as above and configuring the server like this, the packet is serialized.

In the server configuration, ServerThread is the client thread, which inherits Thread. ServerMgr is the main server program with the Main function. Here the client is managed by ClientList and each Thread performs various actions according to the functions defined here. Clients do similar processing.

```java

// ServerMgr.java

public void run() {
	      try {
	         System.out.println("Connected ["+Socket.getInetAddress()+"]");
	         
	         ToClient = new ObjectOutputStream(Socket.getOutputStream());
	         FromClient = new ObjectInputStream(Socket.getInputStream());
	         
	         try {
				while(true) {
					pkt = (CPacket)FromClient.readObject();
					if(pkt != null){
						System.out.println("PACKET_ACCEPT");
						 if(pkt.getTask_title().equals("LOGIN")) {
                             System.out.println("LOGIN_MODE");
                             this.id = pkt.getId();
                             this.ip = pkt.getIp();
                             Server.addUser(pkt);
                             Server.getUsers();
                         }else if(pkt.getTask_title().equals("CHAT")) {
                             Server.sendMsg(pkt);
                         }else if(pkt.getTask_title().equals("MAKEROOM")) {
                             Server.makeRoom(pkt);
                             Server.getRooms();
                         }else if(pkt.getTask_title().equals("ENTERROOM")) {
                             Server.joinRoom(pkt);
                             Server.getRooms();
                         }else if(pkt.getTask_title().equals("DELROOM")) {
                             Server.delRoom(pkt);
                         }else if(pkt.getTask_title().equals("DELUSER")) {
                             Server.delUser(pkt);
                         }

					}
					
					try {
						 this.sleep(1000L);
						 System.out.println("유저수 : "+Server.users.size());
						 System.out.println("방수 : "+Server.rooms.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				    
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	         //FromClient.close();
	         //ToClient.close();
	         //Socket.close();
	      } catch(IOException e) {
	         Server.removeClient(this);
	         
	         System.out.println("["+ Socket.getInetAddress() + "]의 접속이 끊겼습니다.");
	      } 
	   }

```

When the server receives a packet, it looks at the message in the header of the packet and calls the function of the server object.

```java

//SocketThread.java

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
```

The client also processes the client's actions by branching the message based on the message sent by the server.
Actually, the classes to be sent in addition to the CPacket class are changed to JSON through the Jackson JSON library and put into the Message variable of the actual CPacket to process the transmission and reception as a String. So each of our entries has getJSON() / setJSON().

### Database
We have built a DB manager to record the user's score information. In fact, DB connects to each client thread of the server, and we solved the problem of Max Connection by allocating the pool in a thread unit by holding the pool when connecting DB through the full manager method implemented by our company.
  
```java
// PoolManager.java
public static Connection getConnection() throws Exception{ 
         
        // Singleton
    	String userId  = "root"; 
		String userPwd = "admin"; 
		if ( jdcPool == null ) { 
			 
			//String jdbc    = "org.gjt.mm.mysql.Driver"; 
			String jdbc    = "com.mysql.jdbc.Driver"; 
			String jdbcURL = "jdbc:mysql://localhost:3306/avalon_online?characterEncoding=euckr";
			jdcPool = new JDCConnectionDriver( jdbc, jdbcURL, userId, userPwd ); 
		}//if 

		return DriverManager.getConnection("jdbc:jdc:jdcpool");
    }
}//PoolManager
```
The above is the source of our PoolManager. PoolManager is a singleton pattern that connects to Mysql with JDBC driver and processes the whole DB with a certain amount of pool.

### Swing Library
![Game Main](https://matthew.kr/wp-content/uploads/2017/12/avalon2.png)
It implements the double array list which can play game in RoomFrame part, coordinates the position of the stone in CirclePanel to ArrayList, and controls the movement of the stone through function according to the position of mouse click. When you click on the stone you want to move, the color of the stone changes to blue, and the border in the direction you can go changes to blue. The maximum number of stones that can be moved is two. If you select a square with a blue border, the stones are moved. 

Whether or not the stone is movable and coloring is implemented in CirclePanel. Plus, in CirclePanel, the repaint() function is created with the following logic to handle the stone transition in the override paint function.

## How to Run
- Underconstructuring..


## License

[MIT](LICENSE.md) © [Matthew Chang](https://www.matthewlab.com)
