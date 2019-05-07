package com.lq.model;

import java.util.ArrayList;

import com.lq.common.time.*;

public class Flight {//航班
	private String id;//航班号
	private String company;//航空公司
	private String planeType;//机型
	private String building; //航站楼
	private double kidPrice;//儿童票价
	private double adultPrice;//成人票价
	private String src,trans,des,week;//起点、中转、终点,周几
	private PointTime startTime,arriveTime;	
	private PointTime transArriveTime = null,transLeaveTime = null;
	private Container container;//航班容器类
	
	public Flight(String _id,String _company,String _type,String bui) {
		id = _id;
		company = _company;
		planeType = _type;
		building = bui;
	}
	public void setPrice(double kidP,double adultP) {
		kidPrice = kidP;
		adultPrice = adultP;
	}
	public void setWeek(String w) {
		week = w;
	}
	public void setPlace(String s,String m,String e) {
		src = s;
		trans = m;
		des = e;
	}
	public void setCapity(int row) {
		container = new Container(row);
	}
	public void setTime(String name,DateTime t) {
		if(name.equals("start time")) {
			startTime = new PointTime(t);
		}
		else if(name.equals("arrive time")) {
			arriveTime = new PointTime(t);
		}
		else if(name.equals("arrive transport time")) {
			transArriveTime = new PointTime(t);
		}
		else if(name.equals("leave transport time")) {
			transLeaveTime = new PointTime(t);
		}		
	}
	
	public String getFlightBase() {
		String s = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",id,
				company,planeType,building,week);
		return s;
	}
	public String getFlightContainer() {
		String s = String.format("%d,%d", container.capacity,container.remain);
		return s;
	}
	public String getFlightPrice() {
		String s = String.format("%.2f,%.2f", kidPrice,adultPrice);
		return s;
	}
	public String getFlightPlace() {
		return String.format("\"%s\",\"%s\",\"%s\"", src,trans,des);
	}
	public String getFlightTime(String name) {
		if(name.equals("start time")) {
			return startTime.getFormatTime("hope time");
		}
		else if(name.equals("arrive time")) {
			return arriveTime.getFormatTime("hope time");
		}
		else if(name.equals("arrive transport time")) {
			return transArriveTime.getFormatTime("hope time");
		}
		else if(name.equals("leave transport time")) {
			return transLeaveTime.getFormatTime("hope time");
		}
		else {
			System.out.println("no this time");
			return "";
		}
	}
	public String getFlightTime() {
		String s = String.format("%s,%s,%s,%s", getFlightTime("start time"),
				getFlightTime("arrive transport time"),getFlightTime("leave transport time"),
				getFlightTime("arrive time"));
		return s;
	}
}

class PointTime{//航线途经地点的时间
	public DateTime preTime;//预计时间
	public DateTime delayTime;//延误后的时间
	public StayTime stayTime;//停留时间
	public PointTime(DateTime t) {
		preTime = new DateTime(t);
		delayTime = new DateTime(t);//默认是没有延误的
		stayTime = new StayTime(0,0,0);
	}
	public String getFormatTime(String s) {
		if(s.equals("hope time")){
			return preTime.getFormatTime();
		}
		else if(s.equals("delayed time")) {
			return delayTime.getFormatTime();
		}
		else {
			return preTime.getFormatTime();
		}
	}
}


class Container{//航班容器
	public boolean[][] seats;
	public int row,col;
	public int capacity;//总容量
	public int remain;//剩余
	public Container(int _row) {
		row = _row;
		col = 10;
		capacity = row*col;
		remain = capacity;
		seats = new boolean[row][col];
	}
}
