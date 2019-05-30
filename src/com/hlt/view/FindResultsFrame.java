package com.hlt.view;

import java.awt.*;

import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;

import com.lq.client.Client;
import com.lq.common.time.DateTime;
import com.lq.dynamicManage.User;
import com.lq.model.Flight;
import com.lq.requests.SortRequest;

public class FindResultsFrame extends JFrame implements ActionListener{
	User user;
	Client client;
	private Flight[] p;
	private JPanel pan_top = new JPanel();
	private JLabel lab_path = new JLabel();
	private JButton but_date = new JButton();
	private JButton but_sort = new JButton("排序");
	private  JButton but_return = new JButton("返回");
	private JButton but_calender = new JButton("日期");
	//固定内容
	private String src,des;
	private int year,month,day;
	//center面板
	JPanel[] pan = new JPanel[20];
	JLabel[]  lab_time = new JLabel[20];
	JLabel[]  lab_startTime = new JLabel[20];
	JLabel[]  lab_place = new JLabel[20];
	JLabel[]  lab_flightCom = new JLabel[20];
	JLabel[]  lab_price = new JLabel[20];
	JButton[] but_book = new JButton[20];//订票
	
	public FindResultsFrame(ArrayList<Flight> p,User user){
		this.user = user;
		this.client = user.getClient();
		this.p = p.toArray(new Flight[p.size()]);
		setTopPanel();
		setCenterPanel();
		setLayout(null);
		setLocation(400,400);
		setSize(400,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}
	
	public void initTopPanel() {
		src = p[0].getTakeoffPlace();
		des = p[0].getArrivePlace();
		DateTime date = p[0].getStartTime().getPlanTime();
		year = date.getYear();
		month = date.getMonth();
		day = date.getDay();
		lab_path.setText(String.format("%s--%s", src,des));
		but_date.setText(String.format("%02d-%2d %s", 
					month,day,p[0].getWeek()));
		
	}
	
	public void setComponentText(int i) {
		lab_place[i].setText(p[0].getTakeoffPlace()+"--"+p[0].getArrivePlace());
		//lab_price[i].setText();
		
	}
	public void setTopPanel() {
		
		initTopPanel();	
		pan_top.setLayout(null);
		add(pan_top);
		pan_top.setBounds(5, 5, 400, 100);
		pan_top.add(but_return);
		pan_top.add(but_calender);
		pan_top.add(but_sort);
		pan_top.add(but_date);
		pan_top.add(lab_path);
		
		but_return.setBounds(5, 5, 60, 30);
		but_return.addActionListener(this);
		lab_path.setBounds(130, 5, 100, 30);
		but_sort.setBounds(5, 50, 60, 30);
		but_sort.addActionListener(this);
		but_date.setBounds(130, 50, 100, 30);
		but_calender.setBounds(300, 50, 60, 30);
		but_calender.addActionListener(this);
		Font font = new Font("宋体",Font.BOLD,16);
		lab_path.setFont(font);
		but_date.setEnabled(false);
		but_date.setContentAreaFilled(false);
	
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == but_return) {
			dispose();
			user.toWindow("find page");
		}
		else if(e.getSource() == but_sort) {
			SortRequest req = new SortRequest(p,"price","asc");
			ArrayList<Flight> sortedResuts = 
					client.sendRequest(req );
		}
		else if(e.getSource() == but_calender) {
			
		}
		else {			
			for(int i =0;i<p.length;i++) {
				if(e.getSource()==but_book[i]) {
					new OrderFrame(p[i],user);
				}
			}
		}
	}
	public void setCenterPanel() {
	
	
		for(int i=0;i<p.length;i++) {  
			DateTime t = p[i].getStartTime().getPlanTime();
			lab_time[i] = new JLabel(String.format("%d年%d月%d日",
					t.getYear(),t.getMonth(),t.getDay()));	
			lab_startTime[i]  = new JLabel(String.format(
					"%02d:%02d出发",t.getHour(),t.getMinute()));
			lab_place[i]  = new JLabel(String.format("%s--%s", src,des));
			lab_flightCom[i]  = new JLabel(String.format("%s%s"
					, p[i].getCompany(),p[i].getId()));
			lab_price[i]  = new JLabel(String.format("¥%.2f", 
					p[i].getAdultprice()));
			but_book[i]  = new JButton("订票");
			but_book[i].addActionListener(this);
			pan[i]= new JPanel();
			add(pan[i]);
			pan[i].setBorder(BorderFactory.createLoweredBevelBorder());
			pan[i].setLayout(null);
			pan[i].add(but_book[i]);
			pan[i].add(lab_time[i]);
			pan[i].add(lab_startTime[i]);
			pan[i].add(lab_place[i]);
			pan[i].add(lab_flightCom[i]);
			pan[i].add(lab_price[i]);
			pan[i].setBounds(5, 120+100*i,400,100);
			but_book[i].setBounds(280,15,90,30);
			lab_time[i].setBounds(5, 5, 100, 40);
			lab_startTime[i].setBounds(100, 5, 100, 20);
			lab_place[i].setBounds(5, 30, 100, 40);
			lab_flightCom[i].setBounds(5, 55, 100, 40);
			lab_price[i].setBounds(300, 60, 70, 30);
			Font font = new Font("宋体",Font.BOLD,18);
			lab_place[i].setFont(font);
		}
	}
}
