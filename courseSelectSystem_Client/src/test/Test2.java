package test;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Test2 extends JFrame{
	public Test2(){
		  setBak();         //���ñ�������
		  Container c = getContentPane(); //��ȡJFrame���
		  JPanel jp = new JPanel();      //������JPanel
		  jp.setOpaque(false);           //��JPanel����Ϊ͸�� �����Ͳ�����ס����ı���  �����������JPanel����������
		  c.add(jp);                   
		  setSize(1200, 800);
		  setVisible(true);
	}

	public void setBak(){
		   ((JPanel)this.getContentPane()).setOpaque(false);
		   ImageIcon img = new ImageIcon("images://login.png"); 
		   JLabel background = new JLabel(img);
		   this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		   background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test2();

	}

}
