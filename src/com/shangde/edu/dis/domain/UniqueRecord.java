/**
 * 
 */
package com.shangde.edu.dis.domain;

import java.util.Date;

/**
 * @author Libg
 * 
 * @category 用户只能针对某事做一次操作记录
 * 
 */
public class UniqueRecord implements java.io.Serializable {

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int cusId;// 用户id
	/**
	 * 对象记录id
	 */
	private int objectId;// 对象记录id
	/**
	 * 分类
	 */
	private int type;// 分类
	/**
	 * 发布时间
	 */
	private Date pubTime;// 发布时间

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
	 * @return the objectId
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the pubTime
	 */
	public Date getPubTime() {
		return pubTime;
	}

	/**
	 * @param pubTime
	 *            the pubTime to set
	 */
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

}
