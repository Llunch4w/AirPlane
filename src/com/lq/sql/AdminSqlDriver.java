package com.lq.sql;

import java.sql.*;

public class AdminSqlDriver extends MysqlDriver{
	public boolean check(String workID,String pwd) {
		try {	
			connect();
			stmt = conn.createStatement();
			String sql = "select workID,password from AdminLogIn";
			ResultSet rs = stmt.executeQuery(sql);
			// չ����������ݿ�
			while(rs.next()){
				// ͨ���ֶμ���
				String temp_workID = rs.getString("workID");
				String temp_pwd = rs.getString("password");
				if(workID.equals(temp_workID) && pwd.equals(temp_pwd))
					return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
		// ��ɺ�ر�
          close();
		}
		return false;
	}
}
