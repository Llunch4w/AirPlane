package com.lq.sql;

import java.sql.*;
import java.util.ArrayList ;

import com.lq.common.time.DateTime;
import com.lq.common.time.StayTime;
import com.lq.model.Flight;

public class FlightSearchDriver extends MysqlDriver{
	public ArrayList<Flight> searchById_base(String flightId) {
		ArrayList<Flight> results = new ArrayList<Flight>();
		try {
			connect("common");
			String sql = String.format("select * from flight where flightID=\"%s\"", 
					flightId);
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				Flight flight = new Flight(rs.getString("flightID"),
						rs.getString("company"),rs.getString("planeType"),
						rs.getString("building"));		
				flight.setWeek(rs.getString("week"));
				flight.setSrcPoint(rs.getString("takeOff_place"));
				flight.setDesPoint(rs.getString("arrive_place"));
				flight.setStartTime(new DateTime(rs.getTimestamp("takeOff_time")));
				flight.setArriveTime(new DateTime(rs.getTimestamp("arrive_time")));			
				results.add(flight);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return results;
	}
	
	public ArrayList<Flight> searchByPlace_base(String src,String des){
		ArrayList<Flight> results = new ArrayList<Flight>();
		try {
			connect("common");
			String sql = String.format("select * from flight where takeOff_place="
					+ "\"%s\" and arrive_place=\"%s\"", src,des);
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				// 通过字段检索
				Flight flight = new Flight(rs.getString("flightID"),
						rs.getString("company"),rs.getString("planeType"),
						rs.getString("building"));
				flight.setWeek(rs.getString("week"));
				flight.setSrcPoint(rs.getString("takeOff_place"));
				flight.setDesPoint(rs.getString("arrive_place"));
				flight.setStartTime(new DateTime(rs.getTimestamp("takeOff_time")));
				flight.setArriveTime(new DateTime(rs.getTimestamp("arrive_time")));
				results.add(flight);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return results;
	}
	
	public String searchRecommand(String src,String des) {
		try {
			connect("common");
			String sql = String.format("select * from flight\r\n" + 
					"where takeOff_place=\"%s\"\r\n" + 
					"and arrive_place=\"%s\"\r\n" + 
					"and flightID in \r\n" + 
					"    (\r\n" + 
					"        select flightID\r\n" + 
					"        from status\r\n" + 
					"        where isStartDelay=false\r\n" + 
					"    )\r\n" + 
					"order by takeOff_time", src,des);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getString("flightID");
			}
			else {
				System.out.println("没有符合条件的航班");
				return "";
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("推荐航班数据库查询出现异常");
			return "";
		}
	}
	
	public void addDetail(Flight flight) {
		try {
			connect("common");
			String sql = String.format("select * from status where flightID=\"%s\"",
					flight.getId());
			ResultSet rs = stmt.executeQuery(sql);
			flight.setState(rs.getString("state"));
			flight.setTransflag(rs.getBoolean("isTrans"));
			sql = String.format("select * from tickets_info where " + 
			"flightID = \"%s\"", flight.getId());
			rs = stmt.executeQuery(sql);
			flight.setKidprice(rs.getFloat("kidPrice"));
			flight.setAdultprice(rs.getFloat("adultPrice"));
			if(flight.isTrans()) {
				sql = String.format("select * from transport where " + 
						"flightID = \"%s\"", flight.getId());
				rs = stmt.executeQuery(sql);
				flight.setTransPoint(rs.getString("trans_place"));
				flight.setTransArriveTime(new DateTime(rs.getTimestamp("midArvTm")));
				flight.setTransLeaveTime(new DateTime(rs.getTimestamp("midLevTm")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
