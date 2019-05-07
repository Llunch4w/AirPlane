package com.lq.sql;

import java.sql.*;

public class MysqlDriver {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    private String db_url;
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "MyNewPass";
    
    //操作句柄
    protected Connection conn = null;
    protected Statement stmt = null;
    
    
    protected void connect(String database) {
    	db_url = "jdbc:mysql://localhost:3306/" + database + "?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
      System.out.println("连接数据库...");
      try {
			conn = DriverManager.getConnection(db_url,USER,PASS);
			stmt = conn.createStatement();
      } catch (SQLException se) {
			// TODO Auto-generated catch block
    	  System.out.println("this is error");
			se.printStackTrace();
      }catch(Exception e) {
    	  e.printStackTrace();
      }
    }
    
    protected void close() {
    	try {
    		stmt.close();
    	    conn.close();
    	}catch(SQLException se) {
    		se.printStackTrace();
    	}
    }
    
    public MysqlDriver() {
        try {
        	// 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
