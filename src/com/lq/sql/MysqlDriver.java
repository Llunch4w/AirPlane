package com.lq.sql;

import java.sql.*;

public class MysqlDriver {
	// JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    private String db_url;
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "MyNewPass";
    
    //�������
    protected Connection conn = null;
    protected Statement stmt = null;
    
    public void set_db_url(String database) {
    	db_url = "jdbc:mysql://localhost:3306/" + database + "?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
    }
    
    protected void connect() {
      System.out.println("�������ݿ�...");
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
        	// ע�� JDBC ����
            Class.forName(JDBC_DRIVER);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
//    public void execSql(String sql) {
//    	// ִ�в�ѯ
//        try { 
//        	// ������
//            System.out.println("�������ݿ�...");
//            conn = DriverManager.getConnection(db_url,USER,PASS);
//        	// ʵ����Statement����
//        	stmt = conn.createStatement();
//        	ResultSet rs = stmt.executeQuery(sql);
//        	// չ����������ݿ�
//            while(rs.next()){
//                // ͨ���ֶμ���
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String url = rs.getString("dept_name");
//    
//                // �������
//                System.out.print("ID: " + id);
//                System.out.print(", ��ʦ����: " + name);
//                System.out.print(", ����ϵ��: " + url);
//                System.out.print("\n");
//            }
//            // ��ɺ�ر�
//            stmt.close();
//            conn.close();
//        }catch(SQLException se) {
//        	se.printStackTrace();
//        }catch(Exception e){
//            // ���� Class.forName ����
//            e.printStackTrace();
//        }
//    }
}
