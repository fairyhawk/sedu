package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.Reviews;
import com.shangde.edu.exam.condition.QueryReviewsCondition;
import com.shangde.common.service.BaseService;

/**
 * Reviews
 * IReviews的实现类
 * 实现对评价的操作
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class ReviewsImpl extends BaseService implements IReviews{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 添加评价
     * @param reviews 要添加的评价�Reviews
     * @return id
     */
    public java.lang.Integer addReviews(Reviews reviews) {
    	return simpleDao.createEntity("Reviews_NS.createReviews",reviews);
    }
    
    /**
     * 删除评价
     * @param rvId 评价ID
     */
    public void delReviewsById(int rvId){
        simpleDao.deleteEntity("Reviews_NS.deleteReviewsById",rvId);
    }
    /**
     * 更新评价޸�Reviews
     * @param reviews 已更新的评价
     */
    public void updateReviews(Reviews reviews) {
        simpleDao.updateEntity("Reviews_NS.updateReviews",reviews);
    }
    
    /**
     * 通过ID获取评价
     * @param rvId 评价Id
     * @return 评价 
     */
    public Reviews getReviewsById(int rvId) {
        return simpleDao.getEntity("Reviews_NS.getReviewsById",rvId);
    }
    
    /**
     * 通过条件查询评价��������ȡReviews�б�
     * @param queryReviewsCondition 查询条件
     * @return 查询结果集���
     */
    public List<Reviews> getReviewsList(QueryReviewsCondition queryReviewsCondition) {
        return simpleDao.getForList("Reviews_NS.getReviewsList",queryReviewsCondition);
    }
}
