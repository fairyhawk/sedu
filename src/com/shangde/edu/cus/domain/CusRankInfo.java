package com.shangde.edu.cus.domain;

import java.io.Serializable;

/**
 * 主体功能:用户视频统计
 *
 * @author		HQL
 * @date		2012-6-27
 * @version 	修改人:
 * 				修改日期:
 */
public class CusRankInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;//统计类型
	
	private int cusid;//用户ID
	
	private int count;//观看次数
	
	private int rank;//排名

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
