package presentation.stuView;

import java.awt.*;

import javax.swing.*;
import tools.MyTools;

import common.Message;
import common.Student;

import businesslogicservice.StudentBLService;

public class DynamicInfoPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StudentBLService studentBLService;
	StuWindow stuWindow;
	Message message;
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public StudentBLService getStudentBLService() {
		return studentBLService;
	}
	public void setStudentBLService(StudentBLService studentBLService) {
		this.studentBLService = studentBLService;
	}
	public StuWindow getStuWindow() {
		return stuWindow;
	}
	public void setStuWindow(StuWindow stuWindow) {
		this.stuWindow = stuWindow;
	}
	
	public DynamicInfoPanel(StudentBLService studentBLService,Message message){
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		this.setStudentBLService(studentBLService);
		this.setMessage(message);
		Student student=(Student)message.getU();

		setLayout(new GridLayout(4, 4, 12, 110));
		
		JLabel nameOfSecPro = new JLabel("第二专业名称：");
		nameOfSecPro.setFont(MyTools.f5);
		nameOfSecPro.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameOfSecPro);
		
		JLabel lbl1 = new JLabel(student.getNameOfSecPro());
		add(lbl1);
		
		JLabel creditOfSecPro = new JLabel("该专业准入学分：");
		creditOfSecPro.setFont(MyTools.f5);
		creditOfSecPro.setHorizontalAlignment(SwingConstants.CENTER);
		add(creditOfSecPro);
		
		JLabel lbl2 = new JLabel(student.getCreOfSecPro());
		add(lbl2);
		
		JLabel creditOfZhunchu = new JLabel("本院准出学分：");
		creditOfZhunchu.setFont(MyTools.f5);
		creditOfZhunchu.setHorizontalAlignment(SwingConstants.CENTER);
		add(creditOfZhunchu);
		
		JLabel lbl3 = new JLabel(student.getCreOfPrePro());
		add(lbl3);
		
		JLabel creditOfTongshi = new JLabel("通识课学分：");
		creditOfTongshi.setFont(MyTools.f5);
		creditOfTongshi.setHorizontalAlignment(SwingConstants.CENTER);
		add(creditOfTongshi);
		
		JLabel lbl4 = new JLabel(student.getCreOfOption());
		add(lbl4);
		
		JLabel zongXueFen = new JLabel("毕业总学分：");
		zongXueFen.setFont(MyTools.f5);
		zongXueFen.setHorizontalAlignment(SwingConstants.CENTER);
		add(zongXueFen);
		
		JLabel lbl5 = new JLabel(student.getCreOfBiye());
		add(lbl5);
		
		JLabel lbl6 = new JLabel("");
		lbl6.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl6);
		
		JLabel lbl7 = new JLabel("");
		add(lbl7);
		
		JLabel lbl8 = new JLabel("");
		lbl8.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl8);

	

	}
}
