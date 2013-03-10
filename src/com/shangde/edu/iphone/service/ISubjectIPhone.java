package com.shangde.edu.iphone.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;

public interface ISubjectIPhone {
	
	
	
	/**
	 * 得到所有专业
	 * @return
	 */
	public PageResult getAllSubject(QueryCourseIPhoneCondition querySubjectIPhoneCondition);

}
