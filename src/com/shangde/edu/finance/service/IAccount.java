package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Account;
import com.shangde.edu.finance.condition.QueryAccountCondition;

/**
 * Account����ӿ�
 * User: guoqiang.liu
 * Date: 2010-08-13
 */
public interface IAccount {
    /**
     * ���Account
     * @param account Ҫ��ӵ�Account
     * @return id
     */
    public java.lang.Integer addAccount(Account account);

    /**
     * ����idɾ��һ��Account
     * @param id Ҫɾ����id
     */
    public void delAccountById(int id);

    /**
     * �޸�Account
     * @param account Ҫ�޸ĵ�Account
     */
    public void updateAccount(Account account);

    /**
     * ����id��ȡ����Account����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public Account getAccountById(int id);

    /**
     * ����������ȡAccount�б�
     * @param queryAccountCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<Account> getAccountList(QueryAccountCondition queryAccountCondition);
}