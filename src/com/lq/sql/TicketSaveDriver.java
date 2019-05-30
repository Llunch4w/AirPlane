package com.lq.sql;

import com.hlt.model.Ticket;
import com.lq.common.format.OrderInsertFormat;
import com.lq.common.format.TicketInsertFormat;

import java.sql.*;
import java.util.ArrayList;

public class TicketSaveDriver extends MysqlDriver{
	public void save(ArrayList<Ticket> tickets) {
		try {
			connect("common");
			String sql;
			for(Ticket ticket:tickets) {
				sql = "insert into ticket " + 
						String.format("values(%s)",
						new TicketInsertFormat().baseFormat(ticket));
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
