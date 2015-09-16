package businesslogicservice_Stub;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import common.Dean;
import common.Message;
import common.MessageType;
import businesslogicservice.TrainingTeaBLService;

public class TrainingTeaBLServeice_Stub implements TrainingTeaBLService {

	@Override
	public Message checkUserLog(String num, String passwd) {
		Message message = new Message();
		message.setMesType(MessageType.traTea_log_success);
		return message;
	}

	@Override
	public Message ChangePasswd(String num, String passwd) {
		Message message = new Message();
		message.setMesType(MessageType.traTea_changePasswd_success);
		return message;

	}

	@Override
	public Message inputFrameStrategy(Dean dean) {
		Message message = new Message();
		message.setMesType(MessageType.traTea_inputStrategy_success);
		return message;
	}

	@Override
	public Message updateFrameStrategy(Dean dean) {
		Message message = new Message();
		message.setMesType(MessageType.traTea_updStrategy_success);
		return message;
	}

	@Override
	public Message showFrameStrategy() {
		Message message = new Message();
		message.setMesType(MessageType.traTea_showStrategy_success);
		// message.setFrameStrategy("三三制");
		return message;
	}

	@Override
	public Message showTeachingPro(String dean) {
		Message message = new Message();
		message.setMesType(MessageType.traTea_showTeachingPro_success);
		// message.setTeachingPro("选修课5门");
		return message;
	}

	@Override
	public Message showTeacherList() {
		Message message = new Message();
		// String sql="select num,name from Teacher where 1=?";
		// String []paras={"1"};
		// SqlHelper sqlHelper=new SqlHelper();
		// ResultSet rs=sqlHelper.query(sql, paras);
		// message.setRs(rs);
		// sqlHelper.close();
		message.setMesType(MessageType.traTea_showTeacherList_success);
		return message;

	}

	@Override
	public Message showStudentList() {
		Message message = new Message();
		// String sql="select num,name，dean from Student where 1=?";
		// String []paras={"1"};
		// SqlHelper sqlHelper=new SqlHelper();
		// ResultSet rs=sqlHelper.query(sql, paras);
		// message.setRs(rs);
		// sqlHelper.close();
		message.setMesType(MessageType.traTea_showStudentList_success);
		return message;
	}

	@Override
	public Message showCourseList(String dean) {
		Message message = new Message();
		// String
		// sql="select num,name,place,time,tName,credit,isOptional,dean from Course where 1=?";
		// String []paras={"1"};
		// SqlHelper sqlHelper=new SqlHelper();
		// ResultSet rs=sqlHelper.query(sql, paras);
		// message.setRs(rs);
		// sqlHelper.close();
		message.setMesType(MessageType.traTea_showCourseList_success);
		return message;
	}

//	@Override
//	public Message register(String userType, String name, String num,
//			String passwd) {
//		Message message = new Message();
//		message.setMesType(MessageType.traTea_registerUser_success);
//		return message;
//	}

	@Override
	public Message endSelect() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message startSelect() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message register(String userType, String name, String num,
			String passwd, String sex, String dean) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Message register(String userType, String name, String num,
//			String passwd, String sex) throws RemoteException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
