package com.shangde.edu.exam.dto;

import java.io.Serializable;

import com.shangde.edu.cou.domain.Coursesort;
/**
 * 前台试卷列表dto
 * @author 王超
 *
 */
public class ExampaperListDTO implements Serializable {
	
	/**
	 * 平均分
	 */
	private String avgfen;
	/**
	 * 当前用户考试次数
	 */
	private int counts;
	/**
	 * 试卷名称
	 */
	private String epName;
	/**
	 * 试题总数
	 */
	private int qstCount;
	/**
	 * 试卷id
	 */
	private int epId;
	/**
	 * 专业id
	 */
	private int subjectId;
	/**
	 * 课程表
	 */
	private Coursesort coursesort; 
	/**
	 * 课程类型
	 */
	private int sortId;
	/**
	 * 试卷积分
	 */
	private int jifen;
	/**
	 * 试卷难度
	 */
	private int coeffcient;
	/**
	 * 试卷考过人数
	 */
	private int joinNum;
	/**
	 * 试卷创建时间
	 */
	private java.util.Date createTime;
	

	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public int getEpId() {
		return epId;
	}
	public void setEpId(int epId) {
		this.epId = epId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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
	public int getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getAvgfen() {
		return avgfen;
	}
	public void setAvgfen(String avgfen) {
		if(avgfen==null){
			this.avgfen="0";
		}else{
		this.avgfen = avgfen;
		}
	}
	public Coursesort getCoursesort() {
		return coursesort;
	}
	public void setCoursesort(Coursesort coursesort) {
		this.coursesort = coursesort;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getQstCount() {
		return qstCount;
	}
	public void setQstCount(int qstCount) {
		this.qstCount = qstCount;
	}
	
}
