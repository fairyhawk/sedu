package com.shangde.edu.cms.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.shangde.edu.cus.domain.Customer;

public class Comment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int CMT_CHECK_STATE_NEW = 0;
	public static int CMT_CHECK_STATE_PASS = 1;
	public static int CMT_CHECK_MAN_TYPE_VISITOR = 0; 
	public static int CMT_CHECK_MAN_TYPE_USER = 1; 
	private Integer cmtId;
    private Integer cmtParentId;
    private String cmtInfo;
    private Integer cfId;
    private Integer checkState;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.util.Date topTime;
    private Integer checkmanId;
    private Integer checkmanType;
    private Integer sourceId;
    private Integer grade;
    private String visitorName;
    private Object sourceObject;
    private Customer customer; 
    private Integer is_top;//是否置顶
    private Integer is_freeze;//是否隐藏
    private String author;//操作者
    private String mgr_info;//管理员回复内容
    private java.util.Date mgr_creatime;//管理员回复时间
    private List<Comment> replyList;//所有回复内容无管理员
    private String mgrName;
    private int subjectId;
    private String subjectName;
    private String sellName;
    
    
    
    
    
    
    
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMgrName() {
		return mgrName;
	}
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	public java.util.Date getMgr_creatime() {
		return mgr_creatime;
	}
	public void setMgr_creatime(java.util.Date mgr_creatime) {
		this.mgr_creatime = mgr_creatime;
	}
	public String getMgr_info() {
		return mgr_info;
	}
	public void setMgr_info(String mgr_info) {
		this.mgr_info = mgr_info;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	public Integer getIs_freeze() {
		return is_freeze;
	}
	public void setIs_freeze(Integer is_freeze) {
		this.is_freeze = is_freeze;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCmtId() {
		return cmtId;
	}
	public void setCmtId(Integer cmtId) {
		this.cmtId = cmtId;
	}
	public Integer getCmtParentId() {
		return cmtParentId;
	}
	public void setCmtParentId(Integer cmtParentId) {
		this.cmtParentId = cmtParentId;
	}
	public String getCmtInfo() {
		return cmtInfo;
	}
	public void setCmtInfo(String cmtInfo) {
		this.cmtInfo = cmtInfo;
	}
	public Integer getCfId() {
		return cfId;
	}
	public void setCfId(Integer cfId) {
		this.cfId = cfId;
	}
	public Integer getCheckState() {
		return checkState;
	}
	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCheckmanId() {
		return checkmanId;
	}
	public void setCheckmanId(Integer checkmanId) {
		this.checkmanId = checkmanId;
	}
	public Integer getCheckmanType() {
		return checkmanType;
	}
	public void setCheckmanType(Integer checkmanType) {
		this.checkmanType = checkmanType;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public Object getSourceObject() {
		return sourceObject;
	}
	public void setSourceObject(Object sourceObject) {
		this.sourceObject = sourceObject;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public java.util.Date getTopTime() {
		return topTime;
	}
	public void setTopTime(java.util.Date topTime) {
		this.topTime = topTime;
	}
	public List<Comment> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Comment> replyList) {
		this.replyList = replyList;
	}
	
}
