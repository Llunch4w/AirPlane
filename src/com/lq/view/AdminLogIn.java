package com.lq.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.lq.dynamicManage.Admin;
import com.lq.sql.AdminSqlDriver;

public class AdminLogIn extends AdminWindow{
	private JTextField workID = new JTextField(15);
	private JPasswordField pwd = new JPasswordField(15);
	private JButton makeSure = new JButton("登录");
	Admin admin;
	public AdminLogIn(Admin admin) {
		super("管理员登录");
		setLayout(null);
		JLabel idLabel = new JLabel("账号："),pwdLabel = new JLabel("密码：");
		idLabel.setBounds(100,10,40,20);
		pwdLabel.setBounds(100,40,40,20);
		makeSure.setBounds(155,80,60,30);
		workID.setBounds(100 + 40,10,100,20);
		pwd.setBounds(100 + 40,40,100,20);
		add(idLabel);
		add(workID);
		add(pwdLabel);
		add(pwd);
		this.admin = admin;
		makeSure.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				check(workID.getText(),pwd.getText());
			}
		});
		add(makeSure);
		setSize(300,400);
		showing(400,200);
	}
	
	public void check(String wID,String pwd) {
		AdminSqlDriver sqlDriver = new AdminSqlDriver();
		if(sqlDriver.check(wID,pwd)) {
			System.out.println("登录成功");
			admin.toWindow("base manage");
		}
		else {
			System.out.println("账号名或密码错误");
			JOptionPane.showMessageDialog(null,"用户名或密码错误");
		}
	}
}
