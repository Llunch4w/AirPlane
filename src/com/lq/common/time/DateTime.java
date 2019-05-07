package com.lq.common.time;

public class DateTime {
	int year,month,day,hour,min;
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
	public String getFormatTime() {
		String s = String.format("\"%d-%02d-%02d %d:%d:00\"", 
								year,month,day,hour,min);
		return s;
	}
}
