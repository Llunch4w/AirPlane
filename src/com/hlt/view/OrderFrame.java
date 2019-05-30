package com.hlt.view;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import com.hlt.model.*;
import com.lq.client.Client;
import com.lq.common.time.DateTime;
import com.lq.dynamicManage.User;
import com.lq.model.*;

public class OrderFrame extends JFrame implements ActionListener{
	
	private Order order = new Order();//保存时生成具体对象
	private User user;
	private Client client;
	
	private Flight flight;
	private FlightContainer container;
	private int num=0;//记录增加乘机人数，最多5名
	private int numFly=0;//记录选择的乘机人数
	
	private JButton but_return = new JButton("返回");
	private JButton but_yes = new JButton("预定");
	private JLabel  lab_info = new JLabel("————金牌服务·出行保障————");
	
	
	private JPanel pan_north = new JPanel();//航班信息面板
	private JButton but_specific = new JButton("航班详情");
	private JLabel  lab_time = new JLabel("2019年6月21日");
	private JLabel  lab_startTime = new JLabel("10:25出发");
	private JLabel  lab_place = new JLabel("长春-郑州");
	private JLabel  lab_flightCom = new JLabel("东方航空G1422");
	
	private JPanel  pan_center = new JPanel();//乘机人面板
	private JLabel  flyPerson_lab = new JLabel("乘机人");
	private JLabel  flyPersoninfolab = new JLabel("请确保乘机人姓名与证件信息正确");
	private JCheckBox cb_flyPerson []= new JCheckBox[10];
	
	
	private JPanel pan_ticket = new JPanel();//票面板，价格座位
	private JLabel lab_discount = new JLabel("折扣: 3折");
	private JLabel lab_price = new JLabel("¥450");
	private JLabel lab_type = new JLabel("经济舱");
	private JLabel lab_customerType = new JLabel("乘客类型");
	private String[] list = {"成人","儿童","学生"};//根据身份证号到现在的年龄自动选定
	private JButton but_seat = new JButton("座位");
	private JButton but_seatId = new JButton("座位号:");
	
	private JComboBox cb_type = new JComboBox(list);
	
	
	private JPanel  flyPerson_panel2 = new JPanel();//乘机人面板
	private JButton addflyPersonBut = new JButton("新增");//新增乘机人
	private JLabel chooseFlyPerson = new JLabel("选择乘机人");
	private JLabel rNameLab =new JLabel   ("姓      名:");
	private JLabel idCardLab = new JLabel ("身份证号:");
	private JLabel phoneNumLab =new JLabel("手  机 号:");
	private JTextField rNameText = new JTextField(20);//姓名条
	private JTextField idCardText= new JTextField(20); //身份证条
	private JTextField phoneNumText= new JTextField(20); //手机号条
	private JButton but_load = new JButton("保存");
	
	
	private JPanel  pan_south = new JPanel();//乘机人面板
	private JButton pay_but = new JButton("去付款");//付款
	
