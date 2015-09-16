package presentation.trainingTeaView;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.*;

import presentation.tableModel.*;
import tools.MyTools;
import businesslogicservice.*;
import common.*;

public class StudentListPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel warning;// 提示尚未有学生注册

	JPanel mainPanel;// 显示学生列表
	JPanel buttonPanel;
	
	JButton refreshButton;

	JTable studentListTable;
	JScrollPane studentListJSP;

	StudentTableModel stm;

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

	public StudentListPanel(TrainingTeaBLService trainingTeaBLService,
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

		warning = new JLabel("尚未有学生注册！");
		warning.setFont(MyTools.f6);
		this.add(warning, "North");
		
		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel,"Center");
		
		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("刷新");
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
			message = this.trainingTeaBLService.showStudentList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.traTea_showStudentList_fail)){
			warning.setVisible(true);
			if(studentListJSP!=null){
				studentListJSP.setVisible(false);
			}
		}else if(message.getMesType().equals(MessageType.traTea_showStudentList_success)){
			warning.setVisible(false);
			ArrayList<Student> students = message.getStudents();
			ArrayList<String> attriOfStu = message.getAttriOfStu();
			stm = new StudentTableModel(students,attriOfStu);
			if(studentListJSP!=null){
				studentListTable.setModel(stm);
				studentListJSP.setVisible(true);
			}else{
				studentListTable = new JTable();
				studentListTable.setModel(stm);
				studentListJSP = new JScrollPane(studentListTable);
				mainPanel.add(studentListJSP,"Center");
			}
		}else{
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 刷新学生列表
		if(e.getSource()==refreshButton){
			refresh();
		}
	}
}
