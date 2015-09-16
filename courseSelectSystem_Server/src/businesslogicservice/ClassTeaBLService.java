package businesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import common.Message;

public interface ClassTeaBLService extends Remote {
	public Message checkUserLog(String num,String passwd)throws RemoteException;
	public Message ChangePasswd(String num,String passwd)throws RemoteException;
	public Message showMyCourse(String teacherNum)throws RemoteException;
	public Message inputCourseDetailInfo(String courseNum, String detail)throws RemoteException;
	public Message showStuOfMyCourse(String courseNum) throws RemoteException;
	public Message recordScore(String courseNum, ArrayList<String> scores)throws RemoteException ;
	public Message showCourseDetail(String cNum)throws RemoteException;
	

}
