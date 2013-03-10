package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.condition.QueryOptionsCondition;

/**
 * Options 选项接口
 * 试题选项接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IOptions extends Serializable {
    /**
     * 添加选项���Options
     * @param options 添加选项
     * @return id
     */
    public java.lang.Integer addOptions(Options options);

    /**
     * 删除选项��Options
     * @param optId 选项ID
     */
    public void delOptionsById(int optId);

    /**
     * 更新选项�޸�Options
     * @param options 要更新的选项
     */
    public void updateOptions(Options options);

    /**
     * 通过ID获取选项
     * @param optId 选项ID
     * @return 选项
     */
    public Options getOptionsById(int optId);
    
    /**
     * 通过qstId获取试题选项
     * @param qstId
     * @return
     */
    public List<Options> getOptionsListByQstId(int qstId);

    /**
     * 获取选项集合
     * @param queryOptionsCondition 选项查询条件
     * @return ���
     */
    public List<Options> getOptionsList(QueryOptionsCondition queryOptionsCondition);
}