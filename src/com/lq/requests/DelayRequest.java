package com.lq.requests;

public class DelayRequest extends Request{
	private String delayFlight;
	private String userID;
	private String delayMsg;
	public DelayRequest(String flightId,String userID,String msg) {
		delayFlight = flightId;
		this.userID = userID;
		delayMsg = msg;
	}
	public String getFlightId() {
		return delayFlight;
	}
	public String getMsg() {
		return delayMsg;
	}
	public String getUser() {
		return userID;
	}
}
