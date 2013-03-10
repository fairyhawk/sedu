package com.shangde.edu.freshnews.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.condition.QueryActionRecordCondition;

/**
 * ActionRecord����ӿ�
 * User: guoqiang.liu
 * Date: 2012-06-14
 */
public interface IActionRecord {
    /**
     * ���ActionRecord
     * @param actionRecord Ҫ��ӵ�ActionRecord
     * @return id
     */
    public void  addActionRecord(ActionRecord actionRecord);

    /**
     * ���idɾ��һ��ActionRecord
     */
    public void delActionRecordById();

    /**
     * �޸�ActionRecord
     * @param actionRecord Ҫ�޸ĵ�ActionRecord
     */
    public void updateActionRecord(ActionRecord actionRecord);

    /**
     * ���id��ȡ����ActionRecord����
     * @return �꼶
     */
    public ActionRecord getActionRecordById();

    /**
     * ���������ȡActionRecord�б�
     * @param queryActionRecordCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<ActionRecord> getActionRecordList(QueryActionRecordCondition queryActionRecordCondition);
    /**
     * 根据highso问答添加新鲜事
     * @param problem
     * @throws Exception
     */
    public void addActionRecordByProblem(Problem problem);
    /**
     * 获取新鲜事
     */
    public PageResult getActionRecords(QueryActionRecordCondition queryActionRecordCondition);
}