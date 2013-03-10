package com.shangde.edu.finance.dto;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;

public class ContractUserCenterDTO extends Contract implements Serializable {
	
	private List<CashRecordDTO> cashList;

	public List<CashRecordDTO> getCashList() {
		return cashList;
	}

	public void setCashList(List<CashRecordDTO> cashList) {
		this.cashList = cashList;
	}
}
