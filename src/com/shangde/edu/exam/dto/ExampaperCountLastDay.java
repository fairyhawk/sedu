package com.shangde.edu.exam.dto;

import java.io.Serializable;

/**
 * 每日试卷更新DTO类
 * @author 王超
 *
 */
public class ExampaperCountLastDay implements Serializable{
	/**
	 * 试卷数量
	 */
	private int count;
	/**
	 * 专业名称
	 */
	private String subjectName;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
