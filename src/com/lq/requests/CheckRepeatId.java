package com.lq.requests;

import com.lq.sql.*;

public class CheckRepeatId extends Request{
	private String id;
	public CheckRepeatId(String id) {
		this.id = id;
	}
	public String getID() {
		return id;
	}
}
