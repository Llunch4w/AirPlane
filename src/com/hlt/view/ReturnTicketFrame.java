package com.hlt.view;

import javax.swing.*;

import com.lq.dynamicManage.User;

public class ReturnTicketFrame extends JFrame{
	private User user;
	private JLabel rNameLab =new JLabel   ("��      ��:");
	private JLabel idCardLab = new JLabel ("���֤��:");
	private JLabel infoLab = new JLabel("������������Ϣ��ѯ����");
	public ReturnTicketFrame(User user) {
		this.user = user;
	}
}