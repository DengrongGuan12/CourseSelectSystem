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

	JLabel warning;// 提示尚未有课程发布

	JPanel deanSelectPanel;// 选择院系
	JPanel mainPanel;// 显示课程列表
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
			deanSelectJCB.addItem(deans[i]);
		}
		deanSelectJCB.setSelectedItem("请选择院系");
		deanSelectJCB.addItemListener(this);
		deanSelectPanel.add(deanSelectJCB);
		this.add(deanSelectPanel, "North");

		warning = new JLabel("该院系尚未有课程发布！");
		warning.setFont(MyTools.f6);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(warning, "North");
		this.add(mainPanel, "Center");

		buttonPanel = new JPanel(new FlowLayout());
		refreshButton = new JButton("刷新");
		refreshButton.addActionListener(this);
		buttonPanel.add(refreshButton);
		this.add(buttonPanel, "South");
		
		refresh();
	}

	public void refresh() {
		Message message = new Message();

		if(deanSelectJCB.getSelectedItem().toString().equals("请选择院系")){
			JOptionPane.showMessageDialog(this, "请选择院系！");
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
				JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 刷新课程列表
		if (e.getSource() == refreshButton) {
			refresh();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// 更改院系后刷新课程列表
		refresh();
	}
}
