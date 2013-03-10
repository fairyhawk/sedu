package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.KnowledgeUnit;
import com.shangde.edu.kb.condition.QueryKnowledgeUnitCondition;

/**
 * KnowledgeUnit管理接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public interface IKnowledgeUnit {
    /**
     * 添加knowledgeUnit
     * @param knowledgeUnit 要添加的knowledgeUnit
     * @return id
     */
    public java.lang.Integer addKnowledgeUnit(KnowledgeUnit knowledgeUnit);

    /**
     * ���idɾ��删除knowledgeUnit
     * @param kId 要删除的id
     */
    public void delKnowledgeUnitById(int kId);

    /**
     * 修改knowledgeUnit޸�KnowledgeUnit
     * @param knowledgeUnit 要修改的knowledgeUnit
     */
    public void updateKnowledgeUnit(KnowledgeUnit knowledgeUnit);

    /**
     * 根据id获取单个knowledgeUnit对象���id��ȡ����KnowledgeUnit����
     * @param kId 查询id
     * @return 知识单元�
     */
    public KnowledgeUnit getKnowledgeUnitById(int kId);

    /**
     * 分页查询��������ȡKnowledgeUnit�б�
     * @param queryKnowledgeUnitCondition ��查询条件���
     * @return 分页查询结果���
     */
    public List<KnowledgeUnit> getKnowledgeUnitList(QueryKnowledgeUnitCondition queryKnowledgeUnitCondition);
}