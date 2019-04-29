package com.lq.dynamicManage;
import com.lq.view.*;
import com.lq.sql.*;

public class Admin {
	private String workID;
	private String name;
	private String password;
	private AdminLogIn loginWindow;
	private AdminSqlDriver sqlDriver = new AdminSqlDriver();
	public void logIn() {//Admin LogIn
		loginWindow = new AdminLogIn(this);
	}
	public void check(String wID,String pwd) {
		sqlDriver.set_db_url("admin");
		if(sqlDriver.check(wID,pwd)) {
			System.out.println("登录成功");
		}
		else {
			System.out.println("账号名或密码错误");
		}
	}
}
