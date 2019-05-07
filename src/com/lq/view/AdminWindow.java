package com.lq.view;

import javax.swing.*;

public class AdminWindow extends JFrame{
	public AdminWindow(String s) {
		super(s);
	}
	public void showing(int width,int height) {
		setLocation(300,400);
		setSize(width,height);
		setVisible(true);
	}
}
