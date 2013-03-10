package com.shangde.edu.dis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 敏感词
 * 
 * @author changfu.dai
 * 
 */
public class DisWord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键编号、自增
	 */
	private Integer id; // 主键编号、自增

	/**
	 * 敏感字
	 */
	private String word; // 敏感字

	/**
	 * 创建时间
	 */
	private Date createTime; // 创建时间

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
