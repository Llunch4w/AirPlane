package com.lq.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.lq.sql.PlaceSqlDriver;
import com.lq.dynamicManage.*;
import com.lq.common.time.*;
import com.lq.model.Flight;
import com.lq.sql.FlightSaveDriver;

public class AdminManage extends AdminWindow implements ActionListener{
	private ContentPanel content = new ContentPanel();
	private NavPanel navigator = new NavPanel(this);
	public AdminManage(Admin admin) {
		super("����Ա����");
		setLayout(new BorderLayout());
		add(navigator,BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
		showing(500,400);
	}
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String next = btn.getText();
		System.out.println(next);
		content.changeTo(next);
		
	}
}

class NavPanel extends JPanel{
	private AdminManage parent;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	public NavPanel(AdminManage admin) {
		parent = admin;
		String tmp[] = {"����","ɾ��","�޸�","��ѯ"};
		for(int i = 0;i < 4;i++) {
			JButton btn = new JButton(tmp[i]);
			btn.addActionListener(parent);
			buttons.add(btn);
			this.add(btn);
		}
	}
}

class ContentPanel extends JPanel{
	private AdminManage parent;
	private Map<String,JPanel> map = new HashMap<String,JPanel>();
	private CardLayout cardLayout = new CardLayout();//��Ƭ����
	public ContentPanel() {
//		setBackground(Color.BLUE);
		map.put("����", new AddPanel());
		map.put("ɾ��", new DeletePanel());
		map.put("�޸�", new ModifyPanel());
		map.put("��ѯ", new SearchPanel());
		this.setLayout(cardLayout);
		for(Map.Entry<String, JPanel> entry:map.entrySet()) {
			this.add(entry.getKey(),entry.getValue());
			entry.getValue().setVisible(false);
		}
	}
	public void changeTo(String next) {
		cardLayout.show(this,next);
	}
}

class AddPanel extends JPanel{
	private class FirstLayer extends JPanel{	//�ڲ���	
		public JTextField idField = new JTextField(8);
		public JTextField companyField = new JTextField(6);
		public JTextField typeField = new JTextField(6);
		public JTextField buildField = new JTextField(3);
		String id,company,type,building;
		
