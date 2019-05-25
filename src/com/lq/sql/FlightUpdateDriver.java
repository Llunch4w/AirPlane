package com.lq.sql;

import java.sql.*;

import com.lq.common.format.FlightInsertFormat;
import com.lq.model.Flight;

public class FlightUpdateDriver extends MysqlDriver{
	public void delayUpdate(Flight flight) {
		try {
			connect("common");
			String sql = String.format("select * from startDelay where "
					+ "flightID=\"%s\"",flight.getId());
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				sql = String.format("update startDelay "
						+"set dalayTime=%d,delayReason=\"%s\" "
						+ "where flightID = \"%s\"", 
						flight.getStartTime().stayTime.getMin(),
						flight.getStartTime().delayReason,
						flight.getId());
				stmt.execute(sql);
			}
			else {
				sql = String.format("update status "
						+ "set state=\"%s\",isStartDelay=%b "
						+ "where flightID=\"%s\"",
						"延误",1,flight.getId());
				stmt.execute(sql);
				sql = String.format("insert into startDelay "
						+ "values(%s)",
						new FlightInsertFormat().delayFormat(flight));
				System.out.println(sql);
				stmt.execute(sql);
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
