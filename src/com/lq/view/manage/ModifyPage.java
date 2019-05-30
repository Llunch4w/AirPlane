package com.lq.view.manage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lq.model.Flight;
import com.lq.sql.*;

public class ModifyPage extends JFrame{
	private ModifyOperation modifyPanel;
	private Flight flight;
	public ModifyPage(Flight flight) {
		super(flight.getId() + "修改信息页面");
		this.flight = flight;
		setLayout(null);
		modifyPanel = new ModifyOperation(flight);
		
		modifyPanel.setBounds(0,0,800,500);
//		basicPanel.setBackground(Color.red);

		add(modifyPanel);

	}
	public void showing(int width,int height) {
		//w:400;h:600
		setLocation(800,100);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}

class ModifyOperation extends JPanel{//加入按钮 按钮代表每个信息 点相应信息的按钮 对其内容进行修改
	Flight flight;
	public ModifyOperation(Flight flight) 
	{   
       this.flight = flight;
       setLayout(new FlowLayout());
		   add(firstLayer);
    //   add(priceLayer);
		   add(timeLayer);
		   add(ArriveTimeLayer);
		   add(transArriveLayer);
		   add(transLeaveLayer);
		   add(ticketLayer);
		   add(pandwLayer);
	  
     } 
    private class FirstLayer extends JPanel{
			public JTextField idField=new JTextField();
			public JTextField companyField=new JTextField(6);
			public JTextField bulidField=new JTextField(3);
			public JTextField typeField=new JTextField(6);
		    private JButton planeID=new JButton("航班号");
		    private JButton planeCompany=new JButton("航空公司");
		    private JButton planebuild=new JButton("航站楼：");	    
		    private JButton planeType=new JButton("机型：");
		    private FlightUpdateDriver flightUpdateDriver= new FlightUpdateDriver();
		    public String id,company,bulid,type;
		   
