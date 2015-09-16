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
	JPanel jp1;//����ѡ��Ժϵ��������
	JLabel jl1;//����δ��רҵ�η�������
	JLabel jp1_jl2;//"ѡ��Ժϵ"
	JComboBox<String> jp1_jcb;//��ѡ��
	
	TableModel tm1;//רҵ�α�
	JScrollPane jsp1;
	JPanel buttonPanel;
	JButton jb1,jb2,jb3;//ˢ�£�ѡ�񣬲鿴��ϸ��Ϣ
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
		jp1_jl2=new JLabel("��ѡ��Ժϵ");
		jp1.setFont(MyTools.f6);
		
		jp1_jcb=new JComboBox<String>();
		String [] deans=new String[15];
		deans[0]="���ѧԺ";
		deans[1]="���ӿ�ѧѧԺ";
		deans[2]="������ѧѧԺ";
		deans[3]="����ϵ";
		deans[4]="�����뺣���ѧѧԺ";
		deans[5]="�����ѧѧԺ";
		deans[6]="�������ѧ�뼼��ѧԺ";
		deans[7]="��ѧϵ";
		deans[8]="��ѧԺ";
		deans[9]="��ѧϵ";
		deans[10]="ҽѧԺ";
		deans[11]="��ʷѧԺ";
		deans[12]="����ѧԺ";
		deans[13]="��������ѧԺ";
		deans[14]="������ѧѧԺ";
		Student student=(Student)this.message.getU();
		int n=0;//��¼�����ѧԺ
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

		
		
		courseInfo=new JPanel(new BorderLayout());//��Table��������Label�����
		jl1=new JLabel("��Ժϵ��δ�п�רҵ�η�����");
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
		
		jb3=new JButton("ѡ��");
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
		}else if(e.getSource()==jb3){
			int row=this.jt1.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this,"��ѡ��һ�У�");
				
				
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
					JOptionPane.showMessageDialog(this,"�����ظ�ѡ��");
				}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
					String cNames="";
					ArrayList<Course> cs=message.getCourses();
					int l=cs.size();
					for(int i=0;i<l;i++){
						cNames+=((Course)cs.get(i)).getName()+" ";
					}
					JOptionPane.showMessageDialog(this, "ѡ�γɹ���\n"+"���Ϳγ�"+cNames+"ʱ���г�ͻ��\n"+"�����ȥ�γ���ѡ����ѡ�ÿγ̣�");
				}else if(message.getMesType().equals(MessageType.stu_selectCourseSuccess)){
					this.stuWindow.stateNow.setText("ѡ�γɹ���");
					
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
			//JOptionPane.showMessageDialog(this, "��δ��ͨʶ�ι����������ĵȴ���");
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
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
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
			//JOptionPane.showMessageDialog(this, "��δ��ͨʶ�ι����������ĵȴ���");
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
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
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
