package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.List;

public class Problem implements Serializable{
	/** 问题id  */
	private int pblId;
	 /** 课程id   */
    private int courseId;
	 /** 注册用户id   */
    private int cusId;
	 /** 问题标题   */
    private String pblTitle;
	 /** 创建时间   */
    private java.util.Date createTime;
    /** 回答问题的list   */
    private List<ReProblem> reProblemList;
    /** 前台的问题编号   */
    private int nbId;
    /** 问题类型*///1 考试问题 2 课程问题 3 视频问题 4 讲义问题
    private int pblType;
    /** 问题积分*/
    private int pblTotols;
    /** 是否解决问题*/ //2为待解决问题，1为已解决的问题
    private int pblSolv =1;
    /** 是否隐藏问题*/ //0:不隐藏 1：隐藏
    private int pblStatus =0;
    /**问题详细内容*/
    private String pblContent;
    /**是否为热门问题*/ //0为普通问题 1为热门问题
    private int pblHot; 
    /**专业id*/
    private int subjectId;
    /**提问名称*/
    private Customer customer;
    private Integer officialReplyCount; // 官方达人回复数量
    private int newHidePblCount;//隐藏答案之后，该问题的答案数量
    
    private int pblCountByType;//根据问题类型区分问题的数量
    
    public int getPblCountByType() {
		return pblCountByType;
	}

	public void setPblCountByType(int pblCountByType) {
		this.pblCountByType = pblCountByType;
	}

	public int getNewHidePblCount() {
		return newHidePblCount;
	}

	public void setNewHidePblCount(int newHidePblCount) {
		this.newHidePblCount = newHidePblCount;
	}

	public int getPblId(){
        return pblId;
    }

    public void setPblId(int pblId){
        this.pblId = pblId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getPblTitle(){
        return pblTitle;
    }

    public void setPblTitle(String pblTitle){
        this.pblTitle = pblTitle; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }

	public List<ReProblem> getReProblemList() {
		return reProblemList;
	}

	public void setReProblemList(List<ReProblem> reProblemList) {
		this.reProblemList = reProblemList;
	}

	public int getNbId() {
		return nbId;
	}

	public void setNbId(int nbId) {
		this.nbId = nbId;
	}

	public int getPblType() {
		return pblType;
	}

	public void setPblType(int pblType) {
		this.pblType = pblType;
	}

	public int getPblTotols() {
		return pblTotols;
	}

	public void setPblTotols(int pblTotols) {
		this.pblTotols = pblTotols;
	}

	public int getPblSolv() {
		return pblSolv;
	}

	public void setPblSolv(int pblSolv) {
		this.pblSolv = pblSolv;
	}

	public String getPblContent() {
		return pblContent;
	}

	public void setPblContent(String pblContent) {
		this.pblContent = pblContent;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getOfficialReplyCount() {
		return officialReplyCount;
	}

	public void setOfficialReplyCount(Integer officialReplyCount) {
		this.officialReplyCount = officialReplyCount;
	}

	public int getPblStatus() {
		return pblStatus;
	}

	public void setPblStatus(int pblStatus) {
		this.pblStatus = pblStatus;
	}

	
}
