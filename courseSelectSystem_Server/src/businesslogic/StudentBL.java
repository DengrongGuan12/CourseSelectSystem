package businesslogic;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;








import common.Course;
import common.Message;
import common.MessageType;
import common.Student;
import common.User;
import db.SqlHelper;
import dbservice.DbService;


import businesslogicservice.StudentBLService;

@SuppressWarnings("serial")
public class StudentBL extends UnicastRemoteObject implements StudentBLService{
	
	public StudentBL() throws RemoteException{
		super();
	}
	
	

	
	public Message checkUserLog(String num, String passwd) throws RemoteException{
		DbService ds=new SqlHelper();
		Message message = new Message();
		
		String passwd1 = "";
		
		String name = "";
		String year="";
		String dean="";
		String sex="";
		String address="";
		String homeNumber="";
		String phoneNumber="";
		String email="";
		String creOfOption="";
		String nameOfSecPro="";
		String creOfSecPro="";
		String creOfPrePro="";
		String creOfBiye="";
		try {
			        String sql = "select name,passwd,year,dean,sex,address,homeNumber,phoneNumber,email,creOfOption,nameOfSecPro,creOfSecPro,creOfPrePro,creOfBiye" +
			        		" from Student  where num=?";
					String[] paras = { num };
					
					ResultSet rs = ds.query(sql, paras);
					if (rs.next()) {
						name = rs.getString(1);
						passwd1 = rs.getString(2);
						year=rs.getString(3);
						dean=rs.getString(4);
						sex=rs.getString(5);
						address=rs.getString(6);
						homeNumber=rs.getString(7);
						phoneNumber=rs.getString(8);
						email=rs.getString(9);
						creOfOption=rs.getString(10);
						nameOfSecPro=rs.getString(11);
						creOfSecPro=rs.getString(12);
						creOfPrePro=rs.getString(13);
						creOfBiye=rs.getString(14);
						if (passwd.equals(passwd1)) {
							User stu=new Student();
							Calendar cal=Calendar.getInstance();
							int yearnow=cal.get(Calendar.YEAR);
							int year1=Integer.parseInt(year);
							int monthnow=cal.get(Calendar.MONTH);
							int grade=0;
							int term=0;
							if(year1==yearnow){
								grade=1;
								
							}else {
								grade=yearnow-year1+1;
								if((monthnow-9)<0){
									grade--;
								}
							}
							
							if((monthnow>=9&&monthnow<=12)||(monthnow>=1&&monthnow<3)){
								term=1;
							}else {
								term=2;
							}
							
							String grd=grade+"";
							((Student)stu).setTerm(term);
							((Student)stu).setGrade(grd);
							stu.setPasswd(passwd1);
							stu.setName(name);
							stu.setNum(num);
							((Student)stu).setDean(dean);
							((Student)stu).setYear(year);
							((Student)stu).setSex(sex);
							((Student)stu).setAddress(address);
							((Student)stu).setHomeNumber(homeNumber);
							((Student)stu).setPhoneNumber(phoneNumber);
							((Student)stu).setEmail(email);
							((Student)stu).setCanSelect(ds.judgeTempExist());
							((Student)stu).setCreOfBiye(creOfBiye);
							((Student)stu).setCreOfOption(creOfOption);
							((Student)stu).setCreOfPrePro(creOfPrePro);
							((Student)stu).setCreOfSecPro(creOfSecPro);
							((Student)stu).setNameOfSecPro(nameOfSecPro);
							message.setU(stu);
							message.setMesType(MessageType.stu_log_success);
							
							
							
							
						} else {
							message.setMesType(MessageType.stu_log_fail);
							
							
						}
					} else {
						message.setMesType(MessageType.stu_log_fail);
						
						

					}

			} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
			} finally {
				ds.close();
			}

			

			
		
