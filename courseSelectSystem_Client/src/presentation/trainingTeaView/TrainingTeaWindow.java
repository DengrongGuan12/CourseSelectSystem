package presentation.trainingTeaView;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import businesslogicservice.TrainingTeaBLService;

import common.Message;
import common.MessageType;

import tools.ImagePanel;
import tools.MyTools;

public class TrainingTeaWindow extends JFrame implements ActionListener,
		MouseListener {

	private static final long serialVersionUID = 1L;
	// ������Ҫ�����
	ImageIcon titleIcon, timeIcon, backIcon;

	// ������
	JToolBar jtb;
	JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;

	// ������Ҫ��������
	JPanel p1, p2, p3, p4, p5;

	// ��ʾ��ǰʱ��
	JLabel timeNow;

	// ��ʾ��ǰ��״̬(�硱�����ɹ�����)
	JLabel stateNow;

	// Ϊ����ʱ��label�ı���panel
	ImagePanel ip1;

	// ��p2��嶨����Ҫ��JLabel,������ͷ��1ָ���ң�2ָ����
	JLabel p2_lab1, p2_lab2;

	// ��ʱ�䶯������javax.swing���е�Timer����Զ�ʱ�Ĵ���Action�¼�
	javax.swing.Timer t;

	JLabel p1_lab1, p1_lab2, p1_lab3, p1_lab4, p1_lab5, p1_lab6, p1_lab7,
			p1_lab8, p1_lab9, p1_lab10;
	ImagePanel p1_imgPanel;

	JSplitPane jsp;

	CardLayout cardP3;
	CardLayout cardP2;
	Message message;
	TrainingTeaBLService trainingTeaBLService;
	JDialog buffer;
	JDialog suc;
	TrainingTeaWindow trainingTeaWindow;

	public TrainingTeaWindow getTrainingTeaWindow() {
		return trainingTeaWindow;
	}

	public void setTrainingTeaWindow(TrainingTeaWindow trainingTeaWindow) {
		this.trainingTeaWindow = trainingTeaWindow;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public TrainingTeaBLService getTrainingTeaBLService() {
		return trainingTeaBLService;
	}

	public void setTrainingTeaBLService(
			TrainingTeaBLService trainingTeaBLService) {
		this.trainingTeaBLService = trainingTeaBLService;
	}

	// ��ʼ��������
	public void initialToolBar() {
		// �������������
		jtb = new JToolBar();
		// ���ò��ɸ���
		jtb.setFloatable(false);
		jb1 = new JButton(new ImageIcon(
				"images\\TrainingTea\\kuangjiacelue.png"));
		jb1.addMouseListener(this);
		jl1 = new JLabel("�����ܲ���");
		jl1.setFont(MyTools.f3);
		jl1.setVisible(false);
		jb2 = new JButton(
				new ImageIcon("images\\TrainingTea\\jiaoxuejihua.PNG"));
		jb2.addMouseListener(this);
		jl2 = new JLabel("Ժϵ��ѧ�ƻ�");
		jl2.setFont(MyTools.f3);
		jl2.setVisible(false);
		jb3 = new JButton(new ImageIcon("images\\TrainingTea\\allTea.png"));
		jb3.addMouseListener(this);
		jl3 = new JLabel("ȫУ��ʦ");
		jl3.setFont(MyTools.f3);
		jl3.setVisible(false);
		jb4 = new JButton(new ImageIcon("images\\TrainingTea\\allStu.png"));
		jb4.addMouseListener(this);
		jl4 = new JLabel("ȫУѧ��");
		jl4.setFont(MyTools.f3);
		jl4.setVisible(false);
		jb5 = new JButton(new ImageIcon("images\\TrainingTea\\allCourses.png"));
		jb5.addMouseListener(this);
		jl5 = new JLabel("ȫУ�γ�");
		jl5.setFont(MyTools.f3);
		jl5.setVisible(false);
		jb6 = new JButton(new ImageIcon(
				"images\\TrainingTea\\tianjiayonghu.png"));
		jb6.addMouseListener(this);
		jl6 = new JLabel("����û�");
		jl6.setFont(MyTools.f3);
		jl6.setVisible(false);
		jb7 = new JButton(new ImageIcon("images\\TrainingTea\\xiugaimima.png"));
		jb7.addMouseListener(this);
		jl7 = new JLabel("�޸�����");
		jl7.setFont(MyTools.f3);
		jl7.setVisible(false);
		jb8 = new JButton(new ImageIcon("images\\TrainingTea\\endSelect.png"));
		jb8.addMouseListener(this);
		jl8 = new JLabel("����ѡ��");
		jl8.setFont(MyTools.f3);
		jl8.setVisible(false);
		jb9 = new JButton(new ImageIcon("images\\TrainingTea\\startSelect.png"));
		jb9.addMouseListener(this);
		jl9 = new JLabel("��ʼѡ��");
		jl9.setFont(MyTools.f3);
		jl9.setVisible(false);

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
		jtb.add(jb6);
		jtb.add(jl6);
		jtb.add(jb7);
		jtb.add(jl7);
		jtb.add(jb8);
		jtb.add(jl8);
		jtb.add(jb9);
		jtb.add(jl9);
	}

	public void initialAllPanels() {
		// p1������д󱳾������
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		backIcon = new ImageIcon("images\\TrainingTea\\back.png");
		p1_imgPanel = new ImagePanel(backIcon.getImage());

		// ���
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel.setLayout(new GridLayout(10, 1));

		p1_lab1 = new JLabel(new ImageIcon("images\\TrainingTea\\tubiao.gif"));
		p1_imgPanel.add(p1_lab1);

		p1_lab2 = new JLabel("�����ܲ���", new ImageIcon(
				"images\\TrainingTea\\kuangjiacelue.png"), 0);
		p1_lab2.setFont(MyTools.f3);
		// ���ù��
		p1_lab2.setCursor(myCursor);
		p1_lab2.setEnabled(false);
		// ע�����
		p1_lab2.addMouseListener(this);
		p1_imgPanel.add(p1_lab2);

		p1_lab3 = new JLabel("Ժϵ��ѧ�ƻ�", new ImageIcon(
				"images\\TrainingTea\\jiaoxuejihua.PNG"), 0);
		p1_lab3.setFont(MyTools.f3);
		// ���ù��
		p1_lab3.setCursor(myCursor);
		p1_lab3.setEnabled(false);
		// ע�����
		p1_lab3.addMouseListener(this);
		p1_imgPanel.add(p1_lab3);

		p1_lab4 = new JLabel("ȫ У �� ʦ", new ImageIcon(
				"images\\TrainingTea\\allTea.png"), 0);
		p1_lab4.setFont(MyTools.f3);
		// ���ù��
		p1_lab4.setCursor(myCursor);
		p1_lab4.setEnabled(false);
		// ע�����
		p1_lab4.addMouseListener(this);
		p1_imgPanel.add(p1_lab4);

		p1_lab5 = new JLabel("ȫ У ѧ ��", new ImageIcon(
				"images\\TrainingTea\\allStu.png"), 0);
		p1_lab5.setFont(MyTools.f3);
		// ���ù��
		p1_lab5.setCursor(myCursor);
		p1_lab5.setEnabled(false);
		// ע�����
		p1_lab5.addMouseListener(this);
		p1_imgPanel.add(p1_lab5);

		p1_lab6 = new JLabel("ȫ У �� ��", new ImageIcon(
				"images\\TrainingTea\\allCourses.png"), 0);
		p1_lab6.setFont(MyTools.f3);
		// ���ù��
		p1_lab6.setCursor(myCursor);
		p1_lab6.setEnabled(false);
		// ע�����
		p1_lab6.addMouseListener(this);
		p1_imgPanel.add(p1_lab6);

		p1_lab7 = new JLabel("�� �� �� ��", new ImageIcon(
				"images\\TrainingTea\\tianjiayonghu.png"), 0);
		p1_lab7.setFont(MyTools.f3);
		// ���ù��
		p1_lab7.setCursor(myCursor);
		p1_lab7.setEnabled(false);
		// ע�����
		p1_lab7.addMouseListener(this);
		p1_imgPanel.add(p1_lab7);

		p1_lab8 = new JLabel("�� �� �� ��", new ImageIcon(
				"images\\TrainingTea\\xiugaimima.png"), 0);
		p1_lab8.setFont(MyTools.f3);
		// ���ù��
		p1_lab8.setCursor(myCursor);
		p1_lab8.setEnabled(false);
		// ע�����
		p1_lab8.addMouseListener(this);
		p1_imgPanel.add(p1_lab8);

		p1_lab9 = new JLabel("�� �� ѡ ��", new ImageIcon(
				"images\\TrainingTea\\endSelect.png"), 0);
		p1_lab9.setFont(MyTools.f3);
		// ���ù��
		p1_lab9.setCursor(myCursor);
		p1_lab9.setEnabled(false);
		// ע�����
		p1_lab9.addMouseListener(this);
		p1_imgPanel.add(p1_lab9);

		p1_lab10 = new JLabel("�� ʼ ѡ ��", new ImageIcon(
				"images\\TrainingTea\\startSelect.png"), 0);
		p1_lab10.setFont(MyTools.f3);
		// ���ù��
		p1_lab10.setCursor(myCursor);
		p1_lab10.setEnabled(false);
		// ע�����
		p1_lab10.addMouseListener(this);
		p1_imgPanel.add(p1_lab10);

		p1.add(p1_imgPanel);

		// ����p2,p3,p4
		p4 = new JPanel(new BorderLayout());

		cardP2 = new CardLayout();

		// p2���ڴ�����ֲ�ͬ����ļ�ͷ
		p2 = new JPanel(this.cardP2);
		p2_lab1 = new JLabel(new ImageIcon("images\\TrainingTea\\toright.png"));
		p2_lab1.addMouseListener(this);
		p2_lab2 = new JLabel(new ImageIcon("images\\TrainingTea\\toleft.png"));
		p2_lab2.addMouseListener(this);
		p2.add(p2_lab1, "0");
		p2.add(p2_lab2, "1");
		cardP2.show(p2, "1");

		this.cardP3 = new CardLayout();
		p3 = new JPanel(cardP3);

		// �ȸ�p3����һ��������Ŀ�Ƭ,p3�������ݽ���
		ImageIcon mainP3 = new ImageIcon("images\\TrainingTea\\p3.PNG");
		ImagePanel ip = new ImagePanel(mainP3.getImage());
		p3.add(ip, "0");

		TraTeaXiuGaiMMPanel ttxgm = new TraTeaXiuGaiMMPanel(
				this.trainingTeaBLService, this.message);
		ttxgm.setTrainingTeaWindow(this);
		p3.add(ttxgm, "1");

		CourseListPanel clp = new CourseListPanel(this.trainingTeaBLService,
				this.message);
		p3.add(clp, "2");

		RegisterPanel rp = new RegisterPanel(trainingTeaBLService);
		rp.setTrainingTeaWindow(this);
		p3.add(rp, "3");

		StrategyPanel sp = new StrategyPanel(trainingTeaBLService, message);
		sp.setTrainingTeaWindow(this);
		p3.add(sp, "4");

		StudentListPanel slp = new StudentListPanel(trainingTeaBLService,
				message);
		p3.add(slp, "5");

		TeacherListPanel tlp = new TeacherListPanel(trainingTeaBLService,
				message);
		p3.add(tlp, "6");

		TeachingPlanPanel tpp = new TeachingPlanPanel(trainingTeaBLService,
				message);
		p3.add(tpp, "7");

		// //����EmpInfo����
		// EmpInfo empInfo=new EmpInfo();
		//
		// p3.add(empInfo,"1");
		//
		// JLabel dlgl=new JLabel(new ImageIcon("image/degl.jpg"));
		// p3.add(dlgl,"2");
		//
		// //����ʾ�����JPanel new ����
		// Chart myChart=new Chart();
		// p3.add(myChart,"4");

		// ��p2,p3����p4
		p4.add(p2, "West");
		p4.add(p3, "Center");

		// ��һ����ִ��ڣ��ֱ���p1,p4
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		// ָ����ߵ����ռ���ķ�Χ
		jsp.setDividerLocation(130);
		// �ѱ߽�����Ϊ0
		jsp.setDividerSize(0);

	}

	@SuppressWarnings("deprecation")
	public TrainingTeaWindow(TrainingTeaBLService trainingTeaBLService,
			Message message) {
		this.setMessage(message);
		this.setTrainingTeaBLService(trainingTeaBLService);
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}

		this.initialToolBar();

		this.initialAllPanels();
		// ����P5���(״̬�����)
		p5 = new JPanel(new BorderLayout());
		// ����Timer
		t = new Timer(1000, this);// ÿ��һ�봥��ActionEvent
		// ������ʱ��
		t.start();

		timeNow = new JLabel(Calendar.getInstance().getTime().toLocaleString()
				+ "   ");
		timeNow.setFont(MyTools.f2);

		stateNow = new JLabel(
				"");
		stateNow.setFont(MyTools.f2);

		timeIcon = new ImageIcon("images\\TrainingTea\\zhuangtailan.PNG");
		ip1 = new ImagePanel(timeIcon.getImage());
		ip1.setLayout(new BorderLayout());
		ip1.add(stateNow, "Center");
		ip1.add(timeNow, "East");

		p5.add(ip1);

		// ��JFrame��ȡ��container
		Container ct = this.getContentPane();
		ct.add(jtb, "North");
		ct.add(jsp, "Center");
		ct.add(p5, "South");

		if (this.message.getU().getSex().equals("��")) {
			titleIcon = new ImageIcon("images\\TrainingTea\\Mr.jpg");
		} else {
			titleIcon = new ImageIcon("images\\TrainingTea\\Miss.jpg");
		}

		// ���ô�С
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		// �رմ���ʱ�˳�ϵͳ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���ô��ڵ�ͼ��
		this.setIconImage(titleIcon.getImage());
		this.setTitle("�Ͼ���ѧѡ��ϵͳ--������ʦ");
		this.setSize(w, h - 30);
		this.setVisible(true);

	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// new TrainingTeaWindow();
	//
	// }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.p2_lab2) {
			// ����ʾ���ֲ����Ľ�����������
			// ��ʾ���ҵļ�ͷ
			this.cardP2.show(p2, "0");
			this.jsp.setDividerLocation(0);
		} else if (arg0.getSource() == this.p2_lab1) {
			// ����ʾ���ֲ����Ľ���չ��
			// ��ʾ����ļ�ͷ
			this.cardP2.show(p2, "1");
			this.jsp.setDividerLocation(130);
		} else if (arg0.getSource() == this.p1_lab8
				|| arg0.getSource() == this.jb7) {
			this.cardP3.show(p3, "1");
		} else if (arg0.getSource() == this.jb1
				|| arg0.getSource() == this.p1_lab2) {
			this.cardP3.show(p3, "4");
		} else if (arg0.getSource() == this.jb2
				|| arg0.getSource() == this.p1_lab3) {
			this.cardP3.show(p3, "7");
		} else if (arg0.getSource() == this.jb3
				|| arg0.getSource() == this.p1_lab4) {
			this.cardP3.show(p3, "6");
		} else if (arg0.getSource() == this.jb4
				|| arg0.getSource() == this.p1_lab5) {
			this.cardP3.show(p3, "5");
		} else if (arg0.getSource() == this.jb5
				|| arg0.getSource() == this.p1_lab6) {
			this.cardP3.show(p3, "2");
		} else if (arg0.getSource() == this.jb6
				|| arg0.getSource() == this.p1_lab7) {
			this.cardP3.show(p3, "3");
		} else if (arg0.getSource() == this.jb8
				|| arg0.getSource() == this.p1_lab9) {
			this.cardP3.show(p3, "0");
			endSelect();
		} else if (arg0.getSource() == this.jb9
				|| arg0.getSource() == this.p1_lab10) {
			this.cardP3.show(p3, "0");
			startSelect();
		}

	}

	// ��ʼѡ��
	private class startSelectThread extends Thread {
		public synchronized void run() {
			try {
				Message message = new Message();
				ImageIcon im = new ImageIcon("images\\TrainingTea\\buffer.gif");
				buffer = new JDialog();
				ImagePanel imagePanel = new ImagePanel(im.getImage());
				buffer.add(imagePanel);
				buffer.setUndecorated(true);
				buffer.setSize(300, 10);
				buffer.setVisible(true);
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				buffer.setLocation((width - 150) / 2, (height - 80) / 2);

				try {
					message = trainingTeaBLService.startSelect();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message.getMesType() == null) {
					buffer.dispose();
					// buffering b=new buffering();
					// b.start();
					JOptionPane.showMessageDialog(trainingTeaWindow,
							"�������ò���е�С���⣡");
					stateNow.setText("                                                                                          ����ʧ�ܣ�");
				} else if (message.getMesType().equals(
						MessageType.traTea_startSelect_fail)) {
					buffer.dispose();
					// buffering b=new buffering();
					// b.start();
					JOptionPane.showMessageDialog(trainingTeaWindow, "�Ѿ���ʼѡ�Σ�");
					stateNow.setText("                                                                                          ����ʧ�ܣ�");
				} else if (message.getMesType().equals(
						MessageType.traTea_startSelect_suc)) {
					buffer.dispose();
					// buffering b=new buffering();
					// b.start();

					ImageIcon imSuc = new ImageIcon(
							"images\\TrainingTea\\suc.png");
					suc = new JDialog();
					ImagePanel imagePanelSuc = new ImagePanel(imSuc.getImage());
					suc.add(imagePanelSuc);
					suc.setUndecorated(true);
					suc.setSize(150, 80);
					suc.setVisible(true);

					suc.setLocation((width - 150) / 2, (height - 80) / 2);
					success s = new success();
					s.start();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	private void startSelect() {
		this.setTrainingTeaWindow(this);
		startSelectThread s = new startSelectThread();
		buffering buf = new buffering();
		buf.start();
		s.start();
	}

	private class endSelectThread extends Thread {
		public synchronized void run() {
			try {
				Message message = new Message();
				ImageIcon im = new ImageIcon("images\\TrainingTea\\buffer.gif");
				buffer = new JDialog();
				ImagePanel imagePanel = new ImagePanel(im.getImage());
				buffer.add(imagePanel);
				buffer.setUndecorated(true);
				buffer.setSize(300, 10);
				buffer.setVisible(true);
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				buffer.setLocation((width - 150) / 2, (height - 80) / 2);
				try {
					message = trainingTeaBLService.endSelect();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (message.getMesType() == null) {
					// buffering b=new buffering();
					// b.start();
					buffer.dispose();
					JOptionPane.showMessageDialog(trainingTeaWindow,
							"�������ò���е�С���⣡");
					stateNow.setText("                                                                                          ����ʧ�ܣ�");
				} else if (message.getMesType().equals(
						MessageType.traTea_endSelect_fail)) {
					buffer.dispose();
					// buffering b=new buffering();
					// b.start();
					JOptionPane.showMessageDialog(trainingTeaWindow, "�Ѿ�����ѡ�Σ�");
					stateNow.setText("                                                                                          ����ʧ�ܣ�");
				} else if (message.getMesType().equals(
						MessageType.traTea_endSelect_suc)) {
					buffer.dispose();
					// buffering b=new buffering();
					// b.start();
					ImageIcon imSuc = new ImageIcon(
							"images\\TrainingTea\\suc.png");
					suc = new JDialog();
					ImagePanel imagePanelSuc = new ImagePanel(imSuc.getImage());
					suc.add(imagePanelSuc);
					suc.setUndecorated(true);
					suc.setSize(150, 80);
					suc.setVisible(true);

					suc.setLocation((width - 150) / 2, (height - 80) / 2);
					success s = new success();
					s.start();

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private void endSelect() {
		this.setTrainingTeaWindow(this);
		endSelectThread e = new endSelectThread();
		buffering b = new buffering();
		b.start();
		e.start();

	}

	private class buffering extends Thread {
		public synchronized void run() {
			try {

				Thread.sleep(10000);
				// buffer.dispose();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class success extends Thread {
		public synchronized void run() {
			try {

				Thread.sleep(1500);
				suc.dispose();
				stateNow.setText("                                                                                          �����ɹ���");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.p1_lab2) {
			this.p1_lab2.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab3) {
			this.p1_lab3.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab4) {
			this.p1_lab4.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab5) {
			this.p1_lab5.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab6) {
			this.p1_lab6.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab7) {
			this.p1_lab7.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab8) {
			this.p1_lab8.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab9) {
			this.p1_lab9.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab10) {
			this.p1_lab10.setEnabled(true);
		} else if (arg0.getSource() == jb1) {
			jl1.setVisible(true);

		} else if (arg0.getSource() == jb2) {
			jl2.setVisible(true);

		} else if (arg0.getSource() == jb3) {
			jl3.setVisible(true);
		} else if (arg0.getSource() == jb4) {
			jl4.setVisible(true);
		} else if (arg0.getSource() == jb5) {
			jl5.setVisible(true);
		} else if (arg0.getSource() == jb6) {
			jl6.setVisible(true);
		} else if (arg0.getSource() == jb7) {
			jl7.setVisible(true);
		} else if (arg0.getSource() == jb8) {
			jl8.setVisible(true);
		} else if (arg0.getSource() == jb9) {
			jl9.setVisible(true);
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.p1_lab2) {
			this.p1_lab2.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab3) {
			this.p1_lab3.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab4) {
			this.p1_lab4.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab5) {
			this.p1_lab5.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab6) {
			this.p1_lab6.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab7) {
			this.p1_lab7.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab8) {
			this.p1_lab8.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab9) {
			this.p1_lab9.setEnabled(false);
		} else if (arg0.getSource() == this.p1_lab10) {
			this.p1_lab10.setEnabled(false);
		} else if (arg0.getSource() == this.jb1) {
			jl1.setVisible(false);

		} else if (arg0.getSource() == this.jb2) {
			jl2.setVisible(false);
		} else if (arg0.getSource() == this.jb3) {
			jl3.setVisible(false);
		} else if (arg0.getSource() == this.jb4) {
			jl4.setVisible(false);
		} else if (arg0.getSource() == this.jb5) {
			jl5.setVisible(false);
		} else if (arg0.getSource() == this.jb6) {
			jl6.setVisible(false);
		} else if (arg0.getSource() == this.jb7) {
			jl7.setVisible(false);
		} else if (arg0.getSource() == this.jb8) {
			jl8.setVisible(false);
		} else if (arg0.getSource() == this.jb9) {
			jl9.setVisible(false);
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
		this.timeNow.setText("��ǰʱ�䣺 "
				+ Calendar.getInstance().getTime().toLocaleString() + "  ");

	}

}
