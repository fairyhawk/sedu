package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Account;
import com.shangde.edu.finance.condition.QueryAccountCondition;
import com.shangde.common.service.BaseService;

/**
 * Accountï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Êµï¿½ï¿½ï¿½ï¿?
 * User: guoqiang.liu
 * Date: 2010-08-13
 */
@SuppressWarnings("unchecked")
public class AccountImpl extends BaseService implements IAccount{
    public java.lang.Integer addAccount(Account account) {
return simpleDao.createEntity("Account_NS.createAccount",account);
    }

    public void delAccountById(int id){
        simpleDao.deleteEntity("Account_NS.deleteAccountById",id);
    }

    public void updateAccount(Account account) {
        simpleDao.updateEntity("Account_NS.updateAccount",account);
    }

    public Account getAccountById(int id) {
        return simpleDao.getEntity("Account_NS.getAccountById",id);
    }

    public List<Account> getAccountList(QueryAccountCondition queryAccountCondition) {
        return simpleDao.getForList("Account_NS.getAccountList",queryAccountCondition);
    }
}
