package com.shangde.edu.cus.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;

/**
 * TotolsScore�������ʵ����?
 * User: guoqiang.liu
 * Date: 2010-10-19
 */
public class TotolsScoreImpl extends BaseService implements ITotolsScore{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addTotolsScore(TotolsScore totolsScore) {
		return simpleDao.createEntity("TotolsScore_NS.createTotolsScore",totolsScore);
    }

    public void delTotolsScoreById(int tsId){
        simpleDao.deleteEntity("TotolsScore_NS.deleteTotolsScoreById",tsId);
    }

    public void updateTotolsScore(TotolsScore totolsScore) {
        simpleDao.updateEntity("TotolsScore_NS.updateTotolsScore",totolsScore);
    }

    public TotolsScore getTotolsScoreById(int tsId) {
        return simpleDao.getEntity("TotolsScore_NS.getTotolsScoreById",tsId);
    }

    public List<TotolsScore> getTotolsScoreList(QueryTotolsScoreCondition queryTotolsScoreCondition) {
        return simpleDao.getForList("TotolsScore_NS.getTotolsScoreList",queryTotolsScoreCondition);
    }

	public TotolsScore getTotolsScore(int cusId) {
		return simpleDao.getEntity("TotolsScore_NS.getTotolsScore", cusId);
	}

	public PageResult getTotolsScorePageList(QueryTotolsScoreCondition queryTotolsScoreCondition) {
		return simpleDao.getPageResult("TotolsScore_NS.getTotolsScorePageList", "TotolsScore_NS.getTotolsScoreByCount", queryTotolsScoreCondition);
	}

	public void delTotolsScoreByCusId(int cusId) {
		  simpleDao.deleteEntity("TotolsScore_NS.deleteTotolsScoreByCusId",cusId);
	}
	
	
	/**
     * 判断当天是否已经赠送过积分,true赠送过，false未赠送
     */
    public boolean checkScore(int cusId){
    	String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	Map<Object,Object> map = new HashMap<Object,Object>();
    	map.put("cusId", cusId);
    	map.put("accessTime", now);
    	Integer scoreFlag = simpleDao.getEntity("TotolsScore_NS.getSigninScoreByToday", map);
    	if(scoreFlag==0)
    		return false;
    	return true;
    }
    
    /**
     * 赠送积分
     * cusId 用户id
     * score 赠送的积分
     * accessType 积分类型
     */
    public TotolsScore addScore(int cusId,int score,int accessType){
    	//积分表
    	TotolsScore totolsScore = simpleDao.getEntity("TotolsScore_NS.getTotolsScore", cusId);
    	if(null!=totolsScore){
    		totolsScore.setTsCurrent(totolsScore.getTsCurrent()+score);
        	totolsScore.setTsAction(totolsScore.getTsAction()+score);
        	simpleDao.updateEntity("TotolsScore_NS.updateTotolsScore",totolsScore);
    	}else{
    		totolsScore = new TotolsScore();
    		totolsScore.setCusId(cusId);
    		totolsScore.setTsCurrent(score);
    		totolsScore.setTsAction(score);
    		simpleDao.createEntity("TotolsScore_NS.createTotolsScore",totolsScore);
    	}
    	//积分流水表
		TsRecord tsR = new TsRecord();
		tsR.setCusId(cusId);
		tsR.setTrType(TsRecord.TRTYPE_FOR);
		tsR.setAccessType(accessType);
		tsR.setAccessTime(new Date());
		tsR.setTsId(totolsScore.getTsId());
		tsR.setTrNum(score);
		simpleDao.createEntity("TsRecord_NS.createTsRecord",tsR);
		return totolsScore;
    }
    
    
}
