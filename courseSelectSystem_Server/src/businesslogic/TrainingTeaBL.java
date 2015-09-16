package businesslogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.*;
import db.SqlHelper;
import dbIO.DbInput;
import dbIO.DbOutput;
import dbIO.DbUpdate;
import dbIOService.*;
import dbservice.DbService;
import businesslogicservice.*;

@SuppressWarnings("serial")
public class TrainingTeaBL extends UnicastRemoteObject implements
		TrainingTeaBLService {
	DbOService dbo;
	DbIService dbi;
	DbUService dbu;
	Message message;

	boolean judge;

	public TrainingTeaBL() throws RemoteException {
		dbo = new DbOutput();
		dbi = new DbInput();
		dbu = new DbUpdate();
	}

	public Message showFrameStrategy() {
		message = dbo.strategyOutput();
		if (message.getDeans().size() > 0) {
			message.setMesType(MessageType.traTea_showStrategy_success);
		} else {
			message.setMesType(MessageType.traTea_showStrategy_fail);
		}

		return message;
	}

	public Message showTeachingPro(String dean) {// 教务处老师查看各院系教学计划
		message = dbo.teachingProOutput("dean", dean);
		if (message.getTeachingPro().size() > 0) {
			message.setMesType(MessageType.traTea_showTeachingPro_success);
		} else {
			message.setMesType(MessageType.traTea_showTeachingPro_fail);
		}

		return message;
	}

	public Message showTeacherList() {// 教务处老师查看教师统计信息
		message = dbo.teacherOutput("1", null);
		if (message.getTeachers().size() > 0) {
			message.setMesType(MessageType.traTea_showTeacherList_success);
		} else {
			message.setMesType(MessageType.traTea_showTeacherList_fail);
		}
		return message;
	}

	public Message showStudentList() {// 教务处老师查看学生统计信息
		message = dbo.studentOutput("none", "none");
		if (message.getStudents().size() > 0) {
			message.setMesType(MessageType.traTea_showStudentList_success);
		} else {
			message.setMesType(MessageType.traTea_showStudentList_fail);
		}
		return message;
	}

	public Message showCourseList(String dean) {// 教务处老师查看课程统计信息
		message = new Message();
		message = dbo.courseOutput("dean", dean);
		if (message.getCourses().size() > 0) {
			message.setMesType(MessageType.traTea_showCourseList_success);
		} else {
			message.setMesType(MessageType.traTea_showCourseList_fail);
		}
		return message;
		



	}

	public Message register(String userType, String name, String num,
			String passwd,String sex,String dean) {// 教务处老师注册新用户
		message = new Message();
		Message temp;
		User user = new User();
		user.setName(name);
		user.setNum(num);
		user.setPasswd(passwd);
		user.setSex(sex);
		user.setDean(dean);
		message.setU(user);

		judge = true;
		if (userType.equals("学生")) {
			
			
			temp = dbo.studentOutput("none", "none");
			ArrayList<Student> students = temp.getStudents();
			for(Student s:students){
				if(s.getNum().equals(num)){
					judge = false;
					break;
				}
			}
			if(judge==true){
				judge = dbi.studentInput(message);
				ArrayList<Course>courses=new ArrayList<Course>();
				Message courseMessage=new Message();
				courseMessage=dbo.courseOutput("deanAndOptional", dean);
				courses=courseMessage.getCourses();
				for(Course c:courses){
					SelRecord selRecord=new SelRecord();
					selRecord.setStuNum(num);
					selRecord.setcNum(c.getNum());
					Message selRecordMessage=new Message();
					selRecordMessage.setSelRecord(selRecord);
					dbi.selRecordInput(selRecordMessage);
				}
				
				
			}
		} else if (userType.equals("教师")) {
			temp = dbo.teacherOutput("1", null);
			ArrayList<Teacher> teachers = temp.getTeachers();
			for(Teacher t:teachers){
				if(t.getNum().equals(num)){
					judge = false;
					break;
				}
			}
			if(judge==true){
				judge = dbi.teacherInput(message);
			}
		}

		if (judge == false) {
			message.setMesType(MessageType.traTea_registerUser_fail);
		} else {
			message.setMesType(MessageType.traTea_registerUser_success);
		}

		return message;
	}

	public Message checkUserLog(String num, String passwd) {// 判断登陆用户名和密码
		// 判断登陆用户名和密码



		DbService ds=new SqlHelper();
		Message message = new Message();
		
		String passwd1 = "";
		
		String name = "";
		String sex="";
	

		try {
			        String sql = "select name,passwd,sex from TrainingTea  where num=?";
					String[] paras = { num };
					
					ResultSet rs = ds.query(sql, paras);
					if (rs.next()) {
						name = rs.getString(1);
						passwd1 = rs.getString(2);
						
						sex=rs.getString(3);

						if (passwd.equals(passwd1)) {
							User trainTea=new TrainingTea();
						    trainTea.setNum(num);
						    trainTea.setName(name);
						    trainTea.setPasswd(passwd1);
						    trainTea.setSex(sex);
						
							message.setU(trainTea);
							message.setMesType(MessageType.traTea_log_success);
							
							
							
							
						} else {
							message.setMesType(MessageType.traTea_log_fail);
							
							
						}
					} else {
						message.setMesType(MessageType.traTea_log_fail);
						
						

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
			String sql="update TrainingTea set passwd=? where num=?";
			
			String []paras={passwd,num};
			if(ds.exeUpdate(sql, paras)){
			
				String sql2="update TrainingTea set passwd=rtrim(passwd)";
				ds.trim(sql2);
				message.setMesType(MessageType.traTea_changePasswd_success);
			}else {
				message.setMesType(MessageType.traTea_changePasswd_fail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	
	
	

	
	}

	@Override
	public Message inputFrameStrategy(Dean dean) throws RemoteException {
		// 输入整体框架策略
		message = dbo.strategyOutput();
		ArrayList<Dean> deans = message.getDeans();
		message.setMesType(MessageType.traTea_inputStrategy_success);
		for (Dean d : deans) {
			if (d.getName().equals(dean.getName())) {
				message.setMesType(MessageType.traTea_inputStrategy_fail);
				break;
			}
		}

		judge = false;

		if (!message.getMesType().equals(MessageType.traTea_inputStrategy_fail)) {
			message.setDean(dean);
			judge = dbi.strategyInput(message);
		}

		if (judge == true) {
			message = dbo.strategyOutput();
			message.setMesType(MessageType.traTea_inputStrategy_success);
		}

		return message;
	}

	@Override
	public Message updateFrameStrategy(Dean dean) throws RemoteException {
		// 更新整体框架策略
		judge = false;
		message.setDean(dean);
		judge = dbu.strategyUpdate(message);
		if (judge == true) {
			message = dbo.strategyOutput();
			message.setMesType(MessageType.traTea_updStrategy_success);
		} else {
			message.setMesType(MessageType.traTea_updStrategy_fail);
		}
		return message;
	}

	@Override
	public Message endSelect() throws RemoteException {
		Message message=new Message();
		DbService ds=new SqlHelper();
		String grade="";
		String sNum="";
		String cNum="";
		String creOfOption="";
		boolean ifSuc=true;
		double[] d={0.1,0.2,0.3,0.4};//确定一二三四年级所占比例
		ArrayList<Course> courses=new ArrayList<Course>();
		int size=0;//记录课程数量
		try {
			String sql="select * from TempRecord where 1=?";
			String[]paras={"1"};
			ResultSet rs=ds.query(sql, paras);
			
			while(rs.next()){
				grade=rs.getString(1);
				sNum=rs.getString(2);
				cNum=rs.getString(3);
				creOfOption=rs.getString(4);
				//System.out.println(grade+" "+sNum+" "+cNum);
				boolean isExist=false;
				for(int i=0;i<size;i++){
					
					Course c=courses.get(i);
					if(c.getNum().equals(cNum)){
						Student s=new Student();
						s.setNum(sNum);
						s.setGrade(grade);
						s.setCreOfOption(creOfOption);
						//System.out.println(s.getNum());
						c.getStudents().add(s);
						isExist=true;
						break;
					}
				}
				if(!isExist){
					Course c=new Course();
					c.setNum(cNum);
					Student s=new Student();
					s.setNum(sNum);
					s.setGrade(grade);
					s.setCreOfOption(creOfOption);
					c.getStudents().add(s);
					courses.add(c);
					size++;
				}
				
			}
			ds.deleteTable("TempRecord");
			//System.out.println(size);
           String sql2="select limit from Course where num=?";
			
			for(int i=0;i<size;i++){
				String []paras2={courses.get(i).getNum()};
				ResultSet rs2=ds.query(sql2, paras2);
				if(rs2.next()){
					String limit=rs2.getString(1);
					//System.out.println(limit);
					courses.get(i).setLimit(limit);
				}
			}
			
			String sql3="insert into SelRecord values(?,?,?)";
			//分配学生
			for(int i=0;i<size;i++){
				Course c=courses.get(i);
				int limit=Integer.parseInt(c.getLimit());
				//System.out.println(limit);
				ArrayList<Student>students=c.getStudents();
				int ss=students.size();
				//System.out.println(ss);
				if(ss<=limit){
					for(int j=0;j<ss;j++){
						
						String []paras3={students.get(j).getNum(),c.getNum(),null};
						ds.exeUpdate(sql3, paras3);
					}
					
					
				}else {
					ArrayList<Student> students2=new ArrayList<Student>();//创建一个新的人数符合的学生表
					int index=0;
					while(true){//每次循环生成一个随机数
						double random=(Math.random())*10;
						int n=index%ss;
						Student ts=students.get(n);
						int g=Integer.parseInt(ts.getGrade());
						int cre=Integer.parseInt(ts.getCreOfOption());
						double temp1=random*d[g-1];
						double temp2=random*cre;
						if(temp1>1&&temp2<35){
							students2.add(ts);
							if(students2.size()==limit){
								break;
							}
						}
						index++;
					}
					for(int j=0;j<limit;j++){
						
						String []paras3={students2.get(j).getNum(),c.getNum(),null};
						ds.exeUpdate(sql3, paras3);
					}
				}//end else
			}//end for
			String sql4="update SelRecord set sNum=rtrim(sNum),cNum=rtrim(cNum),score=rtrim(score)";
			ds.trim(sql4);
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			//System.out.println("Not exist!");
			ifSuc=false;
		}finally{
			ds.close();
		}
	
		if(ifSuc){
			message.setMesType(MessageType.traTea_endSelect_suc);//调用方需要先判断是否为空
		}else {
			message.setMesType(MessageType.traTea_endSelect_fail);
		}
		
		return message;
	}
	
	public Message startSelect()throws RemoteException{
		Message message=new Message();
		DbService ds=new SqlHelper();
		boolean suc=true;
		try {
			if(ds.createTempRecordTable()){
				suc=true;
			}else {
				suc=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		if(suc){
			message.setMesType(MessageType.traTea_startSelect_suc);
		}else {
			message.setMesType(MessageType.traTea_startSelect_fail);
		}
		return message;
		
	}
	
//	public static void main(String[]arg0){
//		
//		try {
//			TrainingTeaBLService trainingTeaBLService=new TrainingTeaBL();
//			Message message=trainingTeaBLService.endSelect();
//			if(message.getMesType()==null||message.getMesType().equals(MessageType.traTea_endSelect_fail)){
//				System.out.println("error!");
//			}else {
//				System.out.println("Success!");
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
