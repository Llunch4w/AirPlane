package com.lq.sql;

import java.sql.*;

import com.lq.model.Flight;

public class FlightSaveDriver extends MysqlDriver{
	public void save(Flight flight) {
		try {
			connect("common");
			String inform = String.format("values(%s,%s,%s,%s,%s)",
					flight.getFlightBase(),
					flight.getFlightContainer(),flight.getFlightPrice(),
					flight.getFlightPlace(),flight.getFlightTime());
			String sql = "insert into flight " + inform;
			System.out.println(sql);
			stmt.execute(sql);
			
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
