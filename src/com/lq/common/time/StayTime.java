package com.lq.common.time;

import java.sql.Time;

import com.lq.model.Flight;

public class StayTime {
	private int min;
	public StayTime(int h,int m) {
		min = h*60 + m;
	}
	public int getHours() {
		return min/60;
	}
	public int getMinutes() {
		return min%60;
	}
	public int getMin() {
		return min;
	}
	public void add(StayTime other) {
		min += other.getMin();
	}
	public StayTime(DateTime pre,DateTime cur) {
		int minutes_pre = pre.getDay()*24*60 + pre.getHour()*60 
						+ pre.getMinute();
		int minutes_cur = cur.getDay()*24*60 + cur.getHour()*60
						+ cur.getMinute();
		int minutes_sub = minutes_cur-minutes_pre;
		min = minutes_sub;
	}
	public String toString() {
		return String.format("%d小时%d分钟",getHours(),getMinutes());
	}
	
	
//	public StayTime(Time time) {
//		this.hour = time.getHours();
//		this.min = time.getMinutes();
//	}
//	public String getFormatTime() {
//		int days = this.hour/24;
//		int hours = this.hour%24;
//		String s = String.format("%02d %d:%d:00", 
//								days,hours,min);
//		return s;
//	}
//	public String toString() {
//		int days = this.hour/24;
//		int hours = this.hour%24;
//		String s = String.format("%d天%d小时%d分钟", days,hours,min);
//		return s;
//	}
}
