package com.lq.sql;

import java.sql.*;

public class UserSqlDriver extends MysqlDriver{
	public boolean check(String id,String pwd) {
		try {	
			connect("user");
			String sql = "select id,password from userLogIn";
			ResultSet rs = stmt.executeQuery(sql);
			// emmչ����������ݿ�
			while(rs.next()){
				// emmͨ���ֶμ���
				String temp_id = rs.getString("id");
				String temp_pwd = rs.getString("password");
				if(id.equals(temp_id) && pwd.equals(temp_pwd))
					return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		// emm��ɺ�ر�
          close();
		}
		return false;
	}
	
	public boolean check(String id) {
		try {	
			connect("user");
			String sql = "select id from userLogIn";
			ResultSet rs = stmt.executeQuery(sql);
			// emmչ����������ݿ�
			while(rs.next()){
				// emmͨ���ֶμ���
				String temp_id = rs.getString("id");
				if(id.equals(temp_id))
					return false;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		// emm��ɺ�ر�
          close();
		}
		return true;
	}
}
