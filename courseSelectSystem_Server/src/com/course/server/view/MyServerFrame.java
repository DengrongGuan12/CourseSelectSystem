/**
 * 这是服务器端的控制界面，可以完成启动服务器和关闭服务器
 * 可以管理监控用户
 */
package com.course.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.qq.server.model.MyQqServer;

@SuppressWarnings("serial")
public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyServerFrame();

	}
	public MyServerFrame(){
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1){
			//new MyQqServer();
		}
		
	}
	

}
