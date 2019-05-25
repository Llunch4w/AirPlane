package com.lq.view;

import javax.swing.*;

public class AdminWindow extends JFrame{
	public AdminWindow(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void showing(int width,int height) {
		setLocation(300,400);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}
