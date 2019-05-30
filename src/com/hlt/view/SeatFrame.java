package com.hlt.view;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import com.hlt.model.*;
import com.lq.model.*;
import java.util.*;


public class SeatFrame extends JFrame implements ActionListener{
	private Flight flight;	
	private Order order;
	
	private JPanel pan_seat = new JPanel();
	private JButton but_path = new JButton("过道");

	private JPanel pan1_seat = new JPanel();
	private JPanel pan2_seat = new JPanel();
    private JButton but_return = new JButton("返回");
    private JButton but_yes = new JButton("确定");
	private JButton seat[] = new JButton[50];
	private int seatId[]= {-1,-1,-1,-1,-1};//一个订单最多五人

	public SeatFrame(Flight flight,Order order){
		this.order = order;
		this.flight = flight;
	    setLayout(null);	
	    setSeatPanel(pan_seat,0,50);
		setSeatPanel(pan1_seat,10,30);
		setSeatPanel(pan2_seat,30,50);
		setTitle("座位");
		add(pan_seat);
		add(pan1_seat);
		add(pan2_seat);
		add(but_path);
		add(but_yes);
		add(but_return);
		but_yes.setBounds(150, 500, 60, 30);
		but_return.setBounds(300, 500, 60, 30);
		but_path.setContentAreaFilled(false);
		but_path.setEnabled(false);
		but_path.setBounds(220, 150, 60, 200);
		pan_seat.setBounds(5, 5, 500, 100);
		pan1_seat.setBounds(5, 125, 200, 375);
		pan2_seat.setBounds(300,125, 200, 375);
		setVisible(true);
		setLocation(800,400);
		setSize(530,600);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	    but_yes.addActionListener(this);
	    but_return.addActionListener(this);		
	}
	
	public void setSeatPanel(JPanel panel,int start,int num) {
		FlightContainer seats = flight.getContainer();
		for(int i=start;i<num;i++) {
			JButton but = new JButton("");//前10为头等舱
			panel.add(but);
		    but.setContentAreaFilled(false);
		    seat[i]=but;
			if(!seats.check(i)) {//航班对应座位可选		
				but.setIcon(new ImageIcon("images/座位可选.png"));	
				seat[i].addActionListener(this);
			}
			else {//否则
				but.setIcon(new ImageIcon("images/座位已选.png"));
				seat[i].setEnabled(true);//已选的座位设置不可点击，即不可以被选择
			}
		}
		

	}
	int j =0;int flyPerson = 0;
	
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<50;i++)
		if(e.getSource()==seat[i]) {	
			flyPerson++;
			if(flyPerson<=5)
			{
				seatId[j]=i;
				seat[i].setIcon(new ImageIcon("images/座位已选.png"));
				flyPerson++;
			}
			else {
				JOptionPane.showMessageDialog(null,"一个订单最多5个人");
			}
		}
		if(e.getSource()==but_yes) {
			if(seatId[j]==-1) {
				dispose();
//				new ReturnButton(but_yes,this,"OrderFrame");
			}
			else {
				for(int i=0;i<=j;i++);//确认后对应乘机人确定
//				flight.setSeat(false, seatId[j]);//该航班对应座位设置为已选
			}
		}
		if(e.getSource()==but_return) {
			this.dispose();
		}
	}
			
//	public boolean getSeat() {
//		
//	}

	public int getSeatId() {
		return seatId[0];
	}
//	public static void main(String[] args) {
//		new SeatFrame(new Ticket(),new Flight());
//	}
}
