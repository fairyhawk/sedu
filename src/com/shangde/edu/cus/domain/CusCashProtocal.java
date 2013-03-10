package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Yangning
 *	实体类:用户保过协议流水关联表
 */
public class CusCashProtocal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	/**保过协议信息ID**/
	private int cusProtocalId;
	private int cashId;
	private Date createTime;
	
	private String protocalContent;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusProtocalId() {
		return cusProtocalId;
	}
	public void setCusProtocalId(int cusProtocalId) {
		this.cusProtocalId = cusProtocalId;
	}
	public int getCashId() {
		return cashId;
	}
	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getProtocalContent() {
		return protocalContent;
	}
	public void setProtocalContent(String protocalContent) {
		this.protocalContent = protocalContent;
	}
}
