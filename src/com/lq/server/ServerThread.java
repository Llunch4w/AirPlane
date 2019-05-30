package com.lq.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.hlt.model.*;
import com.lq.common.time.DateTime;
import com.lq.model.*;
import com.lq.requests.*;
import com.lq.sql.*;
import com.lq.wr.*;
import com.lq.sort.*;

public class ServerThread extends Thread{
	String connectUser;
	Server server;
	private int port;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectReadThread reader;
	private ObjectWriteMan writer;
	public ServerThread(int port,Server server) {
		this.port = port;
		this.server = server;
	}
	public void run() {
		while(true) {
			if(socket == null) {				
				try {					
					serverSocket = new ServerSocket(port);
//					serverSocket.setSoTimeout(1000000);
					System.out.println("正在监听" + "端口" + port );
					socket = serverSocket.accept();
					System.out.println("端口" + port + "与客户机" + 
							socket.getRemoteSocketAddress() + "连接成功");
					ObjectOutputStream out = 
							new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = 
							new ObjectInputStream(socket.getInputStream());
					reader = new ObjectReadThread(in);
					reader.setServer(this);
					writer = new ObjectWriteMan(out);
					reader.setServer(this);
					reader.start();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void getRequest(Object req) {
		if(req instanceof LoginRequest) {
			LoginRequest r = (LoginRequest)req;
			Boolean flag = 
					new UserSqlDriver().check(r.getID(), r.getPwd());
			writer.write(flag);
			System.out.println("flag:"+flag);
			if(flag) {
				connectUser = r.getID();
				server.addConnect(r.getID(),this);
			}
		}
		else if(req instanceof CheckRepeatId) {
			CheckRepeatId r = (CheckRepeatId)req;
			Boolean flag = 
					new UserSqlDriver().check(r.getID());
			writer.write(flag);
		}
		else if(req instanceof DelayRequest) {
			DelayRequest r = (DelayRequest)req;
			if(server.isConnected(r.getUser())) {
				server.send(r.getUser(),r.getMsg());
				writer.write(true);
			}
			else
				writer.write(false);
		}
		else if(req instanceof UserMe) {
			UserMe r = (UserMe)req;
			new UserSaveDriver().save(r);
			if(!server.isConnected(r.getID())) {
				connectUser = r.getID();
				server.addConnect(r.getID(),this);
			}
		}
		else if(req instanceof SearchByPlaceRequest) {
			SearchByPlaceRequest r = (SearchByPlaceRequest)req;
			String src = r.getSrc();
			String des = r.getDes();
			ArrayList<Flight> results = new ArrayList<Flight>();
			FlightSearchDriver driver = new FlightSearchDriver();
			ArrayList<Flight> flights = driver.searchByPlace_base(src, des);
			if(r.getDate() == null) {
				writer.write(flights.size());
				for(Flight flight:flights) {
					driver.addDetail(flight);
					writer.write(flight);
				}
			}
			else {				
				for(Flight flight:flights) {
					DateTime t1 = flight.getStartTime().getPlanTime();
					Date t2 = r.getDate();
					if(t1.getMonth()==t2.getMonth() &&
						t1.getDay()==t2.getDay()) {
						results.add(flight);
					}
				}
				writer.write(flights.size());
				for(Flight flight:results) {
					driver.addDetail(flight);
					writer.write(flight);
				}
			}
		}
		else if(req instanceof SearchByIdRequest) {
			SearchByIdRequest r = (SearchByIdRequest)req;
			String flightID = r.getFlightID();
			Flight flight = new FlightSearchDriver().searchById_base(flightID);
			writer.write(flight);
		}
		else if(req instanceof SortRequest) {
			SortRequest r = (SortRequest)req;
			ArrayList<Flight> flights = 
					new ArrayList<Flight>(Arrays.asList(r.getFlight()));
			String type = r.getType();
			String method = r.getMethod();
			if(type.equals("price")) {
				new ByPrice().sort(flights, method);
			}
			else if(type.equals("flying time")) {
				new ByFlyingTime().sort(flights, method);
			}
			else if(type.equals("remain tickets")) {
				new ByRemainTicket().sort(flights, method);
			}
			writer.write(flights);
		}
		else if(req instanceof UserDetailRequest) {
			UserDetailRequest r = (UserDetailRequest)req;
			String id = r.getID();
			UserMe me = new UserSearchDriver().search(id);
			writer.write(me);
		}
		else if(req instanceof MyOrderRequest) {
			MyOrderRequest r = (MyOrderRequest)req;
			ArrayList<Order> orders = new OrderSearchDriver().search(r.getID());
			writer.write(orders);	
		}
		else if(req instanceof Order) {
			Order order = (Order)req;	
			new OrderSaveDriver().save(order);
		}
	}
	public void free() {
		try {	
			socket.close();
			serverSocket.close();
			server.removeConnect(connectUser, this);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		socket = null;
		run();
	}
	public void write(Object obj) {
		writer.write(obj);
	}
}

