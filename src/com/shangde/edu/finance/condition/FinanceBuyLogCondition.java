package com.shangde.edu.finance.condition;

import java.io.Serializable;

/**
 * 
 * @ClassName: FinanceBuyLogCondition
 * @Description: TODO(前台用户下订单时，各个点击步骤的记录实体)
 * @author shixiaofeng@sunland.org.cn
 * @date 2012-6-12 上午10:57:44
 * 
 */
public class FinanceBuyLogCondition implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -5418676368527709349L;
	/**
	 * 点击的项目id
	 */
	private int projectId;
	/**
	 * 点击的步骤
	 */
	private int status;

	public FinanceBuyLogCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinanceBuyLogCondition(int projectId, int status) {
		super();
		this.projectId = projectId;
		this.status = status;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
