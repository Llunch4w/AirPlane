package com.lq.common.time;

import java.sql.Timestamp;
import java.util.Arrays;

public class DateTime {
	private int year,month,day,hour,min;
	
	//构造函数
	public DateTime(int y,int m,int d,int h,int mi) {
		year = y;
		month = m;
		day = d;
		hour = h;
		min = mi;
	}
	public DateTime(DateTime t) {
		this.year = t.year;
		this.month = t.month;
		this.day = t.day;
		this.hour = t.hour;
		this.min = t.min;
	}
	public DateTime(Timestamp value) {
		int carry = 0;
		this.min = value.getMinutes();
		this.hour = (value.getHours()+16)%24;
		carry = (value.getHours()+16)/24;
		int months[] = {1,3,5,7,8,10,12};
		int mode;
		if(Arrays.asList(months).contains(this.month)) {
			mode = 31;
		}
		else if(this.month == 2) {
			mode = 28;
		}
		else {
			mode = 30;
		}
		this.day = (value.getDay()+4+carry)%mode;
		if(this.day == 0)
			this.day++;
		carry = (value.getDay()+4+carry)/mode;
		this.month = value.getMonth() + 1;
		if(carry > 0) {
			this.month = (this.month+carry)%12;
			carry = (this.month + carry)/12;
			if(this.month == 0) {
				this.month = 1;
			}
		}
		this.year = value.getYear() + carry + 1900;
	}
	
	//获取单个属性值
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return min;
	}
	
//	public String getFormatTime() {
//		String s = String.format("\"%d-%02d-%02d %d:%d:00\"", 
//								year,month,day,hour,min);
//		return s;
//	}
//	public String toString() {
//		String s = String.format("%d年%d月%d日%d时%d分",
//				year,month,day,hour,min);
//		return s;
//	}
	
	public DateTime add(StayTime stayTime) {
		int carry = 0;
		int sum_min = stayTime.getMinutes() + this.min;
		int new_min = sum_min%60;
		carry = sum_min/60;
		int sum_hour = stayTime.getHours() + this.hour + carry;
		int new_hour = sum_hour%24;
		carry = sum_hour/24;
		int sum_day = this.day + carry;
		int months[] = {1,3,5,7,8,10,12};
		int mode;
		if(Arrays.asList(months).contains(this.month)) {
			mode = 31;
		}
		else if(this.month == 2) {
			mode = 28;
		}
		else {
			mode = 30;
		}
		int new_day = sum_day%mode;
		if(new_day == 0)
			new_day++;
		carry = sum_day/mode;
		int new_month = this.month;
		int new_year = this.year;
		if(carry > 0) {
			new_month = (this.month + carry)%12;
			carry = (this.month + carry)/12;
			if(new_month == 0) {
				new_month = 1;
			}
			new_year = this.year + carry;
		}
		return new DateTime(new_year,new_month,new_day,new_hour,new_min);
	}
	
	public String toString() {
		return String.format("%d-%02d-%02d %02d:%02d",year,month,day,hour,min );
	}
}
