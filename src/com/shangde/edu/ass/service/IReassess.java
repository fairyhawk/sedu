package com.shangde.edu.ass.service;

import java.util.List;
import com.shangde.edu.ass.domain.Reassess;
import com.shangde.edu.ass.condition.QueryReassessCondition;

/**
 * Reassess����ӿ�
 * User: guoqiang.liu
 * Date: 2011-05-23
 */
public interface IReassess {
    /**
     * ���Reassess
     * @param 插入
     * @return id
     */
    public java.lang.Integer addReassess(Reassess reassess);

    /**
     * 删除
     */
    public void delReassessById(int reAssessId);

    /**
     * 修改
     * @param reassess Ҫ�޸ĵ�Reassess
     */
    public void updateReassess(Reassess reassess);

    /**
     * 根据ID查询
     * @return Reassess
     */
    public Reassess getReassessById(int reAssessId);

    /** 
     * @param 条件查询
     * @return list<Reassess>
     */
    public List<Reassess> getReassessList(QueryReassessCondition queryReassessCondition);
    
    /**
     * 根据评论ID 得到当前评论回复
     */
    public Reassess getReassessByAssId(int AssId);
}