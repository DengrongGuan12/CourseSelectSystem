package presentation.classTeaView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import javax.swing.UIManager;

import businesslogicservice.ClassTeaBLService;

import common.Course;
import common.Message;
import common.MessageType;

import tools.MyTools;

public class ShowMyStu extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	JPanel jp1;//���ð�ť
	JButton jb1,jb2;//ˢ�£���ʾ�ҵ�ѧ��
	JSplitPane jsp;
	JPanel jp2,jp3,p2;
	Message message;
	ClassTeaBLService classTeaBLService;
	JLabel jl1,jl2,jl3;
	CourseTableModel tm1;
	UserTableModel tm2;
	JTable jt1,jt2;
	JScrollPane jsp1,jsp2;
	CardLayout cardP2;
	JLabel p2_lab1,p2_lab2;
	JPanel jp2_jp1,jp2_jp2,jp2_jp3;
	JButton jp2_jb1;
	String cNum;//���ڼ�¼��ǰѡ�еĿγ�
	int width=200;
	public String getcNum() {
		return cNum;
	}
	public void setcNum(String cNum) {
		this.cNum = cNum;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public ClassTeaBLService getClassTeaBLService() {
		return classTeaBLService;
	}
	public void setClassTeaBLService(ClassTeaBLService classTeaBLService) {
		this.classTeaBLService = classTeaBLService;
	}
	public ShowMyStu(ClassTeaBLService classTeaBLService,Message message){

		this.setMessage(message);
		this.setClassTeaBLService(classTeaBLService);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		jp3=new JPanel(new BorderLayout());//��Table��������Label�����
		jl1=new JLabel("��δ�����Ŀγ̷�����");
		jl1.setFont(MyTools.f6);
		jp3.add(jl1,"North");
		
		initialPanel();
		
		
		jp1=new JPanel(new FlowLayout());
		jb1=new JButton("ˢ��");
		jb1.setFont(MyTools.f1);
		jb1.addMouseListener(this);
		
		jb2=new JButton("��ʾ�ҵ�ѧ��");
		jb1.setFont(MyTools.f1);
		jb2.addMouseListener(this);
		
		
		jp1.add(jb1);
		jp1.add(jb2);
		
		
		
		this.add(jsp,"Center");
		this.add(jp1,"South");
		
	
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
			tm1=new CourseTableModel(courses,attriOfCourse);
			jt1=new JTable();
			jt1.setModel(tm1);
			
			jsp1=new JScrollPane(jt1);
			jp3.add(jsp1,"Center");
			cardP2=new CardLayout();
		    
			  //p2���ڴ�����ֲ�ͬ����ļ�ͷ
			  p2=new JPanel(this.cardP2);
			  p2_lab1=new JLabel(new ImageIcon("images\\ClassTea\\toleft.png"));
			  p2_lab1.addMouseListener(this);
			  p2_lab2=new JLabel(new ImageIcon("images\\ClassTea\\toright.png"));
			  p2_lab2.addMouseListener(this);
			  p2.add(p2_lab1,"0");
			  p2.add(p2_lab2,"1");
			  cardP2.show(p2, "0");
			  jp3.add(p2,"East");
			
		} else {
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
		// jp2���ڴ���ҵ�ѧ������Ϣ
		jp2 = new JPanel(new BorderLayout());
		jp2_jp1 = new JPanel();
		jl2 = new JLabel("�ҵ�ѧ����");
		jl2.setFont(MyTools.f5);
		jp2_jp1.add(jl2);

		jl3 = new JLabel("��δ��ѧ��ѡ�޸��ſγ̣�");
		jp2_jp2 = new JPanel();
		jl3.setFont(MyTools.f2);
		jp2_jp2.add(jl3, "North");
		jl3.setVisible(false);
		
		jp2_jp3=new JPanel(new FlowLayout());
		jp2_jb1=new JButton("ˢ��");
		jp2_jb1.addMouseListener(this);
		jp2_jb1.setFont(MyTools.f2);
		jp2_jp3.add(jp2_jb1);
		jp2_jb1.setEnabled(false);
		
		jp2.add(jp2_jp1,"North");
		jp2.add(jp2_jp2,"Center");
		jp2.add(jp2_jp3,"South");
		
	
		// ��һ����ִ���
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp3,jp2);
		// ָ����ߵ����ռ���ķ�Χ
		jsp.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width);
		
		// �ѱ߽�����Ϊ0
		jsp.setDividerSize(10);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.p2_lab2){
			//����ʾ���ֲ����Ľ�����������
			//��ʾ����ļ�ͷ
			this.cardP2.show(p2, "0");
			this.jsp.setDividerLocation(this.getWidth());
		}else if(e.getSource()==this.p2_lab1){
			//����ʾ���ֲ����Ľ���չ��
			//��ʾ���ҵļ�ͷ
			this.cardP2.show(p2, "1");
			this.jsp.setDividerLocation(this.getWidth()-width);
		}else if(e.getSource()==this.jb1){
			refreshCoursePanel();
		}else if(e.getSource()==this.jp2_jb1){
			refreshStuPanel();
		}else if(e.getSource()==this.jb2){


			int row=this.jt1.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}else {
				this.setcNum((String)tm1.getValueAt(row, 0));
				Message message=new Message();
				try {
					message=this.classTeaBLService.showStuOfMyCourse(this.getcNum());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.cardP2.show(p2, "1");
				
				
				if(message.getMesType().equals(MessageType.tea_showMyStudent_Success)){
					jl3.setVisible(false);
					if(jsp2==null){
						tm2=new UserTableModel(message.getUsers(), message.getAttri());
						jt2=new JTable();
						
						jt2.setModel(tm2);
						jsp2=new JScrollPane(jt2);
						jsp2.setVisible(true);
						jp2_jp2.add(jsp2,"Center");
					}else {
						tm2=new UserTableModel(message.getUsers(), message.getAttri());
						jt2.setModel(tm2);
						jsp2.setVisible(true);
					}
					
					width=400;
					
				}else {
					width=200;
					jl3.setVisible(true);
					if(jsp2!=null){
						jsp2.setVisible(false);
					}
					
				}
				
				this.jsp.setDividerLocation(this.getWidth()-width);
				this.jp2_jb1.setEnabled(true);
				
			}
			
		
		
		}
		
	}
	private void refreshCoursePanel(){




		Message message=new Message();
		try {
			message=this.classTeaBLService.showMyCourse(this.message.getU().getNum());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.tea_showMyCourse_fail)){
			if(p2!=null){
				p2.setVisible(false);
			}
			if(jsp1!=null){
				jsp1.setVisible(false);
			}
			jl1.setVisible(true);
			jsp.setDividerLocation(this.getWidth());
		
			
		}else if(message.getMesType().equals(MessageType.tea_showMyCourse_success)){
			jl1.setVisible(false);
			if(jsp1==null){
				
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String>attriOfCourse=message.getAttri();
				tm1=new CourseTableModel(courses,attriOfCourse);
				jt1=new JTable();
				jt1.setModel(tm1);
				
				jsp1=new JScrollPane(jt1);
				jp3.add(jsp1,"Center");
				cardP2=new CardLayout();
			    
				  //p2���ڴ�����ֲ�ͬ����ļ�ͷ
				  p2=new JPanel(this.cardP2);
				  p2_lab1=new JLabel(new ImageIcon("images\\ClassTea\\toleft.png"));
				  p2_lab1.addMouseListener(this);
				  p2_lab2=new JLabel(new ImageIcon("images\\ClassTea\\toright.png"));
				  p2_lab2.addMouseListener(this);
				  p2.add(p2_lab1,"0");
				  p2.add(p2_lab2,"1");
				  cardP2.show(p2, "0");
				  jp3.add(p2,"East");
				
			}else{
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String>attriOfCourse=message.getAttri();
				tm1=new CourseTableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1.setVisible(true);
				p2.setVisible(true);
			}
			
			
		}else {
			JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
		}
		
	
	
	
	
		
	}
	
	private void refreshStuPanel(){
		Message message=new Message();
		try {
			message=this.classTeaBLService.showStuOfMyCourse(this.getcNum());
			System.out.println(this.getcNum());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(message.getMesType().equals(MessageType.tea_showMyStudent_Success)){
			if(jsp2==null){
				tm2=new UserTableModel(message.getUsers(), message.getAttri());
				jt2=new JTable();
				jt2.setModel(tm2);
				
				jsp2=new JScrollPane(jt2);
				jsp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				jp2_jp2.add(jsp2);
			}else {
				tm2=new UserTableModel(message.getUsers(), message.getAttri());
				jt2.setModel(tm2);
				jsp2.setVisible(true);
			}
			jl3.setVisible(false);
		}else {
			jl3.setVisible(true);
			if(jsp2!=null){
				jsp2.setVisible(false);
			}
			
			
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
