package com.lq.view.dynamic;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

import com.lq.common.time.StayTime;
import com.lq.model.Flight;
import com.lq.sql.FlightUpdateDriver;

public class FlightDetail extends JFrame{
	private BasicContentPanel basicPanel;
	private ModifyContentPanel modifyPanel;
	private Flight flight;
	public FlightDetail(Flight flight) {
		super(flight.getId() + "������������ҳ");
		this.flight = flight;
		setLayout(null);
		basicPanel = new BasicContentPanel(flight);
		modifyPanel = new ModifyContentPanel(flight);
		basicPanel.setBounds(0,0,400,250);
//		basicPanel.setBackground(Color.red);
		modifyPanel.setBounds(0,250,400,350);
		modifyPanel.setBackground(Color.YELLOW);
		add(basicPanel);
		add(modifyPanel);
	}
	public void showing(int width,int height) {
		//w:400;h:600
		setLocation(500,100);
		setSize(width,height);
		setVisible(true);
	}
	public void close() {
		setVisible(false);
	}
}

class BasicContentPanel extends JPanel{
	public BasicContentPanel(Flight flight) {
		setLayout(null);
		JLabel tmp_id = new JLabel("�����:" + flight.getId());
		tmp_id.setBounds(10,10,100,20);
		add(tmp_id);
		JLabel tmp_company = new JLabel("���չ�˾:" + flight.getCompany());
		tmp_company.setBounds(130,10,120,20);
		add(tmp_company);
		JLabel tmp_state = new JLabel("��ǰ״̬:" + flight.getState());
		tmp_state.setBounds(260,10,120,20);
		add(tmp_state);
		JLabel tmp_tf_place = new JLabel("��ɵ�:" + flight.getTakeoffPlace());
		tmp_tf_place.setBounds(10,40,100,20);
		add(tmp_tf_place);
		String realTime;
		if(flight.getState().equals("�ƻ�")) {
			realTime = flight.getStartTime().getPlanTime().toString();
		}
		else {
			realTime = flight.getStartTime().getRealTime().toString();
		}
		JLabel tmp_tf_time = new JLabel("Ԥ�����ʱ��:" + realTime);
		tmp_tf_time.setBounds(120,40,240,20);
		add(tmp_tf_time);
		JLabel tmp_transport;
		if(flight.isTrans()) {
			tmp_transport = new JLabel("��תվ:" + flight.getTransPlace());
			tmp_transport.setBounds(10,70,300,20);
			JLabel mid_arrive = new JLabel("��ת����ʱ��:" + 
							flight.getTransArriveTime());
			mid_arrive.setBounds(10,100,300,20);
			add(mid_arrive);
			JLabel mid_leave = new JLabel("��ת���ʱ��:" + 
					flight.getTransLeaveTime());
			mid_leave.setBounds(10,130,300,20);
			add(mid_leave);
			JLabel stayTime = new JLabel("ͣ��:");
			stayTime.setBounds(10,160,300,20);
			add(stayTime);
		}
		else {
			tmp_transport = new JLabel("����ת");
			tmp_transport.setBounds(10,130,300,20);
		}
		add(tmp_transport);
		JLabel tmp_ar_place = new JLabel("Ŀ�ĵ�:" + flight.getArrivePlace());
		tmp_ar_place.setBounds(10,190,100,20);
		add(tmp_ar_place);
		JLabel tmp_ar_time = new JLabel("Ԥ�Ƶִ�ʱ��:" + flight.getArriveTime()
										.getRealTime().toString());
		tmp_ar_time.setBounds(120,190,240,20);
		add(tmp_ar_time);
		
	}
}

