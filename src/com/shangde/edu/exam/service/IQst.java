package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.exam.domain.OptRecord;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.QstRightPercent;
import com.shangde.edu.exam.condition.QueryQstCondition;

/**
 * Qst试题ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IQst extends Serializable {
    /**
     * 添加试题���Qst
     * @param qst 试题
     * @return id
     */
    public java.lang.Integer addQst(Qst qst);

    /**
     * 通过ID删除试题
     * @param qstId 试题Id
     */
    public void delQstById(int qstId);

    /**
     * 更新试题޸�Qst
     * @param qst 试题
     */
    public void updateQst(Qst qst);

    /**
     * 通过ID获取试题
     * @param qstId 试题ID
     * @return 试题
     */
    public Qst getQstById(int qstId);

    /**
     * 根据条件获取试题集合
     * @param queryQstCondition 查询条件
     * @return 试题集合���
     */
    public List<Qst> getQstList(QueryQstCondition queryQstCondition);
    
    /**
     * 通过试卷ID获取试题
     * @param epId 试题ID
     * @return 试题
     */
    public List<Qst> getQstByIdList(int epId);
    
    /**
     * 通过知识点查询epid
     */
    public Qst getQstEpid(int kid);
    
    /**
     * 分页查询弹框
     */
    public PageResult getQstListPageAll(QueryQstCondition queryQstCondition);
 
    /**
     * 分页查询
     */
    public PageResult getQstListPageAllqst(QueryQstCondition queryQstCondition);

    /**
     * 查询所有的课程
     * @return  liming
     */
	public List<Coursesort> getCourseList();
	/** 王超  开始  */
	/**
	 * @author 王超
	 * 获取一周 和所有 做题数和正确做题数
	 * @param cusId
	 * @return
	 */
	public int getRightPercent(int cusId);
	/**
	 * @author 王超
	 * 获取最新错误试题信息
	 * @return
	 */
	public List<Qst> getCurrentErrorQst(int subjectId);
	/**
	 * 获取试卷题目的正确率
	 * @author 王超
	 * @param epId
	 * @return
	 */
	/*public List<QstRightPercent> getRightPercentByEpId(int epId);*/
	/**
	 * 获取试卷对应的试题
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<Qst> getExamQstList(int epId);
	
	/**
	 * 获取试卷对应的试题及选项
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<Qst> getExamQstOptList(int epId);
	
	/**
	 * 更新试题提问数+1
	 * @param qstId
	 */
	public void updateQuesCount(int qstId);
	
	/**
     * 更新试题参与人数+1
     * @author 王超
     * @param optRecord 选项记录
     * @return id
     */
    public  void updateQstDoneNum(List<OptRecord> list);
	/** 王超  结束  */
}