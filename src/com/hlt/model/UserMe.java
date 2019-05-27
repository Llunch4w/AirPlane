package com.hlt.model;

import java.io.Serializable;

public class UserMe implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String idCard;
	private String name;
    private String phoneNumber;
    private String type = "normal";//{"normal","membership"}
    public UserMe(String id,String pwd,String idCard,
    		String name,String phone) {
    	this.id = id;
    	this.password = pwd;
    	this.idCard = idCard;
    	this.name = name;
    	this.phoneNumber = phone;
    }
	public void setID(String id) {
		this.id = id;
	}
	public void setpassword(String newpassword) {
		this.password=newpassword;
	}
	public void setIDCard(String IDCard) {
   	 idCard = IDCard;
    }
    public void setPhoneNumber(String PHNUM) {
   	 phoneNumber = PHNUM;
    }
    public void setType(String type) {
    	this.type = type;
    }
    public String getID() {
		return id;
	}
	public String getpassword() {
		return  password;
	}
	public String getName() {
		return name;
	}
    public String getIDcard() {
    	return idCard;
    }
    public String getPhoneNumber() {
   	 return phoneNumber;
    }
	public String getType() {
		return type;
	}
}
