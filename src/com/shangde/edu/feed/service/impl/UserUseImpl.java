package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.UserUse;
import com.shangde.edu.feed.service.IUserUse;

public class UserUseImpl extends BaseService implements IUserUse {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#addUserUse(com.shangde.edu.feed.domain.UserUse)
	 */
	@Override
	public int addUserUse(UserUse userUse) {
		return simpleDao
				.createEntity("Feed_User_Use_NS.createUserUse", userUse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseCount(QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseGroupCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseGroupCount(
			QueryStatCommonCondition queryStatCommonCondition) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseGroupCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseAndTypeFromIdCount(
			QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATECount(int)
	 */
	@Override
	public int getUserUseDATECount(int type) {
		return simpleDao
				.getEntity("Feed_User_Use_NS.getUserUseDATECount", type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATEGroupCount()
	 */
	@Override
	public int getUserUseDATEGroupCount() {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseDATEGroupCount",
				null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseDATEAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseDATEAndTypeFromIdCount(
			QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseDATEAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHCount(int)
	 */
	@Override
	public int getUserUseMONTHCount(int type) {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseMONTHCount",
				type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHGroupCount()
	 */
	@Override
	public int getUserUseMONTHGroupCount() {
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseMONTHGroupCount", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseMONTHAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseMONTHAndTypeFromIdCount(
			QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseMONTHAndTypeFromIdCount",
				queryStatCommonCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKCount(int)
	 */
	@Override
	public int getUserUseWEEKCount(int type) {
		return simpleDao
				.getEntity("Feed_User_Use_NS.getUserUseWEEKCount", type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKGroupCount()
	 */
	@Override
	public int getUserUseWEEKGroupCount() {
		return simpleDao.getEntity("Feed_User_Use_NS.getUserUseWEEKGroupCount",
				null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IUserUse#getUserUseWEEKAndTypeFromIdCount(com.shangde.edu.feed.condition.QueryStatCommonCondition)
	 */
	@Override
	public int getUserUseWEEKAndTypeFromIdCount(
			QueryStatCommonCondition queryStatCommonCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity(
				"Feed_User_Use_NS.getUserUseWEEKAndTypeFromIdCount",
				queryStatCommonCondition);
	}

}
