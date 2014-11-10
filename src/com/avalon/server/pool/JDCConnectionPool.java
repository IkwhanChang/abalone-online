package com.avalon.server.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

class ConnectionReaper extends Thread {

	private JDCConnectionPool pool;
	private final long delay = 300000; //5��

	ConnectionReaper(JDCConnectionPool pool) {
		this.pool = pool;
	}

	public void run() {
		while (true) {
			try {
				sleep(delay);
			} catch (InterruptedException e) {
			}
			pool.reapConnections();
		}
	}
}

public class JDCConnectionPool {

	private Vector connections;
	private String url, user, password;
	final private long timeout = 60000; //1��
	private ConnectionReaper reaper;
	final private int poolsize = 10;

	public JDCConnectionPool(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		connections = new Vector(poolsize);
		reaper = new ConnectionReaper(this);
		reaper.start();
	}

	public synchronized void reapConnections() {

		long stale = System.currentTimeMillis() - timeout;
		Enumeration connlist = connections.elements();

		while ((connlist != null) && (connlist.hasMoreElements()) && connections.size()>poolsize) {
			JDCConnection conn = (JDCConnection) connlist.nextElement();
			
			if ((stale > conn.getLastUse())
				|| (conn.inUse())
				&& (!conn.validate())) {
				try {
					removeConnection(conn);
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized void closeConnections() {

		Enumeration connlist = connections.elements();

		while ((connlist != null) && (connlist.hasMoreElements())) {
			JDCConnection conn = (JDCConnection) connlist.nextElement();
			try {
				removeConnection(conn);
			} catch (SQLException e) {
			}
		}
	}

	private synchronized void removeConnection(JDCConnection conn)throws SQLException{
		conn.realClose();
		connections.removeElement(conn);
		System.out.println("conn(remove) " + connections.size() + "inPool:" + new java.util.Date());
	}

//	private synchronized void removeConnection(JDCConnection conn) {
//		connections.removeElement(conn);
//	}

	public synchronized Connection getConnection() throws SQLException {

		JDCConnection c;
		for (int i = 0; i < connections.size(); i++) {
			c = (JDCConnection) connections.elementAt(i);
			if (c.lease()) {
				System.out.println("conn(reuse) " + connections.size() + "inPool:" + new java.util.Date());
				return c;
			}
		}

		Connection conn = DriverManager.getConnection(url, user, password);
		c = new JDCConnection(conn, this);
		c.lease();
		connections.addElement(c);
		System.out.println("conn(add) " + connections.size() + "inPool:" + new java.util.Date());
		
		return c;
	}

	public synchronized void returnConnection(JDCConnection conn) {
		conn.expireLease();
	}
}
