/**
 * 这是一个可以动态加载图片做背景的JPanel
 */
package tools;


import java.awt.Graphics;
import java.awt.Image;


import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	Image im;
	//构造函数去指定该panel的大小
	public ImagePanel(Image im){
		this.im=im;
		
		//希望他的大小自适应
//		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
//		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//this.setSize(w, h);
		
		
	}
	//画出背景
	public void paintComponent(Graphics g){
		//清屏
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
