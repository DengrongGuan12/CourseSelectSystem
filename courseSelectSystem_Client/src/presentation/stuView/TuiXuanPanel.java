package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;





import tools.ImagePanel;
import tools.MyTools;

import common.Course;
import common.Message;
import common.MessageType;

import businesslogicservice.StudentBLService;

public class TuiXuanPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel courseInfo;
	JTable jt1;
	JLabel jl1;//放置自己尚未有课程的文字
	TableModel tm1;//自己的课程列表
	JScrollPane jsp1;
	JPanel buttonPanel;
	JButton jb1,jb2,jb3;//刷新，退选，查看详细信息
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
	
	public TuiXuanPanel(StudentBLService studentBLService,Message message){
		try {
//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());
		courseInfo=new JPanel(new BorderLayout());//放Table的面板或者Label的面板
		jl1=new JLabel("尚未选择课程！");
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
		
		jb3=new JButton("退选");
		jb3.setFont(MyTools.f1);
		jb3.addActionListener(this);
		
		buttonPanel.add(jb1);
		buttonPanel.add(jb2);
		buttonPanel.add(jb3);
		
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
			}else {
				JOptionPane.showMessageDialog(this,"请选择一行！");
			}
			
		}else if(e.getSource()==jb3){
			if(this.jt1!=null){
				int row=this.jt1.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this,"请选择一行！");
					
					
				}else {
					String cNum=(String)tm1.getValueAt(row,0);
					
					Message message=new Message();
					
					
					try {
						message=this.studentBLService.quitCourse(this.message.getU(), cNum);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					if(message.getMesType().equals(MessageType.stu_quitCourse_fail)){
						JOptionPane.showMessageDialog(this,"不能删除必修课！");
					}else if(message.getMesType().equals(MessageType.stu_quitCourse_success)){
						this.stuWindow.stateNow.setText("退选成功！");
						ImageIcon im=new ImageIcon("images\\Stu\\quitSuccess.png");
						suc=new JDialog();
						ImagePanel imagePanel=new ImagePanel(im.getImage());
						suc.add(imagePanel);
						suc.setUndecorated(true);
						suc.setSize(150,80);
						suc.setVisible(true);
						int width= Toolkit.getDefaultToolkit().getScreenSize().width;
						int height=Toolkit.getDefaultToolkit().getScreenSize().height;
						suc.setLocation((width-150)/2,(height-80)/2);
						showQuitSuccess shQuitSuccess=new showQuitSuccess();
						shQuitSuccess.start();
						
					}
					
					
				}
			}else {
				JOptionPane.showMessageDialog(this,"请选择一行！");
			}
			
			
		}
		
	
		
	}
	private class showQuitSuccess extends Thread{
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
//				JOptionPane.showMessageDialog(this,"课程 "+courses2.get(0).getName()+"\n"+
//				"与课程 "+courses2.get(1).getName()+"\n"+" 可能存在时间冲突！");
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
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
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
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
		
			jl1.setVisible(true);
			if(this.jsp1!=null){
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
				JOptionPane.showMessageDialog(this,"课程 "+courses2.get(0).getName()+"\n"+
				"与课程 "+courses2.get(1).getName()+"\n"+" 可能存在时间冲突！");
			}
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		
	}
	

}