class ModifyContentPanel extends JPanel{
	private class FirstPanel extends JPanel implements ActionListener{
		private JTextField hourField = new JTextField(3);
		private JTextField minField = new JTextField(3);
		int day,hour,min;
		private JButton btn = new JButton("ȷ��");
		private ModifyContentPanel parent;
		public FirstPanel(ModifyContentPanel p) {
			parent = p;
			setLayout(new FlowLayout());
			add(new JLabel("����ʱ����"));
			add(hourField);
			add(new JLabel("Сʱ"));
			add(minField);
			add(new JLabel("����"));
			btn.addActionListener(this);
			add(btn);
		}
		public void actionPerformed(ActionEvent e) {
			try {	
				if(hourField.getText().equals(""))
					hour = 0;
				else
					hour = Integer.parseInt(hourField.getText());
				if(minField.getText().equals(""))
					min = 0;
				else
					min = Integer.parseInt(minField.getText());
				
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, "������Ϸ�����",
						"������ʾ",JOptionPane.ERROR_MESSAGE);
			}finally {
				StayTime stayTime = new StayTime(hour,min);
				parent.delay(stayTime);
				setMessage();
			}
		}
	}
	private FirstPanel firstPanel = new FirstPanel(this);
	
	private class SecondPanel extends JPanel{
		public JTextField countTime = new JTextField(15);
		public SecondPanel() {
			add(new JLabel("���ĺ�����ʱ�䣺"));
			countTime.setEditable(false);
			add(countTime);
		}
		public void changeText(String text) {
			countTime.setText(text);
		}
	}
	private SecondPanel secPanel = new SecondPanel();
	
	private class ReasonPanel extends JPanel implements ItemListener,ActionListener{
		private JComboBox<String> reason = new JComboBox<String>();
		JTextField diy_text = new JTextField(10); //�Զ��崰��������
		int num = 4;//��ʼԭ�����
		public ReasonPanel() {
			setSize(400,30);
			setLayout(new FlowLayout());
			add(new JLabel("����ԭ��"));
			String choose[] = {"����ԭ��","�������","���ɿ���","�Զ���"};
			for(String s:choose) {
				reason.addItem(s);
			}
			reason.addItemListener(this);
			add(reason);
		}
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				String name = (String)e.getItem();
				System.out.println(name);
				selectReason = name;
				setMessage();
				if(name.equals("�Զ���")) {
					diy_text.setText("");
					JFrame diy = new JFrame("�Զ�������ԭ��");
					diy.setSize(300, 100);
					diy.setLocation(500, 400);
					diy.setLayout(new FlowLayout());
					diy.add(new JLabel("ԭ��"));
					diy.add(diy_text);
					JButton btn = new JButton("ȷ��");
					btn.addActionListener(this);
					diy.add(btn);
					diy.setVisible(true);
				}
			}
		}
		public void actionPerformed(ActionEvent e) {
			reason.addItem(diy_text.getText());
			num++;
			reason.setSelectedIndex(num-1);
			JOptionPane.showMessageDialog(null,"���óɹ���");
		}
	}
	private ReasonPanel reasonPanel = new ReasonPanel();
	private String selectReason = "����ԭ��";
	
	private JTextArea message = new JTextArea(8,36);
	private JButton send = new JButton("ȷ�����Ĳ�����������Ϣ");
	public ModifyContentPanel(Flight flight) {
		this.flight = flight;
		add(firstPanel);
		add(secPanel);
		add(reasonPanel);
		message.setLineWrap(true);
		add(new JScrollPane(message));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flight.getStartTime().setDelayReason(selectReason);
				new FlightUpdateDriver().delayUpdate(flight);
				
			}
		});
		add(send);
	}
	
	public void setMessage() {
		String s = String.format("�װ��Ĺ˿����ã��ܱ�Ǹ��֪ͨ����\n������%s���չ�˾��%s�ź���"
			+ "��Ϊ%s���ɻ���������Ԥ�ƽ���%s����ɡ�\n��˶�����ɵĲ����������Ǹ��",
			flight.getCompany(),flight.getId(),
			selectReason,flight.getStartTime().getRealTime().toString());
		message.setText(s);
	}
	
	private Flight flight;
	public void delay(StayTime stayTime) {
		flight.startDelay(stayTime);
		secPanel.changeText(flight.getStartTime().getRealTime().toString());
	}
}
