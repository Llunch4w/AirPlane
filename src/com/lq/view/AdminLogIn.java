package com.lq.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.lq.dynamicManage.Admin;

public class AdminLogIn extends JFrame{
	private JTextField workID = new JTextField(30);
	private JPasswordField pwd = new JPasswordField(30);
	private JButton makeSure = new JButton("确定");
	public AdminLogIn(Admin admin) {
		super("管理员登录");
		setLayout(new FlowLayout());
		add(new JLabel("账号："));
		add(workID);
		add(new JLabel("密码："));
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
