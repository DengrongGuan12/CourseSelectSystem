package businesslogicservice_driver;

import java.io.IOException;
import java.util.ArrayList;

import common.Course;
import common.Message;
import common.MessageType;
import common.TeachingPro;

import businesslogicservice.DeanTeaBLService;

public class DeanTeaBLService_Driver {
	private DeanTeaBLService deanTeaBLService;
	
	
	public DeanTeaBLService_Driver(DeanTeaBLService deanTeaBLService) {
		this.deanTeaBLService = deanTeaBLService;
	} // end function constructor
	
	public void checkUserLog_Driver() {
		Message message = new Message();
		try {
			message = deanTeaBLService.checkUserLog("001", "12345");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_log_success)) {
			System.out.println("login success!");
		} else if (message.getMesType().equals(MessageType.deanTea_log_fail)){
			System.out.println("login fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end checkUserLog_Driver method
	
	public void ChangePasswd_Driver() {
		Message message = new Message();
		try {
			message = deanTeaBLService.ChangePasswd("001", "123");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_changePasswd_success)) {
			System.out.println("Change password success!");
		} else if (message.getMesType().equals(MessageType.deanTea_changePasswd_fail)) {
			System.out.println("Change password fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end ChangePasswd_Driver method
	
	public void publish_Driver() {
		Message message = new Message();
		Course course = new Course();
		// set course information
		course.setNum("1234");
		course.setName("linux系统操作");
		course.setPlace("仙一103");
		course.setTime("周一,三,四 节");
		course.setTeaNum("002");
		course.setTeaName("张丽娜");
		course.setCredit("3");
		course.setLimit("90");
		course.setIsGongxuan("否");
		course.setIsOptional("否");
		course.setIsTongshi("否");
		course.setIsXinSheng("否");
		course.setIsZhunru("否");
		course.setProperty("通修课");
		
		try {
			message = deanTeaBLService.publish(course);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_pubCourse_success)) {
			System.out.println("Publish success!");
		} else if (message.getMesType().equals(MessageType.deanTea_pubCourse_fail)) {
			System.out.println("Publish fail! Sql error!");
		} else if (message.getMesType().equals(MessageType.deanTea_pubCourse_fail_courseID)) {
			System.out.println("Publish fail! Course ID conflict!");
		} else if (message.getMesType().equals(MessageType.deanTea_pubCourse_fail_teacherCourseConflict)) {
			System.out.println("Publish fail! Teacher information not match!");
		} else if (message.getMesType().equals(MessageType.deanTea_pubCourse_fail_timePlaceConflict)) {
			System.out.println("Publish fail! Time and place conflict!");
		} else {
			System.out.println("Net error!");
		}
	} // end publish_Driver method
	
	public void inputTeachingPro_Driver() {
		Message message = new Message();
		TeachingPro inputTeachingPro = new TeachingPro();
		String dean = "软件学院";
		ArrayList<TeachingPro> arr_TeachingPro = new ArrayList<TeachingPro>();
		// set inputTeachingPro 
		inputTeachingPro.setCourseCredit("4");
		inputTeachingPro.setCourseDean(dean);
		inputTeachingPro.setCourseName("软件工程与计算");
		arr_TeachingPro.add(inputTeachingPro);
		
		try {
			message = deanTeaBLService.inputTeachingPro(arr_TeachingPro, dean);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_inputTeachingPro_success)) {
			System.out.println("Input TeachingPro success!");
		} else if (message.getMesType().equals(MessageType.deanTea_inputTeachingPro_fail)) {
			System.out.println("Input TeachingPro fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end inputTeachingPro_Driver method
	
	public void updateCourse() {
		Message message = new Message();
		Course course = new Course();
		// set course information
		course.setNum("1234");
		course.setName("linux系统操作");
		course.setPlace("仙一104"); // update
		course.setTime("周一,三,四 节");
		course.setTeaNum("002");
		course.setTeaName("张丽娜");
		course.setCredit("4"); // update
		course.setLimit("90");
		course.setIsGongxuan("否");
		course.setIsOptional("否");
		course.setIsTongshi("否");
		course.setIsXinSheng("否");
		course.setIsZhunru("否");
		course.setProperty("通修课");
		
		try {
			message = deanTeaBLService.updateCourse(course);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_updCourseInfo_success)) {
			System.out.println("Update success!");
		} else if (message.getMesType().equals(MessageType.deanTea_updCourseInfo_fail)) {
			System.out.println("Update fail! Sql error!");
		} else if (message.getMesType().equals(MessageType.deanTea_updCourseInfo_fail_CourseID)) {
			System.out.println("Update fail! Course ID conflict!");
		} else if (message.getMesType().equals(MessageType.deanTea_updCourseInfo_fail_teacherCourseConflict)) {
			System.out.println("Update fail! Teacher information not match!");
		} else if (message.getMesType().equals(MessageType.deanTea_updCourseInfo_fail_timePlaceConflict)) {
			System.out.println("Update fail! Time and place conflict!");
		} else {
			System.out.println("Net error!");
		}
	} // end updateCourse_Driver method
	
	public void showCourses_Driver() {
		Message message = new Message();
		String dean = "软件学院";
		
		try {
			message = deanTeaBLService.showCourses(dean);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_showCourse_success)) {
			System.out.println("Show courses success!");
		} else if (message.getMesType().equals(MessageType.deanTea_showCourse_fail)) {
			System.out.println("Show courses fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end showCourses_Driver method
	
	public void showCourseInfo_Driver() {
		Message message = new Message();
		String courseID = "1234";
		
		try {
			message = deanTeaBLService.showCourseInfo(courseID);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_show_detailCourInfo_success)) {
			System.out.println("Show detail course information success!");
		} else if (message.getMesType().equals(MessageType.deanTea_show_detailCourInfo_fail)) {
			System.out.println("Show detail course information fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end showCourseInfo_Driver method
	
	public void showCourseOfStudents_Driver() {
		Message message = new Message();
		String courseID = "1234";
		
		try {
			message = deanTeaBLService.showCourseOfStudents(courseID);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_showCourseOfStudents_success)) {
			System.out.println("Show course of students success!");
		} else if (message.getMesType().equals(MessageType.deanTea_showCourseOfStudents_fail)) {
			System.out.println("Show course of students fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end showCourseOfStudents_Driver method
	
	public void showTeachingPro_Driver() {
		Message message = new Message();
		String dean = "软件学院";
		
		try {
			message = deanTeaBLService.showTeachingPro(dean);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_showTeachingPro_success)) {
			System.out.println("Show teaching program success!");
		} else if (message.getMesType().equals(MessageType.deanTea_showTeachingPro_fail)) {
			System.out.println("Show teaching program fail!");
		} else {
			System.out.println("Net error!");
		}
	} // end showTeachingPro_Driver method
	
	public void showStudentList_Driver() {
		Message message = new Message();
		String dean = "软件学院";
		
		try {
			message = deanTeaBLService.showStudentList(dean);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (message.getMesType().equals(MessageType.deanTea_showStudentList_success)) {
			System.out.println("Show student list success!");
		} else if (message.getMesType().equals(MessageType.deanTea_showStudentList_fail)) {
			System.out.println("Show student list fail!");
		} else {
			System.out.println("Net error!");
		}
	}
	
} // end DeanTeaBLService_Driver
