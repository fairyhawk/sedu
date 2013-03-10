package com.shangde.edu.exam.domain;

import java.io.Serializable;
import java.util.Date;

/***
 * 
 * @author yanghaibio
 *
 */
public class FavoritesPaper implements Serializable {
	
	
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
	 * 专业名称
	 */
	private String subjectName;
	
	/**
	 * 试卷名称
	 */
	private String paperName;
	
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
	private int paperId;
	
	/**
	 * 试卷是否被用户收藏
	 */
	private String flag;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 删除时间
	 */
	private Date deleteDate;
	
	/**
	 * 积分
	 */
	private int jifen;
	
	/**
	 * 试卷难度
	 */
	private int coeffcient;
	
	/**
	 * 试卷参考人数
	 */
	private int joinNum;

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public int getCoeffcient() {
		return coeffcient;
	}

	public void setCoeffcient(int coeffcient) {
		this.coeffcient = coeffcient;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
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

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
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
	

}
