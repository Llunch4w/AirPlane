package com.lq.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.lq.dynamicManage.Admin;

public class AdminLogIn extends JFrame{
	private JTextField workID = new JTextField(30);
	private JPasswordField pwd = new JPasswordField(30);
	private JButton makeSure = new JButton("ȷ��");
	public AdminLogIn(Admin admin) {
		super("����Ա��¼");
		setLayout(new FlowLayout());
		add(new JLabel("�˺ţ�"));
		add(workID);
		add(new JLabel("���룺"));
		add(pwd);
		makeSure.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				admin.check(workID.getText(),pwd.getText());
			}
		});
		add(makeSure);
		setSize(400,200);
		setLocation(300,400);
		setVisible(true);
	}
}
