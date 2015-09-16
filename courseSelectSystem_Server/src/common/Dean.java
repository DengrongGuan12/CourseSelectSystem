package common;

import java.io.Serializable;

public class Dean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name,zhunru,zhunchu,graduate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZhunru() {
		return zhunru;
	}

	public void setZhunru(String zhunru) {
		this.zhunru = zhunru;
	}

	public String getZhunchu() {
		return zhunchu;
	}

	public void setZhunchu(String zhunchu) {
		this.zhunchu = zhunchu;
	}

	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
}
