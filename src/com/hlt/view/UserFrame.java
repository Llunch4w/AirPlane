package com.hlt.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.hlt.model.UserMe;
import com.lq.client.Client;
import com.lq.dynamicManage.User;
import com.lq.requests.UserDetailRequest;

public class UserFrame extends JFrame implements ActionListener{
	User user;
	Client client;
	private JLabel  lab_user = new JLabel("——————个人信息——————");
	private JLabel lab_name =new JLabel    ("姓    名:");
	private JLabel lab_idCard = new JLabel ("身份证号:");
	private JLabel lab_phone =new JLabel   ("手 机 号:");
	private JLabel lab_name1;
	private JLabel lab_idCard1;
	private JLabel lab_phone1;
	private JButton but_changeMess = new JButton("修改个人信息");
	private JButton but_return = new JButton("返回");
	private JPanel pan_north = new JPanel();
	private JPanel pan_center = new JPanel();
	public UserFrame(User user){
		this.user = user;
		this.client = user.getClient();
		UserMe me = client.sendRequest(new UserDetailRequest(user.getID()));
		lab_name1 =new JLabel(me.getName());
		lab_idCard1 = new JLabel(me.getIDcard());
		lab_phone1 =new JLabel(me.getPhoneNumber());
		setLayout(new BorderLayout());
	    add(pan_north,BorderLayout.NORTH);
	    add(pan_center,BorderLayout.CENTER);
	    add(but_return,BorderLayout.SOUTH);
	    but_return.addActionListener(this);
	    setNorthPanel();
	    setCenterPanel();
	    setTitle("个人信息");
	    setVisible(true);
	    setLocation(600,400);
	    setSize(400,400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
//	    new ReturnButton(but_return,this,"MainFrame");
	    
	}
	public void setNorthPanel() {       
		
	    pan_north.add(lab_user);
		lab_user.setBounds(50, 5, 60, 30);    
}
	public void setCenterPanel() {
		pan_center.setSize(400, 200);
		pan_center.setLayout(null);
		pan_center.add(lab_name);
		pan_center.add(lab_idCard);
		pan_center.add(lab_phone);
		pan_center.add(lab_name1);
		pan_center.add(lab_idCard1);
		pan_center.add(lab_phone1);
		lab_name.setBounds(105,5,80,40);
		lab_idCard.setBounds(105, 50, 80, 40);
		lab_phone.setBounds(105, 95, 80, 40);
		lab_name1.setBounds(195,5,80,40);
		lab_idCard1.setBounds(195, 50, 80, 40);
		lab_phone1.setBounds(195, 95, 80, 40);
	    Font font = new Font("宋体",Font.BOLD,14);
	    lab_name.setFont(font);
	    lab_idCard.setFont(font);
	    lab_phone.setFont(font);
	    lab_name1.setFont(font);
	    lab_idCard1.setFont(font);
	    lab_phone1.setFont(font);
	    
	    pan_center.add(but_changeMess);
	    but_changeMess.setBounds(105, 200, 150, 40);
	    but_changeMess.addActionListener(this);
	    
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == but_return) {
			dispose();
			user.toWindow("main menu");
		}
		else if(e.getSource() == but_changeMess) {
			
		}
	}

	public void setLab_name1(String lab_name1) {
		this.lab_name1.setText(lab_name1);
	}
	public void setLab_idCard1(String lab_idCard1) {
		this.lab_idCard1.setText(lab_idCard1);
	}

	public void setLab_phone1(String lab_phone1) {
		this.lab_phone1.setText(lab_phone1);
	}
}
