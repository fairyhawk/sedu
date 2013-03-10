package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class ReProblemImpl extends BaseService implements IReProblem{
    public java.lang.Integer addReProblem(ReProblem reProblem) {
return simpleDao.createEntity("ReProblem_NS.createReProblem",reProblem);
    }

    public void delReProblemById(int reId){
        simpleDao.deleteEntity("ReProblem_NS.deleteReProblemById",reId);
    }

    public void updateReProblem(ReProblem reProblem) {
        simpleDao.updateEntity("ReProblem_NS.updateReProblem",reProblem);
    }

    public ReProblem getReProblemById(int reId) {
        return simpleDao.getEntity("ReProblem_NS.getReProblemById",reId);
    }

    public List<ReProblem> getReProblemList(QueryReProblemCondition queryReProblemCondition) {
        return simpleDao.getForList("ReProblem_NS.getReProblemList",queryReProblemCondition);
    }

	public PageResult getPageReProblemList(QueryReProblemCondition queryReProblemCondition) {
		
		return simpleDao.getPageResult("ReProblem_NS.getReProblemByList", "ReProblem_NS.getReProblemCountByList", queryReProblemCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：获取用户回复数量
	 * @param args
	 * @param queryReProblemCondition
	 * @return
	 */
	public int getCountByCusId(QueryReProblemCondition queryReProblemCondition){
		return simpleDao.getEntity("ReProblem_NS.getCountByCusId", queryReProblemCondition);
	}
	
}
