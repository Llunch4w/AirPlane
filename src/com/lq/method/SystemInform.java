package com.lq.method;

import com.lq.client.Client;
import com.lq.model.Announcement;
import com.lq.sql.*;
import com.lq.model.Flight;

public class SystemInform {
	public String recommand(String src,String des) {//δ������������
		String flightID = new FlightSearchDriver().searchRecommand(src, des);
		return flightID;
	}
	public void inform(Flight flight,String msg){
		/*
		 * �������ж����а���flight.getId()���û�����Announcement
		 */
		String send_msg = msg + recommand(flight.getTakeoffPlace(),flight.getArrivePlace());
		new InformDelayDriver().inform(flight.getId(),send_msg);
	}
	
	
}
