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
	// ������Ҫ�����
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
		// �����������
		jl = new JLabel("�Ͼ���ѧѡ��ϵͳ");
		jl.setFont(MyTools.f8);
		jl.setForeground(Color.white);
		jl.setBounds(90, 60, 200, 20);
		ip.add(jl);

		jl1 = new JLabel("�û�����");
		jl1.setForeground(Color.white);
		jl1.setFont(MyTools.f1);
		jl1.setBounds(50, 144, 100, 30);
		ip.add(jl1);

		jcb = new JComboBox<String>();
		jcb.addItem("������ʦ");
		jcb.addItem("Ժϵ������ʦ");
		jcb.addItem("�ον�ʦ");
		jcb.addItem("ѧ��");
		jcb.setSelectedItem("������ʦ");
		jcb.setBounds(152, 144, 150, 30);
		ip.add(jcb);

		jl2 = new JLabel("�û���");
		jl2.setFont(MyTools.f1);
		jl2.setForeground(Color.white);
		jl2.setBounds(70, 206, 150, 30);
		ip.add(jl2);

		jName = new JTextField(20);

		jName.setBounds(152, 206, 150, 30);
		// �����°�
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		// ����
		ip.add(jName);

		jl3 = new JLabel("����");
		jl3.setFont(MyTools.f1);
		jl3.setForeground(Color.white);
		jl3.setBounds(60, 265, 150, 30);
		ip.add(jl3);

		// //�����
		jPasswd = new JPasswordField(20);
		jPasswd.setBounds(152, 265, 150, 30);

		// �����°�
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ip.add(jPasswd);

		jc = new JCheckBox("Remember me");
		jc.setFont(MyTools.f3);
		jc.setForeground(Color.white);
		jc.setBounds(200, 300, 150, 20);
		ip.add(jc);

		// ����ȡ����ť
		jb2 = new JButton("ȡ��");
		jb2.addActionListener(this);
		jb2.setFont(MyTools.f1);
		jb2.setBounds(220, 330, 70, 30);
		ip.add(jb2);

		jb1 = new JButton("��¼");
		jb1.addActionListener(this);
		jb1.setFont(MyTools.f1);
		jb1.setBounds(90, 330, 70, 30);
		// ���뵽����
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

	// ��Ӧ�û���¼������
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// �ж��Ƿ���ȷ����ť
		if (arg0.getSource() == jb1) {
			String userType = this.jcb.getSelectedItem().toString();
			String u = this.jName.getText().trim();
			String p = new String(this.jPasswd.getPassword());
			if (userType.equals("������ʦ")) {

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
					JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
				} else {
					if (message.getMesType().equals(
							MessageType.traTea_log_success)) {
						JOptionPane.showMessageDialog(this, "        ��¼�ɹ���"
								+ "\n" + "��ӭ�㣬" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						TrainingTeaWindow trainingTeaWindow = new TrainingTeaWindow(
								trainingTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.traTea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"�û���������������������Ƿ���ȷ��");
					}
				}

			} else if (userType.equals("Ժϵ������ʦ")) {

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
					JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
				} else {
					if (message.getMesType().equals(
							MessageType.deanTea_log_success)) {
						JOptionPane.showMessageDialog(this, "        ��¼�ɹ���"
								+ "\n" + "��ӭ�㣬" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						DeanTeaWindow deanTeaWindow = new DeanTeaWindow(
								deanTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.deanTea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"�û���������������������Ƿ���ȷ��");
					}
				}

			} else if (userType.equals("�ον�ʦ")) {
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
					JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
				} else {
					if (message.getMesType()
							.equals(MessageType.tea_log_success)) {
						JOptionPane.showMessageDialog(this, "        ��¼�ɹ���"
								+ "\n" + "��ӭ�㣬" + message.getU().getName());
						this.dispose();
						@SuppressWarnings("unused")
						ClassTeaWindow classTeaWindow = new ClassTeaWindow(
								classTeaBLService, message);
					} else if (message.getMesType().equals(
							MessageType.tea_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"�û���������������������Ƿ���ȷ��");
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
					JOptionPane.showMessageDialog(this, "�������ò���е����⣡");
				} else {
					if (message.getMesType()
							.equals(MessageType.stu_log_success)) {
						JOptionPane.showMessageDialog(this, "        ��¼�ɹ���"
								+ "\n" + "��ӭ�㣬" + message.getU().getName());
						this.dispose();
						 @SuppressWarnings("unused")
						StuWindow stuWindow = new StuWindow(studentBLService,
									message);
					} else if (message.getMesType().equals(
							MessageType.stu_log_fail)) {
						JOptionPane.showMessageDialog(this,
								"�û���������������������Ƿ���ȷ��");
					}
				}
			}

		} else if (arg0.getSource() == this.jb2) {
			this.dispose();
			System.exit(0);
		}

	}

}
