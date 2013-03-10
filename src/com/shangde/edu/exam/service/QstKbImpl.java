package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.QstKb;
import com.shangde.edu.exam.condition.QueryQstKbCondition;
import com.shangde.edu.exam.condition.QueryQstKbExamRecordCondition;
import com.shangde.common.service.BaseService;

/**
 * QstKb�������ʵ����?
 * User: guoqiang.liu
 * Date: 2011-01-17
 */
public class QstKbImpl extends BaseService implements IQstKb{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addQstKb(QstKb qstKb) {
		return simpleDao.createEntity("QstKb_NS.createQstKb",qstKb);
    }

    public void delQstKbById(int id){
        simpleDao.deleteEntity("QstKb_NS.deleteQstKbById",id);
    }

    public void updateQstKb(QstKb qstKb) {
        simpleDao.updateEntity("QstKb_NS.updateQstKb",qstKb);
    }

    public QstKb getQstKbById(int id) {
        return simpleDao.getEntity("QstKb_NS.getQstKbById",id);
    }

    public List<QstKb> getQstKbList(QueryQstKbCondition queryQstKbCondition) {
        return simpleDao.getForList("QstKb_NS.getQstKbList",queryQstKbCondition);
    }
    
    
    public int getQstEpId(int epid)
    {
    	return simpleDao.getEntity("QstKb_NS.getQstKbByEpId", epid);
    }
    
    /**
     * 查询总的条数
     */
    public int getCount(QueryQstKbExamRecordCondition qqke)
    {
    	return simpleDao.getEntity("QstKb_NS.getQstKbExamRecordCount", qqke);
    }
    
    /**
     * 查询正确条数
     */
    public int getrightCount(QueryQstKbExamRecordCondition qqke)
    {
    	return simpleDao.getEntity("QstKb_NS.QstKbExamRecordRightCount", qqke);
    }
    
    /**
     * 查询为空条数
     */
    
    public int getisNoCount(QueryQstKbExamRecordCondition qqke)
    {
    	return simpleDao.getEntity("QstKb_NS.getQstKbExamRecordIsNOCount", qqke);
    }
    
}
