package com.lq.sql;

import java.sql.ResultSet;

public class InformDelayDriver extends MysqlDriver{
	public void inform(String flightID) {
		try {
			connect("common");
			String sql = String.format("select * from selled where "
					+ "flightID=\"%s\"",flightID);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				sql = String.format("insert into delayInform\r\n" + 
						"    values (\"%s\",\"%s\")",
						flightID,rs.getString("userID"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
