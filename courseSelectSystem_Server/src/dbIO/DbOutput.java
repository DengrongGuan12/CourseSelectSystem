package dbIO;

/*
 * ��ȡ���ݿ��и����б�����
 */

import java.sql.*;
import java.util.ArrayList;

import common.*;
import db.*;
import dbIOService.*;
import dbservice.*;

public class DbOutput implements DbOService {

	DbService ds;
	Message message;
	ResultSet rs;
	String sql;
	String paras[];

	public DbOutput() {
		super();
	}

	@Override
	public Message strategyOutput() {
		// ��ȡ���ݿ��������ܲ���
		message = new Message();
		ds = new SqlHelper();

		try {
			sql = "select * from Frame where 1=?";
			paras = new String[] { "1" };

			rs = ds.query(sql, paras);
			ArrayList<Dean> dean = new ArrayList<Dean>();
			int i = 0;
			while (rs.next()) {
				Dean d = new Dean();
				d.setName(rs.getString(1));
				d.setZhunru(rs.getString(2));
				d.setZhunchu(rs.getString(3));
				d.setGraduate(rs.getString(4));
				dean.add(d);
				i++;
			}
			if (i > 0) {
				ArrayList<String> attriOfDean = new ArrayList<String>();
				attriOfDean.add("Ժϵ����");
				attriOfDean.add("׼��ѧ����");
				attriOfDean.add("׼��ѧ����");
				attriOfDean.add("��ҵѧ����");
				message.setAttriOfDean(attriOfDean);
				message.setDeans(dean);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	}

	@Override
	public Message teacherOutput(String limitType, String limitation) {
		// ��ȡ��ʦ�б���Ϣ
		message = new Message();
		ds = new SqlHelper();
		if (limitType.equals("dean")) {
			sql = "select num,name,passwd,sex,dean from Teacher where dean=?";
			paras = new String[] { limitation };
		} else {
			sql = "select num,name,passwd,sex,dean from Teacher where 1=?";
			paras = new String[] { "1" };
		}
		try {
			ResultSet rs = ds.query(sql, paras);
			ArrayList<Teacher> teacher = new ArrayList<Teacher>();
			int i = 0;
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setNum(rs.getString(1));
				t.setName(rs.getString(2));
				t.setPasswd(rs.getString(3));
				t.setSex(rs.getString(4));
				t.setDean(rs.getString(5));
				teacher.add(t);
				i++;
			}
			if (i > 0) {
				ArrayList<String> attriOfTea = new ArrayList<String>();
				attriOfTea.add("��ʦ��");
				attriOfTea.add("��ʦ����");
				attriOfTea.add("�Ա�");
				attriOfTea.add("Ժϵ");
				message.setAttriOfTea(attriOfTea);
				message.setTeachers(teacher);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	}

	@Override
	public Message deanTeaOutput() {
		// ��ȡԺϵ������ʦ��Ϣ
		message = new Message();
		return null;
	}

	@Override
	public Message studentOutput(String limitType, String limitation) {
		// ��ȡ���ݿ�������ѧ���б���Ϣ
		message = new Message();
		ds = new SqlHelper();
		if (limitType.equals("dean")) {
			sql = "select num,name,sex,year,address,homeNumber,phoneNumber,"
					+ "email,dean,credit,creOfOption from Student where dean=?";
			paras = new String[] { limitation };
		} else {
			sql = "select num,name,sex,year,address,homeNumber,phoneNumber,"
					+ "email,dean,credit,creOfOption from Student where 1=?";
			paras = new String[] { "1" };
		}

		try {
			rs = ds.query(sql, paras);
			ArrayList<Student> student = new ArrayList<Student>();
			int i = 0;
			while (rs.next()) {
				Student s = new Student();
				s.setNum(rs.getString(1));
				s.setName(rs.getString(2));
				s.setSex(rs.getString(3));
				s.setYear(rs.getString(4));
				s.setAddress(rs.getString(5));
				s.setHomeNumber(rs.getString(6));
				s.setPhoneNumber(rs.getString(7));
				s.setEmail(rs.getString(8));
				s.setDean(rs.getString(9));
				s.setCredit(rs.getString(10));
				s.setCreOfOption(rs.getString(11));
				student.add(s);
				i++;
			}
			if (i > 0) {
				ArrayList<String> attriOfStu = new ArrayList<String>();
				attriOfStu.add("ѧ��");
				attriOfStu.add("����");
				attriOfStu.add("�Ա�");
				attriOfStu.add("��ѧ���");
				attriOfStu.add("��ַ");
				attriOfStu.add("��ͥ�绰");
				attriOfStu.add("�ֻ�");
				attriOfStu.add("����");
				attriOfStu.add("Ժϵ");
				attriOfStu.add("ѧ�ּ�");
				attriOfStu.add("ͨʶ��ѧ��");
				message.setAttriOfStu(attriOfStu);
				message.setStudents(student);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	}

	@Override
	public Message courseSelectOutput(String limitType, String limitation) {
		// ��ȡѡ�μ�¼�б���Ϣ
		message = new Message();
		ds = new SqlHelper();
		if (limitType.equals("stuNum")) {
			sql = "select cNum,score from SelRecord where sNum=?";
			paras = new String[] { limitation };
		} else if (limitType.equals("courseNum")) {
			sql = "select sNum,score from SelRecord where cNum=?";
			paras = new String[] { limitation };
		} else {
			sql = "select sNum,cNum,score from SelRecord where 1=?";
			paras = new String[] { "1" };
		}
		return null;
	}

	public Message teachingProOutput(String limitType, String limitation) {
		message = new Message();
		ds = new SqlHelper();
		if (limitType.equals("dean")) {
			sql = "select dean, cName,credit from TeachingPro where dean=?";
			paras = new String[] { limitation };
		} else {
			sql = "select dean,cName,credit from TeachingPro where 1=?";
			paras = new String[] { limitation };
		}

		try {
			rs = ds.query(sql, paras);
			ArrayList<TeachingPro> arr_TeachingPro = new ArrayList<TeachingPro>();
			int i = 0;
			while (rs.next()) {
				TeachingPro teachingPro = new TeachingPro();
				teachingPro.setCourseDean(rs.getString(1));
				teachingPro.setCourseName(rs.getString(2));
				teachingPro.setCourseCredit(rs.getString(3));
				arr_TeachingPro.add(teachingPro);
				i++;
			}
			if (i > 0) {
				ArrayList<String> attriOfTP = new ArrayList<String>();
				attriOfTP.add("�γ�����");
				attriOfTP.add("ѧ��");
				attriOfTP.add("Ժϵ");
				message.setTeachingPro(arr_TeachingPro);
				message.setAttriOfTP(attriOfTP);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	}

	public Message courseOutput(String limitType, String limitation) {
		message = new Message();
		ds = new SqlHelper();
		if (limitType.equals("dean")) {// ��ʾĳ��Ժ�Ŀγ��б�
			sql = "select num,name,place,time,tName,credit,isOptional,isZhunru,"
					+ "isTongshi,isGongxuan,isXinsheng,dean from Course where dean=?";
			paras = new String[] { limitation };
		} else if (limitType.equals("teacher")) {// ��ʾĳ����ʦ���ο��б�
			sql = "select num,name,place,time,tName,credit,isOptional,isZhunru,"
					+ "isTongshi,isGongxuan,isXinsheng,dean from Course where tNum=?";
			paras = new String[] { limitation };
		} else if (limitType.equals("deanAndOptional")) {
			sql = "select num,name,place,time,tName,credit,isOptional,isZhunru,"
					+ "isTongshi,isGongxuan,isXinsheng,dean from Course where dean=? and isOptional=?";
			paras = new String[] { limitation, "��" };

		} else {// ��ʾȫ���γ��б�
			sql = "select num,name,place,time,tName,credit,isOptional,isZhunru,"
					+ "isTongshi,isGongxuan,isXinsheng,dean from Course where 1=?";
			paras = new String[] { "1" };
		}

		try {
			rs = ds.query(sql, paras);
			ArrayList<Course> courses = new ArrayList<Course>();
			int i = 0;
			String isOptional = "";
			String isZhunru = "";
			String isTongshi = "";
			String isGongxuan = "";
			String isXinsheng = "";

			while (rs.next()) {
				Course c = new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));

				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));
				isOptional = rs.getString(7);
				isZhunru = rs.getString(8);
				isTongshi = rs.getString(9);
				isGongxuan = rs.getString(10);
				isXinsheng = rs.getString(11);
				c.setDean(rs.getString(12));
				if (isOptional.equals("��")) {
					if (isTongshi.equals("��")) {
						c.setProperty("ͨʶ");
					}
					if (isGongxuan.equals("��")) {
						c.setProperty("��ѡ");
					}
					if (isXinsheng.equals("��")) {
						c.setProperty("��������");
					}
				} else {
					if (isZhunru.equals("��")) {
						c.setProperty("רҵ/׼��");
					} else {
						c.setProperty("ͨ��");
					}

				}
				courses.add(c);
				i++;
			}
			if (i > 0) {
				ArrayList<String> attriOfCourse = new ArrayList<String>();
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("�γ�����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ον�ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("�γ�����");

				message.setAttriOfCourse(attriOfCourse);
				message.setCourses(courses);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	}

	public Message outputCourseOfStudents(String courseID) {
		message = new Message();
		ds = new SqlHelper();
		sql = "select sNum from selRecord where cNum=?";
		paras = new String[] { courseID };

		try {
			rs = ds.query(sql, paras);
			ArrayList<Student> arr_StudentList = new ArrayList<Student>();
			int studentNum = 0;
			ArrayList<String> stuNum = new ArrayList<String>();
			while (rs.next()) {
				String studentID = rs.getString(1);
				stuNum.add(studentID);
				studentNum++;
			}
			String sql_Student = "select num,name,sex,year,address,homeNumber,phoneNumber,"
					+ "email,dean,credit,creOfOption from Student where num=?";
			for (String str : stuNum) {
				String paras_Student[] = new String[] { str };
				ResultSet rs_Student = ds.query(sql_Student, paras_Student);
				if (rs_Student.next()) {
					Student s = new Student();
					s.setNum(rs_Student.getString(1));
					s.setName(rs_Student.getString(2));
					s.setSex(rs_Student.getString(3));
					s.setYear(rs_Student.getString(4));
					s.setAddress(rs_Student.getString(5));
					s.setHomeNumber(rs_Student.getString(6));
					s.setPhoneNumber(rs_Student.getString(7));
					s.setEmail(rs_Student.getString(8));
					s.setDean(rs_Student.getString(9));
					s.setCredit(rs_Student.getString(10));
					s.setCreOfOption(rs_Student.getString(11));
					arr_StudentList.add(s);
				}

			}

			if (studentNum > 0) {
				ArrayList<String> attriOfStu = new ArrayList<String>();
				attriOfStu.add("ѧ��");
				attriOfStu.add("����");
				attriOfStu.add("�Ա�");
				attriOfStu.add("��ѧ���");
				attriOfStu.add("��ַ");
				attriOfStu.add("��ͥ�绰");
				attriOfStu.add("�ֻ�");
				attriOfStu.add("����");
				attriOfStu.add("Ժϵ");
				attriOfStu.add("ѧ�ּ�");
				attriOfStu.add("ͨʶ��ѧ��");
				message.setAttriOfStu(attriOfStu);
				message.setStudents(arr_StudentList);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	} // end outputCourseOfStudents method

	public Message outputCourseDetail(String courseNum) {
		message = new Message();
		ds = new SqlHelper();
		sql = "select num,name,place,time,tNum,tName,BriefDes,credit,isOptional,isZhunru,"
				+ "isTongshi,isGongxuan,isXinsheng,dean,limit from Course where num=?";
		paras = new String[] { courseNum };
		Course c = new Course();
		try {
			rs = ds.query(sql, paras);
			String isOptional = "";
			String isZhunru = "";
			String isTongshi = "";
			String isGongxuan = "";
			String isXinsheng = "";

			while (rs.next()) {
				int i = 1;
				c.setNum(rs.getString(i++));
				c.setName(rs.getString(i++));
				c.setPlace(rs.getString(i++));
				c.setTime(rs.getString(i++));
				c.setTeaNum(rs.getString(i++));
				c.setTeaName(rs.getString(i++));
				c.setDetail(rs.getString(i++));
				c.setCredit(rs.getString(i++));
				isOptional = rs.getString(i++);
				isZhunru = rs.getString(i++);
				isTongshi = rs.getString(i++);
				isGongxuan = rs.getString(i++);
				isXinsheng = rs.getString(i++);
				c.setDean(rs.getString(i++));
				c.setLimit(rs.getString(i++));
				if (isOptional.equals("��")) {
					if (isTongshi.equals("��")) {
						c.setProperty("ͨʶ��");
					}
					if (isGongxuan.equals("��")) {
						c.setProperty("��ѡ��");
					}
					if (isXinsheng.equals("��")) {
						c.setProperty("�������ֿ�");
					}
				} else {
					if (isZhunru.equals("��")) {
						c.setProperty("רҵ׼��\\׼����");
					} else {
						c.setProperty("ͨ�޿�");
					}
				}
				message.setCour(c);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
	} // end outputCourseDetail method
	
}
