package com.shangde.edu.dis.domain;

import java.io.Serializable;

import com.shangde.edu.cus.domain.Customer;

public class TopicReply implements Serializable {

	private static final long serialVersionUID = -6035997108029431730L;

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 话题id
	 */
	private int topicId;
	/**
	 * 小组id
	 */
	private int disId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复时间
	 */
	private java.util.Date replyTime;
	/**
	 * 用户id
	 */
	private int cusId;
	/**
	 * 用户对象
	 */
	private Customer customer;

	// private int target_cus_id;
	// private int target_reply_id
	/**
	 * 目标用户对象
	 */
	private Customer targetCustomer;
	/**
	 * 回复目标记录对象
	 */
	private TopicReply targetTopicReply;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId
	 *            the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}

	/**
	 * @param disId
	 *            the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the replyTime
	 */
	public java.util.Date getReplyTime() {
		return replyTime;
	}

	/**
	 * @param replyTime
	 *            the replyTime to set
	 */
	public void setReplyTime(java.util.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the targetCustomer
	 */
	public Customer getTargetCustomer() {
		return targetCustomer;
	}

	/**
	 * @param targetCustomer
	 *            the targetCustomer to set
	 */
	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}

	/**
	 * @return the targetTopicReply
	 */
	public TopicReply getTargetTopicReply() {
		return targetTopicReply;
	}

	/**
	 * @param targetTopicReply
	 *            the targetTopicReply to set
	 */
	public void setTargetTopicReply(TopicReply targetTopicReply) {
		this.targetTopicReply = targetTopicReply;
	}

}
