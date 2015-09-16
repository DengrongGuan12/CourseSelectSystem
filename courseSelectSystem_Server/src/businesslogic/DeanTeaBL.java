package businesslogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.Course;
import common.DeanTea;
import common.Message;
import common.MessageType;
import common.TeachingPro;
import common.User;
import db.SqlHelper;
import dbIO.DbInput;
import dbIO.DbOutput;
import dbIO.DbUpdate;
import dbIOService.DbIService;
import dbIOService.DbOService;
import dbIOService.DbUService;
import dbservice.DbService;

import businesslogicservice.DeanTeaBLService;

public class DeanTeaBL extends UnicastRemoteObject implements DeanTeaBLService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Message message;
	private DbOService dbo;
	private DbIService dbi;
	private DbUService dbu;

	public DeanTeaBL() throws RemoteException {
		super();
	}

	public Message checkUserLog(String num, String passwd) {
		DbService ds = new SqlHelper();
		message = new Message();

		String passwd1 = "";
		String name = "";
		String sex = "";
		String dean = "";

		try {
			String sql = "select name,passwd,dean,sex from DeanTea  where num=?";
			String[] paras = { num };

			ResultSet rs = ds.query(sql, paras);
			if (rs.next()) {
				name = rs.getString(1);
				passwd1 = rs.getString(2);
				dean = rs.getString(3);
				sex = rs.getString(4);

				if (passwd.equals(passwd1)) {
					User deanTea = new DeanTea();
					deanTea.setName(name);
					deanTea.setNum(num);
					deanTea.setSex(sex);
					deanTea.setPasswd(passwd1);
					((DeanTea) deanTea).setDean(dean);

					message.setU(deanTea);
					message.setMesType(MessageType.deanTea_log_success);
				} else {
					message.setMesType(MessageType.deanTea_log_fail);
				}
			} else {
				message.setMesType(MessageType.deanTea_log_fail);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}
		return message;

	} // end checkUserLog method

	public Message ChangePasswd(String num, String passwd) {

		Message message = new Message();
		DbService ds = new SqlHelper();
		try {
			String sql = "update DeanTea set passwd=? where num=?";

			String[] paras = { passwd, num };
			if (ds.exeUpdate(sql, paras)) {

				String sql2 = "update DeanTea set passwd=rtrim(passwd)";
				ds.trim(sql2);
				message.setMesType(MessageType.deanTea_changePasswd_success);
			} else {
				message.setMesType(MessageType.deanTea_changePasswd_fail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}
		return message;

	} // end ChangePassword method

	public Message publish(Course c) {
		dbi = new DbInput();
		dbo = new DbOutput();
		message = new Message();
		message.setCour(c);
		int state = judgeCourse(c, "Publish");

		// 发布课程并返回结果
		if (state == 0) {
			if (dbi.courseInput(message)) {
				message.setMesType(MessageType.deanTea_pubCourse_success);
			} else {
				message.setMesType(MessageType.deanTea_pubCourse_fail);
			}
		} else {
			if (state == 1) {
				message.setMesType(MessageType.deanTea_pubCourse_fail_courseID);
			} else if (state == 2) {
				message.setMesType(MessageType.deanTea_pubCourse_fail_teacherCourseConflict);
			} else if (state == 3) {
				message.setMesType(MessageType.deanTea_pubCourse_fail_timePlaceConflict);
			}
		}
		return message;
	} // end publish method

	public Message inputTeachingPro(ArrayList<TeachingPro> teachingPro,
			String dean) {
		message = new Message();
		message.setTeachingPro(teachingPro);
		DeanTea deanTea = new DeanTea();
		deanTea.setDean(dean);
		message.setU(deanTea);
		dbi = new DbInput();

		if (dbi.teachingProInput(message)) {
			message.setMesType(MessageType.deanTea_inputTeachingPro_success);
		} else {
			message.setMesType(MessageType.deanTea_inputTeachingPro_fail);
		}

		return message;
	} // end inputTeachingPro

	public Message updateCourse(Course course) {
		message = new Message();
		message.setCour(course);
		dbu = new DbUpdate();
		int state = judgeCourse(course, "Update");

		if (state == 0) {
			if (dbu.courseUpdate(message)) {
				message.setMesType(MessageType.deanTea_updCourseInfo_success);
			} else {
				message.setMesType(MessageType.deanTea_updCourseInfo_fail);
			}
		} else {
			if (state == 1) {
				message.setMesType(MessageType.deanTea_updCourseInfo_fail_CourseID);
			} else if (state == 2) {
				message.setMesType(MessageType.deanTea_updCourseInfo_fail_teacherCourseConflict);
			} else if (state == 3) {
				message.setMesType(MessageType.deanTea_updCourseInfo_fail_timePlaceConflict);
			}
		}
		return message;
	} // end updateCourse method

	public Message showCourses(String dean) {
		// 院系教务老师查看课程统计信息
		message = new Message();
		dbo = new DbOutput();
		message = dbo.courseOutput("dean", dean);

		if (message.getCourses().size() > 0) {
			message.setMesType(MessageType.deanTea_showCourse_success);
		} else {
			message.setMesType(MessageType.deanTea_showCourse_fail);
		}

		return message;
	} // end showCourse method

	public Message showCourseInfo(String cNum) {
		message = new Message();
		dbo = new DbOutput();
		message = dbo.outputCourseDetail(cNum);

		if (message.getCour() != null) {
			message.setMesType(MessageType.deanTea_show_detailCourInfo_success);
		} else {
			message.setMesType(MessageType.deanTea_show_detailCourInfo_fail);
		}
		return message;
	} // end showCourseInfo method

	public Message showStudentList(String dean) {
		message = new Message();
		dbo = new DbOutput();
		message = dbo.studentOutput("dean", dean);

		if (message.getStudents().size() > 0) {
			message.setMesType(MessageType.deanTea_showStudentList_success);
		} else {
			message.setMesType(MessageType.deanTea_showStudentList_fail);
		}
		return message;
	} // end showStudentList method

	public Message showCourseOfStudents(String cNum) {
		message = new Message();
		dbo = new DbOutput();
		message = dbo.outputCourseOfStudents(cNum);

		if (message.getStudents().size() > 0) {
			message.setMesType(MessageType.deanTea_showCourseOfStudents_success);
		} else {
			message.setMesType(MessageType.deanTea_showCourseOfStudents_fail);
		}
		return message;
	} // end showCourseOfStudents method

	public Message showTeachingPro(String dean) {
		message = new Message();
		dbo = new DbOutput();
		message = dbo.teachingProOutput("dean", dean);

		if (message.getTeachingPro().size() > 0) {
			message.setMesType(MessageType.deanTea_showTeachingPro_success);
		} else {
			message.setMesType(MessageType.deanTea_showTeachingPro_fail);
		}
		return message;
	} // end showTeachingPro method
	
	public Message showTeacherList(String dean) {
		message = new Message();
		dbo = new DbOutput();
		message = dbo.teacherOutput("dean", dean);
		
		if (message.getTeachers().size() > 0) {
			message.setMesType(MessageType.deanTea_showTeacherList_success);
		} else {
			message.setMesType(MessageType.deanTea_showTeacherList_fail);
		}
		return message;
	}

	/**
	 * 
	 * @param course
	 * @param operation
	 * @return 0 - course can be published or updated 
	 * 		   1 - course id conflict 
	 * 		   2 - teacher course conflict
	 * 		   3 - course time and place conflict
	 */
	private int judgeCourse(Course course, String operation) {
		int result = 0;
		boolean teacherState = true;
		ArrayList<Course> arr_CourseList = new ArrayList<Course>();
		ArrayList<Course> arr_CourseOfTeacher = new ArrayList<Course>();
		Message message_Course = new Message();
		Message message_TeacherCourse = new Message();
		String dean = course.getDean();
		String courseID = course.getNum();
		String time = course.getTime();
		String place = course.getPlace();
		String teacherID = course.getTeaNum();

		// 获取本院课程以及教师任课信息
		message_Course = dbo.courseOutput("dean", dean);
		arr_CourseList = message_Course.getCourses();
		message_TeacherCourse = dbo.courseOutput("teacher", teacherID);
		arr_CourseOfTeacher = message_TeacherCourse.getCourses();

		// 判断教师任课列表与该课程是否有时间冲突
		for (int i = 0; i < arr_CourseOfTeacher.size(); i++) {
			Course tempCourse = arr_CourseList.get(i);
			if (judgeIfConflict(time, tempCourse.getTime())) {
				teacherState = false;
				break;
			}
		}
		if (!teacherState) {
			result = 2;
			return result;
		}
		
		// 判断能否发布课程 或 修改课程信息
		if (operation.equals("Publish")) {
			for (int i = 0; i < arr_CourseList.size(); i++) {
				Course tempCourse = arr_CourseList.get(i);
				if (courseID.equals(tempCourse.getNum())) {
					result = 1;
					break;
				} else if (judgeIfConflict(time, tempCourse.getTime())
						&& place.equals(tempCourse.getPlace())) {
					result = 3;
					break;
				}
			}
		} else if (operation.equals("Update")) {
			for (int i = 0; i < arr_CourseList.size(); i++) {
				Course tempCourse = arr_CourseList.get(i);
				if (judgeIfConflict(time, tempCourse.getTime())
						&& place.equals(tempCourse.getPlace())) {
					if (!courseID.equals(tempCourse.getNum())) {
						result = 3;
						break;
					} // end inside if
				} // end out if
			} // end for
		}

		return result;
	} // end judgeCourse method

	// 判断是否课程时间冲突
	private boolean judgeIfConflict(String t1, String t2) {
		boolean b = false;
		String time1[] = t1.split(" ");
		String time2[] = t2.split(" ");
		String[] split1 = time1[0].split(",");
		int n = split1.length;
		String[] split2 = time2[0].split(",");
		int m = split2.length;
		if (!split1[0].equals(split2[0])) {
			b = false;
		} else {
			for (int i = 1; i < n; i++) {
				for (int j = 1; j < m; j++) {
					if (split1[i].equals(split2[j])) {
						b = true;
						break;
					}
				} // end inside for
				if (b) {
					break;
				}
			} // end for
		}
		return b;
	} // end judgeIfConflict method

}
