package com.hlt.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.lq.client.*;
import com.lq.requests.LoginRequest;
import com.lq.dynamicManage.User;

public class UserLogin extends JFrame
{
	private Client client;
	private User user;
//	private JFrame frame  = new JFrame("�򵥵�¼ģʽ");
	private JButton submit = new JButton("��¼");
	private JButton reset = new JButton("����");
	private JButton regist = new JButton("ע��");
	private JLabel namelab = new JLabel("�û���:");
	private JLabel passlab = new JLabel("��   ��:");
	private JLabel infolab = new JLabel("�������û���������");
	private JTextField nameText = new JTextField(10);
	private JPasswordField passText = new JPasswordField(10);
	String picpath="images/1.jpg";
	ImageIcon background= new ImageIcon(picpath);
	JLabel lab=new JLabel(background);
	JPanel imagePanel=(JPanel)/*frame.*/getContentPane();
	public UserLogin(User user) {
		super("�򵥵�¼ģʽ");
		this.user = user;
		this.client = user.getClient();
		Font fnt =new Font("Serief",Font.BOLD,14);
		infolab.setFont(fnt);
		submit.setFont(fnt);
		reset.setFont(fnt); 
		namelab .setFont(fnt);
		passlab.setFont(fnt);
		regist.setFont(fnt);
		//������¼�¼�
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submit) {
					String tname =nameText.getText();
					String tpass = new String(passText.getPassword());
					LoginRequest req = new LoginRequest(tname,tpass);
					if(client.sendYONq(req))
					{
						infolab.setText("��¼�ɹ���");
						/*frame.*/dispose();
//					    new MainFrame();
						user.toWindow("main menu");
						
					}
					else {
						infolab.setText("��¼ʧ�ܣ�������û��������룡");
					}
				}
			}
		});
		//����ע���¼�
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==regist) {
					/*frame.*/dispose();//�ͷ�ϵͳ��Դ
//					RegistFrame r=new RegistFrame();
					user.toWindow("register");
				}
			}
		});
	 //���������¼�����
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()== reset)
				{//�û�����������
					nameText.setText("");
					passText.setText("");
					infolab.setText("�������û��������룡");
				}
			}
		});
	//�رմ����¼�
		/*frame.*/addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		//�����¼�
		//����Ӧ���ֹ�����
		//frame.setLayout(null);
		//���õ�һ�����������λ�úʹ�С
		namelab.setBounds(25, 5, 60,20);//x,y,�� ��
		nameText.setBounds(85,5,100,20);
		submit.setBounds(190,5, 80, 20);
		regist.setBounds(190,30, 80, 20);
		//���õڶ����������λ�úʹ�С
		passlab.setBounds(25, 30, 60,20);
		passText.setBounds(85,30,100,20);
		reset.setBounds(190,55,80,20);
		//������ʾ��ǩ��λ�úʹ�С
		infolab.setBounds(5, 65, 220, 30);
		//���ñ���
		lab.setBounds(0,0, background.getIconWidth(), background.getIconHeight());
		imagePanel.setOpaque(false);
		/*frame.*/getLayeredPane().add(lab,new Integer(Integer.MIN_VALUE));
		//������
		//��һ��frame.add(lab1);
		imagePanel.add(namelab);
		imagePanel.add(nameText);
		imagePanel.add(submit);
		imagePanel.add(regist);
		//�ڶ���
		imagePanel.add(passlab);
		imagePanel.add(passText);
		imagePanel.add(reset);
		//������
		imagePanel.add(infolab);
		/*frame.*/setSize(360,200);
		
		//���ô��屳��
		/*frame.*/setLocation(600, 400);
		/*frame.*/setVisible(true);
        
		//Sql����ʱ�޸�ʱ��
/*   set global time_zone = '+8:00'; ##�޸�mysqlȫ��ʱ��Ϊ����ʱ�䣬���������ڵĶ�8��
		  set time_zone = '+8:00'; ##�޸ĵ�ǰ�Ựʱ��
		  flush privileges; #������Ч*/
}
//	public static void main(String[] args) {
//		new UserLogin();
//	}
}
