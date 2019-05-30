package com.lq.model;

import java.io.Serializable;

import com.lq.common.time.DateTime;
import com.lq.common.time.StayTime;

public class PointTime implements Serializable{//����;���ص��ʱ��
	public DateTime planTime;//�ƻ�ʱ��
	public DateTime realTime;//ʵ��ʱ��
	public StayTime stayTime;//ͣ��ʱ��(����ʱ��)
	public boolean isDelayed = false;//�Ƿ�����
	public String delayReason = "";
	public PointTime(DateTime t) {
		planTime = new DateTime(t);
		realTime = new DateTime(t);//Ĭ����û�������
		stayTime = new StayTime(0,0);
		isDelayed = false;
	}

	// ��̬����
	public void delay(StayTime stayTime) {
		this.stayTime.add(stayTime);
		isDelayed = true;
		realTime = planTime.add(stayTime);
	}
	public void setDelayReason(String s) {
		delayReason = s;
	}
	
	//��ȡ����ֵ
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
