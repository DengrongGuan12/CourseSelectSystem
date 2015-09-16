package presentation.trainingTeaView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.*;

import presentation.tableModel.*;
import tools.MyTools;
import businesslogicservice.*;
import common.*;

public class TeacherListPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel warning;// ��ʾ��δ�н�ʦע��

	JPanel mainPanel;// ��ʾ��ʦ�б�
	JPanel buttonPanel;

	JButton refreshButton;

	JTable teacherListTable;
	JScrollPane teacherListJSP;

	TeacherTableModel ttm;

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

	public TeacherListPanel(TrainingTeaBLService trainingTeaBLService,
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

		warning = new JLabel("��δ�н�ʦע�ᣡ");
		warning.setFont(MyTools.f6);
		this.add(warning, "North");
		
		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel,"Center");
		
		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("ˢ��");
		refreshButton.addActionListener(this);
		buttonPanel.add(refreshButton);
		this.add(buttonPanel,"South");
		
		initialPanel();
	}

	public void initialPanel() {
		refresh();
	}

	public void refresh() {
		try {
			message = this.trainingTeaBLService.showTeacherList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.getMesType()
				.equals(MessageType.traTea_showTeacherList_fail)) {
			warning.setVisible(true);
			if (teacherListJSP != null) {
				teacherListJSP.setVisible(false);
			}
		} else if (message.getMesType().equals(
				MessageType.traTea_showTeacherList_success)) {
			warning.setVisible(false);
			ArrayList<Teacher> teachers = message.getTeachers();
			ArrayList<String> attriOfTea = message.getAttriOfTea();
			ttm = new TeacherTableModel(teachers, attriOfTea);
			if (teacherListJSP != null) {
				teacherListTable.setModel(ttm);
				teacherListJSP.setVisible(true);
			} else {
				teacherListTable = new JTable();
				teacherListTable.setModel(ttm);
				teacherListJSP = new JScrollPane(teacherListTable);
				mainPanel.add(teacherListJSP,"Center");
			}
		} else {
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ˢ�½�ʦ�б�
		if(e.getSource()==refreshButton){
			refresh();
		}
	}
}
