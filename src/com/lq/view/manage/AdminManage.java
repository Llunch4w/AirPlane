package com.lq.view.manage;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.lq.sql.PlaceSqlDriver;
import com.lq.view.AdminWindow;
import com.lq.dynamicManage.*;
import com.lq.common.time.*;
import com.lq.model.Flight;
import com.lq.sql.FlightSaveDriver;

public class AdminManage extends AdminWindow implements ActionListener{
	private ContentPanel content = new ContentPanel();
	private NavPanel navigator = new NavPanel(this);
	private Admin theAdmin = null;
	public AdminManage(Admin admin) {
		super("����Ա����");
		setLayout(new BorderLayout());
		add(navigator,BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
		showing(500,400);
		theAdmin = admin;
	}
	public void actionPerformed(ActionEvent e) {//��������������
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
	private AdminManage parent;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JButton dyNamicManageBtn = new JButton("��̬����");
	private JButton exitBtn = new JButton("�˳�");
	public NavPanel(AdminManage admin) {
		parent = admin;
		String tmp[] = {"����","ɾ��","�޸�","��ѯ"};
		for(int i = 0;i < tmp.length;i++) {
			JButton btn = new JButton(tmp[i]);
			btn.addActionListener(parent);
			buttons.add(btn);
			this.add(btn);
		}
		dyNamicManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				parent.need("dynamic manage");
			}
		});
		this.add(dyNamicManageBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.need("login");
			}
		});
		add(exitBtn);
	}
}

class ContentPanel extends JPanel{
	private AdminManage parent;
	private Map<String,JPanel> map = new HashMap<String,JPanel>();
	private CardLayout cardLayout = new CardLayout();//��Ƭ����
	public ContentPanel() {
//		setBackground(Color.BLUE);
		map.put("����", new AddPanel());
		map.put("ɾ��", new DeletePanel());
		map.put("�޸�", new ModifyPanel());
		map.put("��ѯ", new SearchPanel());
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

