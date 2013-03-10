package com.shangde.edu.sys.action;


import java.util.Date;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.sys.domain.SignUp;
import com.shangde.edu.sys.service.SignUpService;

public class SignUpAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SignUpAction.class);
	private SignUp signUp;
	
	private SignUpService signUpService;


	public SignUpService getSignUpService() {
		return signUpService;
	}

	public void setSignUpService(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	public SignUp getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}
	
	/**
	 * 新增最新注会查询
	 * @return
	 */
	public String addSignUp() {
		try {
			signUp.setCreateDate(new Date(System.currentTimeMillis()));
			this.getSignUpService().addSignUp(signUp);
			this.setResult(new Result(true,"","","success"));
			Result result=this.getResult();
			return "signUp_add";
		} catch (Exception e) {
			logger.error("SignUpAction.addSignUp",e);
			return ERROR;
		}

	}
	/**
	 * 进入最新注会查询增加页面
	 * @return
	 */
	public String toAddSignUp() {
		try {
			return "signUp_toAdd";
		} catch (Exception e) {
			logger.error("SignUpAction.toAddSignUp",e);
			return ERROR;
		}

	}
}
