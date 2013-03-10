package com.shangde.edu.dis.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

/**
 * 
 * @author ...
 * @author Libg
 * 
 */
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2780125588332824361L;

	private int id;// 主键 id
	private String title;// 100 标题
	private String content;// 内容
	private int cusId;// 创建人
	private int disId;// 所属组
	private int disAreaId;// 所属区域
	private int isVote;// 是否投票
	private int voteId;// 投票ID
	private Date createTime;// 创建时间
	private Date replyTime;// 最后回复时间
	private Date modified;//修改时间
	private int clickCounts;// 点击次数
	private int replyCounts;// 回复次数
	private int recNum;// 推荐积分
	private int recCount;// 默认值0 推荐次数
	private int canReply;// 默认值0 是否允许回复
	private int isTop;// 默认值0 是否置顶
	private Customer customer;
	private int type;
	private int status;
	private int voteCount;// 投票次数
	private String sysUserName;//管理员设置的名称
	private String tags;//标签项,格式：A,B,C 之间“,”逗号隔开
	private String picUrl;//图片地址，新版添加时可以附带图片
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title + "-isTop" + isTop + "-content>" + content;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}

	/**
	 * @param cusId the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}

	/**
	 * @param disId the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}

	/**
	 * @return the disAreaId
	 */
	public int getDisAreaId() {
		return disAreaId;
	}

	/**
	 * @param disAreaId the disAreaId to set
	 */
	public void setDisAreaId(int disAreaId) {
		this.disAreaId = disAreaId;
	}

	/**
	 * @return the isVote
	 */
	public int getIsVote() {
		return isVote;
	}

	/**
	 * @param isVote the isVote to set
	 */
	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}

	/**
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the replyTime
	 */
	public Date getReplyTime() {
		return replyTime;
	}

	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the clickCounts
	 */
	public int getClickCounts() {
		return clickCounts;
	}

	/**
	 * @param clickCounts the clickCounts to set
	 */
	public void setClickCounts(int clickCounts) {
		this.clickCounts = clickCounts;
	}

	/**
	 * @return the replyCounts
	 */
	public int getReplyCounts() {
		return replyCounts;
	}

	/**
	 * @param replyCounts the replyCounts to set
	 */
	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}

	/**
	 * @return the recNum
	 */
	public int getRecNum() {
		return recNum;
	}

	/**
	 * @param recNum the recNum to set
	 */
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	/**
	 * @return the recCount
	 */
	public int getRecCount() {
		return recCount;
	}

	/**
	 * @param recCount the recCount to set
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	/**
	 * @return the canReply
	 */
	public int getCanReply() {
		return canReply;
	}

	/**
	 * @param canReply the canReply to set
	 */
	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}

	/**
	 * @return the isTop
	 */
	public int getIsTop() {
		return isTop;
	}

	/**
	 * @param isTop the isTop to set
	 */
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}

	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the sysUserName
	 */
	public String getSysUserName() {
		return sysUserName;
	}

	/**
	 * @param sysUserName the sysUserName to set
	 */
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	

}
