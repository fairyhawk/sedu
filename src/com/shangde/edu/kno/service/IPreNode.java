package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;

/**
 * PreNode����ӿ�
 * User: guoqiang.liu
 * Date: 2011-08-31
 */
public interface IPreNode extends Serializable{
    /**
     * ���PreNode
     * @param preNode Ҫ��ӵ�PreNode
     * @return id
     */
    public java.lang.Integer addPreNode(PreNode preNode);

    /**
     * ���idɾ��һ��PreNode
     * @param preNodeId Ҫɾ���id
     */
    public void delPreNodeById(int preNodeId);

    /**
     * �޸�PreNode
     * @param preNode Ҫ�޸ĵ�PreNode
     */
    public void updatePreNode(PreNode preNode);

    /**
     * ���id��ȡ����PreNode����
     * @param preNodeId Ҫ��ѯ��id
     * @return �꼶
     */
    public PreNode getPreNodeById(int preNodeId);

    /**
     * ���������ȡPreNode�б�
     * @param queryPreNodeCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<PreNode> getPreNodeList(QueryPreNodeCondition queryPreNodeCondition);
    
    /**
     * 批量插入操作
     * @param preNodes
     */
    public void addbatchCreateEntity(List<PreNode> preNodes);
    
    /**
     * 批量删除操作
     * @param preNodeIds: 预设项子结点的ID
     */
    public void delbatchDelPreNodes(List<Integer> preNodeIds);
    /**
     * 批量修改操作
     * @param preNodes
     */
    public void updatebatchUpdateEntity(List<PreNode> preNodes);
    
    /**
     * 分页查找
     */
    public PageResult getPreNodePageList(QueryPreNodeCondition queryPreNodeCondition);
    
    
    public List<PreNode> getPreNodeListByPreItemId(QueryPreNodeCondition pCondition);
    
    public List<Integer> getPreNodeIdsByPreItemId(QueryPreNodeCondition pCondition);
    
    public int preNodeRelKnowledgeCount(int preNodeId);
    
    public int getProNodeCount(QueryPreNodeCondition pnCondition);
    
    public int getPreNodeCountByName(Map<String , Object> map);
}