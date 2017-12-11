package com.avalon.server.pool; 
 
import java.sql.Connection; 
import java.sql.DriverManager; 
public class PoolManager{ 

     
    static JDCConnectionDriver jdcPool;    

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


*/
