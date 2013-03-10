package com.shangde.edu.dis.condition;

/**
 * 
 * @author Libg
 * 
 */
public class QueryCusUserDisCondition implements java.io.Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 小组id
	 */
	private Integer disId;
	/**
	 * 用户id
	 */
	private Integer cusId;

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
	 * @return the disId
	 */
	public Integer getDisId() {
		return disId;
	}

	/**
	 * @param disId
	 *            the disId to set
	 */
	public void setDisId(Integer disId) {
		this.disId = disId;
	}

	/**
	 * @return the cusId
	 */
	public Integer getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

}