package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 
 * 投票详细模型
 * 
 * @author Libg
 * 
 */
public class VoteDetail implements Serializable {

	private static final long serialVersionUID = -3449801607100603023L;

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 投票id
	 */
	private int voteId;
	/**
	 * 排序值 1~
	 */
	private int voteOrder;
	/**
	 * 投票项
	 */
	private String voteOption;
	/**
	 * 投票次数
	 */
	private int voteCount;

	/**
	 * 扩展属性，百分比（存储通过算法计算出来的 *%）
	 */
	private String percentage = "0";

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
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the voteOrder
	 */
	public int getVoteOrder() {
		return voteOrder;
	}

	/**
	 * @param voteOrder
	 *            the voteOrder to set
	 */
	public void setVoteOrder(int voteOrder) {
		this.voteOrder = voteOrder;
	}

	/**
	 * @return the voteOption
	 */
	public String getVoteOption() {
		return voteOption;
	}

	/**
	 * @param voteOption
	 *            the voteOption to set
	 */
	public void setVoteOption(String voteOption) {
		this.voteOption = voteOption;
	}

	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}

	/**
	 * @param voteCount
	 *            the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

}
