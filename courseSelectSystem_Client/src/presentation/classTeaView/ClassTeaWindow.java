package presentation.classTeaView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.UIManager;

import businesslogicservice.ClassTeaBLService;

import common.Message;

import tools.ImagePanel;
import tools.MyTools;

public class ClassTeaWindow extends JFrame implements ActionListener,MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//������Ҫ�����
		ImageIcon titleIcon,timeIcon,backIcon;
		
		//������
		JToolBar jtb;
		JButton jb1,jb2,jb3,jb4,jb5;
		
		JLabel jl1,jl2,jl3,jl4,jl5;
		
		    //������Ҫ��������
			JPanel p1,p2,p3,p4,p5;
			
			//��ʾ��ǰʱ��
			JLabel timeNow;
			
			//��ʾ��ǰ��״̬(�硱�����ɹ�����)
			JLabel stateNow;
			
			//Ϊ����ʱ��label�ı���panel
			ImagePanel ip1;
			
			//��p2��嶨����Ҫ��JLabel,������ͷ��1ָ���ң�2ָ����
			JLabel p2_lab1,p2_lab2;
			
			
			
			//��ʱ�䶯������javax.swing���е�Timer����Զ�ʱ�Ĵ���Action�¼�
			javax.swing.Timer t;
			
			JLabel p1_lab1,p1_lab2,p1_lab3,p1_lab4,p1_lab5,p1_lab6;
			ImagePanel p1_imgPanel;
			
			JSplitPane jsp;
			
			CardLayout cardP3;
			CardLayout cardP2;
			
			Message message;
			public void setMessage(Message message) {
				this.message = message;
			}
			public void setClassTeaBLService(ClassTeaBLService classTeaBLService) {
				this.classTeaBLService = classTeaBLService;
			}


			ClassTeaBLService classTeaBLService;


	@SuppressWarnings("deprecation")
	public ClassTeaWindow(ClassTeaBLService classTeaBLService,Message message){
		this.setClassTeaBLService(classTeaBLService);
		this.setMessage(message);
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	   
	    
	   
	    this.initialToolBar();
	   
	    this.initialAllPanels();
	    //����P5���(״̬�����)
	    p5=new JPanel(new BorderLayout());
	    //����Timer
	    t=new Timer(1000, this);//ÿ��һ�봥��ActionEvent
	    //������ʱ��
	    t.start();
	    
	    timeNow=new JLabel(Calendar.getInstance().getTime().toLocaleString()+"   ");
	    timeNow.setFont(MyTools.f2);
	    
	    stateNow =new JLabel("��״̬��");
	    stateNow.setFont(MyTools.f2);
	    
	    timeIcon=new ImageIcon("images\\ClassTea\\zhuangtailan.PNG");
	    ip1=new ImagePanel(timeIcon.getImage());
	    ip1.setLayout(new BorderLayout());
	    ip1.add(stateNow,"Center");
	    ip1.add(timeNow,"East");
	    
	    
	    
	    
	    p5.add(ip1);
	    
	    //��JFrame��ȡ��container
	    Container ct=this.getContentPane();
	    ct.add(jtb,"North");
	    ct.add(jsp,"Center");
	    ct.add(p5,"South");
	    
	    
		//ע�������ͼ��Ҫ������Ů��ѡ�񣡣�������������������������
	    if(this.message.getU().getSex().equals("��")){
	    	titleIcon=new ImageIcon("images\\ClassTea\\Mr.jpg");
	    }else {
	    	titleIcon=new ImageIcon("images\\ClassTea\\Miss.jpg");
		}
		
		//���ô�С
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//�رմ���ʱ�˳�ϵͳ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//���ô��ڵ�ͼ��
		this.setIconImage(titleIcon.getImage());
		this.setTitle("�Ͼ���ѧѡ��ϵͳ--�ον�ʦ");
		this.setSize(w, h-60);
		this.setVisible(true);
		
		
	}
	//��ʼ��������
	public void initialToolBar(){
			 //�������������
		    jtb=new JToolBar();
		    //���ò��ɸ���
		    jtb.setFloatable(false);
		    jb1=new JButton(new ImageIcon("images\\ClassTea\\MyCourse.png"));
		    jb1.addMouseListener(this);
		    jl1=new JLabel("�ҵĿγ�");
		    jl1.setFont(MyTools.f3);
		    jl1.setVisible(false);
		    jb2=new JButton(new ImageIcon("images\\ClassTea\\tianxie.png"));
		    jb2.addMouseListener(this);
		    jl2=new JLabel("��д�γ���Ϣ");
		    jl2.setFont(MyTools.f3);
		    jl2.setVisible(false);
		    jb3=new JButton(new ImageIcon("images\\ClassTea\\MyStu.png"));
		    jb3.addMouseListener(this);
		    jl3=new JLabel("�ҵ�ѧ��");
		    jl3.setFont(MyTools.f3);
		    jl3.setVisible(false);
		    jb4=new JButton(new ImageIcon("images\\ClassTea\\dengji.PNG"));
		    jb4.addMouseListener(this);
		    jl4=new JLabel("�Ǽǳɼ�");
		    jl4.setFont(MyTools.f3);
		    jl4.setVisible(false);
		    jb5=new JButton(new ImageIcon("images\\ClassTea\\xiugaimima.png"));
		    jb5.addMouseListener(this);
		    jl5=new JLabel("�޸�����");
		    jl5.setFont(MyTools.f3);
		    jl5.setVisible(false);
		   
		    
		    jtb.add(jb1);
		    jtb.add(jl1);
		    jtb.add(jb2);
		    jtb.add(jl2);
		    jtb.add(jb3);
		    jtb.add(jl3);
		    jtb.add(jb4);
		    jtb.add(jl4);
		    jtb.add(jb5);
		    jtb.add(jl5);
		   
	}
	
	public void initialAllPanels(){
		//p1������д󱳾������
		p1=new JPanel();
		p1.setLayout(new BorderLayout());
		backIcon=new ImageIcon("images\\ClassTea\\back.png");
		p1_imgPanel=new ImagePanel(backIcon.getImage());
		
		
		
		
		//���
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
				
	   
			    
		this.p1_imgPanel.setLayout(new GridLayout(6, 1));
		
		p1_lab1=new JLabel(new ImageIcon("images\\ClassTea\\tubiao.gif"));
		p1_imgPanel.add(p1_lab1);
		
		p1_lab2=new JLabel("�� �� �� ��",new ImageIcon("images\\ClassTea\\MyCourse.png"),0);
		p1_lab2.setFont(MyTools.f3);
		//���ù��
		p1_lab2.setCursor(myCursor);
		p1_lab2.setEnabled(false);
		 //ע�����
		p1_lab2.addMouseListener(this);
		p1_imgPanel.add(p1_lab2);
		
		p1_lab3=new JLabel("�γ���Ϣ��д",new ImageIcon("images\\ClassTea\\tianxie.png"),0);
		p1_lab3.setFont(MyTools.f3);
		//���ù��
		p1_lab3.setCursor(myCursor);
		p1_lab3.setEnabled(false);
		 //ע�����
		p1_lab3.addMouseListener(this);
		p1_imgPanel.add(p1_lab3);
		
		p1_lab4=new JLabel("�� �� ѧ ��",new ImageIcon("images\\ClassTea\\MyStu.png"),0);
		p1_lab4.setFont(MyTools.f3);
		//���ù��
		p1_lab4.setCursor(myCursor);
		p1_lab4.setEnabled(false);
		 //ע�����
		p1_lab4.addMouseListener(this);
		p1_imgPanel.add(p1_lab4);
		
		p1_lab5=new JLabel("�� �� �� ��",new ImageIcon("images\\ClassTea\\dengji.PNG"),0);
		p1_lab5.setFont(MyTools.f3);
		//���ù��
		p1_lab5.setCursor(myCursor);
		p1_lab5.setEnabled(false);
		 //ע�����
		p1_lab5.addMouseListener(this);
		p1_imgPanel.add(p1_lab5);
		
		p1_lab6=new JLabel("�� �� �� ��",new ImageIcon("images\\ClassTea\\xiugaimima.png"),0);
		p1_lab6.setFont(MyTools.f3);
		//���ù��
		p1_lab6.setCursor(myCursor);
		p1_lab6.setEnabled(false);
		 //ע�����
		p1_lab6.addMouseListener(this);
		p1_imgPanel.add(p1_lab6);
		
		
		
		p1.add(p1_imgPanel);
		
		//����p2,p3,p4
		 p4=new JPanel(new BorderLayout());
		    
		 cardP2=new CardLayout();
		    
		  //p2���ڴ�����ֲ�ͬ����ļ�ͷ
		  p2=new JPanel(this.cardP2);
		  p2_lab1=new JLabel(new ImageIcon("images\\ClassTea\\toright.png"));
		  p2_lab1.addMouseListener(this);
		  p2_lab2=new JLabel(new ImageIcon("images\\ClassTea\\toleft.png"));
		  p2_lab2.addMouseListener(this);
		  p2.add(p2_lab1,"0");
		  p2.add(p2_lab2,"1");
		  cardP2.show(p2, "1");
		  
		  this.cardP3=new CardLayout();
		    p3=new JPanel(cardP3);
		    
		    //�ȸ�p3����һ��������Ŀ�Ƭ,p3�������ݽ���
		   ImageIcon mainP3=new ImageIcon("images\\ClassTea\\p3.PNG");
		    ImagePanel ip=new ImagePanel(mainP3.getImage());
		    p3.add(ip,"0");
		    
		    ClassTeaXiuGaiMMPanel cxgmp=new ClassTeaXiuGaiMMPanel(classTeaBLService, message);
		    cxgmp.setClassTeaWindow(this);
		    p3.add(cxgmp,"1");
		    
		    MyCoursePanel mcp=new MyCoursePanel(this.classTeaBLService,this. message);
		    p3.add(mcp,"2");
		    
		    ModifyCourInfoPanel mcip=new ModifyCourInfoPanel(this.classTeaBLService, this.message);
		    mcip.setClassTeaWindow(this);
		    p3.add(mcip,"3");
		    
		    ShowMyStu smsp=new ShowMyStu(this.classTeaBLService,this. message);
		    p3.add(smsp,"4");
		    
		    RecordScorePanel rsp=new RecordScorePanel(this.classTeaBLService, this.message);
		    rsp.setClassTeaWindow(this);
		    p3.add(rsp,"5");
//		    //����EmpInfo����
//		    EmpInfo empInfo=new EmpInfo();
//		   
//		    p3.add(empInfo,"1");
//		    
//		    JLabel dlgl=new JLabel(new ImageIcon("image/degl.jpg"));
//		    p3.add(dlgl,"2");
//		    
//		    //����ʾ�����JPanel new ����
//		    Chart myChart=new Chart();
//		    p3.add(myChart,"4");
		    
		    
		    //��p2,p3����p4
		    p4.add(p2,"West");
		    p4.add(p3,"Center");
		    
		    //��һ����ִ��ڣ��ֱ���p1,p4
		    jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p1,p4);
		    //ָ����ߵ����ռ���ķ�Χ
		    jsp.setDividerLocation(130);
		    //�ѱ߽�����Ϊ0
		   jsp.setDividerSize(0);
		  
		
		
		
		
		
		
	}
		
		

	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ClassTeaWindow();
