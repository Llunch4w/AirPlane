package com.lq.sql;

import java.sql.*;
import java.util.*;

public class PlaceSqlDriver extends MysqlDriver{
	public ArrayList<String> getPlaces() {
		ArrayList<String> returnRes = new ArrayList<String>();
		try {
			connect("common");
			String sql = "select * from takeOffPlace";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
//				String province = rs.getString("province");
				String city = rs.getString("city");
//				String airport = rs.getString("airport");
//				String merge = city + " " + airport;
				returnRes.add(city);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return returnRes;
	}
}
