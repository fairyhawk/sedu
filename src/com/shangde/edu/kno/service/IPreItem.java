package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.kno.domain.PreItem;
import com.shangde.edu.kno.condition.QueryPreItemCondition;

/**
 * PreItem����ӿ�
 * User: guoqiang.liu
 * Date: 2011-08-31
 */
public interface IPreItem extends Serializable{
    /**
     * ���PreItem
     * @param preItem Ҫ��ӵ�PreItem
     * @return id
     */
    public java.lang.Integer addPreItem(PreItem preItem);

    /**
     * ���idɾ��һ��PreItem
     * @param preId Ҫɾ���id
     */
    public void delPreItemById(int preId);

    /**
     * �޸�PreItem
     * @param preItem Ҫ�޸ĵ�PreItem
     */
    public void updatePreItem(PreItem preItem);

    /**
     * ���id��ȡ����PreItem����
     * @param preId Ҫ��ѯ��id
     * @return �꼶
     */
    public PreItem getPreItemById(int preId);

    /**
     * ���������ȡPreItem�б�
     * @param queryPreItemCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<PreItem> getPreItemList(QueryPreItemCondition queryPreItemCondition);
    
    public PageResult getPreItemPageList(QueryPreItemCondition pCondition);
    /**
     * 查询此预设项是否和知识树已有关联
     * @param preItemId
     * @return
     */
    public int preItemRelKnowledgeCount(int preItemId);
    
    public int getPreItemCountByName(Map<String, Object> map);
}