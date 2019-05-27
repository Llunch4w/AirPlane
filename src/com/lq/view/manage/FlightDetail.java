package com.lq.view.manage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.lq.model.Flight;

public class FlightDetail extends JFrame{
	private Flight flight;
	private BasePanel basePanel;
	public FlightDetail(Flight flight) {
		super(flight.getId() + "航班详情页");
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
			add(new JLabel(flight.getId()+"号航班"));
			add(new JLabel(flight.getCompany()));
			add(new JLabel(flight.getBuilding() +"航站楼"));
			add(new JLabel(flight.getPlanetype()));
			add(new JLabel(flight.getWeek()));
		}
	}
	private class SecondPanel extends JPanel{
		public SecondPanel() {
			setBounds(0,40,400,40);
//			setBackground(Color.yellow);
			add(new JLabel("起飞地：" + flight.getTakeoffPlace()));
			add(new JLabel("起飞时间：" + flight.getStartTime().getPlanTime()));
		}
	}
	private class ThirdPanel extends JPanel{
		public ThirdPanel() {
			setBounds(0,80,400,40);
//			setBackground(Color.red);
			add(new JLabel("目的地：" + flight.getArrivePlace()));
			add(new JLabel("到达时间：" + flight.getArriveTime().getPlanTime()));
		}
	}
	
	private class TransPanel extends JPanel{
		public boolean isTrans;
		public TransPanel(boolean isTrans) {
			this.isTrans = isTrans;
			setBounds(0,120,400,40);
			if(isTrans == true) {
				add(new JLabel("中转：" + flight.getTransPlace()));
				add(new JLabel("中转到达时间：" + flight.getTransArriveTime().getPlanTime()));
				add(new JLabel("中转起飞时间：" + flight.getTransLeaveTime().getPlanTime()));
			}
			else {
				add(new JLabel("无中转"));
			}
		}
	}
	
	private class PricePanel extends JPanel{
		public PricePanel() {
			setBounds(0,160,400,40);
			add(new JLabel("成人票价：" + flight.getAdultprice()));
			add(new JLabel("儿童票价：" + flight.getKidprice()));
		}
	}
	
	private class CapacityPanel extends JPanel{
		public CapacityPanel() {
			setBounds(0,200,400,40);
			int capacity = flight.getContainer().getCapacity();
			int remain = flight.getContainer().getReamin();
			add(new JLabel("总容量：" + capacity));
			add(new JLabel("已选座位数：" + (capacity-remain)));
			add(new JLabel("剩余座位数：" + remain));
			JButton btn = new JButton("座位表");
			btn.setSize(40,20);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeatDetail seatDetail = new SeatDetail();
					seatDetail.showing(400, 400);
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
		setBounds(0,0,400,240);
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
