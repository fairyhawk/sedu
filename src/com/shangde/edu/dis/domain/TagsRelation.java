package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 
 * @author Libg
 * 
 * @category 标签关联表
 */
public class TagsRelation implements Serializable {

	/**
	 * 记录id
	 */
	private int id;
	/**
	 * 分类
	 */
	private int objectTypeId;// 分类
	/**
	 * 对象记录id
	 */
	private int ObjectId;// 对象记录id
	/**
	 * 标签id
	 */
	private int tagId;// 

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
	 * @return the objectTypeId
	 */
	public int getObjectTypeId() {
		return objectTypeId;
	}

	/**
	 * @param objectTypeId
	 *            the objectTypeId to set
	 */
	public void setObjectTypeId(int objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	/**
	 * @return the objectId
	 */
	public int getObjectId() {
		return ObjectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(int objectId) {
		ObjectId = objectId;
	}

	/**
	 * @return the tagId
	 */
	public int getTagId() {
		return tagId;
	}

	/**
	 * @param tagId
	 *            the tagId to set
	 */
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

}
