package com.shangde.edu.dis.domain;

/**
 * 
 * @author Libg
 * 
 * @category 标签
 */
public class Tags implements java.io.Serializable {

	/**
	 * id
	 */
	private int id;
	/**
	 * 标签名称
	 */
	private String name;
	/**
	 * 个数，有程序统计实现
	 */
	private int count;
	/**
	 * 状态
	 */
	private int status;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
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
