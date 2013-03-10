package com.shangde.edu.feed.service;

import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryReviewCondition;
import com.shangde.edu.feed.domain.Review;

/**
 * 微学习-留言模块接口
 * 
 * @author Libg
 * 
 */
public interface IReview {

	/**
	 * 添加
	 * 
	 * @param review
	 * @return
	 */
	public int addReview(Review review);

	/**
	 * 修改
	 * 
	 * @param review
	 * @return
	 */
	public int updateReview(Review review);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public Review getReviewById(Integer id);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public int delReviewById(Integer id);

	/**
	 * 修改字段counts值[递增/递减]
	 * 
	 * @param map
	 *            key=incremental表示增量(1)/id表示记录id
	 * @return
	 */
	public int updateReviewCounts(Map<String, Integer> map);

	/**
	 * 分页查询
	 * 
	 * @param queryReviewCondition
	 * @return
	 */
	public PageResult getReviewList(QueryReviewCondition queryReviewCondition);

	/**
	 * 分页查询
	 * 
	 * @param queryReviewCondition
	 * @return
	 */
	public PageResult getReviewListBack(
			QueryReviewCondition queryReviewCondition);

	/**
	 * 修改状态+detail字段
	 * 
	 * @param review
	 * @return
	 */
	public int updateReviewStatusDetail(Review review);

	/**
	 * 修改状态
	 * 
	 * @param queryReviewCondition
	 * @return
	 */
	public int updateReviewStatus(QueryReviewCondition queryReviewCondition);

	/**
	 * 获取留言总记录数[status = 1]
	 * 
	 * @return
	 */
	public int getTotalReviewNum();

	/**
	 * 修改反对数
	 * 
	 * @param id
	 * @return
	 */
	public int updateAntilogNumber(Integer id);

	/**
	 * 修改支持数
	 * 
	 * @param id
	 * @return
	 */
	public int updateSupportNumber(Integer id);
}
