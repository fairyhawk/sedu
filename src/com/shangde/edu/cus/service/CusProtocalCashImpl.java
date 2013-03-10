package com.shangde.edu.cus.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cus.domain.CusCashProtocal;

/**
 * 
 * @author Yangning
 *	2012/03/26
 */
public class CusProtocalCashImpl extends BaseService implements ICusProtocalCash{

	private static final long serialVersionUID = -9054100921514669997L;

	@Override
	public int addCusProtocalCash(CusCashProtocal cusCashprotocal) {
		return simpleDao.createEntity("CusProtocalCash_NS.createCusProtocalCash", cusCashprotocal);
	}
	
}
