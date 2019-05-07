package com.lq.model;

import java.util.ArrayList;

import com.lq.common.time.*;

public class Flight {//����
	private String id;//�����
	private String company;//���չ�˾
	private String planeType;//����
	private String building; //��վ¥
	private double kidPrice;//��ͯƱ��
	private double adultPrice;//����Ʊ��
	private String src,trans,des,week;//��㡢��ת���յ�,�ܼ�
	private PointTime startTime,arriveTime;	
	private PointTime transArriveTime = null,transLeaveTime = null;
	private Container container;//����������
	
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

class PointTime{//����;���ص��ʱ��
	public DateTime preTime;//Ԥ��ʱ��
	public DateTime delayTime;//������ʱ��
	public StayTime stayTime;//ͣ��ʱ��
	public PointTime(DateTime t) {
		preTime = new DateTime(t);
		delayTime = new DateTime(t);//Ĭ����û�������
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


class Container{//��������
	public boolean[][] seats;
	public int row,col;
	public int capacity;//������
	public int remain;//ʣ��
	public Container(int _row) {
		row = _row;
		col = 10;
		capacity = row*col;
		remain = capacity;
		seats = new boolean[row][col];
	}
}
