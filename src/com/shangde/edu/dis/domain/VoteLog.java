package com.shangde.edu.dis.domain;

import java.io.Serializable;

public class VoteLog implements Serializable {

	private static final long serialVersionUID = -5378373736003881885L;

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 投票id
	 */
	private int voteId;
	/**
	 * 投票的那一项
	 */
	private String voteDetailId;
	/**
	 * 用户id,表示某个用户的投票
	 */
	private int cusId;

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
	 * @return the voteDetailId
	 */
	public String getVoteDetailId() {
		return voteDetailId;
	}

	/**
	 * @param voteDetailId
	 *            the voteDetailId to set
	 */
	public void setVoteDetailId(String voteDetailId) {
		this.voteDetailId = voteDetailId;
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
}
