package com.lq.sql;

import java.sql.SQLException;
import java.util.ArrayList;

import com.lq.common.format.FlightInsertFormat;

public class FlightDeleteDriver extends MysqlDriver{
	public void deleteAll(ArrayList<String> flights) {
		try {
			connect("common");
			String sql;
			String tables[] = {"status","tickets_info","transport",
					"startDelay","flight"};
			for(String flight:flights) {
				for(String table:tables) {					
					sql = String.format("delete from %s where flightID=\"%s\"",
							table,flight);
					stmt.execute(sql);
				}
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 完成后关闭
	          close();
		}
	}
}
