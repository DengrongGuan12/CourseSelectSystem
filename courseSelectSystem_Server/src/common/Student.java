package common;





public class Student extends User{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dean;
	String credit;
	String year;
	String grade;
	String address;
	String homeNumber;//��ͥ�绰
	String score;
	boolean canSelect;
	String nameOfSecPro;//�ڶ�רҵ������
	String creOfSecPro;//�ڶ�רҵ��ѧ��
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


	String creOfPrePro;//׼��ѧ��
	String creOfBiye;//��ҵ��ѧ��
	public String getNameOfSecPro() {
		return nameOfSecPro;
	}


	public void setNameOfSecPro(String nameOfSecPro) {
		this.nameOfSecPro = nameOfSecPro;
	}


	public boolean isCanSelect() {
		return canSelect;
	}


	public void setCanSelect(boolean canSelect) {
		this.canSelect = canSelect;
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


	String phoneNumber;//�ֻ���
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


	String email;
	int  term;
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


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getCreOfOption() {
		return creOfOption;
	}


	public void setCreOfOption(String creOfOption) {
		this.creOfOption = creOfOption;
	}


	String creOfOption;//ͨʶ����ѧ��

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
