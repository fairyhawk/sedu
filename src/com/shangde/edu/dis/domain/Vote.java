package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class Vote implements Serializable {

	private static final long serialVersionUID = -3725491313538070427L;

	private int id;
	/**
	 * 投票类型
	 */
	private int voteType;// 
	/**
	 * 话题ID
	 */
	private int topicId;// 
	/**
	 * 参考结果
	 */
	private String referenceResults;// 
	/**
	 * 状态 1=显示投票结果,0=不显示
	 */
	private int status;// 

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
	 * @return the voteType
	 */
	public int getVoteType() {
		return voteType;
	}

	/**
	 * @param voteType
	 *            the voteType to set
	 */
	public void setVoteType(int voteType) {
		this.voteType = voteType;
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
	 * @return the referenceResults
	 */
	public String getReferenceResults() {
		return referenceResults;
	}

	/**
	 * @param referenceResults
	 *            the referenceResults to set
	 */
	public void setReferenceResults(String referenceResults) {
		this.referenceResults = referenceResults;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
