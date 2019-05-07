package com.lq.dynamicManage;

import com.lq.view.*;
import com.lq.method.*;
import com.lq.sql.*;

import javax.swing.JOptionPane;

public class Admin {
	private String workID;
	private String name;
	private String password;
	private AdminLogIn loginWindow;
	private AdminManage manageWindow;
	private AdminSqlDriver sqlDriver = new AdminSqlDriver();
	private AdminMethod method;
	public void logIn() {//Admin LogIn
		loginWindow = new AdminLogIn(this);
	}
	public void check(String wID,String pwd) {
		if(sqlDriver.check(wID,pwd)) {
			System.out.println("登录成功");
			loginWindow.setVisible(false);
			manageWindow = new AdminManage(this);
		}
		else {
			System.out.println("账号名或密码错误");
			JOptionPane.showMessageDialog(null,"用户名或密码错误");
		}
	}
}
