package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCusProtocalCondition;
import com.shangde.edu.cus.domain.CusProtocalDetail;


public class CusProtocalDetailImpl extends BaseService implements ICusProtocalDetail{

	private static final long serialVersionUID = -9054100921514669997L;

	@Override
	public int addCusProtocalDetail(CusProtocalDetail detail){
		return simpleDao.createEntity("CusProtocalDetail_NS.createCusProtocalDetail", detail);
	}

	@Override
	public int delCusProtocalDetailById(int id) {
		return simpleDao.delete("CusProtocalDetail_NS.deleteCusProtocalDetailById", id);
	}

	@Override
	public CusProtocalDetail getCusProtocalDetailById(int id) {
		return simpleDao.getEntity("CusProtocalDetail_NS.getCusProtocalDetailById", id);
	}

	@Override
	public List<CusProtocalDetail> getCusProtocalDetailList(QueryCusProtocalCondition condition) {
		return null;
	}

	@Override
	public PageResult getCusProtocalDetailPage(QueryCusProtocalCondition condition) {
		return simpleDao.getPageResult("CusProtocalDetail_NS.getCusProtocalDetailByPage", "CusProtocalDetail_NS.getCusProtocalDetailCount", condition);
	}

	@Override
	public CusProtocalDetail getCusProtocalDetailSellId(int cashId) {
		return simpleDao.getEntity("CusProtocalDetail_NS.getCusProtocalByCashId", cashId);
	}
	
}
