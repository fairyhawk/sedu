package com.shangde.edu.cus.web;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.domain.CusProtocalDetail;
import com.shangde.edu.cus.service.ICusProtocalDetail;


/**
 * 功能:用户协议
 * @author Yangning
 *
 */
public class CusProtocalAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(CusProtocalAction.class);

	private static final long serialVersionUID = 1L;
	
	private CusProtocalDetail detail;
	
	private ICusProtocalDetail cusProtocalService;
	
	private Integer cashId;
	/**
	 * 写入用户保过协议,并返回json协议Id,前台购物车使用
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-26
	 */
	public String saveCusDet(){
		try{
			if(detail != null && getLoginUserId() > 0 ){
				//检验参数
				if(checkParam(detail)){
					detail.setCusId(getLoginUserId());
					int id = cusProtocalService.addCusProtocalDetail(detail);
					this.setResult(new Result(true,"success",null , id));
				}else{
					this.setResult(new Result(true,"invalid",null , null));
				}
			}
		}catch(Exception e){
			logger.error("CusProtocalAction.saveProtocal", e);
			this.setResult(new Result(false,"error",null , null));
		}
		return "json";
	}
	
	public String getProtocalByCash(){
		try{
			detail = cusProtocalService.getCusProtocalDetailSellId(cashId);
			if(detail != null){
				this.setResult(new Result(true,"success",null , detail));
			}else{
				this.setResult(new Result(true,"failed",null , null));
			}
		}catch(Exception e){
			logger.error("CusProtocalAction.getProtocalByCash",e);
			this.setResult(new Result(false,"error",null , null));
		} 
		return "json";
	}
	
	
	
	
	private boolean checkParam(CusProtocalDetail detail){
		if(detail.getAddress() == null || detail.getAddress().trim().length() <= 0){
			return false;
		}
		if(detail.getMobile() == null || detail.getMobile().trim().length() <= 0){
			return false;
		}
		if(detail.getCusName() == null || detail.getCusName().length() <= 0){
			return false;
		}
		if(detail.getIdentityCard() == null || detail.getIdentityCard().length() <= 0){
			return false;
		}
		return true;
	}
	public CusProtocalDetail getDetail() {
		return detail;
	}

	public void setDetail(CusProtocalDetail detail) {
		this.detail = detail;
	}

	public ICusProtocalDetail getCusProtocalService() {
		return cusProtocalService;
	}

	public void setCusProtocalService(ICusProtocalDetail cusProtocalService) {
		this.cusProtocalService = cusProtocalService;
	}

	public Integer getCashId() {
		return cashId;
	}

	public void setCashId(Integer cashId) {
		this.cashId = cashId;
	}
	
}
