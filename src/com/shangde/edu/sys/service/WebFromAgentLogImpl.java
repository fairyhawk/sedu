package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.domain.WebFromAgentLog;

public class WebFromAgentLogImpl extends BaseService implements IWebFromAgentLog {

	public void saveWebFromAgentLog(WebFromAgentLog webFromAgentLog) {
		//查询今天内是否存在
		WebFromAgentLog agentLog = 
				simpleDao.getEntity("WebFromAgentLog_NS.getWebFromAgentLog", webFromAgentLog);
		if (agentLog==null){
			simpleDao.createEntity("WebFromAgentLog_NS.saveWebFromAgentLog",webFromAgentLog);
		}else{
			//将今天的数据+1
			simpleDao.update("WebFromAgentLog_NS.updateWebFromAgentLog", agentLog);
		}
	}
}
