package com.lq.server;

import java.util.*;

public class Server extends Thread{
	private int[] ports;
	private Map<Integer,ServerThread> dsn 
						= new HashMap<Integer,ServerThread>();
	
	public Server(int[] ports) {
		this.ports = ports;
	}
	
	public void run() {
		for(int port:ports) {		
			ServerThread thread = new ServerThread(port,this);
			thread.start();
			dsn.put(port, thread);
		}
	}
	public static void main(String[] args) {
		int ports[] = {8888,9999};
		Server server = new Server(ports);
		server.start();
	}
}

