package com.shangde.edu.dis.dto;

public class DisAreaDTO implements java.io.Serializable {

	/**
	 * 记录id
	 */
	private int id;

	/**
	 * 专业名称
	 */
	private String subjectName;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * ...
	 */
	private String loginName;

	/**
	 * 话题个数
	 */
	private int topicCount;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 专业id
	 */
	private int subjectId;

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
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * @param subjectName
	 *            the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName
	 *            the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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