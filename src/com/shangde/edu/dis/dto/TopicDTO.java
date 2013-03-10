package com.shangde.edu.dis.dto;

import java.util.Date;

/**
 * 话题DTO
 * 
 * @author Libg
 * 
 */
public class TopicDTO implements java.io.Serializable {

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 小组名称
	 */
	private String disName;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 用户
	 */
	private String user;
	/**
	 * 所属板块
	 */
	private String area;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 回复次数
	 */
	private int replyCounts;
	/**
	 * 推荐次数
	 */
	private int recCount;
	/**
	 * 推荐积分
	 */
	private int recNum;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 用户名
	 */
	private String cusName;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 用户id
	 */
	private int cusId;
	/**
	 * 系统用户名称
	 */
	private String sysUserName;

	/**
	 * 帖子状态
	 */
	private int status;

	/**
	 * 修改时间
	 */
	private Date modified;

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
	 * @return the disName
	 */
	public String getDisName() {
		return disName;
	}

	/**
	 * @param disName
	 *            the disName to set
	 */
	public void setDisName(String disName) {
		this.disName = disName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * @return the replyCounts
	 */
	public int getReplyCounts() {
		return replyCounts;
	}

	/**
	 * @param replyCounts
	 *            the replyCounts to set
	 */
	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}

	/**
	 * @return the recCount
	 */
	public int getRecCount() {
		return recCount;
	}

	/**
	 * @param recCount
	 *            the recCount to set
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	/**
	 * @return the recNum
	 */
	public int getRecNum() {
		return recNum;
	}

	/**
	 * @param recNum
	 *            the recNum to set
	 */
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * @return the sysUserName
	 */
	public String getSysUserName() {
		return sysUserName;
	}

	/**
	 * @param sysUserName
	 *            the sysUserName to set
	 */
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
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
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

}
