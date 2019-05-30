package com.lq.view.manage;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import com.lq.common.time.DateTime;
import com.lq.common.time.StayTime;
import com.lq.model.Flight;
import com.lq.sql.FlightSaveDriver;
import com.lq.sql.PlaceSqlDriver;
import com.lq.exception.*;


public class AddPanel extends JPanel{
	private class FirstLayer extends JPanel{	//内部类	
		public JTextField idField = new JTextField(8);
		public JTextField companyField = new JTextField(6);
		public JTextField typeField = new JTextField(6);
		public JTextField buildField = new JTextField(3);
		String id,company,type,building;
		
		public FirstLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("航班号："));
			add(idField);
			add(new JLabel("航空公司："));
			add(companyField);
			add(new JLabel("航站楼："));
			add(buildField);
			add(new JLabel("机型："));
			add(typeField);
		}
		
		public void commit() {
			id = idField.getText();
			company = companyField.getText();
			type = typeField.getText();
			building = buildField.getText();
		}
		
		public void textClear() {
			idField.setText("");
			companyField.setText("");
			typeField.setText("");
			buildField.setText("");
		}
	}
	private FirstLayer firstLayer = new FirstLayer();//第一层面板
	
	private class SecondLayer extends JPanel implements ItemListener{		
		private ArrayList<String> places_canbe_choose;
		private JComboBox<String> startPlace = new JComboBox<String>();
		private JComboBox<String> midPlace = new JComboBox<String>();
		private JComboBox<String> endPlace = new JComboBox<String>();
		private JComboBox<String> weekPlace = new JComboBox<String>();
		public String start = null,mid = null,end = null,week = "周一";
		
		@Override
		public void itemStateChanged(ItemEvent e) {//实现ItemListen接口
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getSource() == startPlace)
					start = (String)startPlace.getSelectedItem();
				else if(e.getSource() == midPlace)
					mid = (String)midPlace.getSelectedItem();
				else if(e.getSource() == endPlace)
					end = (String)endPlace.getSelectedItem();
				else if(e.getSource() == weekPlace)
					week = (String)weekPlace.getSelectedItem();
			}
		}
		
		private void addToComboBox(JComboBox<String> box,ArrayList<String> array) {
			for(String s:array) {
				box.addItem(s);
			}
			start = "";
			mid = "";
			end = "";
		}
		
		private void init() {//下拉选项框的值先准备好,并添加监听事件
			PlaceSqlDriver tempSqlDriver = new PlaceSqlDriver();
			places_canbe_choose = tempSqlDriver.getPlaces();
			addToComboBox(startPlace,places_canbe_choose);
			addToComboBox(midPlace,places_canbe_choose);
			addToComboBox(endPlace,places_canbe_choose);
			String enumDays[] = {"周一","周二","周三","周四",
					"周五","周六","周日"};
			for(String s:enumDays) {
				weekPlace.addItem(s);
			}
			startPlace.addItemListener(this);
			midPlace.addItemListener(this);
			endPlace.addItemListener(this);
			weekPlace.addItemListener(this);
		}
		
		public SecondLayer() {
			init();
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("起飞地"));
			add(startPlace);
			add(new JLabel("经停地"));
			add(midPlace);
			add(new JLabel("目的地"));
			add(endPlace);
			add(new JLabel("星期:"));
			add(weekPlace);
		}
		
	}
	private SecondLayer secondLayer = new SecondLayer();//第二层
	
	private class TimeLayer extends JPanel{
		public int year,month,day,hour,min;
		public DateTime dateTime = null;
		public StayTime stayTime = null;
		public JTextField yearField = new JTextField(3);
		public JTextField monthField = new JTextField(3);
		public JTextField dayField = new JTextField(3);
		public JTextField hourField = new JTextField(3);
		public JTextField minField = new JTextField(3);		
		public TimeLayer(String title) {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel(title));
			add(yearField);
			add(new JLabel("年"));
			add(monthField);
			add(new JLabel("月"));
			add(dayField);
			add(new JLabel("日"));
			add(hourField);
			add(new JLabel("时"));
			add(minField);
			add(new JLabel("分"));
		}
		
		public void textClear() {
			yearField.setText("");
			monthField.setText("");
			dayField.setText("");
			hourField.setText("");
			minField.setText("");
		}
		
		public void commit() {
			try {				
				year = Integer.parseInt(yearField.getText());
			}catch(Exception e) {
				year = new Date().getYear();
			}
			try {				
				month = Integer.parseInt(monthField.getText());
			}catch(Exception e) {
				month = new Date().getMonth();
			}
			try {				
				day = Integer.parseInt(dayField.getText());
			}catch(Exception e) {
				day = new Date().getDay();
			}
			try {				
				hour = Integer.parseInt(hourField.getText());
			}catch(Exception e) {
				hour = 0;
			}
			try {				
				min = Integer.parseInt(minField.getText());
			}catch(Exception e) {
				min = 0;
			}
			dateTime = new DateTime(year,month,day,hour,min);
			stayTime = new StayTime(day*24+hour,min);
		}
	}
	private TimeLayer startTimeLayer = new TimeLayer("起飞时间");
	private TimeLayer endTimeLayer = new TimeLayer("抵达时间");
	private TimeLayer transArriveTimeLayer = new TimeLayer("中转到达时间");
	private TimeLayer transLeaveTimeLayer = new TimeLayer("中转离开时间");
	
	
	private class PriceLayer extends JPanel{
		public double kidPrice,adultPrice,topPrice;
		public JTextField kidText = new JTextField(5);
		public JTextField adultText = new JTextField(5);
		public JTextField topText = new JTextField(5);

		public PriceLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("成人票价："));
			add(adultText);
			add(new JLabel("儿童票价："));
			add(kidText);
			add(new JLabel("商务舱票价："));
			add(topText);
		}
		
		public void commit() {
			try {				
				kidPrice = Double.parseDouble(kidText.getText());
				adultPrice = Double.parseDouble(adultText.getText());
				topPrice = Double.parseDouble(topText.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"票价应该是数字！");
			}
		}
		
		public void textClear() {
			kidText.setText("");
			adultText.setText("");
			topText.setText("");
		}
	}
	private PriceLayer priceLayer = new PriceLayer();
	
	private class DiscountLayer extends JPanel{
		public double discount;
		public JTextField countText = new JTextField(3);
		public DiscountLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("折扣："));
			add(countText);
		}
		
		public void commit() {		
			try {				
				discount = Double.parseDouble(countText.getText());
				if(discount > 10 || discount < 0) {
					throw new NotRangeException();
				}
			}catch(NotRangeException e) {
				JOptionPane.showMessageDialog(null, "折扣不在合法范围内!");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "折扣输入不合法!");
			}
		}
		
		public void textClear() {
			countText.setText("");
		}
	}
	private DiscountLayer countLayer = new DiscountLayer();	
	private JButton makeSure = new JButton("确定");
	public AddPanel() {	
		setLayout(new FlowLayout());
		add(firstLayer);
		add(secondLayer);
		add(startTimeLayer);
		add(transArriveTimeLayer);
		add(transLeaveTimeLayer);
		add(endTimeLayer);
		add(priceLayer);
		add(countLayer);
		makeSure.addActionListener(new ActionListener() {//点击按钮后提交数据给数据库
			public void actionPerformed(ActionEvent e) {
				firstLayer.commit();
				//第二层加了监听所以不必commit
				startTimeLayer.commit();
				transArriveTimeLayer.commit();
				transLeaveTimeLayer.commit();
				endTimeLayer.commit();
				priceLayer.commit();
				countLayer.commit();
				Flight flight = new Flight(firstLayer.id,firstLayer.company,
						firstLayer.type,firstLayer.building);
				flight.setKidprice(priceLayer.kidPrice);
				flight.setAdultprice(priceLayer.adultPrice);
				flight.setTopprice(priceLayer.topPrice);
				flight.setStartTime(startTimeLayer.dateTime);
				flight.setArriveTime(endTimeLayer.dateTime);
				flight.setWeek(secondLayer.week);
				flight.setSrcPoint(secondLayer.start);
				flight.setDesPoint(secondLayer.end);
				flight.setDiscount(countLayer.discount);
				if(!secondLayer.mid.equals("")) {
					flight.setTransflag(true);
					flight.setTransPoint(secondLayer.mid);
					flight.setTransArriveTime(transArriveTimeLayer.dateTime);
					flight.setTransLeaveTime(transLeaveTimeLayer.dateTime);
				}
				try {
					new FlightSaveDriver().save(flight);	
					JOptionPane.showMessageDialog(null,"设置成功！");
					textClear();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"设置失败！");
				}
				
				System.out.println("航班号:" + firstLayer.id);
				System.out.println("航空公司:" + firstLayer.company);
				System.out.println("机型:" + firstLayer.type);
				System.out.println("起点:" + secondLayer.start);
				System.out.println("中转站:" + secondLayer.mid);
				System.out.println("终点:" + secondLayer.end);
				System.out.println("儿童票价:" + priceLayer.kidPrice);
				System.out.println("成人票价:" + priceLayer.adultPrice);
				System.out.println("折扣:" + countLayer.discount);
			}
		});
		add(makeSure);
	}
	
	private void textClear() {
		firstLayer.textClear();
		startTimeLayer.textClear();
		transArriveTimeLayer.textClear();
		transLeaveTimeLayer.textClear();
		endTimeLayer.textClear();
		priceLayer.textClear();
		countLayer.textClear();	
	}
}
