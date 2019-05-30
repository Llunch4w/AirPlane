package com.lq.sql;

import java.sql.*;
import java.util.ArrayList;

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
						+"set daleyTime=%d,delayReason=\"%s\" "
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
	
	public void cancleUpdate(ArrayList<String> flights) {
		try {
			connect("common");
			for(String flightID:flights) {				
				String sql = String.format("update status set state=\"取消\"" 
						+ "where flightID=\"%s\"",flightID);
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
	
	public void allUpdate(Flight flight) {
		try {
			connect("common");
			String sql;
			String tables[] = {"tickets_info","flight"};
			for(String table:tables) {					
				sql = String.format("delete from %s where flightID=\"%s\"",
						table,flight);
				stmt.execute(sql);
			}
			sql = "insert into flight " + 
					String.format("values(%s)",
					new FlightInsertFormat().baseFormat(flight));
			System.out.println(sql);
			stmt.execute(sql);
			sql = "insert into tickets_info " + 
					String.format("values(%s)",
					new FlightInsertFormat().ticketFormat(flight));
			stmt.execute(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void updateSection(String id,String type,String newValue) {
		try {
			connect("common");
			String sql = String.format("update flight natural join tickets_info\r\n" + 
					"set %s=\"%s\"\r\n" + 
					"where flightID=\"%s\"",type,newValue,id);
			stmt.execute(sql);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
