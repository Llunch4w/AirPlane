package com.lq.wr;

import java.io.*;

public class DataWriteMan {
	private DataOutputStream writer;
	public DataWriteMan(DataOutputStream writer) {
		this.writer = writer;
	}
	public void write(String s) {
		try {
			writer.writeUTF(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
