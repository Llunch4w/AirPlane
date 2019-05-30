package com.lq.model;

import java.io.Serializable;

import com.lq.common.time.DateTime;
import com.lq.common.time.StayTime;

public class PointTime implements Serializable{//航线途经地点的时间
	public DateTime planTime;//计划时间
	public DateTime realTime;//实际时间
	public StayTime stayTime;//停留时间(延误时间)
	public boolean isDelayed = false;//是否延误
	public String delayReason = "";
	public PointTime(DateTime t) {
		planTime = new DateTime(t);
		realTime = new DateTime(t);//默认是没有延误的
		stayTime = new StayTime(0,0);
		isDelayed = false;
	}

	// 动态操作
	public void delay(StayTime stayTime) {
		this.stayTime.add(stayTime);
		isDelayed = true;
		realTime = planTime.add(stayTime);
	}
	public void setDelayReason(String s) {
		delayReason = s;
	}
	
	//获取属性值
	public DateTime getPlanTime() {
		return planTime;
	}
	public DateTime getRealTime() {
		return realTime;
	}
	public StayTime getStayTime() {
		return stayTime;
	}
}
