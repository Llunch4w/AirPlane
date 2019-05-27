package com.hlt.view;

import javax.swing.*;

import com.lq.dynamicManage.User;

public class ReturnTicketFrame extends JFrame{
	private User user;
	private JLabel rNameLab =new JLabel   ("姓      名:");
	private JLabel idCardLab = new JLabel ("身份证号:");
	private JLabel infoLab = new JLabel("请输入您的信息查询订单");
	public ReturnTicketFrame(User user) {
		this.user = user;
	}
}