package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;



import tools.MyTools;
import businesslogicservice.StudentBLService;

import common.Course;
import common.Message;
import common.MessageType;

public class MyCoursePanel extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	JPanel courseInfo;
	JTable jt1;
	JLabel jl1;//�����Լ���δ�пγ̵�����
	TableModel tm1;//�Լ��Ŀγ��б�
	JScrollPane jsp1;
	JPanel buttonPanel;
	JButton jb1,jb2;//ˢ�£��鿴��ϸ��Ϣ
	StudentBLService studentBLService;
	Message message;
	StuWindow stuWindow;
	JDialog suc;
	public StuWindow getStuWindow() {
		return stuWindow;
	}
	public void setStuWindow(StuWindow stuWindow) {
		this.stuWindow = stuWindow;
	}
	public StudentBLService getStudentBLService() {
		return studentBLService;
	}
	public void setStudentBLService(StudentBLService studentBLService) {
		this.studentBLService = studentBLService;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public MyCoursePanel(StudentBLService studentBLService,Message message){
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());
		courseInfo=new JPanel(new BorderLayout());//��Table��������Label�����
		jl1=new JLabel("��δѡ��γ̣�");
		jl1.setFont(MyTools.f6);
		courseInfo.add(jl1,"North");
		
		
		initialPanel();
		buttonPanel=new JPanel(new FlowLayout());
		jb1=new JButton("ˢ��");
		jb1.setFont(MyTools.f1);
		jb1.addActionListener(this);
		
		jb2=new JButton("��ʾ��ϸ��Ϣ");
		jb2.setFont(MyTools.f1);
		jb2.addActionListener(this);
		
		buttonPanel.add(jb1);
		buttonPanel.add(jb2);
		
		this.add(courseInfo,"Center");
		this.add(buttonPanel,"South");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			refresh();
		}else if(e.getSource()==jb2){
			if(this.jt1!=null){
				int row=this.jt1.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
				}else {
					String cNum=(String)tm1.getValueAt(row, 0);
					Message message=new Message();
					try {
						message=this.studentBLService.showCourseDetail(cNum);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(message.getMesType().equals(MessageType.stu_show_detailCourInfoFail)){
						JOptionPane.showMessageDialog(this, "�޶�Ӧ��Ϣ��");
					}else {
						String detail=message.getCour().getDetail();
						new CDetail(this.stuWindow, "��ϸ��Ϣ",true,detail );
						
						
					}
					
					
					
				}
			}else {
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			
		}
		
	
		
	}
	
	
	private void initialPanel(){
		Message message=new Message();
		try {
			message=this.studentBLService.showMyCourseList(this.message.getU().getNum());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			
			jl1.setVisible(true);
		
			
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_timeConflict)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			tm1=new TableModel(courses,attriOfCourse);
			jt1=new JTable();
			jt1.setModel(tm1);
			
			jsp1=new JScrollPane(jt1);
			courseInfo.add(jsp1);
//			ArrayList<ArrayList<Course>> arrayLists=message.getArrayLists();
//			int n=arrayLists.size();
//			for(int i=0;i<n;i++){
//				ArrayList<Course> courses2=arrayLists.get(i);
//				JOptionPane.showMessageDialog(this,"�γ� "+courses2.get(0).getName()+"\n"+
//				"��γ� "+courses2.get(1).getName()+"\n"+" ���ܴ���ʱ���ͻ��");
//			}
			
			
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_success)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			tm1=new TableModel(courses,attriOfCourse);
			jt1=new JTable();
			jt1.setModel(tm1);
			
			jsp1=new JScrollPane(jt1);
			courseInfo.add(jsp1);
			
		}else {
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
	}
	
	public void refresh(){
		Message message=new Message();
		try {
			message=this.studentBLService.showMyCourseList(this.message.getU().getNum());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message==null){
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
		
			jl1.setVisible(true);
			if(jsp1!=null){
				jsp1.setVisible(false);
			}
		
			
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_success)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			if(jt1==null){
				jt1=new JTable();
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1=new JScrollPane(jt1);
				courseInfo.add(jsp1);
			}else {
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
			}
			
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_timeConflict)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			if(jt1==null){
				jt1=new JTable();
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1=new JScrollPane(jt1);
				courseInfo.add(jsp1);
			}else {
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
			}
			
			ArrayList<ArrayList<Course>> arrayLists=message.getArrayLists();
			int n=arrayLists.size();
			for(int i=0;i<n;i++){
				ArrayList<Course> courses2=arrayLists.get(i);
				JOptionPane.showMessageDialog(this,"�γ� "+courses2.get(0).getName()+"\n"+
				"��γ� "+courses2.get(1).getName()+"\n"+" ���ܴ���ʱ���ͻ��");
			}
		}
		
	}
	



}
