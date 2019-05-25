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
		return String.format("亲爱的顾客您好：\r\n" + 
				"很抱歉地通知您，您乘坐的%s航班现在因为%s处于%s状态，\r\n" + 
				"对此我们深表歉意，您可以进行相应的退款操作，\r\n" + 
				"同时我们向您推荐从相同起降地且未延误的最早航班--%s\r\n" + 
				"最后，我们再次为给您带来的不便致以真挚对的道歉",
				flightID,reason,type,recommand);
	}
}
