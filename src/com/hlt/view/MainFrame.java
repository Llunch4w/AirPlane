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
	private JButton btn_find = new JButton("����");//����
	private JButton btn_order = new JButton("����");//����
	private JButton btn_message = new JButton("��Ϣ");//��Ϣ
	private JButton btn_user = new JButton("������Ϣ");
	private JButton btn_ticketGrabing = new JButton("��Ʊ");//��Ʊ
	private JLabel  lab_welcome = new JLabel("����������ӭʹ�ú��ն�Ʊϵͳ��������");//��ӭ��ǩ
	private JLabel  lab_time = new JLabel(" ");//ʱ���ǩ
	private JButton btn_return = new JButton("�˳���¼");//����
	public MainFrame(User user){
		this.user = user;
		setLayout(new BorderLayout());
		
		add(pan_time,BorderLayout.NORTH);
		pan_time.add(lab_welcome);
		pan_time.add(lab_time);
		lab_welcome.setBounds(40, 5, 60, 40);
		TimeThread t = new TimeThread(lab_time);
		new Thread(t).start();
		
		//��������
		Font font =new Font("Serief",Font.BOLD,14);
		btn_find.setFont(font);
		btn_order.setFont(font);
		btn_user.setFont(font);
		btn_ticketGrabing.setFont(font);
		lab_welcome.setFont(font);
		lab_time.setFont(font);
		//���������
		add(pan_main,BorderLayout.SOUTH);
		btn_find.setIcon(new ImageIcon("images/��ѯ1.png"));
		btn_find.setSize(100, 100);
		btn_order.setIcon(new ImageIcon("images/order.png"));
		btn_order.setSize(100, 100);
		btn_ticketGrabing.setIcon(new ImageIcon("images/��Ʊ.png"));
		btn_ticketGrabing.setSize(100, 100);
     	btn_user.setIcon(new ImageIcon("images/user.png"));
		btn_user.setSize(100, 100);
		pan_main.add(btn_find);
		pan_main.add(btn_ticketGrabing);
		pan_main.add(btn_order);
		pan_main.add(btn_user);
	    
	    //���ñ���ͼƬ
	    String picpath="images/2.jpg";
		ImageIcon background= new ImageIcon(picpath);
		JLabel lab=new JLabel(background);
		JPanel imagePanel=(JPanel) this.getContentPane();
		lab.setBounds(80,35, background.getIconWidth(), background.getIconHeight());
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(lab,new Integer(Integer.MIN_VALUE));
		
		//��������
	    setTitle("������");
		setVisible(true);
		setLocation(300,400);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô��ڹر�
		//���ü���
		//����
		btn_find.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_find) {
					dispose();
					user.toWindow("find page");
				}
				
			}
		});
		//����
		btn_order.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_order) {
					dispose();
//					new MyOrderFrame();
					user.toWindow("order page");
				}
				
			}
		});
		//������Ϣ
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