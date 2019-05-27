package com.lq.wr;

import java.io.*;

import com.lq.server.ServerThread;

public class ObjectWriteMan{
	private ServerThread server;
	private ObjectOutputStream writer;
	public ObjectWriteMan(ObjectOutputStream writer) {
		this.writer = writer;
	}
	public void setServer(ServerThread server) {
		this.server = server;
	}
	public void write(Object obj) {
		try {
			writer.writeObject(obj);
		} catch (IOException e) {
			System.out.println("eeeee");
			e.printStackTrace();
		}
	}
}
