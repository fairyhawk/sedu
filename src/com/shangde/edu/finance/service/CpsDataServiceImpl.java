package com.shangde.edu.finance.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractCpsDTO;

public class CpsDataServiceImpl extends BaseService implements ICpsDataService {

	public ContractCpsDTO getUserOderAndWebCpsURLByid(Contract c){
		return simpleDao.getEntity("Contract_NS.getUserOderAndWebCpsURLByid", c);
	}
	
}
