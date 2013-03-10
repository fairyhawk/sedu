package com.shangde.edu.iphone.web;

import java.util.List;

import org.testng.log4testng.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;
import com.shangde.edu.iphone.service.ISellWayIPhone;

public class IPhoneSellWayWebAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(IPhoneSellWayWebAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1187622558201602281L;

	/**
	 * 订单服务
	 */
	private IContract contractService;

	private ISellWayIPhone sellWayIPhoneService;

	private QuerySellWayIPhoneCondition queryCondition;

	public QuerySellWayIPhoneCondition getQueryCondition() {
		if(this.queryCondition == null)
			this.queryCondition = new QuerySellWayIPhoneCondition();
		return queryCondition;
	}

	public void setQueryCondition(QuerySellWayIPhoneCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	/**
	 * 学员
	 */
	private Customer customer;

	/**
	 * 给张栋提供iphone端数据 得到当前用户购买的售卖方式集合 获取当前用户所包含的课程
	 * 
	 * @return
	 */
	public String getSellWayByCusId() {
		try{
			PageResult pageResult = null;
			String returnMessage = "";
			if (getLoginUserId() != 0) {
				this.getCustomer().setCusId(getLoginUserId());
			}
			if (this.customer != null && this.customer.getCusId() != 0) {
	
				this.getQueryCondition()
						.setUserId(customer.getCusId()); // 添加查询条件，当前用户登陆ID
	//			 this.getQueryCondition().setPageSize(10); //设置每页显示数量
				pageResult = this.sellWayIPhoneService
						.getContractSellWayByCusId(this
								.getQueryCondition()); // 查询当前用户所购买的售卖方式
				List<IphoneModel> sellList = pageResult.getPageResult();
				if (sellList != null && !sellList.isEmpty()) {
					for (IphoneModel iphoneModel : sellList) {
						iphoneModel
								.setTeachers(getCourseTeacherBySellId(iphoneModel
										.getVideoId()));
					}
				}
				pageResult.setPageResult(sellList);
				setPage(pageResult);
				setPageUrlParms();
				if (getPage() != null) {
					getPage().setPageSize(this.getQueryCondition().getPageSize());
				}
				returnMessage = "success";
			} else {
				returnMessage = "fail";
			}
			setResult(new Result<PageResult>(false, returnMessage, null, pageResult));
		}catch(Exception e){
			logger.error("IPhoneSellWayWebAction.getSellWayByCusId", e);
		}
		return "json";
	}

	/**
	 * 得到当前售卖方式下的课程老师！
	 * 
	 * @param sellWayId
	 *            售卖方式ID
	 * @return
	 */
	private String getCourseTeacherBySellId(int sellWayId) {
		StringBuilder sb = new StringBuilder();
		List<Teacher> teacherList = this.sellWayIPhoneService
				.getTeacherBySellWayId(sellWayId); // 根据售卖方式ID 得到售卖方式下所有课程的老师
		if (teacherList.size() != 0) { // 遍历老师 做处理
			for (int i = 0; i < teacherList.size(); i++) {
				if (teacherList.get(i) != null) {
					sb.append(teacherList.get(i).getName());
					if (i != teacherList.size() - 1) {
						sb.append("、");
					}
				}
			}
		}
		return sb.toString();
	}
	
	private int pageId;
	
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	/**
	 * 得到当前专业下所有售卖方式
	 * @return
	 */
	public String getSellWayBySubjectId(){
		try{
			PageResult pageResult=null;
			String returnMessage="";
			if(pageId != 0){
	//			this.getQueryCondition().setPageSize(10);
				this.getQueryCondition().setSubjectId(pageId);
				pageResult = this.sellWayIPhoneService.getSellWayBySubjectId(this.getQueryCondition());
				List<IphoneModel> iphoneList = pageResult.getPageResult();
				for (IphoneModel iphoneModel :iphoneList) {
					if(iphoneModel!=null){
						iphoneModel.setTeachers(getCourseTeacherBySellId(iphoneModel.getVideoId()));
						iphoneModel.setLeaf(1);
					}
				}
				returnMessage = "success";
			}else {
				returnMessage = "error";
			}
			setPage(pageResult);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(this.getQueryCondition().getPageSize());
			}
			setResult(new Result<PageResult>(false, returnMessage, null, pageResult));
		}catch(Exception e){
			logger.error("IPhoneSellWayWebAction.getSellWayBySubjectId", e);
		}
		return "json";
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public Customer getCustomer() {
		if (this.customer == null) {
			this.customer = new Customer();
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ISellWayIPhone getSellWayIPhoneService() {
		return sellWayIPhoneService;
	}

	public void setSellWayIPhoneService(ISellWayIPhone sellWayIPhoneService) {
		this.sellWayIPhoneService = sellWayIPhoneService;
	}


}
