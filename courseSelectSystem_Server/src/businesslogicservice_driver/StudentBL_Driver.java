package businesslogicservice_driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import common.Course;
import common.Message;
import common.MessageType;
import common.Student;


import businesslogicservice.StudentBLService;

public class StudentBL_Driver {
	public void drive(StudentBLService studentBLService){
		Message message=new Message();
		Student student=new Student();
		student.setNum("121250037");
		student.setDean("���ѧԺ");
		student.setGrade("2013");
		student.setCreOfOption("10");
		student.setName("�ص���");
		student.setSex("��");
		student.setAddress("����ʡ");
		student.setHomeNumber("12345678");
		student.setPhoneNumber("12234234");
		student.setEmail("gdr12@software.nju.edu.cn");
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.changeMyInfo(student);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_changeMyInfo_suc)){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ��!");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.ChangePasswd("121250037", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_changePasswd_success)){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.checkUserLog("121250037", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_log_success)){
			System.out.println("��¼�ɹ���");
		}else {
			System.out.println("��¼ʧ�ܣ�");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.quitCourse(student, "1003");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_quitCourse_success)){
			System.out.println("��ѡ�ɹ���");
		}else {
			System.out.println("����ɾ�����޿Σ�");
		}
		Course c=new Course();
		c.setNum("1003");
		c.setTime("��һ,һ,�� ��");
		//��ѧ��ѡ���רҵ�εĲ���
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.selectCourse("121250037", c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_selectCourseFail_HasSelected)){
			System.out.println("�����ظ�ѡ��");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
			String conflictCourses="";
			for(Course course:message.getCourses()){
				conflictCourses+=course.getName()+" ";
			}
			System.out.println("ѡ�γɹ�,���� "+conflictCourses+"��ʱ���ͻ��");
			
		}else {
			System.out.println("ѡ�γɹ���");
		}
		System.out.println("----------------------------------------------------------------------------------");
		/*
		 * ���ͨʶ�Σ��������ֿΣ��͹�ѡ�ε�ѡ�η���
		 */
		try {
			message=studentBLService.selectCourse(student, c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_selectCourseFail_More)){
			System.out.println("�������Ƶ�ѡ��������");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_HasSelected)){
			System.out.println("�����ظ�ѡ��");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
			String conflictCourses="";
			for(Course course:message.getCourses()){
				conflictCourses+=course.getName()+" ";
			}
			System.out.println("ѡ�γɹ�,���� "+conflictCourses+"��ʱ���ͻ��");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseSuccess)){
			System.out.println("ѡ�ɹ���");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showAllCourse("���ѧԺ");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			ArrayList<Course> courses=new ArrayList<Course>();
			for(Course course:courses){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getProperty());
				
			}
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			System.out.println("��Ժϵ��δ�пγ̷�����");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showAllGongxuan();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			ArrayList<Course> courses=new ArrayList<Course>();
			for(Course course:courses){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getLimit());
				
			}
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			System.out.println("��δ�й�ѡ�η�����");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showAllTongshi();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			ArrayList<Course> courses=new ArrayList<Course>();
			for(Course course:courses){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getLimit());
				
			}
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			System.out.println("��δ��ͨʶ�η�����");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showAllXinsheng();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			ArrayList<Course> courses=new ArrayList<Course>();
			for(Course course:courses){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getLimit());
				
			}
			
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			System.out.println("��δ���������ֿη�����");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showCourseDetail("1003");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_detailCourInfoSuccess)){
			System.out.println("��ϸ��Ϣ��"+message.getCour().getDetail());
		}else {
			System.out.println("��ʾʧ�ܣ�");
		}
		System.out.println("----------------------------------------------------------------------------------");
		/*
		 * ��ʾ��רҵ�εĲ���
		 */
		try {
			message=studentBLService.showKuaZhuanYe("���ѧԺ");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_allCourseSuccess)){
			ArrayList<Course> courses=new ArrayList<Course>();
			for(Course course:courses){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean());
				
			}
		}else if(message.getMesType().equals(MessageType.stu_show_allCourseFail)){
			System.out.println("��Ժϵ��δ��׼��γ̷�����");
			
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showMyCourseList("121250037");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			System.out.println("��δѡ��γ̣�");
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_timeConflict)){
			String conflictCourses="";
			for(ArrayList<Course> courses:message.getArrayLists()){
				for(Course course:courses){
					conflictCourses+=course.getName()+" ";
					
				}
				System.out.println(conflictCourses+" ����ʱ���ͻ��");
				conflictCourses="";
			}
			for(Course course:message.getCourses()){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getProperty());
			}
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_success)){
			for(Course course:message.getCourses()){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getProperty());
			}
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showMyScore("121250037");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			System.out.println("��δѡ��γ�!");
		}else{
			for(Course course:message.getCourses()){
				System.out.println(course.getNum()+" "+course.getName()+" "+course.getPlace()
						+" "+course.getTime()+" "+course.getTeaName()+" "+course.getCredit()+" "
						+course.getDean()+" "+course.getProperty()+" "+course.getScore());
			}
		}
		System.out.println("----------------------------------------------------------------------------------");
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
