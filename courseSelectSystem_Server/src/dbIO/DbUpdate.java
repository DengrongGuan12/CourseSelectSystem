package dbIO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.*;
import db.SqlHelper;
import dbIOService.DbOService;
import dbIOService.DbUService;
import dbservice.DbService;

public class DbUpdate implements DbUService {

	DbService ds;
	ResultSet rs;
	String sql;
	String paras[];
	
	boolean judge;

	public DbUpdate() {
		ds = new SqlHelper();
	}

	@Override
	public boolean strategyUpdate(Message message) {
		// 更新整体框架策略
		Dean dean = message.getDean();
		sql = "update Frame set zhunru=?,zhunchu=?,graduate=? where dean=?";
		paras = new String[] { dean.getZhunru(), dean.getZhunchu(),
				dean.getGraduate(), dean.getName() };
		judge = ds.exeUpdate(sql, paras);
		return judge;
	}

	@Override
	public boolean passwordUpdate(Message message) {
		// 更新用户密码
		return false;
	}

	@Override
	public boolean courseUpdate(Message message) {
		// 向数据库更新课程信息
		Course course = message.getCour();
		String isOptional="";
		DbService ds1=new SqlHelper();
		sql="select isOptional from Course where num=?";
		paras=new String[]{course.getNum()};
		ResultSet rs1=ds1.query(sql, paras);
		try {
			if(rs1.next()){
				isOptional=rs1.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ds1.close();
		}
		if(isOptional.equals("否")&&course.getIsOptional().equals("是")){
			sql="delete from SelRecord where cNum=?";
			paras=new String[]{course.getNum()};
			ds.exeUpdate(sql, paras);
		}else if(isOptional.equals("是")&&course.getIsOptional().equals("否")){
			DbOService dbOutput = new DbOutput();
			ArrayList<Student> students = new ArrayList<Student>();
			Message message_DeanStudent = new Message();
			String sql_Select = "insert into SelRecord values(?,?,?)";
			String paras_Select[] = new String[3];
			message_DeanStudent = dbOutput.studentOutput("dean", course.getDean());
			students = message_DeanStudent.getStudents();
			
			for (int i = 0; i< students.size(); i++) {
				Student tempStudent = students.get(i);
				paras_Select = new String[] {tempStudent.getNum(), course.getNum(), null};
				ds.exeUpdate(sql_Select, paras_Select);
			}
			String sqlString2 = "update SelRecord set sNum=rtrim(sNum), cNum=rtrim(cNum), score=rtrim(score)";
			ds.trim(sqlString2);
		}
		sql = "update Course set name=?,place=?,time=?,tNum=?,tName=?," +
				"credit=?,isOptional=?,isZhunru=?,isTongshi=?,isGongxuan=?," +
				"isXinsheng=?,limit=? where num=?";
		paras = new String[]{course.getName(), course.getPlace(), course.getTime(), course.getTeaNum(), course.getTeaName(),
				course.getCredit(), course.getIsOptional(), course.getIsZhuanru(), course.getIsTongshi(), course.getIsGongxuan(),
				course.getIsXinsheng(), course.getLimit(), course.getNum()};
		judge = ds.exeUpdate(sql, paras);
		String sqlString = "update Course set num=rtrim(num), name=rtrim(name), place=rtrim(place), time=rtrim(time), " +
				"tNum=rtrim(tNum), tName=rtrim(tName), credit=rtrim(credit), BriefDes=rtrim(BriefDes), isOptional=rtrim(isOptional)," +
				"isZhunru=rtrim(isZhunru), isTongshi=rtrim(isTongshi), isGongxuan=rtrim(isGongxuan), isXinsheng=rtrim(isXinsheng), limit=rtrim(limit)";
		ds.trim(sqlString);
		
		return judge;
	} // end updateCourse method

}
