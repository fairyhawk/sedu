package com.shangde.edu.res.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.condition.QueryClazzCouCondition;
import com.shangde.edu.res.domain.VedioCount;

public class VedioCountImpl  extends BaseService implements IVedioCount{
	

	@Override
	public Integer addCount(VedioCount vedio) {
	
		 return simpleDao.createEntity("VedioCount_NS.createVedioCount",vedio);
	}





	
	
}
