package com.shangde.edu.ass.domain;

import java.io.Serializable;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Kpoint;

public class Assess implements Serializable{
    private int id;
    private int subjectId;//专业ID
    private int cusId;//用户ID
    private int sellwayId;//售卖方式ID
    private int courseId;//课程ID
    private int kpointId;//知识点ID
    private String assTitle;//评价标题
    private String assContext;//评价内容
    private java.util.Date assTime;//评价发表时间
    private java.util.Date verifyTime;//审核时间
    private int assLeavel;//用户评分
    private int status;//0:未审核；1：已审核；2：审核未通过；
    private String verifyContext;//审核意见
    private double levelAvg;//各专业评价的平均分
    private Course course;//课程对象	
    private Kpoint kpoint;//知识点对象
    public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getSellwayId(){
        return sellwayId;
    }

    public void setSellwayId(int sellwayId){
        this.sellwayId = sellwayId; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getKpointId(){
        return kpointId;
    }

    public void setKpointId(int kpointId){
        this.kpointId = kpointId; 
    }
        
    public String getAssTitle(){
        return assTitle;
    }

    public void setAssTitle(String assTitle){
        this.assTitle = assTitle; 
    }
        
    public String getAssContext(){
        return assContext;
    }

    public void setAssContext(String assContext){
        this.assContext = assContext; 
    }
        
    public java.util.Date getAssTime(){
        return assTime;
    }

    public void setAssTime(java.util.Date assTime){
        this.assTime = assTime; 
    }
        
    public java.util.Date getVerifyTime(){
        return verifyTime;
    }

    public void setVerifyTime(java.util.Date verifyTime){
        this.verifyTime = verifyTime; 
    }
        
    public int getAssLeavel(){
        return assLeavel;
    }

    public void setAssLeavel(int assLeavel){
        this.assLeavel = assLeavel; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public String getVerifyContext(){
        return verifyContext;
    }

    public void setVerifyContext(String verifyContext){
        this.verifyContext = verifyContext; 
    }

	public double getLevelAvg() {
		return levelAvg;
	}

	public void setLevelAvg(double levelAvg) {	
		
		this.levelAvg = levelAvg;
	}
}
