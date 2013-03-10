package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

public class TsRecordImpl extends BaseService implements ITsRecord{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addTsRecord(TsRecord tsRecord) {
    	return simpleDao.createEntity("TsRecord_NS.createTsRecord",tsRecord);
    }

    public void delTsRecordById(int trId){
        simpleDao.deleteEntity("TsRecord_NS.deleteTsRecordById",trId);
    }

    public void updateTsRecord(TsRecord tsRecord) {
        simpleDao.updateEntity("TsRecord_NS.updateTsRecord",tsRecord);
    }

    public TsRecord getTsRecordById(int trId) {
        return simpleDao.getEntity("TsRecord_NS.getTsRecordById",trId);
    }

    public List<TsRecord> getTsRecordList(QueryTsRecordCondition queryTsRecordCondition) {
        return simpleDao.getForList("TsRecord_NS.getTsRecordList",queryTsRecordCondition);
    }

	public PageResult getTsRecordPageList(QueryTsRecordCondition queryTsRecordCondition) {
		return simpleDao.getPageResult("TsRecord_NS.getTsRecordPageList", "TsRecord_NS.getTsRecordByCount", queryTsRecordCondition);
	}

	public PageResult getTsRecordListByCus(
			QueryTsRecordCondition queryTsRecordCondition) {
		return simpleDao.getPageResult("TsRecord_NS.getTsRecordListByCus", "TsRecord_NS.getTsRecordCountByCus", queryTsRecordCondition);
	}

	public void delTsRecordByCusId(int cusId) {
		  simpleDao.deleteEntity("TsRecord_NS.deleteTsRecordByCusId",cusId);
	}
	
}
