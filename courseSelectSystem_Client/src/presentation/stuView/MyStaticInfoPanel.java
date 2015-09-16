package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



import tools.ImagePanel;
import tools.MyTools;



import businesslogicservice.StudentBLService;

import common.Message;
import common.MessageType;
import common.Student;

public class MyStaticInfoPanel extends JPanel implements ActionListener,DocumentListener{

	private static final long serialVersionUID = 1L;
	JPanel jp1;//����label��panel
	JPanel jp2;//����Button��panel
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13;
	JLabel jl14,jl15;
	JTextField jt1,jt2,jt3,jt4,jt5;
	JRadioButton jr1,jr2;
	ButtonGroup bg;
	JButton jb1,jb2;
	Message message;
	StudentBLService studentBLService;
	Boolean b1,b2,b3,b4,b5;//�ж����޸Ķ�;
	Boolean b11,b22,b33,b44,b55;//�ж��Ƿ���ϸ�ʽ
	boolean b6;//��¼��ѡ��ť�ĳ�ʼ״̬true��ʾ�У�false��ʾŮ
	boolean b66;//�жϰ�ť״̬�Ƿ񱻸ı�
	boolean b7;//��¼��ǰ��ť��״̬
	Student s;
	JDialog suc;
	StuWindow stuWindow;
	public void setStuWindow(StuWindow stuWindow) {
		this.stuWindow = stuWindow;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setStudentBLService(StudentBLService studentBLService) {
		this.studentBLService = studentBLService;
	}

	public MyStaticInfoPanel(Message message,StudentBLService studentBLService){
		this.setMessage(message);
		this.setStudentBLService(studentBLService);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		s=(Student)this.message.getU();
		jp1=new JPanel(new GridLayout(7, 3, 12, 36));
		jl1=new JLabel("����",JLabel.CENTER);
		jl1.setFont(MyTools.f5);
		
		jt1=new JTextField();
		jt1.setText(s.getName());
		jt1.setEditable(false);
//		jt1.setBorder(BorderFactory.createLoweredBevelBorder());
		jt1.getDocument().addDocumentListener(this);
		
		jl8=new JLabel("��������Ϊ�գ�");
		jl8.setFont(MyTools.f2);
		b1=false;
		b11=true;
		jl8.setForeground(Color.red);
		jl8.setVisible(false);
		
		jl2=new JLabel("�Ա�",JLabel.CENTER);
		jl2.setFont(MyTools.f5);
		
		//����ͼƬ
		//URL imageUrl = DataUtil.getImgPath(getClass(),"database.png");  //�õ�ͼƬ��·��  
		bg=new ButtonGroup();
		@SuppressWarnings("unused")
		ImageIcon im1=new ImageIcon("images\\Stu\\boy.png");
		jr1=new JRadioButton("��");
		jr1.addActionListener(this);
		jr1.setEnabled(false);
		@SuppressWarnings("unused")
		ImageIcon im2=new ImageIcon("images\\Stu\\girl.png");
		jr2=new JRadioButton("Ů");
		jr2.addActionListener(this);
		jr2.setEnabled(false);
		bg.add(jr1);
		bg.add(jr2);
		String sex=s.getSex();
		if(sex.equals("��")){
			jr1.setSelected(true);
			b6=true;
		}else {
			jr2.setSelected(true);
			b6=false;
		}
		b66=false;
		
		jl14=new JLabel(" ");
		
		jl3=new JLabel("��ѧ���",JLabel.CENTER);
		jl3.setFont(MyTools.f5);
		
		String year=s.getYear();
		
		jl9=new JLabel(year);
		jl9.setFont(MyTools.f1);
		jl15=new JLabel(" ");
		
		jl4=new JLabel("��ͥסַ",JLabel.CENTER);
		jl4.setFont(MyTools.f5);
		jt2=new JTextField();
//		jt2.setBorder(BorderFactory.createLoweredBevelBorder());
		jt2.setText(s.getAddress());
		jt2.setEditable(false);
		b2=false;
		b22=true;
		jt2.getDocument().addDocumentListener(this);
		
		jl10=new JLabel("����Ϊ�գ�");
		jl10.setFont(MyTools.f2);
		jl10.setForeground(Color.red);
		jl10.setVisible(false);
		
		jl5=new JLabel("��ͥ�绰",JLabel.CENTER);
		jl5.setFont(MyTools.f5);
		jt3=new JTextField();
//		jt3.setBorder(BorderFactory.createLoweredBevelBorder());
		jt3.setText(s.getHomeNumber());//����Ϊ����
		jt3.setEditable(false);
		b3=false;
		b33=true;
		jt3.getDocument().addDocumentListener(this);
		
		jl11=new JLabel("����Ϊ���֣�");
		jl11.setFont(MyTools.f2);
		jl11.setForeground(Color.red);
		jl11.setVisible(false);
		
		jl6=new JLabel("�ֻ�����",JLabel.CENTER);
		jl6.setFont(MyTools.f5);
		jt4=new JTextField();
//		jt4.setBorder(BorderFactory.createLoweredBevelBorder());
		jt4.setText(s.getPhoneNumber());
		jt4.setEditable(false);
		b4=false;
		b44=true;
		jt4.getDocument().addDocumentListener(this);
		
		jl12=new JLabel("����Ϊ���֣�");
		jl12.setFont(MyTools.f2);
		jl12.setForeground(Color.red);
		jl12.setVisible(false);
		
		jl7=new JLabel("��������",JLabel.CENTER);
		jl7.setFont(MyTools.f5);
		jt5=new JTextField();
//		jt5.setBorder(BorderFactory.createLoweredBevelBorder());
		jt5.setText(s.getEmail());
		jt5.setEditable(false);
		b5=false;
		b55=true;
		jt5.getDocument().addDocumentListener(this);
		
		jl13=new JLabel("����Ϊ�գ�");
		jl13.setFont(MyTools.f2);
		jl13.setForeground(Color.red);
		jl13.setVisible(false);
		
		jp1.add(jl1);
		jp1.add(jt1);
		jp1.add(jl8);
		jp1.add(jl2);
		jp1.add(jr1);
		jp1.add(jr2);
		jp1.add(jl3);
		jp1.add(jl9);
		jp1.add(jl15);
		jp1.add(jl4);
		jp1.add(jt2);
		jp1.add(jl10);
		jp1.add(jl5);
		jp1.add(jt3);
		jp1.add(jl11);
		jp1.add(jl6);
		jp1.add(jt4);
		jp1.add(jl12);
		jp1.add(jl7);
		jp1.add(jt5);
		jp1.add(jl13);
		
		jp2=new JPanel(new FlowLayout());
		jb1=new JButton("��Ϣ����");
		jb1.setFont(MyTools.f1);
		jb1.addActionListener(this);
		jb2=new JButton("ȷ��");
		jb2.setFont(MyTools.f1);
		jb2.addActionListener(this);
		jb2.setEnabled(false);
		jp2.add(jb1);
		jp2.add(jb2);
		
		this.add(jp1,"Center");
		this.add(jp2,"South");
		
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			jb1.setEnabled(false);
			jt1.setEditable(true);
			jr1.setEnabled(true);
			jr2.setEnabled(true);
			jt2.setEditable(true);
			jt3.setEditable(true);
			jt4.setEditable(true);
			jt5.setEditable(true);
			
		}else if(e.getSource()==jb2){
			jb2.setEnabled(false);
			jt1.setEditable(false);
			jr1.setEnabled(false);
			jr2.setEnabled(false);
			jt2.setEditable(false);
			jt3.setEditable(false);
			jt4.setEditable(false);
			jt5.setEditable(false);
			s.setName(jt1.getText());
			if(b7){
				s.setSex("��");
			}else {
				s.setSex("Ů");
			}
			
			s.setAddress(jt2.getText());
			s.setHomeNumber(jt3.getText());
			s.setPhoneNumber(jt4.getText());
			s.setEmail(jt5.getText());
		
			//�����߼��㷽��
			Message message=new Message();
			try {
				message=this.studentBLService.changeMyInfo(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			if(message.getMesType().equals(MessageType.stu_changeMyInfo_suc)){
				this.stuWindow.stateNow.setText("�޸ĳɹ���");
				ImageIcon im=new ImageIcon("images\\chgeSuc.png");
				suc=new JDialog();
				ImagePanel imagePanel=new ImagePanel(im.getImage());
				suc.add(imagePanel);
				suc.setUndecorated(true);
				suc.setSize(150,80);
				suc.setVisible(true);
				int width= Toolkit.getDefaultToolkit().getScreenSize().width;
				int height=Toolkit.getDefaultToolkit().getScreenSize().height;
				suc.setLocation((width-150)/2,(height-80)/2);
				
				chgeInfoSuccess chgeSuc=new chgeInfoSuccess();
				chgeSuc.start();
			}else {
				this.stuWindow.stateNow.setText("�޸�ʧ�ܣ�");
				JOptionPane.showMessageDialog(this, "�������ò���е�С���⣡");
			}
			
			jb1.setEnabled(true);
			
		}else if(e.getSource()==jr1){
			if(b6){
				b66=false;
			}else {
				b66=true;
			}
			b7=true;
			if((b1||b2||b3||b4||b5||b66)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else{
				jb2.setEnabled(false);
			}
		}else if(e.getSource()==jr2){
			if(!b6){
				b66=false;
			}else {
				b66=true;
			}
			b7=false;
			if((b1||b2||b3||b4||b5||b66)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else{
				jb2.setEnabled(false);
			}
		}
			
		
	}

	private class chgeInfoSuccess extends Thread{
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
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getDocument()==jt1.getDocument()){
			if(!jt1.getText().equals(s.getName())){
				b1=true;
			}else {
				b1=false;
			}
			if(jt1.getText().equals("")){
				jl8.setVisible(true);
				b11=false;
				
			}else {
				jl8.setVisible(false);
				b11=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else{
				jb2.setEnabled(false);
			}
				
		}else if(arg0.getDocument()==jt2.getDocument()){
			if(!jt2.getText().equals(s.getAddress())){
				b2=true;
			}else {
				b2=false;
			}
			if(jt2.getText().equals("")){
				jl10.setVisible(true);
				b22=false;
				
			}else {
				jl10.setVisible(false);
				b22=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt3.getDocument()){
			if(!jt3.getText().equals(s.getHomeNumber())){
				b3=true;
			}else {
				b3=false;
			}
			if(jt3.getText().equals("")){
				b33=true;
				jl11.setVisible(false);
				
			}else{
				char[] c=jt3.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b33=true;
					jl11.setVisible(false);
				}else{
					b33=false;
					jl11.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt4.getDocument()){

			if(!jt4.getText().equals(s.getPhoneNumber())){
				b4=true;
			}else {
				b4=false;
			}
			if(jt4.getText().equals("")){
				b44=true;
				jl12.setVisible(false);
				
			}else{
				char[] c=jt4.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b44=true;
					jl12.setVisible(false);
				}else{
					b44=false;
					jl12.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}else if(arg0.getDocument()==jt5.getDocument()){

			if(!jt5.getText().equals(s.getEmail())){
				b5=true;
			}else {
				b5=false;
			}
			if(jt5.getText().equals("")){
				jl13.setVisible(true);
				b55=false;
				
			}else {
				jl13.setVisible(false);
				b55=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if(arg0.getDocument()==jt1.getDocument()){
			if(!jt1.getText().equals(s.getName())){
				b1=true;
			}else {
				b1=false;
			}
			if(jt1.getText().equals("")){
				jl8.setVisible(true);
				b11=false;
				
			}else {
				jl8.setVisible(false);
				b11=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else{
				jb2.setEnabled(false);
			}
				
		}else if(arg0.getDocument()==jt2.getDocument()){
			if(!jt2.getText().equals(s.getAddress())){
				b2=true;
			}else {
				b2=false;
			}
			if(jt2.getText().equals("")){
				jl10.setVisible(true);
				b22=false;
				
			}else {
				jl10.setVisible(false);
				b22=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt3.getDocument()){
			if(!jt3.getText().equals(s.getHomeNumber())){
				b3=true;
			}else {
				b3=false;
			}
			if(jt3.getText().equals("")){
				b33=true;
				jl11.setVisible(false);
				
			}else{
				char[] c=jt3.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b33=true;
					jl11.setVisible(false);
				}else{
					b33=false;
					jl11.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt4.getDocument()){

			if(!jt4.getText().equals(s.getPhoneNumber())){
				b4=true;
			}else {
				b4=false;
			}
			if(jt4.getText().equals("")){
				b44=true;
				jl12.setVisible(false);
				
			}else{
				char[] c=jt4.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b44=true;
					jl12.setVisible(false);
				}else{
					b44=false;
					jl12.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}else if(arg0.getDocument()==jt5.getDocument()){

			if(!jt5.getText().equals(s.getEmail())){
				b5=true;
			}else {
				b5=false;
			}
			if(jt5.getText().equals("")){
				jl13.setVisible(true);
				b55=false;
				
			}else {
				jl13.setVisible(false);
				b55=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}
		
	
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if(arg0.getDocument()==jt1.getDocument()){
			if(!jt1.getText().equals(s.getName())){
				b1=true;
			}else {
				b1=false;
			}
			if(jt1.getText().equals("")){
				jl8.setVisible(true);
				b11=false;
				
			}else {
				jl8.setVisible(false);
				b11=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else{
				jb2.setEnabled(false);
			}
				
		}else if(arg0.getDocument()==jt2.getDocument()){
			if(!jt2.getText().equals(s.getAddress())){
				b2=true;
			}else {
				b2=false;
			}
			if(jt2.getText().equals("")){
				jl10.setVisible(true);
				b22=false;
				
			}else {
				jl10.setVisible(false);
				b22=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt3.getDocument()){
			if(!jt3.getText().equals(s.getHomeNumber())){
				b3=true;
			}else {
				b3=false;
			}
			if(jt3.getText().equals("")){
				b33=true;
				jl11.setVisible(false);
				
			}else{
				char[] c=jt3.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b33=true;
					jl11.setVisible(false);
				}else{
					b33=false;
					jl11.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		}else if(arg0.getDocument()==jt4.getDocument()){

			if(!jt4.getText().equals(s.getPhoneNumber())){
				b4=true;
			}else {
				b4=false;
			}
			if(jt4.getText().equals("")){
				b44=true;
				jl12.setVisible(false);
				
			}else{
				char[] c=jt4.getText().toCharArray();
				int n=c.length;
				boolean b=true;
				for(int i=0;i<n;i++){
					if(c[i]>='0'&&c[i]<='9'){
						
					}else {
						b=false;
						break;
					}
				}
				if(b){
					b44=true;
					jl12.setVisible(false);
				}else{
					b44=false;
					jl12.setVisible(true);
							
				}
					
			}
				
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}else if(arg0.getDocument()==jt5.getDocument()){

			if(!jt5.getText().equals(s.getEmail())){
				b5=true;
			}else {
				b5=false;
			}
			if(jt5.getText().equals("")){
				jl13.setVisible(true);
				b55=false;
				
			}else {
				jl13.setVisible(false);
				b55=true;
			}
			
			if((b1||b2||b3||b4||b5)&&b11&&b22&&b33&&b44&&b55){
				jb2.setEnabled(true);
			}else {
				jb2.setEnabled(false);
			}
		
		}
		
	
		
	}

}
