package com.shangde.edu.cus.web;

import java.util.List;

import org.apache.log4j.Logger;


import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.condition.QueryAreaCondition;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.service.IArea;

/**
 * 
 * @author zhouZhiQiang
 * @version 1.0
 */
public class AreaWebAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(AreaWebAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 地区服务类
	 */
	private IArea areaService;
	
	/**
	 * 地区列表
	 */
	private List<Area> areaList;
	
	/**
	 * 查询地区条件
	 */
	private QueryAreaCondition queryAreaCondition;

	/**
	 * 根据父id获取子地区列表
	 * @return
	 */
	public String getAreasByParentId(){
		try{
			if(getQueryAreaCondition().getParentId() == 0) {
				setResult(new Result<List<Area>>(false, "success", "", null));
				return "json";
			}
			areaList = areaService.getAreaList(getQueryAreaCondition());
			setResult(new Result<List<Area>>(false, "success", "", areaList));
		}catch(Exception e){
			logger.error("AreaWebAction.getAreasByParentId", e);
		}
		return "json";
	}

	public QueryAreaCondition getQueryAreaCondition() {
		if(queryAreaCondition == null) {
			queryAreaCondition = new QueryAreaCondition();
		}
		return queryAreaCondition;
	}

	public void setQueryAreaCondition(QueryAreaCondition queryAreaCondition) {
		this.queryAreaCondition = queryAreaCondition;
	}

	public IArea getAreaService() {
		return areaService;
	}

	public void setAreaService(IArea areaService) {
		this.areaService = areaService;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
}