//
//	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==this.p2_lab2){
			//����ʾ���ֲ����Ľ�����������
			//��ʾ���ҵļ�ͷ
			this.cardP2.show(p2, "0");
			this.jsp.setDividerLocation(0);
		}else if(arg0.getSource()==this.p2_lab1){
			//����ʾ���ֲ����Ľ���չ��
			//��ʾ����ļ�ͷ
			this.cardP2.show(p2, "1");
			this.jsp.setDividerLocation(130);
		}else if(arg0.getSource()==this.jb5||arg0.getSource()==this.p1_lab6){
			this.cardP3.show(p3, "1");
			
			
		}else if(arg0.getSource()==jb1||arg0.getSource()==p1_lab2){
			this.cardP3.show(p3, "2");
		}else if(arg0.getSource()==jb2||arg0.getSource()==p1_lab3){
			this.cardP3.show(p3, "3");
		}else if(arg0.getSource()==jb3||arg0.getSource()==p1_lab4){
			this.cardP3.show(p3, "4");
		}else if(arg0.getSource()==jb4||arg0.getSource()==p1_lab5){
			this.cardP3.show(p3, "5");
		}
		
	}


	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//����û�ѡ����ĳ������JLabel�������
		if(arg0.getSource()==this.p1_lab2){
			this.p1_lab2.setEnabled(true);
		}else if(arg0.getSource()==this.p1_lab3){
			this.p1_lab3.setEnabled(true);
		}else if(arg0.getSource()==this.p1_lab4){
			this.p1_lab4.setEnabled(true);
		}else if(arg0.getSource()==this.p1_lab5){
			this.p1_lab5.setEnabled(true);
		}else if(arg0.getSource()==this.p1_lab6){
			this.p1_lab6.setEnabled(true);
		}else if(arg0.getSource()==jb1){
			jl1.setVisible(true);
		}else if(arg0.getSource()==jb2){
			jl2.setVisible(true);
			
		}else if(arg0.getSource()==jb3){
			jl3.setVisible(true);
		}else if(arg0.getSource()==jb4){
			jl4.setVisible(true);
		}else if(arg0.getSource()==jb5){
			jl5.setVisible(true);
		}
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//����û�ѡ����ĳ������JLabel�������
		if(arg0.getSource()==this.p1_lab2){
				this.p1_lab2.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab3){
			this.p1_lab3.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab4){
			this.p1_lab4.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab5){
			this.p1_lab5.setEnabled(false);
		}else if(arg0.getSource()==this.p1_lab6){
			this.p1_lab6.setEnabled(false);
		}else if(arg0.getSource()==this.jb1){
			jl1.setVisible(false);
			
		}else if(arg0.getSource()==this.jb2){
			jl2.setVisible(false);
		}else if(arg0.getSource()==this.jb3){
			jl3.setVisible(false);
		}else if(arg0.getSource()==this.jb4){
			jl4.setVisible(false);
		}else if(arg0.getSource()==this.jb5){
			jl5.setVisible(false);
		}
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.timeNow.setText("��ǰʱ�䣺 "+Calendar.getInstance().getTime().toLocaleString()+"  ");
	}

}
