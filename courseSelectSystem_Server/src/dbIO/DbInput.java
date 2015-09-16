package dbIO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import common.*;
import db.*;
import dbIOService.*;
import dbservice.*;

public class DbInput implements DbIService {

	DbService ds;
	ResultSet rs;
	String sql;
	String paras[];

	User user;

	boolean judge;

	public DbInput() {
		ds = new SqlHelper();
	}

	@Override
	public boolean strategyInput(Message message) {
		// 向数据库写入整体框架策略
		sql = "insert into Frame values(?,?,?,?)";
		Dean dean = message.getDean();
		paras = new String[]{dean.getName(),dean.getZhunru(),dean.getZhunchu(),
				dean.getGraduate()};
		judge = ds.exeUpdate(sql, paras);
		return judge;
	}

	@Override
	public boolean teacherInput(Message message) {
		// 向数据库写入教师信息
		sql = "insert into Teacher values(?,?,?,?,?)";
		user = message.getU();
		paras = new String[] { user.getNum(), user.getName(), user.getPasswd(),user.getSex(),user.getDean() };
		judge = ds.exeUpdate(sql, paras);
		sql = "update Teacher set num = rtrim(num),name = rtrim(name),passwd = rtrim(passwd),sex = rtrim(sex),dean=rtrim(dean)";
		ds.trim(sql);
		return judge;
	}

	@Override
	public boolean deanTeaInput(Message message) {
		// 向数据库写入院系教务老师信息
		return judge;
	}

	@Override
	public boolean studentInput(Message message) {
		// 向数据库写入学生信息
		sql = "insert into Student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		user = message.getU();
		Calendar cal=Calendar.getInstance();
		int yearnow=cal.get(Calendar.YEAR);
		String year = String.valueOf(yearnow);
		paras = new String[] { user.getNum(), user.getName(), user.getPasswd(),
				user.getSex(), year, " ", " ", " ", " ", user.getDean(), " ", "0", " ", " ", " ", " " };
		judge = ds.exeUpdate(sql, paras);
		String sql1="update Student set num=rtrim(num),name=rtrim(name),passwd=rtrim(passwd)," +
				"sex=rtrim(sex),year=rtrim(year),dean=rtrim(dean),creOfOption=rtrim(creOfOption),address=rtrim(address)";
		ds.trim(sql1);
		return judge;
	}

	@Override
	public boolean courseSelectInput(Message message) {
		// 向数据库写入选课信息
		
		return judge;
	}
	
	public boolean courseInput(Message message){
		// 向数据库写入课程信息
				sql="insert into Course values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Course c = message.getCour();
				String isOptional = c.getIsOptional();
				String courseID = c.getNum();
				String dean = c.getDean();
				paras = new String[]{courseID,c.getName(),c.getPlace(),
						c.getTime(),c.getTeaNum(),c.getTeaName(),c.getDetail(),
						c.getCredit(),c.getIsOptional(),c.getIsZhuanru(),c.getIsTongshi(),
						c.getIsGongxuan(),c.getIsXinsheng(),c.getDean(),c.getLimit()};
				judge = ds.exeUpdate(sql, paras);
				String sqlString = "update Course set num=rtrim(num), name=rtrim(name), place=rtrim(place), time=rtrim(time), " +
						"tNum=rtrim(tNum), tName=rtrim(tName), credit=rtrim(credit), BriefDes=rtrim(BriefDes), isOptional=rtrim(isOptional)," +
						"isZhunru=rtrim(isZhunru), isTongshi=rtrim(isTongshi), isGongxuan=rtrim(isGongxuan), isXinsheng=rtrim(isXinsheng)," +
						" dean=rtrim(dean), limit=rtrim(limit)";
				ds.trim(sqlString);
				
				if (isOptional.equals("否")) {
					DbOService dbOutput = new DbOutput();
					ArrayList<Student> students = new ArrayList<Student>();
					Message message_DeanStudent = new Message();
					String sql_Select = "insert into SelRecord values(?,?,?)";
					String paras_Select[] = new String[3];
					message_DeanStudent = dbOutput.studentOutput("dean", dean);
					students = message_DeanStudent.getStudents();
					
					for (int i = 0; i< students.size(); i++) {
						Student tempStudent = students.get(i);
						paras_Select = new String[] {tempStudent.getNum(), courseID, null};
						ds.exeUpdate(sql_Select, paras_Select);
					}
					String sqlString2 = "update SelRecord set sNum=rtrim(sNum), cNum=rtrim(cNum), score=rtrim(score)";
					ds.trim(sqlString2);
				} // end if 
				return judge;
	}
	
	public boolean teachingProInput(Message message) {
		//  向数据库写入院系教学计划
		DbOutput dbo = new DbOutput();
		String sql_Delete = "delete from TeachingPro where dean=?"; 
		String paras_Delete[];
		ArrayList<TeachingPro> arr_OldTeachingPro = new ArrayList<TeachingPro>();
		Message message_OldTeachingPro = new Message();
		DeanTea deanTea = (DeanTea)message.getU();
		String dean = deanTea.getDean();
		message_OldTeachingPro = dbo.teachingProOutput("dean", dean);
		arr_OldTeachingPro = message_OldTeachingPro.getTeachingPro();
		boolean result = false;
		
		// 删除原有教学计划
		if (arr_OldTeachingPro.size() > 0) {
			paras_Delete = new String[]{dean};
			ds.exeUpdate(sql_Delete, paras_Delete);
		}
		
		// 更新新教学计划
		ArrayList<TeachingPro> arr_TeachingPro = new ArrayList<TeachingPro>();
		arr_TeachingPro = message.getTeachingPro();
		Iterator<TeachingPro> iter = arr_TeachingPro.iterator();
		while (iter.hasNext()) {
			TeachingPro teachingPro = new TeachingPro();
			teachingPro = iter.next();
			String courseName = teachingPro.getCourseName();
			String courseCredit = teachingPro.getCourseCredit();
			sql = "insert into TeachingPro values(?,?,?)";
			paras = new String[]{dean, courseName, courseCredit};
			ds.exeUpdate(sql, paras);
			String sqlString = "update TeachingPro set dean=rtrim(dean), cName=rtrim(cName), credit=rtrim(credit)";
			ds.trim(sqlString);

			result = true;
		}
		return result;
	} // end teachingProInput method
	//向数据库写入选课记录
	public void selRecordInput(Message message){
		
		SelRecord selRecord=new SelRecord();
		selRecord=message.getSelRecord();
		sql="insert into SelRecord values(?,?,?)";
		paras=new String[]{selRecord.getStuNum(),selRecord.getcNum(),null};
		ds.exeUpdate(sql, paras);
		String sqlString = "update SelRecord set sNum=rtrim(sNum),cNum=rtrim(cNum),score=rtrim(score)";
		ds.trim(sqlString);
		
		
	}

}
