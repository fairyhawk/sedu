package com.shangde.edu.card.service;


import java.util.Date;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardCourseCondition;
import com.shangde.edu.card.domain.CardCourse;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;

public class CardCourseServiceImpl extends BaseService implements
		CardCourseService {

	private ICustomer customerService;//客户service
	
	public ICustomer getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	/**
	 * 查询课程列表
	 * @param queryCardCourseCondition
	 * @return
	 * @throws Exception
	 */
	public PageResult getCardCourseList(
			QueryCardCourseCondition queryCardCourseCondition,Integer cusId) throws Exception {
		queryCardCourseCondition.setUserAccount(customerService.getCustomerById(cusId).getEmail());
		return simpleDao.getPageResult("CardCourse_NS.getCardCourseList", "CardCourse_NS.getCardCourseCount", queryCardCourseCondition);
	}

	/**
	 * 校验课程卡号和课程密码是否正确
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	public CardCourse checkCardCourse(CardCourse cardCourse) throws Exception {
		return simpleDao.getEntity("CardCourse_NS.getCardCourse", cardCourse);
		
	}
	/**
	 * 更新课程卡的用户名称及更新人和更新时间
	 * @param cardCourse
	 * @throws Exception
	 */
	public Integer updateCardCourse(CardCourse cardCourse,Integer cusId) throws Exception {
		if(fillCardCourse(cardCourse,cusId)){
			simpleDao.updateEntity("CardCourse_NS.updateCardCourse", cardCourse);
			return  simpleDao.getEntity("CardCourse_NS.selectCardMainId", cardCourse);
		}
		return null;
	}
	private boolean fillCardCourse(CardCourse cardCourse,Integer cusId){
		Customer customer=customerService.getCustomerById(cusId);
		if(customer !=null){
			cardCourse.setUserAccount(customer.getEmail());
			cardCourse.setCardCourseStatus(1);
			cardCourse.setUpdateUser(customer.getEmail());
			cardCourse.setUpdateTime(new Date(System.currentTimeMillis()));
			return true;
		}
		return false;
	}
}
