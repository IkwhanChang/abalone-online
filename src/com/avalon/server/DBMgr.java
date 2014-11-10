package com.avalon.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.avalon.client.entry.UserEntry;
import com.avalon.client.social.FBManager;
import com.avalon.server.pool.Database;
import com.restfb.types.User;

public class DBMgr {
	Database db;		
	ResultSet rs;
	PreparedStatement pstmt;
	StringBuffer sbq;
	
	public DBMgr() {
		db = new Database();
	}
	
	public void closeAll(){
		try{ if(db != null){ db.closeAll(); }	}catch(Exception e){}
	}
	
	public int isUser(UserEntry tuser) {
		int cnt = 0;
		try{ 
			
			sbq = new StringBuffer();
			sbq.setLength(0);

			sbq.append(" SELECT COUNT(*) AS CNT FROM ava_member WHERE id='"+tuser.getId()+"' ");

			rs = db.select(sbq.toString());
			if(rs.next()){
				cnt = rs.getInt("CNT");
			}

			
			try{ if(rs != null){ rs.close(); } }catch(Exception e){}

			return cnt;
		}catch(Exception e){
		    System.out.println(""+e+" error");
			return cnt;
		}finally{
			try{ if(rs != null){ rs.close(); } }catch(Exception e){}
		}
	}
	
	public int insertUser(UserEntry tuser) {
		try{ 
			
			db.dbCommitFalse();

			sbq = new StringBuffer();
			sbq.setLength(0);

			sbq.append(" INSERT INTO ava_member(id,token,email,name) VALUES (?,?,?,?) ");
			
			pstmt = db.setPstmt(sbq.toString());
			
			pstmt.setString(1, tuser.getId());
			pstmt.setString(2, tuser.getACCESS_KEY());
			pstmt.setString(3, tuser.getEmail());
			pstmt.setString(4, tuser.getName());
			
			
			int inst = db.execPstmt(pstmt);
			
			try{ if(pstmt != null){ pstmt.close(); } }catch(Exception e){}
			
			db.dbCommitTrue();

			return inst;
		}catch(Exception e){
			db.dbRollback();
		    System.out.println(""+e+" error");
			return -1;
		}finally{
		}
	}
	
	public int updateUserInfo(UserEntry tuser){

		try{ 
			
			db.dbCommitFalse();

			sbq = new StringBuffer();
			sbq.setLength(0);

			sbq.append(" UPDATE ava_member SET email=?,name=?,token=? WHERE id=? ");
			
			pstmt = db.setPstmt(sbq.toString());
			
			pstmt.setString(1, tuser.getEmail());
			pstmt.setString(2, tuser.getName());
			pstmt.setString(3, tuser.getACCESS_KEY());
			pstmt.setString(4, tuser.getId());

			int inst = db.execPstmt(pstmt);
			
			try{ if(pstmt != null){ pstmt.close(); } }catch(Exception e){}
			
			db.dbCommitTrue();

			return inst;
		}catch(Exception e){
			db.dbRollback();
		    System.out.println(""+e+" error");
			return -1;
		}finally{
		}
	}
	
	public ArrayList<UserEntry> getAllUser(){
		
		ArrayList<UserEntry> users = new ArrayList<UserEntry>();

		try{ 
			
			sbq = new StringBuffer();
			sbq.setLength(0);

			sbq.append(" SELECT * FROM ava_member");

			rs = db.select(sbq.toString());
			while( rs.next() ){
				UserEntry tuser = new UserEntry();
				tuser.setId(rs.getString("ID"));
				tuser.setName(rs.getString("NAME"));
				System.out.println(rs.getString("NAME"));
				tuser.setACCESS_KEY(rs.getString("TOKEN"));
				if(rs.getString("EMAIL") != null)	tuser.setEmail(rs.getString("EMAIL"));
				tuser.setWin(rs.getInt("WIN"));
				tuser.setLose(rs.getInt("LOSE"));
				users.add(tuser);
			}
			
			
			try{ if(rs != null){ rs.close(); } }catch(Exception e){}

			return users;
		}catch(Exception e){
		    System.out.println(""+e+" error");
			return users;
		}finally{
			try{ if(rs != null){ rs.close(); } }catch(Exception e){}
		}
	}
}
