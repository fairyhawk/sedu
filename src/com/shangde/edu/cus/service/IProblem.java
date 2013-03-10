package com.shangde.edu.cus.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.domain.Problem;

public interface IProblem {
	
	/**
	 * 根据项目ID获取该专业下有多少问题
	 * @param subjectId 专业ID
	 * @return
	 */
	public int getCusProblemCount(int subjectId);
	
	/**
	 * 根据项目ID获取该专业下有多少回复问题
	 * @param subjectId 专业ID
	 * @return
	 */
	public int getCusProblemReplyCount(int subjectId);
	/**
     * 添加Problem
     * 
     */
    public java.lang.Integer addProblem(Problem problem);

    /**
     * 删除Problem
     * 根据问题 id
     */
    public void delProblemById(int pblId);
    
    /**
     * 更新Problem
     */
    public void updateProblem(Problem problem);

    /**
     * 根据问题id获得整个Problem对象
     */
    public Problem getProblemById(int pblId);

    /**
     * 根据条件查询获得,List
     * 
     */
    public List<Problem> getProblemList(QueryProblemCondition queryProblemCondition);
    
    /**
     * 
     * 后台分页查询
     * 
     */
    public PageResult getPageProblemList(QueryProblemCondition queryProblemCondition);
    
    /**
     * 
     * 前台分页查询
     * 
     */
    public PageResult getPageProblemListById(QueryProblemCondition queryProblemCondition);
    /**
     * 
     * 查最新的十条热门问题
     * 
     */
    public List<Problem> getProblemByHost(QueryProblemCondition queryProblemCondition);
    
	/**
	 * 
	 * 根据用户id返回回答数
	 */
	public PageResult getReProblemByCusId(QueryProblemCondition queryProblemCondition);
	/**
	 * 
	 * 查出最新，高分，已解决，的六个问题
	 */
	public List<Problem> getNewProblem(QueryProblemCondition queryProblemCondition);
	
	public List<Problem> getProblemCountBySubjectId(String subjectid);
}