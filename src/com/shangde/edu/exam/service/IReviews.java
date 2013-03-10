package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.exam.domain.Reviews;
import com.shangde.edu.exam.condition.QueryReviewsCondition;

/**
 * Reviews 评价
 * 试卷评价接口
 * 对试卷评价的操作接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IReviews extends Serializable {
    /**
     * 添加评价
     * @param reviews 要添加的评价�Reviews
     * @return id
     */
    public java.lang.Integer addReviews(Reviews reviews);

    /**
     * 删除评价
     * @param rvId 评价ID
     */
    public void delReviewsById(int rvId);

    /**
     * 更新评价޸�Reviews
     * @param reviews 已更新的评价
     */
    public void updateReviews(Reviews reviews);

    /**
     * 通过ID获取评价
     * @param rvId 评价Id
     * @return 评价 
     */
    public Reviews getReviewsById(int rvId);

    /**
     * 通过条件查询评价��������ȡReviews�б�
     * @param queryReviewsCondition 查询条件
     * @return 查询结果集���
     */
    public List<Reviews> getReviewsList(QueryReviewsCondition queryReviewsCondition);
}