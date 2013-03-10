package com.shangde.edu.finance.web;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.finance.condition.FinanceBuyLogCondition;
import com.shangde.edu.finance.service.IFinanceBuyLog;

/**
 * 
 * @ClassName: FinanceBuyLogAction
 * @Description: TODO(统计用户各个操作的访问量，放到缓存中)
 * @author shixiaofeng@sunland.org.cn
 * @date 2012-6-12 上午10:32:47
 * 
 */
public class FinanceBuyLogAction extends CommonAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(FinanceBuyLogAction.class);
	/**
	 * 前台用户下订单的步骤条件实体
	 */
	private FinanceBuyLogCondition financeConditon;
	/**
	 * 前台用户下单步骤统计的接口
	 */
	private IFinanceBuyLog finaceLogService;

	/**
	 * 
	 * @Title: getCountByProjFromFront
	 * @Description: TODO(获取前台用户点击按钮的事件，将信息放到缓存中，并通过json返回)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String queryCountByProjFromFront() {
		try {

			logger.debug("=====进入FinanceBuyLogAction.getCountByProjFromFront方法==");
			int count = finaceLogService.addFinanceBuyLog(financeConditon
					.getProjectId(), financeConditon.getStatus());
			logger.debug("=====进入FinanceBuyLogAction.getCountByProjFromFront,查询出的访问量是=="
							+ count);
			this.setResult(new Result(true, "success", null, count));
		} catch (Exception ex) {
			logger.error("FinanceBuyLogAction.getCountByProjFromFront", ex);
			return "json";
		}
		return "json";
	}

	public FinanceBuyLogCondition getFinanceConditon() {
		if (financeConditon == null) {
			financeConditon = new FinanceBuyLogCondition();
		}
		return financeConditon;
	}

	public void setFinanceConditon(FinanceBuyLogCondition financeConditon) {
		this.financeConditon = financeConditon;
	}

	public IFinanceBuyLog getFinaceLogService() {
		return finaceLogService;
	}

	public void setFinaceLogService(IFinanceBuyLog finaceLogService) {
		this.finaceLogService = finaceLogService;
	}

}