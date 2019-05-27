package com.lq.dynamicManage;

import com.hlt.view.*;
import com.lq.client.*;
import com.lq.requests.Request;

import javax.swing.*;
import java.awt.*;

public class User {
	private JFrame curWindow = null;
	private Client client;
	public Client getClient() {
		return client;
	}
	public void toWindow(String name) {
		if(name.equals("login")) {
			curWindow = new UserLogin(this);
		}
		else if(name.equals("register")) {
			curWindow = new RegistFrame(this);
		}
		else if(name.equals("main menu")) {
			curWindow = new MainFrame(this);
		}
		else if(name.equals("find page")) {
			curWindow = new FindFrame(this);
		}
		else if(name.equals("order page")) {
			curWindow = new MyOrderFrame(this);
		}
		else if(name.equals("returnTicket page")) {
			curWindow = new ReturnTicketFrame(this);
		}
		
	}
	public void logIn() {//User LogIn
//		client = new Client("10.151.177.229",8888);
		client = new Client("localhost",8888);
		client.start();
		curWindow = new UserLogin(this);
	}
}
