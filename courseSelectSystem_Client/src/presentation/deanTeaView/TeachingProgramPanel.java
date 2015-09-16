package presentation.deanTeaView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import presentation.tableModel.TeachingProTableModel;
import tools.ImagePanel;
import tools.MyTools;
import businesslogicservice.DeanTeaBLService;
import common.DeanTea;
import common.Message;
import common.MessageType;
import common.TeachingPro;

public class TeachingProgramPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int TEACHING_PRO_SIZE = 20;
	private static final int TEACHING_PRO_MAX_CREDIT = 5;

	private JPanel panel_TeachingPro;
	private JTable jt_TeachingPro;
	private TeachingProTableModel tm_TeachingPro;
	private JScrollPane jsp_TeachingPro;
	private JLabel lbl_Warning;

	private Message message;
	private DeanTeaBLService deanTeaBLService;
    private DeanTeaWindow deanTeaWindow;
    JDialog suc;
	public void setDeanTeaWindow(DeanTeaWindow deanTeaWindow) {
		this.deanTeaWindow = deanTeaWindow;
	}
	
	public void setDeanTeaBLService(DeanTeaBLService deanTeaBLService) {
		this.deanTeaBLService = deanTeaBLService;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * Create TeachingProgram panel
	 */
	public TeachingProgramPanel(DeanTeaBLService deanTeaBLService, Message message) {
		this.setDeanTeaBLService(deanTeaBLService);
		this.setMessage(message);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		

		panel_TeachingPro = new JPanel(new BorderLayout());
		this.add(panel_TeachingPro, BorderLayout.CENTER);
		
		lbl_Warning = new JLabel("该院系尚未有教学计划发布！");
		lbl_Warning.setFont(MyTools.f6);
		lbl_Warning.setVisible(false);
		panel_TeachingPro.add(lbl_Warning, BorderLayout.NORTH);
		
		JPanel panel_Button = new JPanel();
		FlowLayout fl_panel_Button = (FlowLayout) panel_Button.getLayout();
		fl_panel_Button.setVgap(12);
		fl_panel_Button.setHgap(100);
		this.add(panel_Button, BorderLayout.SOUTH);
		
		JButton button_UpdateTeachingPro = new JButton("更新教学计划");
		button_UpdateTeachingPro.addActionListener(new UpdateTeachingProListener());
		panel_Button.add(button_UpdateTeachingPro);

		JButton button_Refresh = new JButton("刷新");
		button_Refresh.addActionListener(new RefreshButtonListener());
		panel_Button.add(button_Refresh);
		
		refresh();
	} // end function constructor
	
	public void refresh() {
		DeanTea deanTea = (DeanTea) message.getU();
		String dean = deanTea.getDean();
		Message message_ShowTeachingPro = new Message();
		
		try {
			message_ShowTeachingPro = this.deanTeaBLService.showTeachingPro(dean);
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		if (message_ShowTeachingPro.getMesType().equals(MessageType.deanTea_showTeachingPro_fail)) {
			//JOptionPane.showMessageDialog(deanTeaWindow, "尚未有教学计划发布！");
			lbl_Warning.setVisible(true);
			if(jsp_TeachingPro!=null){
				jsp_TeachingPro.setVisible(false);
			}
		} else if (message_ShowTeachingPro.getMesType().equals(MessageType.deanTea_showTeachingPro_success)) {
			lbl_Warning.setVisible(false);
			ArrayList<TeachingPro> arr_TeachingPro = message_ShowTeachingPro.getTeachingPro();
			ArrayList<String> attriOfTp = message_ShowTeachingPro.getAttriOfTP();
			tm_TeachingPro = new TeachingProTableModel(arr_TeachingPro, attriOfTp);
			if (jsp_TeachingPro != null) {
				jt_TeachingPro.setModel(tm_TeachingPro);
				jsp_TeachingPro.setVisible(true);
			} else {
				jt_TeachingPro = new JTable();
				jt_TeachingPro.setModel(tm_TeachingPro);
				jsp_TeachingPro = new JScrollPane(jt_TeachingPro);
				panel_TeachingPro.add(jsp_TeachingPro, BorderLayout.CENTER);
			}
		} else {
			JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
		}
	}
	
	class UpdateTeachingProListener implements ActionListener {
		@SuppressWarnings("serial")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int frameWidth = 800;
			int frameHeight = 450;
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			
			final JFrame frame = new JFrame("更新教学计划");
			frame.setVisible(true);
			frame.setBounds((screenWidth - frameWidth)/2, (screenHeight - frameHeight)/2,
					frameWidth, frameHeight);
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			
			JPanel panel_Warning = new JPanel();
			frame.add(panel_Warning, BorderLayout.NORTH);
			
			JLabel lbl_Warning = new JLabel("尚未有教学计划发布");
			lbl_Warning.setVisible(false);
			panel_Warning.add(lbl_Warning);
			
			// 添加原教学计划表
			DeanTea deanTea = (DeanTea) message.getU();
			final String dean = deanTea.getDean();
			Message message_ShowTeachingPro = new Message();
			String rows[][] = new String[TEACHING_PRO_SIZE][2];
			String[] columnNames = {"课程名称", "学分"};
			try {
				message_ShowTeachingPro = deanTeaWindow.deanTeaBLService.showTeachingPro(dean);
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
			if (message_ShowTeachingPro.getMesType().equals(MessageType.deanTea_showTeachingPro_fail)) {
				//JOptionPane.showMessageDialog(deanTeaWindow, "尚未有教学计划发布！");
				lbl_Warning.setVisible(true);
			} else if (message_ShowTeachingPro.getMesType().equals(MessageType.deanTea_showTeachingPro_success)) {
				lbl_Warning.setVisible(false);
				ArrayList<TeachingPro> arr_TeachingPro = message_ShowTeachingPro.getTeachingPro();
				for (int i = 0; i < arr_TeachingPro.size(); i++) {
					TeachingPro temp_TeachingPro = arr_TeachingPro.get(i);
					rows[i][0] = temp_TeachingPro.getCourseName();
					rows[i][1] = temp_TeachingPro.getCourseCredit();
				}
			} else {
				JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
			}
			final TableModel tableModel = new DefaultTableModel(rows, columnNames) {
				boolean[] columnEditables = new boolean[]{true, true, true};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			JTable table = new JTable(tableModel);
			JScrollPane scorllPane = new JScrollPane(table);
			//scorllPane.setViewportView(table);
			frame.add(scorllPane, BorderLayout.CENTER);

			JPanel panel_Button = new JPanel();
			FlowLayout fl_panel_Button = (FlowLayout) panel_Button.getLayout();
			fl_panel_Button.setHgap(136);
			frame.add(panel_Button, BorderLayout.SOUTH);
			
			JButton button_UpdateTeachingPro = new JButton("更新");
			button_UpdateTeachingPro.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					boolean isEmpty = false;
					boolean isValid = true;
					boolean isNumber = true; // 记录输入学分是否存在问题
					int rowNum = 0; // 记录有问题的行号
					int emptyColumn = 0; // 记录空列的列号
					ArrayList<TeachingPro> arr_TeachingPro = new ArrayList<TeachingPro>();
					for (int row = 0; row < TEACHING_PRO_SIZE; row++) {
						TeachingPro teachingPro = new TeachingPro();
						for (int column = 0; column < 2; column++) {
							String temp = (String) tableModel.getValueAt(row, column);
							if (temp == null) {
								isEmpty = true;
								rowNum = row + 1;
								emptyColumn = column;
								break;
							} else if (temp.equals("")) {
								isEmpty = true;
								rowNum = row + 1;
								emptyColumn = column;
								break;
							} else {
								switch (column) {
								case 0: teachingPro.setCourseName(temp); 
										break;
								case 1: 
									if (checkCredit(temp, row+1)) {
										teachingPro.setCourseCredit(temp); 
										arr_TeachingPro.add(teachingPro);
									} else {
										isNumber = false;
										break;
									}
									break;
								}
							}
						} // end inside for
						if (isNumber) {
							if (isEmpty) {
								if (emptyColumn!=0 || row==0) {
									isValid = false;
								}
								break;
							}
						} else {
							break;
						}
						
					} // end outside for
					if (isNumber) {
						if (!isValid) {
							JOptionPane.showMessageDialog(deanTeaWindow, "第"+rowNum+"或其它行数据为空，请重新输入！");
						} else {
							Message message_UpdateTeachingPro = new Message();
							try {
								message_UpdateTeachingPro = deanTeaWindow.deanTeaBLService.inputTeachingPro(arr_TeachingPro, dean);
							} catch (RemoteException ex) {
								ex.printStackTrace();
							}
							
							if (message_UpdateTeachingPro.getMesType().equals(MessageType.deanTea_inputTeachingPro_fail)) {
								JOptionPane.showMessageDialog(deanTeaWindow, "更新教学计划失败！");
							} else if (message_UpdateTeachingPro.getMesType().equals(MessageType.deanTea_inputTeachingPro_success)) {
								deanTeaWindow.stateNow.setText("更新成功！");
								ImageIcon im=new ImageIcon("images\\DeanTea\\updateSuc.png");
								suc=new JDialog();
								ImagePanel imagePanel=new ImagePanel(im.getImage());
								suc.add(imagePanel);
								suc.setUndecorated(true);
								suc.setSize(150,80);
								suc.setVisible(true);
								int width= Toolkit.getDefaultToolkit().getScreenSize().width;
								int height=Toolkit.getDefaultToolkit().getScreenSize().height;
								suc.setLocation((width-150)/2,(height-80)/2);
								
								UpdateSuccess Success=new UpdateSuccess();
								Success.start();
							} else {
								JOptionPane.showMessageDialog(deanTeaWindow, "你的网络貌似有点问题！");
							}
						}
					}
				}
			});
			panel_Button.add(button_UpdateTeachingPro);
			
			JButton button_Shutdown = new JButton("关闭");
			button_Shutdown.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					frame.dispose();
				}
			});
			panel_Button.add(button_Shutdown);
		}
	} // end UpdateTeachingProListener inside class
	private class UpdateSuccess extends Thread{
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
	class RefreshButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			refresh();
		}
	} // end CancelListener inside class
	
	/**
	 * for update TeachingPro to check whether credit is number or not
	 */
	private boolean checkCredit(String  credit, int row) {
		boolean result = true;
		try {
			int tempCredit = Integer.parseInt(credit);
			if (tempCredit > TEACHING_PRO_MAX_CREDIT) {
				JOptionPane.showMessageDialog(deanTeaWindow, "第" + row + "行输入学分超过预定课程学分数。"
										+"\n课程最大学分数为" + TEACHING_PRO_MAX_CREDIT + "，更新教学计划失败！");
				result = false;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(deanTeaWindow, "第" + row + "行输入学分不是数字，请重新输入！");
			result = false;
		}
		return result;
	} // end checkIsNumber method
	
} // end TeachingProgramPanel class
