package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.Share;
import com.shangde.edu.feed.service.IShare;

public class ShareImpl extends BaseService implements IShare {

	@Override
	public int addShare(Share share) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("Feed_Share_NS.createShare", "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IShare#getShareCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	@Override
	public int getShareCount(QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_Share_NS.getShareCount",
				queryAppStatCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IShare#getShareUserGroupCount(com.shangde.edu.feed.condition.QueryAppStatCondition)
	 */
	@Override
	public int getShareUserGroupCount(
			QueryAppStatCondition queryAppStatCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Feed_Share_NS.getShareUserGroupCount",
				queryAppStatCondition);
	}

}
