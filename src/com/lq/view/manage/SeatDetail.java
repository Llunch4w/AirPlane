package com.lq.view.manage;

import javax.swing.*;

public class SeatDetail extends JFrame{
	public SeatDetail() {
		super("×ùÎ»±íÏêÇéÒ³");
	}
	public void showing(int width,int height) {
		//w:400;h:600
		setLocation(600,100);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}
