package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.common.service.BaseService;

/**
 * Qstmiddle 方法实现
 * User: 何海强
 * Date: 2011-05-19
 */
public class QstmiddleImpl extends BaseService implements IQstmiddle{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addQstmiddle(Qstmiddle qstmiddle) {
    	return simpleDao.createEntity("Qstmiddle_NS.createQstmiddle",qstmiddle);
    }

    public void delQstmiddleById(int eqId){
        simpleDao.deleteEntity("Qstmiddle_NS.deleteQstmiddleById",eqId);
    }
    public void delQstmiddleByQstId(int qstId){
    	
    	  simpleDao.deleteEntity("Qstmiddle_NS.deleteQstmiddleByQstId",qstId);
    }
    

    public void updateQstmiddle(Qstmiddle qstmiddle) {
        simpleDao.updateEntity("Qstmiddle_NS.updateQstmiddle",qstmiddle);
    }

    public Qstmiddle getQstmiddleById(int eqId) {
        return simpleDao.getEntity("Qstmiddle_NS.getQstmiddleById",eqId);
    }

    public List<Qstmiddle> getQstmiddleList(QueryQstmiddleCondition queryQstmiddleCondition) {
        return simpleDao.getForList("Qstmiddle_NS.getQstmiddleList",queryQstmiddleCondition);
    }
    
    public List<Qstmiddle> getQstbyQstid(int qstid){
    	
    	return simpleDao.getForList("Qstmiddle_NS.getQstbyQstid", qstid);
    }
}
