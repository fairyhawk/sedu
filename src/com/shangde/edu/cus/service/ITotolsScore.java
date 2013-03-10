package com.shangde.edu.cus.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;


public interface ITotolsScore extends Serializable {
    
    public java.lang.Integer addTotolsScore(TotolsScore totolsScore);

    public void delTotolsScoreById(int tsId);

    public void updateTotolsScore(TotolsScore totolsScore);

    public TotolsScore getTotolsScoreById(int tsId);

    public List<TotolsScore> getTotolsScoreList(QueryTotolsScoreCondition queryTotolsScoreCondition);
    
    public TotolsScore getTotolsScore(int cusId);
    
    public PageResult getTotolsScorePageList(QueryTotolsScoreCondition queryTotolsScoreCondition);
    
    public void delTotolsScoreByCusId(int cusId);
    
    /**
     * 判断当天是否已经赠送过积分
     */
    public boolean checkScore(int cusId);
    /**
     * 赠送积分
     * cusId 用户id
     * score 赠送的积分
     * accessType 积分类型
     */
    public TotolsScore addScore(int cusId,int score,int accessType);
}