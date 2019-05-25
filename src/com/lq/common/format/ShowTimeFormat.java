package com.lq.common.format;

import com.lq.common.time.StayTime;

public class ShowTimeFormat {
	public String staytimeFormat(StayTime stayTime) {
		return String.format("%d–° ±%d∑÷÷”", stayTime.getHours(),stayTime.getMin());
	}
}
