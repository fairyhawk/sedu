package com.shangde.edu.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.sys.domain.WebFromAgentLog;
import com.shangde.edu.sys.service.IWebFromAgentLog;
import org.apache.log4j.Logger;

public class WebFromAgentLogAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(WebFromAgentLogAction.class);

	private WebFromAgentLog log;

	private IWebFromAgentLog webFromAgentLogService;

	public String saveWebFromAgentLog() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			log.setStartTime(dateFormat.format(date)+" 00:00:00");
			log.setEndTime(dateFormat.format(date)+" 23:59:59");
			webFromAgentLogService.saveWebFromAgentLog(log);
			setResult(new Result<String>(true, "1", null, null));
		} catch (Exception e) {
			logger.error("WebFromAgentLogAction.saveWebFromAgentLog", e);
		}
		return "json";
	}

	public WebFromAgentLog getLog() {
		return log;
	}

	public void setLog(WebFromAgentLog log) {
		this.log = log;
	}

	public IWebFromAgentLog getWebFromAgentLogService() {
		return webFromAgentLogService;
	}

	public void setWebFromAgentLogService(
			IWebFromAgentLog webFromAgentLogService) {
		this.webFromAgentLogService = webFromAgentLogService;
	}

}
