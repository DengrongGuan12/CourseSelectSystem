package presentation.classTeaView;

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
import javax.swing.UIManager;

import presentation.stuView.TableModel;

import tools.MyTools;

import businesslogicservice.ClassTeaBLService;

import common.Course;
import common.Message;
import common.MessageType;

public class MyCoursePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2;
	JButton jb1;
	TableModel tm1;
	JScrollPane jsp1;
	JLabel jl1;//放置自己尚未有课程的文字
	Message message;
	ClassTeaBLService classTeaBLService;
	JTable jt1;

	public void setMessage(Message message) {
		this.message = message;
	}


	public void setClassTeaBLService(ClassTeaBLService classTeaBLService) {
		this.classTeaBLService = classTeaBLService;
	}
	public MyCoursePanel(ClassTeaBLService classTeaBLService,Message message){
		this.setMessage(message);
		this.setClassTeaBLService(classTeaBLService);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		jp1=new JPanel(new BorderLayout());//放Table的面板或者Label的面板
		jl1=new JLabel("尚未有您的课程发布！");
		jl1.setFont(MyTools.f6);
		jp1.add(jl1,"North");
		
		
		initialPanel();
		jp2=new JPanel(new FlowLayout());
		jb1=new JButton("刷新");
		jb1.setFont(MyTools.f1);
		jb1.addActionListener(this);
		
		
		jp2.add(jb1);
		
		this.add(jp1,"Center");
		this.add(jp2,"South");
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		refresh();
		
	}
	private void initialPanel(){

		Message message=new Message();
		try {
			message=this.classTeaBLService.showMyCourse(this.message.getU().getNum());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_showMyCourse_fail)){
			
			jl1.setVisible(true);
		
			
		}else if(message.getMesType().equals(MessageType.tea_showMyCourse_success)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			tm1=new TableModel(courses,attriOfCourse);
			jt1=new JTable();
			jt1.setModel(tm1);
			
			jsp1=new JScrollPane(jt1);
			jp1.add(jsp1);
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	
	}
	
	private void refresh(){

		Message message=new Message();
		try {
			message=this.classTeaBLService.showMyCourse(this.message.getU().getNum());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_showMyCourse_fail)){
		
			jl1.setVisible(true);
		
			
		}else if(message.getMesType().equals(MessageType.tea_showMyCourse_success)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			if(jt1==null){
				jt1=new JTable();
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1=new JScrollPane(jt1);
				jp1.add(jsp1);
			}else {
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		
	
	}
	

}
