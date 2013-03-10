package com.shangde.edu.crm.domain;

import java.io.Serializable;
import java.util.Date;

public class Chance implements Serializable{
    private int id;
    private int crmUserId;
    private int origin;
    private String webName;
    private int subjectId;
    private int followStatus;
    private int userId;
    private Date chanceStime;
    private Date chanceUtime;
    private int chanceNumber;
    private int backCusId;//备份座席id
    private Date createTime; //记录创建时间
    public int getId(){
        return id;
    }

    
    public void setId(int id){
        this.id = id; 
    }
        
    public int getCrmUserId(){
        return crmUserId;
    }

    public void setCrmUserId(int crmUserId){
        this.crmUserId = crmUserId; 
    }
        
    public int getOrigin(){
        return origin;
    }

    public void setOrigin(int origin){
        this.origin = origin; 
    }
        
    public String getWebName(){
        return webName;
    }

    public void setWebName(String webName){
        this.webName = webName; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getFollowStatus(){
        return followStatus;
    }

    public void setFollowStatus(int followStatus){
        this.followStatus = followStatus; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public java.util.Date getChanceStime(){
        return chanceStime;
    }

    public void setChanceStime(java.util.Date chanceStime){
        this.chanceStime = chanceStime; 
    }
        
    public java.util.Date getChanceUtime(){
        return chanceUtime;
    }

    public void setChanceUtime(java.util.Date chanceUtime){
        this.chanceUtime = chanceUtime; 
    }
        
    public int getChanceNumber(){
        return chanceNumber;
    }

    public void setChanceNumber(int chanceNumber){
        this.chanceNumber = chanceNumber; 
    }

	public int getBackCusId() {
		return backCusId;
	}

	public void setBackCusId(int backCusId) {
		this.backCusId = backCusId;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
