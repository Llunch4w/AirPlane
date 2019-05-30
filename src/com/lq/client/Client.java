package com.lq.client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.hlt.model.*;
import com.lq.model.*;
import com.lq.requests.*;
import com.lq.wr.*;

public class Client extends Thread{
	private String serverName;
	private int port;
	private Socket socket;
	private ObjectReadThread reader;
	private ObjectWriteMan writer;
	public Client(String server,int port) {
		serverName = server;
		this.port = port;
	}
	public void run() {
		try {		
			System.out.println("尝试连接服务器地址：" + serverName + ":" + port);
			socket = new Socket(serverName,port);
			System.out.println(socket.getLocalPort());
			System.out.println("连接成功");
			reader = new ObjectReadThread(new ObjectInputStream(socket.getInputStream()));
			writer = new ObjectWriteMan(new ObjectOutputStream(socket.getOutputStream()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean sendYONq(Request req) {//yes or no question
		System.out.println(req);
		writer.write(req);
		Object res = reader.read();
		if(res instanceof Boolean) {
			System.out.println(res);
			return (boolean)res;
		}
		else {
			System.out.println("login socket error");
			return false;
		}
	}
	public void sendNewUser(UserMe user) {
		writer.write(user);
	}
	public ArrayList<Flight> sendRequest(SearchByPlaceRequest req) {
		writer.write(req);
		ArrayList<Flight> flights = new ArrayList<Flight>();
		int size = (int)reader.read();
		for(int i = 0;i < size;i++) {
			Flight f = (Flight)reader.read();
			flights.add(f);
		}
		return flights;
	}
	public void sendRequest(Request req) {
		writer.write(req);
	}
	public Flight sendRequest(SearchByIdRequest req) {
		writer.write(req);
		Flight flight = (Flight)reader.read();
		return flight;
	}
	public ArrayList<Flight> sendRequest(SortRequest req){
		writer.write(req);
		ArrayList<Flight> flights = (ArrayList<Flight>)reader.read();
		return flights;
	}
	public UserMe sendRequest(UserDetailRequest req) {
		writer.write(req);
		UserMe detail = (UserMe)reader.read();
		return detail;
	}
	public ArrayList<Order> sendRequest(MyOrderRequest req){
		writer.write(req);
		ArrayList<Order> orders = (ArrayList<Order>)reader.read();
		return orders;
	}	
	public void sendOrder(Order order) {
		writer.write(order);
	}
}