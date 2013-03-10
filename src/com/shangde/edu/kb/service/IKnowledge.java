package com.shangde.edu.kb.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.condition.QueryKnowledgeCondition;

/**
 * Knowledge����ӿ�
 * User: guoqiang.liu
 * Date: 2011-01-04
 */
public interface IKnowledge extends Serializable {
    /**
     * ���Knowledge
     * @param knowledge Ҫ��ӵ�Knowledge
     * @return id
     */
    public java.lang.Integer addKnowledge(Knowledge knowledge);

    /**
     * ���idɾ��һ��Knowledge
     * @param kId Ҫɾ���id
     */
    public void delKnowledgeById(int kId);

    /**
     * �޸�Knowledge
     * @param knowledge Ҫ�޸ĵ�Knowledge
     */
    public void updateKnowledge(Knowledge knowledge);

    /**
     * ���id��ȡ����Knowledge����
     * @param kId Ҫ��ѯ��id
     * @return �꼶
     */
    public Knowledge getKnowledgeById(int kId);

    /**
     * ��������ȡKnowledge�б�
     * @param queryKnowledgeCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Knowledge> getKnowledgeList(QueryKnowledgeCondition queryKnowledgeCondition);
    
    public List<Knowledge> getKnowledgeListByScId(QueryKnowledgeCondition queryKnowledgeCondition);
    
    public Knowledge getKnowledgeByIndex(String kIndex);
    /**
     * 根据专业，索引获取课程信息
     * @param queryKnowledgeCondition
     * @return
     */
    public List<Knowledge> getKnowledgeListByPidOrIndex(QueryKnowledgeCondition queryKnowledgeCondition);
    
    /**
     * 根据kid发布课程
     * @param knowledge
     */
    public void publishKnowledge(Knowledge knowledge);
    /**
     * 查询最后一条
     * @param knowledge
     */
    public Knowledge getKnowledgeLast(QueryKnowledgeCondition queryKnowledgeCondition);
    
	/**
	 * author : hhq
	 * 根据epid查询跟试题有关系链的知识节点集合
	 */
    public List<Knowledge> getKnowledgeEpidList(int epid);
    
    /**
     * author:hhq
     * 递归查询
     */
    public List <Knowledge> getKnowledgediguiList(String index);
}