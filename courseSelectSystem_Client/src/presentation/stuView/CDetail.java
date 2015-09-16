package presentation.stuView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tools.MyTools;


public class CDetail extends JDialog implements ActionListener{


	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb;

	// owner它的父窗口
	// title 窗口名
	// modal指定是模式窗口还是非模式窗口
	public CDetail(Frame owner,String title,boolean modal,String detail){
		super(owner,title,modal);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		jp1=new JPanel(new BorderLayout());
		jta=new JTextArea();
		jta.setText(detail);
		jta.setFont(MyTools.f7);
		jta.setBackground(Color.LIGHT_GRAY);
		jta.setForeground(Color.BLUE);
		
		jta.setEditable(false);//不可编辑
		jta.setLineWrap(true);//自动换行
		jta.setWrapStyleWord(true);//换行不换字
		jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jp1.add(jsp,"Center");
		
		jp2=new JPanel(new FlowLayout());
		jb=new JButton("确定");
		jb.setFont(MyTools.f2);
		jb.addActionListener(this);
		jp2.add(jb);
		
		this.add(jp1,"Center");
		this.add(jp2,"South");
		
		this.setSize(250,250);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}
	

}
