package com.lq.server;

import java.util.*;

import com.lq.requests.DelayRequest;

public class Server extends Thread{
	private int[] ports;
	private Map<String,ServerThread> dsn 
						= new HashMap<String,ServerThread>();
	private Map<Integer,ServerThread> dsn_port
						= new HashMap<Integer,ServerThread>();
	public Server(int[] ports) {
		this.ports = ports;
	}
	
	public void run() {
		for(int port:ports) {		
			ServerThread thread = new ServerThread(port,this);
			dsn_port.put(port,thread);
			thread.start();
		}
	}
	
	public void addConnect(String userID,ServerThread thread) {
		dsn.put(userID, thread);
	}
	public void removeConnect(String userID,ServerThread thread) {
		dsn.remove(userID,thread);
	}
	public boolean isConnected(String userID) {
		if(dsn.containsKey(userID))
			return true;
		else
			return false;
	}
	
	public void send(String userID,String msg) {
		ServerThread thread = dsn.get(userID);
		thread.write(msg);
	}
	
	public static void main(String[] args) {
		int ports[] = {8887,8888,9999};
		Server server = new Server(ports);
		server.start();
	}
}

