package com.shangde.edu.cus.web;

import com.opensymphony.xwork2.ActionSupport;
import com.shangde.edu.cus.service.TestService;

public class TestAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6929375396481855465L;
	private TestService testService;

	public String test() {
		System.out.println(1111111);
		System.out.println(testService.getTest());
		System.out.println(222222);
		return "success";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	};

}
