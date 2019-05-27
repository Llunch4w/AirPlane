package com.lq.wr;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.lq.requests.*;
import com.lq.server.ServerThread;

public class ObjectReadThread extends Thread{
	private ServerThread server;
	private ObjectInputStream reader;
	public ObjectReadThread(ObjectInputStream reader) {
		this.reader = reader;
	}
	public void setServer(ServerThread server) {
		this.server = server;
	}
	public void run() {
		while(true) {
			try {
				if(isInterrupted()) {
					server.free();//释放目前端口
					break;
				}
				Object obj = reader.readObject();
				System.out.println(obj);
				server.getRequest(obj);
			}catch(Exception e) {
				e.printStackTrace();
				this.interrupt();
			}
		}
	}
	public Object read() {
		Object obj = null;
		try {
			obj = reader.readObject();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
