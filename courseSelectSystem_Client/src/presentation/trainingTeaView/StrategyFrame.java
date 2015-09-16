package presentation.trainingTeaView;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import tools.ImagePanel;

import businesslogicservice.TrainingTeaBLService;
import common.*;

public class StrategyFrame extends JFrame implements ActionListener,
		ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Message message;
	Message backMessage;// 修改后返回的信息
	TrainingTeaBLService trainingTeaBLService;

	ArrayList<Dean> deans;
	ArrayList<String> nameOfDepartment;// 如果command为输入，则将未输入的院系名称添加入jcombobox
										// 如果command为修改，则将已经输入的院系名称添加入jcombobox
	ArrayList<String> allDepartment;// 存所有院系名称

	String itemDean;// 当前选择的院系名称
	String command;// 判断是输入还是更新整体框架策略

	boolean existBlank;// 判断修改后是否存在信息为空

	JPanel contentPane;
	JPanel selectPanel;
	JPanel mainPanel;
	JPanel buttonPanel;
	JDialog suc;
	JComboBox<String> deanSelect;

	JLabel zhunruLabel, zhunchuLabel, graduateLabel;
	JTextField zhunruTF, zhunchuTF, graduateTF;

	JButton confirmButton;
	TrainingTeaWindow trainingTeaWindow;
	public TrainingTeaWindow getTrainingTeaWindow() {
		return trainingTeaWindow;
	}

	public void setTrainingTeaWindow(TrainingTeaWindow trainingTeaWindow) {
		this.trainingTeaWindow = trainingTeaWindow;
	}

	public StrategyFrame(Message message,
			TrainingTeaBLService trainingTeaBLService, String command) {
		this.message = message;
		this.trainingTeaBLService = trainingTeaBLService;
		this.command = command;
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setTitle("院系整体框架策略变更");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);

		// 取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// 置于屏幕中央
		this.setLocation(screenWidth / 3, screenHeight / 3);

		// 初始化选项框
		selectPanel = new JPanel(new FlowLayout());
		deanSelect = new JComboBox<String>();

		allDepartment = new ArrayList<String>();// 填充所有院系名称
		allDepartment.add("软件学院");
		allDepartment.add("电子科学学院");
		allDepartment.add("大气科学学院");
		allDepartment.add("天文系");
		allDepartment.add("地理与海洋科学学院");
		allDepartment.add("地球科学学院");
		allDepartment.add("计算机科学与技术学院");
		allDepartment.add("哲学系");
		allDepartment.add("文学院");
		allDepartment.add("数学系");
		allDepartment.add("医学院");
		allDepartment.add("历史学院");
		allDepartment.add("物理学院");
		allDepartment.add("政府管理学院");
		allDepartment.add("环境科学学院");

		deans = message.getDeans();
		nameOfDepartment = new ArrayList<String>();
		deanSelect.addItem("请选择院系");
		for (Dean d : deans) {
			nameOfDepartment.add(d.getName());
		}
		if (command.equals("输入整体框架策略")) {
			for (String s : nameOfDepartment) {
				allDepartment.remove(s);
			}
			for (String s : allDepartment) {
				deanSelect.addItem(s);
			}
		} else if (command.equals("修改整体框架策略")) {
			for (String s : nameOfDepartment) {
				deanSelect.addItem(s);
			}
		} else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
		deanSelect.setSelectedItem("请选择院系");
		deanSelect.addItemListener(this);
		selectPanel.add(deanSelect);

		mainPanel = new JPanel(new FlowLayout());
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		zhunruLabel = new JLabel("准入学分数：");
		GridBagConstraints gbc_zhunruLabel = new GridBagConstraints();
		gbc_zhunruLabel.weightx = 0.2;
		gbc_zhunruLabel.weighty = 0.0;
		gbc_zhunruLabel.ipady = 10;
		gbc_zhunruLabel.ipadx = 10;
		gbc_zhunruLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zhunruLabel.anchor = GridBagConstraints.EAST;
		gbc_zhunruLabel.gridx = 1;
		gbc_zhunruLabel.gridy = 1;
		mainPanel.add(zhunruLabel, gbc_zhunruLabel);

		zhunchuLabel = new JLabel("准出学分数：");
		GridBagConstraints gbc_zhunchuLabel = new GridBagConstraints();
		gbc_zhunchuLabel.weighty = 0.0;
		gbc_zhunchuLabel.weightx = 0.2;
		gbc_zhunchuLabel.ipady = 10;
		gbc_zhunchuLabel.ipadx = 10;
		gbc_zhunchuLabel.anchor = GridBagConstraints.EAST;
		gbc_zhunchuLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zhunchuLabel.gridx = 1;
		gbc_zhunchuLabel.gridy = 3;

		graduateLabel = new JLabel("毕业学分总数：");
		GridBagConstraints gbc_graduateLabel = new GridBagConstraints();
		gbc_graduateLabel.weighty = 0.0;
		gbc_graduateLabel.weightx = 0.2;
		gbc_graduateLabel.ipady = 10;
		gbc_graduateLabel.ipadx = 10;
		gbc_graduateLabel.anchor = GridBagConstraints.EAST;
		gbc_graduateLabel.insets = new Insets(0, 0, 0, 5);
		gbc_graduateLabel.gridx = 1;
		gbc_graduateLabel.gridy = 5;
		mainPanel.add(graduateLabel, gbc_graduateLabel);

		zhunruTF = new JTextField();
		GridBagConstraints gbc_zhunruTF = new GridBagConstraints();
		gbc_zhunruTF.anchor = GridBagConstraints.WEST;
		gbc_zhunruTF.insets = new Insets(0, 0, 5, 0);
		gbc_zhunruTF.gridx = 2;
		gbc_zhunruTF.gridy = 1;
		mainPanel.add(zhunruTF, gbc_zhunruTF);
		zhunruTF.setColumns(20);

		zhunchuTF = new JTextField();
		GridBagConstraints gbc_zhunchuTF = new GridBagConstraints();
		gbc_zhunchuTF.anchor = GridBagConstraints.WEST;
		gbc_zhunchuTF.insets = new Insets(0, 0, 5, 0);
		gbc_zhunchuTF.gridx = 2;
		gbc_zhunchuTF.gridy = 3;
		mainPanel.add(zhunchuTF, gbc_zhunchuTF);
		zhunchuTF.setColumns(20);

		graduateTF = new JTextField();
		GridBagConstraints gbc_graduateTF = new GridBagConstraints();
		gbc_graduateTF.anchor = GridBagConstraints.WEST;
		gbc_graduateTF.gridx = 2;
		gbc_graduateTF.gridy = 5;
		mainPanel.add(graduateTF, gbc_graduateTF);
		graduateTF.setColumns(20);

		mainPanel.add(zhunruLabel, gbc_zhunruLabel);
		mainPanel.add(zhunruTF, gbc_zhunruTF);
		mainPanel.add(zhunchuLabel, gbc_zhunchuLabel);
		mainPanel.add(graduateLabel, gbc_graduateLabel);
		mainPanel.add(graduateTF, gbc_graduateTF);

		buttonPanel = new JPanel(new FlowLayout());
		confirmButton = new JButton("确认");
		confirmButton.addActionListener(this);
		buttonPanel.add(confirmButton);

		contentPane.add(selectPanel, "North");
		contentPane.add(mainPanel, "Center");
		contentPane.add(buttonPanel, "South");
		this.setVisible(true);

	}

	public void refresh() {
		if (command.equals("输入整体框架策略")
				|| deanSelect.getSelectedItem().toString().equals("请选择院系")) {
			zhunruTF.setText("");
			zhunchuTF.setText("");
			graduateTF.setText("");
		} else {
			itemDean = deanSelect.getSelectedItem().toString();
			deans = message.getDeans();
			for (Dean d : deans) {
				if (d.getName().equals(itemDean)) {
					zhunruTF.setText(d.getZhunru());
					zhunchuTF.setText(d.getZhunchu());
					graduateTF.setText(d.getGraduate());
					break;
				}
			}
		}
	}

	public boolean checkBlank() {
		boolean existBlank;
		if (zhunruTF.getText().length() > 0 && zhunchuTF.getText().length() > 0
				&& graduateTF.getText().length() > 0) {
			existBlank = false;
		} else {
			existBlank = true;
		}
		return existBlank;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// 选择不同院系，若该院系存在框架策略则显示，若不存在则留空
		refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 确定之后对原整体框架策略进行修改
		backMessage = new Message();
		existBlank = checkBlank();
		if (e.getSource() == confirmButton) {
			if (deanSelect.getSelectedItem().toString().equals("请选择院系")) {
				JOptionPane.showMessageDialog(this, "请选择院系！");
			} else if (existBlank == true) {
				JOptionPane.showMessageDialog(this, "整体框架策略信息不能为空！");
			} else {
				Dean dean = new Dean();
				dean.setName(deanSelect.getSelectedItem().toString());
				dean.setZhunru(zhunruTF.getText());
				dean.setZhunchu(zhunchuTF.getText());
				dean.setGraduate(graduateTF.getText());
				try {
					if (command.equals("修改整体框架策略")) {
						backMessage = trainingTeaBLService
								.updateFrameStrategy(dean);
					} else {
						backMessage = trainingTeaBLService
								.inputFrameStrategy(dean);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (backMessage.getMesType().equals(
						MessageType.traTea_inputStrategy_fail)
						|| backMessage.getMesType().equals(
								MessageType.traTea_updStrategy_fail)) {
					JOptionPane.showMessageDialog(this, "更改失败！");
				} else if (backMessage.getMesType().equals(
						MessageType.traTea_inputStrategy_success)
						|| backMessage.getMesType().equals(
								MessageType.traTea_updStrategy_success)) {
					this.trainingTeaWindow.stateNow.setText("更改成功！");
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
					
					ChangeInfoSuccess Success=new ChangeInfoSuccess();
					Success.start();
				} else {
					JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
				}
				this.setVisible(false);
			}

		}
	}
	private class ChangeInfoSuccess extends Thread{
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

}
