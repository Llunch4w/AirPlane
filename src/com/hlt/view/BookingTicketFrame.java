package com.hlt.view;

import javax.swing.*;

import com.lq.model.Flight;

public class BookingTicketFrame extends JFrame{
	Flight flight;
	private JPanel pan_plant = new JPanel();
	private JLabel path = new JLabel("长春--郑州");
	private JLabel date = new JLabel("06-21 周五");
	private JButton sort = new JButton("排序");
	private  JButton but_return = new JButton("返回");
	private JButton calender = new JButton("");
	private JLabel lab_topSeat = new JLabel ("头等舱");
	private JLabel lab_norSeat = new JLabel("经济舱");
//	private JLabel lab_topTicket = new JLabel("余票: "+flight.getTopTicketNum());
//	private JLabel lab_norTicket = new JLabel("余票: "+(plant.getTicketnumber()-plant.getTopTicketNum()));
	BookingTicketFrame(Flight flight){
		setTitle("订票");
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
