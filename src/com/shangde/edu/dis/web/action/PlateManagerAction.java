package com.shangde.edu.dis.web.action;


import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.dis.condition.QueryPlateCondition;
import com.shangde.edu.dis.service.IPlateManager;

/**
 * 板块管理，目前暂停使用
 * 
 * @author Libg
 * 
 */
public class PlateManagerAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(PlateManagerAction.class);
	/**
	 * 板块接口服务
	 */
	private IPlateManager plateService;

	/**
	 * 板块Query结果
	 */
	private QueryPlateCondition queryPlate;

	public QueryPlateCondition getQueryPlate() {
		if (queryPlate == null) {
			queryPlate = new QueryPlateCondition();
		}
		return queryPlate;
	}

	public void setQueryPlate(QueryPlateCondition queryPlate) {
		this.queryPlate = queryPlate;
	}

	public IPlateManager getPlateService() {
		return plateService;
	}

	public void setPlateService(IPlateManager plateService) {
		this.plateService = plateService;
	}

	public String searchPlate() {
		try{
			this.getQueryPlate().setPageSize(20);
			setPage(plateService.getPlate(getQueryPlate()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		}catch(Exception e){
			logger.error("PlateManagerAction.searchPlate", e);
			return ERROR;
		}
		return "success";
	}
}
