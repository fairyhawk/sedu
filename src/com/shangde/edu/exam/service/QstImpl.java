package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.exam.domain.OptRecord;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.QstRightPercent;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * IQst接口的实现类
 * 试题操作类
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class QstImpl extends BaseService implements IQst{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 添加试题���Qst
     * @param qst 试题
     * @return id
     */
    public java.lang.Integer addQst(Qst qst) {
    	return simpleDao.createEntity("Qst_NS.createQst",qst);
    }
    
    /**
     * 通过ID删除试题
     * @param qstId 试题Id
     */
    public void delQstById(int qstId){
        simpleDao.deleteEntity("Qst_NS.deleteQstById",qstId);
    }
    
    /**
     * 更新试题޸�Qst
     * @param qst 试题
     */
    public void updateQst(Qst qst) {
        simpleDao.updateEntity("Qst_NS.updateQst",qst);
    }
    
    /**
     * 通过ID获取试题
     * @param qstId 试题ID
     * @return 试题
     */
    public Qst getQstById(int qstId) {
        return simpleDao.getEntity("Qst_NS.getQstById",qstId);
    }
    
    /**
     * 通过试卷ID获取试题
     * @param epId 试题ID
     * @return 试题
     */
    public List<Qst> getQstByIdList(int epId) {
        return simpleDao.getForList("Qst_NS.getQstListByEpid",epId);
    }
    
    /**
     * 根据条件获取试题集合
     * @param queryQstCondition 查询条件
     * @return 试题集合���
     */
    public List<Qst> getQstList(QueryQstCondition queryQstCondition) {
        return simpleDao.getForList("Qst_NS.getQstList",queryQstCondition);
    }
    
    /**
     * 
     */
    public Qst getQstEpid(int kid)
    {
    	 return simpleDao.getEntity("Qst_NS.getQstEpid", kid);
    }
    
    
    /**
     * 分页查询弹框
     */
    public PageResult getQstListPageAll(QueryQstCondition queryQstCondition){
    	
    	return  simpleDao.getPageResult("Qst_NS.getQstListPage", "Qst_NS.getQstListPageCount", queryQstCondition);
    }
    
    /**
     * 分页查询
     */
    public PageResult getQstListPageAllqst(QueryQstCondition queryQstCondition){
    	
    	return  simpleDao.getPageResult("Qst_NS.getQstListPageqst", "Qst_NS.getQstListPageCountqst", queryQstCondition);
    }
    /**
     * 查询所有的课程
     * @return  liming
     */
	public List<Coursesort> getCourseList() {
		return simpleDao.getForList("Qst_NS.getCourseList", null);
	}
	
	/**
	 * @author 王超
	 * 获取一周 和所有 做题数和正确做题数
	 * @param cusId
	 * @return
	 */
	public int getRightPercent(int cusId){
		return simpleDao.getEntity("Qst_NS.QstRightPercent", cusId);
	}
	
	/**
	 * @author 王超
	 * 获取最新错误试题信息
	 * @return
	 */
	public List<Qst> getCurrentErrorQst(int subjectId){
		return simpleDao.getForList("Qst_NS.getCurrentErrorQst", subjectId);
	}
	
	/**
	 * 获取试卷题目的正确率
	 * @author 王超
	 * @param epId
	 * @return
	 */
	/*public List<QstRightPercent> getRightPercentByEpId(int epId){
		return simpleDao.getForList("Qst_NS.getRightPercentByEpId", epId);
	}*/
	
	/**
	 * 获取试卷对应的试题
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<Qst> getExamQstList(int epId){
		return simpleDao.getForList("Qst_NS.getExamQstList", epId);
	}
	
	/**
	 * 获取试卷对应的试题及选项
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<Qst> getExamQstOptList(int epId){
		return simpleDao.getForList("Qst_NS.getExamQstOptList", epId);
	}
	
	/**
	 * 更新试题提问数+1
	 * @param qstId
	 */
	public void updateQuesCount(int qstId){
		simpleDao.update("Qst_NS.updateQuesCount", qstId);
	}
	
	/**
     * 更新试题参与人数+1
     * @author 王超
     * @param optRecord 选项记录
     * @return id
     */
    public  void updateQstDoneNum(List<OptRecord> list){
    	simpleDao.createEntity("Qst_NS.updateQstDoneNum", list);
    }
}
