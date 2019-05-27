package com.hlt.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.lq.dynamicManage.User;

public class MyOrderFrame extends JFrame{
	private User user;
	private JLabel  lab_order = new JLabel("��Ʊ����");
	private JButton but_return = new JButton("����");
	private JPanel pan_north = new JPanel();
	private JPanel pan_center = new JPanel();
	
	private JButton but_returnTicket = new JButton("��Ʊ");
	private JButton but_specific = new JButton("�鿴����");
	private JLabel  lab_time = new JLabel("2019��6��21��");
	private JLabel  lab_startTime = new JLabel("10:25����");
	private JLabel  lab_place = new JLabel("����-֣��");
	private JLabel  lab_flightCom = new JLabel("��������G1422");

	public MyOrderFrame(User user){
		this.user = user;
		setTitle("����");
		setLayout(new BorderLayout());
		add(pan_north,BorderLayout.NORTH);
		add(pan_center,BorderLayout.CENTER);
		setSize(400,600);
		setLocation(600,400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		setNorthPanel();
		setCenterPanel();
		
		
	}
	public void setNorthPanel() {
	    pan_north.setLayout(null);
	    pan_north.add(lab_order);
	    pan_north.add(but_return);
		lab_order.setBounds(50, 5, 60, 30);
		but_return.setBounds(300,5,60,30);
		pan_north.setVisible(true);
		    
	}
	public void setCenterPanel() {
		but_returnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==but_returnTicket) {
					JDialog Dia_returnTicket = new JDialog();
					Dia_returnTicket.setModal(true);
					Dia_returnTicket.show(true);
					Dia_returnTicket.setTitle("��Ʊ");
					JLabel lab_info = new JLabel("ȷ����Ʊ��");
					JButton but_yes = new JButton("ȷ��");
					JButton but_no = new JButton("����");
					Dia_returnTicket.add(but_yes);
					Dia_returnTicket.add(but_no);
					Dia_returnTicket.add(lab_info,BorderLayout.CENTER);
					Dia_returnTicket.add(but_yes,BorderLayout.SOUTH);
					Dia_returnTicket.add(but_yes,BorderLayout.SOUTH);
					dispose(); 
					
				}
			}
		});
		setActionListener(but_returnTicket);
		setOrderPanel(1);
	}
	public void setActionListener(JButton button) {
		if (button.getName()=="��Ʊ")
		{
			but_returnTicket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==but_returnTicket) {
						JDialog Dia_returnTicket = new JDialog();
						Dia_returnTicket.setModal(true);
						Dia_returnTicket.show(true);
						Dia_returnTicket.setTitle("��Ʊ");
						JLabel lab_info = new JLabel("ȷ����Ʊ��");
						JButton but_yes = new JButton("ȷ��");
						JButton but_no = new JButton("����");
						Dia_returnTicket.add(but_yes);
						Dia_returnTicket.add(but_no);
						Dia_returnTicket.add(lab_info,BorderLayout.CENTER);
						Dia_returnTicket.add(but_yes,BorderLayout.SOUTH);
						Dia_returnTicket.add(but_yes,BorderLayout.SOUTH);
						dispose(); 
						
					}
				}
			});
		}
		
	}
	public void setOrderPanel(int num) {//����Ϊ�˿͵Ķ�����
		for(int i =0;i<num;i++) {
			JPanel pan_order = new JPanel();
			pan_center.setLayout(null);
			pan_center.add(pan_order);
			
			pan_order.setBorder(BorderFactory.createLoweredBevelBorder());//���ñ߿���
		    pan_order.setLayout(null);
			pan_order.setBounds(5,5+100*i,375,100);//��������С
			pan_order.add(but_returnTicket);
			pan_order.add(but_specific);
			pan_order.add(lab_time);
			pan_order.add(lab_startTime);
			pan_order.add(lab_place);
			pan_order.add(lab_flightCom);
			//����λ�ô�С
			but_returnTicket.setBounds(200, 5, 70, 30);
			but_specific.setBounds(280,5,90,30);
			lab_time.setBounds(5, 5, 100, 40);
			lab_startTime.setBounds(100, 5, 100, 40);
			lab_place.setBounds(5, 30, 100, 40);
			lab_flightCom.setBounds(5, 55, 100, 40);
			//��������
			Font font = new Font("����",Font.BOLD,18);
			lab_place.setFont(font);
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
	//public static void main(String[] args) {
	//  new MyOrderFrame();	
	//}
	public void setLab_startTime(String startTime) {
		this.lab_startTime.setText(startTime+"����");
	}
	public JLabel getLab_flightCom() {
		return lab_flightCom;
	}
	public void setLab_flightCom(JLabel lab_flightCom) {
		this.lab_flightCom = lab_flightCom;
	}
}