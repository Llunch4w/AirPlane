package com.hlt.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//import MySql.*;
import com.lq.dynamicManage.User;
import com.lq.requests.CheckRepeatId;
import com.lq.client.*;
import com.hlt.model.UserMe;

public class RegistFrame extends JFrame {
	private User user;
	private Client client;
	private JPanel RegistPanel = new JPanel();
//	RegistSql sql = new RegistSql();
	private JButton regist = new JButton("注册");
	private JButton returnn = new JButton("返回");
	private JLabel idlab = new JLabel   ("用  户 名:");
	private JLabel passlab = new JLabel   ("密      码:");
	private JLabel passlab2 = new JLabel  ("确认密码:");
	private JLabel nameLab =new JLabel   ("姓      名:");
	private JLabel idCardLab = new JLabel ("身份证号:");
	private JLabel phoneNumLab =new JLabel("手  机 号:");
	private JTextField idText = new JTextField(10);//用户名条
	private JPasswordField passText = new JPasswordField(20);//密码条
	private JPasswordField passText2 = new JPasswordField(20);//确认密码条
	private JTextField nameText = new JTextField(20);//姓名条
	private JTextField idCardText= new JTextField(20); //身份证条
	private JTextField phoneNumText= new JTextField(20); //手机号条
	private JLabel infoLab = new JLabel(" ");//提示条
	private void setLoca(Rectangle r,Rectangle r1,int x,int y) {
		r.y+=y;
		r1.y+=y;
		r.x+=x;
		r1.x+=x;
	}
	public RegistFrame(User user){
		this.user = user;
		this.client = user.getClient();
		Font fnt =new Font("serief",Font.BOLD,15);
		idlab.setFont(fnt);
		passlab.setFont(fnt);
		passlab2.setFont(fnt);
		nameLab.setFont(fnt);
		idCardLab.setFont(fnt);
		phoneNumLab.setFont(fnt);
		regist.setFont(fnt);
		returnn.setFont(fnt);
		setLayout(null);
		Rectangle r = new Rectangle(25,5,100,20);
		Rectangle r1 = new Rectangle(125,5,200,20);
		idlab.setBounds(r);
		idText.setBounds(r1);
		infoLab.setBounds(325,5,200,20);
		setLoca(r,r1,0,30);
		passlab.setBounds(r);
		passText.setBounds(r1);
		r.y=r.y+30;r1.y=r1.y+30;
		passlab2.setBounds(r);
		passText2.setBounds(r1);
		r.y=r.y+30;r1.y=r1.y+30;
		nameLab.setBounds(r);
		nameText.setBounds(r1);
		r.y=r.y+30;r1.y=r1.y+30;
		idCardLab.setBounds(r);
		idCardText.setBounds(r1);
		r.y=r.y+30;r1.y=r1.y+30;
		phoneNumLab.setBounds(r);
		phoneNumText.setBounds(r1);
		regist.setBounds(125,200,75,30);
		returnn.setBounds(220, 200, 75, 30);
		//第一行
		add(idlab);
		add(idText);
		add(infoLab);
		//第二行
		add(passlab);
		add(passText);
		//第三行
		add(passlab2);	
		add(passText2);
		//第四行
		add(nameLab);
		add(nameText);
		//第五行
		add(idCardLab);
		add(idCardText);
		//第六行
		add(phoneNumLab);
		add(phoneNumText);
		//注册
		add(regist);
		add(returnn);
		//用户名重复提示
		//监听注册
		setLocation(600, 400);
		setTitle("注册");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==regist) {
					String username = idText.getText();
					CheckRepeatId req = new CheckRepeatId(username);
					if(!client.sendYONq(req))
						infoLab.setText("用户名已存在，请重新输入！");
					else {					
						if(passText.getText().equals(passText2.getText())){	
							infoLab.setBounds(125,250,200,20);
							UserMe newUser = 
									new UserMe(idText.getText(),
											passText.getText(),idCardText.getText(),
											nameText.getText(),phoneNumText.getText());
							client.sendNewUser(newUser);
							JOptionPane.showMessageDialog(null,"注册成功！");
							dispose();
							user.toWindow("main menu");
						}
					}
								
				}
			}
		});
		//监听返回
		returnn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==returnn) {
					dispose();
					user.toWindow("login");
				}
			}
		});
	}
}
