package com.hlt.view;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import com.lq.dynamicManage.User;

public class OrderFrame extends JFrame{
	private User user;
	private JButton return_but = new JButton("返回<");
	private JButton yes_but = new JButton("确定");
	
	private JPanel flightMessage_panel = new JPanel();//航班信息面板
	private JButton flightMBut = new JButton("航班详情");
	
	private JPanel  flyPerson_panel = new JPanel();//乘机人面板
	private JLabel  flyPerson_lab = new JLabel("乘机人");
	private JLabel  flyPersoninfolab = new JLabel("请确保乘机人姓名与证件信息正确");
	
	private JPanel  flyPerson_panel2 = new JPanel();//乘机人面板
	private JButton addflyPersonBut = new JButton("新增");//新增乘机人
	private JLabel chooseFlyPerson = new JLabel("选择乘机人");
	private JLabel rNameLab =new JLabel   ("姓      名:");
	private JLabel idCardLab = new JLabel ("身份证号:");
	private JLabel phoneNumLab =new JLabel("手  机 号:");
	private JTextField rNameText = new JTextField(20);//姓名条
	private JTextField idCardText= new JTextField(20); //身份证条
	private JTextField phoneNumText= new JTextField(20); //手机号条
	private JButton load_but = new JButton("保存");
	
	private JButton pay_but = new JButton("去付款");//付款
	public OrderFrame(User user){
		this.user = user;
		add(return_but);
		add(yes_but);
		return_but.setBounds(5, 5, 80, 30);
		yes_but.setBounds(505, 5, 60, 30);
		
		
		setLayout(new BorderLayout());
		
		
		add(flightMessage_panel,BorderLayout.NORTH);
		flightMessage_panel.add(flightMBut);
		//flightMessage_panel.setLayout(null);
		flightMessage_panel.setBounds(5, 5, 60, 30);
		//flightMessage_panel.setBounds();
		
		add(flyPerson_panel,BorderLayout.CENTER);
		flyPerson_panel.add(addflyPersonBut);//新增
		addflyPersonBut.setContentAreaFilled(false);//按钮设置为透明
		//addflyPersonBut.setBorderPainted(false);//按钮去掉边框
		flyPerson_panel.add(chooseFlyPerson);//选择乘机人
	//
		
		
		
		flyPerson_panel2.setBorder(BorderFactory.createLoweredBevelBorder());//设置面板边框凹陷
		flyPerson_panel2.setVisible(false);
		flyPerson_panel2.setSize(400,200);
		flyPerson_panel2.add(rNameLab);
		flyPerson_panel2.add(rNameText);
		flyPerson_panel2.add(idCardLab);
		flyPerson_panel2.add(idCardText);
		flyPerson_panel2.add(phoneNumLab);
		flyPerson_panel2.add(phoneNumText);
		flyPerson_panel2.add(load_but);
		flyPerson_panel2.setLayout(null);
		Rectangle r = new Rectangle(55,5,100,20);
		Rectangle r1 = new Rectangle(165,5,200,20);
		rNameLab.setBounds(r);
		rNameText.setBounds(r1);
		r.y+=30;r1.y+=30;
		idCardLab.setBounds(r);
		idCardText.setBounds(r1);
		r.y+=30;r1.y+=30;
		phoneNumLab.setBounds(r);
		phoneNumText.setBounds(r1);
		load_but.setBounds(200, 90, 60, 30);
		//监听返回
		return_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==return_but)
					if(flyPerson_panel2.getVisibleRect().equals(true))
						{flyPerson_panel2.setVisible(false);
				        flyPerson_panel.setVisible(true);}
					else {
						dispose();
//						new MainFrame();
						user.toWindow("main menu");
					}
			}
			
			}
		);
		//监听增加乘机人
		addflyPersonBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==addflyPersonBut)
					flyPerson_panel.setVisible(false);
				    flyPerson_panel2.setVisible(true);	
				    add(flyPerson_panel2,BorderLayout.CENTER);
					
			}
		});
		//监听保存
		load_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==load_but)
					flyPerson_panel.setVisible(false);
				    flyPerson_panel2.setVisible(true);	
				    add(flyPerson_panel2,BorderLayout.CENTER);
					
			}
		});
		pack();
		setLocation(600,400);
		setSize(600,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("订单");
	}
	
//	public static void main(String[] args) {
//		new OrderFrame();
//	}
}