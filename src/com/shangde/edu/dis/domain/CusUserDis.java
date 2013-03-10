package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 
 * 讨论小组与用户关联关系
 * 
 * @author Libg
 * 
 */
public class CusUserDis implements Serializable {

	private static final long serialVersionUID = 3691554633665030891L;

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
	 * 权限值
	 */
	private Integer auth;

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

	/**
	 * @return the auth
	 */
	public Integer getAuth() {
		return auth;
	}

	/**
	 * @param auth
	 *            the auth to set
	 */
	public void setAuth(Integer auth) {
		this.auth = auth;
	}

}
