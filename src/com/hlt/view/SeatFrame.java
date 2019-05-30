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
	private JButton but_path = new JButton("����");

	private JPanel pan1_seat = new JPanel();
	private JPanel pan2_seat = new JPanel();
    private JButton but_return = new JButton("����");
    private JButton but_yes = new JButton("ȷ��");
	private JButton seat[] = new JButton[50];
	private int seatId[]= {-1,-1,-1,-1,-1};//һ�������������

	public SeatFrame(Flight flight,Order order){
		this.order = order;
		this.flight = flight;
	    setLayout(null);	
	    setSeatPanel(pan_seat,0,50);
		setSeatPanel(pan1_seat,10,30);
		setSeatPanel(pan2_seat,30,50);
		setTitle("��λ");
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
			JButton but = new JButton("");//ǰ10Ϊͷ�Ȳ�
			panel.add(but);
		    but.setContentAreaFilled(false);
		    seat[i]=but;
			if(!seats.check(i)) {//�����Ӧ��λ��ѡ		
				but.setIcon(new ImageIcon("images/��λ��ѡ.png"));	
				seat[i].addActionListener(this);
			}
			else {//����
				but.setIcon(new ImageIcon("images/��λ��ѡ.png"));
				seat[i].setEnabled(true);//��ѡ����λ���ò��ɵ�����������Ա�ѡ��
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
				seat[i].setIcon(new ImageIcon("images/��λ��ѡ.png"));
				flyPerson++;
			}
			else {
				JOptionPane.showMessageDialog(null,"һ���������5����");
			}
		}
		if(e.getSource()==but_yes) {
			if(seatId[j]==-1) {
				dispose();
//				new ReturnButton(but_yes,this,"OrderFrame");
			}
			else {
				for(int i=0;i<=j;i++);//ȷ�Ϻ��Ӧ�˻���ȷ��
//				flight.setSeat(false, seatId[j]);//�ú����Ӧ��λ����Ϊ��ѡ
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
