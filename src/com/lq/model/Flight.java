package com.lq.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.lq.common.time.*;

public class Flight implements Serializable{//航班
	private String id;//航班号
	private String state = "计划";//当前状态，默认计划状态
	private String company;//航空公司
	private String planeType;//机型
	private String building; //航站楼
	private double kidPrice;//儿童票价
	private double adultPrice;//成人票价
	private double topPrice;//商务舱票价
	private double discount;//折扣
	private boolean isTransFlag = false;//是否中转
	private boolean isCancelFlag = false;//是否取消
	private String src,trans,des,week;//起点、中转、终点,周几
	private PointTime startTime,arriveTime;	
	private PointTime transArriveTime = null,transLeaveTime = null;
	private FlightContainer container = new FlightContainer();//航班容器类
	
	//构造函数
	public Flight(String _id,String _company,String _type,String bui) {
		id = _id;
		company = _company;
		planeType = _type;
		building = bui;
	}
	public Flight() {}
	
	//设置单属性值
	public void setID(String id) {
		this.id = id;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setPlanetype(String planeType) {
		this.planeType = planeType;
	}
	
	public void setKidprice(double kidPrice) {
		this.kidPrice = kidPrice;
	}
	
	public void setAdultprice(double adultPrice) {
		this.adultPrice = adultPrice;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public void setTransflag(boolean flag) {
		this.isTransFlag = flag;
	}
	
	public void setCancelflag(boolean flag) {
		this.isCancelFlag = flag;
	}
	
	public void setWeek(String week) {
		this.week = week;
	}
	
	public void setSrcPoint(String src) {
		this.src = src;
	}
	
	public void setTransPoint(String trans) {
		this.trans = trans;
	}
	
	public void setDesPoint(String des) {
		this.des = des;
	}
	
	public void setStartTime(DateTime time) {
		startTime = new PointTime(time);
	}
	
	public void setArriveTime(DateTime time) {
		arriveTime = new PointTime(time);
	}
	
	public void setTransArriveTime(DateTime time) {
		transArriveTime = new PointTime(time);
	}
	
	public void setTransLeaveTime(DateTime time) {
		transLeaveTime = new PointTime(time);
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void setTopprice(double price) {
		this.topPrice = price;
	}
	
	// 获取单属性值
	public String getId() {
		return id;
	}	
	
	public String getState() {
		return state;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getPlanetype() {
		return planeType;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public double getKidprice() {
		return kidPrice;
	}
	
	public double getAdultprice() {
		return adultPrice;
	}
	
	public double getTopprice() {
		return topPrice;
	}
	
	public double getDiscount() {
		return discount;
	}
	public boolean isTrans() {
		return isTransFlag;
	}
	
	public boolean isCancel() {
		return isCancelFlag;
	}
	
	public String getWeek() {
		return week;
	}
	
	public String getTakeoffPlace() {
		return src;
	}
	
	public String getArrivePlace() {
		return des;
	}
	
	public String getTransPlace() {
		if(isTransFlag)
			return trans;
		else
			return "";
	}
	
	public PointTime getStartTime() {		
		return startTime;
	}
	
	public PointTime getArriveTime() {
		return arriveTime;
	}
	
	public PointTime getTransArriveTime() {
		return transArriveTime;
	}
	
	public PointTime getTransLeaveTime() {
		return transLeaveTime;
	}
	
	public FlightContainer getContainer() {
		return container;
	}
	
	
	//动态操作
	public void startDelay(StayTime stayTime) {
		startTime.delay(stayTime);
		arriveTime.delay(stayTime);
		if(isTransFlag) {
			transArriveTime.delay(stayTime);
			transLeaveTime.delay(stayTime);
		}
	}
	public void arriveDelay(StayTime stayTime) {
		arriveTime.delay(stayTime);
	}
	public void transArriveDelay(StayTime stayTime) {
		if(!isTransFlag) {
			System.out.println("没有中转！不能设置此时间");
		}
		else {
			transArriveTime.delay(stayTime);
			transLeaveTime.delay(stayTime);
			arriveTime.delay(stayTime);
		}
	}
	public void tranLeaveDelay(StayTime stayTime) {
		if(!isTransFlag) {
			System.out.println("没有中转！不能设置此时间");
		}
		else {
			transLeaveTime.delay(stayTime);
			arriveTime.delay(stayTime);
		}
	}
}