	private ArrayList<Ticket> mayTicket = new ArrayList<Ticket>();
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==but_yes) {
			order = new Order();
			order.setPlaneID(flight.getId());
			order.setFlyPersonNum(numFly);
			order.setUserID(user.getID());
			for(Ticket t:mayTicket) {
				t.setDiscount(flight.getDiscount());
				t.setPlaneID(flight.getId());
			}
			order.setTicket(mayTicket);
			order.commit();
			client.sendOrder(order);
			JOptionPane.showMessageDialog(null, "预订成功");
		}
		else if(e.getSource()==addflyPersonBut) {
			pan_center.setVisible(false);
		    flyPerson_panel2.setVisible(true);
		    flyPerson_panel2.setBounds(5,210,375,100);
		    add(flyPerson_panel2);
		}
		else if(e.getSource()==but_seat) {
	    	
			SeatFrame seat = new SeatFrame(flight,order);
			
			int i = seat.getSeatId();
			but_seatId.setText(String.valueOf(i));
		}  
		else if(e.getSource()==but_load) {		
			if(num<5) {
				num++;
				String name = rNameText.getText();
				String idCard = idCardText.getText();
				String phoneNum = phoneNumText.getText();
				Ticket ticket = new Ticket(name,idCard,phoneNum);//新增乘机人
				mayTicket.add(ticket);
				cb_flyPerson[num] = new JCheckBox(name);
				pan_center.add(cb_flyPerson[num]);
				rNameText.setText(null);
				idCardText.setText(null);
				phoneNumText.setText(null);
				pan_center.setVisible(true);
				flyPerson_panel2.setVisible(false);
				
				//订单类增加乘机人到数据库中
			}
		}
		else if(e.getSource() == but_return) {
			dispose();
		}
		
	}
	public void onCheckChanged(boolean isChecked) {
	    if(isChecked) 
			order.setFlyPersonNum(++numFly);
	
	}
	public void setActionListener() {
		//监听预订
		but_yes.addActionListener(this);
		//监听座位
		but_seat.addActionListener(this); 
		//监听返回
//	    new ReturnButton(but_return,flyPerson_panel2,pan_center);
		but_return.addActionListener(this);
		//监听增加乘机人
		addflyPersonBut.addActionListener(this);	
		//监听保存
		but_load.addActionListener(this);
		for(int i=0;i<num;i++) {
			cb_flyPerson[i].addActionListener(this);
		}
		
	}
	 public void setTicketPanel() {
		lab_discount.setText(String.format("%.2f折", flight.getDiscount()));
		lab_price.setText(String.format("¥%.2f", flight.getAdultprice()));
		pan_ticket.add(lab_type);
		pan_ticket.add(lab_price);
		pan_ticket.add(lab_discount);
		pan_ticket.add(lab_customerType);
		pan_ticket.add(cb_type);
		pan_ticket.add(but_seat);
		pan_ticket.add(but_seatId);
		but_seatId.setContentAreaFilled(false);
		but_seatId.setEnabled(true);
	}	
	 
	public void setNorthPanel() {
		//赋值
		DateTime t = flight.getStartTime().getPlanTime();
		lab_time.setText(String.format("%d年%d月%d日",
					t.getYear(),t.getMonth(),t.getDay()));
		lab_startTime.setText(String.format("%d:%d出发",t.getHour(),
				t.getMinute()));
		lab_place.setText(String.format("%s--%s", flight.getTakeoffPlace(),
				flight.getArrivePlace()));
		lab_flightCom.setText(String.format("%s%s",
				flight.getCompany(),flight.getId()));
		
		pan_north.setBorder(BorderFactory.createLoweredBevelBorder());//设置边框凹陷
	 	pan_north.setLayout(null);
		pan_north.add(but_specific);
		pan_north.add(lab_time);
		pan_north.add(lab_startTime);
		pan_north.add(lab_place);
		pan_north.add(lab_flightCom);
		//设置位置大小
		but_specific.setBounds(280,5,90,30);
		lab_time.setBounds(5, 5, 100, 40);
		lab_startTime.setBounds(100, 5, 100, 40);
		lab_place.setBounds(5, 30, 100, 40);
		lab_flightCom.setBounds(5, 55, 100, 40);
		//设置字体
		Font font = new Font("宋体",Font.BOLD,18);
		lab_place.setFont(font);	
//		lab_info.setFont(font);
		//航班详情按钮
	}
	public void setCenterPanel() {
		pan_center.setBorder(BorderFactory.createLoweredBevelBorder());
		pan_center.add(addflyPersonBut);//新增
		addflyPersonBut.setContentAreaFilled(false);//按钮设置为透明
		//addflyPersonBut.setBorderPainted(false);//按钮去掉边框
		pan_center.add(chooseFlyPerson);//选择乘机人
		setFlyPersonPanel();
	}
	public void setFlyPersonPanel() {
		flyPerson_panel2.setBorder(BorderFactory.createLoweredBevelBorder());//设置面板边框凹陷
		flyPerson_panel2.setVisible(false);
		flyPerson_panel2.setLayout(null);
		flyPerson_panel2.add(rNameLab);
		flyPerson_panel2.add(rNameText);
		flyPerson_panel2.add(idCardLab);
		flyPerson_panel2.add(idCardText);
		flyPerson_panel2.add(phoneNumLab);
		flyPerson_panel2.add(phoneNumText);
		flyPerson_panel2.add(but_load);
		Rectangle r = new Rectangle(25,5,100,20);
		Rectangle r1 = new Rectangle(105,5,200,20);
		rNameLab.setBounds(r);
		rNameText.setBounds(r1);
		r.y+=30;r1.y+=30;
		idCardLab.setBounds(r);
		idCardText.setBounds(r1);
		r.y+=30;r1.y+=30;
		phoneNumLab.setBounds(r);
		phoneNumText.setBounds(r1);
		but_load.setBounds(310, 65, 60, 20); 
		
	}
	public void setSouthPanel() {
		pan_south.setLayout(null);
		pan_south.add(but_return);
		pan_south.add(but_yes);
		but_return.setBounds(205, 5, 80, 30);
		but_yes.setBounds(75, 5, 80, 30);
	}
	public OrderFrame(Flight plant,User user){
		this.flight=plant;
		this.user = user;
		this.client = user.getClient();
		setLayout(null);
		add(pan_north);
		add(pan_center);
		add(pan_south);
		add(pan_ticket);
		add(lab_info);
		pan_north.setBounds(5,5,375,200);//设置面板大小
		pan_center.setBounds(5,210,375,100);
	    pan_south.setBounds(5, 450, 375, 100);
	    pan_ticket.setBounds(5,310, 375, 100);
	    lab_info.setBounds(85, 410, 300, 30);
	    setNorthPanel();
	    setCenterPanel();
	    setSouthPanel() ;
	    setTicketPanel();
	    setActionListener();
		setLocation(600,400);
		setSize(400,600);
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("订单");
	}
	
//	public static void main(String[] args) {
//		Flight plant = new Flight();
////		plant.setSeat(false, 1);
////		plant.setSeat(false, 10);
////		plant.setSeat(false, 40);
//		Order order = new Order();
//		new OrderFrame(plant,order);
//	}
}
