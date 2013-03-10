package com.shangde.edu.cus.domain;

import java.io.Serializable;

/** 官方认证达人。Highso问答。 */
public class OfficialCustomer implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private Integer id;
	/** 学员编号 */
	private Integer cusId;
	/** 项目编号 */
	private Integer subjectId;
	/** 头像图片名称 */
	private String portrait;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

}
