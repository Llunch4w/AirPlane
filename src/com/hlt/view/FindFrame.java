package com.hlt.view;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
//import java.util.*;
import java.util.ArrayList;

import com.lq.client.Client;
import com.lq.dynamicManage.User;
import com.lq.model.Flight;
import com.lq.requests.SearchByPlaceRequest;
import com.lq.common.time.DateTime;


public class FindFrame extends JFrame implements ActionListener{
//	private Flight = new Flight();
	private User user;
	private Client client;
	private JButton but_find = new JButton("��ѯ");
	private JButton but_change = new JButton("");
	private JButton but_return = new JButton("����");
	private JButton but_topFind = new JButton("�߼���ѯ");
	private JLabel startlab = new JLabel  ("���:");
	private JLabel endlab = new JLabel   ("�յ�:");	
	private JLabel lab_today = new JLabel("");
	JLabel timelab = new JLabel("��������:");
	Time today = new Time();
	private JTextField startText = new JTextField(10);//�����
	private JTextField endText = new JTextField(20);//�յ���
	private JTextField Text_time = new JTextField(10);//������
//	private Plant[] p= {plant};
    private Integer []startTime = {1,2,3,4,5,6,7,8,9,10,11,12};
    private JComboBox mon = new JComboBox(startTime);
	private JLabel infoLab = new JLabel(" ");//��ʾ��
	        JPanel find_Panel = new JPanel();
	public FindFrame(User user) {
		super("��ѯ");
		this.user = user;
		this.client = user.getClient();
//		setLayout(null);
		init();
		setLocation(500,100);
		setSize(500,300);
		setVisible(true);
	}
	public User getUser() {
		return user;
	}
	public void init() {
		find_Panel.setLayout(null); 
		startlab.setBounds(61, 66, 54, 15);
		find_Panel.add(startlab);

		startText .setBounds(104, 63, 93, 21);
		find_Panel.add(startText);


		find_Panel.add(endlab);
		endlab.setBounds(280, 66, 54, 15);
		
		endText.setBounds(320, 63, 93, 22);
	    find_Panel.add(endText);
		

		find_Panel.add(timelab);
		timelab.setBounds(61, 96, 204, 15);
		

		mon.setBounds(302, 96, 60, 22);
		find_Panel.add(mon);
		
		but_find.setIcon(new ImageIcon("images/��ѯ1.png"));
		but_find.setBounds(120,152, 103, 30);
		but_find.addActionListener(this);
		find_Panel.add(but_find);
		
		but_return.setBounds(253, 152, 100, 30);
		but_return.addActionListener(this);
		find_Panel.add(but_return);
		//�������ڱ�ǩ
		lab_today.setText(today.getDate());
		lab_today.setBounds(65, 5, 160, 30);
		find_Panel.add(lab_today);
		//����text
		find_Panel.add(Text_time);
		Text_time.setBounds(120, 96, 120, 20);
		//�߼���ѯ
		but_topFind.setBounds(305, 5, 100, 30);
		but_topFind.setContentAreaFilled(false);
		but_topFind.addActionListener(this);
		find_Panel.add(but_topFind);
		//ת����ť
		but_change.setIcon(new ImageIcon("images/����.png"));	
		but_change.setBounds(220, 63, 40, 20);
		but_change.setContentAreaFilled(false);
		find_Panel.add(but_change);

		add(find_Panel);
		pack();
		
	}
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == but_find){
    		SearchByPlaceRequest req = new SearchByPlaceRequest(startText.getText(),endText.getText(),
    				Text_time.getText());
    		ArrayList<Flight> flights = client.sendRequest(req);
    		if(flights.size() == 0) {
    			JOptionPane.showMessageDialog(null, "û�з��������ĺ��࣡");
    		}
    		else {	
    			dispose();
    			new FindResultsFrame(flights,user);
    		}
    	}
        else if(e.getSource()==but_topFind) {//�߼���ѯ���
    		
    	}
        else if(e.getSource() == but_return) {
        	dispose();
        	user.toWindow("main menu");
        }
    	    
    } 
	public static void main(String[] args) {
		new FindFrame(new User());
		
	}
}
