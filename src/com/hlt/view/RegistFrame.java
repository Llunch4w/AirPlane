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
	private JButton regist = new JButton("ע��");
	private JButton returnn = new JButton("����");
	private JLabel idlab = new JLabel   ("��  �� ��:");
	private JLabel passlab = new JLabel   ("��      ��:");
	private JLabel passlab2 = new JLabel  ("ȷ������:");
	private JLabel nameLab =new JLabel   ("��      ��:");
	private JLabel idCardLab = new JLabel ("���֤��:");
	private JLabel phoneNumLab =new JLabel("��  �� ��:");
	private JTextField idText = new JTextField(10);//�û�����
	private JPasswordField passText = new JPasswordField(20);//������
	private JPasswordField passText2 = new JPasswordField(20);//ȷ��������
	private JTextField nameText = new JTextField(20);//������
	private JTextField idCardText= new JTextField(20); //���֤��
	private JTextField phoneNumText= new JTextField(20); //�ֻ�����
	private JLabel infoLab = new JLabel(" ");//��ʾ��
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
		//��һ��
		add(idlab);
		add(idText);
		add(infoLab);
		//�ڶ���
		add(passlab);
		add(passText);
		//������
		add(passlab2);	
		add(passText2);
		//������
		add(nameLab);
		add(nameText);
		//������
		add(idCardLab);
		add(idCardText);
		//������
		add(phoneNumLab);
		add(phoneNumText);
		//ע��
		add(regist);
		add(returnn);
		//�û����ظ���ʾ
		//����ע��
		setLocation(600, 400);
		setTitle("ע��");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==regist) {
					String username = idText.getText();
					CheckRepeatId req = new CheckRepeatId(username);
					if(!client.sendYONq(req))
						infoLab.setText("�û����Ѵ��ڣ����������룡");
					else {					
						if(passText.getText().equals(passText2.getText())){	
							infoLab.setBounds(125,250,200,20);
							UserMe newUser = 
									new UserMe(idText.getText(),
											passText.getText(),idCardText.getText(),
											nameText.getText(),phoneNumText.getText());
							client.sendNewUser(newUser);
							JOptionPane.showMessageDialog(null,"ע��ɹ���");
							dispose();
							user.toWindow("main menu");
						}
					}
								
				}
			}
		});
		//��������
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
