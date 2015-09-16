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


	public Message showAllTongshi() throws RemoteException{//��ʾȫУ��ͨʶ��
		DbService ds=new SqlHelper();
		Message message = new Message();
		
		try {
			String sql="select num,name,place,time,tName,credit,dean,limit from Course where isTongshi=?";
			String paras[]={"��"};
			
			
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
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("�γ�����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ον�ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("����");
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
	
	public Message showAllGongxuan() throws RemoteException {//��ʾȫУ�Ĺ�ѡ��
		DbService ds = new SqlHelper();
		Message message = new Message();

		try {
			String sql = "select num,name,place,time,tName,credit,dean,limit from Course where isGongxuan=?";
			String paras[] = { "��" };

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
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("�γ�����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ον�ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("����");
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
	
	public Message showAllXinsheng() throws RemoteException{//��ʾȫ�����������ֿ�
		DbService ds = new SqlHelper();
		Message message = new Message();

		try {
			String sql = "select num,name,place,time,tName,credit,dean,limit from Course where isXinsheng=?";
			String paras[] = { "��" };

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
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("�γ�����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ον�ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("����");
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
	
	public Message showKuaZhuanYe(String dean)throws RemoteException{//��ʾ��ӦԺϵ�Ŀ�רҵ��
		
				
				Message message = new Message();
				DbService ds = new SqlHelper();

				try {
					String sql = "select num,name,place,time,tName,credit,dean from Course where isZhunru=? and dean=?";
					String paras[] = { "��" ,dean};

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
						attriOfCourse.add("�γ̺�");
						attriOfCourse.add("�γ�����");
						attriOfCourse.add("�ص�");
						attriOfCourse.add("ʱ��");
						attriOfCourse.add("�ον�ʦ");
						attriOfCourse.add("ѧ��");
						attriOfCourse.add("Ժϵ");
						
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


	//���ͨʶ�Σ��������ֿΣ��͹�ѡ�ε�ѡ�η���
	public Message selectCourse(User stu, Course course)throws RemoteException {//ѡ��γ̵ķ�������ѡ��ʱ���ͻ��������messagetypeΪ����ͻ�źţ���ͻ�Ŀγ����֡�
		Message message = new Message();
		DbService ds=new SqlHelper();
		try {
			//���ר�������ж���ʱѡ�μ�¼�е����
			String sql="select c.num,c.name,c.time,c.isTongshi,c.isGongxuan,c.isXinsheng from Course c,TempRecord t where t.sNum=? and t.cNum=c.num";
			String paras[]={stu.getNum()};
			ResultSet rs=ds.query(sql, paras);
			//���ж��ܲ���ѡ�Σ�ѡ�ε�������û�г������ƣ������ж��Ƿ�ʱ���ͻ��ֻ����ʾ���ܣ���
			int i=0;//��¼��ѡ�ε�����
			int j=0;//��¼ͨʶ�μ��������ֿε�����
			String cNum="";
			String cName="";
			String cTime="";
			String isTongshi="";
			String isGongxuan="";
			String isXinsheng="";
			ArrayList<Course> courses=new ArrayList<Course>();//������¼����Щ�γ���ʱ���ͻ
			while(rs.next()){
				cNum=rs.getString(1);
				cName=rs.getString(2);
				cTime=rs.getString(3);
				isTongshi=rs.getString(4);
			    isGongxuan=rs.getString(5);
				isXinsheng=rs.getString(6);
				if(isTongshi.equals("��")||isXinsheng.equals("��")){
					j++;
				}
				if(isGongxuan.equals("��")){
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

				//��ʵ�ʵ�ѡ�μ�¼���ж��Ƿ���ʱ���ͻ�����Ա��޿κͿ�רҵ�ε��жϣ�
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
	//ѧ��ѡ���רҵ�εķ���
	public Message selectCourse(String sNum,Course course)throws RemoteException{
		//ѡ��γ̵ķ�������ѡ��ʱ���ͻ��������messagetypeΪ����ͻ�źţ���ͻ�Ŀγ����֡�
				Message message = new Message();
				DbService ds=new SqlHelper();
				try {
					//���ר�������ж�ʵ��ѡ�μ�¼�е����������ѡ����ſγ̺��Ƿ�ʱ���ͻ��
					String sql="select c.num,c.name,c.time from Course c,SelRecord s where s.sNum=? and s.cNum=c.num";
					String paras[]={sNum};
					ResultSet rs=ds.query(sql, paras);
					//���ж��ܲ���ѡ��(�Ƿ�ѡ������ſγ�)
				
					String cNum="";
					String cName="";
					String cTime="";
					
					ArrayList<Course> courses=new ArrayList<Course>();//������¼����Щ�γ���ʱ���ͻ
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

						//����ʱ��ѡ�μ�¼���ж��Ƿ���ʱ���ͻ������ѡ�޿ε��жϣ�
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
	//�γ�ʱ��ĸ�ʽΪӢ�İ涺��
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
			//�ж��Ƿ��Ǳ�Ժ�ı��޿�
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
			if(isOptional.equals("��")&&dean.equals(dean1)){
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
				if(isOptional.equals("��")){
					if(isTongshi.equals("��")){
						c.setProperty("ͨʶ");
					}
					if(isGongxuan.equals("��")){
						c.setProperty("��ѡ");
					}
					if(isXinsheng.equals("��")){
						c.setProperty("��������");
					}
				}else {
					if(isZhunru.equals("��")){
						c.setProperty("רҵ/׼��");
					}else{
						c.setProperty("ͨ��");
					}
						
				}
				courses.add(c);
				i++;
			}
			if (i > 0) {
				message.setMesType(MessageType.stu_show_allCourseSuccess);
				ArrayList<String> attriOfCourse = new ArrayList<String>();
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("�γ�����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ον�ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("�γ�����");
				
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
				if(isOPtional.equals("��")){
					if(isGongxuan.equals("��")){
						c.setProperty("��ѡ");
					}
					if(isTongshi.equals("��")){
						c.setProperty("ͨʶ");
					}
					if(isXinsheng.equals("��")){
						c.setProperty("��������");
					}
					
				}else{
					if(isZhunru.equals("��")){
						c.setProperty("׼��/רҵ");
					}else {
						c.setProperty("ͨ��");
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
					c.setProperty("����");
					courses.add(c);
					n++;
					
				}
				
			}
			
			if(n==0){
				message.setMesType(MessageType.stu_showMyCourse_fail_noCourse);
			}else {//�ж�����ʱ���ͻ
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ο���ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("����");
				Boolean b=false;
				
				ArrayList<ArrayList<Course>> arrayLists=new ArrayList<ArrayList<Course>>();//�洢��ͬʱ���ͻ�Ķ��ſγ�
				
				
				for(int i=0;i<n;i++){
					Course c=courses.get(i);
					String t1=c.getTime();
					for(int j=i+1;j<n;j++){
						Course c1=courses.get(j);
						String t2=c1.getTime();
						if(judgeIfConflict(t1, t2)){
							b=true;
							ArrayList<Course> courses3=new ArrayList<Course>();//�洢ͬһʱ���ͻ�Ŀγ�
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
					c.setScore("��δ����");
				}else {
					c.setScore(score);
				}
				if(isOPtional.equals("��")){
					if(isGongxuan.equals("��")){
						c.setProperty("��ѡ");
					}
					if(isTongshi.equals("��")){
						c.setProperty("ͨʶ");
					}
					if(isXinsheng.equals("��")){
						c.setProperty("��������");
					}
					
				}else{
					if(isZhunru.equals("��")){
						c.setProperty("׼��/רҵ");
					}else {
						c.setProperty("ͨ��");
					}
				}
				courses.add(c);
				n++;
				
				
				
			}
			
			if(n==0){
				message.setMesType(MessageType.stu_showMyCourse_fail_noCourse);
			}else {
				attriOfCourse.add("�γ̺�");
				attriOfCourse.add("����");
				attriOfCourse.add("�ص�");
				attriOfCourse.add("ʱ��");
				attriOfCourse.add("�ο���ʦ");
				attriOfCourse.add("ѧ��");
				attriOfCourse.add("Ժϵ");
				attriOfCourse.add("����");
				attriOfCourse.add("�ɼ�");
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
