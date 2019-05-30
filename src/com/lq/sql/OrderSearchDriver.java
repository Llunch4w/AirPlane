package com.lq.sql;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.hlt.model.Order;
import com.hlt.model.Ticket;
import com.lq.common.time.DateTime;


public class OrderSearchDriver extends MysqlDriver{
	public ArrayList<Order> search(String id) {
		try {
			connect("common");
			ArrayList<Order> orders = new ArrayList<Order>();
			String sql = String.format("select * from orders "
					+"where userID=\"%s\"",id);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Order order = new Order();
				order.setPlaneID(rs.getString("flightID"));
				String orderID = rs.getString("orderID");
				order.setOrderID(orderID);
				order.setFlyPersonNum(rs.getInt("flyPersonNum"));
				DateTime t = new DateTime(rs.getTimestamp("buyTime"));
				order.setBuyTime(t);
				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				String sqlTicket = String.format("select * from ticket "
						+"where orderID=\"%s\"",orderID);
				
				Statement stmt2 = conn.createStatement();
				ResultSet ticketRs = stmt2.executeQuery(sqlTicket);
				while(ticketRs.next()) {
					Ticket ticket = new Ticket(ticketRs.getString("orderID"),
							ticketRs.getString("name"),ticketRs.getString("IDcard"),
							ticketRs.getString("phone"),ticketRs.getString("flightID"));
					ticket.setDiscount(ticketRs.getFloat("discount"));
					ticket.setPrice(ticketRs.getFloat("price"));
					ticket.setUsed(ticketRs.getBoolean("used"));
					ticket.setSeatID(ticketRs.getInt("seatID"));
					ticket.setSeattype(ticketRs.getString("seatType"));
					tickets.add(ticket);
				}
				order.setTicket(tickets);
				orders.add(order);			
			}
			return orders;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
	}
}
