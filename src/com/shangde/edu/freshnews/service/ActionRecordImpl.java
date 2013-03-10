package com.shangde.edu.freshnews.service;

import java.util.List;

import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.condition.QueryActionRecordCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * ActionRecord�������ʵ����?
 * User: guoqiang.liu
 * Date: 2012-06-14
 */
@SuppressWarnings("unchecked")
public class ActionRecordImpl extends BaseService implements IActionRecord{
	private ICustomer customerService;//客户service
    public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public void  addActionRecord(ActionRecord actionRecord) {
    }

    public void delActionRecordById(){
    }

    public void updateActionRecord(ActionRecord actionRecord) {
        simpleDao.updateEntity("ActionRecord_NS.updateActionRecord",actionRecord);
    }

   

    public List<ActionRecord> getActionRecordList(QueryActionRecordCondition queryActionRecordCondition) {
        return simpleDao.getForList("ActionRecord_NS.getActionRecordList",queryActionRecordCondition);
    }

	@Override
	public ActionRecord getActionRecordById() {
		return null;
	}

	/**
     * 根据highso问答添加新鲜事
     * @param problem
     * @throws Exception
     */
	public void addActionRecordByProblem(Problem problem) {
		simpleDao.createEntity("ActionRecord_NS.createActionRecord", fillActionRecordByProblem(problem));
	}
	/**
	 * 通过problem填充新鲜事
	 * @param problem
	 * @return
	 */
	private ActionRecord fillActionRecordByProblem(Problem problem){
		ActionRecord actionRecord=new ActionRecord();
		Customer customer=customerService.getCustomerById(problem.getCusId());
		actionRecord.setRelateId(problem.getPblId());
		actionRecord.setContent(problem.getPblContent());
		actionRecord.setCreateTime(problem.getCreateTime());
		actionRecord.setCurrentType(1);
		actionRecord.setCusEmail(customer.getEmail());
		actionRecord.setCusId(problem.getCusId());
		actionRecord.setCusName(customer.getCusName());
		actionRecord.setIsAnswer(0);
		actionRecord.setSubjectId(problem.getSubjectId());
		actionRecord.setUpdateTime(problem.getCreateTime());
		return actionRecord;
	}
	
	/**
	 * 获取新鲜事
	 */
	public PageResult getActionRecords(QueryActionRecordCondition queryActionRecordCondition){
		return simpleDao.getPageResult("ActionRecord_NS.getActionRecord","ActionRecord_NS.getActionRecordCount",queryActionRecordCondition);
	}
}
