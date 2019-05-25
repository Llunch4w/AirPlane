package com.lq.dynamicManage;

import com.lq.view.*;
import com.lq.view.dynamic.DynamicManage;
import com.lq.view.manage.AdminManage;
import com.lq.method.*;
import com.lq.sql.*;

import javax.swing.JOptionPane;

public class Admin {
	private String workID;
	private String name;
	private String password;
	private AdminWindow curWindow = null;
//	private AdminMethod method;//优化备用
	public void toWindow(String name) {
		if(name.equals("login")) {
			curWindow.close();
			curWindow = new AdminLogIn(this);
		}
		else if(name.equals("base manage")) {
			curWindow.close();
			curWindow = new AdminManage(this);
		}
		else if(name.equals("dynamic manage")) {
			curWindow.close();
			curWindow = new DynamicManage(this);
		}
	}
	public void logIn() {//Admin LogIn
		curWindow = new AdminLogIn(this);
	}
	
}
