package com.shangde.edu.cus.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;


public interface ITsRecord extends Serializable {
  
    public java.lang.Integer addTsRecord(TsRecord tsRecord);

    public void delTsRecordById(int trId);

    public void updateTsRecord(TsRecord tsRecord);

    public TsRecord getTsRecordById(int trId);

    public List<TsRecord> getTsRecordList(QueryTsRecordCondition queryTsRecordCondition);
    
    public PageResult getTsRecordPageList(QueryTsRecordCondition queryTsRecordCondition);

	public PageResult getTsRecordListByCus(
			QueryTsRecordCondition queryTsRecordCondition);
	public void delTsRecordByCusId(int cusId);
	
}