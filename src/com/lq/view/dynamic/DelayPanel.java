package com.lq.view.dynamic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import com.lq.common.format.*;
import com.lq.model.Flight;
import com.lq.sql.*;

public class DelayPanel extends JPanel implements ListenPanel{
	
	private IdSearchPanel idSearchPanel = new IdSearchPanel();
	private PlaceSearchPanel placeSearchPanel = new PlaceSearchPanel();
	
	class ResultPanel extends JPanel{
		public DefaultListModel listmode = new DefaultListModel();
		public JList result = new JList(listmode);
		public ResultPanel() {
			add(new JLabel("�������:"));
			add(result);
			result.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting())
						return;
					/*
					 * �����⵽�¼��ڸ��ģ��򷵻�true��������䲻ִ��
					 * �����Ľ������򷵻�false��ִ�к������
					 * */
					for(Object item:result.getSelectedValuesList()) {
						try {						
							System.out.println(item);
							String tmp_s = (String)item;
							String[] temp = tmp_s.split(" ");
							Flight flight = new FlightSearchDriver()
									.searchById_base(temp[0]).get(0);
							FlightDetail detailPage = new FlightDetail(flight);
							detailPage.showing(400, 600);
						}catch(Exception exc) {
							break;
						}
					}			
				}		
			});
		}
		public void setResult(ArrayList<Flight> flights) {
			for(Flight f:flights) {			
				listmode.addElement(new FlightSearchResultFormat().getFormat(f));
			}
		}
	}
	
	private ResultPanel resultPanel = new ResultPanel();	
	private SurePanel surePanel = new SurePanel(this);
	

	
	public DelayPanel() {
		setBackground(Color.red);
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
			JOptionPane.showMessageDialog(null,"�������ѯ����!");
		}
		else if(!idSearchPanel.id.equals("") &&
				!(placeSearchPanel.takeoff.equals("") || 
						placeSearchPanel.arrive.equals(""))) {
			JOptionPane.showMessageDialog(null,"��ѯ��������!");
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