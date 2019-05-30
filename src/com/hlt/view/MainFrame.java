package com.hlt.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.lq.dynamicManage.User;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	User user;
	private JPanel  pan_main= new JPanel();
	private JPanel  pan_time= new JPanel();
	private JButton btn_find = new JButton("查找");//查找
	private JButton btn_order = new JButton("订单");//订单
	private JButton btn_message = new JButton("消息");//消息
	private JButton btn_user = new JButton("个人信息");
	private JButton btn_ticketGrabing = new JButton("抢票");//抢票
	private JLabel  lab_welcome = new JLabel("————欢迎使用航空订票系统————");//欢迎标签
	private JLabel  lab_time = new JLabel(" ");//时间标签
	private JButton btn_return = new JButton("退出登录");//返回
	public MainFrame(User user){
		this.user = user;
		setLayout(new BorderLayout());
		
		add(pan_time,BorderLayout.NORTH);
		pan_time.add(lab_welcome);
		pan_time.add(lab_time);
		lab_welcome.setBounds(40, 5, 60, 40);
		TimeThread t = new TimeThread(lab_time);
		new Thread(t).start();
		
		//设置字体
		Font font =new Font("Serief",Font.BOLD,14);
		btn_find.setFont(font);
		btn_order.setFont(font);
		btn_user.setFont(font);
		btn_ticketGrabing.setFont(font);
		lab_welcome.setFont(font);
		lab_time.setFont(font);
		//设置主面板
		add(pan_main,BorderLayout.SOUTH);
		btn_find.setIcon(new ImageIcon("images/查询1.png"));
		btn_find.setSize(100, 100);
		btn_order.setIcon(new ImageIcon("images/order.png"));
		btn_order.setSize(100, 100);
		btn_ticketGrabing.setIcon(new ImageIcon("images/抢票.png"));
		btn_ticketGrabing.setSize(100, 100);
     	btn_user.setIcon(new ImageIcon("images/user.png"));
		btn_user.setSize(100, 100);
		pan_main.add(btn_find);
		pan_main.add(btn_ticketGrabing);
		pan_main.add(btn_order);
		pan_main.add(btn_user);
	    
	    //设置背景图片
	    String picpath="images/2.jpg";
		ImageIcon background= new ImageIcon(picpath);
		JLabel lab=new JLabel(background);
		JPanel imagePanel=(JPanel) this.getContentPane();
		lab.setBounds(80,35, background.getIconWidth(), background.getIconHeight());
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(lab,new Integer(Integer.MIN_VALUE));
		
		//常规设置
	    setTitle("主界面");
		setVisible(true);
		setLocation(300,400);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭
		//设置监听
		//查找
		btn_find.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_find) {
					dispose();
					user.toWindow("find page");
				}
				
			}
		});
		//订单
		btn_order.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_order) {
					dispose();
//					new MyOrderFrame();
					user.toWindow("order page");
				}
				
			}
		});
		//个人信息
		btn_user.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_user) {
					dispose();
					user.toWindow("user page");
				}
				
			}
		});
	}

}