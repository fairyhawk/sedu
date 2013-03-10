package com.shangde.edu.crm.domain;

import java.io.Serializable;
import java.util.Date;

public class ChanceUser implements Serializable{
    private int id;
    private int cusId;
    private String email;
    private String mobile;
    private String realName;
    private String sex;
    private java.util.Date regTime;
    private String qq;
    private int userType;
    private int subjectId;
    private int age;
    private String birthday;    
    private int mesStatus;
    private Date createTime; //记录创建时间
    public int getMesStatus() {
		return mesStatus;
	}

	public void setMesStatus(int mesStatus) {
		this.mesStatus = mesStatus;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
        
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile; 
    }
        
    public String getRealName(){
        return realName;
    }

    public void setRealName(String realName){
        this.realName = realName; 
    }
        
    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex; 
    }
        
    public java.util.Date getRegTime(){
        return regTime;
    }

    public void setRegTime(java.util.Date regTime){
        this.regTime = regTime; 
    }
        
    public String getQq(){
        return qq;
    }

    public void setQq(String qq){
        this.qq = qq; 
    }
        
    public int getUserType(){
        return userType;
    }

    public void setUserType(int userType){
        this.userType = userType; 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age; 
    }

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
