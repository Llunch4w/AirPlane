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
			System.out.println("��¼�ɹ�");
			loginWindow.setVisible(false);
			manageWindow = new AdminManage(this);
		}
		else {
			System.out.println("�˺������������");
			JOptionPane.showMessageDialog(null,"�û������������");
		}
	}
}
