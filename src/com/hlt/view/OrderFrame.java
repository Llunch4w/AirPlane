package com.hlt.view;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import com.lq.dynamicManage.User;

public class OrderFrame extends JFrame{
	private User user;
	private JButton return_but = new JButton("����<");
	private JButton yes_but = new JButton("ȷ��");
	
	private JPanel flightMessage_panel = new JPanel();//������Ϣ���
	private JButton flightMBut = new JButton("��������");
	
	private JPanel  flyPerson_panel = new JPanel();//�˻������
	private JLabel  flyPerson_lab = new JLabel("�˻���");
	private JLabel  flyPersoninfolab = new JLabel("��ȷ���˻���������֤����Ϣ��ȷ");
	
	private JPanel  flyPerson_panel2 = new JPanel();//�˻������
	private JButton addflyPersonBut = new JButton("����");//�����˻���
	private JLabel chooseFlyPerson = new JLabel("ѡ��˻���");
	private JLabel rNameLab =new JLabel   ("��      ��:");
	private JLabel idCardLab = new JLabel ("���֤��:");
	private JLabel phoneNumLab =new JLabel("��  �� ��:");
	private JTextField rNameText = new JTextField(20);//������
	private JTextField idCardText= new JTextField(20); //���֤��
	private JTextField phoneNumText= new JTextField(20); //�ֻ�����
	private JButton load_but = new JButton("����");
	
	private JButton pay_but = new JButton("ȥ����");//����
	public OrderFrame(User user){
		this.user = user;
		add(return_but);
		add(yes_but);
		return_but.setBounds(5, 5, 80, 30);
		yes_but.setBounds(505, 5, 60, 30);
		
		
		setLayout(new BorderLayout());
		
		
		add(flightMessage_panel,BorderLayout.NORTH);
		flightMessage_panel.add(flightMBut);
		//flightMessage_panel.setLayout(null);
		flightMessage_panel.setBounds(5, 5, 60, 30);
		//flightMessage_panel.setBounds();
		
		add(flyPerson_panel,BorderLayout.CENTER);
		flyPerson_panel.add(addflyPersonBut);//����
		addflyPersonBut.setContentAreaFilled(false);//��ť����Ϊ͸��
		//addflyPersonBut.setBorderPainted(false);//��ťȥ���߿�
		flyPerson_panel.add(chooseFlyPerson);//ѡ��˻���
	//
		
		
		
		flyPerson_panel2.setBorder(BorderFactory.createLoweredBevelBorder());//�������߿���
		flyPerson_panel2.setVisible(false);
		flyPerson_panel2.setSize(400,200);
		flyPerson_panel2.add(rNameLab);
		flyPerson_panel2.add(rNameText);
		flyPerson_panel2.add(idCardLab);
		flyPerson_panel2.add(idCardText);
		flyPerson_panel2.add(phoneNumLab);
		flyPerson_panel2.add(phoneNumText);
		flyPerson_panel2.add(load_but);
		flyPerson_panel2.setLayout(null);
		Rectangle r = new Rectangle(55,5,100,20);
		Rectangle r1 = new Rectangle(165,5,200,20);
		rNameLab.setBounds(r);
		rNameText.setBounds(r1);
		r.y+=30;r1.y+=30;
		idCardLab.setBounds(r);
		idCardText.setBounds(r1);
		r.y+=30;r1.y+=30;
		phoneNumLab.setBounds(r);
		phoneNumText.setBounds(r1);
		load_but.setBounds(200, 90, 60, 30);
		//��������
		return_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==return_but)
					if(flyPerson_panel2.getVisibleRect().equals(true))
						{flyPerson_panel2.setVisible(false);
				        flyPerson_panel.setVisible(true);}
					else {
						dispose();
//						new MainFrame();
						user.toWindow("main menu");
					}
			}
			
			}
		);
		//�������ӳ˻���
		addflyPersonBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==addflyPersonBut)
					flyPerson_panel.setVisible(false);
				    flyPerson_panel2.setVisible(true);	
				    add(flyPerson_panel2,BorderLayout.CENTER);
					
			}
		});
		//��������
		load_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==load_but)
					flyPerson_panel.setVisible(false);
				    flyPerson_panel2.setVisible(true);	
				    add(flyPerson_panel2,BorderLayout.CENTER);
					
			}
		});
		pack();
		setLocation(600,400);
		setSize(600,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("����");
	}
	
//	public static void main(String[] args) {
//		new OrderFrame();
//	}
}