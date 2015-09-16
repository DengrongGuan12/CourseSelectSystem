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

	// owner���ĸ�����
	// title ������
	// modalָ����ģʽ���ڻ��Ƿ�ģʽ����
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
		
		jta.setEditable(false);//���ɱ༭
		jta.setLineWrap(true);//�Զ�����
		jta.setWrapStyleWord(true);//���в�����
		jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jp1.add(jsp,"Center");
		
		jp2=new JPanel(new FlowLayout());
		jb=new JButton("ȷ��");
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