			private class IdAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {				
					String Newid=idField.getText();
				    flightUpdateDriver.updateSection(flight.getId(),"flightID",Newid);
				}
			}
			IdAction idAction=new IdAction();
			private class CompanyAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					 company="company";
					 String Newcompany=companyField.getText();
				    flightUpdateDriver.updateSection(flight.getId(),"company",Newcompany);
				}
			}
			CompanyAction companyAction=new CompanyAction();
			private class BuildAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					bulid="building";
					String NewBuild=bulidField.getText();
				    flightUpdateDriver.updateSection(flight.getId(),"buliding",NewBuild);
				}
			}
			BuildAction  buildAction=new BuildAction();
			
			private class TypeAction implements ActionListener{	
				public void actionPerformed(ActionEvent event) {
					 type="planeType";
					 String NewType=typeField.getText();
				    flightUpdateDriver.updateSection(flight.getId(), type,NewType);
				}
			}
			TypeAction  typeAction=new TypeAction();
			public FirstLayer() {
				setPreferredSize(new Dimension(800,30));
				setLayout(new FlowLayout());
				add(planeID);
				planeID.addActionListener(idAction);
				add(idField);
				
				add(planeCompany);
				planeCompany.addActionListener(companyAction);
				add(companyField);
				
				add(planebuild);
				planebuild.addActionListener(buildAction); 					
				add(bulidField);
				
				add(planeType);
				planeType.addActionListener(typeAction);
				add(typeField);
				
				
			}
			
		}
    private FirstLayer firstLayer = new FirstLayer();
    private class TimeLayer extends JPanel{
    	public JTextField yearField = new JTextField(3);
		public JTextField monthField = new JTextField(3);
		public JTextField dayField = new JTextField(3);
		public JTextField hourField = new JTextField(3);
		public JTextField minField = new JTextField(3);	
		public int year,month,day,hour,min;
		//年月日时分
		public  FlightUpdateDriver flightUpdateDriver=new FlightUpdateDriver();	
		public JButton startTime=new JButton("起飞时间");
     	public TimeLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(startTime);
			startTime.addActionListener(starttimeAction);
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
		private class startTimeAction implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				year = Integer.parseInt(yearField.getText());
				month = Integer.parseInt(monthField.getText());
				day = Integer.parseInt(dayField.getText());
				hour = Integer.parseInt(hourField.getText());
				min = Integer.parseInt(minField.getText());
				String takeOffTime;
				String type;
				type="takeOff_time";
				takeOffTime = String.format("%d-%02d-%02d %d:%d", year,month,day,hour,min);	
				flightUpdateDriver.updateSection(flight.getId(),type,takeOffTime);
		
			}
		}
		private startTimeAction starttimeAction=new startTimeAction();}
    	private TimeLayer timeLayer=new TimeLayer();
	    private class arriveTimeLayer extends JPanel{
	        	public JTextField yearField = new JTextField(3);
	    		public JTextField monthField = new JTextField(3);
	    		public JTextField dayField = new JTextField(3);
	    		public JTextField hourField = new JTextField(3);
	    		public JTextField minField = new JTextField(3);	
	    		public  FlightUpdateDriver flightUpdateDriver=new FlightUpdateDriver();		
	    		//年月日时分
	    		public int year,month,day,hour,min;
	    		public JButton startTime=new JButton("到达时间");
	
	    	
	    		public arriveTimeLayer() {  
	    			setPreferredSize(new Dimension(500,30));
	    			setLayout(new FlowLayout());
	    			add(startTime);
	    			startTime.addActionListener(starttimeAction);
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
		private class startTimeAction implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				year = Integer.parseInt(yearField.getText());
				month = Integer.parseInt(monthField.getText());
				day = Integer.parseInt(dayField.getText());
				hour = Integer.parseInt(hourField.getText());
				min = Integer.parseInt(minField.getText());
				String takeOffTime;
				takeOffTime = String.format("%d-%02d-%02d %d:%d", year,month,day,hour,min);
				String type;
				type="arrive_time";
				flightUpdateDriver.updateSection(flight.getId(),type,takeOffTime);
		
			}
		}
		private startTimeAction starttimeAction=new startTimeAction();

    }
	private arriveTimeLayer ArriveTimeLayer = new arriveTimeLayer();
    private class TransArriveTimeLayer extends JPanel{
    	public JTextField yearField = new JTextField(3);
		public JTextField monthField = new JTextField(3);
		public JTextField dayField = new JTextField(3);
		public JTextField hourField = new JTextField(3);
		public JTextField minField = new JTextField(3);	
		public int year,month,day,hour,min;
		//年月日时分
	
		public JButton startTime=new JButton("中转到达时间");
     	public  TransArriveTimeLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(startTime);
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
		private class startTimeAction implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				year = Integer.parseInt(yearField.getText());
				month = Integer.parseInt(monthField.getText());
				day = Integer.parseInt(dayField.getText());
				hour = Integer.parseInt(hourField.getText());
				min = Integer.parseInt(minField.getText());
				String takeOffTime;
				String type;
				
				takeOffTime = String.format("%d-%02d-%02d %d:%d", year,month,day,hour,min);
                FlightUpdateDriver flightUpdateDriver=new FlightUpdateDriver();		
			//	flightUpdateDriver.UpdateSection(flight.getId(),type ,takeOffTime);
		
			}
		}
		private startTimeAction starttimeAction=new startTimeAction();}
    private TransArriveTimeLayer transArriveLayer=new TransArriveTimeLayer();
    private class TransLeaveTimeLayer extends JPanel{
    	public JTextField yearField = new JTextField(3);
		public JTextField monthField = new JTextField(3);
		public JTextField dayField = new JTextField(3);
		public JTextField hourField = new JTextField(3);
		public JTextField minField = new JTextField(3);	
		public int year,month,day,hour,min;
		//年月日时分
	
		public JButton startTime=new JButton("中转离开时间");
     	public  TransLeaveTimeLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(startTime);
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
		private class startTimeAction implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				year = Integer.parseInt(yearField.getText());
				month = Integer.parseInt(monthField.getText());
				day = Integer.parseInt(dayField.getText());
				hour = Integer.parseInt(hourField.getText());
				min = Integer.parseInt(minField.getText());
				String takeOffTime;
				String type;
				
				takeOffTime = String.format("%d-%02d-%02d %d:%d", year,month,day,hour,min);
                FlightUpdateDriver flightUpdateDriver=new FlightUpdateDriver();		
			//	flightUpdateDriver.UpdateSection(flight.getId(),type ,takeOffTime);
		
			}
		}
		private startTimeAction starttimeAction=new startTimeAction();}
    private TransLeaveTimeLayer transLeaveLayer=new TransLeaveTimeLayer();
    private class TicketsLayer extends JPanel{
			public JTextField capacityField=new JTextField(3);
			public JTextField remainField=new JTextField(6);
			public JTextField kidField=new JTextField(3);
			public JTextField adultField=new JTextField(6);
		    private JButton planecapacity=new JButton("总票数");
		    private JButton planeremain=new JButton("剩余票数");
		    private JButton planekid=new JButton("儿童票价");	    
		    private JButton planeadult=new JButton("成人票价");
		    private FlightUpdateDriver flightUpdateDriver= new FlightUpdateDriver();
		    public String capacity,remain,kid,adult;
		    
			private class CapacityAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					
					String Newcapacity=capacityField.getText();
					String type="capcity";
				    flightUpdateDriver.updateSection(flight.getId(),type,Newcapacity);
				}
			}
			CapacityAction capacityAction=new CapacityAction();
			private class RemainAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					 remain="remain";
					 String Newremain=remainField.getText();
				    flightUpdateDriver.updateSection(flight.getId(),remain,Newremain );
				}
			}
			RemainAction remainAction=new RemainAction();
			
			
			private class KidAction implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					kid="kidPrice";
					String Newkid=kidField.getText();
				    flightUpdateDriver.updateSection(flight.getId(),kid,Newkid);
				}
			}
			KidAction  kidAction=new KidAction();
			
			private class AdultAction implements ActionListener{	
				public void actionPerformed(ActionEvent event) {
					 adult="adultPrice";
					 String Newadult=adultField.getText();
				    flightUpdateDriver.updateSection(flight.getId(), adult,Newadult);
				}
			}
			AdultAction  adultAction=new AdultAction();
			public TicketsLayer() {
				setPreferredSize(new Dimension(800,30));
				setLayout(new FlowLayout());
				add(planecapacity);
				planecapacity.addActionListener(capacityAction);
				add(capacityField);
				
				add(planeremain);
				planeremain.addActionListener(remainAction);
				add(remainField);
				
				add(planekid);
				planekid.addActionListener(kidAction); 					
				add(kidField);
				
				add(planeadult);
				planeadult.addActionListener(adultAction);
				add(adultField);
				
				
			}
			
		}
    private TicketsLayer ticketLayer = new TicketsLayer();
	private class PandWLayer extends JPanel implements ItemListener{		
		private ArrayList<String> places_canbe_choose;
		private JComboBox<String> startPlace = new JComboBox<String>();
		private JComboBox<String> midPlace = new JComboBox<String>();
		private JComboBox<String> endPlace = new JComboBox<String>();
		private JComboBox<String> weekPlace = new JComboBox<String>();
		public String start = null,mid = null,end = null,week = "周一";
	    private JButton planestartPlace=new JButton("起飞地");
	    private JButton planemidPlace=new JButton("经停地");
	    private JButton planeendPlace=new JButton("目的地");	    
	    private JButton planeweek=new JButton("星期:");
	    private FlightUpdateDriver flightUpdateDriver= new FlightUpdateDriver();
	
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
			start = array.get(0);
			mid = array.get(0);
			end = array.get(0);
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
		private class sPAction implements  ActionListener{
			public void actionPerformed(ActionEvent e){
				 String type="takeOff_place ";
				flightUpdateDriver.updateSection(flight.getId(),type,start);
			}
		}
		public sPAction spaction=new sPAction();
//		private class mPAction implements  ActionListener{
//			public void actionPerformed(ActionEvent e){
//				 String type="trans_place";
//				//flightUpdateDriver.UpdateSection(flight.getId(),type,mid);
//			}
//		}
//		public mPAction mpaction=new mPAction();
		private class ePAction implements  ActionListener{
			public void actionPerformed(ActionEvent e){
				 String type="arrive_place";
				flightUpdateDriver.updateSection(flight.getId(),type,end);
			}
		}
		public ePAction epaction=new ePAction();
		
		private class WAction implements  ActionListener{
			public void actionPerformed(ActionEvent e){
				 String type="week";
				flightUpdateDriver.updateSection(flight.getId(),type,week);
			}
		}
		public WAction waction=new WAction();
		
		public PandWLayer() {
			init();
			setPreferredSize(new Dimension(600,30));
			setLayout(new FlowLayout());
			add(planestartPlace);
			planestartPlace.addActionListener(spaction);
			add(startPlace);
			
			add(planemidPlace);
			add(midPlace);
			
			add(planeendPlace);
			planeendPlace.addActionListener(epaction);
			add(endPlace);
			
			add(planeweek);
			planeweek.addActionListener(waction);
			add(weekPlace);
		}
		
	}
	private PandWLayer pandwLayer = new PandWLayer();//第二层
   
}