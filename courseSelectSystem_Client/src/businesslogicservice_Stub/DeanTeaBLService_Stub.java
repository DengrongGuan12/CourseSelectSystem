package businesslogicservice_Stub;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.Course;
import common.Message;
import common.MessageType;
import common.TeachingPro;
import businesslogicservice.DeanTeaBLService;

public class DeanTeaBLService_Stub implements DeanTeaBLService{
	

	@Override
	public Message checkUserLog(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.deanTea_log_success);
		return message;
	}

	@Override
	public Message ChangePasswd(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.deanTea_changePasswd_success);
		return message;
	}

	@Override
	public Message publish(Course c) {
		Message message=new Message();
		message.setMesType(MessageType.deanTea_pubCourse_success);
		return message;
	}

	@Override
	public Message inputTeachingPro(ArrayList<TeachingPro> teachingPro, String dean) {
		Message message=new Message();
		message.setMesType(MessageType.deanTea_inputTeachingPro_success);
		return message;
	}

	@Override
	public Message updateCourse(Course course) {
		Message message=new Message();
		message.setMesType(MessageType.deanTea_updCourseInfo_success);
		return message;
		
	}

	@Override
	public Message showCourses(String dean) {
		Message message=new Message();
//		String sql="select num,name,place,time,tName,credit,isOptional,dean from Course where dean=?";
//		String []paras={dean};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setRs(rs);
//		sqlHelper.close();
		message.setMesType(MessageType.deanTea_showCourse_success);
		return message;
	}

	@Override
	public Message showCourseInfo(String cNum) {
		    Message message=new Message();
			
			message.setMesType(MessageType.deanTea_show_detailCourInfo_success);
			
//			String sql="select BriefDes from Course where num=?";
//			String []paras={cNum};
//			SqlHelper sqlHelper=new SqlHelper();
//			ResultSet rs=sqlHelper.query(sql, paras);
//			message.setRs(rs);
//			sqlHelper.close();
			
			return message;
	}

	@Override
	public Message showCourseOfStudents(String cNum) {
		Message message=new Message();
//		String sql="select stu.num,stu.name,stu.dean from Student stu,SelRecord s where s.cNum=? and s.sNum=stu.num";
//		String[] paras={cNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setMesType(MessageType.deanTea_showCourseOfStudents_success);
//		message.setRs(rs);
//		sqlHelper.close();
		return message;
	}

	@Override
	public Message showTeachingPro(String dean) throws RemoteException {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setMesType(MessageType.deanTea_showTeachingPro_success);
		return message;
	}

	@Override
	public Message showStudentList(String dean) throws RemoteException {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setMesType(MessageType.deanTea_showStudentList_success);
		return message;
	}

	@Override
	public Message showTeacherList(String dean) throws RemoteException {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setMesType(MessageType.deanTea_showTeacherList_success);
		return message;
	}

}
