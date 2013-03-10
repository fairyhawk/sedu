package com.shangde.edu.exam.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageResult;

/**
 * 查询选项记录条件
 * @author chenshuai
 *
 */
public class QueryOptRecordCondition extends PageResult implements Serializable {
	/**
	 * 学员id
	 */
	private int cusId;
	private Integer qstType;//试题类型
	private Integer level;//试题难度
	private Integer doTime;//做题时间
	private String addTime;//参数时间
	private String compTime;
	
	/**
	 * 学员已做试卷记录id
	 */
	private int userEpId;
	
	
	public String getCompTime() {
		return compTime;
	}
	public void setCompTime(String compTime) {
		this.compTime = compTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getQstType() {
		return qstType;
	}
	public void setQstType(Integer qstType) {
		this.qstType = qstType;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getDoTime() {
		return doTime;
	}
	public void setDoTime(Integer doTime) {
		this.doTime = doTime;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getUserEpId() {
		return userEpId;
	}
	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}
}