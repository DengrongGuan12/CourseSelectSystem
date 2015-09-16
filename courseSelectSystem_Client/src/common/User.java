package common;

import java.io.Serializable;


public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String name;
	String passwd;
    String num;
	String sex;
	String dean;
	public String getDean() {
		return dean;
	}
	public void setDean(String dean) {
		this.dean = dean;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

//	String grade;
//	public String getGrade() {
//		return grade;
//	}
//	public void setGrade(String grade) {
//		this.grade = grade;
//	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
