package com.shangde.edu.cus.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCusProtocalCondition;
import com.shangde.edu.cus.domain.CusProtocalDetail;

public interface ICusProtocalDetail extends Serializable {
   
	public int addCusProtocalDetail (CusProtocalDetail detail);
	
	public int delCusProtocalDetailById (int id);
	
	public CusProtocalDetail getCusProtocalDetailById(int id);
	
	public List<CusProtocalDetail> getCusProtocalDetailList(QueryCusProtocalCondition condition);
	
	public PageResult getCusProtocalDetailPage(QueryCusProtocalCondition condition);
	
	/**
	 * 前台订单页面使用,显示保过协议内容
	 * @param cashId
	 * @return
	 */
	public CusProtocalDetail getCusProtocalDetailSellId(int cashId);
	
}