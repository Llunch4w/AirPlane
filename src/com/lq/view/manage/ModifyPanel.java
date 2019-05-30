package com.lq.view.manage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lq.common.format.FlightSearchResultFormat;
import com.lq.model.Flight;
import com.lq.sql.FlightDeleteDriver;
import com.lq.sql.FlightSearchDriver;
import com.lq.view.common.IdSearchPanel;
import com.lq.view.common.ListenPanel;
import com.lq.view.common.PlaceSearchPanel;
import com.lq.view.common.SurePanel;
import com.lq.view.dynamic.FlightDetail;
import com.lq.view.manage.DeletePanel.ResultPanel;

public class ModifyPanel extends JPanel implements ListenPanel{
	private IdSearchPanel idSearchPanel = new IdSearchPanel();
	private PlaceSearchPanel placeSearchPanel = new PlaceSearchPanel();
	
	class ResultPanel extends JPanel{
		public DefaultListModel listmode = new DefaultListModel();
		public JList result = new JList(listmode);
		public ResultPanel() {
			add(new JLabel("搜索结果:"));
			result.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			add(result);
			result.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting())
						return;
					/*
					 * 如果检测到事件在更改，则返回true，后面语句不执行
					 * 当更改结束后，则返回false，执行后面语句
					 * */
					for(Object item:result.getSelectedValuesList()) {
						try {						
							System.out.println(item);
							String tmp_s = (String)item;
							String[] temp = tmp_s.split(" ");
							FlightSearchDriver f = new FlightSearchDriver();
							Flight flight = f
									.searchById_base(temp[0]);
							f.addDetail(flight);
							
							ModifyPage detailPage = new ModifyPage(flight);
							detailPage.showing(800, 600);
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
		public void setResult(Flight flight) {
			listmode.addElement(new FlightSearchResultFormat().getFormat(flight));
		}
	}
	
	private ResultPanel resultPanel = new ResultPanel();	
	private SurePanel surePanel = new SurePanel(this);
	public ModifyPanel() {
		setBackground(Color.pink);
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
			Flight res = 
				new FlightSearchDriver().searchById_base(idSearchPanel.id);
			if(res == null) {
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

