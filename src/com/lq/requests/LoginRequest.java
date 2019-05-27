package com.lq.requests;

public class LoginRequest extends Request{
	private static final long serialVersionUID = 1L;
	private String id;
	private String pwd;
	public LoginRequest(String id,String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	public String getID() {
		return id;
	}
	public String getPwd() {
		return pwd;
	}
	public String toString() {
		String s = id + ";" + pwd;
		return s;
	}
}
