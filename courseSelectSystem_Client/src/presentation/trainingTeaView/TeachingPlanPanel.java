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
	JPanel jp1;// ����ѡ��Ժϵ��������
	JLabel jl1;// ����δ��Ժϵ��ѧ�ƻ���������
	JLabel jp1_jl2;// "ѡ��Ժϵ"
	JComboBox<String> jp1_jcb;// ��ѡ��
	TeachingProTableModel tm_TeachingPro;// ��Ժϵ��ѧ�ƻ�
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
		jp1_jl2 = new JLabel("��ѡ��Ժϵ");
		jp1.setFont(MyTools.f6);

		jp1_jcb = new JComboBox<String>();
		String[] deans = new String[15];
		deans[0] = "���ѧԺ";
		deans[1] = "���ӿ�ѧѧԺ";
		deans[2] = "������ѧѧԺ";
		deans[3] = "����ϵ";
		deans[4] = "�����뺣���ѧѧԺ";
		deans[5] = "�����ѧѧԺ";
		deans[6] = "�������ѧ�뼼��ѧԺ";
		deans[7] = "��ѧϵ";
		deans[8] = "��ѧԺ";
		deans[9] = "��ѧϵ";
		deans[10] = "ҽѧԺ";
		deans[11] = "��ʷѧԺ";
		deans[12] = "����ѧԺ";
		deans[13] = "��������ѧԺ";
		deans[14] = "������ѧѧԺ";

		for (int i = 0; i < 15; i++) {

			jp1_jcb.addItem(deans[i]);

		}
		jp1_jcb.setSelectedItem(deans[0]);
		jp1_jcb.addItemListener(this);

		jp1.add(jp1_jl2);
		jp1.add(jp1_jcb);

		teachingPro = new JPanel(new BorderLayout());// ��Table��������Label�����
		jl1 = new JLabel("��Ժϵ��δ�н�ѧ�ƻ�������");
		jl1.setFont(MyTools.f6);

		teachingPro.add(jl1, "North");

		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("ˢ��");
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
			message = this.trainingTeaBLService.showTeachingPro("���ѧԺ");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.getMesType()
				.equals(MessageType.traTea_showTeachingPro_fail)) {
			// JOptionPane.showMessageDialog(this, "��δ�н�ѧ�ƻ������������ĵȴ���");
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
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
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
