package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.exam.domain.QstKb;
import com.shangde.edu.exam.condition.QueryQstKbCondition;
import com.shangde.edu.exam.condition.QueryQstKbExamRecordCondition;

/**
 * QstKb管理接口
 * User: guoqiang.liu
 * Date: 2011-01-17
 */
public interface IQstKb extends Serializable {
    /**
     * 添加QstKb
     * @param qstKb 要添加的QstKb
     * @return id
     */
    public java.lang.Integer addQstKb(QstKb qstKb);

    /**
     * 根据id删除一个QstKb
     * @param id 要删除的id
     */
    public void delQstKbById(int id);

    /**
     * 修改QstKb
     * @param qstKb 要修改的QstKb
     */
    public void updateQstKb(QstKb qstKb);

    /**
     * 根据id获取单个QstKb对象
     * @param id 要查询的id
     * @return 年级
     */
    public QstKb getQstKbById(int id);

    /**
     * 根据epid查询试题知识点数量
     */
    
    public int getQstEpId(int epid);
    
    
    /**
     * 根据条件获取QstKb列表
     * @param queryQstKbCondition 查询条件
     * @return 查询结果
     */
    public List<QstKb> getQstKbList(QueryQstKbCondition queryQstKbCondition);
    
    /**
     * 查询总的条数
     */
    public int getCount(QueryQstKbExamRecordCondition qqke);
    
    /**
     * 查询正确条数
     */
    public int getrightCount(QueryQstKbExamRecordCondition qqke);
    
    /**
     * 查询为空条数
     */
    
    public int getisNoCount(QueryQstKbExamRecordCondition qqke);
}