package com.lq.view.common;

import java.awt.FlowLayout;

import javax.swing.*;

public class PlaceSearchPanel extends JPanel{
	private JTextField takeoffField = new JTextField(8);
	private JTextField arriveField = new JTextField(8);
	public String takeoff,arrive;
	public PlaceSearchPanel(){
		setLayout(new FlowLayout());
		add(new JLabel("���ص�����"));
		add(new JLabel("��ɵ�:"));
		add(takeoffField);
		add(new JLabel("Ŀ�ĵ�:"));
		add(arriveField);
	}
	public void commit() {
		takeoff = takeoffField.getText();
		arrive = arriveField.getText();
	}
}
