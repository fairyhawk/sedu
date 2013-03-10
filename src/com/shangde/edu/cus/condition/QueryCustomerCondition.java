package com.shangde.edu.cus.condition;

import com.shangde.common.vo.PageQuery;

public class QueryCustomerCondition extends PageQuery{
    private int cusId;
    private String cusName;
    private String cusPwd;
    private String realName;
    private Integer sex;
    private String mobile;
    private String email;
    private int year;
    private int month;
    private int day;
    private String startTime;
    private String endTime;
    private String regTime;
    private String cusType;
    private String dateDay;
    
    private String startCountTime;
    private String endCountTime;
    private int subjectId;
    private int startNumber;
    private int endNumber;
    private String cusFromUrl;
    private int visitType=0;//访问类型，1：注册学员，2：未支付学员 
    private int queryFlag;//查询类型。0：普通展示的查询，1：导出EXCEL查询
  
    /* fanxin 2011-08-10 add
     * 统计通过某个推广网站或站长注册人数
     */
    private String cusWebFrom;
    private String cusWebAgent;
        
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getStartCountTime() {
		return startCountTime;
	}

	public void setStartCountTime(String startCountTime) {
		this.startCountTime = startCountTime;
	}

	public String getEndCountTime() {
		return endCountTime;
	}

	public void setEndCountTime(String endCountTime) {
		this.endCountTime = endCountTime;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}

	public String getCusFromUrl() {
		return cusFromUrl;
	}

	public void setCusFromUrl(String cusFromUrl) {
		this.cusFromUrl = cusFromUrl;
	}

	public String getCusPwd() {
		return cusPwd;
	}

	public void setCusPwd(String cusPwd) {
		this.cusPwd = cusPwd;
	}

	public String getCusWebFrom() {
		return cusWebFrom;
	}

	public void setCusWebFrom(String cusWebFrom) {
		this.cusWebFrom = cusWebFrom;
	}

	public String getCusWebAgent() {
		return cusWebAgent;
	}

	public void setCusWebAgent(String cusWebAgent) {
		this.cusWebAgent = cusWebAgent;
	}

	public int getVisitType() {
		return visitType;
	}

	public void setVisitType(int visitType) {
		this.visitType = visitType;
	}

	public int getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(int queryFlag) {
		this.queryFlag = queryFlag;
	}
	
	
}