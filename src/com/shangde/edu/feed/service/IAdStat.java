package com.shangde.edu.feed.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryAdStatCondition;
import com.shangde.edu.feed.condition.QueryStatCommonCondition;
import com.shangde.edu.feed.domain.AdStat;

/**
 * 微学习，ad广告统计接口
 * 
 * @author Lee
 * 
 */
public interface IAdStat {

	/**
	 * 添加
	 * 
	 * @param ad
	 * @return
	 */
	public int addAd(AdStat adStat);

	/**
	 * 分页查询
	 * 
	 * @param queryStatCommonCondition
	 * @return
	 */
	public PageResult getAdList(
			QueryStatCommonCondition queryStatCommonCondition);

	public int getAdStatGroupCount(
			QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 获取当天数据
	 * 
	 * @param queryAdStatCondition
	 * @return
	 */
	public PageResult getAdStatDATEList(
			QueryAdStatCondition queryAdStatCondition);

	/**
	 * 根据时间段查询、adId查询分组数量
	 * 
	 * @param queryStatCommonCondition
	 * @return
	 */
	public int getAdStatAndAdIdGroupCount(
			QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 根据时间段查询、adId查询数量
	 * 
	 * @param queryStatCommonCondition
	 * @return
	 */
	public int getAdStatAndAdIdCount(
			QueryStatCommonCondition queryStatCommonCondition);

	/**
	 * 查询当前数据独立ip分组后数
	 * 
	 * @param queryAdStatCondition
	 * @return
	 */
	public int getAdStatDATEGroupCount(QueryAdStatCondition queryAdStatCondition);

	/**
	 * 某广告日数据，日独立数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatDATEAndAdIdGroupCount(int adId);

	/**
	 * 某广告日数据，日数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatDATEAndAdIdCount(int adId);

	/**
	 * 获取本周数据
	 * 
	 * @param queryAdStatCondition
	 * @return
	 */
	public PageResult getAdStatWEEKList(
			QueryAdStatCondition queryAdStatCondition);

	public int getAdStatWEEKGroupCount(QueryAdStatCondition queryAdStatCondition);

	/**
	 * 某广告周数据，周独立数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatWEEKAndAdIdGroupCount(int adId);

	/**
	 * 某广告周数据，周数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatWEEKAndAdIdCount(int adId);

	/**
	 * 获取月数据
	 * 
	 * @param queryAdStatCondition
	 * @return
	 */
	public PageResult getAdStatMONTHList(
			QueryAdStatCondition queryAdStatCondition);

	public int getAdStatMONTHGroupCount(
			QueryAdStatCondition queryAdStatCondition);

	/**
	 * 某广告月独立数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatMONTHAndAdIdGroupCount(int adId);

	/**
	 * 某广告月数量
	 * 
	 * @param adId
	 * @return
	 */
	public int getAdStatMONTHAndAdIdCount(int adId);
}
