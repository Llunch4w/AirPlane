package com.lq.sql;

import java.sql.*;
import java.util.ArrayList;

import com.hlt.model.Order;
import com.hlt.model.Ticket;
import com.lq.common.format.OrderInsertFormat;


public class OrderSaveDriver extends MysqlDriver{
	public void save(Order order) {
		try {
			connect("common");
			String sql = "insert into orders " + 
					String.format("values(%s)",
					new OrderInsertFormat().baseFormat(order));
			System.out.println(sql);
			stmt.execute(sql);
			ArrayList<Ticket> tickets = order.getTicket();
			TicketSaveDriver driver = new TicketSaveDriver();
			if(tickets.size() < 1)
				return;
			else{
				driver.save(tickets);
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
