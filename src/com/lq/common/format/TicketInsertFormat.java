package com.lq.common.format;

import com.hlt.model.Order;
import com.hlt.model.Ticket;

public class TicketInsertFormat {
	public String baseFormat(Ticket ticket) {
		return String.format("%s,%d,%s,%s,%s,%s,"
				+ "%.2f,%s,%.2f,%b",
				stringWrapper(ticket.getPlaneID()),
				ticket.getSeatID(),
				stringWrapper(ticket.getOrderId()),
				stringWrapper(ticket.getName()),
				stringWrapper(ticket.getIDCard()),
				stringWrapper(ticket.getPhoneNumber()),
				ticket.getPrice(),
				stringWrapper(ticket.getSeatType()),
				ticket.getDiscount(),
				ticket.isUsed());
	}
	
	private String stringWrapper(String s) {
		return String.format("\"%s\"", s);
	}
}