		public FirstLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("����ţ�"));
			add(idField);
			add(new JLabel("���չ�˾��"));
			add(companyField);
			add(new JLabel("��վ¥��"));
			add(buildField);
			add(new JLabel("���ͣ�"));
			add(typeField);
		}
		
		public void commit() {
			id = idField.getText();
			company = companyField.getText();
			type = typeField.getText();
			building = buildField.getText();
		}
	}
	private FirstLayer firstLayer = new FirstLayer();//��һ�����
	
	private class SecondLayer extends JPanel implements ItemListener{		
		private ArrayList<String> places_canbe_choose;
		private JComboBox<String> startPlace = new JComboBox<String>();
		private JComboBox<String> midPlace = new JComboBox<String>();
		private JComboBox<String> endPlace = new JComboBox<String>();
		private JComboBox<String> weekPlace = new JComboBox<String>();
		public String start = null,mid = null,end = null,week = null;
		
		@Override
		public void itemStateChanged(ItemEvent e) {//ʵ��ItemListen�ӿ�
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getSource() == startPlace)
					start = (String)startPlace.getSelectedItem();
				else if(e.getSource() == midPlace)
					mid = (String)midPlace.getSelectedItem();
				else if(e.getSource() == endPlace)
					end = (String)endPlace.getSelectedItem();
				else if(e.getSource() == weekPlace)
					week = (String)weekPlace.getSelectedItem();
			}
		}
		
		private void addToComboBox(JComboBox<String> box,ArrayList<String> array) {
			for(String s:array) {
				box.addItem(s);
			}
			start = array.get(0);
			mid = array.get(0);
			end = array.get(0);
		}
		
		private void init() {//����ѡ����ֵ��׼����,����Ӽ����¼�
			PlaceSqlDriver tempSqlDriver = new PlaceSqlDriver();
			places_canbe_choose = tempSqlDriver.getPlaces();
			addToComboBox(startPlace,places_canbe_choose);
			addToComboBox(midPlace,places_canbe_choose);
			addToComboBox(endPlace,places_canbe_choose);
			String enumDays[] = {"��һ","�ܶ�","����","����",
					"����","����","����"};
			for(String s:enumDays) {
				weekPlace.addItem(s);
			}
			startPlace.addItemListener(this);
			midPlace.addItemListener(this);
			endPlace.addItemListener(this);
			weekPlace.addItemListener(this);
		}
		
		public SecondLayer() {
			init();
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("��ɵ�"));
			add(startPlace);
			add(new JLabel("��ͣ��"));
			add(midPlace);
			add(new JLabel("Ŀ�ĵ�"));
			add(endPlace);
			add(new JLabel("����:"));
			add(weekPlace);
		}
		
	}
	private SecondLayer secondLayer = new SecondLayer();//�ڶ���
	
	private class TimeLayer extends JPanel{
		public int year,month,day,hour,min;
		public DateTime dateTime = null;
		public StayTime stayTime = null;
		public JTextField yearField = new JTextField(3);
		public JTextField monthField = new JTextField(3);
		public JTextField dayField = new JTextField(3);
		public JTextField hourField = new JTextField(3);
		public JTextField minField = new JTextField(3);		
		public TimeLayer(String title) {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel(title));
			add(yearField);
			add(new JLabel("��"));
			add(monthField);
			add(new JLabel("��"));
			add(dayField);
			add(new JLabel("��"));
			add(hourField);
			add(new JLabel("ʱ"));
			add(minField);
			add(new JLabel("��"));
		}
		public void commit() {
			try {				
				year = Integer.parseInt(yearField.getText());
			}catch(Exception e) {
				year = 1970;
			}
			try {				
				month = Integer.parseInt(monthField.getText());
			}catch(Exception e) {
				month = 1;
			}
			try {				
				day = Integer.parseInt(dayField.getText());
			}catch(Exception e) {
				day = 1;
			}
			try {				
				hour = Integer.parseInt(hourField.getText());
			}catch(Exception e) {
				hour = 10;
			}
			try {				
				min = Integer.parseInt(minField.getText());
			}catch(Exception e) {
				min = 10;
			}
			dateTime = new DateTime(year,month,day,hour,min);
			stayTime = new StayTime(day,hour,min);
		}
	}
	private TimeLayer startTimeLayer = new TimeLayer("���ʱ��");
	private TimeLayer endTimeLayer = new TimeLayer("�ִ�ʱ��");
	private TimeLayer transArriveTimeLayer = new TimeLayer("��ת����ʱ��");
	private TimeLayer transLeaveTimeLayer = new TimeLayer("��ת�뿪ʱ��");
	
	
	private class PriceLayer extends JPanel{
		public double kidPrice,adultPrice;
		public JTextField kidText = new JTextField(8);
		public JTextField adultText = new JTextField(8);

		public PriceLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("����Ʊ�ۣ�"));
			add(adultText);
			add(new JLabel("��ͯƱ�ۣ�"));
			add(kidText);
		}
		
		public void commit() {
			try {				
				kidPrice = Double.parseDouble(kidText.getText());
				adultPrice = Double.parseDouble(adultText.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Ʊ��Ӧ�������֣�");
			}
		}
	}
	private PriceLayer priceLayer = new PriceLayer();
	
	private class CapityLayer extends JPanel{
		public int capity,remain,row;
		public JTextField capityText = new JTextField(3);
		public JTextField remainText = new JTextField(3);
		public JTextField rowText = new JTextField(3);
		public CapityLayer() {
			setPreferredSize(new Dimension(500,30));
			setLayout(new FlowLayout());
			add(new JLabel("����λ����"));
			add(capityText);
			add(new JLabel("ʣ����λ����"));
			add(remainText);
			add(new JLabel("��λ������"));
			add(rowText);
		}
		public void commit() {
			try {				
				capity = Integer.parseInt(capityText.getText());
				remain = Integer.parseInt(remainText.getText());
				row = Integer.parseInt(rowText.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"����Ӧ����������");
			}
		}
	}
	private CapityLayer capityLayer = new CapityLayer();
	
	private JButton makeSure = new JButton("ȷ��");
	public AddPanel() {	
		setLayout(new FlowLayout());
		add(firstLayer);
		add(secondLayer);
		add(startTimeLayer);
		add(transArriveTimeLayer);
		add(transLeaveTimeLayer);
		add(endTimeLayer);
		add(priceLayer);
		add(capityLayer);
		makeSure.addActionListener(new ActionListener() {//�����ť���ύ���ݸ����ݿ�
			public void actionPerformed(ActionEvent e) {
				firstLayer.commit();
				//�ڶ�����˼������Բ���commit
				startTimeLayer.commit();
				transArriveTimeLayer.commit();
				transLeaveTimeLayer.commit();
				endTimeLayer.commit();
				priceLayer.commit();
				capityLayer.commit();
				Flight flight = new Flight(firstLayer.id,firstLayer.company,
						firstLayer.type,firstLayer.building);
				flight.setPrice(priceLayer.kidPrice, 
								priceLayer.adultPrice);	
				flight.setTime("start time", startTimeLayer.dateTime);
				flight.setTime("arrive transport time",
							transArriveTimeLayer.dateTime);
				flight.setTime("leave transport time", 
								transLeaveTimeLayer.dateTime);
				flight.setTime("arrive time", endTimeLayer.dateTime);
				flight.setWeek(secondLayer.week);
				flight.setPlace(secondLayer.start, secondLayer.mid,
						secondLayer.end);
				flight.setCapity(capityLayer.row);
				new FlightSaveDriver().save(flight);
				System.out.println("�����:" + firstLayer.id);
				System.out.println("���չ�˾:" + firstLayer.company);
				System.out.println("����:" + firstLayer.type);
				System.out.println("���:" + secondLayer.start);
				System.out.println("��תվ:" + secondLayer.mid);
				System.out.println("�յ�:" + secondLayer.end);
				System.out.println("��ͯƱ��:" + priceLayer.kidPrice);
				System.out.println("����Ʊ��:" + priceLayer.adultPrice);
				System.out.println("����:" + capityLayer.capity);
			}
		});
		add(makeSure);
	}
}

class DeletePanel extends JPanel{
	public DeletePanel() {
		add(new JButton("DeletePanel"));
	}
}

class ModifyPanel extends JPanel{
	public ModifyPanel() {
		add(new JButton("ModifyPanel"));
	}
}

class SearchPanel extends JPanel{
	public SearchPanel() {
		add(new JButton("SearchPanel"));
	}
}
