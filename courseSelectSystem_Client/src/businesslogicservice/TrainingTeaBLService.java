package businesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Dean;
import common.Message;

public interface TrainingTeaBLService extends Remote {
	public Message checkUserLog(String num, String passwd)
			throws RemoteException;

	public Message ChangePasswd(String num, String passwd)
			throws RemoteException;

	public Message inputFrameStrategy(Dean dean) throws RemoteException;

	public Message updateFrameStrategy(Dean dean) throws RemoteException;

	public Message showFrameStrategy() throws RemoteException;

	public Message showTeachingPro(String dean) throws RemoteException;

	public Message showTeacherList() throws RemoteException;

	public Message showStudentList() throws RemoteException;

	public Message showCourseList(String dean) throws RemoteException;

	public Message register(String userType, String name, String num,
			String passwd, String sex, String dean) throws RemoteException;

	public Message endSelect() throws RemoteException;

	public Message startSelect() throws RemoteException;

}
