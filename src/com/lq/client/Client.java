package com.lq.client;

import java.io.*;
import java.net.*;

import com.hlt.model.UserMe;
import com.lq.requests.Request;
import com.lq.wr.*;

public class Client extends Thread{
	private String serverName;
	private int port;
	private Socket socket;
	private ObjectReadThread reader;
	private ObjectWriteMan writer;
	public Client(String server,int port) {
		serverName = server;
		this.port = port;
	}
	public void run() {
		try {		
			System.out.println("尝试连接服务器地址：" + serverName + ":" + port);
			socket = new Socket(serverName,port);
			System.out.println(socket.getLocalPort());
			System.out.println("连接成功");
			reader = new ObjectReadThread(new ObjectInputStream(socket.getInputStream()));
			writer = new ObjectWriteMan(new ObjectOutputStream(socket.getOutputStream()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean sendYONq(Request req) {//yes or no question
		System.out.println(req);
		writer.write(req);
		Object res = reader.read();
		if(res instanceof Boolean) {
			System.out.println(res);
			return (boolean)res;
		}
		else {
			System.out.println("login socket error");
			return false;
		}
	}
	public void sendNewUser(UserMe user) {
		writer.write(user);
	}
}