		return message;
	}

	
	public Message ChangePasswd(String num, String passwd)throws RemoteException {
		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			String sql="update Student set passwd=? where num=?";
			String []paras={passwd,num};
			if(ds.exeUpdate(sql, paras)){
				String sql2="update Student set passwd=rtrim(passwd)";
				ds.trim(sql2);
				message.setMesType(MessageType.stu_changePasswd_success);
			}else {
				message.setMesType(MessageType.stu_changePasswd_fail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	}


	public Message showAllTongshi() throws RemoteException{//显示全校的通识课
		DbService ds=new SqlHelper();
		Message message = new Message();
		
		try {
			String sql="select num,name,place,time,tName,credit,dean,limit from Course where isTongshi=?";
			String paras[]={"是"};
			
			
			ResultSet rs=ds.query(sql, paras);
			ArrayList<Course> courses=new ArrayList<Course>();
			int i=0;
			while(rs.next()){
				Course c=new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));
				
				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));
				
				c.setDean(rs.getString(7));
				c.setLimit(rs.getString(8));
				courses.add(c);
				i++;
			}
			if(i>0){
				message.setMesType(MessageType.stu_show_allCourseSuccess);
				ArrayList<String> attriOfCourse=new ArrayList<String>();
				attriOfCourse.add("课程号");
				attriOfCourse.add("课程名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课教师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("人数");
				message.setAttri(attriOfCourse);
				message.setCourses(courses);
				
				
			}else {
				message.setMesType(MessageType.stu_show_allCourseFail);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		
		return message;
	}
	
	public Message showAllGongxuan() throws RemoteException {//显示全校的公选课
		DbService ds = new SqlHelper();
		Message message = new Message();

		try {
			String sql = "select num,name,place,time,tName,credit,dean,limit from Course where isGongxuan=?";
			String paras[] = { "是" };

			ResultSet rs = ds.query(sql, paras);
			ArrayList<Course> courses = new ArrayList<Course>();
			int i = 0;
			while (rs.next()) {
				Course c = new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));

				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));

				c.setDean(rs.getString(7));
				c.setLimit(rs.getString(8));
				courses.add(c);
				i++;
			}
			if (i > 0) {
				message.setMesType(MessageType.stu_show_allCourseSuccess);
				ArrayList<String> attriOfCourse = new ArrayList<String>();
				attriOfCourse.add("课程号");
				attriOfCourse.add("课程名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课教师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("人数");
				message.setAttri(attriOfCourse);
				message.setCourses(courses);

			} else {
				message.setMesType(MessageType.stu_show_allCourseFail);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;

	}
	
	public Message showAllXinsheng() throws RemoteException{//显示全部的新生研讨课
		DbService ds = new SqlHelper();
		Message message = new Message();

		try {
			String sql = "select num,name,place,time,tName,credit,dean,limit from Course where isXinsheng=?";
			String paras[] = { "是" };

			ResultSet rs = ds.query(sql, paras);
			ArrayList<Course> courses = new ArrayList<Course>();
			int i = 0;
			while (rs.next()) {
				Course c = new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));

				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));

				c.setDean(rs.getString(7));
				c.setLimit(rs.getString(8));
				courses.add(c);
				i++;
			}
			if (i > 0) {
				message.setMesType(MessageType.stu_show_allCourseSuccess);
				ArrayList<String> attriOfCourse = new ArrayList<String>();
				attriOfCourse.add("课程号");
				attriOfCourse.add("课程名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课教师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("人数");
				message.setAttri(attriOfCourse);
				message.setCourses(courses);

			} else {
				message.setMesType(MessageType.stu_show_allCourseFail);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}

		return message;
		
	}
	
	public Message showKuaZhuanYe(String dean)throws RemoteException{//显示对应院系的跨专业课
		
				
				Message message = new Message();
				DbService ds = new SqlHelper();

				try {
					String sql = "select num,name,place,time,tName,credit,dean from Course where isZhunru=? and dean=?";
					String paras[] = { "是" ,dean};

					ResultSet rs = ds.query(sql, paras);
					ArrayList<Course> courses = new ArrayList<Course>();
					int i = 0;
					while (rs.next()) {
						Course c = new Course();
						c.setNum(rs.getString(1));
						c.setName(rs.getString(2));
						c.setPlace(rs.getString(3));
						c.setTime(rs.getString(4));

						c.setTeaName(rs.getString(5));
						c.setCredit(rs.getString(6));

						c.setDean(rs.getString(7));
						
						courses.add(c);
						i++;
					}
					if (i > 0) {
						message.setMesType(MessageType.stu_show_allCourseSuccess);
						ArrayList<String> attriOfCourse = new ArrayList<String>();
						attriOfCourse.add("课程号");
						attriOfCourse.add("课程名称");
						attriOfCourse.add("地点");
						attriOfCourse.add("时间");
						attriOfCourse.add("任课教师");
						attriOfCourse.add("学分");
						attriOfCourse.add("院系");
						
						message.setAttri(attriOfCourse);
						message.setCourses(courses);

					} else {
						message.setMesType(MessageType.stu_show_allCourseFail);

					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					ds.close();
				}

				return message;
				
			
		
	}


	public Message showCourseDetail(String cNum)throws RemoteException {
		DbService ds=new SqlHelper();
		Message message = new Message();
		try {
			String sql="select BriefDes from Course where num=?";
			String paras[]={cNum};
			ResultSet rs=ds.query(sql, paras);
			if(rs.next()){
				message.setMesType(MessageType.stu_show_detailCourInfoSuccess);
				Course c=new Course();
				c.setDetail(rs.getString(1));
				message.setCour(c);
				
			}else {
				message.setMesType(MessageType.stu_show_detailCourInfoFail);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
			
		}
		
		
		return message;
	}


	//针对通识课，新生研讨课，和公选课的选课方法
	public Message selectCourse(User stu, Course course)throws RemoteException {//选择课程的方法，若选课时间冲突，则设置messagetype为“冲突信号：冲突的课程名字”
		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			//这个专门用来判断临时选课记录中的情况
			String sql="select c.num,c.name,c.time,c.isTongshi,c.isGongxuan,c.isXinsheng from Course c,TempRecord t where t.sNum=? and t.cNum=c.num";
			String paras[]={stu.getNum()};
			ResultSet rs=ds.query(sql, paras);
			//先判断能不能选课（选课的数量有没有超过限制），再判断是否时间冲突（只是提示可能！）
			int i=0;//记录公选课的数量
			int j=0;//记录通识课加新生研讨课的数量
			String cNum="";
			String cName="";
			String cTime="";
			String isTongshi="";
			String isGongxuan="";
			String isXinsheng="";
			ArrayList<Course> courses=new ArrayList<Course>();//用来记录与哪些课程有时间冲突
			while(rs.next()){
				cNum=rs.getString(1);
				cName=rs.getString(2);
				cTime=rs.getString(3);
				isTongshi=rs.getString(4);
			    isGongxuan=rs.getString(5);
				isXinsheng=rs.getString(6);
				if(isTongshi.equals("是")||isXinsheng.equals("是")){
					j++;
				}
				if(isGongxuan.equals("是")){
					i++;
				}
				
				if(cNum.equals(course.getNum())){
					message.setMesType(MessageType.stu_selectCourseFail_HasSelected);
					break;
					
				}else {
					String[] t1=cTime.split(" ");
					String[] t2=course.getTime().split(" ");
					if(this.judgeIfConflict(t1[0], t2[0])){
						message.setMesType(MessageType.stu_selectCourseFail_timeconflict);
						Course c=new Course();
						c.setName(cName);
						courses.add(c);
						
						
						
					}
					
				}
				
			}
			if(i==4||j==4){
				message.setMesType(MessageType.stu_selectCourseFail_More);
				
			}else if(message.getMesType()==null||message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){

				//在实际的选课记录中判断是否有时间冲突（即对必修课和跨专业课的判断）
				String sql2="select c.name,c.time from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
				String paras2[]={stu.getNum()};
				ResultSet rs2=ds.query(sql2, paras2);
				String cName1="";
				String cTime1="";
				while(rs2.next()){
					cName1=rs2.getString(1);
					cTime1=rs2.getString(2);
					String [] t1=cTime1.split(" ");
					String [] t2=course.getTime().split(" ");
					if(this.judgeIfConflict(t1[0], t2[0])){
						message.setMesType(MessageType.stu_selectCourseFail_timeconflict);
						Course c=new Course();
						c.setName(cName1);
						courses.add(c);
					}
				}

				String sql1="insert into TempRecord values(?,?,?,?)";
				String paras1[]={((Student)stu).getGrade(),stu.getNum(),course.getNum(),((Student)stu).getCreOfOption()};
				if(ds.exeUpdate(sql1, paras1)){
					if(message.getMesType()==null){
						message.setMesType(MessageType.stu_selectCourseSuccess);
					}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
						message.setCourses(courses);
					}
					
					
				}else {
					message.setMesType(MessageType.internetFail);
				}
				
			
				
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		return message;
	}
	//学生选择跨专业课的方法
	public Message selectCourse(String sNum,Course course)throws RemoteException{
		//选择课程的方法，若选课时间冲突，则设置messagetype为“冲突信号：冲突的课程名字”
				Message message = new Message();
				DbService ds=new SqlHelper();
				try {
					//这个专门用来判断实际选课记录中的情况（有无选择该门课程和是否时间冲突）
					String sql="select c.num,c.name,c.time from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
					String paras[]={sNum};
					ResultSet rs=ds.query(sql, paras);
					//先判断能不能选课(是否选择过该门课程)
				
					String cNum="";
					String cName="";
					String cTime="";
					
					ArrayList<Course> courses=new ArrayList<Course>();//用来记录与哪些课程有时间冲突
					while(rs.next()){
						cNum=rs.getString(1);
						cName=rs.getString(2);
						cTime=rs.getString(3);
	
						if(cNum.equals(course.getNum())){
							message.setMesType(MessageType.stu_selectCourseFail_HasSelected);
							break;
							
						}else {
							String[] t1=cTime.split(" ");
							String[] t2=course.getTime().split(" ");
							if(this.judgeIfConflict(t1[0], t2[0])){
								message.setMesType(MessageType.stu_selectCourseFail_timeconflict);
								Course c=new Course();
								c.setName(cName);
								courses.add(c);
								
								
								
							}
							
						}
						
					}
					if(message.getMesType()==null||message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){

						//在临时的选课记录中判断是否有时间冲突（即对选修课的判断）
						String sql2="select c.name,c.time from Course c,TempRecord t where t.sNum=? and t.cNum=c.num";
						String paras2[]={sNum};
						ResultSet rs2=ds.query(sql2, paras2);
						String cName1="";
						String cTime1="";
						while(rs2.next()){
							cName1=rs2.getString(1);
							cTime1=rs2.getString(2);
							String [] t1=cTime1.split(" ");
							String [] t2=course.getTime().split(" ");
							if(this.judgeIfConflict(t1[0], t2[0])){
								message.setMesType(MessageType.stu_selectCourseFail_timeconflict);
								Course c=new Course();
								c.setName(cName1);
								courses.add(c);
							}
						}

						String sql1="insert into SelRecord values(?,?,?)";
						String paras1[]={sNum,course.getNum(),null};
						if(ds.exeUpdate(sql1, paras1)){
							if(message.getMesType()==null){
								message.setMesType(MessageType.stu_selectCourseSuccess);
							}else if(message.getMesType().equals(MessageType.stu_selectCourseFail_timeconflict)){
								message.setCourses(courses);
							}
							
							
						}else {
							message.setMesType(MessageType.internetFail);
						}
						
					
						
					
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					ds.close();
				}
				
				return message;
			
		
	}
	//课程时间的格式为英文版逗号
	private boolean judgeIfConflict(String t1,String t2){
		boolean b=false;
		String[] split1=t1.split(",");
		int n=split1.length;
		String [] split2=t2.split(",");
		int m=split2.length;
		if(!split1[0].equals(split2[0])){
			b=false;
		}else {
			for(int i=1;i<n;i++){
				for(int j=1;j<m;j++){
					if(split1[i].equals(split2[j])){
						b=true;
						break;
					}
				}
				if(b){
					break;
				}
			}
			
			
		}
		
		return b;
		
	}


	public Message quitCourse(User u, String courseNum) throws RemoteException{
		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			//判断是否是本院的必修课
			String sql="select isOptional,dean from Course where num=?";
			String[] paras={courseNum};
			ResultSet rs=ds.query(sql, paras);
			String isOptional="";
			String dean="";
			if(rs.next()){
				isOptional=rs.getString(1);
				dean=rs.getString(2);
				
			}
			String dean1=((Student)u).getDean();
			if(isOptional.equals("否")&&dean.equals(dean1)){
				message.setMesType(MessageType.stu_quitCourse_fail);
				
			}else {
				String sql1="delete from SelRecord where sNum=? and cNum=? ";
				String []paras1={u.getNum(),courseNum};
				ds.exeUpdate(sql1, paras1);
				
				String sql2="delete from TempRecord where sNum=? and cNum=? ";
				String []paras2={u.getNum(),courseNum};
				ds.exeUpdate(sql2, paras2);
				message.setMesType(MessageType.stu_quitCourse_success);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	}
	
	public Message showAllCourse(String dean)throws RemoteException{
		Message message=new Message();
		DbService ds = new SqlHelper();

		try {
			String sql = "select num,name,place,time,tName,credit,isOptional,isZhunru,isTongshi,isGongxuan,isXinsheng,dean from Course where dean=?";
			String paras[] = {dean};

			ResultSet rs = ds.query(sql, paras);
			ArrayList<Course> courses = new ArrayList<Course>();
			int i = 0;
			String isOptional="";
			String isZhunru="";
			String isTongshi="";
			String isGongxuan="";
			String isXinsheng="";
			
			while (rs.next()) {
				Course c = new Course();
				c.setNum(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPlace(rs.getString(3));
				c.setTime(rs.getString(4));

				c.setTeaName(rs.getString(5));
				c.setCredit(rs.getString(6));
				isOptional=rs.getString(7);
				isZhunru=rs.getString(8);
				isTongshi=rs.getString(9);
				isGongxuan=rs.getString(10);
				isXinsheng=rs.getString(11);
				c.setDean(rs.getString(12));
				if(isOptional.equals("是")){
					if(isTongshi.equals("是")){
						c.setProperty("通识");
					}
					if(isGongxuan.equals("是")){
						c.setProperty("公选");
					}
					if(isXinsheng.equals("是")){
						c.setProperty("新生研讨");
					}
				}else {
					if(isZhunru.equals("是")){
						c.setProperty("专业/准入");
					}else{
						c.setProperty("通修");
					}
						
				}
				courses.add(c);
				i++;
			}
			if (i > 0) {
				message.setMesType(MessageType.stu_show_allCourseSuccess);
				ArrayList<String> attriOfCourse = new ArrayList<String>();
				attriOfCourse.add("课程号");
				attriOfCourse.add("课程名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课教师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("课程性质");
				
				message.setAttri(attriOfCourse);
				message.setCourses(courses);

			} else {
				message.setMesType(MessageType.stu_show_allCourseFail);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			ds.close();
		}
		return message;
	}


	public Message showGPA(String sNum)throws RemoteException {
		Message message = new Message();
		return message;
	}


	public Message showMyCourseList(String stuNum)throws RemoteException {
		Message message = new Message();
		DbService ds = new SqlHelper();
		DbService ds2=new SqlHelper();
		String paras[] = { stuNum };
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<String> attriOfCourse = new ArrayList<String>();
		int n = 0;
		try {
			String sql = "select c.num,c.name,c.place,c.time,c.tName,c.credit,c.isOptional," +
					"c.isZhunru,c.isTongshi,c.isGongxuan,c.isXinsheng,c.dean from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
			
			ResultSet rs = ds.query(sql, paras);
			
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
			if(ds.judgeTempExist()){
				
				String sql2="select c.num,c.name,c.place,c.time,c.tName,c.credit,c.dean from Course c,TempRecord t where t.sNum=? and t.cNum=c.num";
				ResultSet rs2=ds2.query(sql2, paras);
				while(rs2.next()){
					Course c=new Course();
					c.setNum(rs2.getString(1));
					c.setName(rs2.getString(2));
					c.setPlace(rs2.getString(3));
					c.setTime(rs2.getString(4));
					c.setTeaName(rs2.getString(5));
					c.setCredit(rs2.getString(6));
					c.setDean(rs2.getString(7));
					c.setProperty("待定");
					courses.add(c);
					n++;
					
				}
				
			}
			
			if(n==0){
				message.setMesType(MessageType.stu_showMyCourse_fail_noCourse);
			}else {//判断有无时间冲突
				attriOfCourse.add("课程号");
				attriOfCourse.add("名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课老师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("性质");
				Boolean b=false;
				
				ArrayList<ArrayList<Course>> arrayLists=new ArrayList<ArrayList<Course>>();//存储不同时间冲突的多门课程
				
				
				for(int i=0;i<n;i++){
					Course c=courses.get(i);
					String t1=c.getTime();
					for(int j=i+1;j<n;j++){
						Course c1=courses.get(j);
						String t2=c1.getTime();
						if(judgeIfConflict(t1, t2)){
							b=true;
							ArrayList<Course> courses3=new ArrayList<Course>();//存储同一时间冲突的课程
							courses3.add(c);
							courses3.add(c1);
							arrayLists.add(courses3);
							break;
							
						}
					}
				}
				if(b){
					message.setMesType(MessageType.stu_showMyCourse_fail_timeConflict);
					message.setArrayLists(arrayLists);
					message.setAttri(attriOfCourse);
					message.setCourses(courses);
				}else {
					message.setMesType(MessageType.stu_showMyCourse_success);
					message.setAttri(attriOfCourse);
					message.setCourses(courses);
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds2.close();
			ds.close();
		}
		return message;
	}


	public Message showMyScore(String stuNum)throws RemoteException {
		Message message = new Message();
		DbService ds = new SqlHelper();
		try {
			String sql = "select c.num,c.name,c.place,c.time,c.tName,c.credit,c.isOptional," +
					"c.isZhunru,c.isTongshi,c.isGongxuan,c.isXinsheng,c.dean,s.score from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
			String paras[] = { stuNum };
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
				String score=rs.getString(13);
				if(score==null){
					c.setScore("尚未公布");
				}else {
					c.setScore(score);
				}
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
				message.setMesType(MessageType.stu_showMyCourse_fail_noCourse);
			}else {
				attriOfCourse.add("课程号");
				attriOfCourse.add("名称");
				attriOfCourse.add("地点");
				attriOfCourse.add("时间");
				attriOfCourse.add("任课老师");
				attriOfCourse.add("学分");
				attriOfCourse.add("院系");
				attriOfCourse.add("性质");
				attriOfCourse.add("成绩");
				message.setMesType(MessageType.stu_showMyCourse_success);
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




	@Override
	public Message changeMyInfo(User u) throws RemoteException {
		Message message=new Message();
		DbService ds=new SqlHelper();
		Student s=(Student)u;
		try {
			String sql="update Student set name=?,sex=?,address=?,homeNumber=?,phoneNumber=?,email=? where num=?";
			String []paras={s.getName(),s.getSex(),s.getAddress(),s.getHomeNumber(),s.getPhoneNumber(),s.getEmail(),s.getNum()};
			if(ds.exeUpdate(sql, paras)){
				String sql2="update Student set name=rtrim(name),sex=rtrim(sex)," +
						"address=rtrim(address),homeNumber=rtrim(homeNumber),phoneNumber=rtrim(phoneNumber),email=rtrim(email)";
				ds.trim(sql2);
				message.setMesType(MessageType.stu_changeMyInfo_suc);
			}else {
				message.setMesType(MessageType.stu_changeMyInfo_fail);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return message;
	}
	
	

}
