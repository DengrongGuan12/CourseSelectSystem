package presentation;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import presentation.classTeaView.ClassTeaWindow;
import presentation.deanTeaView.DeanTeaWindow;
import presentation.stuView.StuWindow;
import presentation.trainingTeaView.TrainingTeaWindow;

import common.Message;
import common.MessageType;

import businesslogicservice.ClassTeaBLService;
import businesslogicservice.DeanTeaBLService;
import businesslogicservice.StudentBLService;
import businesslogicservice.TrainingTeaBLService;

import rmi.RMIClient;
import tools.ImagePanel;
import tools.MyTools;

@SuppressWarnings("serial")
public class UserLogin extends JDialog implements ActionListener {
	// 定义需要的组件
	JLabel jl;
	JLabel jl1, jl2, jl3;
	JComboBox<String> jcb;
	JTextField jName;
	JPasswordField jPasswd;
	JButton jb1, jb2;
	JCheckBox jc;
	JPanel p1;
	ImagePanel ip;

	public static void main(String[] args) {
		new UserLogin();

	}

	public UserLogin() {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// this.setLayout(null);
		Container ct = this.getContentPane();
		p1 = new JPanel(new BorderLayout());
		ImageIcon im = new ImageIcon("images//Login.gif");
		ip = new ImagePanel(im.getImage());
		ip.setLayout(null);
		// 创建各个组件
		jl = new JLabel("南京大学选课系统");
		jl.setFont(MyTools.f8);
		jl.setForeground(Color.white);
		jl.setBounds(90, 60, 200, 20);
		ip.add(jl);

		jl1 = new JLabel("用户类型");
		jl1.setForeground(Color.white);
		jl1.setFont(MyTools.f1);
		jl1.setBounds(50, 144, 100, 30);
		ip.add(jl1);

		jcb = new JComboBox<String>();
		jcb.addItem("教务处老师");
		jcb.addItem("院系教务老师");
		jcb.addItem("任课教师");
		jcb.addItem("学生");
		jcb.setSelectedItem("教务处老师");
		jcb.setBounds(152, 144, 150, 30);
		ip.add(jcb);

		jl2 = new JLabel("用户名");
		jl2.setFont(MyTools.f1);
		jl2.setForeground(Color.white);
		jl2.setBounds(70, 206, 150, 30);
		ip.add(jl2);

		jName = new JTextField(20);

		jName.setBounds(152, 206, 150, 30);
		// 设置下凹
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		// 放入
		ip.add(jName);

		jl3 = new JLabel("密码");
		jl3.setFont(MyTools.f1);
		jl3.setForeground(Color.white);
		jl3.setBounds(60, 265, 150, 30);
		ip.add(jl3);

		// //密码框
		jPasswd = new JPasswordField(20);
		jPasswd.setBounds(152, 265, 150, 30);

		// 设置下凹
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ip.add(jPasswd);

		jc = new JCheckBox("Remember me");
		jc.setFont(MyTools.f3);
		jc.setForeground(Color.white);
		jc.setBounds(200, 300, 150, 20);
		ip.add(jc);

		// 加入取消按钮
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		jb2.setFont(MyTools.f1);
		jb2.setBounds(220, 330, 70, 30);
		ip.add(jb2);

		jb1 = new JButton("登录");
		jb1.addActionListener(this);
		jb1.setFont(MyTools.f1);
		jb1.setBounds(90, 330, 70, 30);
		// 放入到容器
		ip.add(jb1);
		p1.add(ip);
		ct.add(p1);

		this.setUndecorated(true);
		this.setSize(360, 400);

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 360) / 2, (height - 400) / 2);
		this.setVisible(true);

	}

	// 响应用户登录的请求
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// 判断是否点击确定按钮
		if (arg0.getSource() == jb1) {
			String userType = this.jcb.getSelectedItem().toString();
			String u = this.jName.getText().trim();
			String p = new String(this.jPasswd.getPassword());
			if (userType.equals("教务处老师")) {

				TrainingTeaBLService trainingTeaBLService = null;
				Message message = new Message();
				try {
					RMIClient rmiClient = new RMIClient();
					trainingTeaBLService = rmiClient.getTrainingTeaBLService();
					message = trainingTeaBLService.checkUserLog(u, p);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message == null) {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
				} else {
					if (message.getMesType().equals(
							MessageType.traTea_log_success)) {
						JOptionPane.showMessageDialog(this, "        登录成功！"
								+ "\n" + "欢迎你，" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						TrainingTeaWindow trainingTeaWindow = new TrainingTeaWindow(
								trainingTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.traTea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"用户名或密码错误，请检查输入是否正确！");
					}
				}

			} else if (userType.equals("院系教务老师")) {

				DeanTeaBLService deanTeaBLService = null;
				Message message = new Message();
				try {
					RMIClient rmiClient = new RMIClient();
					deanTeaBLService = rmiClient.getDeanTeaBLService();
					message = deanTeaBLService.checkUserLog(u, p);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message == null) {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
				} else {
					if (message.getMesType().equals(
							MessageType.deanTea_log_success)) {
						JOptionPane.showMessageDialog(this, "        登录成功！"
								+ "\n" + "欢迎你，" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						DeanTeaWindow deanTeaWindow = new DeanTeaWindow(
								deanTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.deanTea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"用户名或密码错误，请检查输入是否正确！");
					}
				}

			} else if (userType.equals("任课教师")) {
				ClassTeaBLService classTeaBLService = null;
				Message message = new Message();
				try {
					RMIClient rmiClient = new RMIClient();
					classTeaBLService = rmiClient.getClassTeaBLService();
					message = classTeaBLService.checkUserLog(u, p);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message == null) {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
				} else {
					if (message.getMesType()
							.equals(MessageType.tea_log_success)) {
						JOptionPane.showMessageDialog(this, "        登录成功！"
								+ "\n" + "欢迎你，" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						ClassTeaWindow classTeaWindow = new ClassTeaWindow(
								classTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.tea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"用户名或密码错误，请检查输入是否正确！");
					}
				}
			} else {
				StudentBLService studentBLService = null;
				Message message = new Message();
				try {
					RMIClient rmiClient = new RMIClient();
					studentBLService = rmiClient.getStudentBLService();
					message = studentBLService.checkUserLog(u, p);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();

				}
				if (message == null) {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
				} else {
					if (message.getMesType()
							.equals(MessageType.stu_log_success)) {
						JOptionPane.showMessageDialog(this, "        登录成功！"
								+ "\n" + "欢迎你，" + message.getU().getName());
						this.dispose();
						 @SuppressWarnings("unused")
						StuWindow stuWindow = new StuWindow(studentBLService,
									message);
					} else if (message.getMesType().equals(
							MessageType.stu_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"用户名或密码错误，请检查输入是否正确！");
					}
				}
			}

		} else if (arg0.getSource() == this.jb2) {
			this.dispose();
			System.exit(0);
		}

	}

}
