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
//	private JFrame frame  = new JFrame("简单登录模式");
	private JButton submit = new JButton("登录");
	private JButton reset = new JButton("重置");
	private JButton regist = new JButton("注册");
	private JLabel namelab = new JLabel("用户名:");
	private JLabel passlab = new JLabel("密   码:");
	private JLabel infolab = new JLabel("请输入用户名和密码");
	private JTextField nameText = new JTextField(10);
	private JPasswordField passText = new JPasswordField(10);
	String picpath="images/1.jpg";
	ImageIcon background= new ImageIcon(picpath);
	JLabel lab=new JLabel(background);
	JPanel imagePanel=(JPanel)/*frame.*/getContentPane();
	public UserLogin(User user) {
		super("简单登录模式");
		this.user = user;
		this.client = user.getClient();
		Font fnt =new Font("Serief",Font.BOLD,14);
		infolab.setFont(fnt);
		submit.setFont(fnt);
		reset.setFont(fnt); 
		namelab .setFont(fnt);
		passlab.setFont(fnt);
		regist.setFont(fnt);
		//监听登录事件
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submit) {
					String tname =nameText.getText();
					String tpass = new String(passText.getPassword());
					LoginRequest req = new LoginRequest(tname,tpass);
					if(client.sendYONq(req))
					{
						infolab.setText("登录成功！");
						/*frame.*/dispose();
//					    new MainFrame();
						user.toWindow("main menu");
						
					}
					else {
						infolab.setText("登录失败，错误的用户名或密码！");
					}
				}
			}
		});
		//监听注册事件
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==regist) {
					/*frame.*/dispose();//释放系统资源
//					RegistFrame r=new RegistFrame();
					user.toWindow("register");
				}
			}
		});
	 //设置重置事件处理
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()== reset)
				{//用户名密码框清空
					nameText.setText("");
					passText.setText("");
					infolab.setText("请输入用户名和密码！");
				}
			}
		});
	//关闭窗口事件
		/*frame.*/addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		//加入事件
		//不适应布局管理器
		//frame.setLayout(null);
		//设置第一行三个组件的位置和大小
		namelab.setBounds(25, 5, 60,20);//x,y,宽 高
		nameText.setBounds(85,5,100,20);
		submit.setBounds(190,5, 80, 20);
		regist.setBounds(190,30, 80, 20);
		//设置第二行三个组件位置和大小
		passlab.setBounds(25, 30, 60,20);
		passText.setBounds(85,30,100,20);
		reset.setBounds(190,55,80,20);
		//设置提示标签的位置和大小
		infolab.setBounds(5, 65, 220, 30);
		//设置背景
		lab.setBounds(0,0, background.getIconWidth(), background.getIconHeight());
		imagePanel.setOpaque(false);
		/*frame.*/getLayeredPane().add(lab,new Integer(Integer.MIN_VALUE));
		//添加组件
		//第一行frame.add(lab1);
		imagePanel.add(namelab);
		imagePanel.add(nameText);
		imagePanel.add(submit);
		imagePanel.add(regist);
		//第二行
		imagePanel.add(passlab);
		imagePanel.add(passText);
		imagePanel.add(reset);
		//第三行
		imagePanel.add(infolab);
		/*frame.*/setSize(360,200);
		
		//设置窗体背景
		/*frame.*/setLocation(600, 400);
		/*frame.*/setVisible(true);
        
		//Sql掉线时修改时区
/*   set global time_zone = '+8:00'; ##修改mysql全局时区为北京时间，即我们所在的东8区
		  set time_zone = '+8:00'; ##修改当前会话时区
		  flush privileges; #立即生效*/
}
//	public static void main(String[] args) {
//		new UserLogin();
//	}
}
