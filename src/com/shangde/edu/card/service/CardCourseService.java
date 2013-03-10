package com.shangde.edu.card.service;


import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardCourseCondition;
import com.shangde.edu.card.domain.*;

public interface CardCourseService {
	
	/**
	 * 查询课程列表
	 * @param queryCardCourseCondition
	 * @return
	 * @throws Exception
	 */
	PageResult getCardCourseList(QueryCardCourseCondition queryCardCourseCondition,Integer cusId) throws Exception; 
	/**
	 * 校验课程卡号和课程密码是否正确
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	CardCourse checkCardCourse(CardCourse cardCourse) throws Exception;
	/**
	 * 更新课程卡的用户名称及更新人和更新时间
	 * @param cardCourse
	 * @throws Exception
	 */
	Integer updateCardCourse(CardCourse cardCourse,Integer cusId) throws Exception;
	
}
