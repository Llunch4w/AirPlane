package com.hlt.view;

import javax.swing.*;

import com.lq.model.Flight;

public class BookingTicketFrame extends JFrame{
	Flight flight;
	private JPanel pan_plant = new JPanel();
	private JLabel path = new JLabel("����--֣��");
	private JLabel date = new JLabel("06-21 ����");
	private JButton sort = new JButton("����");
	private  JButton but_return = new JButton("����");
	private JButton calender = new JButton("");
	private JLabel lab_topSeat = new JLabel ("ͷ�Ȳ�");
	private JLabel lab_norSeat = new JLabel("���ò�");
//	private JLabel lab_topTicket = new JLabel("��Ʊ: "+flight.getTopTicketNum());
//	private JLabel lab_norTicket = new JLabel("��Ʊ: "+(plant.getTicketnumber()-plant.getTopTicketNum()));
	BookingTicketFrame(Flight flight){
		setTitle("��Ʊ");
		setLocation(600,400);
		setSize(400,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.flight = flight;
		add(lab_norSeat);
//		add(lab_norTicket);
		add(lab_topSeat);
//		add(lab_topTicket);
		lab_topSeat.setBounds(5, 300, 60, 30);
		lab_norSeat.setBounds(5, 350, 60, 30);
//		lab_topTicket.setBounds(105, 300, 60, 30);
//		lab_norTicket.setBounds(105, 350, 60, 30);
	}
//	public static void main(String[] args) {
//		Plant plant =new Plant();
//		plant.getTicketnumber();
//		plant.getTopTicketNum();
//		new BookingTicketFrame(plant);
//	}

}
