package presentation.trainingTeaView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.*;

import presentation.tableModel.*;
import tools.MyTools;
import businesslogicservice.*;
import common.*;

public class CourseListPanel extends JPanel implements ActionListener,
		ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel warning;// ��ʾ��δ�пγ̷���

	JPanel deanSelectPanel;// ѡ��Ժϵ
	JPanel mainPanel;// ��ʾ�γ��б�
	JPanel buttonPanel;

	JComboBox<String> deanSelectJCB;

	JButton refreshButton;

	JTable courseListTable;
	JScrollPane courseListJSP;

	CourseTableModel ctm;

	Message message;
	TrainingTeaBLService trainingTeaBLService;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public TrainingTeaBLService getTrainingTeaBLService() {
		return trainingTeaBLService;
	}

	public void setTrainingTeaBLService(
			TrainingTeaBLService trainingTeaBLService) {
		this.trainingTeaBLService = trainingTeaBLService;
	}

	public CourseListPanel(TrainingTeaBLService trainingTeaBLService,
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

		deanSelectPanel = new JPanel(new FlowLayout());
		deanSelectJCB = new JComboBox<String>();
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
			deanSelectJCB.addItem(deans[i]);
		}
		deanSelectJCB.setSelectedItem("��ѡ��Ժϵ");
		deanSelectJCB.addItemListener(this);
		deanSelectPanel.add(deanSelectJCB);
		this.add(deanSelectPanel, "North");

		warning = new JLabel("��Ժϵ��δ�пγ̷�����");
		warning.setFont(MyTools.f6);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(warning, "North");
		this.add(mainPanel, "Center");

		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("ˢ��");
		refreshButton.addActionListener(this);
		buttonPanel.add(refreshButton);
		this.add(buttonPanel, "South");
		
		refresh();
	}

	public void refresh() {
		Message message = new Message();

		if(deanSelectJCB.getSelectedItem().toString().equals("��ѡ��Ժϵ")){
			JOptionPane.showMessageDialog(this, "��ѡ��Ժϵ��");
		}else{
			try {
				message = this.trainingTeaBLService.showCourseList(deanSelectJCB
						.getSelectedItem().toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (message.getMesType().equals(MessageType.traTea_showCourseList_fail)) {
				warning.setVisible(true);
				if (courseListJSP != null) {
					courseListJSP.setVisible(false);
				}
			} else if (message.getMesType().equals(
					MessageType.traTea_showCourseList_success)) {
				warning.setVisible(false);
				ArrayList<Course> courses = message.getCourses();
				ArrayList<String> attriOfCou = message.getAttriOfCourse();
				ctm = new CourseTableModel(courses, attriOfCou);
				if (courseListJSP != null) {
					courseListTable.setModel(ctm);
					courseListJSP.setVisible(true);
				} else {
					courseListTable = new JTable();
					courseListTable.setModel(ctm);
					courseListJSP = new JScrollPane(courseListTable);
					mainPanel.add(courseListJSP);
				}
			} else {
				JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ˢ�¿γ��б�
		if (e.getSource() == refreshButton) {
			refresh();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// ����Ժϵ��ˢ�¿γ��б�
		refresh();
	}
}
