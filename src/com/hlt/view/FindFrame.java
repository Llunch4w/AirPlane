package com.hlt.view;

import java.awt.BorderLayout;

import javax.swing.*;

import com.lq.dynamicManage.User;

public class FindFrame extends JFrame{
	private User user;
	private JButton btn_panel_find;
	private JButton yes = new JButton("ȷ��");
	private JButton no = new JButton("ȡ��");
	private JLabel startlab = new JLabel   ("���:");
	private JLabel endlab = new JLabel   ("�յ�:");
	private JLabel rNameLab =new JLabel   ("��      ��:");
	private JLabel idCardLab = new JLabel ("���֤��:");
	private JLabel phoneNumLab =new JLabel("��  �� ��:");
	private JTextField startText = new JTextField(10);//�����
	private JPasswordField EndText = new JPasswordField(20);//�յ���
	private JTextField rNameText = new JTextField(20);//������
	private JTextField idCardText= new JTextField(20); //���֤��
	private JTextField phoneNumText= new JTextField(20); //�ֻ�����
	private JLabel infoLab = new JLabel(" ");//��ʾ��
	        JPanel find_Panel = new JPanel();
	    
	public FindFrame(User user){
		this.user = user;
	    find_Panel.setLayout(new BorderLayout()); 
		JLabel startlab = new JLabel("���");
		startlab.setBounds(61, 66, 54, 15);
		find_Panel.add(startlab, BorderLayout.NORTH);

		JTextField starttxt = new JTextField();
		starttxt .setBounds(104, 63, 93, 21);
		find_Panel.add(starttxt, BorderLayout.NORTH);


		JLabel endlab = new JLabel("�յ�");
		find_Panel.add(endlab, BorderLayout.NORTH);
		endlab.setBounds(271, 66, 54, 15);
		
		JTextField endtext = new JTextField();
		endtext.setBounds(300, 63, 85, 22);
	    find_Panel.add(endtext, BorderLayout.NORTH);
		
		JLabel timelab = new JLabel("ʱ��");
		find_Panel.add(timelab, BorderLayout.NORTH);
		timelab.setBounds(479, 66, 54, 15);
		
		JTextField starttimeText = new JTextField();
		starttimeText.setBounds(512, 63, 102, 22);
		find_Panel.add(starttimeText, BorderLayout.NORTH);
		
		btn_panel_find = new JButton("��ѯ");
		btn_panel_find.setIcon(new ImageIcon("images/��ѯ1.png"));
		btn_panel_find.setBounds(651, 62, 93, 23);
		find_Panel.add(btn_panel_find, BorderLayout.SOUTH);

		add(find_Panel);
		pack();
		setLocation(600,400);
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("��ѯ");
	    }

}