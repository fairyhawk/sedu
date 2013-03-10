package com.shangde.edu.web.domain;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.cus.domain.VideoCusInfo;

/**
 * 主体功能:用户登录信息统计
 *
 * @author		HQL
 * @date		2012-6-26
 */
public class CusBuyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int loginCount;//登录次数
	
	private int loginRank;//登录排名
	
	private int watchVideoTime;//观看视频时间
	
	private int watchVideoRank;//观看视频排名
	
	private int workQuestionCount;//总做题数
	
	private int workQuestionRank;//做题排名

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginRank() {
		return loginRank;
	}

	public void setLoginRank(int loginRank) {
		this.loginRank = loginRank;
	}

	public int getWatchVideoTime() {
		return watchVideoTime;
	}

	public void setWatchVideoTime(int watchVideoTime) {
		this.watchVideoTime = watchVideoTime;
	}

	public int getWatchVideoRank() {
		return watchVideoRank;
	}

	public void setWatchVideoRank(int watchVideoRank) {
		this.watchVideoRank = watchVideoRank;
	}

	public int getWorkQuestionCount() {
		return workQuestionCount;
	}

	public void setWorkQuestionCount(int workQuestionCount) {
		this.workQuestionCount = workQuestionCount;
	}

	public int getWorkQuestionRank() {
		return workQuestionRank;
	}

	public void setWorkQuestionRank(int workQuestionRank) {
		this.workQuestionRank = workQuestionRank;
	}
}
