package com.shangde.edu.cus.condition;

import com.shangde.common.vo.PageQuery;

public class QueryProblemCondition extends PageQuery{
    private int pblId;
    private int courseId;
    private int cusId;
    private String pblTitle;
    private int highProblem;
    private int pblSolv;
    private int pblHot;
    private int pblType;
    private int subjectId;
    private String subjectIds;
    private Integer officialCusId;
        
    public int getPblId(){
        return pblId;
    }

    public void setPblId(int pblId){
        this.pblId = pblId;
    }

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getPblTitle() {
		return pblTitle;
	}

	public void setPblTitle(String pblTitle) {
		this.pblTitle = pblTitle;
	}

	public int getHighProblem() {
		return highProblem;
	}

	public void setHighProblem(int highProblem) {
		this.highProblem = highProblem;
	}

	public int getPblSolv() {
		return pblSolv;
	}

	public void setPblSolv(int pblSolv) {
		this.pblSolv = pblSolv;
	}

	public int getPblType() {
		return pblType;
	}

	public void setPblType(int pblType) {
		this.pblType = pblType;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	public Integer getOfficialCusId() {
		return officialCusId;
	}

	public void setOfficialCusId(Integer officialCusId) {
		this.officialCusId = officialCusId;
	}

	
}