package com.shangde.edu.finance.service;

import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractCpsDTO;

public interface ICpsDataService {
	public ContractCpsDTO getUserOderAndWebCpsURLByid(Contract c);
}
