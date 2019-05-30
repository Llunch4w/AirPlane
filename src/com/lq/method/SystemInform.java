package com.lq.method;

import com.lq.client.Client;
import com.lq.model.Announcement;
import com.lq.sql.*;
import com.lq.model.Flight;

public class SystemInform {
	public String recommand(String src,String des) {//未延误的最近航班
		String flightID = new FlightSearchDriver().searchRecommand(src, des);
		return flightID;
	}
	public void inform(Flight flight,String msg){
		/*
		 * 搜索所有订单中包含flight.getId()的用户发送Announcement
		 */
		String send_msg = msg + recommand(flight.getTakeoffPlace(),flight.getArrivePlace());
		new InformDelayDriver().inform(flight.getId(),send_msg);
	}
	
	
}
