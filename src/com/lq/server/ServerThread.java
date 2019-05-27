package com.lq.server;

import java.io.*;
import java.net.*;

import com.hlt.model.UserMe;
import com.lq.requests.*;
import com.lq.sql.*;
import com.lq.wr.*;

public class ServerThread extends Thread{
	Server server;
	private int port;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectReadThread reader;
	private ObjectWriteMan writer;
	public ServerThread(int port,Server server) {
		this.port = port;
		this.server = server;
	}
	public void run() {
		while(true) {
			if(socket == null) {				
				try {					
					serverSocket = new ServerSocket(port);
					serverSocket.setSoTimeout(1000000);
					System.out.println("正在监听" + "端口" + port );
					socket = serverSocket.accept();
					System.out.println("端口" + port + "与客户机" + 
							socket.getRemoteSocketAddress() + "连接成功");
					ObjectOutputStream out = 
							new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = 
							new ObjectInputStream(socket.getInputStream());
					reader = new ObjectReadThread(in);
					reader.setServer(this);
					writer = new ObjectWriteMan(out);
					reader.setServer(this);
					reader.start();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void getRequest(Object req) {
		if(req instanceof LoginRequest) {
			LoginRequest r = (LoginRequest)req;
			Boolean flag = 
					new UserSqlDriver().check(r.getID(), r.getPwd());
			writer.write(flag);
			System.out.println("flag:"+flag);
		}
		else if(req instanceof CheckRepeatId) {
			CheckRepeatId r = (CheckRepeatId)req;
			Boolean flag = 
					new UserSqlDriver().check(r.getID());
			writer.write(flag);
		}
		else if(req instanceof UserMe) {
			new UserSaveDriver().save((UserMe)req);
		}
	}
	public void free() {
		try {			
			socket.close();
			serverSocket.close();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		socket = null;
		run();
	}
}

