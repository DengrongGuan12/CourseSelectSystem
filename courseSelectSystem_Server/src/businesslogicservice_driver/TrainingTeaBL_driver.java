package businesslogicservice_driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import common.*;
import businesslogic.TrainingTeaBL;

public class TrainingTeaBL_driver {
	TrainingTeaBL trainingTeaBL;

	public TrainingTeaBL_driver(TrainingTeaBL trainingTeaBL) {
		this.trainingTeaBL = trainingTeaBL;
	}

	public void showFrameStrategy_drive() {
		Message message = new Message();
		message = trainingTeaBL.showFrameStrategy();
		if (message.getMesType()
				.equals(MessageType.traTea_showStrategy_success)) {
			System.out.println("Get strategy success:");
			ArrayList<Dean> strategy = new ArrayList<Dean>();
			strategy = message.getDeans();
			for (Dean d : strategy) {
				System.out.println(d.getName() + " " + d.getZhunru() + " "
						+ d.getZhunchu() + " " + d.getGraduate());
			}
		} else if (message.getMesType().equals(
				MessageType.traTea_showStrategy_fail)) {
			System.out.println("Strategy does not exist!");
		} else {
			System.out.println("Network connect refused!");
		}
	}

	public void showTeachingPro_drive() {
		Message message = new Message();
		message = trainingTeaBL.showTeachingPro("软件学院");
		if (message.getMesType().equals(
				MessageType.traTea_showTeachingPro_success)) {
			System.out.println("Get teachingProgram success!");
			ArrayList<TeachingPro> teachingPro = new ArrayList<TeachingPro>();
			teachingPro = message.getTeachingPro();
			for (TeachingPro tp : teachingPro) {
				System.out.println(tp.getCourseName() + " "
						+ tp.getCourseDean() + " " + tp.getCourseCredit());
			}
		}else if(message.getMesType().equals(MessageType.traTea_showTeachingPro_fail)){
			System.out.println("TeachingProgram does not exist!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void showTeacherList_drive(){
		Message message = new Message();
		message = trainingTeaBL.showTeacherList();
		if(message.getMesType().equals(MessageType.traTea_showTeacherList_success)){
			System.out.println("Get teacherList success!");
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			teachers = message.getTeachers();
			for(Teacher t:teachers){
				System.out.println(t.getNum()+" "+t.getName()+" "+t.getSex());
			}
		}else if(message.getMesType().equals(MessageType.traTea_showTeacherList_fail)){
			System.out.println("Teacher does not exist!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void showStudentList_drive(){
		Message message = new Message();
		message = trainingTeaBL.showStudentList();
		if(message.getMesType().equals(MessageType.traTea_showStudentList_success)){
			System.out.println("Get studentList success!");
			ArrayList<Student> students = new ArrayList<Student>();
			students = message.getStudents();
			for(Student s:students){
				System.out.println(s.getNum()+" "+s.getName());
			}
		}else if(message.getMesType().equals(MessageType.traTea_showStudentList_fail)){
			System.out.println("Student does not exist!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void showCourseList_drive(){
		Message message = new Message();
		message = trainingTeaBL.showCourseList("软件学院");
		if(message.getMesType().equals(MessageType.traTea_showCourseList_success)){
			System.out.println("Get courseList success!");
			ArrayList<Course> courses = new ArrayList<Course>();
			courses = message.getCourses();
			for(Course c:courses){
				System.out.println(c.getNum()+" "+c.getName());
			}
		}else if(message.getMesType().equals(MessageType.traTea_showCourseList_fail)){
			System.out.println("Course does not exist!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void register_drive(){
		Message message = new Message();
		message = trainingTeaBL.register("学生", "gdr", "gdr12", "12345","男","软件学院");
		if(message.getMesType().equals(MessageType.traTea_registerUser_success)){
			System.out.println("Register success!");
		}else if(message.getMesType().equals(MessageType.traTea_registerUser_fail)){
			System.out.println("The num has been registered!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void checkUserLog_drive(){
		Message message = new Message();
		message = trainingTeaBL.checkUserLog("gdr", "12345");
		if(message.getMesType().equals(MessageType.traTea_log_success)){
			System.out.println("Login success!");
		}else if(message.getMesType().equals(MessageType.traTea_log_fail)){
			System.out.println("User num or passwd is incorrect!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void changePasswd_drive(){
		Message message = new Message();
		message = trainingTeaBL.ChangePasswd("gdr", "123");
		if(message.getMesType().equals(MessageType.traTea_changePasswd_success)){
			System.out.println("Change passwd success!");
		}else if(message.getMesType().equals(MessageType.traTea_changePasswd_fail)){
			System.out.println("Change passwd failed!");
		}else{
			System.out.println("Network connect refused!");
		}
	}
	
	public void inputFrameStrategy_drive(){
		Message message = new Message();
		Dean dean = new Dean();
		dean.setName("软件学院");
		dean.setZhunru("30");
		dean.setZhunchu("50");
		dean.setGraduate("150");
		try {
			message = trainingTeaBL.inputFrameStrategy(dean);
			if(message.getMesType().equals(MessageType.traTea_inputStrategy_success)){
				System.out.println("Input frame strategy success!");
			}else if(message.getMesType().equals(MessageType.traTea_inputStrategy_fail)){
				System.out.println("Input frame strategy failed!");
			}else{
				System.out.println("Network connect refused!");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFrameStrategy_drive(){
		Message message = new Message();
		Dean dean = new Dean();
		dean.setName("软件学院");
		dean.setZhunru("60");
		dean.setZhunchu("48");
		dean.setGraduate("143");
		try {
			message = trainingTeaBL.updateFrameStrategy(dean);
			if(message.getMesType().equals(MessageType.traTea_updStrategy_success)){
				System.out.println("Update frame strategy success!");
			}else if(message.getMesType().equals(MessageType.traTea_updStrategy_fail)){
				System.out.println("Update frame strategy failed!");
			}else{
				System.out.println("Network connect refused!");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
