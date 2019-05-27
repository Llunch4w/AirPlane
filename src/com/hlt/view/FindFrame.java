package com.hlt.view;

import java.awt.BorderLayout;

import javax.swing.*;

import com.lq.dynamicManage.User;

public class FindFrame extends JFrame{
	private User user;
	private JButton btn_panel_find;
	private JButton yes = new JButton("确定");
	private JButton no = new JButton("取消");
	private JLabel startlab = new JLabel   ("起点:");
	private JLabel endlab = new JLabel   ("终点:");
	private JLabel rNameLab =new JLabel   ("姓      名:");
	private JLabel idCardLab = new JLabel ("身份证号:");
	private JLabel phoneNumLab =new JLabel("手  机 号:");
	private JTextField startText = new JTextField(10);//起点条
	private JPasswordField EndText = new JPasswordField(20);//终点条
	private JTextField rNameText = new JTextField(20);//姓名条
	private JTextField idCardText= new JTextField(20); //身份证条
	private JTextField phoneNumText= new JTextField(20); //手机号条
	private JLabel infoLab = new JLabel(" ");//提示条
	        JPanel find_Panel = new JPanel();
	    
	public FindFrame(User user){
		this.user = user;
	    find_Panel.setLayout(new BorderLayout()); 
		JLabel startlab = new JLabel("起点");
		startlab.setBounds(61, 66, 54, 15);
		find_Panel.add(startlab, BorderLayout.NORTH);

		JTextField starttxt = new JTextField();
		starttxt .setBounds(104, 63, 93, 21);
		find_Panel.add(starttxt, BorderLayout.NORTH);


		JLabel endlab = new JLabel("终点");
		find_Panel.add(endlab, BorderLayout.NORTH);
		endlab.setBounds(271, 66, 54, 15);
		
		JTextField endtext = new JTextField();
		endtext.setBounds(300, 63, 85, 22);
	    find_Panel.add(endtext, BorderLayout.NORTH);
		
		JLabel timelab = new JLabel("时间");
		find_Panel.add(timelab, BorderLayout.NORTH);
		timelab.setBounds(479, 66, 54, 15);
		
		JTextField starttimeText = new JTextField();
		starttimeText.setBounds(512, 63, 102, 22);
		find_Panel.add(starttimeText, BorderLayout.NORTH);
		
		btn_panel_find = new JButton("查询");
		btn_panel_find.setIcon(new ImageIcon("images/查询1.png"));
		btn_panel_find.setBounds(651, 62, 93, 23);
		find_Panel.add(btn_panel_find, BorderLayout.SOUTH);

		add(find_Panel);
		pack();
		setLocation(600,400);
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("查询");
	    }

}