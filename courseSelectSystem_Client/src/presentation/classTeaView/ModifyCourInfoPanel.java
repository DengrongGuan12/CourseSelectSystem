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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
//实现拆分窗口
import javax.swing.JSplitPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;






import businesslogicservice.ClassTeaBLService;

import common.Course;
import common.Message;
import common.MessageType;


import tools.ImagePanel;
import tools.MyTools;
public class ModifyCourInfoPanel extends JPanel implements MouseListener,DocumentListener{

	private static final long serialVersionUID = 1L;
	JSplitPane jsp;
	JPanel jp1,jp2,jp3,jp4;
	JTable jt1;
	JLabel jl1;//放置自己尚未有课程的文字
	CourseTableModel tm1;//自己的课程列表
	JScrollPane jsp1;
	Message message;
	JButton jb1,jb2,jb3;
	JPanel p2;
	CardLayout cardP2;
	JLabel  p2_lab1, p2_lab2,jp3_lab1;
	JTextArea jp3_jta;
	JScrollPane jsp2;
	String detail;//用于记录原来的课程详细信息，以便监听到是否发生更新
	String cNum;//用于记录当前选中的课程
	ClassTeaWindow classTeaWindow;
	JDialog suc;
	public void setClassTeaWindow(ClassTeaWindow classTeaWindow) {
		this.classTeaWindow = classTeaWindow;
	}


	public String getcNum() {
		return cNum;
	}


