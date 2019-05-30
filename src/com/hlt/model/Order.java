package com.hlt.model;

import java.util.*;

import com.lq.common.time.DateTime;

import java.io.*;

public class Order implements Serializable{
	String planeID;//航班号
	private int flyPersonNum;//乘机人数，用于座位检测
	String userID;//此账单所属用户ID
	String orderID;//订单号,由时间戳自动生成
	DateTime buyTime;//订单生成时间
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();//顾客购票信息
//	Ticket tickets[] = new Ticket[5];
	public Order() {
		
	}
	public void setOrderID(String OrderID) {
		orderID = OrderID;
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setPlaneID(String PlaneID) {
		 planeID =  PlaneID;
	}
	
	public String  getPlaneID() {
		return planeID;
	}
	
	public DateTime getBuyTime() {
		return buyTime;
	} 
	
	public ArrayList<Ticket> getTicket() {
		return tickets;
	}
	
	public void setTicket(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public void setBuyTime(DateTime buyTime) {
		this.buyTime = buyTime;
	}
	public int getFlyPersonNum() {
		return flyPersonNum;
	}
	public void setFlyPersonNum(int flyPersonNum) {
		this.flyPersonNum = flyPersonNum;
	}
	
	public void commit() {
		Date t = new Date();
		buyTime = new DateTime(t);
		orderID = String.format("%d%d%d%d%d%d", 
				t.getYear(),t.getMonth(),
				t.getDay(),t.getHours(),
				t.getMinutes(),t.getSeconds());
	}
}

