package com.lq.model;

import java.io.*;

public class Announcement implements Serializable{
	private static final long serialVersionUID = 1L;
	private String type = "";//cancel or delay
	private String reason = "";
	private String flightID = "";
	private String recommand = "";
	public void setType(String type) {
		this.type = type;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setFlight(String flightID) {
		this.flightID = flightID;
	}
	public void setRecommand(String recommand) {
		this.recommand = recommand;
	}
	public String toString() {
		return String.format("�װ��Ĺ˿����ã�\r\n" + 
				"�ܱ�Ǹ��֪ͨ������������%s����������Ϊ%s����%s״̬��\r\n" + 
				"�Դ��������Ǹ�⣬�����Խ�����Ӧ���˿������\r\n" + 
				"ͬʱ���������Ƽ�����ͬ�𽵵���δ��������纽��--%s\r\n" + 
				"��������ٴ�Ϊ���������Ĳ���������ֿ�Եĵ�Ǹ",
				flightID,reason,type,recommand);
	}
}
