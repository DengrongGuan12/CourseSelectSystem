package presentation.deanTeaView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;
import java.util.ArrayList;
import javax.swing.*;

import businesslogicservice.DeanTeaBLService;
import common.Course;
import common.DeanTea;
import common.Message;
import common.MessageType;
import common.Student;
import presentation.stuView.CDetail;
import presentation.tableModel.CourseTableModel;
import presentation.tableModel.StudentTableModel;
import tools.MyTools;

public class CourseListPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField_CourseID;
	private JPanel panel_CourseList;
	private JLabel lbl_Warning;
	private JTable jt_CourseList;
	private JScrollPane jsp_CourseList;
	private CourseTableModel ctm;
	
	private Message message;
	private DeanTeaBLService deanTeaBLService;
    private DeanTeaWindow deanTeaWindow;
	
	public Message getMessage() {
		return this.message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public DeanTeaBLService getDeanTeaBLService() {
		return this.deanTeaBLService;
	}
	
	public void setDeanTeaBLService(DeanTeaBLService deanTeaBLService) {
		this.deanTeaBLService = deanTeaBLService;
	}
	
	public void setDeanTeaWindow(DeanTeaWindow deanTeaWindow) {
		this.deanTeaWindow = deanTeaWindow;
	}
	
	public CourseListPanel(DeanTeaBLService deanTeaBLService, Message message) {
		this.setDeanTeaBLService(deanTeaBLService);
		this.setMessage(message);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		
		JPanel panel_Search = new JPanel();
		FlowLayout fl_panel_Search = (FlowLayout) panel_Search.getLayout();
		fl_panel_Search.setVgap(10);
		fl_panel_Search.setHgap(150);
		add(panel_Search, BorderLayout.NORTH);

		
		JLabel label_CourseID = new JLabel("课程号");
		panel_Search.add(label_CourseID);
		
		textField_CourseID = new JTextField();
		panel_Search.add(textField_CourseID);
		textField_CourseID.setColumns(10);
		
		JButton button_Search = new JButton("查找");
		button_Search.addActionListener(new SearchButtonListener());
		panel_Search.add(button_Search);
		
		lbl_Warning = new JLabel("本院尚未发布课程！");
		lbl_Warning.setFont(MyTools.f6);
		lbl_Warning.setVisible(false);
		
		panel_CourseList = new JPanel(new BorderLayout());
		panel_CourseList.add(lbl_Warning,BorderLayout.NORTH);
		add(panel_CourseList, BorderLayout.CENTER);
		
		JPanel panel_Button = new JPanel();
		FlowLayout fl_panel_Button = (FlowLayout) panel_Button.getLayout();
		fl_panel_Button.setHgap(136);
		add(panel_Button, BorderLayout.SOUTH);
		
		JButton button_refresh = new JButton("刷新");
		button_refresh.addActionListener(new RefreshButtonListener());
		panel_Button.add(button_refresh);
		
		JButton button_StudentList = new JButton("对应学生列表");
		button_StudentList.addActionListener(new StudentListButtonListener());
		panel_Button.add(button_StudentList);
		
		JButton button_CourseDetailInfo = new JButton("课程详细信息");
		button_CourseDetailInfo.addActionListener(new CourseDetailInfoButtonListener());
		panel_Button.add(button_CourseDetailInfo);
		
		refresh();
	} // end function constructor
	
	public void refresh() {
		DeanTea deanTea = (DeanTea)message.getU();
		String dean = deanTea.getDean();
		Message message_ShowCourses = new Message();
		
		try {
			message_ShowCourses = this.deanTeaBLService.showCourses(dean);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message_ShowCourses.getMesType().equals(MessageType.deanTea_showCourse_fail)) {
			//JOptionPane.showMessageDialog(deanTeaWindow, "尚未发布课程！");
			lbl_Warning.setVisible(true);
			if(jsp_CourseList!=null){
				jsp_CourseList.setVisible(false);
			}
		} else if(message_ShowCourses.getMesType().equals(MessageType.deanTea_showCourse_success)) {
			lbl_Warning.setVisible(false);
			ArrayList<Course> courses = message_ShowCourses.getCourses();
			ArrayList<String> attriOfCou = message_ShowCourses.getAttriOfCourse();
			ctm = new CourseTableModel(courses,attriOfCou);
			if(jsp_CourseList!=null) {
				jt_CourseList.setModel(ctm);
				jsp_CourseList.setVisible(true);
			} else {
				jt_CourseList = new JTable();
				jt_CourseList.setModel(ctm);
				jsp_CourseList = new JScrollPane(jt_CourseList);
				panel_CourseList.add(jsp_CourseList);
			}
		} else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	} // end refresh method
	
	class RefreshButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			refresh();
		}
	} // end RefreshButtonListener inside class
	
	class SearchButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			String courseID = textField_CourseID.getText();
			int row = 0;
			boolean select = false;
			
			if (!courseID.equals("")) {
				row = jt_CourseList.getRowCount();
				for (int i = 0; i < row; i++) {
					String cNum = (String) ctm.getValueAt(i, 0);
					if (courseID.equals(cNum)) {
						jt_CourseList.setRowSelectionAllowed(true);
						jt_CourseList.setRowSelectionInterval(i, i);
						select = true;
						break;
					}
				}
				if (!select) {
					JOptionPane.showMessageDialog(deanTeaWindow, "没有对应课程号的课程！");
				}
			} else {
				JOptionPane.showMessageDialog(deanTeaWindow, "请输入课程号！");
			}
			
		}
	} // end SearchButtonListener inside class
	
	class StudentListButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int row = jt_CourseList.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(deanTeaWindow, "请选择课程！");
			} else {
				String courseID = (String) ctm.getValueAt(row, 0);
				Message message_StudentList = new Message();
				try {
					message_StudentList = deanTeaWindow.deanTeaBLService.showCourseOfStudents(courseID);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
				if (message_StudentList.getMesType().equals(MessageType.deanTea_showCourseOfStudents_fail)) {
					JOptionPane.showMessageDialog(deanTeaWindow, "尚未有学生选择该课程！");
				} else if (message_StudentList.getMesType().equals(MessageType.deanTea_showCourseOfStudents_success)) {
					ArrayList<Student> students = message_StudentList.getStudents();
					ArrayList<String> attriOfStudent = message_StudentList.getAttriOfStu();
					StudentTableModel tm_Student = new StudentTableModel(students, attriOfStudent);
					JTable jt_Student = new JTable();
					jt_Student.setModel(tm_Student);
					JScrollPane jsp_Student = new JScrollPane(jt_Student);
					
					Toolkit kit = Toolkit.getDefaultToolkit();
					Dimension screenSize = kit.getScreenSize();
					int frameWidth = 960;
					int frameHeight = 540;
					int screenWidth = screenSize.width;
					int screenHeight = screenSize.height;
					JFrame frame = new JFrame("学生列表");
					frame.add(jsp_Student);
					frame.setVisible(true);
					frame.setBounds((screenWidth - frameWidth)/2, (screenHeight - frameHeight)/2,
							frameWidth, frameHeight);
				} else {
					JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
				}
			}
		}
	} // end StudentListButtonListener inside class
	
	class CourseDetailInfoButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			int row = jt_CourseList.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(deanTeaWindow, "请选择课程！");
			} else {
				String courseID = (String) ctm.getValueAt(row, 0);
				Message message_CourseDetail = new Message();
				try {
					message_CourseDetail = deanTeaWindow.deanTeaBLService.showCourseInfo(courseID);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
				if (message_CourseDetail.getMesType().equals(MessageType.deanTea_show_detailCourInfo_fail)) {
					JOptionPane.showMessageDialog(deanTeaWindow, "暂无课程详细信息！");
				} else if(message_CourseDetail.getMesType().equals(MessageType.deanTea_show_detailCourInfo_success)) {
					String detail = message_CourseDetail.getCour().getDetail();
					new CDetail(deanTeaWindow, "详细信息", true, detail);
				} else {
					JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
				}
			}
		}
	} // end CourseDetailInfoButtonListener inside class
}
