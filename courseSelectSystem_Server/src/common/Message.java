package common;

import java.io.Serializable;
import java.util.ArrayList;


public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mesType;
	User u;
	Course cour;
	SelRecord selRecord;
	
	public SelRecord getSelRecord() {
		return selRecord;
	}

	public void setSelRecord(SelRecord selRecord) {
		this.selRecord = selRecord;
	}

	ArrayList<TeachingPro> teachingPro = new ArrayList<TeachingPro>();
    ArrayList<Course> courses=new ArrayList<Course>();
    ArrayList<String> attri=new ArrayList<String>();
    ArrayList<ArrayList<Course>> arrayLists=new ArrayList<ArrayList<Course>>();
    ArrayList<User> users=new ArrayList<User>();
    ArrayList<String> attriOfStu = new ArrayList<String>();
    ArrayList<String> attriOfDean = new ArrayList<String>();
    ArrayList<String> attriOfTP = new ArrayList<String>();
    public ArrayList<String> getAttriOfTP() {
		return attriOfTP;
	}

	public void setAttriOfTP(ArrayList<String> attriOfTP) {
		this.attriOfTP = attriOfTP;
	}

	public ArrayList<String> getAttriOfDean() {
		return attriOfDean;
	}

	public void setAttriOfDean(ArrayList<String> attriOfDean) {
		this.attriOfDean = attriOfDean;
	}

	public ArrayList<String> getAttriOfStu() {
		return attriOfStu;
	}

	public void setAttriOfStu(ArrayList<String> attriOfStu) {
		this.attriOfStu = attriOfStu;
	}

	public ArrayList<String> getAttriOfTea() {
		return attriOfTea;
	}

	public void setAttriOfTea(ArrayList<String> attriOfTea) {
		this.attriOfTea = attriOfTea;
	}

	public ArrayList<String> getAttriOfCourse() {
		return attriOfCourse;
	}

	public void setAttriOfCourse(ArrayList<String> attriOfCourse) {
		this.attriOfCourse = attriOfCourse;
	}

	public ArrayList<Dean> getDeans() {
		return deans;
	}

	public void setDeans(ArrayList<Dean> deans) {
		this.deans = deans;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public Dean getDean() {
		return dean;
	}

	public void setDean(Dean dean) {
		this.dean = dean;
	}

	ArrayList<String> attriOfTea = new ArrayList<String>();
    ArrayList<String> attriOfCourse = new ArrayList<String>();
    ArrayList<Dean> deans = new ArrayList<Dean>();
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    ArrayList<Student> students = new ArrayList<Student>();
    Dean dean = new Dean();
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<ArrayList<Course>> getArrayLists() {
		return arrayLists;
	}

	public void setArrayLists(ArrayList<ArrayList<Course>> arrayLists) {
		this.arrayLists = arrayLists;
	}

	public ArrayList<String> getAttri() {
		return attri;
	}

	public void setAttri(ArrayList<String> attri) {
		this.attri = attri;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public ArrayList<TeachingPro> getTeachingPro() {
		return this.teachingPro;
	}

	public void setTeachingPro(ArrayList<TeachingPro> teachingPro) {
		this.teachingPro = teachingPro;
	}

	
	public Course getCour() {
		return cour;
	}

	public void setCour(Course cour) {
		this.cour = cour;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	

}
