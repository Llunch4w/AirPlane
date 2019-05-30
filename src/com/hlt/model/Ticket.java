package com.hlt.model;

import java.io.*;

public class Ticket implements Serializable{
	private String OrderId;
	private String name;
	private String idCard;
    private String phoneNumber;
    private String planeID;//航班号
    private int seatID;//座位号
    private double price;//票价
    private  String seatType;//座位类型
//    private  String CustomerType[]= {"成人","儿童"};//机票类型(成人，儿童）
    private double discount;//因座位机票类型差异导致的折扣
    private double realDiscount;//折扣
    private boolean used ;//是否使用
    public Ticket(){
    	discount=1;
    	used=false;   	
    }
    public Ticket(String orderId,String name,String idCard,
    		String phone,String planeID) {
    	this.OrderId = orderId;
    	this.name = name;
    	this.idCard = idCard;
    	this.phoneNumber = phone;
    	this.planeID = planeID;
    }
    public Ticket(String name,String idCard,String phone) {
    	this.name = name;
    	this.idCard = idCard;
    	this.phoneNumber = phone;
    }
    public void setOrderId(String orderId) {
    	this.OrderId = orderId;
    }
    
    public String getOrderId() {
    	return OrderId;
    }
    
    public void setPlaneID(String PlaneID) {
    	planeID =  PlaneID;
    }
    
    public void setName(String Name) {
    	name = Name;
    } 
    
    public void setIDCard(String IDCard) {
    	idCard = IDCard;
    }
    
    public void setPhoneNumber(String PHNUM) {
    	phoneNumber = PHNUM;
    }
    
    public void setSeatID(int seatID) {
 	   this.seatID =  seatID;
    }

    public String  getPlaneID() {
    	return planeID;
    }
   
    public String getName() {
    	return name;
    }
    
   
    public String getIDCard() {
    	return idCard;
    }
    public String getPhoneNumber() {
    	return phoneNumber;
    }

	public int getSeatID() {
		return seatID;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	public void setSeattype(String type) {
		this.seatType = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price*discount*realDiscount;
	}
	public double getRealdiscount() {
		return realDiscount;
	}
//	public void setRealdiscount(double realdiscount) {
//		this.realDiscount = realdiscount;
//	}
	public String getSeatType() {
		return seatType;
	}
//	public String[] getCustomerType() {
//		return CustomerType;
//	}
	
}
