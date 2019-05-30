package com.lq.view.manage;

import java.awt.event.*;

import javax.swing.*;

import com.lq.model.Flight;
import com.lq.model.FlightContainer;

public class SeatDetail extends JFrame implements ActionListener{
	private Flight flight;
	private JPanel pan_seat = new JPanel();
	private JButton but_path = new JButton("过道");

	private JPanel pan1_seat = new JPanel();
	private JPanel pan2_seat = new JPanel();
	private JButton seat[] = new JButton[50];
	
	public SeatDetail(Flight flight) {
		super("座位表详情页");
		this.flight = flight;
		setLayout(null);	
	    setSeatPanel(pan_seat,0,10);
		setSeatPanel(pan1_seat,10,30);
		setSeatPanel(pan2_seat,30,50);
		setTitle("座位");
		add(pan_seat);
		add(pan1_seat);
		add(pan2_seat);
		add(but_path);
		but_path.setContentAreaFilled(false);
		but_path.setEnabled(false);
		but_path.setBounds(220, 150, 60, 200);
		pan_seat.setBounds(5, 5, 500, 100);
		pan1_seat.setBounds(5, 125, 200, 375);
		pan2_seat.setBounds(300,125, 200, 375);
		setVisible(true);
		setLocation(800,400);
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	}
	
	public void setSeatPanel(JPanel panel,int start,int num) {
		FlightContainer seats = flight.getContainer();
		for(int i=start;i<num;i++) {
			JButton but = new JButton("");//前10为头等舱
			panel.add(but);
		    but.setContentAreaFilled(false);
		    seat[i]=but;
		    but.addActionListener(this);
			if(!seats.check(i)) {//航班对应座位可选		
				but.setIcon(new ImageIcon("images/座位可选.png"));	
			}
			else {//否则
				but.setIcon(new ImageIcon("images/座位已选.png"));
				seat[i].setEnabled(true);//已选的座位设置不可点击，即不可以被选择
			}
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void showing(int width,int height) {
		//w:400;h:600
		setLocation(600,100);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}

