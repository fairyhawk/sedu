package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.exam.domain.QstKb;
import com.shangde.edu.exam.condition.QueryQstKbCondition;
import com.shangde.edu.exam.condition.QueryQstKbExamRecordCondition;

/**
 * QstKb����ӿ�
 * User: guoqiang.liu
 * Date: 2011-01-17
 */
public interface IQstKb extends Serializable {
    /**
     * ���QstKb
     * @param qstKb Ҫ��ӵ�QstKb
     * @return id
     */
    public java.lang.Integer addQstKb(QstKb qstKb);

    /**
     * ����idɾ��һ��QstKb
     * @param id Ҫɾ����id
     */
    public void delQstKbById(int id);

    /**
     * �޸�QstKb
     * @param qstKb Ҫ�޸ĵ�QstKb
     */
    public void updateQstKb(QstKb qstKb);

    /**
     * ����id��ȡ����QstKb����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public QstKb getQstKbById(int id);

    /**
     * ����epid��ѯ����֪ʶ������
     */
    
    public int getQstEpId(int epid);
    
    
    /**
     * ����������ȡQstKb�б�
     * @param queryQstKbCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<QstKb> getQstKbList(QueryQstKbCondition queryQstKbCondition);
    
    /**
     * ��ѯ�ܵ�����
     */
    public int getCount(QueryQstKbExamRecordCondition qqke);
    
    /**
     * ��ѯ��ȷ����
     */
    public int getrightCount(QueryQstKbExamRecordCondition qqke);
    
    /**
     * ��ѯΪ������
     */
    
    public int getisNoCount(QueryQstKbExamRecordCondition qqke);
}