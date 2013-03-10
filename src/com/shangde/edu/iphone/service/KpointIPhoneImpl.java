package com.shangde.edu.iphone.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryKpointIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;

public class KpointIPhoneImpl extends BaseService implements IKpointIPhone {

	public int getKpointSumByCourseId(int courseId) {
		// TODO Auto-generated method stub
		Integer result = simpleDao.getEntity("KpointIPhone_NS.getKpointSumByCourseId",
				courseId);
		if(result==null){
			return 0;
		}
		return result.intValue();
	}

	public String getKpointVoUrlById(int courseId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("KpointIPhone_NS.getKpointVoUrlById",
				courseId);
	}

	public PageResult getIphoneKpointListByPid(
			QueryKpointIPhoneCondition queryKpointIPhoneCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult(
				"KpointIPhone_NS.getIphoneKpointListByPid",
				"KpointIPhone_NS.getIphoneKpointCountByPid",
				queryKpointIPhoneCondition);
	}

	public List<IphoneModel> getKpointListByPid(
			QueryKpointIPhoneCondition queryKpointIPhoneCondition) {
		// TODO Auto-generated method stub
		List<IphoneModel> result = simpleDao.getForList(
				"KpointIPhone_NS.getKpointListByPid",
				queryKpointIPhoneCondition);
		return result;
	}

	public PageResult getKpointPageListByPid(
			QueryKpointIPhoneCondition queryKpointIPhoneCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("KpointIPhone_NS.getKpointPageListByPid", "KpointIPhone_NS.getKpointCountByPid", queryKpointIPhoneCondition);
	}

}
