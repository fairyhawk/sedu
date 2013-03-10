package com.shangde.edu.exam.dto;

import java.io.Serializable;

public class SubjectExampaperNumDTO implements Serializable {

	private int subjectId;
	private String subjectName;
	private int num;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