	public void setcNum(String cNum) {
		this.cNum = cNum;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public void setClassTeaBLService(ClassTeaBLService classTeaBLService) {
		this.classTeaBLService = classTeaBLService;
	}


	ClassTeaBLService classTeaBLService;
	public ModifyCourInfoPanel(ClassTeaBLService classTeaBLService,Message message){
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
		jb1.addMouseListener(this);
		
		jb2=new JButton("显示详细信息");
		jb1.setFont(MyTools.f1);
		jb2.addMouseListener(this);
		
		jb3=new JButton("更新课程信息");
		jb3.setFont(MyTools.f1);
		jb3.addMouseListener(this);
		jb3.setEnabled(false);
		
		
		
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		
		
		this.add(jsp,"Center");
		this.add(jp2,"South");
		
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
			jp1.add(jsp1);
			cardP2=new CardLayout();
		    
			  //p2用于存放两种不同方向的箭头
			  p2=new JPanel(this.cardP2);
			  p2_lab1=new JLabel(new ImageIcon("images\\ClassTea\\down.png"));
			  p2_lab1.addMouseListener(this);
			  p2_lab2=new JLabel(new ImageIcon("images\\ClassTea\\up.png"));
			  p2_lab2.addMouseListener(this);
			  p2.add(p2_lab1,"0");
			  p2.add(p2_lab2,"1");
			  cardP2.show(p2, "0");
			  jp1.add(p2,"North");
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		jp4=new JPanel(new BorderLayout());
		
		  //jp3用于存放课程详细信息
		  jp3=new JPanel(new BorderLayout());
		  jp3_lab1=new JLabel("课程详细信息：");
		  jp3_lab1.setFont(MyTools.f5);
		  jp3_jta=new JTextArea();
		  jp3_jta.setLineWrap(true);//自动换行
		  jp3_jta.setWrapStyleWord(true);//换行不换字
		  jp3_jta.getDocument().addDocumentListener(this);
		  jsp2=new JScrollPane(jp3_jta);
		  jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		  
		  jp3.add(jp3_lab1,"North");
		  jp3.add(jp3_jta,"Center");
		  
		  
		  jp4.add(jp3,"Center");
		  
		  //做一个拆分窗口，分别存放p1,p4
		    jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jp4,jp1);
		    //指定上边的面板占多大的范围
		    jsp.setDividerLocation(0);
		    //把边界线设为0
		   jsp.setDividerSize(0);
	
	
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
			if(p2!=null){
				p2.setVisible(false);
			}
			if(jsp1!=null){
				jsp1.setVisible(false);
			}
			jl1.setVisible(true);
			jsp.setDividerLocation(0);
		
			
		}else if(message.getMesType().equals(MessageType.tea_showMyCourse_success)){
			jl1.setVisible(false);
			if(jsp1==null){
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String>attriOfCourse=message.getAttri();
				tm1=new CourseTableModel(courses,attriOfCourse);
				jt1=new JTable();
				jt1.setModel(tm1);
				
				jsp1=new JScrollPane(jt1);
				jp1.add(jsp1);
				cardP2=new CardLayout();
			    
				  //p2用于存放两种不同方向的箭头
				  p2=new JPanel(this.cardP2);
				  p2_lab1=new JLabel(new ImageIcon("images\\ClassTea\\down.png"));
				  p2_lab1.addMouseListener(this);
				  p2_lab2=new JLabel(new ImageIcon("images\\ClassTea\\up.png"));
				  p2_lab2.addMouseListener(this);
				  p2.add(p2_lab1,"0");
				  p2.add(p2_lab2,"1");
				  cardP2.show(p2, "0");
				  jp1.add(p2,"North");
			}else{
				ArrayList<Course> courses=message.getCourses();
				ArrayList<String>attriOfCourse=message.getAttri();
				tm1=new CourseTableModel(courses,attriOfCourse);
				jt1.setModel(tm1);
				jsp1.setVisible(true);
				p2.setVisible(true);
			}
			
			
		}else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		
	
	
	
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==this.p2_lab2){
			//把显示各种操作的界面隐藏起来
			//显示向下的箭头
			this.cardP2.show(p2, "0");
			this.jsp.setDividerLocation(0);
		}else if(arg0.getSource()==this.p2_lab1){
			//把显示各种操作的界面展开
			//显示向上的箭头
			this.cardP2.show(p2, "1");
			this.jsp.setDividerLocation(70);
		}else if(arg0.getSource()==jb1){
			refresh();
			
		}else if(arg0.getSource()==jb2){

			int row=this.jt1.getSelectedRow();
			if(row==-1){
				JOptionPane.showMessageDialog(this, "请选择一行！");
			}else {
				this.setcNum((String)tm1.getValueAt(row, 0));
				Message message=new Message();
				try {
					message=this.classTeaBLService.showCourseDetail(this.getcNum());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.cardP2.show(p2, "1");
				this.jsp.setDividerLocation(70);
				if(message.getMesType().equals(MessageType.tea_show_detailCourInfoFail)){
					this.setDetail("");
					this.jp3_jta.setText("");
				}else {
					String detail=message.getCour().getDetail();
					this.jp3_jta.setText(detail);
					this.setDetail(detail);
					
					
				}
				
				
				
			}
			this.jb3.setEnabled(false);

		} else if (arg0.getSource() == jb3) {
			this.setDetail(this.jp3_jta.getText());
			String detailString = this.getDetail();
			char[] details = detailString.toCharArray();
			if (details.length > 150) {
				JOptionPane.showMessageDialog(this, "字符过长，请确保字符长度不要超过150！");
				this.classTeaWindow.stateNow.setText("更新失败！");
			} else {
				Message message = new Message();

				try {
					message = this.classTeaBLService.inputCourseDetailInfo(
							this.getcNum(), this.getDetail());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message.getMesType().equals(
						MessageType.tea_inputDetailCInfo_success)) {
					this.classTeaWindow.stateNow.setText("更新成功！");
					ImageIcon im = new ImageIcon(
							"images\\ClassTea\\upInfoSuc.png");
					suc = new JDialog();
					ImagePanel imagePanel = new ImagePanel(im.getImage());
					suc.add(imagePanel);
					suc.setUndecorated(true);
					suc.setSize(150, 80);
					suc.setVisible(true);
					int width = Toolkit.getDefaultToolkit().getScreenSize().width;
					int height = Toolkit.getDefaultToolkit().getScreenSize().height;
					suc.setLocation((width - 150) / 2, (height - 80) / 2);

					UpdateInfoSuccess updateInfoSuccess = new UpdateInfoSuccess();
					updateInfoSuccess.start();
				} else {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点小问题！");
					this.classTeaWindow.stateNow.setText("更新失败！");
				}

				this.jb3.setEnabled(false);

			}

		}

	}

	private class UpdateInfoSuccess extends Thread {
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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (e.getDocument() == this.jp3_jta.getDocument()) {
			String temp = this.jp3_jta.getText();
			if (temp.equals(this.getDetail())) {
				this.jb3.setEnabled(false);
			} else {
				this.jb3.setEnabled(true);
			}
		}

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (e.getDocument() == this.jp3_jta.getDocument()) {
			String temp = this.jp3_jta.getText();
			if (temp.equals(this.getDetail())) {
				this.jb3.setEnabled(false);
			} else {
				this.jb3.setEnabled(true);
			}
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (e.getDocument() == this.jp3_jta.getDocument()) {
			String temp = this.jp3_jta.getText();
			if (temp.equals(this.getDetail())) {
				this.jb3.setEnabled(false);
			} else {
				this.jb3.setEnabled(true);
			}
		}

	}

}
