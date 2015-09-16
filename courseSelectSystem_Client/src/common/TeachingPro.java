package common;

import java.io.Serializable;

public class TeachingPro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String courseCredit;
	private String courseDean;
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}
	
	public String getCourseCredit() {
		return this.courseCredit;
	}
	
	public void setCourseDean(String courseDean) {
		this.courseDean = courseDean;
	}
	
	public String getCourseDean() {
		return this.courseDean;
	}
	
}
