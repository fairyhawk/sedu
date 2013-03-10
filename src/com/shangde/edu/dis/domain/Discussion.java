package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 讨论组
 * 
 * 
 */
public class Discussion implements Serializable {
	private static String DEFAULT_PIC = "http://import.highso.org.cn/test/upload/dis/discussion.gif";
	/**
	 * 
	 */
	private static final long serialVersionUID = 5732233606466236426L;
	/**
	 * id
	 */
	private int id;
	/**
	 * 小组名称
	 */
	private String name;
	/**
	 * 专业id
	 */
	private int subjectId;
	/**
	 * 专业名称
	 */
	private String subject;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 创建人
	 */
	private String creatuser;
	/**
	 * ...
	 */
	private String features;
	/**
	 * 介绍
	 */
	private String introduction;
	/**
	 * 图片地址
	 */
	private String picurl;
	/**
	 * ...
	 */
	private int inflag;
	/**
	 * 类型
	 */
	private int type;// 0固定小组，1自创建小组
	/**
	 * 状态
	 */
	private int status;// 0启用，1禁用
	/**
	 * 小组成员数
	 */
	private int member;

	/**
	 * @return the dEFAULT_PIC
	 */
	public static String getDEFAULT_PIC() {
		return DEFAULT_PIC;
	}

	/**
	 * @param default_pic
	 *            the dEFAULT_PIC to set
	 */
	public static void setDEFAULT_PIC(String default_pic) {
		DEFAULT_PIC = default_pic;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the creatuser
	 */
	public String getCreatuser() {
		return creatuser;
	}

	/**
	 * @param creatuser
	 *            the creatuser to set
	 */
	public void setCreatuser(String creatuser) {
		this.creatuser = creatuser;
	}

	/**
	 * @return the features
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(String features) {
		this.features = features;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction
	 *            the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl
	 *            the picurl to set
	 */
	public void setPicurl(String picurl) {
		if (picurl == null || picurl.equals("")) {
			this.picurl = DEFAULT_PIC;
		} else {
			this.picurl = picurl;
		}
	}

	/**
	 * @return the inflag
	 */
	public int getInflag() {
		return inflag;
	}

	/**
	 * @param inflag
	 *            the inflag to set
	 */
	public void setInflag(int inflag) {
		this.inflag = inflag;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the member
	 */
	public int getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(int member) {
		this.member = member;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
