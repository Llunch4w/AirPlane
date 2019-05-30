package com.lq.requests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchByPlaceRequest extends Request{
	private static final long serialVersionUID = 1L;
	private String src,des;
	private Date date;
	public SearchByPlaceRequest(String src,String des,String s_date) {
		this.src = src;
		this.des = des;
		if(s_date.equals("")) {
			date = null;
		}
		else {		
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
			try {
				date = ft.parse(s_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	public String getSrc() {
		return src;
	}
	public String getDes() {
		return des;
	}
	public Date getDate() {
		return date; 
	}
}
