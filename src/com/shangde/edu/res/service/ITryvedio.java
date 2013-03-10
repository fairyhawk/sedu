package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.condition.QueryTryvedioCondition;

/**
 * Tryvedio����ӿ�
 * User: guoqiang.liu
 * Date: 2011-03-04
 */
public interface ITryvedio {
    /**
     * ���Tryvedio
     * @param tryvedio Ҫ��ӵ�Tryvedio
     * @return id
     */
    public java.lang.Integer addTryvedio(Tryvedio tryvedio);

    /**
     * ���idɾ��һ��Tryvedio
     * @param id Ҫɾ���id
     */
    public void delTryvedioById(int id);

    /**
     * �޸�Tryvedio
     * @param tryvedio Ҫ�޸ĵ�Tryvedio
     */
    public void updateTryvedio(Tryvedio tryvedio);

    /**
     * ���id��ȡ����Tryvedio����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public Tryvedio getTryvedioById(int id);

    /**
     * ��������ȡTryvedio�б�
     * @param queryTryvedioCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Tryvedio> getTryvedioList(QueryTryvedioCondition queryTryvedioCondition);
    
}