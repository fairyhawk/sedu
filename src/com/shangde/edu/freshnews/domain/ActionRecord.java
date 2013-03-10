package com.shangde.edu.freshnews.domain;

import java.io.Serializable;
import java.util.List;

public class ActionRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
    private Integer relateId;
    /**
     * 1：问答；2：视频；3：注册；4：下单；5：考试
     */
    private int currentType;
    private String content;
    private String cusName;
    private int cusId;
    private String cusEmail;
    private int isAnswer;
    private int subjectId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String otherInfo;
    private String headImg; 
    
    /**
     * 自定义字段
     */
    private long upTime;
    
    private List<ActionReply> arList;
        
    public Integer getRelateId() {
		return relateId;
	}

	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCurrentType(){
        return currentType;
    }

    public void setCurrentType(int currentType){
        this.currentType = currentType; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
        
    public String getCusName(){
        return cusName;
    }

    public void setCusName(String cusName){
        this.cusName = cusName; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getCusEmail(){
        return cusEmail;
    }

    public void setCusEmail(String cusEmail){
        this.cusEmail = cusEmail; 
    }
        
    public int getIsAnswer(){
        return isAnswer;
    }

    public void setIsAnswer(int isAnswer){
        this.isAnswer = isAnswer; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
    	setUpTime(updateTime.getTime());
        this.updateTime = updateTime; 
    }

	public List<ActionReply> getArList() {
		return arList;
	}

	public void setArList(List<ActionReply> arList) {
		this.arList = arList;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public long getUpTime() {
		return upTime;
	}

	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}
}
