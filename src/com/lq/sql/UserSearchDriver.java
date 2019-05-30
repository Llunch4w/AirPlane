package com.lq.sql;

import com.hlt.model.UserMe;
import java.sql.*;

public class UserSearchDriver extends MysqlDriver{
	public UserMe search(String id) {
		try {
			connect("user");
			String sql = String.format("select * from userDetail "
					+"natural join userLogin where "
					+ "id=\"%s\"",id);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				UserMe me = new UserMe(rs.getString("id"),
						rs.getString("password"),rs.getString("idCard"),
						rs.getString("name"),rs.getString("phone"));
				return me;
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
	}
}
