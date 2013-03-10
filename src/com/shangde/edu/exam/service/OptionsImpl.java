package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.condition.QueryOptionsCondition;
import com.shangde.common.service.BaseService;

/**
 * Options�������ʵ����?
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class OptionsImpl extends BaseService implements IOptions{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 添加选项���Options
     * @param options 添加选项
     * @return id
     */
    public java.lang.Integer addOptions(Options options) {
    	return simpleDao.createEntity("Options_NS.createOptions",options);
    }
    
    /**
     * 删除选项��Options
     * @param optId 选项ID
     */
    public void delOptionsById(int optId){
        simpleDao.deleteEntity("Options_NS.deleteOptionsById",optId);
    }
    
    /**
     * 更新选项�޸�Options
     * @param options 要更新的选项
     */
    public void updateOptions(Options options) {
        simpleDao.updateEntity("Options_NS.updateOptions",options);
    }
    
    /**
     * 通过ID获取选项
     * @param optId 选项ID
     * @return 选项
     */
    public Options getOptionsById(int optId) {
        return simpleDao.getEntity("Options_NS.getOptionsById",optId);
    }
    
    /**
     * 通过qstId获取试题选项
     * @param qstId
     * @return
     */
    public List<Options> getOptionsListByQstId(int qstId){
    	return simpleDao.getForList("Options_NS.getOptionsListByQstId", qstId);
    }
    
    /**
     * 获取选项集合
     * @param queryOptionsCondition 选项查询条件
     * @return ���
     */
    public List<Options> getOptionsList(QueryOptionsCondition queryOptionsCondition) {
        return simpleDao.getForList("Options_NS.getOptionsList",queryOptionsCondition);
    }
}
