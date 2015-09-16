package presentation.trainingTeaView;

import javax.swing.*;

import presentation.tableModel.*;
import tools.MyTools;
import businesslogicservice.TrainingTeaBLService;
import common.*;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.*;

public class StrategyPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel strategy, buttonPanel;
	JButton jb1, jb2, refresh;
	JLabel jl1;
	StrategyTableModel tm1;
	JTable jt1;
	JScrollPane jsp1;
	StrategyFrame strategyFrame;

	TrainingTeaBLService trainingTeaBLService;
	TrainingTeaWindow trainingTeaWindow;
	public TrainingTeaWindow getTrainingTeaWindow() {
		return trainingTeaWindow;
	}

	public void setTrainingTeaWindow(TrainingTeaWindow trainingTeaWindow) {
		this.trainingTeaWindow = trainingTeaWindow;
	}

	public TrainingTeaBLService getTrainingTeaBLService() {
		return trainingTeaBLService;
	}

	public void setTrainingTeaBLService(
			TrainingTeaBLService TrainingTeaBLService) {
		this.trainingTeaBLService = TrainingTeaBLService;
	}

	Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public StrategyPanel(TrainingTeaBLService TrainingTeaBLService,
			Message message) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		this.setTrainingTeaBLService(TrainingTeaBLService);
		this.setMessage(message);
		this.setLayout(new BorderLayout());
		strategy = new JPanel(new BorderLayout());// 放Table的面板或者Label的面板
		jl1 = new JLabel("尚未发布整体框架策略！");
		jl1.setFont(MyTools.f6);
		strategy.add(jl1, "North");

		buttonPanel = new JPanel(new FlowLayout());
		jb1 = new JButton("输入整体框架策略");
		jb1.setFont(MyTools.f1);
		jb1.addActionListener(this);

		jb2 = new JButton("修改整体框架策略");
		jb2.setFont(MyTools.f1);
		jb2.addActionListener(this);

		refresh = new JButton("刷新");
		refresh.setFont(MyTools.f6);
		refresh.addActionListener(this);

		buttonPanel.add(jb1);
		buttonPanel.add(jb2);
		buttonPanel.add(refresh);

		
		refresh();
		this.add(strategy, "Center");
		this.add(buttonPanel, "South");
	}

	public void refresh() {
		Message message = new Message();
		try {
			message = this.trainingTeaBLService.showFrameStrategy();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.getMesType().equals(MessageType.traTea_showStrategy_fail)) {
			// JOptionPane.showMessageDialog(this, "整体框架策略尚未发布！");
			jl1.setVisible(true);
			jb2.setEnabled(false);
			if(jsp1!=null){
				jsp1.setVisible(false);
			}
		} else if (message.getMesType().equals(
				MessageType.traTea_showStrategy_success)) {
			jl1.setVisible(false);
			ArrayList<Dean> dean = message.getDeans();
			ArrayList<String> attriOfDean = message.getAttriOfDean();
			tm1 = new StrategyTableModel(dean, attriOfDean);
			if(jsp1!=null){
				jt1.setModel(tm1);
				jsp1.setVisible(true);
			}else{
				jt1 = new JTable();
				jt1.setModel(tm1);

				jsp1 = new JScrollPane(jt1);
				strategy.add(jsp1);
			}
			
			if(dean.size()>=15){
				jb1.setEnabled(false);
			}
			
			jb2.setEnabled(true);

		} else {
			JOptionPane.showMessageDialog(this, "你的网络貌似有点问题！");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			try {
				strategyFrame = new StrategyFrame(
						trainingTeaBLService.showFrameStrategy(),
						trainingTeaBLService, jb1.getText());
				strategyFrame.setTrainingTeaWindow(this.trainingTeaWindow);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jb2) {
			try {
				strategyFrame = new StrategyFrame(
						trainingTeaBLService.showFrameStrategy(),
						trainingTeaBLService, jb2.getText());
				strategyFrame.setTrainingTeaWindow(this.trainingTeaWindow);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == refresh) {
			refresh();
		}
	}
}
