package com.lq.wr;

import java.io.ObjectInputStream;


public class ObjectReadThread extends Thread{
	private ObjectInputStream reader;
	public ObjectReadThread(ObjectInputStream reader) {
		this.reader = reader;
	}
	public void run() {
		while(true) {
			try {
				if(isInterrupted()) {
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
				this.interrupt();
			}
		}
	}
}
