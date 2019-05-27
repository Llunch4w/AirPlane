package com.hlt.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

class TimeThread implements Runnable {
    JLabel time = new JLabel(" ");
	SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	TimeThread(JLabel time){
		this.time = time;
	}
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			time.setText(simFormat.format(new Date()) + "  " + weekDays[w]);
		}
	}

}