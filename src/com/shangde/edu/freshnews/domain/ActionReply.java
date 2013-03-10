package com.shangde.edu.freshnews.domain;

import java.io.Serializable;

public class ActionReply implements Serializable{
    private int id;
    private int actionId;
    private int operateType;
    private String operateUserId;
    private String operateUserEmail;
    private String operateUserName;
    private java.util.Date createTime;
    private int replyFrom;
    private String content;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getActionId(){
        return actionId;
    }

    public void setActionId(int actionId){
        this.actionId = actionId; 
    }
        
    public int getOperateType(){
        return operateType;
    }

    public void setOperateType(int operateType){
        this.operateType = operateType; 
    }
        
    public String getOperateUserId(){
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId){
        this.operateUserId = operateUserId; 
    }
        
    public String getOperateUserEmail(){
        return operateUserEmail;
    }

    public void setOperateUserEmail(String operateUserEmail){
        this.operateUserEmail = operateUserEmail; 
    }
        
    public String getOperateUserName(){
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName){
        this.operateUserName = operateUserName; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getReplyFrom(){
        return replyFrom;
    }

    public void setReplyFrom(int replyFrom){
        this.replyFrom = replyFrom; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
}
