/**
 * 用户登录的闪屏效果
 */

package presentation;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JWindow;


@SuppressWarnings("serial")
public class Index extends JWindow implements Runnable{

	paint p=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Index index=new Index();
		Thread t=new Thread(index);
		t.start();
		

	}
	public Index(){
		p=new paint();
		this.add(p);
		
		this.setSize(400, 250);
		//确定JWindow的初始位置
		int width= Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,height/2-150 );
		this.setVisible(true);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			//根据计算我们等待闪屏效果结束后再跳转到用户登录界面
			try {
				Thread.sleep(30*400);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			//跳转到登录
			//System.out.println("闪屏结束");
			new UserLogin();
			//同时让他消失
			this.dispose();
			break;
			
		}
		
	}

}

//开发一个闪屏类
@SuppressWarnings("serial")
class paint extends JPanel implements Runnable{
	Thread t;
	int x=10;
	int i=0,j=40,u=10;
	String gg[]={"系","统","正","在","加","载","请","稍","侯"};
	int k=0,tt=0;
	//String shi[] ={};
	Font f=new Font("隶书",Font.PLAIN,18);
	
	boolean ifok=true;
	int width=180;
	int height=0;
	int dian=0;
	
	paint(){
		t=new Thread(this);
		t.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(x<=380){
				repaint();
			}
			try {
				Thread.sleep(70);
				i++;
				j=j-6;
				u=u+10;
				if(tt==3){
					width=width-20;
				}
				if(i==4){
					tt++;
					if(ifok&&x>120+k*20){
						k++;
					}
					if(k>=gg.length-1){
						ifok=false;
					}
					x=x+10;
					i=0;
					j=40;
					u=10;
					dian++;
					if(dian>3){
						dian=0;
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("线程中断");
			}
			
		}
		
	}
	public void paintComponent(Graphics g){
		//super.paintComponent(g);
		Image image;
		image=Toolkit.getDefaultToolkit().getImage("images\\jiazai.jpg");
		g.drawImage(image, 0, 0, this.getWidth(), 200, this);
		
		int r=(int )(Math.random()*255);
		int b=(int)(Math.random()*255);
		int y=(int)(Math.random()*255);
		
		g.setColor(new Color(253,250,250));
		g.fillRect(x, 210, 390-x, 30);
		g.setColor(new Color(253,250,250));
		if(i>1){
			g.fillRect(x, 225-(j+20)/2, 10, j+20);
			
		}
		if(j>25){
			g.setColor(new Color(r,b,y));
		}else {
			g.setColor(new Color(123, 194, 252));
		}
		g.fillRect(x, 225-j/2, 10, j);
		g.setColor(new Color(123,194,252));
		g.drawRect(10, 210, 380, 30);
		
		if(x<120){
			for(int l=0;l<gg.length;l++){
				g.setColor(new Color(0,0,0));
				g.drawString(gg[l],120+l*20,230);
				
			}
			for(int l=0;l<dian;l++){
				g.setColor(new Color(0,0,0));
				g.drawString("*", 300+l*10, 235);
			}
			g.drawString("*", 300+10*dian, 235);
		}else {
			g.setColor(new Color(23,23,230));
			g.drawString(gg[k], 120+k*20, 230);
			for(int l=k+1;l<gg.length;l++){
				g.setColor(new Color(0,0,0));
				g.drawString(gg[l], 120+l*20,230);
			}
			if(x>300+dian*10){
				g.setColor(new Color(23,23,230));
			}
			for(int l=0;l<dian;l++){
				g.drawString("*", 300+l*10, 235);
				
			}
			g.drawString("*", 300+10*dian, 235);
			
		}
		
		
		//逐字写诗
//		if(tt<3){
//			for(int rr=0;rr<=tt;rr++){
//				g.setColor(new Color(r,b,y));
//				g.drawString(shi[rr], 180, 60+rr*20);
//			}
//			g.drawString(shi[tt], 180, 60+tt*20);
//			
//		}
//		if(tt>=3&&tt<7){
//			g.setColor(new Color(230,0,0));
//			for(int rr=0;rr<3;rr++){
//				g.drawString(shi[rr], 180, 60+rr*20);
//			}
//			g.setColor(new Color(r,b,y));
//			//少掉部分代码
//			
//		}
//		if(tt>=7&&tt<13){
//			g.setColor(new Color(230,0,0));
//			for(int rr=0;rr<3;rr++){
//				g.drawString(shi[rr], 180, 60+rr*20);
//			}
//			for(int rr=3;rr<=7;rr++){
//				g.drawString(shi[rr], 150, rr*20-20);
//			}
//			//少掉部分代码
//			
//		}
//		if(tt>=13&&tt<18){
//			g.setColor(new Color(230,0,0));
//		}
		
		
		
		
	}
	
}

