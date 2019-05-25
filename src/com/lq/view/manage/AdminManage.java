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
		super("管理员窗口");
		setLayout(new BorderLayout());
		add(navigator,BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
		showing(500,400);
		theAdmin = admin;
	}
	public void actionPerformed(ActionEvent e) {//导航栏监听处理
		JButton btn = (JButton)e.getSource();
		String next = btn.getText();
		System.out.println(next);
		content.changeTo(next);	
	}
	public void need(String s) {//跳转窗口
		theAdmin.toWindow(s);
	}
}

class NavPanel extends JPanel{
	private AdminManage parent;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JButton dyNamicManageBtn = new JButton("动态管理");
	private JButton exitBtn = new JButton("退出");
	public NavPanel(AdminManage admin) {
		parent = admin;
		String tmp[] = {"新增","删除","修改","查询"};
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
	private CardLayout cardLayout = new CardLayout();//卡片布局
	public ContentPanel() {
//		setBackground(Color.BLUE);
		map.put("新增", new AddPanel());
		map.put("删除", new DeletePanel());
		map.put("修改", new ModifyPanel());
		map.put("查询", new SearchPanel());
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

