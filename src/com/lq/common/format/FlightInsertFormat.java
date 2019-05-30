package com.lq.common.format;

import com.lq.common.time.DateTime;
import com.lq.model.Flight;

public class FlightInsertFormat {
	public String baseFormat(Flight flight) {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
				stringWrapper(flight.getId()),
				stringWrapper(flight.getCompany()),
				stringWrapper(flight.getPlanetype()),
				stringWrapper(flight.getBuilding()),
				stringWrapper(flight.getWeek()),
				stringWrapper(flight.getTakeoffPlace()),
				stringWrapper(flight.getArrivePlace()),
				timeStampWrapper(flight.getStartTime().getPlanTime()),
				timeStampWrapper(flight.getArriveTime().getPlanTime()));
	}
	public String ticketFormat(Flight flight) {
		return String.format("%s,%d,%.2f,%.2f,%.2f,%.2f", 
				stringWrapper(flight.getId()),
				flight.getContainer().getReamin(),
				flight.getTopprice(),
				flight.getKidprice(),
				flight.getAdultprice(),
				flight.getDiscount());
	}
	public String transportFormat(Flight flight) {
		return String.format("%s,%s,%s,%s,%b,%d", 
				stringWrapper(flight.getId()),
				stringWrapper(flight.getTransPlace()),
				timeStampWrapper(flight.getTransArriveTime().getPlanTime()),
				timeStampWrapper(flight.getTransLeaveTime().getPlanTime()),
				flight.getTransLeaveTime().isDelayed,
				flight.getTransLeaveTime().getStayTime().getMin());
	}
	public String statusFormat(Flight flight) {
		return String.format("%s,%s,%b,%b,%b", 
				stringWrapper(flight.getId()),
				stringWrapper(flight.getState()),
				flight.isTrans(),
				flight.getStartTime().isDelayed,
				flight.isCancel());
	}
	public String delayFormat(Flight flight) {
		return String.format("%s,%d,%s", 
				stringWrapper(flight.getId()),
				flight.getStartTime().stayTime.getMin(),
				stringWrapper(flight.getStartTime().delayReason));
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
