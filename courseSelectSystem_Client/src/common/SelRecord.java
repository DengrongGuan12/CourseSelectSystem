package common;

import java.io.Serializable;

public class SelRecord implements Serializable{
	
	private static final long serialVersionUID = 3516276788034426847L;
	
	private String stuNum;
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getcNum() {
		return cNum;
	}
	public void setcNum(String cNum) {
		this.cNum = cNum;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	private String cNum;
	private String credit;
	

}
