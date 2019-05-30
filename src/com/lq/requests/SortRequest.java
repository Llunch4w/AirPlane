package com.lq.requests;

import com.lq.model.Flight;

//��������
public class SortRequest extends Request{
	private Flight[] flights;
	private String type;//����ʽ
	private String method;//�������
	public SortRequest(Flight[] flights,String type,String method) {
		this.flights = flights;
		this.type = type;
		this.method = method;
	}
	public Flight[] getFlight() {
		return flights;
	}
	public String getType() {
		return type;
	}
	public String getMethod() {
		return method;
	}
}
