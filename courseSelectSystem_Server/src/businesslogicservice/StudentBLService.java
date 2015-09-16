package businesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Course;
import common.Message;
import common.User;

public interface StudentBLService extends Remote{
	public Message checkUserLog(String num,String passwd)throws RemoteException;
	public Message ChangePasswd(String num,String passwd)throws RemoteException;
	public Message showAllTongshi()throws RemoteException ;//显示全校的通识课
	public Message showAllGongxuan()throws RemoteException;
	public Message showAllXinsheng()throws RemoteException;
	public Message showKuaZhuanYe(String dean)throws RemoteException;
	public Message showCourseDetail(String cNum)throws RemoteException;
	public Message selectCourse(User u, Course course)throws RemoteException;
	public Message selectCourse(String sNum,Course course)throws RemoteException;
	public Message quitCourse(User u, String courseNum)throws RemoteException ;
	public Message showGPA(String sNum) throws RemoteException;
	public Message showMyCourseList(String stuNum)throws RemoteException ;
	public Message showMyScore(String stuNum)throws RemoteException;
	public Message showAllCourse(String dean)throws RemoteException;
	public Message changeMyInfo(User u)throws RemoteException;
	
	
	
	

}
