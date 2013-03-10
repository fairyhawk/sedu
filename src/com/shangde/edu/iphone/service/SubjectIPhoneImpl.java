package com.shangde.edu.iphone.service;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;

public class SubjectIPhoneImpl extends BaseService implements ISubjectIPhone {

	public PageResult getAllSubject(
			QueryCourseIPhoneCondition querySubjectIPhoneCondition) {
		return simpleDao.getPageResult("SubjectIPhone_NS.getAllSubject", "SubjectIPhone_NS.getCountSubject", querySubjectIPhoneCondition);
	}
}
