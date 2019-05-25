package com.lq.wr;

import java.io.*;

public class ObjectWriteMan {
	private ObjectOutputStream writer;
	public ObjectWriteMan(ObjectOutputStream writer) {
		this.writer = writer;
	}
	public void write(Object obj) {
		try {
			writer.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
