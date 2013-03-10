package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.domain.SignUp;

public class SignUpServiceImpl extends BaseService implements SignUpService {

	/**
	 * 新增最新注会查询
	 * @param signUp
	 */
	public void addSignUp(SignUp signUp) {
		this.simpleDao.createEntity("SignUp_NS.createSignUp", signUp);
	}

	
}
