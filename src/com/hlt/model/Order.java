package com.hlt.model;

import java.util.*;

import com.lq.common.time.DateTime;

import java.io.*;

public class Order implements Serializable{
	String planeID;//�����
	private int flyPersonNum;//�˻�������������λ���
	String userID;//���˵������û�ID
	String orderID;//������,��ʱ����Զ�����
	DateTime buyTime;//��������ʱ��
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();//�˿͹�Ʊ��Ϣ
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

