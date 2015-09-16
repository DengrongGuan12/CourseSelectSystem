package common;


public class Student extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dean;
	String credit;
	String grade;
	String year;
	String address;
	String homeNumber;
	String score;//记录某门课程的成绩
	String creOfOption;
	boolean canSelect;
	String nameOfSecPro;//第二专业的名称
	public String getNameOfSecPro() {
		return nameOfSecPro;
	}


	public void setNameOfSecPro(String nameOfSecPro) {
		this.nameOfSecPro = nameOfSecPro;
	}


	public String getCreOfSecPro() {
		return creOfSecPro;
	}


	public void setCreOfSecPro(String creOfSecPro) {
		this.creOfSecPro = creOfSecPro;
	}


	public String getCreOfPrePro() {
		return creOfPrePro;
	}


	public void setCreOfPrePro(String creOfPrePro) {
		this.creOfPrePro = creOfPrePro;
	}


	public String getCreOfBiye() {
		return creOfBiye;
	}


	public void setCreOfBiye(String creOfBiye) {
		this.creOfBiye = creOfBiye;
	}


	String creOfSecPro;//第二专业的学分
	String creOfPrePro;//准出学分
	String creOfBiye;//毕业总学分
	public boolean isCanSelect() {
		return canSelect;
	}


	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
	}


	public String getCreOfOption() {
		return creOfOption;
	}


	public void setCreOfOption(String creOfOption) {
		this.creOfOption = creOfOption;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHomeNumber() {
		return homeNumber;
	}


	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	String phoneNumber;
	String email;
	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	int term;
	public int getTerm() {
		return term;
	}


	public void setTerm(int term) {
		this.term = term;
	}

	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getCredit() {
		return credit;
	}


	public void setCredit(String credit) {
		this.credit = credit;
	}


	public String getDean() {
		return dean;
	}


	public void setDean(String dean) {
		this.dean = dean;
	}


}
