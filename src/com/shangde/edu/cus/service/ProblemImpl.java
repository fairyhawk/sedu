package com.shangde.edu.cus.service;

import java.util.List;
import java.util.Map;


import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.service.IActionRecord;
import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;


public class ProblemImpl extends BaseService implements IProblem{
	private IActionRecord actionRecordService;//新鲜事service
    public IActionRecord getActionRecordService() {
		return actionRecordService;
	}

	public void setActionRecordService(IActionRecord actionRecordService) {
		this.actionRecordService = actionRecordService;
	}

	public java.lang.Integer addProblem(Problem problem) {
		Integer id=simpleDao.createEntity("Problem_NS.createProblem",problem);
		actionRecordService.addActionRecordByProblem(problem);
    	return id;
    }
	

    public void delProblemById(int pblId){
        simpleDao.deleteEntity("Problem_NS.deleteProblemById",pblId);
    }

    public void updateProblem(Problem problem) {
        simpleDao.updateEntity("Problem_NS.updateProblem",problem);
    }

    public Problem getProblemById(int pblId) {
        return simpleDao.getEntity("Problem_NS.getProblemById",pblId);
    }

    public List<Problem> getProblemList(QueryProblemCondition queryProblemCondition) {
        return simpleDao.getForList("Problem_NS.getProblemList",queryProblemCondition);
    }

	public PageResult getPageProblemList(QueryProblemCondition queryProblemCondition) {
		PageResult pr = simpleDao.getPageResult("Problem_NS.getProblemByList", "Problem_NS.getProblemCountByList", queryProblemCondition);
//		List<Problem> pageResult=pr.getPageResult();
//		StringUtil su = new StringUtil();
//		for(int i=0;pageResult!=null&&i<pageResult.size();i++){
//			pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 10, "..."));
//		}
		return pr;
	}

	public PageResult getPageProblemListById(QueryProblemCondition queryProblemCondition) {
		PageResult pe=simpleDao.getPageResult("Problem_NS.getProblemByIdList", "Problem_NS.getProblemCountByIdList", queryProblemCondition);
		List<Problem> pageResult=pe.getPageResult();
		StringUtil su = new StringUtil();
		for(int i=0;pageResult!=null&&i<pageResult.size();i++){
			pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 28, "..."));
		}
		return pe;
	}
	 public List<Problem> getProblemByHost(QueryProblemCondition queryProblemCondition){
		 List<Problem> pageResult=simpleDao.getForList("Problem_NS.getProblemByHost",queryProblemCondition);
		 StringUtil su = new StringUtil();
			for(int i=0;pageResult!=null&&i<pageResult.size();i++){
				pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 14, "..."));
			}
		 return pageResult;
	 }
	 public PageResult getReProblemByCusId(QueryProblemCondition queryProblemCondition){
			return simpleDao.getPageResult("Problem_NS.getReProblemByCusId","Problem_NS.getReProblemCountByCusId", queryProblemCondition);
		}
	 public List<Problem> getNewProblem(QueryProblemCondition queryProblemCondition){
		 List<Problem> pageResult=simpleDao.getForList("Problem_NS.getNewProblem", queryProblemCondition);
			StringUtil su = new StringUtil();
			for(int i=0;pageResult!=null&&i<pageResult.size();i++){
				pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 28, "..."));
			}
		 return pageResult;
	 }

	@Override
	public int getCusProblemCount(int subjectId) {
		return simpleDao.getEntity("Problem_NS.getCusProblemBySubjectId", subjectId);
	}

	@Override
	public int getCusProblemReplyCount(int subjectId) {
		return simpleDao.getEntity("Problem_NS.getCusProblemReplyBySubjectId", subjectId);
	}

	@Override
	public List<Problem> getProblemCountBySubjectId(String subjectid) {
		return simpleDao.getForList("Problem_NS.getProCountByType", subjectid);
	}
    
}
