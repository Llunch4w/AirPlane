package com.lq.graph;

import java.util.ArrayList;
import java.sql.*;
import com.lq.sql.MysqlDriver;

public class BuildGraph extends MysqlDriver{
	public Graph build() {
		try {
			ArrayList<String> citys = new ArrayList<String>();
			connect("common");
			String sql = "select takeOff_place from flight";
			ResultSet rs = stmt.executeQuery(sql);
			int cnt = 0;
			while(rs.next()) {
				cnt++;
				String city = rs.getString("takeOff_place");
				if(!citys.contains(city))
					citys.add(city);
			}
			sql = "select arrive_place from flight";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String city = rs.getString("arrive_place");
				if(!citys.contains(city))
					citys.add(city);
			}
			Graph graph = new Graph(citys.size(),cnt);
			sql = "select * from flight natural join tickets_info";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String src = rs.getString("takeOff_place");
				String des = rs.getString("arrive_place");
				float price = rs.getFloat("adultPrice");
				Timestamp t1 = rs.getTimestamp("takeoff_time");
				Timestamp t2 = rs.getTimestamp("arrive_time");
				int min2 = t2.getDay()*24*60 + t2.getHours()*60
						+t2.getMinutes();
				int min1 = t1.getDay()*24*60 + t1.getHours()*60
						+t1.getMinutes();
				int gap = min2-min1;
				int remain = rs.getInt("remain");
				graph.addEdge(src, des,gap,price,remain);
			}
			return graph;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			close();
		}
	}
}
