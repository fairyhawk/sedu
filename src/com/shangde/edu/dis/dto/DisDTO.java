package com.shangde.edu.dis.dto;

import java.io.Serializable;

import com.shangde.edu.dis.domain.Discussion;

public class DisDTO implements Serializable {

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 小组名称
	 */
	private String name;
	/**
	 * 专业id
	 */
	private int subjectId = -1;
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
	private int type = -1;
	/**
	 * 状态
	 */
	private int status = -1;
	/**
	 * 用户总数
	 */
	private int userCount;// 
	/**
	 * 用户id
	 */
	private int cusId;
	/**
	 * ...
	 */
	private int cudId;
	/**
	 * 小组id
	 */
	private int disId;

	/**
	 * 角色值
	 */
	private int auth;// 
	/**
	 * 帖子总数
	 */
	private int topicCount;//
	/**
	 * 推荐帖子 推荐总数
	 */
	private int topCount;// 
	/**
	 * 小组对象,根据dis_id查询
	 */
	private Discussion discussion;// 

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
		this.picurl = picurl;
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
	 * @return the userCount
	 */
	public int getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount
	 *            the userCount to set
	 */
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

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
	 * @return the cudId
	 */
	public int getCudId() {
		return cudId;
	}

	/**
	 * @param cudId
	 *            the cudId to set
	 */
	public void setCudId(int cudId) {
		this.cudId = cudId;
	}

	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}

	/**
	 * @param disId
	 *            the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}

	/**
	 * @return the auth
	 */
	public int getAuth() {
		return auth;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuth(int auth) {
		this.auth = auth;
	}

	/**
	 * @return the topicCount
	 */
	public int getTopicCount() {
		return topicCount;
	}

	/**
	 * @param topicCount
	 *            the topicCount to set
	 */
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	/**
	 * @return the topCount
	 */
	public int getTopCount() {
		return topCount;
	}

	/**
	 * @param topCount
	 *            the topCount to set
	 */
	public void setTopCount(int topCount) {
		this.topCount = topCount;
	}

	/**
	 * @return the discussion
	 */
	public Discussion getDiscussion() {
		return discussion;
	}

	/**
	 * @param discussion
	 *            the discussion to set
	 */
	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

}
