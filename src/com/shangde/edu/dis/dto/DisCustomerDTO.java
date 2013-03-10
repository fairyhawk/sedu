package com.shangde.edu.dis.dto;

import java.io.Serializable;

/**
 * 用于展示小组所属成员
 * 
 * @author Basil
 * 
 */
@SuppressWarnings("serial")
public class DisCustomerDTO implements Serializable {

	/**
	 * 用户id
	 */
	private int cusId;
	/**
	 * 用户名称
	 */
	private String cusName;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 头像地址
	 */
	private String photo;
	/**
	 * 专业id
	 */
	private int subjectId;

	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the cusName
	 */
	public String getCusName() {
		return cusName;
	}

	/**
	 * @param cusName
	 *            the cusName to set
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
