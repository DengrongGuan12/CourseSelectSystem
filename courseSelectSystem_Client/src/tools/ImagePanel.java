/**
 * ����һ�����Զ�̬����ͼƬ��������JPanel
 */
package tools;


import java.awt.Graphics;
import java.awt.Image;


import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	Image im;
	//���캯��ȥָ����panel�Ĵ�С
	public ImagePanel(Image im){
		this.im=im;
		
		//ϣ�����Ĵ�С����Ӧ
//		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
//		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		//this.setSize(w, h);
		
		
	}
	//��������
	public void paintComponent(Graphics g){
		//����
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
