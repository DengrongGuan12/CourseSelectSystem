package businesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import common.Course;
import common.Message;
import common.TeachingPro;

public interface DeanTeaBLService extends Remote{
	public Message checkUserLog(String num,String passwd)throws RemoteException;
	public Message ChangePasswd(String num,String passwd)throws RemoteException;
	public Message publish(Course c)throws RemoteException;
	public Message inputTeachingPro(ArrayList<TeachingPro> teachingPro, String dean)throws RemoteException ;
	public Message updateCourse(Course course)throws RemoteException ;
	public Message showCourses(String dean)throws RemoteException ;
	public Message showCourseInfo(String cNum)throws RemoteException;
	public Message showCourseOfStudents(String cNum)throws RemoteException;
	public Message showTeachingPro(String dean) throws RemoteException;
	public Message showStudentList(String dean) throws RemoteException;
	public Message showTeacherList(String dean) throws RemoteException;
}
