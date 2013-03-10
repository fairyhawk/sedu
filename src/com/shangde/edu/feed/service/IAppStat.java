package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.AppStat;

public interface IAppStat {

	/**
	 * 添加
	 * 
	 * @param download
	 * @return
	 */
	public int addAppStat(AppStat appStat);

	/**
	 * 某个应用总次数，根据时间段查询
	 * 
	 * @param queryAppStatCondition
	 * @return
	 */
	public int getAppStatCount(QueryAppStatCondition queryAppStatCondition);

	/**
	 * 提问总人数，根据时间段
	 * 
	 * @param queryAppStatCondition
	 * @return
	 */
	public int getAppStatUserCount(QueryAppStatCondition queryAppStatCondition);
}
