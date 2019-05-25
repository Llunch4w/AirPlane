package com.lq.model;

public class FlightContainer {
	public boolean[][] seats;
	public int row,col;
	public int capacity;//总容量
	public int remain;//剩余
	public FlightContainer(int _row) {
		row = _row;
		col = 10;
		capacity = row*col;
		remain = capacity;
		seats = new boolean[row][col];
	}
	//设置单个属性值
	public void setRow(int row) {
		this.row = row;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setRemain(int reamin) {
		this.remain = remain;
	}
	public void setSeat(int r,int c,boolean flag) {//第r排第c列选座/退座
		seats[r-1][c-1] = flag;
	}
	
	//获取单个属性值
	public int getCapacity() {
		return capacity;
	}
	public int getRow() {
		return row;
	}
	public int getReamin() {
		return remain;
	}
}
