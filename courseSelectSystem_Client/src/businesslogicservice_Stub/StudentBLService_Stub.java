package businesslogicservice_Stub;

import java.rmi.RemoteException;
import java.sql.ResultSet;



import common.Course;
import common.Message;
import common.MessageType;
import common.User;

import businesslogicservice.StudentBLService;

public class StudentBLService_Stub implements StudentBLService{
	

	@Override
	public Message checkUserLog(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.stu_log_success);
		return message;
	}

	@Override
	public Message ChangePasswd(String num, String passwd) {
		Message message=new Message();
		message.setMesType(MessageType.stu_changePasswd_success);
		return message;
	}

	@Override
	public Message showAllTongshi() {
		Message message=new Message();
		
		message.setMesType(MessageType.stu_show_allCourseSuccess);
		
//		String sql="select * from Course where isOptional=?";
//		String []paras={"选修"};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		sqlHelper.close();
//		message.setRs(rs);
		return message;
		
	}
	public Message showAllGongxuan()throws RemoteException{
		Message message=new Message();
		return message;
	}
	
	public Message showAllXinsheng()throws RemoteException{
		Message message=new Message();
		return message;
	}
	
	public Message showKuaZhuanYe(String dean)throws RemoteException{
		Message message=new Message();
		return message;
	}

	@Override
	public Message showCourseDetail(String cNum) {
        Message message=new Message();
		
		message.setMesType(MessageType.stu_show_detailCourInfoSuccess);
		
//		String sql="select BriefDes from Course where num=?";
//		String []paras={cNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		sqlHelper.close();
//		message.setRs(rs);
		return message;
	}

	public Message selectCourse(User u, Course course) {
		Message message=new Message();
		message.setMesType(MessageType.stu_selectCourseSuccess);
		return message;
	}

	@Override
	public Message quitCourse(User u, String courseNum) {
		Message message=new Message();
		message.setMesType(MessageType.stu_quitCourse_success);
		return message;
	}

	@Override
	public Message showGPA(String sNum) {
		Message message=new Message();
//		String sql="select credit from Student where num=?";
//		String []paras={sNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setRs(rs);
//		sqlHelper.close();
		message.setMesType(MessageType.stu_showMyGpa_success);
		return message;
	}

	@Override
	public Message showMyCourseList(String stuNum) {
		Message message=new Message();
//		String sql="select c.num,c.name,c.place,c.time,c.tName,c.credit,c.isOptional,c.dean from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
//		String paras[]={stuNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		sqlHelper.close();
//		message.setMesType(MessageType.stu_showMyCourse_success);
//		message.setRs(rs);
		return message;
	}

	@Override
	public Message showMyScore(String stuNum) {
		Message message=new Message();
//		String sql="select c.num,c.name,c.tName,c.credit,c.isOptional,c.dean,s.score from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
//		String[] paras={stuNum};
//		SqlHelper sqlHelper=new SqlHelper();
//		ResultSet rs=sqlHelper.query(sql, paras);
//		message.setMesType(MessageType.stu_showMyCourse_success);//就是显示我的课程列表加上成绩
//		message.setRs(rs);
//		sqlHelper.close();
		return message;
	}

	@Override
	public Message selectCourse(String sNum, Course course)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message showAllCourse(String dean) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message changeMyInfo(User u) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
