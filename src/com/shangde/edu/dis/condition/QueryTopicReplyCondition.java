package com.shangde.edu.dis.condition;

import com.shangde.common.vo.PageQuery;
import com.shangde.edu.cus.domain.Customer;

/**
 * 
 * 
 * @author Libg
 * 
 */
public class QueryTopicReplyCondition extends PageQuery implements
		java.io.Serializable {

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 用户名称
	 */
	private String cusName;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 用户id
	 */
	private int cusId;
	/**
	 * 回复内容
	 */
	private String replyContent;
	/**
	 * 回复时间
	 */
	private String replyTime;
	/**
	 * 回复目标记录id，引用自身
	 */
	private int replyId;
	/**
	 * 话题id
	 */
	private int topicId;
	/**
	 * 图片地址
	 */
	private String photo;
	/**
	 * 关联用户模型
	 */
	private Customer customer;

	/**
	 * 关联用户模型,针对某个人
	 */
	private Customer targetCustomer;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the cusName
	 */
	public String getCusName() {
		return cusName;
	}

	/**
	 * @param cusName
	 *            the cusName to set
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the replyContent
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * @param replyContent
	 *            the replyContent to set
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * @return the replyTime
	 */
	public String getReplyTime() {
		return replyTime;
	}

	/**
	 * @param replyTime
	 *            the replyTime to set
	 */
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * @return the replyId
	 */
	public int getReplyId() {
		return replyId;
	}

	/**
	 * @param replyId
	 *            the replyId to set
	 */
	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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