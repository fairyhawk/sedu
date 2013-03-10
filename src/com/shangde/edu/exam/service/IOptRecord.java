package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.domain.OptRecord;
import com.shangde.edu.exam.dto.UserQst;
import com.shangde.edu.exam.condition.QueryOptRecordCondition;

/**
 * OptRecord 选项记录
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IOptRecord extends Serializable {
    /**
     * 添加试题记录���OptRecord
     * @param optRecord 选项记录
     * @return id
     */
    public  void addOptRecord(OptRecord optRecord);

    /**
     * 删除选项记录
     */
    public void delOptRecordById(int qstId, int asrId, int cusId);

    /**
     * 更新选项记录�޸�OptRecord
     * @param optRecord 选项
     */
    public void updateOptRecord(OptRecord optRecord);

    /**
     * 通过ID获取选项记录
     * @return 
     */
    public OptRecord getOptRecordById(int qstId, int asrId, int cusId);

    /**
     * 获取选项记录集合
     * @param queryOptRecordCondition 查询条件
     * @return 查询结果集合���
     */
    public List<OptRecord> getOptRecordList(
);
    /**
     * 删除根据用户id
     */
    public void delOptRecordByCusId(int cusId);
    
    /**
	 * 通过试卷Id获取用户做题历史记录
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<UserQst> getUserQstOptionList(int epId);
	
	/**
     * 一次添加所有试题记录
     * @author 王超
     * @param optRecord 选项记录
     * @return id
     */
    public  void addManyOptRecord(List<OptRecord> list);
    /*
     * 得到错题记录
     */
    public PageResult getErrorQstList(QueryOptRecordCondition queryOptRecordCondition);
    

public Integer getErrorQstCountBycusId(Integer userId);
    
}