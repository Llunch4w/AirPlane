package com.lq.view.common;

import javax.swing.*;

public class SurePanel extends JPanel{
	private JButton searchBtn = new JButton("ËÑË÷");
	public SurePanel(ListenPanel window) {
		searchBtn.addActionListener(window);
		searchBtn.setSize(10,4);
		add(searchBtn);
	}
}