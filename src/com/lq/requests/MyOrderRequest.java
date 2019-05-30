package com.lq.requests;

public class MyOrderRequest extends Request{
	private static final long serialVersionUID = 1L;
	private String userID;
	public MyOrderRequest(String id) {
		userID = id;
	}
	public String getID() {
		return userID;
	}
}
