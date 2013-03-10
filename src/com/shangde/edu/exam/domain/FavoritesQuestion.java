package com.shangde.edu.exam.domain;

import java.io.Serializable;
import java.util.Date;

/***
 * 
 * @author yanghaibio 2012-07-11 11:32
 *
 */
public class FavoritesQuestion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 用户ID
	 */
	private int userId;
	
	/**
	 * 问题ID
	 */
	private int questionId;
	
	/**
	 * 是否被收藏
	 */
	private String flag;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 删除日期
	 */
	private Date deleteDate;
	
	/**
	 * 试题ID
	 */
	private int qstId;
	
	/**
	 * 试题名称
	 */
	private String qstContent;
	
	/**
	 * 试题类型
	 */
	private int qstType;
	
	/**
	 * 试题难度
	 */
	private int coeffcient;
	
	/**
	 * 试卷名称
	 */
	private String epName;
	
	/**
	 * 参与人数
	 */
	private int joinNum;
	/**
	 * 试卷ID
	 */
	private int epId;
	
	/**
	 * 试题参与人数
	 */
	private int doneNum;
	
	/**
	 * 专业ID
	 */
	private int subjectId;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}

	public String getQstContent() {
		return qstContent;
	}

	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public int getCoeffcient() {
		return coeffcient;
	}

	public void setCoeffcient(int coeffcient) {
		this.coeffcient = coeffcient;
	}

	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public int getDoneNum() {
		return doneNum;
	}

	public void setDoneNum(int doneNum) {
		this.doneNum = doneNum;
	}

}
