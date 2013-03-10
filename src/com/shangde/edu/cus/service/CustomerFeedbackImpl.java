package com.shangde.edu.cus.service;


import com.shangde.common.service.BaseService;
import com.shangde.edu.cus.domain.CustomerFeedback;

/**
 * Description:用户反馈信息接口
 * User: Yangning
 * Date: 2011-11-09
 */
public class CustomerFeedbackImpl extends BaseService implements ICustomerFeedback {
	
	@Override
	public int addCustomerFeedback(CustomerFeedback feedback) throws Exception {
		int result = 0;
		try{
			result = simpleDao.createEntity("CustoerFeedback_NS.createCustomerFeedback", feedback);
		}catch(Exception e){
			logger.error("CustomerFeedbackImpl.addCustomerFeedback", e);
			throw new Exception (e.getMessage());
		}
		return result;
	}
}
