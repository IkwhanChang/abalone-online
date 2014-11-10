package com.avalon.server.pool; 
 
import java.sql.Connection; 
import java.sql.DriverManager; 
public class PoolManager{ 

     
    static JDCConnectionDriver jdcPool;    

    public static Connection getConnection() throws Exception{ 
         
        // Singleton
    	String userId  = "root"; 
		String userPwd = "276918"; 
		if ( jdcPool == null ) { 
			 
			//String jdbc    = "org.gjt.mm.mysql.Driver"; 
			String jdbc    = "com.mysql.jdbc.Driver"; 
			String jdbcURL = "jdbc:mysql://192.168.10.150:3306/avalon_online?characterEncoding=euckr";
			//String jdbcURL = "jdbc:mysql://localhost:3306/hanbul_intra?useUnicode=true&characterEncoding=KSC5601"; 
			
			System.out.println(jdbcURL);
			jdcPool = new JDCConnectionDriver( jdbc, jdbcURL, userId, userPwd ); 
		}//if 

		return DriverManager.getConnection("jdbc:jdc:jdcpool");
		//return DriverManager.getConnection("jdbc:mysql://localhost:3310/hanbul_intra?user=root&password=276918&characterEncoding=euckr&useUnicode=true&mysqlEncoding=euckr",userId,userPwd);
    }
}//PoolManager


/*
public class PoolManager{ 

     
    static JDCConnectionDriver jdcPool;    

    public static Connection getConnection() throws Exception{ 
         
        // �̱��� 		
		if ( jdcPool == null ) { 
			 
			String jdbc    = "com.mysql.jdbc.Driver"; 
			String jdbcURL = "jdbc:mysql://localhost:3306/hanbul_intra"; 
			String userId  = "root"; 
			String userPwd = "276918"; 
			jdcPool = new pool.JDCConnectionDriver( jdbc, jdbcURL, userId, userPwd ); 
		}//if 

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hanbul_intra?user=root&password=276918"); 
    }
}//PoolManager

*/