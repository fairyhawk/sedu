package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.condition.QueryChildContractCondition;

/**
 * ChildContract����ӿ�
 * User: guoqiang.liu
 * Date: 2012-02-23
 */
public interface IChildContract {
   
    public int addChildContract(ChildContract childContract);

    public void delChildContractById();

  
    public void updateChildContract(ChildContract childContract);

  
    public ChildContract getChildContractById(int id);
    
    public ChildContract getChildContract(QueryChildContractCondition queryChildContractCondition);

    public List<ChildContract> getChildContractList(QueryChildContractCondition queryChildContractCondition);
    
    public ChildContract getChildContractByNo(String subContractNo);
    
    public int updateChildContractPayOK(ChildContract childContract);
    
    public   QueryChildContractCondition getPayChildContract(QueryChildContractCondition queryChildContractCondition);
    
}