package com.lq.model;

import java.io.Serializable;

public class FlightContainer implements Serializable{
	private boolean[][] normal_seats;
	private boolean[] top_seats;
	static int topNum = 10;
	static int row = 7;
	static int col = 6;
	static private int capacity = 50;//总容量
	private int remain;//剩余
	public FlightContainer() {
		remain = capacity;
		normal_seats = new boolean[row][col];
		top_seats = new boolean[topNum];
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public void sellOne(int num) {
		remain -= num;
	}
	public void setNormalSeat(int r,int c,boolean flag) {//第r排第c列选座/退座
		normal_seats[r][c] = flag;
	}
	public void setTopSeat(int loc,boolean flag) {
		top_seats[loc] = flag;
	}
	
	//获取单个属性值
	public int getCapacity() {
		return capacity;
	}
	public int getReamin() {
		return remain;
	}
	
	public boolean check(int i) {
		int num = 0,row = 0,col = 0;
		if(i >=0 && i < 10)
			return top_seats[i];		
		else if(i >= 10 && i< 30){
			num = i - 10;
			row = num/3;
			col = num%3;
		}
		else if(i >= 30 && i < 50) {
			num = i - 30;
			row = num/3;
			col = num%3 + 3;
		}
		return normal_seats[row][col];
	}

}
