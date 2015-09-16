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
		student.setDean("软件学院");
		student.setGrade("2013");
		student.setCreOfOption("10");
		student.setName("关灯荣");
		student.setSex("男");
		student.setAddress("江苏省");
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
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败!");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.ChangePasswd("121250037", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_changePasswd_success)){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.checkUserLog("121250037", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_log_success)){
			System.out.println("登录成功！");
		}else {
			System.out.println("登录失败！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.quitCourse(student, "1003");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_quitCourse_success)){
			System.out.println("退选成功！");
		}else {
			System.out.println("不能删除必修课！");
		}
		Course c=new Course();
		c.setNum("1003");
		c.setTime("周一,一,二 节");
		//对学生选择跨专业课的测试
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.selectCourse("121250037", c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_selectCourseFail_HasSelected)){
			System.out.println("不能重复选择！");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
			String conflictCourses="";
			for(Course course:message.getCourses()){
				conflictCourses+=course.getName()+" ";
			}
			System.out.println("选课成功,但和 "+conflictCourses+"有时间冲突！");
			
		}else {
			System.out.println("选课成功！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		/*
		 * 针对通识课，新生研讨课，和公选课的选课方法
		 */
		try {
			message=studentBLService.selectCourse(student, c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_selectCourseFail_More)){
			System.out.println("超过限制的选课数量！");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_HasSelected)){
			System.out.println("不能重复选择！");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
			String conflictCourses="";
			for(Course course:message.getCourses()){
				conflictCourses+=course.getName()+" ";
			}
			System.out.println("选课成功,但和 "+conflictCourses+"有时间冲突！");
		}else if(message.getMesType().equals(MessageType.stu_selectCourseSuccess)){
			System.out.println("选成功！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showAllCourse("软件学院");
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
			System.out.println("该院系尚未有课程发布！");
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
			System.out.println("尚未有公选课发布！");
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
			System.out.println("尚未有通识课发布！");
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
			System.out.println("尚未有新生研讨课发布！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showCourseDetail("1003");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_show_detailCourInfoSuccess)){
			System.out.println("详细信息："+message.getCour().getDetail());
		}else {
			System.out.println("显示失败！");
		}
		System.out.println("----------------------------------------------------------------------------------");
		/*
		 * 显示跨专业课的测试
		 */
		try {
			message=studentBLService.showKuaZhuanYe("软件学院");
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
			System.out.println("该院系尚未有准入课程发布！");
			
		}
		System.out.println("----------------------------------------------------------------------------------");
		try {
			message=studentBLService.showMyCourseList("121250037");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_noCourse)){
			System.out.println("尚未选择课程！");
		}else if(message.getMesType().equals(MessageType.stu_showMyCourse_fail_timeConflict)){
			String conflictCourses="";
			for(ArrayList<Course> courses:message.getArrayLists()){
				for(Course course:courses){
					conflictCourses+=course.getName()+" ";
					
				}
				System.out.println(conflictCourses+" 存在时间冲突！");
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
			System.out.println("尚未选择课程!");
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
