package com.lq.wr;

import java.io.*;

public class DataReadThread extends Thread{
	private DataInputStream reader;
	public DataReadThread(DataInputStream reader) {
		this.reader = reader;
	}
	public void run() {
		while(true) {
			try {
				String request = reader.readUTF();
				
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}
}
