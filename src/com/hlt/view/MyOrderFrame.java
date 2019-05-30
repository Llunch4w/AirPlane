package com.hlt.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import com.hlt.model.Order;
import com.lq.client.Client;
import com.lq.common.time.DateTime;
import com.lq.dynamicManage.User;
import com.lq.model.Flight;
import com.lq.requests.MyOrderRequest;
import com.lq.requests.SearchByIdRequest;
import com.lq.requests.SearchByPlaceRequest;

public class MyOrderFrame extends JFrame implements ActionListener{
	private User user;
	private Client client;
	private JLabel  lab_order = new JLabel("机票订单");
	private JButton but_return = new JButton("返回");
	private JPanel pan_north = new JPanel();
	private JPanel pan_center = new JPanel();
	private JScrollPane scrollPane = new JScrollPane(pan_center);
	
	private JButton but_returnTicket = new JButton("退票");
	private JButton but_specific = new JButton("查看详情");
	private JLabel  lab_time = new JLabel("2019年6月21日");
	private JLabel  lab_startTime = new JLabel("10:25出发");
	private JLabel  lab_place = new JLabel("长春-郑州");
	private JLabel  lab_flightCom = new JLabel("东方航空G1422");
	private JLabel  lab_price = new JLabel("¥510");

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == but_returnTicket) {
			new ReturnTicketFrame(user);
		}
		else if(e.getSource() == but_specific) {
			
		}
		else if(e.getSource() == but_return) {
			dispose();
			user.toWindow("main menu");
		}
	}
	
	public MyOrderFrame(User user){
		this.user = user;
		client = user.getClient();
		setTitle("订单");
		add(pan_north,BorderLayout.NORTH);
//		add(pan_center,BorderLayout.CENTER);
		add(scrollPane,BorderLayout.CENTER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(400,400);
//		pan_center.setPreferredSize(new Dimension(300,10000));

		add(but_return,BorderLayout.SOUTH);
		but_return.addActionListener(this);
		setSize(400,600);
		setLocation(600,400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		setNorthPanel();
		setCenterPanel();
		
		
	}
	public void setNorthPanel() {        
		    pan_north.add(lab_order);
			lab_order.setBounds(50, 5, 60, 30);    
	}
	public void setCenterPanel() {
		but_returnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==but_returnTicket) {

					Object [] options = {"退票","返回"};
					int m =JOptionPane.showOptionDialog(null, "确定退票吗", "退票", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					
					
				}
			}
		});
		MyOrderRequest req = new MyOrderRequest(user.getID());
		System.out.println("user:" + user.getID());
		ArrayList<Order> orders = client.sendRequest(req);
		setOrderPanel(orders);
	}
	
	public void setOrderPanel(ArrayList<Order> orders) {//参数为顾客的订单数
		pan_center.setPreferredSize(new Dimension(300,100*orders.size()));
		for(int i = 0;i < orders.size();i++) {
			JPanel pan_order = new JPanel();
			pan_center.setLayout(null);
			pan_center.add(pan_order);
			
			SearchByIdRequest req = new SearchByIdRequest(
					orders.get(i).getPlaneID());
			Flight flight = client.sendRequest(req);
			
			//根据order赋值
			JButton but_returnTicket = new JButton("退票");
			JButton but_specific = new JButton("查看详情");
			but_returnTicket.addActionListener(this);
			but_specific.addActionListener(this);
			
			DateTime t = flight.getStartTime().getPlanTime();
			JLabel  lab_time = new JLabel(String.format("%d-%02d-%2d", 
					t.getYear(),t.getMonth(),t.getDay()));
			JLabel  lab_startTime = new JLabel(String.format(
					"%d:%d出发", t.getHour(),t.getMinute()));
			JLabel  lab_place = new JLabel(
					String.format("%s--%s", flight.getTakeoffPlace(),
							flight.getArrivePlace()));		
			JLabel  lab_flightCom = new JLabel(String.format("%s%s"
					, flight.getCompany(),flight.getId()));		
			JLabel  lab_price = new JLabel(String.format("¥%.2f", 
					flight.getAdultprice()));
			
			pan_order.setBorder(BorderFactory.createLoweredBevelBorder());//设置边框凹陷
		    pan_order.setLayout(null);
			pan_order.setBounds(5,5+100*i,375,100);//设置面板大小
			
			pan_order.add(but_returnTicket);
			pan_order.add(but_specific);
			pan_order.add(lab_time);
			pan_order.add(lab_startTime);
			pan_order.add(lab_place);
			pan_order.add(lab_flightCom);
			pan_order.add(lab_price);
			//设置位置大小
			but_returnTicket.setBounds(200, 5, 70, 30);
			but_specific.setBounds(280,5,90,30);
			lab_time.setBounds(5, 5, 100, 40);
			lab_startTime.setBounds(100, 5, 100, 40);
			lab_place.setBounds(5, 30, 100, 40);
			lab_flightCom.setBounds(5, 55, 100, 40);
			lab_price.setBounds(300, 60, 70, 30);
			//设置字体
			Font font = new Font("宋体",Font.BOLD,18);
			lab_place.setFont(font);
			lab_price.setFont(font);
		}
	}
	
	public void setLab_time(String time) {
		this.lab_time.setText (time);
	}
	public JLabel getLab_place() {
		return lab_place;
	}
	public void setLab_place(String startPlace,String endPlace) {
		this.lab_place.setText(startPlace+"--"+endPlace);
	}
//	public static void main(String[] args) {
//		new MyOrderFrame();	
//	}
	public void setLab_startTime(String startTime) {
		this.lab_startTime.setText(startTime+"出发");
	}
	public JLabel getLab_flightCom() {
		return lab_flightCom;
	}
	public void setLab_flightCom(JLabel lab_flightCom) {
		this.lab_flightCom = lab_flightCom;
	}
	public static void main(String[] args) {
		new MyOrderFrame(new User());
	}
}