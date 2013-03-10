package com.shangde.edu.feed.service.impl;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.condition.QueryEntranceCondition;
import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.condition.QueryUserUseCondition;
import com.shangde.edu.feed.domain.From;
import com.shangde.edu.feed.domain.StudentStat;
import com.shangde.edu.feed.domain.UserStatLog;
import com.shangde.edu.feed.dto.StudentStatDTO;
import com.shangde.edu.feed.service.IStudentStat;

public class StudentStatImpl extends BaseService implements IStudentStat {
    /*
     * (non-Javadoc)
     * @see com.shangde.edu.feed.service.IStudentStat#insertStudentStat(com.shangde.edu.feed.domain.StudentStat)
     */
	@Override
	public int addStudentStat(StudentStat studentStat) {
		return simpleDao.createEntity("StudentStat_NS.createStudentStat", studentStat);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#getPageFlowCount(com.shangde.edu.feed.condition.QueryEntranceCondition)
	 */
	@Override
	public int getPageFlowCount(QueryEntranceCondition queryEntranceCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("StudentStat_NS.queryPageFlowCount", queryEntranceCondition);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IStudentStat#getStudentStatCount(QueryUserUseCondition queryUserUseCondition)
	 */
	@Override
	public  List<StudentStatDTO> getStudentUseCount(QueryUserUseCondition queryUserUseCondition) {
		return simpleDao.getForList("StudentStat_NS.queryStudentUseCount", queryUserUseCondition);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryStudentUseLogin(com.shangde.edu.feed.condition.QueryUserUseCondition)
	 */
	@Override
	public List<StudentStatDTO> queryStudentUseLogin(QueryUserUseCondition queryUserUseCondition) {
		return simpleDao.getForList("StudentStat_NS.queryStudentUseLogin", queryUserUseCondition);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryStudentUseRegister(com.shangde.edu.feed.condition.QueryUserUseCondition)
	 */
	@Override
	public List<StudentStatDTO> queryStudentUseRegister(QueryUserUseCondition queryUserUseCondition) {
		return simpleDao.getForList("StudentStat_NS.queryStudentUseRegister", queryUserUseCondition);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IStudentStat#getFirstUserCount(QueryUserStatLogCondition queryUserStatLogCondition)
	 */
	@Override
	public  List<StudentStatDTO>  getFirstUserCount(Map<String,Object> map) {
		return simpleDao.getForList("StudentStat_NS.queryFirstUserCount", map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IStudentStat#getNonRepeatUserCount(QueryUserStatLogCondition queryUserStatLogCondition)
	 */
	@Override
	public List<StudentStatDTO> getNonRepeatUserCount(Map<String,Object> map) {
		return simpleDao.getForList("StudentStat_NS.queryNonRepeatUserCount", map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#getRepeatUserCount(com.shangde.edu.feed.condition.QueryUserStatLogCondition)
	 */
	@Override
	public List<StudentStatDTO> getRepeatUserCount(Map<String,Object> map) {
		return simpleDao.getForList("StudentStat_NS.queryRepeatUserCount", map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryStudentStatList()
	 */
	@Override
	public List<StudentStat> queryStudentStatList(String sreachDate) {
		return simpleDao.getForList("StudentStat_NS.queryStudentStatList",sreachDate);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#sreachStudentStat(java.lang.String)
	 */
	@Override
	public List<StudentStat> searchStudentStat(String searchDate) {
		return simpleDao.getForList("StudentStat_NS.searchStudentStat", searchDate);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryOrderCount(java.util.List)
	 */
	@Override
	public int queryOrderCount(Map<String,Object> map) {
		return simpleDao.getEntity("StudentStat_NS.queryOrderCount", map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#getBringWater(java.util.List)
	 */
	@Override
	public String getBringWater(Map<String,Object> map) {
		return simpleDao.getEntity("StudentStat_NS.getBringWater", map);
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#searchStuStatByFromIds(java.lang.String)
	 */
	@Override
	public List<StudentStat> searchStuStatByFromIds(Map<String,Object> map) {
			return simpleDao.getForList("StudentStat_NS.searchStuStatByFromIds", map);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shangde.edu.feed.service.IStudentStat#queryUserStatLog()
	 */
	@Override
	public List<UserStatLog> queryUserStatLog(QueryUserStatLogCondition queryUserStatLogCondition) {
		return simpleDao.getForList("StudentStat_NS.queryUserStatLog", queryUserStatLogCondition);
	}
}
