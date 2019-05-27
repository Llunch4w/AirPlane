package com.lq.view.common;

import java.awt.FlowLayout;

import javax.swing.*;

public class IdSearchPanel extends JPanel{
	private JTextField idField = new JTextField(10);
	public String id;
	public IdSearchPanel() {
		setLayout(new FlowLayout());
		add(new JLabel("按航班号搜索"));
		add(idField);
	}	
	public void commit() {
		id = idField.getText();
	}
	public void addNoResult() {
		JOptionPane.showMessageDialog(null,"没有符合条件的航班!");
	}
}	