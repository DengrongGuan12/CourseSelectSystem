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
	JPanel jp1;//���ÿγ���Ϣ��ѧ�ּ�������δѡ��γ̵�Label
	JPanel jp2;//����ˢ�°�ť��button
	JPanel jp1_jp1;//����ѧ�ּ�
	JLabel jp1_jl1;//��δѡ��γ�
	JLabel jp1_jp1_jl2;//ѧ�ּ�
	//JComboBox<String> jp1_jcb;
	JLabel jp1_jp1_jl1;//ѧ�ּ�
	
	TableModel tm;
	JScrollPane jp1_jp2_jsp;
	JTable jt;
	JButton jb;//ˢ�°�ť
	
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
		jp1_jl1=new JLabel("��δѡ��γ̣�");
		jp1_jl1.setFont(MyTools.f1);
		jp1_jl1.setVisible(false);
		jp1.add(jp1_jl1,"North");
//		jp1_jl1=new JLabel("ѧ��");
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
//				String t1="��"+" "+s1+"��"+" "+"1"+" "+"ѧ��";
//				String t2="��"+" "+s1+"��"+" "+"2"+" "+"ѧ��";
//				
//			}
//		}
//		jp1_jcb.addItemListener(this);
		initialPanel();
		jp2=new JPanel(new FlowLayout());
		
		jb=new JButton("ˢ��");
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
			jp1_jp1_jl2=new JLabel("ѧ�ּ�:");
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
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
			
	}
	
	private void setGPA(ArrayList<Course> courses){
		int size=courses.size();
		boolean b=true;
		double res=0;//��¼���Ľ��
		int mulsum=0;//��¼�ɼ���ѧ�ֵĳ˻�֮��
		int sum=0;//��¼��ѧ��
		for(int i=0;i<size;i++){
			Course c=courses.get(i);
			if(c.getProperty().equals("��ѡ")||c.getProperty().equals("ͨʶ")||c.getProperty().equals("��������")){
				continue;
			}else{
				if(c.getScore().equals("��δ����")){
					b=false;
					jp1_jp1_jl1.setText("ȱ����Ϣ��");
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
				jp1_jp1_jl1.setText("ȱ����Ϣ��");
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
				jp1_jp1_jl2=new JLabel("ѧ�ּ�:");
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
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
			
	
	}

	

}
