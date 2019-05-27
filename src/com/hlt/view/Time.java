package com.hlt.view;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Time {
 Date date = new Date();//日期
 String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
 
 public String getDate(){
	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");//将Date格式转化为时间字符串
	 String time = dateFormat.format(date);
	 int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
	 return time+" "+ weekDays[w]; 
 }

 public void setDate() {
	 
 }
  public static  void main(String[] args) {		
	 System.out.println(new Time().getDate());
 }
}
