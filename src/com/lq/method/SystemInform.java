package com.lq.method;

import com.lq.model.Announcement;
import com.lq.sql.*;
import com.lq.model.Flight;

public class SystemInform {
	public String recommand(String src,String des) {//δ������������
		String flightID = new FlightSearchDriver().searchRecommand(src, des);
		return flightID;
	}
	public void inform(Flight flight,String type,String reason){
		Announcement ann = new Announcement();
		ann.setType(type);
		ann.setFlight(flight.getId());
		ann.setReason(reason);
		ann.setRecommand(recommand(flight.getTakeoffPlace(),flight.getArrivePlace()));
		/*
		 * �������ж����а���flight.getId()���û�����Announcement
		 */
	}
	
	
}
