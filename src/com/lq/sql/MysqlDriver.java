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
    
    public void set_db_url(String database) {
    	db_url = "jdbc:mysql://localhost:3306/" + database + "?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
    }
    
    protected void connect() {
      System.out.println("连接数据库...");
      try {
			conn = DriverManager.getConnection(db_url,USER,PASS);
      } catch (SQLException e) {
			// TODO Auto-generated catch block
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
//    public void execSql(String sql) {
//    	// 执行查询
//        try { 
//        	// 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(db_url,USER,PASS);
//        	// 实例化Statement对象
//        	stmt = conn.createStatement();
//        	ResultSet rs = stmt.executeQuery(sql);
//        	// 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String url = rs.getString("dept_name");
//    
//                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 教师姓名: " + name);
//                System.out.print(", 所在系名: " + url);
//                System.out.print("\n");
//            }
//            // 完成后关闭
//            stmt.close();
//            conn.close();
//        }catch(SQLException se) {
//        	se.printStackTrace();
//        }catch(Exception e){
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }
//    }
}
