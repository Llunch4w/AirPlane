package com.hlt.view;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Time {
 Date date = new Date();//����
 String[] weekDays = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
 
 public String getDate(){
	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");//��Date��ʽת��Ϊʱ���ַ���
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
