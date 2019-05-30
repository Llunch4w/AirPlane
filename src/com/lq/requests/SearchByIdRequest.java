package com.lq.requests;

public class SearchByIdRequest extends Request{
	private static final long serialVersionUID = 1L;
	private String flightID;
	public SearchByIdRequest(String flightID) {
		this.flightID = flightID;
	}
	public String getFlightID() {
		return flightID;
	}
}
