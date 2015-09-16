package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;



import tools.ImagePanel;
import tools.MyTools;

import businesslogicservice.StudentBLService;

import common.Course;
import common.Message;
import common.MessageType;
import common.Student;

public class KuaZhuanYePanel extends JPanel implements ActionListener,ItemListener{

	private static final long serialVersionUID = 1L;
	JPanel courseInfo;
	
	
	JTable jt1;
	JPanel jp1;//放置选择院系组件的面板
	JLabel jl1;//放尚未有专业课发布文字
	JLabel jp1_jl2;//"选择院系"
	JComboBox<String> jp1_jcb;//复选框
	
	TableModel tm1;//专业课表
	JScrollPane jsp1;
	JPanel buttonPanel;
	JButton jb1,jb2,jb3;//刷新，选择，查看详细信息
	StudentBLService studentBLService;
	StuWindow stuWindow;
	String defaultDean;
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
	Message message;
	
	public KuaZhuanYePanel(StudentBLService studentBLService,Message message){

		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());
		
		
		jp1=new JPanel(new FlowLayout());
		jp1_jl2=new JLabel("请选择院系");
		jp1.setFont(MyTools.f6);
		
		jp1_jcb=new JComboBox<String>();
		String [] deans=new String[15];
		deans[0]="软件学院";
		deans[1]="电子科学学院";
		deans[2]="大气科学学院";
		deans[3]="天文系";
		deans[4]="地理与海洋科学学院";
		deans[5]="地球科学学院";
		deans[6]="计算机科学与技术学院";
		deans[7]="哲学系";
		deans[8]="文学院";
		deans[9]="数学系";
		deans[10]="医学院";
		deans[11]="历史学院";
		deans[12]="物理学院";
		deans[13]="政府管理学院";
		deans[14]="环境科学学院";
		Student student=(Student)this.message.getU();
		int n=0;//记录本身的学院
		for(int i=0;i<15;i++){
			if(deans[i].equals(student.getDean())){
				n=i;
				continue;
			}else {
				jp1_jcb.addItem(deans[i]);
			}
			
		}
		if(n==14){
			defaultDean=deans[13];
			jp1_jcb.setSelectedItem(defaultDean);
		}else if(n==0){
			defaultDean=deans[1];
			jp1_jcb.setSelectedItem(defaultDean);
		}else {
			defaultDean=deans[n+1];
			jp1_jcb.setSelectedItem(defaultDean);
		}
		jp1_jcb.addItemListener(this);

		
		jp1.add(jp1_jl2);
		jp1.add(jp1_jcb);

		
		
		courseInfo=new JPanel(new BorderLayout());//放Table的面板或者Label的面板
		jl1=new JLabel("该院系尚未有跨专业课发布！");
		jl1.setFont(MyTools.f6);
		
		courseInfo.add(jl1,"North");
		
		
		
		initialPanel();
		buttonPanel=new JPanel(new FlowLayout());
		jb1=new JButton("刷新");
		jb1.setFont(MyTools.f1);
		jb1.addActionListener(this);
		
		jb2=new JButton("显示详细信息");
		jb2.setFont(MyTools.f1);
		jb2.addActionListener(this);
		
		jb3=new JButton("选择");
		jb3.setFont(MyTools.f1);
		jb3.addActionListener(this);
		
		buttonPanel.add(jb1);
		buttonPanel.add(jb2);
		buttonPanel.add(jb3);
		
		this.add(jp1,"North");
		this.add(courseInfo,"Center");
		this.add(buttonPanel,"South");
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			refresh();
		}else if(e.getSource()==jb2){
			int row=this.jt1.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this, "请选择一行！");
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
					JOptionPane.showMessageDialog(this, "无对应信息！");
				}else {
					String detail=message.getCour().getDetail();
					new CDetail(this.stuWindow, "详细信息",true,detail );
					
					
				}
				
				
				
			}
		}else if(e.getSource()==jb3){
			int row=this.jt1.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this,"请选择一行！");
				
				
			}else {
				String cNum=(String)tm1.getValueAt(row,0);
				String time=(String)tm1.getValueAt(row,3);
				Message message=new Message();
				Course c=new Course();
				c.setNum(cNum);
				c.setTime(time);
				
				try {
					message=this.studentBLService.selectCourse(this.message.getU().getNum(), c);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				if(message.getMesType().equals(MessageType.stu_selectCourseFail_HasSelected)){
					JOptionPane.showMessageDialog(this,"不能重复选择！");
				}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
					String cNames="";
					ArrayList<Course> cs=message.getCourses();
					int l=cs.size();
					for(int i=0;i<l;i++){
						cNames+=((Course)cs.get(i)).getName()+" ";
					}
					JOptionPane.showMessageDialog(this, "选课成功！\n"+"但和课程"+cNames+"时间有冲突！\n"+"你可以去课程退选中退选该课程！");
				}else if(message.getMesType().equals(MessageType.stu_selectCourseSuccess)){
					this.stuWindow.stateNow.setText("选课成功！");
					
					ImageIcon im=new ImageIcon("images\\Stu\\selSuccess.png");
					suc=new JDialog();
					ImagePanel imagePanel=new ImagePanel(im.getImage());
					suc.add(imagePanel);
					suc.setUndecorated(true);
					suc.setSize(150,80);
					suc.setVisible(true);
					int width= Toolkit.getDefaultToolkit().getScreenSize().width;
					int height=Toolkit.getDefaultToolkit().getScreenSize().height;
					suc.setLocation((width-150)/2,(height-80)/2);
					
					
					showSelSuccess shSelSuccess=new showSelSuccess();
					shSelSuccess.start();
					
					
				}
				
				
			}
			
		}
		
	
		
	}
	private class showSelSuccess extends Thread{
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
	
	private void initialPanel(){
		Message message=new Message();
		try {
			message=this.studentBLService.showKuaZhuanYe(defaultDean);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			//JOptionPane.showMessageDialog(this, "尚未有通识课公布，请耐心等待！");
			jl1.setVisible(true);
			
			
		
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			jl1.setVisible(false);
			
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			tm1=new TableModel(courses,attriOfCourse);
			jt1=new JTable();
			jt1.setModel(tm1);
			
			jsp1=new JScrollPane(jt1);
			
			courseInfo.add(jsp1);
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	}
	
	private void refresh(){
		String dean=this.jp1_jcb.getSelectedItem().toString();
		Message message=new Message();
		try {
			message=this.studentBLService.showKuaZhuanYe(dean);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			//JOptionPane.showMessageDialog(this, "尚未有通识课公布，请耐心等待！");
			jl1.setVisible(true);
			if(jsp1!=null){
				jsp1.setVisible(false);
			}
			
		
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			jl1.setVisible(false);
			ArrayList<Course> courses=message.getCourses();
			ArrayList<String>attriOfCourse=message.getAttri();
			if(jsp1!=null){
				tm1=new TableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1.setVisible(true);
			}else {
				tm1=new TableModel(courses,attriOfCourse);
				jt1=new JTable();
				jt1.setModel(tm1);
				
				jsp1=new JScrollPane(jt1);
				
				courseInfo.add(jsp1);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp1_jcb){
			refresh();
		}
		
		
	}
	

}
