package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.util.ArrayList;


import javax.swing.JButton;

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


public class ShowScorePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel jp1;//放置课程信息加学分绩或者尚未选择课程的Label
	JPanel jp2;//放置刷新按钮的button
	JPanel jp1_jp1;//放置学分绩
	JLabel jp1_jl1;//尚未选择课程
	JLabel jp1_jp1_jl2;//学分绩
	//JComboBox<String> jp1_jcb;
	JLabel jp1_jp1_jl1;//学分绩
	
	TableModel tm;
	JScrollPane jp1_jp2_jsp;
	JTable jt;
	JButton jb;//刷新按钮
	
	Message message;
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public StudentBLService getStudentBLService() {
		return studentBLService;
	}

	public void setStudentBLService(StudentBLService studentBLService) {
		this.studentBLService = studentBLService;
	}

	StudentBLService studentBLService;
	
	public ShowScorePanel(StudentBLService studentBLService,Message message){
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());
		jp1=new JPanel(new BorderLayout());
		jp1_jl1=new JLabel("尚未选择课程！");
		jp1_jl1.setFont(MyTools.f1);
		jp1_jl1.setVisible(false);
		jp1.add(jp1_jl1,"North");
//		jp1_jl1=new JLabel("学期");
//		jp1_jl1.setFont(MyTools.f1);
//		jp1_jcb=new JComboBox<String>();
//		int term=((Student)this.message.getU()).getTerm();
//		String year=((Student)this.message.getU()).getYear();
//		String grade=((Student)this.message.getU()).getGrade();
//		ArrayList<String> xueqi=new ArrayList<String>();
//		int grd=Integer.parseInt(grade);
//		if(term==1){
//			for(int i=1;i<grd;i++){
//				String s1=i+"";
//				String t1="大"+" "+s1+"第"+" "+"1"+" "+"学期";
//				String t2="大"+" "+s1+"第"+" "+"2"+" "+"学期";
//				
//			}
//		}
//		jp1_jcb.addItemListener(this);
		initialPanel();
		jp2=new JPanel(new FlowLayout());
		
		jb=new JButton("刷新");
		jb.setFont(MyTools.f1);
		jb.addActionListener(this);
		jp2.add(jb);
		
		this.add(jp1,"Center");
		this.add(jp2,"South");
		
		
	}

//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==jp1_jcb){
//			
//		}
//		
//	}
	private void initialPanel(){
		Message message=new Message();
		try {
			message=this.studentBLService.showMyScore(this.message.getU().getNum());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			jp1_jl1.setVisible(true);
		
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_success)){
			jp1_jl1.setVisible(false);
			jp1_jp1=new JPanel(new FlowLayout());
			jp1_jp1_jl2=new JLabel("学分绩:");
			jp1_jp1_jl2.setFont(MyTools.f1);
			
			jp1_jp1_jl1=new JLabel();
		
			jp1_jp1.add(jp1_jp1_jl2);
			jp1_jp1.add(jp1_jp1_jl1);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String> colName=message.getAttri();
			setGPA(courses);
			tm=new TableModel(courses, colName);
			jt=new JTable(tm);
			jp1_jp2_jsp=new JScrollPane(jt);
			jp1.add(jp1_jp1,"North");
			jp1.add(jp1_jp2_jsp,"Center");
			
			
			
		}else{
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
			
	}
	
	private void setGPA(ArrayList<Course> courses){
		int size=courses.size();
		boolean b=true;
		double res=0;//记录最后的结果
		int mulsum=0;//记录成绩与学分的乘积之和
		int sum=0;//记录总学分
		for(int i=0;i<size;i++){
			Course c=courses.get(i);
			if(c.getProperty().equals("公选")||c.getProperty().equals("通识")||c.getProperty().equals("新生研讨")){
				continue;
			}else{
				if(c.getScore().equals("尚未公布")){
					b=false;
					jp1_jp1_jl1.setText("缺少信息！");
					break;
				}else {
					int score=Integer.parseInt(c.getScore());
					int credit=Integer.parseInt(c.getCredit());
					
					mulsum+=score*credit;
					sum+=credit;
				}
			}
				
		}
		if(b){
			if(sum==0){
				jp1_jp1_jl1.setText("缺少信息！");
			}else {
				res=mulsum/sum;
				res=res/100;
				res=res*5;
				DecimalFormat dfDecimalFormat=new DecimalFormat(".###");
				res=Double.valueOf(dfDecimalFormat.format(res));
				String reString=String.valueOf(res);
				jp1_jp1_jl1.setText(reString);
			}
			
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		refresh();
		
	}
	private void refresh(){

		Message message=new Message();
		try {
			message=this.studentBLService.showMyScore(this.message.getU().getNum());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			if(jp1_jp1!=null){
				jp1_jp1.setVisible(false);
			}
			if(jp1_jp2_jsp!=null){
				jp1_jp2_jsp.setVisible(false);
			}
			jp1_jl1.setVisible(true);
			
		
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_success)){
			jp1_jl1.setVisible(false);
			if(jp1_jp1==null){
				jp1_jp1=new JPanel(new FlowLayout());
				jp1_jp1_jl2=new JLabel("学分绩:");
				jp1_jp1_jl2.setFont(MyTools.f1);
				
				jp1_jp1_jl1=new JLabel();
				
				jp1_jp1.add(jp1_jp1_jl2);
				jp1_jp1.add(jp1_jp1_jl1);
				jp1.add(jp1_jp1,"North");
			}else{
				jp1_jp1.setVisible(true);
				
			}
			if(jp1_jp2_jsp==null){
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String> colName=message.getAttri();
				setGPA(courses);
				tm=new TableModel(courses, colName);
				jt=new JTable(tm);
				jp1_jp2_jsp=new JScrollPane(jt);
				
				jp1.add(jp1_jp2_jsp,"Center");
			}else {
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String> colName=message.getAttri();
				setGPA(courses);
				tm=new TableModel(courses, colName);
				jt.setModel(tm);
				jp1_jp2_jsp.setVisible(true);
				
			}
			
			
		}else{
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
			
	
	}

	

}
