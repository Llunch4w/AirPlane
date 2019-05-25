package com.lq.sql;

import java.sql.*;
import com.lq.common.format.*;

import com.lq.model.Flight;

public class FlightSaveDriver extends MysqlDriver{
	public void save(Flight flight) {
		try {
			connect("common");
			String sql = "insert into flight " + 
					String.format("values(%s)",
					new FlightInsertFormat().baseFormat(flight));;
			System.out.println(sql);
			stmt.execute(sql);
			sql = "insert into tickets_info " + 
					String.format("values(%s)",
					new FlightInsertFormat().ticketFormat(flight));
			stmt.execute(sql);
			sql = "insert into status " + 
					String.format("values(%s)",
					new FlightInsertFormat().statusFormat(flight));
			stmt.execute(sql);
			if(flight.isTrans()) {
				sql = "insert into transport " + 
					String.format("values(%s)",
					new FlightInsertFormat().transportFormat(flight));
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
