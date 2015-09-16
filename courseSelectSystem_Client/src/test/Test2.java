package test;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Test2 extends JFrame{
	public Test2(){
		  setBak();         //调用背景方法
		  Container c = getContentPane(); //获取JFrame面板
		  JPanel jp = new JPanel();      //创建个JPanel
		  jp.setOpaque(false);           //把JPanel设置为透明 这样就不会遮住后面的背景  这样你就能在JPanel随意加组件了
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
