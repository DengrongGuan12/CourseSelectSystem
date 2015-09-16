package businesslogicservice_Stub;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.Message;
import common.MessageType;

import businesslogicservice.ClassTeaBLService;

public class ClassTeaBLService_Stub implements ClassTeaBLService{
	
	
	

	@Override
	public Message checkUserLog(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.tea_log_success);
		return message;
	}

	@Override
	public Message ChangePasswd(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.tea_changePasswd_success);
		return message;
		
	}

	@Override
	public Message showMyCourse(String teacherNum) {
		Message message=new Message();
//		String sql="select num,name,place,time,credit,isOptional,dean from Course where tNum=?";
//		String [] paras={teacherNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setRs(rs);
		message.setMesType(MessageType.tea_showMyCourse_success);
//		sqlHelper.close();
		return message;
	}

	@Override
	public Message inputCourseDetailInfo(String courseNum, String detail) {
		Message message=new Message();
		message.setMesType(MessageType.tea_inputDetailCInfo_success);
		return message;
		
	}

	@Override
	public Message showStuOfMyCourse(String courseNum) {
		Message message=new Message();
		
//		String sql="select stu.num,stu.name,stu.dean from Student stu,SelRecord s where s.cNum=? and s.sNum=stu.num";
//		String[] paras={courseNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setMesType(MessageType.deanTea_showCourseOfStudents_success);
//		message.setRs(rs);
//		sqlHelper.close();
		return message;
		
	}

	

	@Override
	public Message showCourseDetail(String cNum) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message recordScore(String courseNum, ArrayList<String> scores)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
