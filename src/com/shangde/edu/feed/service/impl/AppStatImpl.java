package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.AppStat;
import com.shangde.edu.feed.service.IAppStat;

public class AppStatImpl extends BaseService implements IAppStat {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAppStat#addAppStat(com.shangde.edu.feed.domain.AppStat)
	 */
	@Override
	public int addAppStat(AppStat appStat) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAppStat#getAppStatCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	@Override
	public int getAppStatCount(QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AppStat_NS.getAppStatCount",
				queryAppStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IAppStat#getAppStatUserCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	@Override
	public int getAppStatUserCount(QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_AppStat_NS.getAppStatUserCount",
				queryAppStatCondition);
	}

}
