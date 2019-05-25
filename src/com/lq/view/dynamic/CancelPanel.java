package com.lq.view.dynamic;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.lq.common.format.FlightSearchResultFormat;
import com.lq.model.Flight;
import com.lq.sql.*;

public class CancelPanel extends JPanel implements ListenPanel{
	private IdSearchPanel idSearchPanel = new IdSearchPanel();
	private PlaceSearchPanel placeSearchPanel = new PlaceSearchPanel();
	
	class ResultPanel extends JPanel{
		public DefaultListModel listmode = new DefaultListModel();
		public JList result = new JList(listmode);
		public JButton deleteBtn = new JButton("删除");
		public ResultPanel() {
			add(new JLabel("搜索结果:"));
			result.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			add(result);
			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String msg = "";
					ArrayList<String> flights = new ArrayList<String>();
					for(Object item:result.getSelectedValuesList()) {
						String tmp_s = (String)item;
						flights.add(tmp_s.split(" ")[0]);
						msg += tmp_s + "\n";
					}		
					int flag = JOptionPane.showConfirmDialog(null,
							"确定要取消以下航班吗？\n" + msg, "注意",
							JOptionPane.YES_NO_OPTION);//返回0表示选是
					if(flag == 0) {
						new FlightDeleteDriver().deleteAll(flights);
						//socket通知客户
						
						JOptionPane.showMessageDialog(null,
								"取消成功!并已通知所有购买此次航班的乘客");
					}
				}
			});
			add(deleteBtn);
		}
		public void setResult(ArrayList<Flight> flights) {
			for(Flight f:flights) {			
				listmode.addElement(new FlightSearchResultFormat().getFormat(f));
			}
		}
	}
	
	private ResultPanel resultPanel = new ResultPanel();	
	private SurePanel surePanel = new SurePanel(this);
	public CancelPanel() {
		setBackground(Color.blue);
		idSearchPanel.setBounds(0,10,500,20);
		placeSearchPanel.setBounds(0,80,500,20);
		surePanel.setBounds(0,120,500,20);
		resultPanel.setBounds(0,160,500,20);
		add(idSearchPanel);
		add(placeSearchPanel);
		add(surePanel);
		add(resultPanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		idSearchPanel.commit();
		placeSearchPanel.commit();
		resultPanel.listmode.clear();
		if(idSearchPanel.id.equals("") && 
			(placeSearchPanel.takeoff.equals("") || 
				placeSearchPanel.arrive.equals(""))) {
			JOptionPane.showMessageDialog(null,"请输入查询条件!");
		}
		else if(!idSearchPanel.id.equals("") &&
				!(placeSearchPanel.takeoff.equals("") || 
						placeSearchPanel.arrive.equals(""))) {
			JOptionPane.showMessageDialog(null,"查询条件过多!");
		}
		else if(!idSearchPanel.id.equals("")) {
			ArrayList<Flight> res = 
				new FlightSearchDriver().searchById_base(idSearchPanel.id);
			if(res.isEmpty()) {
				idSearchPanel.addNoResult();
			}
			else {
				resultPanel.setResult(res);
			}
		}
		else if(!(placeSearchPanel.takeoff.equals("") || 
				placeSearchPanel.arrive.equals(""))) {
			ArrayList<Flight> res = 
					new FlightSearchDriver().searchByPlace_base(
					placeSearchPanel.takeoff,placeSearchPanel.arrive);
			if(res.isEmpty()) {
				idSearchPanel.addNoResult();
			}
			else {
				resultPanel.setResult(res);
			}
		}
	}
}
