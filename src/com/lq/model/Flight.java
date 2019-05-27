package com.lq.model;

import java.util.ArrayList;

import com.lq.common.time.*;

public class Flight{//����
	private String id;//�����
	private String state = "�ƻ�";//��ǰ״̬��Ĭ�ϼƻ�״̬
	private String company;//���չ�˾
	private String planeType;//����
	private String building; //��վ¥
	private double kidPrice;//��ͯƱ��
	private double adultPrice;//����Ʊ��
	private boolean isTransFlag = false;//�Ƿ���ת
	private boolean isCancelFlag = false;//�Ƿ�ȡ��
	private String src,trans,des,week;//��㡢��ת���յ�,�ܼ�
	private PointTime startTime,arriveTime;	
	private PointTime transArriveTime = null,transLeaveTime = null;
	private FlightContainer container;//����������
	
	//���캯��
	public Flight(String _id,String _company,String _type,String bui) {
		id = _id;
		company = _company;
		planeType = _type;
		building = bui;
	}
	public Flight() {}
	
	//���õ�����ֵ
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
	
	public void setCapity(int row) {//������������������������
		container = new FlightContainer(row);
	}
	
	// ��ȡ������ֵ
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
	
	
	//��̬����
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
			System.out.println("û����ת���������ô�ʱ��");
		}
		else {
			transArriveTime.delay(stayTime);
			transLeaveTime.delay(stayTime);
			arriveTime.delay(stayTime);
		}
	}
	public void tranLeaveDelay(StayTime stayTime) {
		if(!isTransFlag) {
			System.out.println("û����ת���������ô�ʱ��");
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
//	public String getTransStay() {//����
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


//class Container{//��������
//	public boolean[][] seats;
//	public int row,col;
//	public int capacity;//������
//	public int remain;//ʣ��
//	public Container(int _row) {
//		row = _row;
//		col = 10;
//		capacity = row*col;
//		remain = capacity;
//		seats = new boolean[row][col];
//	}
//}

//enum Statu{//����״̬
//	PLANING(0),CHECKOUT(1),DELAYED(2),CANCELED(3),
//	FLYING(4),LEISURE(5);
//	//�ƻ���ֵ��������ȡ�������С�����
//	private int value;
//	private Statu(int value) {
//		this.value = value;
//	}
//	public int value() {
//		return value;
//	}
//}
