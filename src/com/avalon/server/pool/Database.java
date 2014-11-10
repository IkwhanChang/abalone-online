package com.avalon.server.pool;

import java.sql.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class Database
{
	Connection con;
	PreparedStatement pstmt;
	PreparedStatement pstmt0;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;
	PreparedStatement pstmt3;
	public Database()
	{
		try{
			//Context initContext = new InitialContext();
			//Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//DataSource ds = (DataSource)envContext.lookup("jdbc/mysql/hanbul");`
			//con = ds.getConnection();
			con = PoolManager.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void dbCommitFalse(){
		try{
			con.setAutoCommit(false);
		}catch(Exception e){
			System.out.println(""+e+" error");
		}
		
	}
	public void dbCommitTrue(){
		try{
			con.commit();
			con.setAutoCommit(true);
		}catch(Exception e){
			System.out.println(""+e+" error");
		}
		
	}
	public void dbRollback(){
		try{
			con.rollback();
		}catch(Exception e){
			System.out.println(""+e+" error");
		}
		
	}
	public int update(String sql)
	{
		try{
			if(pstmt != null){ pstmt.close();}
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	public int delete(String sql)
	{
		try{
			if(pstmt != null){ pstmt.close();}
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public int insert(String sql)
	{
		try{
			if(pstmt != null){ pstmt.close();}
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public int create(String sql)
	{
		try{
			if(pstmt != null){ pstmt.close();}
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 100;
		}
	}

	public ResultSet select(String sql)
	{
		try{
			if(pstmt0 != null){ pstmt0.close();}
			pstmt0 = con.prepareStatement(sql);
			return pstmt0.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet select1(String sql)
	{
		try{
			if(pstmt1 != null){ pstmt1.close();}
			pstmt1 = con.prepareStatement(sql);
			return pstmt1.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet select2(String sql)
	{
		try{
			if(pstmt2 != null){ pstmt2.close();}
			pstmt2 = con.prepareStatement(sql);
			return pstmt2.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet select3(String sql)
	{
		try{
			if(pstmt3 != null){ pstmt3.close();}
			pstmt3 = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return pstmt3.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void stmtClose(){
		try{ 
			if(pstmt != null){ pstmt.close(); }
		}catch(Exception e){
		   System.out.println(""+e+" error");
		}
	}
	
	public PreparedStatement setPstmt(String sql){
		try{
			
			return con.prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public int execPstmt(PreparedStatement client_pstmt){
		try{
			return client_pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	public ResultSet execSelect(PreparedStatement client_pstmt){
		try{
			return client_pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet execToRs(PreparedStatement client_pstmt){
		try{
			return client_pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public void closeAll(){
		try{
			if(pstmt != null ){ pstmt.close();}
			if(pstmt1 != null){ pstmt1.close();}
			if(pstmt2 != null){ pstmt2.close();}
			if(pstmt3 != null){ pstmt3.close();}
			if(con !=  null){ con.close(); }
		}catch(Exception e){
			System.out.println("pool close error");
		}finally{
			con =  null;
		}
	}

	public Connection getCon(){
		return con;
	}
}

