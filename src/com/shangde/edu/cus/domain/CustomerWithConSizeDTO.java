package com.shangde.edu.cus.domain;

import java.io.Serializable;

import com.shangde.edu.sys.domain.Subject;

public class CustomerWithConSizeDTO extends Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contractCount;
	private int contractStatus;
	private Subject cusSubject;

	public int getContractCount() {
		return contractCount;
	}

	public void setContractCount(int contractCount) {
		this.contractCount = contractCount;
	}

	public Subject getCusSubject() {
		return cusSubject;
	}

	public void setCusSubject(Subject cusSubject) {
		this.cusSubject = cusSubject;
	}

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
	}
}
