package presentation.trainingTeaView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;

import presentation.tableModel.*;
import presentation.trainingTeaView.TrainingTeaWindow;
import tools.MyTools;
import businesslogicservice.TrainingTeaBLService;
import common.Message;
import common.MessageType;
import common.TeachingPro;

public class TeachingPlanPanel extends JPanel implements ActionListener,ItemListener {
	private static final long serialVersionUID = 1L;
	JPanel teachingPro,buttonPanel;

	JTable jt1;
	JPanel jp1;// 放置选择院系组件的面板
	JLabel jl1;// 放尚未有院系教学计划发布文字
	JLabel jp1_jl2;// "选择院系"
	JComboBox<String> jp1_jcb;// 复选框
	TeachingProTableModel tm_TeachingPro;// 本院系教学计划
	JScrollPane jsp1;
	TrainingTeaBLService trainingTeaBLService;
	TrainingTeaWindow TrainingTeaWindow;
	JDialog suc;

	JButton refreshButton;
	
	public TrainingTeaWindow getTrainingTeaWindow() {
		return TrainingTeaWindow;
	}

	public void setTrainingTeaWindow(TrainingTeaWindow TrainingTeaWindow) {
		this.TrainingTeaWindow = TrainingTeaWindow;
	}

	public TrainingTeaBLService getTrainingTeaBLService() {
		return trainingTeaBLService;
	}

	public void setTrainingTeaBLService(
			TrainingTeaBLService TrainingTeaBLService) {
		this.trainingTeaBLService = TrainingTeaBLService;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	Message message;

	public TeachingPlanPanel(TrainingTeaBLService trainingTeaBLService,
			Message message) {

		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setTrainingTeaBLService(trainingTeaBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());

		jp1 = new JPanel(new FlowLayout());
		jp1_jl2 = new JLabel("请选择院系");
		jp1.setFont(MyTools.f6);

		jp1_jcb = new JComboBox<String>();
		String[] deans = new String[15];
		deans[0] = "软件学院";
		deans[1] = "电子科学学院";
		deans[2] = "大气科学学院";
		deans[3] = "天文系";
		deans[4] = "地理与海洋科学学院";
		deans[5] = "地球科学学院";
		deans[6] = "计算机科学与技术学院";
		deans[7] = "哲学系";
		deans[8] = "文学院";
		deans[9] = "数学系";
		deans[10] = "医学院";
		deans[11] = "历史学院";
		deans[12] = "物理学院";
		deans[13] = "政府管理学院";
		deans[14] = "环境科学学院";

		for (int i = 0; i < 15; i++) {

			jp1_jcb.addItem(deans[i]);

		}
		jp1_jcb.setSelectedItem(deans[0]);
		jp1_jcb.addItemListener(this);

		jp1.add(jp1_jl2);
		jp1.add(jp1_jcb);

		teachingPro = new JPanel(new BorderLayout());// 放Table的面板或者Label的面板
		jl1 = new JLabel("该院系尚未有教学计划发布！");
		jl1.setFont(MyTools.f6);

		teachingPro.add(jl1, "North");

		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("刷新");
		refreshButton.addActionListener(this);
		buttonPanel.add(refreshButton);
		
		this.add(jp1, "North");
		this.add(teachingPro, "Center");
		this.add(buttonPanel,"South");

		initialPanel();
	}

	private void initialPanel() {
		Message message = new Message();
		try {
			message = this.trainingTeaBLService.showTeachingPro("软件学院");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.getMesType()
				.equals(MessageType.traTea_showTeachingPro_fail)) {
			// JOptionPane.showMessageDialog(this, "尚未有教学计划发布，请耐心等待！");
			jl1.setVisible(true);

		} else if (message.getMesType().equals(
				MessageType.traTea_showTeachingPro_success)) {
			jl1.setVisible(false);
			ArrayList<TeachingPro> arr_TeachingPro = message.getTeachingPro();
			ArrayList<String> attriOfTP = message.getAttriOfTP();
			tm_TeachingPro = new TeachingProTableModel(arr_TeachingPro,
					attriOfTP);
			jt1 = new JTable();
			jt1.setModel(tm_TeachingPro);

			jsp1 = new JScrollPane(jt1);

			teachingPro.add(jsp1, BorderLayout.CENTER);

		} else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	}

	public void refresh() {
		String list = this.jp1_jcb.getSelectedItem().toString();
		Message message = new Message();
		try {
			message = this.trainingTeaBLService.showTeachingPro(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (message.getMesType()
				.equals(MessageType.traTea_showTeachingPro_fail)) {
			jl1.setVisible(true);
			if (jsp1 != null) {
				jsp1.setVisible(false);
			}
		} else if (message.getMesType().equals(
				MessageType.traTea_showTeachingPro_success)) {
			jl1.setVisible(false);
			ArrayList<TeachingPro> arr_TeachingPro = message.getTeachingPro();
			ArrayList<String> attriOfTP = message.getAttriOfTP();
			tm_TeachingPro = new TeachingProTableModel(arr_TeachingPro,
					attriOfTP);
			if (jsp1 != null) {
				jt1.setModel(tm_TeachingPro);
			}else{
				jt1 = new JTable();
				jt1.setModel(tm_TeachingPro);
				
				jsp1 = new JScrollPane(jt1);
			}
			

			teachingPro.add(jsp1, BorderLayout.CENTER);
			jsp1.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp1_jcb) {
			refresh();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==refreshButton){
			refresh();
		}
	}

}
