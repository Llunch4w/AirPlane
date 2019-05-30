package com.lq.view.manage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.lq.model.Flight;

public class FlightDetail extends JFrame{
	private Flight flight;
	private BasePanel basePanel;
	public FlightDetail(Flight flight) {
		super(flight.getId() + "��������ҳ");
		this.flight = flight;
		setLayout(null);
		basePanel = new BasePanel(flight);
		add(basePanel);
		
	}
	public void showing(int width,int height) {
		//w:400;h:600
		setLocation(500,100);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}

class BasePanel extends JPanel{
	private Flight flight;
	private class FirstPanel extends JPanel{
		public FirstPanel() {
			setBounds(0,0,400,40);
//			setBackground(Color.blue);
			add(new JLabel(flight.getId()+"�ź���"));
			add(new JLabel(flight.getCompany()));
			add(new JLabel(flight.getBuilding() +"��վ¥"));
			add(new JLabel(flight.getPlanetype()));
			add(new JLabel(flight.getWeek()));
		}
	}
	private class SecondPanel extends JPanel{
		public SecondPanel() {
			setBounds(0,40,400,40);
//			setBackground(Color.yellow);
			add(new JLabel("��ɵأ�" + flight.getTakeoffPlace()));
			add(new JLabel("���ʱ�䣺" + flight.getStartTime().getPlanTime()));
		}
	}
	private class ThirdPanel extends JPanel{
		public ThirdPanel() {
			setBounds(0,80,400,40);
//			setBackground(Color.red);
			add(new JLabel("Ŀ�ĵأ�" + flight.getArrivePlace()));
			add(new JLabel("����ʱ�䣺" + flight.getArriveTime().getPlanTime()));
		}
	}
	
	private class TransPanel extends JPanel{
		public boolean isTrans;
		public JLabel transPlace;
		public JLabel arriveTime;
		public JLabel leaveTime;
		public TransPanel(boolean isTrans) {
			this.isTrans = isTrans;
			setBounds(0,120,400,80);
			if(isTrans == true) {
				setLayout(null);
				transPlace = new JLabel("��ת�أ�" + flight.getTransPlace());
				arriveTime = new JLabel("��ת����ʱ�䣺" + flight.getTransArriveTime().getPlanTime());
				leaveTime = new JLabel("��ת���ʱ�䣺" + flight.getTransLeaveTime().getPlanTime());	
				add(transPlace);
				add(arriveTime);
				add(leaveTime);
				transPlace.setBounds(60,0,100,20);
				arriveTime.setBounds(60,25,400,20);
				leaveTime.setBounds(60,50,400,20);
			}
			else {
				add(new JLabel("����ת"));
			}
		}
	}
	
	private class PricePanel extends JPanel{
		public PricePanel() {
			setBounds(0,200,400,40);
			add(new JLabel("���ˣ�" + flight.getAdultprice()));
			add(new JLabel("��ͯ��" + flight.getKidprice()));
			add(new JLabel("����գ�" + flight.getTopprice()));
			add(new JLabel("�ۿۣ�" + flight.getDiscount() + "��"));
		}
	}
	
	private class CapacityPanel extends JPanel{
		public CapacityPanel() {
			setBounds(0,240,400,40);
			int capacity = flight.getContainer().getCapacity();
			int remain = flight.getContainer().getReamin();
			add(new JLabel("��������" + capacity));
			add(new JLabel("��ѡ��λ����" + (capacity-remain)));
			add(new JLabel("ʣ����λ����" + remain));
			JButton btn = new JButton("��λ��");
			btn.setSize(40,20);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeatDetail seatDetail = new SeatDetail(flight);
					seatDetail.showing(530,500);
				}
			});
			add(btn);
		}
	}
	
	private FirstPanel firstLayer;
	private SecondPanel secondLayer;
	private ThirdPanel thirdLayer;
	private TransPanel transLayer;
	private PricePanel priceLayer;
	private CapacityPanel capacityLayer;
	public BasePanel(Flight flight) {
		this.flight = flight;
		setBounds(0,0,400,300);
		setLayout(null);
		firstLayer = new FirstPanel();
		secondLayer = new SecondPanel();
		thirdLayer = new ThirdPanel();
		transLayer = new TransPanel(flight.isTrans());
		priceLayer = new PricePanel();
		capacityLayer = new CapacityPanel();
		add(firstLayer);
		add(secondLayer);
		add(thirdLayer);
		add(transLayer);
		add(priceLayer);
		add(capacityLayer);
	}
}
