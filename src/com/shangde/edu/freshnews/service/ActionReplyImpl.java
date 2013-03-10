package com.shangde.edu.freshnews.service;

import java.util.List;
import com.shangde.edu.freshnews.domain.ActionReply;
import com.shangde.edu.freshnews.condition.QueryActionReplyCondition;
import com.shangde.common.service.BaseService;

/**
 * ActionReply�������ʵ����?
 * User: guoqiang.liu
 * Date: 2012-06-14
 */
@SuppressWarnings("unchecked")
public class ActionReplyImpl extends BaseService implements IActionReply{
    public void  addActionReply(ActionReply actionReply) {
    }

    public void delActionReplyById(){
    }

    public void updateActionReply(ActionReply actionReply) {
        simpleDao.updateEntity("ActionReply_NS.updateActionReply",actionReply);
    }

   

    public List<ActionReply> getActionReplyList(QueryActionReplyCondition queryActionReplyCondition) {
        return simpleDao.getForList("ActionReply_NS.getActionReplyList",queryActionReplyCondition);
    }

	public ActionReply getActionReplyById() {
	
		return null;
	}
}
