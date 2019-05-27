package com.lq.model;

import java.util.ArrayList;

import com.lq.common.time.*;

public class Flight{//航班
	private String id;//航班号
	private String state = "计划";//当前状态，默认计划状态
	private String company;//航空公司
	private String planeType;//机型
	private String building; //航站楼
	private double kidPrice;//儿童票价
	private double adultPrice;//成人票价
	private boolean isTransFlag = false;//是否中转
	private boolean isCancelFlag = false;//是否取消
	private String src,trans,des,week;//起点、中转、终点,周几
	private PointTime startTime,arriveTime;	
	private PointTime transArriveTime = null,transLeaveTime = null;
	private FlightContainer container;//航班容器类
	
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
	
	public void setCapity(int row) {//参数是排数还是总容量待定
		container = new FlightContainer(row);
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
	
	
	
	
//	public String getFlightBase() {
//		String s = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",id,
//				company,planeType,building,week);
//		return s;
//	}
//	public String getFlightContainer() {
//		String s = String.format("%d,%d", container.capacity,container.remain);
//		return s;
//	}
//	public String getFlightPrice() {
//		String s = String.format("%.2f,%.2f", kidPrice,adultPrice);
//		return s;
//	}
//	public String getFlightPlace() {
//		return String.format("\"%s\",\"%s\",\"%s\"", src,trans,des);
//	}
//	public String getFlightTime(String name,String type) {
//		/*
//		 * type{"hope time","delayed time","stay time"}
//		 * */
//		if(name.equals("start time")) {
//			return startTime.getFormatTime(type);
//		}
//		else if(name.equals("arrive time")) {
//			return arriveTime.getFormatTime(type);
//		}
//		else if(name.equals("arrive transport time")) {
//			return transArriveTime.getFormatTime(type);
//		}
//		else if(name.equals("leave transport time")) {
//			return transLeaveTime.getFormatTime(type);
//		}
//		else {
//			System.out.println("no this time");
//			return "";
//		}
//	}
//	public String getStringTime(String name,String type) {
//		if(name.equals("start time")) {
//			return startTime.toString(type);
//		}
//		else if(name.equals("arrive time")) {
//			return arriveTime.toString(type);
//		}
//		else if(name.equals("arrive transport time")) {
//			return transArriveTime.toString(type);
//		}
//		else if(name.equals("leave transport time")) {
//			return transLeaveTime.toString(type);
//		}
//		else {
//			System.out.println("no this time");
//			return "";
//		}
//	}
//	public StayTime getDelayTime(String name) {
//		if(name.equals("start")) {
//			return startTime.stayTime;
//		}
//		else if(name.equals("trans")) {
//			return transArriveTime.stayTime;
//		}
//		else
//			return null;
//	}
//	public String getTransStay() {//待定
//		try {		
//			StayTime stayTime = new StayTime(transArriveTime.preTime,
//					transLeaveTime.preTime);
//			return stayTime.toString();
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//	public String getFlightTime() {
//		String s = String.format("%s,%s,%s,%s", getFlightTime("start time","hope time"),
//				getFlightTime("arrive transport time","hope time"),
//				getFlightTime("leave transport time","hope time"),
//				getFlightTime("arrive time","hope time"));
//		return s;
//	}
//	public String getQueryResult() {
//		String s = String.format("%s %s %s %s %s", id,company,src,des,week);
//		return s;
//	}
}


//class Container{//航班容器
//	public boolean[][] seats;
//	public int row,col;
//	public int capacity;//总容量
//	public int remain;//剩余
//	public Container(int _row) {
//		row = _row;
//		col = 10;
//		capacity = row*col;
//		remain = capacity;
//		seats = new boolean[row][col];
//	}
//}

//enum Statu{//航班状态
//	PLANING(0),CHECKOUT(1),DELAYED(2),CANCELED(3),
//	FLYING(4),LEISURE(5);
//	//计划、值机、延误、取消、飞行、空闲
//	private int value;
//	private Statu(int value) {
//		this.value = value;
//	}
//	public int value() {
//		return value;
//	}
//}
