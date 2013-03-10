package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.condition.QueryChildContractCondition;
import com.shangde.common.service.BaseService;

/**
 * ChildContract�������ʵ����?
 * User: guoqiang.liu
 * Date: 2012-02-23
 */
@SuppressWarnings("unchecked")
public class ChildContractImpl extends BaseService implements IChildContract{
	
    public int  addChildContract(ChildContract childContract) {
    	return simpleDao.createEntity("ChildContract_NS.createChildContract",childContract);
    }

    public void delChildContractById(){
    }

    public void updateChildContract(ChildContract childContract) {
        simpleDao.updateEntity("ChildContract_NS.updateChildContract",childContract);
    }

    public ChildContract getChildContractById(int id) {
    	return simpleDao.getEntity("ChildContract_NS.getChildContractById", id);
    }
    public ChildContract getChildContract(QueryChildContractCondition queryChildContractCondition){
    	return simpleDao.getEntity("ChildContract_NS.getChildContract", queryChildContractCondition);
    }
    public List<ChildContract> getChildContractList(QueryChildContractCondition queryChildContractCondition) {
        return simpleDao.getForList("ChildContract_NS.getChildContractList",queryChildContractCondition);
    }

	public ChildContract getChildContractByNo(String subContractNo) {
		return simpleDao.getEntity("ChildContract_NS.getChildContractByNo", subContractNo);
	}
	
    public int updateChildContractPayOK(ChildContract childContract) {
        return  simpleDao.update("ChildContract_NS.updateChildContractPayOK",childContract);
    }
    public   QueryChildContractCondition getPayChildContract(QueryChildContractCondition queryChildContractCondition){
        return simpleDao.getEntity("ChildContract_NS.getPayChildContract", queryChildContractCondition);
    }
	
}
