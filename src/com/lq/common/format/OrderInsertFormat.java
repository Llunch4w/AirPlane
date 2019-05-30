package com.lq.common.format;

import java.util.ArrayList;
import java.util.Date;

import com.hlt.model.Order;
import com.hlt.model.Ticket;
import com.lq.common.time.DateTime;


public class OrderInsertFormat {
	public String baseFormat(Order order) {
		return String.format("%s,%s,%s,%d,%s",
				stringWrapper(order.getOrderID()),
				stringWrapper(order.getPlaneID()),
				stringWrapper(order.getUserID()),
				order.getFlyPersonNum(),
				timeStampWrapper(order.getBuyTime()));
	}
	
	private String stringWrapper(String s) {
		return String.format("\"%s\"", s);
	}
	
	private String timeStampWrapper(DateTime time) {
		return String.format("\"%d-%02d-%02d %d:%d\"",
								time.getYear(),
								time.getMonth(),
								time.getDay(),
								time.getHour(),
								time.getMinute());
	}
}
