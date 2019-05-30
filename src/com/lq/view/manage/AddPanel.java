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
	private class FirstLayer extends JPanel{	//�ڲ���	
		public JTextField idField = new JTextField(8);
		public JTextField companyField = new JTextField(6);
		public JTextField typeField = new JTextField(6);
		public JTextField buildField = new JTextField(3);
		String id,company,type,building;
		
		public FirstLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("����ţ�"));
			add(idField);
			add(new JLabel("���չ�˾��"));
			add(companyField);
			add(new JLabel("��վ¥��"));
			add(buildField);
			add(new JLabel("���ͣ�"));
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
	private FirstLayer firstLayer = new FirstLayer();//��һ�����
	
	private class SecondLayer extends JPanel implements ItemListener{		
		private ArrayList<String> places_canbe_choose;
		private JComboBox<String> startPlace = new JComboBox<String>();
		private JComboBox<String> midPlace = new JComboBox<String>();
		private JComboBox<String> endPlace = new JComboBox<String>();
		private JComboBox<String> weekPlace = new JComboBox<String>();
		public String start = null,mid = null,end = null,week = "��һ";
		
		@Override
		public void itemStateChanged(ItemEvent e) {//ʵ��ItemListen�ӿ�
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
		
		private void init() {//����ѡ����ֵ��׼����,����Ӽ����¼�
			PlaceSqlDriver tempSqlDriver = new PlaceSqlDriver();
			places_canbe_choose = tempSqlDriver.getPlaces();
			addToComboBox(startPlace,places_canbe_choose);
			addToComboBox(midPlace,places_canbe_choose);
			addToComboBox(endPlace,places_canbe_choose);
			String enumDays[] = {"��һ","�ܶ�","����","����",
					"����","����","����"};
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
			add(new JLabel("��ɵ�"));
			add(startPlace);
			add(new JLabel("��ͣ��"));
			add(midPlace);
			add(new JLabel("Ŀ�ĵ�"));
			add(endPlace);
			add(new JLabel("����:"));
			add(weekPlace);
		}
		
	}
	private SecondLayer secondLayer = new SecondLayer();//�ڶ���
	
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
			add(new JLabel("��"));
			add(monthField);
			add(new JLabel("��"));
			add(dayField);
			add(new JLabel("��"));
			add(hourField);
			add(new JLabel("ʱ"));
			add(minField);
			add(new JLabel("��"));
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
	private TimeLayer startTimeLayer = new TimeLayer("���ʱ��");
	private TimeLayer endTimeLayer = new TimeLayer("�ִ�ʱ��");
	private TimeLayer transArriveTimeLayer = new TimeLayer("��ת����ʱ��");
	private TimeLayer transLeaveTimeLayer = new TimeLayer("��ת�뿪ʱ��");
	
	
	private class PriceLayer extends JPanel{
		public double kidPrice,adultPrice,topPrice;
		public JTextField kidText = new JTextField(5);
		public JTextField adultText = new JTextField(5);
		public JTextField topText = new JTextField(5);

		public PriceLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("����Ʊ�ۣ�"));
			add(adultText);
			add(new JLabel("��ͯƱ�ۣ�"));
			add(kidText);
			add(new JLabel("�����Ʊ�ۣ�"));
			add(topText);
		}
		
		public void commit() {
			try {				
				kidPrice = Double.parseDouble(kidText.getText());
				adultPrice = Double.parseDouble(adultText.getText());
				topPrice = Double.parseDouble(topText.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Ʊ��Ӧ�������֣�");
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
			add(new JLabel("�ۿۣ�"));
			add(countText);
		}
		
		public void commit() {		
			try {				
				discount = Double.parseDouble(countText.getText());
				if(discount > 10 || discount < 0) {
					throw new NotRangeException();
				}
			}catch(NotRangeException e) {
				JOptionPane.showMessageDialog(null, "�ۿ۲��ںϷ���Χ��!");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "�ۿ����벻�Ϸ�!");
			}
		}
		
		public void textClear() {
			countText.setText("");
		}
	}
	private DiscountLayer countLayer = new DiscountLayer();	
	private JButton makeSure = new JButton("ȷ��");
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
		makeSure.addActionListener(new ActionListener() {//�����ť���ύ���ݸ����ݿ�
			public void actionPerformed(ActionEvent e) {
				firstLayer.commit();
				//�ڶ�����˼������Բ���commit
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
					JOptionPane.showMessageDialog(null,"���óɹ���");
					textClear();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"����ʧ�ܣ�");
				}
				
				System.out.println("�����:" + firstLayer.id);
				System.out.println("���չ�˾:" + firstLayer.company);
				System.out.println("����:" + firstLayer.type);
				System.out.println("���:" + secondLayer.start);
				System.out.println("��תվ:" + secondLayer.mid);
				System.out.println("�յ�:" + secondLayer.end);
				System.out.println("��ͯƱ��:" + priceLayer.kidPrice);
				System.out.println("����Ʊ��:" + priceLayer.adultPrice);
				System.out.println("�ۿ�:" + countLayer.discount);
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
