package com.shangde.edu.book.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;
import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.service.IBuyInfo;

public class BookAction extends CommonAction {
	IBuyInfo  buyInfoService=null;
	 BuyInfo buyInfo=null;
	 String success="0";
	 QueryBuyInfoCondition  buyInfocondition=new QueryBuyInfoCondition();
	public QueryBuyInfoCondition getBuyInfocondition() {
		return buyInfocondition;
	}

	public void setBuyInfocondition(QueryBuyInfoCondition buyInfocondition) {
		this.buyInfocondition = buyInfocondition;
	}

	public BuyInfo getBuyInfo() {
		return buyInfo;
	}

	public void setBuyInfo(BuyInfo buyInfo) {
		this.buyInfo = buyInfo;
	}

	

	public IBuyInfo getBuyInfoService() {
		return buyInfoService;
	}

	public void setBuyInfoService(IBuyInfo buyInfoService) {
		this.buyInfoService = buyInfoService;
	}

	public void addBuyInfo() throws IOException{
		try{
		buyInfo.setCreatetime(new Date());
		buyInfoService.addBook(buyInfo);	
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.getWriter().print("success");
		}catch(Exception ex){
			ex.printStackTrace();
			ServletActionContext.getResponse().getWriter().print("error");
		}
		
	}
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String GoBuyInfo(){
		buyInfo=new BuyInfo();
		buyInfo.setCusId(666);
		return "success";
	}
}
