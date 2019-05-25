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
		public JButton deleteBtn = new JButton("ɾ��");
		public ResultPanel() {
			add(new JLabel("�������:"));
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
							"ȷ��Ҫȡ�����º�����\n" + msg, "ע��",
							JOptionPane.YES_NO_OPTION);//����0��ʾѡ��
					if(flag == 0) {
						new FlightDeleteDriver().deleteAll(flights);
						//socket֪ͨ�ͻ�
						
						JOptionPane.showMessageDialog(null,
								"ȡ���ɹ�!����֪ͨ���й���˴κ���ĳ˿�");
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
