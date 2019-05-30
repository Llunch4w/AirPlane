package com.lq.client;

import java.io.*;
import java.net.*;
import javax.swing.*;

import com.lq.model.*;
import com.lq.requests.*;

public class ListenClient extends Thread{
	private String serverName;
	private int port;
	private Socket socket;
	private ObjectReadThread reader;
	public ListenClient(String server,int port) {
		serverName = server;
		this.port = port;
	}
	public void run() {
		try {		
			System.out.println("�������ӷ�������ַ��" + serverName + ":" + port);
			socket = new Socket(serverName,port);
			System.out.println(socket.getLocalPort());
			System.out.println("���ӳɹ�");
			reader = new ObjectReadThread(new ObjectInputStream(socket.getInputStream()));
			reader.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void showDelayMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}

class ObjectReadThread extends Thread{
	private ListenClient server;
	private ObjectInputStream reader;
	public ObjectReadThread(ObjectInputStream reader) {
		this.reader = reader;
	}
	public void setServer(ListenClient server) {
		this.server = server;
	}
	public void run() {
		while(true) {
			try {
				if(isInterrupted()) {
					break;
				}
				String obj = (String)reader.readObject();
				System.out.println(obj);
				server.showDelayMsg(obj);
			}catch(Exception e) {
				System.out.println("�Ͽ�д����");
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
