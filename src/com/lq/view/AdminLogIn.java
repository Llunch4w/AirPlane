package com.lq.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.lq.dynamicManage.Admin;

public class AdminLogIn extends AdminWindow{
	private JTextField workID = new JTextField(30);
	private JPasswordField pwd = new JPasswordField(30);
	private JButton makeSure = new JButton("µ«¬º");
	public AdminLogIn(Admin admin) {
		super("π‹¿Ì‘±µ«¬º");
		setLayout(new FlowLayout());
		add(new JLabel("’À∫≈£∫"));
		add(workID);
		add(new JLabel("√‹¬Î£∫"));
		add(pwd);
		makeSure.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				admin.check(workID.getText(),pwd.getText());
			}
		});
		add(makeSure);
		setSize(300,400);
		showing(400,200);
	}
}
