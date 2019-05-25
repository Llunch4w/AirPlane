package com.lq.common.format;

import com.lq.model.Flight;

public class FlightSearchResultFormat {
	public String getFormat(Flight flight) {
		return String.format("%s %s %s %s",
							flight.getId(),
							flight.getCompany(),
							flight.getTakeoffPlace(),
							flight.getArrivePlace());
	}
}
