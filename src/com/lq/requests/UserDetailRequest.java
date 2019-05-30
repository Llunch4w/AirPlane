package com.lq.requests;

public class UserDetailRequest extends Request{
	private String userID;
	public UserDetailRequest(String userID) {
		this.userID = userID;
	}
	public String getID() {
		return userID;
	}
}
