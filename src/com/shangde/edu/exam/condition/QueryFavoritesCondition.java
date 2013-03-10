package com.shangde.edu.exam.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageResult;

/***
 * 
 * @author yanghaibio
 *
 */
public class QueryFavoritesCondition extends PageResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 试卷类型
	 */
	private int type;
	
	/**
	 * 试卷级别
	 */
	private int level;
	
	/**
	 * 试卷ID
	 */
	private int epId;
	
	/**
	 * 用户Id
	 */
	private int userId;
	
	/**
	 * 试卷难易系数
	 */
	private int coeffcient;
	
	/**
	 * 试题ID
	 */
	private int questionID;
	
	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getCoeffcient() {
		return coeffcient;
	}

	public void setCoeffcient(int coeffcient) {
		this.coeffcient = coeffcient;
	}

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
