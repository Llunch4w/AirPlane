package com.lq.view.dynamic;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import com.lq.common.format.FlightInsertFormat;
import com.lq.common.format.FlightSearchResultFormat;
import com.lq.dynamicManage.Admin;
import com.lq.model.Flight;
import com.lq.view.AdminWindow;
import com.lq.sql.FlightSearchDriver;

public class DynamicManage extends AdminWindow implements ActionListener{
	private Admin theAdmin = null;
	private ContentPanel content = new ContentPanel();
	private NavPanel navigator = new NavPanel(this);
	public DynamicManage(Admin admin) {
		super("��̬������");
		setLayout(new BorderLayout());
		add(navigator,BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
		showing(500,300);
		theAdmin = admin;
	}
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String next = btn.getText();
		System.out.println(next);
		content.changeTo(next);	
	}
	public void need(String s) {//��ת����
		theAdmin.toWindow(s);
	}
}

class NavPanel extends JPanel{
	private DynamicManage parent;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JButton backBtn = new JButton("������һ��");
	public NavPanel(DynamicManage admin) {
		parent = admin;
		String tmp[] = {"�����������","����ȡ������"};
		for(int i = 0;i < tmp.length;i++) {
			JButton btn = new JButton(tmp[i]);
			btn.addActionListener(parent);
			buttons.add(btn);
			this.add(btn);
		}
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				parent.need("base manage");
			}
		});
		add(backBtn);
	}
}

class ContentPanel extends JPanel{
	private DynamicManage parent;
	private Map<String,JPanel> map = new HashMap<String,JPanel>();
	private CardLayout cardLayout = new CardLayout();//��Ƭ����
	public ContentPanel() {
//		setBackground(Color.BLUE);
		map.put("�����������", new DelayPanel());
		map.put("����ȡ������", new CancelPanel());
		this.setLayout(cardLayout);
		for(Map.Entry<String, JPanel> entry:map.entrySet()) {
			this.add(entry.getKey(),entry.getValue());
			entry.getValue().setVisible(false);
		}
	}
	public void changeTo(String next) {
		cardLayout.show(this,next);
	}
}
