package com.lq.sql;

import java.sql.*;

public class UserSqlDriver extends MysqlDriver{
	public boolean check(String id,String pwd) {
		try {	
			connect("user");
			String sql = "select id,password from userLogIn";
			ResultSet rs = stmt.executeQuery(sql);
			// emm展开结果集数据库
			while(rs.next()){
				// emm通过字段检索
				String temp_id = rs.getString("id");
				String temp_pwd = rs.getString("password");
				if(id.equals(temp_id) && pwd.equals(temp_pwd))
					return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		// emm完成后关闭
          close();
		}
		return false;
	}
	
	public boolean check(String id) {
		try {	
			connect("user");
			String sql = "select id from userLogIn";
			ResultSet rs = stmt.executeQuery(sql);
			// emm展开结果集数据库
			while(rs.next()){
				// emm通过字段检索
				String temp_id = rs.getString("id");
				if(id.equals(temp_id))
					return false;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		// emm完成后关闭
          close();
		}
		return true;
	}
}
