package com.avalon.server;

import java.sql.*;
import javax.swing.*;


public class DBAccess
{
 //public ResultSet resultSet; //질의문의 결과를 처리하는 클래스 
 public ResultSetMetaData metaData; //메타데이터
 public int numberOfRows; //테이블의 행
   
 public Connection db=null; 
 public Statement stmt=null;
 public PreparedStatement pstmt = null;
 String queryState=""; 
 //String sql = "SELECT * FROM Table1"; //테이블 명
 static final String JDBC_ODBC_DRIVER="sun.jdbc.odbc.JdbcOdbcDriver";
 static final String url="jdbc:odbc:nanidb";  //dsn_db1은 DB이름   
 DBAccess()
 {
	 /*JDBC - ODBC Bridge Driver Setting*/
	   try {
		Class.forName(JDBC_ODBC_DRIVER);
		db=DriverManager.getConnection(url,"","");
		 stmt=db.createStatement();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
 }
 
 public ResultSet GetQuery(String sql) throws SQLException
 {
	 ResultSet resultSet = null;
	 System.out.println("GetQuery:"+sql);
	 	   //SQL문 작성을 위한 Statement를 준비
	   
	   resultSet = stmt.executeQuery(sql); //query를 실행
	   
	   //Query 실행값을 저장할 버퍼*/
	   //StringBuffer results = new StringBuffer();
	   //ResultSetMetaData metaData = resultSet.getMetaData(); //MetaData를 얻는다.
	   
	   return resultSet;
 }
 public void ExeQuery(String sql) throws SQLException
 {
	 System.out.println(sql);
	 stmt.executeUpdate(sql);
 }
 public boolean LoginCheck(String id, String pw, String ip)
 {
	 ResultSet rs = null;
	 String sql = "SELECT * FROM userDB where id='"+id+"'"; // 테이블 명
	 String sql2 = "UPDATE userDB SET onoff=1 where id='"+id+"'";
	 
  try
  {
   
   rs = GetQuery(sql);
   
   while(rs.next())
   {
	   //System.out.println(resultSet.getObject(3).toString());
	   if(pw.equals(rs.getObject(3).toString()))
	   {
	   	ExeQuery(sql2);
	   return true;   
	   }
	   else
	   {
		   
		   return false;
	   }
   }
  } //try의 끝
  
  catch(SQLException sqlException)
  { 
       JOptionPane.showMessageDialog( null,sqlException.getMessage(), "DataBase ERROR", 
             JOptionPane.ERROR_MESSAGE );
       System.exit(1);//비정상적으로 종료시킨다.
  }
  
  finally{
   /*연결된 데이타베이스를 닫는다.*/
   try{
    stmt.close();
    db.close();
   }
   catch(SQLException sqlException)
   {
	   JOptionPane.showMessageDialog(null,sqlException.getMessage(), "DataBase Error",JOptionPane.ERROR_MESSAGE);
	   System.exit(1);   
   }
  }
return false;
}
  
  public void LogOff(String id)
  {
 	 String sql2 = "UPDATE userDB SET onoff=0 where id='"+id+"'";
 	 
   try
   {
 		   	ExeQuery(sql2);
    }
   catch(SQLException sqlException)
   { 
        JOptionPane.showMessageDialog( null,sqlException.getMessage(), "DataBase ERROR", 
              JOptionPane.ERROR_MESSAGE );
        System.exit(1);//비정상적으로 종료시킨다.
   }
   
   finally{
    /*연결된 데이타베이스를 닫는다.*/
    try{
     stmt.close();
     db.close();
    }
    catch(SQLException sqlException){
     JOptionPane.showMessageDialog(null,
         sqlException.getMessage(), "DataBase Error",
         JOptionPane.ERROR_MESSAGE);
     System.exit(1);
    }
   }
 }
 /*main Function*/
 
 
}




