package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.dto.OfficialCustomerDTO;
import com.shangde.edu.sys.domain.Subject;

public interface IOfficialCustomer {

	public List<OfficialCustomerDTO> queryOfficialCustomerDTOBySubjectIds(
			List<Subject> subjectList);

	public boolean checkOfficialCustomer(Integer cusId);

}
