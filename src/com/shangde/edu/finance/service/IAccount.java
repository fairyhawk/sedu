package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Account;
import com.shangde.edu.finance.condition.QueryAccountCondition;

/**
 * Account管理接口
 * User: guoqiang.liu
 * Date: 2010-08-13
 */
public interface IAccount {
    /**
     * 添加Account
     * @param account 要添加的Account
     * @return id
     */
    public java.lang.Integer addAccount(Account account);

    /**
     * 根据id删除一个Account
     * @param id 要删除的id
     */
    public void delAccountById(int id);

    /**
     * 修改Account
     * @param account 要修改的Account
     */
    public void updateAccount(Account account);

    /**
     * 根据id获取单个Account对象
     * @param id 要查询的id
     * @return 年级
     */
    public Account getAccountById(int id);

    /**
     * 根据条件获取Account列表
     * @param queryAccountCondition 查询条件
     * @return 查询结果
     */
    public List<Account> getAccountList(QueryAccountCondition queryAccountCondition);
}