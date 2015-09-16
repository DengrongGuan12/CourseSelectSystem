package presentation.deanTeaView;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import businesslogicservice.DeanTeaBLService;
import common.Course;
import common.DeanTea;
import common.Message;
import common.MessageType;
import common.Teacher;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import presentation.tableModel.TeacherTableModel;

import tools.ImagePanel;
import tools.MyTools;

public class PublishCoursePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COURSE_SECTION_MIN = 2;
	private static final int COURSE_SECTION_MAX = 3;
	
	private JTextField textField_CourseID;
	private JTextField textField_CourseName;
	private JTextField textField_Place;
	private JTextField textField_TeacherName;
	private JTextField textField_TeacherID;
	private JTextField textField_Time;
	private JTextField textField_Credit;
	private JTextField textField_StudentNumber;
	private JComboBox<String>  comboBox;

	private Message message;
	@SuppressWarnings("unused")
	private DeanTeaBLService deanTeaBLService;
    private DeanTeaWindow deanTeaWindow;
    JDialog suc;
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
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PublishCoursePanel(DeanTeaBLService deanTeaBLService, Message message) {
		this.setDeanTeaBLService(deanTeaBLService);
		this.setMessage(message);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(8);
		add(panel_1, BorderLayout.NORTH);
		
		JLabel lbl_Title = new JLabel("发布课程");
		panel_1.add(lbl_Title);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(5, 4, 100, 81));
		
		JLabel lbl_CourseID = new JLabel("课程号");
		lbl_CourseID.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_CourseID);
		
		textField_CourseID = new JTextField();
		panel_2.add(textField_CourseID);
		textField_CourseID.setColumns(10);
		
		JLabel lbl_CourseName = new JLabel("课程名");
		lbl_CourseName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_CourseName);
		
		textField_CourseName = new JTextField();
		panel_2.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		JLabel lbl_Place = new JLabel("地点");
		lbl_Place.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_Place);
		
		JPanel panel_Place = new JPanel();
		panel_2.add(panel_Place);
		panel_Place.setLayout(new BoxLayout(panel_Place, BoxLayout.X_AXIS));
		
		textField_Place = new JTextField();
		textField_Place.setEditable(false);
		panel_Place.add(textField_Place);
		textField_Place.setColumns(10);
		
		JButton button_Place = new JButton("*");
		button_Place.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_Place.addActionListener(new PlaceButtonListener());
		panel_Place.add(button_Place);
		
		JLabel lbl_Time = new JLabel("时间");
		lbl_Time.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_Time);
		
		JPanel panel_Time = new JPanel();
		panel_2.add(panel_Time);
		panel_Time.setLayout(new BoxLayout(panel_Time, BoxLayout.X_AXIS));
		
		textField_Time = new JTextField();
		textField_Time.setEditable(false);
		panel_Time.add(textField_Time);
		textField_Time.setColumns(10);
		
		JButton button_Time = new JButton("*");
		button_Time.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_Time.addActionListener(new TimeButtonListener());
		panel_Time.add(button_Time);
		
		JLabel lbl_TeacherID = new JLabel("教师工号");
		lbl_TeacherID.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_TeacherID);
		
		JPanel panel_TeacherID = new JPanel();
		panel_2.add(panel_TeacherID);
		panel_TeacherID.setLayout(new BoxLayout(panel_TeacherID, BoxLayout.X_AXIS));
		
		textField_TeacherID = new JTextField();
		textField_TeacherID.setEditable(false);
		panel_TeacherID.add(textField_TeacherID);
		textField_TeacherID.setColumns(10);
		
		JButton button_TeacherID = new JButton("*");
		button_TeacherID.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_TeacherID.addActionListener(new TeacherIDButtonListener());
		panel_TeacherID.add(button_TeacherID);
		
		JLabel lbl_TeacherName = new JLabel("教师姓名");
		lbl_TeacherName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_TeacherName);
		
		textField_TeacherName = new JTextField();
		textField_TeacherName.setEditable(false);
		panel_2.add(textField_TeacherName);
		textField_TeacherName.setColumns(10);
		
		JLabel lbl_Credit = new JLabel("课程学分");
		lbl_Credit.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_Credit);
		
		textField_Credit = new JTextField();
		panel_2.add(textField_Credit);
		textField_Credit.setColumns(10);
		
		JLabel lbl_StudentNumber = new JLabel("学生数量");
		lbl_StudentNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_StudentNumber);
		
		textField_StudentNumber = new JTextField();
		panel_2.add(textField_StudentNumber);
		textField_StudentNumber.setColumns(10);
		
		JLabel lbl_CourseType = new JLabel("课程类型");
		lbl_CourseType.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbl_CourseType);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "通识课", "公选课", "新生研讨课",
						"专业准入\\准出课", "通修课"}));
		panel_2.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		FlowLayout fl_panel_3 = (FlowLayout) panel_3.getLayout();
		fl_panel_3.setVgap(12);
		fl_panel_3.setHgap(100);
		add(panel_3, BorderLayout.SOUTH);
		
		JButton button_Publish = new JButton("发布课程");
		button_Publish.addActionListener(new PublishButtonListener());
		panel_3.add(button_Publish);
		
		JButton button_Cancel = new JButton("取消");
		button_Cancel.addActionListener(new CancelButtonListener());
		panel_3.add(button_Cancel);
		
		// 美化界面侧边空白面板
		JPanel panel_East = new JPanel();
		add(panel_East, BorderLayout.EAST);
		JPanel panel_West = new JPanel();
		add(panel_West, BorderLayout.WEST);

	} // end function constructor
	
	class PublishButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Course course_Publish = new Course();
			Message message_Publish = new Message();
			String courseID = textField_CourseID.getText();
			String courseName = textField_CourseName.getText();
			String place = textField_Place.getText();
			String time = textField_Time.getText();
			String teacherID = textField_TeacherID.getText();
			String teacherName = textField_TeacherName.getText();
			String credit = textField_Credit.getText();
			String studentNumber = textField_StudentNumber.getText();
			String courseType = (String) comboBox.getSelectedItem();
			DeanTea deanTea = (DeanTea) message.getU();
			String dean = deanTea.getDean();
			boolean publish = false;
			StringBuilder content = new StringBuilder("");
			
			// 判断能否发布并设置返回对话框内容
			if (courseID.equals("")) {
				content.append("课程号 ");
			} if (courseName.equals("")) {
				content.append("课程名 ");
			} if (place.equals("")) {
				content.append("地点 ");
			} if (time.equals("")) {
				content.append("时间 ");
			} if (teacherID.equals("")) {
				content.append("教师工号 ");
			} if (teacherName.equals("")) {
				content.append("教师姓名 ");
			} if (credit.equals("")) {
				content.append("课程学分 ");
			} if (studentNumber.equals("")) {
				content.append("学生数量 ");
			} if (courseType.equals("")) {
				content.append("课程类型 ");
			}
			if (content.toString().equals("")) {
				publish = true;
			}
			
			if (publish) {
				// 初始化发布课程信息
				course_Publish.setNum(courseID);
				course_Publish.setName(courseName);
				course_Publish.setPlace(place);
				course_Publish.setTime(time);
				course_Publish.setTeaNum(teacherID);
				course_Publish.setTeaName(teacherName);
				course_Publish.setCredit(credit);
				course_Publish.setLimit(studentNumber);
				course_Publish.setProperty(courseType);
				course_Publish.setDean(dean);
				// 初始化课程属性
				course_Publish.setIsOptional("否"); 
				course_Publish.setIsZhunru("否");
				course_Publish.setIsTongshi("否");
				course_Publish.setIsGongxuan("否");
				course_Publish.setIsXinSheng("否");
				if (course_Publish.getProperty().equals("通识课")) {
					course_Publish.setIsOptional("是");
					course_Publish.setIsTongshi("是");
				} else if (course_Publish.getProperty().equals("公选课")) {
					course_Publish.setIsOptional("是");
					course_Publish.setIsGongxuan("是");
				} else if (course_Publish.getProperty().equals("新生研讨课")) {
					course_Publish.setIsOptional("是");
					course_Publish.setIsXinSheng("是");
				} else if (course_Publish.getProperty().equals("专业准入/准出课")) {
					course_Publish.setIsZhunru("是");
				} // 通修课不需设置
				
				try {
					message_Publish = deanTeaWindow.deanTeaBLService.publish(course_Publish);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
				
				if (message_Publish.getMesType().equals(MessageType.deanTea_pubCourse_success)) {
					deanTeaWindow.stateNow.setText("发布成功！");
					ImageIcon im=new ImageIcon("images\\DeanTea\\publishSuc.png");
					suc=new JDialog();
					ImagePanel imagePanel=new ImagePanel(im.getImage());
					suc.add(imagePanel);
					suc.setUndecorated(true);
					suc.setSize(150,80);
					suc.setVisible(true);
					int width= Toolkit.getDefaultToolkit().getScreenSize().width;
					int height=Toolkit.getDefaultToolkit().getScreenSize().height;
					suc.setLocation((width-150)/2,(height-80)/2);
					
					PublishSuccess Success=new PublishSuccess();
					Success.start();
				} else if (message_Publish.getMesType().equals(MessageType.deanTea_pubCourse_fail)){
					JOptionPane.showMessageDialog(deanTeaWindow, "课程发布失败，请注意课程发布格式!");
				} else if (message_Publish.getMesType().equals(MessageType.deanTea_pubCourse_fail_courseID)) {
					JOptionPane.showMessageDialog(deanTeaWindow, "课程发布失败，课程号重复！");
				} else if (message_Publish.getMesType().equals(MessageType.deanTea_pubCourse_fail_teacherCourseConflict)) {
					JOptionPane.showMessageDialog(deanTeaWindow, "课程发布失败，课程与教师课程列表时间冲突！");
				} else if (message_Publish.getMesType().equals(MessageType.deanTea_pubCourse_fail_timePlaceConflict)) {
					JOptionPane.showMessageDialog(deanTeaWindow, "课程发布失败，课程时间地点与已有课程冲突！");
				} else {
					JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题");
				}
			}
			else {
				content.append("\n内容未输入，请重新输入！");
				JOptionPane.showMessageDialog(deanTeaWindow, content);
			}	
		}
	} // end PublishButtonListener inside class
	private class PublishSuccess extends Thread{
    	public synchronized void run() {
            try {
                   Thread.sleep(1500);
                   suc.dispose();
            } catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
       }
    	
    }
	class CancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textField_CourseID.setText("");
			textField_CourseName.setText("");
			textField_Place.setText("");
			textField_Time.setText("");
			textField_TeacherID.setText("");
			textField_TeacherName.setText("");
			textField_Credit.setText("");
			textField_StudentNumber.setText("");
			comboBox.setSelectedIndex(0);
		}
	} // end CancelButtonListener inside class
	
	class PlaceButtonListener implements ActionListener {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed (ActionEvent event) {
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = (int) screenSize.getWidth();
			int screenHeight = (int) screenSize.getHeight();
			int frameWidth = 445;
			int frameHeight = 200;
			
			final JFrame frame_Place = new JFrame("课程地点");
			frame_Place.setSize(frameWidth, frameHeight);
			frame_Place.setLocation((screenWidth - frameWidth)/2, (screenHeight - frameHeight)/2);
			frame_Place.setResizable(false);
			frame_Place.setVisible(true);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			frame_Place.add(panel);
			
			JLabel lbl_TeachingBuilding = new JLabel("请选择教学楼");
			lbl_TeachingBuilding.setFont(MyTools.f5);
			lbl_TeachingBuilding.setBounds(42, 27, 139, 24);
			panel.add(lbl_TeachingBuilding);
			
			JLabel lbl_RoomNum = new JLabel("请输入教室号：");
			lbl_RoomNum.setFont(MyTools.f5);
			lbl_RoomNum.setBounds(42, 83, 154, 24);
			panel.add(lbl_RoomNum);
			
			final JComboBox comboBox_Place = new JComboBox();
			comboBox_Place.setFont(MyTools.f2);
			comboBox_Place.setModel(new DefaultComboBoxModel(new String[] {"", "仙一", "仙二", "鼓楼"}));
			comboBox_Place.setBounds(254, 31, 100, 21);
			panel.add(comboBox_Place);
			
			final JTextField textField_Room = new JTextField();
			textField_Room.setBounds(254, 87, 100, 21);
			panel.add(textField_Room);
			textField_Room.setColumns(10);
			
			JButton button_Comfire = new JButton("确认");
			button_Comfire.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					StringBuilder place = new StringBuilder("");
					boolean state = true;
					
					String teachingBuilding = (String) comboBox_Place.getSelectedItem();
					if (teachingBuilding.equals("")) {
						JOptionPane.showMessageDialog(deanTeaWindow, "请选择课程教学楼！");
						state = false;
					} else {
						place.append(teachingBuilding);
					}
					
					if (state) {
						String room = textField_Room.getText();
						if (room.equals("")) {
							JOptionPane.showMessageDialog(deanTeaWindow, "请输入教室号！");
						} else {
							int roomNum = 0;
							try {
								roomNum = Integer.parseInt(room);
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(deanTeaWindow, "教室号应为数字！");
								state = false;
							}
							
							if (state) {
								if (100 <= roomNum && roomNum <= 1000) {
									place.append(room);
									textField_Place.setText(place.toString());
									frame_Place.dispose();
								} else {
									JOptionPane.showMessageDialog(deanTeaWindow, "不存在对应教室号！");
									state = false;
								} // end inside if 
							} // end out if 
						}
					}
					
				}	
			});
			button_Comfire.setFont(MyTools.f2);
			button_Comfire.setBounds(73, 129, 93, 23);
			panel.add(button_Comfire);
			
			JButton button_Cancel = new JButton("取消");
			button_Cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					comboBox_Place.setSelectedIndex(0);
					textField_Room.setText("");
				}
			});
			button_Cancel.setFont(MyTools.f2);
			button_Cancel.setBounds(238, 129, 93, 23);
			panel.add(button_Cancel);
		}
	} // end PlaceButtonListener inside class
	
	class TimeButtonListener implements ActionListener {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void actionPerformed(ActionEvent event) {
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = (int) screenSize.getWidth();
			int screenHeight = (int) screenSize.getHeight();
			int frameWidth = 480;
			int frameHeight = 270;
			
			final JFrame frame_Time = new JFrame("课程时间");
			frame_Time.setSize(frameWidth, frameHeight);
			frame_Time.setLocation((screenWidth - frameWidth)/2, (screenHeight - frameHeight)/2);
			frame_Time.setResizable(false);
			frame_Time.setVisible(true);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			frame_Time.add(panel);
			
			JPanel panel_Week = new JPanel();
			panel_Week.setBounds(0, 0, 464, 43);
			panel.add(panel_Week);
			panel_Week.setLayout(null);
			
			JLabel lbl_Week = new JLabel("请选择课程周次");
			lbl_Week.setFont(MyTools.f5);
			lbl_Week.setBounds(31, 10, 160, 31);
			panel_Week.add(lbl_Week);
			
			final JComboBox comboBox_Week = new JComboBox();
			comboBox_Week.setModel(new DefaultComboBoxModel(new String[] {"", "周一", "周二", "周三", "周四", "周五", "周六", "周日"}));
			comboBox_Week.setFont(MyTools.f2);
			comboBox_Week.setBounds(265, 10, 100, 28);
			panel_Week.add(comboBox_Week);
			
			JPanel panel_Section = new JPanel();
			panel_Section.setBounds(0, 40, 464, 153);
			panel.add(panel_Section);
			panel_Section.setLayout(null);
			
			JLabel lbl_Section = new JLabel("请选择课程节次");
			lbl_Section.setBounds(30, 20, 147, 24);
			lbl_Section.setFont(MyTools.f5);
			panel_Section.add(lbl_Section);
			
			final JRadioButton radioButton_1 = new JRadioButton("一");
			radioButton_1.setBounds(65, 64, 45, 23);
			panel_Section.add(radioButton_1);
			
			final JRadioButton radioButton_2 = new JRadioButton("二");
			radioButton_2.setBounds(132, 64, 45, 23);
			panel_Section.add(radioButton_2);
			
			final JRadioButton radioButton_3 = new JRadioButton("三");
			radioButton_3.setBounds(203, 64, 45, 23);
			panel_Section.add(radioButton_3);
			
		    final JRadioButton radioButton_4 = new JRadioButton("四");
			radioButton_4.setBounds(276, 64, 45, 23);
			panel_Section.add(radioButton_4);
			
			final JRadioButton radioButton_5 = new JRadioButton("五");
			radioButton_5.setBounds(342, 64, 45, 23);
			panel_Section.add(radioButton_5);
			
			final JRadioButton radioButton_6 = new JRadioButton("六");
			radioButton_6.setBounds(65, 117, 45, 23);
			panel_Section.add(radioButton_6);
			
			final JRadioButton radioButton_7 = new JRadioButton("七");
			radioButton_7.setBounds(132, 117, 45, 23);
			panel_Section.add(radioButton_7);
			
			final JRadioButton radioButton_8 = new JRadioButton("八");
			radioButton_8.setBounds(203, 117, 45, 23);
			panel_Section.add(radioButton_8);
			
			final JRadioButton radioButton_9 = new JRadioButton("九");
			radioButton_9.setBounds(276, 117, 45, 23);
			panel_Section.add(radioButton_9);
			
			final JRadioButton radioButton_10 = new JRadioButton("十");
			radioButton_10.setBounds(342, 117, 45, 23);
			panel_Section.add(radioButton_10);
			
			JPanel panel_Button = new JPanel();
			panel_Button.setBounds(0, 194, 464, 35);
			panel.add(panel_Button);
			
			JButton button_Comfire = new JButton("确定");
			button_Comfire.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					boolean state = true;
					StringBuilder time = new StringBuilder(""); 
					
					String week = (String) comboBox_Week.getSelectedItem();
					if (week.equals("")) {
						JOptionPane.showMessageDialog(deanTeaWindow, "请选择课程周次！");
						state = false;
					} else {
						time.append(week);
						time.append(",");
					}
					
					if (state) {
						ArrayList<String> section = new ArrayList<String>();
						if (radioButton_1.isSelected()) {
							section.add("一");
						} 
						if (radioButton_2.isSelected()) {
							section.add("二");
						}
						if (radioButton_3.isSelected()) {
							section.add("三");
						}
						if (radioButton_4.isSelected()) {
							section.add("四");
						}
						if (radioButton_5.isSelected()) {
							section.add("五");
						}
						if (radioButton_6.isSelected()) {
							section.add("六");
						}
						if (radioButton_7.isSelected()) {
							section.add("七");
						}
						if (radioButton_8.isSelected()) {
							section.add("八");
						} 
						if (radioButton_9.isSelected()) {
							section.add("九");
						} 
						if (radioButton_10.isSelected()) {
							section.add("十");
						}
						
						if (section.size() >= COURSE_SECTION_MIN && section.size()<=COURSE_SECTION_MAX) {
							int size = section.size();
							for (int i = 0; i < size; i++) {
								time.append(section.get(i));
								if (i == (size-1)) {
									time.append(" 节");
								} else {
									time.append(",");
								}
							}
							textField_Time.setText(time.toString());
							frame_Time.dispose();
						} else {
							JOptionPane.showMessageDialog(deanTeaWindow, "课程节次应至少" + COURSE_SECTION_MIN + "节；" +
									"\n但不超过" + COURSE_SECTION_MAX + "节！");
						}
					}
				}
			});
			button_Comfire.setFont(MyTools.f2);
			panel_Button.add(button_Comfire);
			
			JButton button_Cancel = new JButton("取消");
			button_Cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					comboBox_Week.setSelectedIndex(0);
					radioButton_1.setSelected(false);
					radioButton_2.setSelected(false);
					radioButton_3.setSelected(false);
					radioButton_4.setSelected(false);
					radioButton_5.setSelected(false);
					radioButton_6.setSelected(false);
					radioButton_7.setSelected(false);
					radioButton_8.setSelected(false);
				}
			});
			button_Cancel.setFont(MyTools.f2);
			panel_Button.add(button_Cancel);
		}
	} // end TimeButtonListener inside class
	
	class TeacherIDButtonListener implements ActionListener {
		private JTable teacherListTable;
		private JScrollPane teacherListJSP;
		private TeacherTableModel ttm;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int frameWidth = 960;
			int frameHeight = 480;
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			
			final JFrame frame = new JFrame("教师列表");
			frame.setVisible(true);
			frame.setBounds((screenWidth - frameWidth)/2, (screenHeight - frameHeight)/2,
					frameWidth, frameHeight);
			frame.setResizable(false);
			frame.setLayout(new BorderLayout());
			
			JPanel panel_Warning = new JPanel();
			frame.add(panel_Warning, BorderLayout.NORTH);
			
			JLabel lbl_Warning = new JLabel("本院尚未有教教师注册！");
			lbl_Warning.setVisible(false);
			panel_Warning.add(lbl_Warning);
			
			// 添加本院教师表
			DeanTea deanTea = (DeanTea) message.getU();
			String dean = deanTea.getDean();
			Message message_ShowTeacherList = new Message();
			try {
				message_ShowTeacherList = deanTeaWindow.deanTeaBLService.showTeacherList(dean);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (message_ShowTeacherList.getMesType().equals(MessageType.deanTea_showTeacherList_fail)) {
				lbl_Warning.setVisible(true);
			} else if (message_ShowTeacherList.getMesType().equals(MessageType.deanTea_showTeacherList_success)){
				lbl_Warning.setVisible(false);
				ArrayList<Teacher> teachers = message_ShowTeacherList.getTeachers();
				ArrayList<String> attriOfTea = message_ShowTeacherList.getAttriOfTea();
				ttm = new TeacherTableModel(teachers, attriOfTea);
				teacherListTable = new JTable();
				teacherListTable.setModel(ttm);
				teacherListJSP = new JScrollPane(teacherListTable);
				frame.add(teacherListJSP, BorderLayout.CENTER);
			} else {
				JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
			}
			
			JPanel panel_Button = new JPanel();
			FlowLayout fl_panel_Button = (FlowLayout) panel_Button.getLayout();
			fl_panel_Button.setHgap(136);
			frame.add(panel_Button, BorderLayout.SOUTH);
			
			JButton button_Comfire = new JButton("确定");
			button_Comfire.setFont(MyTools.f1);
			button_Comfire.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int row = teacherListTable.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(deanTeaWindow, "请选择一个本院教师!");
					} else {
						String teacherID = (String) ttm.getValueAt(row, 0);
						String teacherName = (String) ttm.getValueAt(row, 1);
						textField_TeacherID.setText(teacherID);
						textField_TeacherName.setText(teacherName);
						frame.dispose();
					}
				}
			});
			panel_Button.add(button_Comfire);
		}
	} // end TeacherIDButtonListener inside class

} // end PublishCoursePanel class
