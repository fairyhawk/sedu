package com.shangde.edu.feed.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryBrowseLogCondition;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.BrowseLog;

public interface IBrowseLog {

	/**
	 * 添加
	 * 
	 * @param browseLog
	 * @return
	 */
	public int addBrowseLog(BrowseLog browseLog);

	/**
	 * 分页查询
	 * 
	 * @param queryBrowseLogCondition
	 * @return
	 */
	public PageResult getBrowseLogList(
			QueryStatCommonCondition queryStatCommonCondition);

	public int getBrowseLogGroupCount(
			QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 获取当天数据
	 * 
	 * @param queryBrowseLogCondition
	 * @return
	 */
	public PageResult getBrowseLogDATEList(
			QueryBrowseLogCondition queryBrowseLogCondition);

	public int getBrowseLogDATEGroupCount(
			QueryBrowseLogCondition queryBrowseLogCondition);

	/**
	 * 获取本周数据
	 * 
	 * @param queryBrowseLogCondition
	 * @return
	 */
	public PageResult getBrowseLogWEEKList(
			QueryBrowseLogCondition queryBrowseLogCondition);

	public int getBrowseLogWEEKGroupCount(
			QueryBrowseLogCondition queryBrowseLogCondition);

	/**
	 * 获取月数据
	 * 
	 * @param queryBrowseLogCondition
	 * @return
	 */
	public PageResult getBrowseLogMONTHList(
			QueryBrowseLogCondition queryBrowseLogCondition);

	public int getBrowseLogMONTHGroupCount(
			QueryBrowseLogCondition queryBrowseLogCondition);
}
