package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cus.dto.OfficialCustomerDTO;
import com.shangde.edu.sys.domain.Subject;

public class OfficialCustomerImpl extends BaseService implements
		IOfficialCustomer {

	public List<OfficialCustomerDTO> queryOfficialCustomerDTOBySubjectIds(
			List<Subject> subjectList) {
		if (subjectList == null || subjectList.size() == 0) {
			return null;
		}
		return simpleDao.getForList(
				"OfficialCustomer_NS.queryOfficialCustomerDTOBySubjectIds",
				subjectList);
	}

	public boolean checkOfficialCustomer(Integer cusId) {
		Integer result = simpleDao.getEntity(
				"OfficialCustomer_NS.checkOfficialCustomer", cusId);
		if (result != null && result > 0) {
			return true;
		}
		return false;
	}
}
