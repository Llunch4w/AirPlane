package com.lq.server;

import java.net.*;
import java.io.*;
import com.lq.wr.*;

public class Server extends Thread{
	int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataReadThread reader;
	private ObjectWriteMan writer;
	public Server(int port) {
		this.port = port;
	}
	public void run() {
		while(true) {
			try {
				if(isInterrupted()) {
					break;
				}
				serverSocket = new ServerSocket(port);
				System.out.println("正在等待连接");
				socket = serverSocket.accept();
				System.out.println("连接成功");
				reader = new DataReadThread(new DataInputStream(socket.getInputStream()));
				writer = new ObjectWriteMan(new ObjectOutputStream(socket.getOutputStream()));
				reader.start();
			}catch(Exception e) {
				e.printStackTrace();
				try {			
					socket.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				this.interrupt();
			}
		}
	}
	public void send(Object obj) {
		writer.write(obj);
	}
}
