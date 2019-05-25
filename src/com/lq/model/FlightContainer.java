package com.lq.model;

public class FlightContainer {
	public boolean[][] seats;
	public int row,col;
	public int capacity;//������
	public int remain;//ʣ��
	public FlightContainer(int _row) {
		row = _row;
		col = 10;
		capacity = row*col;
		remain = capacity;
		seats = new boolean[row][col];
	}
	//���õ�������ֵ
	public void setRow(int row) {
		this.row = row;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setRemain(int reamin) {
		this.remain = remain;
	}
	public void setSeat(int r,int c,boolean flag) {//��r�ŵ�c��ѡ��/����
		seats[r-1][c-1] = flag;
	}
	
	//��ȡ��������ֵ
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
