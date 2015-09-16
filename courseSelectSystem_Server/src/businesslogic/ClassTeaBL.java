package businesslogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;


import common.Course;
import common.Message;
import common.MessageType;
import common.Student;

import common.Teacher;
import common.User;
import db.SqlHelper;
import dbservice.DbService;

import businesslogicservice.ClassTeaBLService;

@SuppressWarnings("serial")
public class ClassTeaBL extends UnicastRemoteObject implements ClassTeaBLService{
	
	public ClassTeaBL() throws RemoteException {
		super();
	}
	
	public Message showCourseDetail(String cNum)throws RemoteException {
		DbService ds=new SqlHelper();
		Message message = new Message();
		try {
			String sql="select BriefDes from Course where num=?";
			String paras[]={cNum};
			ResultSet rs=ds.query(sql, paras);
			if(rs.next()){
				message.setMesType(MessageType.tea_show_detailCourInfoSuccess);
				Course c=new Course();
				c.setDetail(rs.getString(1));
				message.setCour(c);
				
			}else {
				message.setMesType(MessageType.tea_show_detailCourInfoFail);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
			
		}
		
		
		return message;
	}

	
	public Message checkUserLog(String num, String passwd) {

		DbService ds=new SqlHelper();
		Message message = new Message();
		
		String passwd1 = "";
		
		String name = "";
		String sex="";

		try {
			        String sql = "select name,passwd,sex from Teacher  where num=?";
					String[] paras = { num };
					
					ResultSet rs = ds.query(sql, paras);
					if (rs.next()) {
						name = rs.getString(1);
						passwd1 = rs.getString(2);
						sex=rs.getString(3);

						if (passwd.equals(passwd1)) {
							User tea=new Teacher();
							tea.setNum(num);
							tea.setName(name);
							tea.setPasswd(passwd1);
							tea.setSex(sex);
						
							message.setU(tea);
							message.setMesType(MessageType.tea_log_success);
							
							
							
							
						} else {
							message.setMesType(MessageType.tea_log_fail);
							
							
						}
					} else {
						message.setMesType(MessageType.tea_log_fail);
						
						

					}

			} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
			} finally {
				ds.close();
			}

			

			
		
		return message;
	
	}


	public Message ChangePasswd(String num, String passwd) {

		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			String sql="update Teacher set passwd=? where num=?";
	
			String []paras={passwd,num};
			if(ds.exeUpdate(sql, paras)){
		
				String sql2="update Teacher set passwd=rtrim(passwd)";
				ds.trim(sql2);
				message.setMesType(MessageType.tea_changePasswd_success);
			}else {
				message.setMesType(MessageType.tea_changePasswd_fail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	
	}

	
	public Message showMyCourse(String teacherNum) {//无需考虑时间冲突

		Message message = new Message();
		DbService ds = new SqlHelper();
		try {
			String sql = "select c.num,c.name,c.place,c.time,c.tName,c.credit,c.isOptional," +
					"c.isZhunru,c.isTongshi,c.isGongxuan,c.isXinsheng,c.dean from Course c where tNum=?";
			String paras[] = { teacherNum };
			ResultSet rs = ds.query(sql, paras);
			ArrayList<Course> courses = new ArrayList<Course>();
			ArrayList<String> attriOfCourse = new ArrayList<String>();
			int n = 0;
			while (rs.next()) {
				Course c=new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));
				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));
				String isOPtional=rs.getString(7);
				String isZhunru=rs.getString(8);
				String isTongshi=rs.getString(9);
				String isGongxuan=rs.getString(10);
				String isXinsheng=rs.getString(11);
				c.setDean(rs.getString(12));
				if(isOPtional.equals("是")){
					if(isGongxuan.equals("是")){
						c.setProperty("公选");
					}
					if(isTongshi.equals("是")){
						c.setProperty("通识");
					}
					if(isXinsheng.equals("是")){
						c.setProperty("新生研讨");
					}
					
				}else{
					if(isZhunru.equals("是")){
						c.setProperty("准入/专业");
					}else {
						c.setProperty("通修");
					}
				}
				courses.add(c);
				n++;
				
				
				
			}
			
			if(n==0){
				message.setMesType(MessageType.tea_showMyCourse_fail);
			}else {
				attriOfCourse.add("课程号");
				attriOfCourse.add("名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课老师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("性质");
				message.setMesType(MessageType.tea_showMyCourse_success);
				message.setAttri(attriOfCourse);
				message.setCourses(courses);
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	
	}

	
	public Message inputCourseDetailInfo(String courseNum, String detail) {


		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			String sql="update Course set BriefDes=? where num=?";
	
			String []paras={detail,courseNum};
			if(ds.exeUpdate(sql, paras)){
		
				String sql2="update Course set BriefDes=rtrim(BriefDes)";
				ds.trim(sql2);
				message.setMesType(MessageType.tea_inputDetailCInfo_success);
			}else {
				message.setMesType(MessageType.tea_inputDetailCInfo_fail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	
	
	}

	
	public Message showStuOfMyCourse(String courseNum) {
		
		Message message = new Message();
		DbService ds=new SqlHelper();
		String sNum="";
		String name="";
		String dean="";
		String score="";
		try {
			
			String sql="select stu.num,stu.name,stu.dean,s.score from Student stu,SelRecord s " +
					"where s.cNum=? and s.sNum=stu.num";
			String []paras={courseNum};
			ResultSet rs=ds.query(sql, paras);
			int n=0;//记录数量
			ArrayList<User> students=new ArrayList<User>();
			ArrayList<String> attriOfStu = new ArrayList<String>();
			while(rs.next()){
				sNum=rs.getString(1);
				name=rs.getString(2);
				dean=rs.getString(3);
				score=rs.getString(4);
				
				User u=new Student();
				u.setNum(sNum);
				u.setName(name);
				((Student)u).setDean(dean);
				if(score==null){
					((Student)u).setScore("尚未填写");
				}else {
					((Student)u).setScore(score);
				}
				
				students.add(u);
				n++;
				
			}
			
			if(n==0){
				message.setMesType(MessageType.tea_showMyStudent_fail);
			}else {
				attriOfStu.add("学号");
				attriOfStu.add("姓名");
				attriOfStu.add("院系");
				attriOfStu.add("成绩");
				
				message.setMesType(MessageType.tea_showMyStudent_Success);
				message.setAttri(attriOfStu);
				message.setUsers(students);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	}


	public Message recordScore(String courseNum, ArrayList<String> scores) {//scores 里面的数据格式为学号：分数
		Message message = new Message();
		int size=scores.size();
		boolean isSuc=true;
		DbService ds=new SqlHelper();
		try {
			for(int i=0;i<size;i++){
				String temp=scores.get(i);
				String [] splits=temp.split(":");
				String sql="update SelRecord set score=? where sNum=? and cNum=?";
				String []paras={splits[1],splits[0],courseNum};
				if(!ds.exeUpdate(sql, paras)){
					isSuc=false;
					break;
				}
				
			}
			String sql2="update SelRecord set score=rtrim(score)";
			ds.trim(sql2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		if(isSuc){
		
			message.setMesType(MessageType.tea_recordScore_success);
		}else {
			message.setMesType(MessageType.tea_recordScore_fail);
		}
		return message;
	}

}
