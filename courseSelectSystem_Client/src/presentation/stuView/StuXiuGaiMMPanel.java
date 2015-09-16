package presentation.stuView;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import tools.ImagePanel;
import tools.MyTools;
import businesslogicservice.StudentBLService;

import common.Message;
import common.MessageType;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StuXiuGaiMMPanel extends JPanel implements ActionListener, DocumentListener{
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField_Original;
	private JPasswordField passwordField_New;
	private JPasswordField passwordField_Comfire;
	private JLabel lbl_OriginalState;
	private JLabel lbl_NewState;
	private JLabel lbl_ComfireState;
	private JButton button_Change ;
	private boolean state_Original; // 控制原密码JPasswordField状态
	private boolean state_New; // 控制新密码JPasswordField状态
	private boolean state_Comfire; // 控制确认密码JPasswordField状态
	JDialog suc;
	
	private Message message;
	private StudentBLService studentBLService;
	private StuWindow stuWindow;
	
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
	/**
	 * Create the panel.
	 */
	public StuXiuGaiMMPanel(StudentBLService studentBLService,Message message) {
		state_Original = false;
		state_New = false;
		state_Comfire = false;
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_ChangePassword = new JPanel();
		panel_ChangePassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_ChangePassword, BorderLayout.CENTER);
		panel_ChangePassword.setLayout(new GridLayout(0, 4, 60, 108));
		
		JLabel lbl1 = new JLabel("");
		panel_ChangePassword.add(lbl1);
		
		JLabel lbl_OriginalPassword = new JLabel("原密码");
		lbl_OriginalPassword.setFont(MyTools.f5);
		lbl_OriginalPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ChangePassword.add(lbl_OriginalPassword);
		
		passwordField_Original = new JPasswordField();
		passwordField_Original.getDocument().addDocumentListener(this);
		panel_ChangePassword.add(passwordField_Original);
		
		lbl_OriginalState = new JLabel("原密码错误！");
		lbl_OriginalState.setFont(MyTools.f1);
		lbl_OriginalState.setForeground(Color.red);
		lbl_OriginalState.setVisible(false);
		panel_ChangePassword.add(lbl_OriginalState);
		
		JLabel lbl3 = new JLabel("");
		panel_ChangePassword.add(lbl3);
		
		JLabel lbl_NewPassword = new JLabel("新密码");
		lbl_NewPassword.setFont(MyTools.f5);
		lbl_NewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ChangePassword.add(lbl_NewPassword);
		
		passwordField_New = new JPasswordField();
		passwordField_New.getDocument().addDocumentListener(this);
		panel_ChangePassword.add(passwordField_New);
		
		lbl_NewState = new JLabel("密码不能为空！");
		lbl_NewState.setFont(MyTools.f1);
		lbl_NewState.setForeground(Color.red);
		lbl_NewState.setVisible(false);
		panel_ChangePassword.add(lbl_NewState);
		
		JLabel lbl5 = new JLabel("");
		panel_ChangePassword.add(lbl5);
		
		JLabel lbl_ComfirePassword = new JLabel("确认密码");
		lbl_ComfirePassword.setFont(MyTools.f5);
		lbl_ComfirePassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ChangePassword.add(lbl_ComfirePassword);
		
		passwordField_Comfire = new JPasswordField();
		passwordField_Comfire.getDocument().addDocumentListener(this);
		panel_ChangePassword.add(passwordField_Comfire);
		
		lbl_ComfireState = new JLabel("两次输入的密码不符！");
		lbl_ComfireState.setFont(MyTools.f1);
		lbl_ComfireState.setForeground(Color.red);
		lbl_ComfireState.setVisible(false);
		panel_ChangePassword.add(lbl_ComfireState);
		
		JLabel lbl7 = new JLabel(""); // 控制界面比例
		panel_ChangePassword.add(lbl7);
		
		JPanel panel_Button = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_Button.getLayout();
		flowLayout_3.setVgap(12);
		flowLayout_3.setHgap(100);
		add(panel_Button, BorderLayout.SOUTH);
		
		button_Change = new JButton("修改");
		button_Change.setFont(MyTools.f1);
		button_Change.addActionListener(this);
		button_Change.setEnabled(false);
		panel_Button.add(button_Change);
		
		JButton button_Cancel = new JButton("取消");
		button_Cancel.setFont(MyTools.f1);
		button_Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				state_Original = false;
				state_New = false;
				state_Comfire = false;
				passwordField_Original.setText("");
				passwordField_New.setText("");
				passwordField_Comfire.setText("");
			}
		});
		panel_Button.add(button_Cancel);
		
		// 添加东西北面板，控制界面比例
		JPanel panel_East = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_East.getLayout();
		flowLayout_1.setHgap(8);
		add(panel_East, BorderLayout.WEST);
		JPanel panel_North = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_North.getLayout();
		flowLayout_2.setVgap(8);
		add(panel_North, BorderLayout.NORTH);
		JPanel panel_West = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_West.getLayout();
		flowLayout.setHgap(8);
		add(panel_West, BorderLayout.EAST);
	} // end function constructor 
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Message message_ChangePassword = new Message();
		String deanTeaID = message.getU().getNum();
		String password = new String(this.passwordField_New.getPassword());
		char[]passc1=password.toCharArray();
		password.trim();
		char[]passc2=password.toCharArray();
		if(passc1.length!=passc2.length){
			JOptionPane.showMessageDialog(stuWindow, "密码中存在空格，无法修改！");
			this.stuWindow.stateNow.setText("修改失败！");
		}else if(passc1.length>100){
			JOptionPane.showMessageDialog(stuWindow, "密码长度过长，无法修改！");
			this.stuWindow.stateNow.setText("修改失败！");
		}else {
			try {
				message_ChangePassword = this.studentBLService.ChangePasswd(deanTeaID, password);
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
			if (message_ChangePassword.getMesType().
					equals(MessageType.stu_changePasswd_success)) { 
				this.message.getU().setPasswd(password);
				this.stuWindow.stateNow.setText("修改成功！");
				ImageIcon image = new ImageIcon("images\\chgeSuc.png");
				suc=new JDialog();
				ImagePanel imagePanel=new ImagePanel(image.getImage());
				suc.add(imagePanel);
				suc.setUndecorated(true);
				suc.setSize(150,80);
				suc.setVisible(true);
				int width= Toolkit.getDefaultToolkit().getScreenSize().width;
				int height=Toolkit.getDefaultToolkit().getScreenSize().height;
				suc.setLocation((width-150)/2,(height-80)/2);
				
				chgePasswdSuccess chgeSuc=new chgePasswdSuccess();
				chgeSuc.start();
				passwordField_Original.setText("");
				passwordField_New.setText("");
				passwordField_Comfire.setText("");
				state_Original = false;
				state_New = false;
				state_Comfire = false;
			} else {
				JOptionPane.showMessageDialog(stuWindow, "你的网络貌似有点问题！");
				this.stuWindow.stateNow.setText("修改失败！");
			}
		}
		
	} // end actionPerformed method

	@Override
	public void changedUpdate(DocumentEvent event) {
		// TODO Auto-generated method stub
		if (event.getDocument() == passwordField_Original.getDocument()) {
			String password = new String(passwordField_Original.getPassword());
			if (password.equals(this.message.getU().getPasswd())) {
				state_Original = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(false);
			} else {
				state_Original = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(true);
			} 
			// end if inside
		}  else if(event.getDocument() == passwordField_New.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			if (password.equals("")) {
				state_New = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(true);
			} else {
				state_New = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(false);
			}
			
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
			// end if inside
		} else if (event.getDocument() == passwordField_Comfire.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent event) {
		// TODO Auto-generated method stub
		if (event.getDocument() == passwordField_Original.getDocument()) {
			String password = new String(passwordField_Original.getPassword());
			if (password.equals(this.message.getU().getPasswd())) {
				state_Original = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(false);
			} else {
				state_Original = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(true);
			} 
			// end if inside
		}  else if(event.getDocument() == passwordField_New.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			if (password.equals("")) {
				state_New = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(true);
			} else {
				state_New = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(false);
			}
			
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
			// end if inside
		} else if (event.getDocument() == passwordField_Comfire.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		// TODO Auto-generated method stub
		if (event.getDocument() == passwordField_Original.getDocument()) {
			String password = new String(passwordField_Original.getPassword());
			if (password.equals(this.message.getU().getPasswd())) {
				state_Original = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(false);
			} else {
				state_Original = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_OriginalState.setVisible(true);
			} 
			// end if inside
		}  else if(event.getDocument() == passwordField_New.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			if (password.equals("")) {
				state_New = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(true);
			} else {
				state_New = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_NewState.setVisible(false);
			}
			
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
			// end if inside
		} else if (event.getDocument() == passwordField_Comfire.getDocument()) {
			String password = new String(passwordField_New.getPassword());
			String password2 = new String(passwordField_Comfire.getPassword());
			if (password.equals(password2)) {
				state_Comfire = true;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(false);
			} else {
				state_Comfire = false;
				button_Change.setEnabled(state_Original && state_New && state_Comfire);
				lbl_ComfireState.setVisible(true);
			}
		}
	}
	
	private class chgePasswdSuccess extends Thread {
		public synchronized void run() {
			try {
				Thread.sleep(1500);
				suc.dispose();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	} // end chgePasswdSuccess inside class

}
