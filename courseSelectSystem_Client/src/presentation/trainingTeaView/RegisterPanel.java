package presentation.trainingTeaView;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

import tools.ImagePanel;

import common.*;
import businesslogicservice.TrainingTeaBLService;

public class RegisterPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TrainingTeaBLService trainingTeaBLService;
	Message message;

	String sex, dean;

	boolean existBlank;
	 JDialog suc;
	JPanel userTypeSelectPanel;
	JPanel mainPanel;
	JPanel buttonPanel;

	JRadioButton maleButton, femaleButton;
	ButtonGroup bg;

	JComboBox<String> deanSelectJCB;
	JComboBox<String> userTypeSelectJCB;

	JLabel numLabel, nameLabel, passwdLabel;
	JTextField numTF, nameTF, passwdTF;

	JButton registerButton;
	TrainingTeaWindow trainingTeaWindow;
	

	public TrainingTeaWindow getTrainingTeaWindow() {
		return trainingTeaWindow;
	}

	public void setTrainingTeaWindow(TrainingTeaWindow trainingTeaWindow) {
		this.trainingTeaWindow = trainingTeaWindow;
	}

	public RegisterPanel(TrainingTeaBLService trainingTeaBLService) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.trainingTeaBLService = trainingTeaBLService;

		this.setLayout(new BorderLayout());

		userTypeSelectPanel = new JPanel(new FlowLayout());
		userTypeSelectJCB = new JComboBox<String>();
		userTypeSelectJCB.setFont(new Font("宋体", Font.PLAIN, 20));
		String[] userType = new String[2];
		userType[0] = "学生";
		userType[1] = "教师";
		for (int i = 0; i < 2; i++) {
			userTypeSelectJCB.addItem(userType[i]);
		}
		userTypeSelectJCB.setSelectedItem("请选择用户类型");
		userTypeSelectPanel.add(userTypeSelectJCB);

		mainPanel = new JPanel();
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		numLabel = new JLabel("用户名");
		numLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		GridBagConstraints gbc_numLabel = new GridBagConstraints();
		gbc_numLabel.weighty = 0.1;
		gbc_numLabel.anchor = GridBagConstraints.EAST;
		gbc_numLabel.ipadx = 20;
		gbc_numLabel.ipady = 30;
		gbc_numLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numLabel.gridx = 17;
		gbc_numLabel.gridy = 3;
		mainPanel.add(numLabel, gbc_numLabel);

		numTF = new JTextField();
		numTF.setFont(new Font("宋体", Font.PLAIN, 30));
		GridBagConstraints gbc_numTF = new GridBagConstraints();
		gbc_numTF.insets = new Insets(0, 0, 5, 5);
		gbc_numTF.anchor = GridBagConstraints.WEST;
		gbc_numTF.gridx = 19;
		gbc_numTF.gridy = 3;
		mainPanel.add(numTF, gbc_numTF);
		numTF.setColumns(30);

		nameLabel = new JLabel("姓名");
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.weighty = 0.1;
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.ipady = 30;
		gbc_nameLabel.ipadx = 20;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 17;
		gbc_nameLabel.gridy = 5;
		mainPanel.add(nameLabel, gbc_nameLabel);

		nameTF = new JTextField();
		nameTF.setFont(new Font("宋体", Font.PLAIN, 30));
		GridBagConstraints gbc_nameTF = new GridBagConstraints();
		gbc_nameTF.insets = new Insets(0, 0, 5, 5);
		gbc_nameTF.anchor = GridBagConstraints.WEST;
		gbc_nameTF.gridx = 19;
		gbc_nameTF.gridy = 5;
		mainPanel.add(nameTF, gbc_nameTF);
		nameTF.setColumns(30);

		passwdLabel = new JLabel("密码");
		passwdLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		GridBagConstraints gbc_passwdLabel = new GridBagConstraints();
		gbc_passwdLabel.weighty = 0.1;
		gbc_passwdLabel.anchor = GridBagConstraints.EAST;
		gbc_passwdLabel.ipady = 30;
		gbc_passwdLabel.ipadx = 20;
		gbc_passwdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwdLabel.gridx = 17;
		gbc_passwdLabel.gridy = 7;
		mainPanel.add(passwdLabel, gbc_passwdLabel);

		passwdTF = new JTextField();
		passwdTF.setFont(new Font("宋体", Font.PLAIN, 30));
		passwdTF.setColumns(30);
		GridBagConstraints gbc_passwdTF = new GridBagConstraints();
		gbc_passwdTF.insets = new Insets(0, 0, 5, 5);
		gbc_passwdTF.anchor = GridBagConstraints.WEST;
		gbc_passwdTF.gridx = 19;
		gbc_passwdTF.gridy = 7;
		mainPanel.add(passwdTF, gbc_passwdTF);

		maleButton = new JRadioButton("男");
		maleButton.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_maleButton = new GridBagConstraints();
		gbc_maleButton.weighty = 0.1;
		gbc_maleButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_maleButton.insets = new Insets(0, 0, 5, 40);
		gbc_maleButton.gridx = 17;
		gbc_maleButton.gridy = 9;
		mainPanel.add(maleButton, gbc_maleButton);

		femaleButton = new JRadioButton("女");
		femaleButton.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_femaleButton = new GridBagConstraints();
		gbc_femaleButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_femaleButton.insets = new Insets(0, 0, 5, 5);
		gbc_femaleButton.gridx = 18;
		gbc_femaleButton.gridy = 9;
		mainPanel.add(femaleButton, gbc_femaleButton);

		deanSelectJCB = new JComboBox<String>();
		deanSelectJCB.setFont(new Font("宋体", Font.PLAIN, 20));
		String[] deans = new String[15];
		deans[0] = "软件学院";
		deans[1] = "电子科学学院";
		deans[2] = "大气科学学院";
		deans[3] = "天文系";
		deans[4] = "地理与海洋科学学院";
		deans[5] = "地球科学学院";
		deans[6] = "计算机科学与技术学院";
		deans[7] = "哲学系";
		deans[8] = "文学院";
		deans[9] = "数学系";
		deans[10] = "医学院";
		deans[11] = "历史学院";
		deans[12] = "物理学院";
		deans[13] = "政府管理学院";
		deans[14] = "环境科学学院";
		for (int i = 0; i < 15; i++) {
			deanSelectJCB.addItem(deans[i]);
		}
		deanSelectJCB.setSelectedItem("软件学院");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.weighty = 0.9;
		gbc_comboBox.insets = new Insets(0, 0, 0, 300);
		gbc_comboBox.gridx = 19;
		gbc_comboBox.gridy = 9;
		mainPanel.add(deanSelectJCB, gbc_comboBox);

		bg = new ButtonGroup();
		bg.add(femaleButton);
		bg.add(maleButton);
		maleButton.setSelected(true);

		buttonPanel = new JPanel(new FlowLayout());
		registerButton = new JButton("注册");
		registerButton.setFont(new Font("宋体", Font.PLAIN, 20));
		registerButton.addActionListener(this);
		buttonPanel.add(registerButton);

		this.add(userTypeSelectPanel, "North");
		this.add(mainPanel, "Center");
		this.add(buttonPanel, "South");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取用户类型等信息进行注册
		if (numTF.getText().length() > 0 && nameTF.getText().length() > 0
				&& passwdTF.getText().length() > 0) {
			existBlank = false;
		} else {
			existBlank = true;
		}
		if (e.getSource() == registerButton) {
			if (userTypeSelectJCB.getSelectedItem().toString()
					.equals("请选择用户类型")) {
				JOptionPane.showMessageDialog(this, "请选择用户类型！");
			} else if (existBlank == true) {
				JOptionPane.showMessageDialog(this, "用户信息不完整！");
			} else {
				if (femaleButton.isSelected() == true) {
					sex = "女";
				} else {
					sex = "男";
				}
				char[]namecs=nameTF.getText().toCharArray();
				char[]numcs=numTF.getText().toCharArray();
				char[]passcs=passwdTF.getText().toCharArray();
				if(namecs.length>40){
					JOptionPane.showMessageDialog(this, "姓名过长，注册失败！");
					this.trainingTeaWindow.stateNow.setText("注册失败！");
				}else if(numcs.length>100){
					JOptionPane.showMessageDialog(this, "用户名过长，注册失败！");
					this.trainingTeaWindow.stateNow.setText("注册失败！");
				}else if(passcs.length>100){
					JOptionPane.showMessageDialog(this, "密码过长，注册失败！");
					this.trainingTeaWindow.stateNow.setText("注册失败！");
				}else {
					try {
						message = trainingTeaBLService.register(userTypeSelectJCB
								.getSelectedItem().toString(), nameTF.getText(),
								numTF.getText(), passwdTF.getText(), sex,
								deanSelectJCB.getSelectedItem().toString());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (message.getMesType().equals(
							MessageType.traTea_registerUser_fail)) {
						JOptionPane.showMessageDialog(this, "用户名已存在，注册失败！");
						this.trainingTeaWindow.stateNow.setText("注册失败！");
					} else if (message.getMesType().equals(MessageType.traTea_registerUser_success)){
							this.trainingTeaWindow.stateNow.setText("注册成功！");
							ImageIcon im=new ImageIcon("images\\TrainingTea\\regSuc.png");
							suc=new JDialog();
							ImagePanel imagePanel=new ImagePanel(im.getImage());
							suc.add(imagePanel);
							suc.setUndecorated(true);
							suc.setSize(150,80);
							suc.setVisible(true);
							int width= Toolkit.getDefaultToolkit().getScreenSize().width;
							int height=Toolkit.getDefaultToolkit().getScreenSize().height;
							suc.setLocation((width-150)/2,(height-80)/2);
							
							RegisterSuccess Success=new RegisterSuccess();
							Success.start();
					} else {
						JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
					}
				}
				
			}
		}
	}
	private class RegisterSuccess extends Thread{
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
