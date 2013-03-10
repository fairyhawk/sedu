package com.shangde.edu.freshnews.service;

import java.util.List;
import com.shangde.edu.freshnews.domain.ActionReply;
import com.shangde.edu.freshnews.condition.QueryActionReplyCondition;

/**
 * ActionReply����ӿ�
 * User: guoqiang.liu
 * Date: 2012-06-14
 */
public interface IActionReply {
    /**
     * ���ActionReply
     * @param actionReply Ҫ��ӵ�ActionReply
     * @return id
     */
    public void addActionReply(ActionReply actionReply);

    /**
     * ���idɾ��һ��ActionReply
     */
    public void delActionReplyById();

    /**
     * �޸�ActionReply
     * @param actionReply Ҫ�޸ĵ�ActionReply
     */
    public void updateActionReply(ActionReply actionReply);

    /**
     * ���id��ȡ����ActionReply����
     * @return �꼶
     */
    public ActionReply getActionReplyById();

    /**
     * ���������ȡActionReply�б�
     * @param queryActionReplyCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<ActionReply> getActionReplyList(QueryActionReplyCondition queryActionReplyCondition);
}