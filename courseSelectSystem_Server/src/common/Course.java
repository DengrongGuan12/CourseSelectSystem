package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String num;
	private String name;
	private String place;
	private String time;
	private String teaName;

	private String teaNum;
	private String credit;

	// 课程类型
	private String isOptional;
	private String isZhunru;
	private String isTongshi;
	private String isGongxuan;
	private String isXinsheng;

	private String dean;
	private String limit;
	private String detail;
	private String property;// 表示课程的性质是指选还是待定
	private String score;
	ArrayList<Student> students = new ArrayList<Student>();

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getDean() {
		return dean;
	}

	public void setDean(String dean) {
		this.dean = dean;
	}

	public String getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}

	public String getIsZhuanru() {
		return this.isZhunru;
	}

	public void setIsZhunru(String isZhunru) {
		this.isZhunru = isZhunru;
	}

	public String getIsTongshi() {
		return this.isTongshi;
	}

	public void setIsTongshi(String isTongshi) {
		this.isTongshi = isTongshi;
	}

	public String getIsGongxuan() {
		return this.isGongxuan;
	}

	public void setIsGongxuan(String isGongxuan) {
		this.isGongxuan = isGongxuan;
	}

	public String getIsXinsheng() {
		return this.isXinsheng;
	}

	public void setIsXinSheng(String isXinSheng) {
		this.isXinsheng = isXinSheng;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getTeaNum() {
		return teaNum;
	}

	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}

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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

}
