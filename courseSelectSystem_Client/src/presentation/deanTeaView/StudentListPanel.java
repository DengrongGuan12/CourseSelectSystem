package presentation.deanTeaView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import presentation.tableModel.StudentTableModel;
import tools.MyTools;
import businesslogicservice.DeanTeaBLService;
import common.DeanTea;
import common.Message;
import common.MessageType;
import common.Student;

public class StudentListPanel extends JPanel {	
	private static final long serialVersionUID = 1L;

	private JTable table_StudentList;
	private JScrollPane jsp_StudentList;
	private StudentTableModel stm;
	private JLabel lbl_Warning;
	private JPanel panel_StudentList;
	private JButton button_Refresh;
	private JTextField textField_StudentID;
	
	private Message message;
	private DeanTeaBLService deanTeaBLService;
    private DeanTeaWindow deanTeaWindow;
	
	public void setDeanTeaWindow(DeanTeaWindow deanTeaWindow) {
		this.deanTeaWindow = deanTeaWindow;
	}
	
	public void setDeanTeaBLService(DeanTeaBLService deanTeaBLService) {
		this.deanTeaBLService = deanTeaBLService;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * Create StudentList panel
	 */
	public StudentListPanel(DeanTeaBLService deanTeaBLService, Message message) {
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
		this.add(panel_Search, BorderLayout.NORTH);
		
		JLabel lbl_StudentID = new JLabel("学号");
		panel_Search.add(lbl_StudentID);
		
		textField_StudentID = new JTextField();
		panel_Search.add(textField_StudentID);
		textField_StudentID.setColumns(10);
		
		JButton button_Search = new JButton("查找");
		button_Search.addActionListener(new SearchButtonListener());
		panel_Search.add(button_Search);
		
		lbl_Warning = new JLabel("尚未有学生注册！");
		lbl_Warning.setFont(MyTools.f6);
		lbl_Warning.setVisible(false);
		
		panel_StudentList = new JPanel(new BorderLayout());
		panel_StudentList.add(lbl_Warning, BorderLayout.NORTH);
		this.add(panel_StudentList, BorderLayout.CENTER);
		
		JPanel panel_Button = new JPanel();
		this.add(panel_Button, BorderLayout.SOUTH);
		
		button_Refresh = new JButton("刷新");
		button_Refresh.addActionListener(new RefreshButtonListener());
		panel_Button.add(button_Refresh);
		
		refresh();
		//button_Refresh.addActionListener(new RefreshButtonListener());
	} // end function constructor
	
	public void refresh() {
		DeanTea deanTea = (DeanTea)message.getU();
		String dean = deanTea.getDean();
		Message message_StudentList = new Message();
		
		try {
			message_StudentList = this.deanTeaBLService.showStudentList(dean);
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		
		if (message_StudentList.getMesType().equals(MessageType.deanTea_showStudentList_fail)){
			//JOptionPane.showMessageDialog(this, "尚未有课程发布");
			lbl_Warning.setVisible(true);
			if(jsp_StudentList!=null){
				jsp_StudentList.setVisible(false);
			}
		} else if (message_StudentList.getMesType().equals(MessageType.deanTea_showStudentList_success)){
			lbl_Warning.setVisible(false);
			ArrayList<Student> students = message_StudentList.getStudents();
			ArrayList<String> attriOfStu = message_StudentList.getAttriOfStu();
			stm = new StudentTableModel(students,attriOfStu);
			if (jsp_StudentList != null) {
				table_StudentList.setModel(stm);
				jsp_StudentList.setVisible(true);
			} else {
				table_StudentList = new JTable();
				table_StudentList.setModel(stm);
				jsp_StudentList = new JScrollPane(table_StudentList);
				panel_StudentList.add(jsp_StudentList);
			}
		} else {
			JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
		}
	} // end refresh method
	
	class SearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String studentID = textField_StudentID.getText();
			int row = 0;
			boolean select = false;
			
			if (!studentID.equals("")) {
				row = table_StudentList.getRowCount();
				for (int i = 0; i < row; i++) {
					String sNum = (String) stm.getValueAt(i, 0);
					if (studentID.equals(sNum)) {
						table_StudentList.setRowSelectionAllowed(true);
						table_StudentList.setRowSelectionInterval(i, i);
						select = true;
						break;
					}
				}
				if (!select) {
					JOptionPane.showMessageDialog(deanTeaWindow, "没有对应学号的学生！");
				}
			} else {
				JOptionPane.showMessageDialog(deanTeaWindow, "请输入学生学号！");
			}
		}
	} // end SearchButtonListener inside class
	
	class RefreshButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			refresh();
		}
	} // end RefreshButtonListener inside class
	
	
} // end StudentListPanel class
