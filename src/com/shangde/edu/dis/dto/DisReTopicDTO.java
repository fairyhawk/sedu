package com.shangde.edu.dis.dto;

import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

/**
 * 
 * @author Libg
 * 
 */
public class DisReTopicDTO implements java.io.Serializable {

	/**
	 * 记录id
	 */
	private int id; // 回复的内容ID
	/**
	 * 话题id
	 */
	private int topicId; // 话题ID
	/**
	 * 创建人id
	 */
	private int cusId; // 创建ID
	/**
	 * 小组id
	 */
	private int disId; // 小组ID
	/**
	 * 话题内容
	 */
	private String topicContent; // 话题内容
	/**
	 * 话题回复内容
	 */
	private String reTopicContent; // 回复内容
	/**
	 * 话题创建时间
	 */
	private Date topicTime; // 话题创建时间
	/**
	 * 话题回复时间
	 */
	private Date reTopicTime; // 话题回复时间
	/**
	 * 当前话题回复人
	 */
	private Customer customer; // 当前话题回复人
	/**
	 * 针对的某人的回复
	 */
	private Customer targetCustomer; // 针对的某人的回复

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
	 * @return the topicContent
	 */
	public String getTopicContent() {
		return topicContent;
	}

	/**
	 * @param topicContent
	 *            the topicContent to set
	 */
	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	/**
	 * @return the reTopicContent
	 */
	public String getReTopicContent() {
		return reTopicContent;
	}

	/**
	 * @param reTopicContent
	 *            the reTopicContent to set
	 */
	public void setReTopicContent(String reTopicContent) {
		this.reTopicContent = reTopicContent;
	}

	/**
	 * @return the topicTime
	 */
	public Date getTopicTime() {
		return topicTime;
	}

	/**
	 * @param topicTime
	 *            the topicTime to set
	 */
	public void setTopicTime(Date topicTime) {
		this.topicTime = topicTime;
	}

	/**
	 * @return the reTopicTime
	 */
	public Date getReTopicTime() {
		return reTopicTime;
	}

	/**
	 * @param reTopicTime
	 *            the reTopicTime to set
	 */
	public void setReTopicTime(Date reTopicTime) {
		this.reTopicTime = reTopicTime;
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

}
