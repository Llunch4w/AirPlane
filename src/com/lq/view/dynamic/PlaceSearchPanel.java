package com.lq.view.dynamic;

import java.awt.FlowLayout;

import javax.swing.*;

class PlaceSearchPanel extends JPanel{
	private JTextField takeoffField = new JTextField(8);
	private JTextField arriveField = new JTextField(8);
	public String takeoff,arrive;
	public PlaceSearchPanel(){
		setLayout(new FlowLayout());
		add(new JLabel("按地点搜索"));
		add(new JLabel("起飞地:"));
		add(takeoffField);
		add(new JLabel("目的地:"));
		add(arriveField);
	}
	public void commit() {
		takeoff = takeoffField.getText();
		arrive = arriveField.getText();
	}
}
