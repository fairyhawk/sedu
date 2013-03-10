package com.shangde.edu.cus.domain;

import java.io.Serializable;

/**
 * 
 * @author Yangning
 * @createDate:2011-11-09
 * @desc:用户反馈实体类
 */
public class CustomerFeedback implements Serializable{

	private static final long serialVersionUID = 7900573039099321717L;
	
	private int id;
	
	private String email;
	
	private int qq;
	
	private String mobile;
	
	private int province;
	
	private int city;
	 
	/**--项目专业Id--**/
	private int subject_id;
	
	/**--用户反馈描述信息--**/
	private String description;
	
	/**运营商id**/
	private int sp;
	/**网络带宽**/
	private int bandwidth;
	
	/**专业名称**/
	private String subjectname;
	
	/**省份名**/
	private String provincename;
	
	/**城市名**/
	private String cityname;
	
	/**反馈信息状态 0未解决 1已解决**/
	private int status;
	/**解决部门**/
	private String soldept;
	/**解决方式**/
	private String soltype;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSoldept() {
		return soldept;
	}

	public void setSoldept(String soldept) {
		this.soldept = soldept;
	}

	public String getSoltype() {
		return soltype;
	}

	public void setSoltype(String soltype) {
		this.soltype = soltype;
	}
	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
