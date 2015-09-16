package presentation.stuView;

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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;

import javax.swing.JSplitPane;

import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.UIManager;

import common.Message;
import common.Student;

import businesslogicservice.StudentBLService;

import tools.ImagePanel;
import tools.MyTools;

public class StuWindow extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	TuiXuanPanel txp;// 为了让点击按钮之后才提醒时间冲突
	// 定义接口
	StudentBLService studentBLService;
	Message message;

	public void setMessage(Message message) {
		this.message = message;

	}

	public void set(StudentBLService studentBLService) {
		this.studentBLService = studentBLService;
	}

	// 定义需要的组件

	ImageIcon titleIcon, timeIcon, backIcon;

	JMenuBar jmb;
	// 一级菜单
	JMenu jm1, jm2, jm3;
	// 二级菜单
	JMenuItem jm1_jmi1, jm1_jmi2, jm1_jmi3, jm1_jmi4, jm1_jmi5, jm2_jmi1,
			jm2_jmi2, jm2_jmi3, jm2_jmi4, jm3_jmi1, jm3_jmi2;

	// 图标
	ImageIcon jm1_icon1, jm1_icon2, jm1_icon3, jm1_icon4, jm1_icon5, jm2_icon1,
			jm2_icon2, jm2_icon3, jm2_icon4, jm3_icon1, jm3_icon2;

	// 工具栏
	JToolBar jtb;
	JButton jb1, jb2, jb3, jb4, jb5, jb6;

	JLabel jl1, jl2, jl3, jl4, jl5, jl6;

	// 定义需要的五个面板
	JPanel p1, p2, p3, p4, p5;

	// 显示当前时间
	JLabel timeNow;

	// 显示当前的状态(如”发布成功！“)
	JLabel stateNow;

	// 为放置时间label的背景panel
	ImagePanel ip1;

	// 给p2面板定义需要的JLabel,两个箭头（1指向右，2指向左）
	JLabel p2_lab1, p2_lab2;

	// 让时间动起来，javax.swing包中的Timer类可以定时的触发Action事件
	javax.swing.Timer t;

	JLabel p1_lab1, p1_lab2, p1_lab3, p1_lab4, p1_lab5, p1_lab6, p1_lab7,
			p1_lab8;
	ImagePanel p1_imgPanel;

	JSplitPane jsp;

	CardLayout cardP3;
	CardLayout cardP2;
	Student s;

	private JPanel panel_ToolAndMenu;
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// new StuWindow();
	//
	// }

	// 初始化菜单
	public void initialMenu() {
		// 创建一级菜单
		jm1 = new JMenu("学期选课");
		jm1.setFont(MyTools.f1);
		Student s = (Student) (this.message.getU());
		if (!s.isCanSelect()) {
			jm1.setEnabled(false);
		}
		// jm1.setMnemonic(KeyEvent.VK_X);
		jm1_icon1 = new ImageIcon("images\\Stu\\tongshi.PNG");
		jm1_icon2 = new ImageIcon("images\\Stu\\gongxuanke.PNG");
		jm1_icon3 = new ImageIcon("images\\Stu\\kuazhuanye.PNG");
		jm1_icon4 = new ImageIcon("images\\Stu\\xinsheng.PNG");
		jm1_icon5 = new ImageIcon("images\\Stu\\tuixuan.PNG");
		// 创建它的二级菜单
		jm1_jmi1 = new JMenuItem("通识课", jm1_icon1);
		jm1_jmi1.addMouseListener(this);
		jm1_jmi1.setFont(MyTools.f2);

		jm1_jmi2 = new JMenuItem("公选课", jm1_icon2);
		jm1_jmi2.addMouseListener(this);
		jm1_jmi2.setFont(MyTools.f2);

		jm1_jmi3 = new JMenuItem("跨专业", jm1_icon3);
		jm1_jmi3.addMouseListener(this);
		jm1_jmi3.setFont(MyTools.f2);

		jm1_jmi4 = new JMenuItem("新生研讨课", jm1_icon4);
		jm1_jmi4.addMouseListener(this);
		jm1_jmi4.setFont(MyTools.f2);
		String grade = s.getGrade();
		int grd = Integer.parseInt(grade);

		if (grd > 1) {
			jm1_jmi4.setEnabled(false);
		} else {
			jm1_jmi4.setEnabled(true);
		}

		jm1_jmi5 = new JMenuItem("课程退选", jm1_icon5);
		jm1_jmi5.addMouseListener(this);
		jm1_jmi5.setFont(MyTools.f2);

		// 加入
		jm1.add(jm1_jmi1);
		jm1.add(jm1_jmi2);
		jm1.add(jm1_jmi3);
		jm1.add(jm1_jmi4);
		jm1.add(jm1_jmi5);

		jm2 = new JMenu("个人信息");
		jm2.setFont(MyTools.f1);
		jm2_icon1 = new ImageIcon("images\\Stu\\jingtaixinxi.png");
		jm2_icon2 = new ImageIcon("images\\Stu\\xiugaimima.png");
		jm2_icon3 = new ImageIcon("images\\Stu\\dongtaixinxi.png");
		jm2_icon4 = new ImageIcon("images\\Stu\\chjichakan.png");

		// 创建他的二级菜单
		jm2_jmi1 = new JMenuItem("静态信息", jm2_icon1);
		jm2_jmi1.addMouseListener(this);
		jm2_jmi1.setFont(MyTools.f2);
		jm2_jmi2 = new JMenuItem("修改密码", jm2_icon2);
		jm2_jmi2.addMouseListener(this);
		jm2_jmi2.setFont(MyTools.f2);
		jm2_jmi3 = new JMenuItem("动态信息", jm2_icon3);
		jm2_jmi3.addMouseListener(this);
		jm2_jmi3.setFont(MyTools.f2);
		jm2_jmi4 = new JMenuItem("成绩查看", jm2_icon4);
		jm2_jmi4.addMouseListener(this);
		jm2_jmi4.setFont(MyTools.f2);

		jm2.add(jm2_jmi1);
		jm2.add(jm2_jmi2);
		jm2.add(jm2_jmi3);
		jm2.add(jm2_jmi4);

		jm3 = new JMenu("教学信息");
		jm3.setFont(MyTools.f1);
		jm3_icon1 = new ImageIcon("images\\Stu\\allCourses.png");
		jm3_icon2 = new ImageIcon("images\\Stu\\MyCourse.png");

		jm3_jmi1 = new JMenuItem("全校课程", jm3_icon1);
		jm3_jmi1.addMouseListener(this);
		jm3_jmi1.setFont(MyTools.f2);
		jm3_jmi2 = new JMenuItem("我的课程", jm3_icon2);
		jm3_jmi2.addMouseListener(this);
		jm3_jmi2.setFont(MyTools.f2);

		jm3.add(jm3_jmi1);
		jm3.add(jm3_jmi2);

		// 把一级菜单加入到JMenuBar
		jmb = new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);

	}

	// 初始化工具栏
	public void initialToolBar() {
		// 处理工具栏的组件
		jtb = new JToolBar();
		// 设置不可浮动
		Student s = (Student) (this.message.getU());
		jtb.setFloatable(false);
		jb1 = new JButton(new ImageIcon("images\\Stu\\tongshi.PNG"));
		if (!s.isCanSelect()) {
			jb1.setEnabled(false);
		}
		jb1.addMouseListener(this);
		jl1 = new JLabel("通识课选课");
		jl1.setFont(MyTools.f3);
		jl1.setVisible(false);

		jb2 = new JButton(new ImageIcon("images\\Stu\\tuixuan.PNG"));
		jb2.addMouseListener(this);
		jl2 = new JLabel("课程退选");
		jl2.setFont(MyTools.f3);
		jl2.setVisible(false);
		jb3 = new JButton(new ImageIcon("images\\Stu\\xiugaimima.png"));
		jb3.addMouseListener(this);
		jl3 = new JLabel("修改密码");
		jl3.setFont(MyTools.f3);
		jl3.setVisible(false);
		jb4 = new JButton(new ImageIcon("images\\Stu\\chjichakan.png"));
		jb4.addMouseListener(this);
		jl4 = new JLabel("查看成绩");
		jl4.setFont(MyTools.f3);
		jl4.setVisible(false);
		jb5 = new JButton(new ImageIcon("images\\Stu\\allCourses.png"));
		jb5.addMouseListener(this);
		jl5 = new JLabel("全校课程");
		jl5.setFont(MyTools.f3);
		jl5.setVisible(false);
		jb6 = new JButton(new ImageIcon("images\\Stu\\MyCourse.png"));
		jb6.addMouseListener(this);
		jl6 = new JLabel("我的课程");
		jl6.setFont(MyTools.f3);
		jl6.setVisible(false);

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
	}

	public void initialAllPanels() {
		// p1面板是有大背景的面板
		s = (Student) (this.message.getU());
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		backIcon = new ImageIcon("images\\Stu\\back.png");
		p1_imgPanel = new ImagePanel(backIcon.getImage());

		// 光标
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);

		this.p1_imgPanel.setLayout(new GridLayout(8, 1));

		p1_lab1 = new JLabel(new ImageIcon("images\\Stu\\tubiao.gif"));
		p1_imgPanel.add(p1_lab1);

		p1_lab2 = new JLabel("全 校 课 程", new ImageIcon(
				"images\\Stu\\allCourses.png"), 0);

		p1_lab2.setFont(MyTools.f3);
		// 设置光标
		p1_lab2.setCursor(myCursor);
		p1_lab2.setEnabled(false);
		// 注册监听
		p1_lab2.addMouseListener(this);
		p1_imgPanel.add(p1_lab2);

		p1_lab3 = new JLabel("我 的 课 程", new ImageIcon(
				"images\\Stu\\MyCourse.png"), 0);
		p1_lab3.setFont(MyTools.f3);
		// 设置光标
		p1_lab3.setCursor(myCursor);
		p1_lab3.setEnabled(false);
		// 注册监听
		p1_lab3.addMouseListener(this);
		p1_imgPanel.add(p1_lab3);

		p1_lab4 = new JLabel("成 绩 查 看", new ImageIcon(
				"images\\Stu\\chjichakan.png"), 0);
		p1_lab4.setFont(MyTools.f3);
		// 设置光标
		p1_lab4.setCursor(myCursor);
		p1_lab4.setEnabled(false);
		// 注册监听
		p1_lab4.addMouseListener(this);
		p1_imgPanel.add(p1_lab4);

		p1_lab5 = new JLabel("课 程 退 选", new ImageIcon(
				"images\\Stu\\tuixuan.PNG"), 0);
		p1_lab5.setFont(MyTools.f3);
		// 设置光标
		p1_lab5.setCursor(myCursor);
		p1_lab5.setEnabled(false);
		// 注册监听
		p1_lab5.addMouseListener(this);
		p1_imgPanel.add(p1_lab5);

		p1_lab6 = new JLabel("选课(通识课)", new ImageIcon(
				"images\\Stu\\tongshi.PNG"), 0);
		p1_lab6.setFont(MyTools.f3);
		// 设置光标
		p1_lab6.setCursor(myCursor);
		p1_lab6.setEnabled(false);
		// 注册监听
		p1_lab6.addMouseListener(this);

		p1_imgPanel.add(p1_lab6);

		p1_lab7 = new JLabel("我 的 信 息", new ImageIcon(
				"images\\Stu\\dongtaixinxi.png"), 0);
		p1_lab7.setFont(MyTools.f3);
		// 设置光标
		p1_lab7.setCursor(myCursor);
		p1_lab7.setEnabled(false);
		// 注册监听
		p1_lab7.addMouseListener(this);
		p1_imgPanel.add(p1_lab7);

		p1_lab8 = new JLabel("修 改 密 码", new ImageIcon(
				"images\\Stu\\xiugaimima.png"), 0);
		p1_lab8.setFont(MyTools.f3);
		// 设置光标
		p1_lab8.setCursor(myCursor);
		p1_lab8.setEnabled(false);
		// 注册监听
		p1_lab8.addMouseListener(this);
		p1_imgPanel.add(p1_lab8);

		p1.add(p1_imgPanel);

		// 处理p2,p3,p4
		p4 = new JPanel(new BorderLayout());

		cardP2 = new CardLayout();

		// p2用于存放两种不同方向的箭头
		p2 = new JPanel(this.cardP2);
		p2_lab1 = new JLabel(new ImageIcon("images\\Stu\\toright.png"));
		p2_lab1.addMouseListener(this);
		p2_lab2 = new JLabel(new ImageIcon("images\\Stu\\toleft.png"));
		p2_lab2.addMouseListener(this);
		p2.add(p2_lab1, "0");
		p2.add(p2_lab2, "1");
		cardP2.show(p2, "1");

		this.cardP3 = new CardLayout();
		p3 = new JPanel(cardP3);

		// 先给p3加入一个主界面的卡片,p3是主操纵界面
		ImageIcon mainP3 = new ImageIcon("images\\Stu\\p3.PNG");
		ImagePanel ip = new ImagePanel(mainP3.getImage());
		p3.add(ip, "0");

		TongshiPanel tsp = new TongshiPanel(this.studentBLService, this.message);
		tsp.setStuWindow(this);

		p3.add(tsp, "1");

		GongxuanPanel gxp = new GongxuanPanel(this.studentBLService,
				this.message);
		gxp.setStuWindow(this);
		p3.add(gxp, "2");

		XinShengPanel xsp = new XinShengPanel(this.studentBLService,
				this.message);
		xsp.setStuWindow(this);
		p3.add(xsp, "3");

		KuaZhuanYePanel kzyp = new KuaZhuanYePanel(this.studentBLService,
				this.message);
		kzyp.setStuWindow(this);
		p3.add(kzyp, "4");

		txp = new TuiXuanPanel(this.studentBLService, this.message);
		txp.setStuWindow(this);
		p3.add(txp, "5");

		AllCoursePanel acp = new AllCoursePanel(this.studentBLService,
				this.message);
		acp.setStuWindow(this);
		p3.add(acp, "6");

		MyCoursePanel mcp = new MyCoursePanel(this.studentBLService,
				this.message);
		mcp.setStuWindow(this);
		p3.add(mcp, "7");

		StuXiuGaiMMPanel sxgmm = new StuXiuGaiMMPanel(this.studentBLService,
				this.message);
		sxgmm.setStuWindow(this);
		p3.add(sxgmm, "8");

		ShowScorePanel ssp = new ShowScorePanel(this.studentBLService,
				this.message);
		p3.add(ssp, "9");

		MyStaticInfoPanel msi = new MyStaticInfoPanel(this.message,
				this.studentBLService);
		msi.setStuWindow(this);
		p3.add(msi, "10");
		
		DynamicInfoPanel dip = new DynamicInfoPanel(this.studentBLService,this.message);
		dip.setStuWindow(this);
		p3.add(dip,"11");
		// //创建EmpInfo对象
		// EmpInfo empInfo=new EmpInfo();
		//
		// p3.add(empInfo,"1");
		//
		// JLabel dlgl=new JLabel(new ImageIcon("image/degl.jpg"));
		// p3.add(dlgl,"2");
		//
		// //把显示报表的JPanel new 出来
		// Chart myChart=new Chart();
		// p3.add(myChart,"4");

		// 把p2,p3加入p4
		p4.add(p2, "West");
		p4.add(p3, "Center");

		// 做一个拆分窗口，分别存放p1,p4
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);
		// 指定左边的面板占多大的范围
		jsp.setDividerLocation(130);
		// 把边界线设为0
		jsp.setDividerSize(0);

	}

	@SuppressWarnings("deprecation")
	public StuWindow(StudentBLService studentBLService, Message message) {
		this.setMessage(message);
		this.set(studentBLService);
		try {
//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}

		this.initialMenu();
		// 把JmenuBar添加到Jframe
		//

		this.initialToolBar();

		this.initialAllPanels();

		//this.setJMenuBar(jmb);

		panel_ToolAndMenu = new JPanel(new BorderLayout());
		panel_ToolAndMenu.add(jmb, BorderLayout.NORTH);
		panel_ToolAndMenu.add(jtb, BorderLayout.SOUTH);
		
		// 处理P5面板(状态栏面板)
		p5 = new JPanel(new BorderLayout());
		// 创建Timer
		t = new Timer(1000, this);// 每隔一秒触发ActionEvent
		// 启动定时器
		t.start();

		timeNow = new JLabel(Calendar.getInstance().getTime().toLocaleString()
				+ "   ");
		timeNow.setFont(MyTools.f2);

		stateNow = new JLabel(
				"无状态！");
		stateNow.setFont(MyTools.f2);

		timeIcon = new ImageIcon("images\\Stu\\zhuangtailan.PNG");
		ip1 = new ImagePanel(timeIcon.getImage());
		ip1.setLayout(new BorderLayout());
		ip1.add(stateNow, "Center");
		ip1.add(timeNow, "East");

		p5.add(ip1);

		// 从JFrame中取得container
		Container ct = this.getContentPane();
		ct.add(panel_ToolAndMenu, "North");
		ct.add(jsp, "Center");
		ct.add(p5, "South");

		titleIcon = new ImageIcon("images\\Stu\\stuTitle.png");
		// 设置大小
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		// 关闭窗口时退出系统
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗口的图标
		this.setIconImage(titleIcon.getImage());
		this.setTitle("南京大学选课系统--学生");
		this.setSize(w, h - 30);
		this.setVisible(true);

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.p2_lab2) {
			// 把显示各种操作的界面隐藏起来
			// 显示向右的箭头
			this.cardP2.show(p2, "0");
			this.jsp.setDividerLocation(0);
		} else if (arg0.getSource() == this.p2_lab1) {
			// 把显示各种操作的界面展开
			// 显示向左的箭头
			this.cardP2.show(p2, "1");
			this.jsp.setDividerLocation(130);
		} else if ((arg0.getSource() == jb1 && jb1.isEnabled())
				|| (arg0.getSource() == p1_lab6 && p1_lab6.isEnabled())) {
			this.cardP3.show(p3, "1");
		} else if (arg0.getSource() == jb2 || arg0.getSource() == p1_lab5) {
			this.cardP3.show(p3, "5");
			txp.refresh();

		} else if (arg0.getSource() == jb5 || arg0.getSource() == p1_lab2) {
			this.cardP3.show(p3, "6");
		} else if (arg0.getSource() == jb6 || arg0.getSource() == p1_lab3) {
			this.cardP3.show(p3, "7");
		} else if (arg0.getSource() == jb3 || arg0.getSource() == p1_lab8) {
			this.cardP3.show(p3, "8");
		} else if (arg0.getSource() == jb4 || arg0.getSource() == p1_lab4) {
			this.cardP3.show(p3, "9");
		} else if (arg0.getSource() == p1_lab7) {
			this.cardP3.show(p3, "10");
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// 如果用户选中了某个操作JLabel，则高亮
		if (arg0.getSource() == this.p1_lab2) {
			this.p1_lab2.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab3) {
			this.p1_lab3.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab4) {
			this.p1_lab4.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab5) {
			this.p1_lab5.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab6) {
			if (!s.isCanSelect()) {
				this.p1_lab6.setEnabled(false);
			} else {
				this.p1_lab6.setEnabled(true);

			}

		} else if (arg0.getSource() == this.p1_lab7) {
			this.p1_lab7.setEnabled(true);
		} else if (arg0.getSource() == this.p1_lab8) {
			this.p1_lab8.setEnabled(true);
		} else if (arg0.getSource() == jb1) {
			// mousepoint=MouseInfo.getPointerInfo().getLocation();
			// jl1=new JLabel("通识课选课");
			// jl1.setBounds((int)mousepoint.getX(), (int)mousepoint.getY(), 50,
			// 20);

			jl1.setVisible(true);
			// this.add(jl1);

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
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// 如果用户选中了某个操作JLabel，则禁用
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
		}

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == jm1_jmi1) {
			this.cardP3.show(p3, "1");
		} else if (arg0.getSource() == jm1_jmi2) {
			this.cardP3.show(p3, "2");

		} else if (arg0.getSource() == jm1_jmi4&&this.jm1_jmi4.isEnabled()) {
			this.cardP3.show(p3, "3");

		} else if (arg0.getSource() == jm1_jmi3) {
			this.cardP3.show(p3, "4");

		} else if (arg0.getSource() == jm1_jmi5) {
			this.cardP3.show(p3, "5");
			txp.refresh();
		} else if (arg0.getSource() == jm3_jmi1) {
			this.cardP3.show(p3, "6");

		} else if (arg0.getSource() == jm3_jmi2) {
			this.cardP3.show(p3, "7");
		} else if (arg0.getSource() == jm2_jmi2) {
			this.cardP3.show(p3, "8");
		} else if (arg0.getSource() == jm2_jmi4) {
			this.cardP3.show(p3, "9");

		} else if (arg0.getSource() == jm2_jmi1) {
			this.cardP3.show(p3, "10");
		}else if(arg0.getSource() == jm2_jmi3){
			this.cardP3.show(p3, "11");
		}
		jmb.repaint();
		jm1.repaint();
		jm2.repaint();
		jm3.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间： "
				+ Calendar.getInstance().getTime().toLocaleString() + "  ");

	}
	
}
