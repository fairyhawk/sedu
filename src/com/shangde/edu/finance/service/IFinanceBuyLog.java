package com.shangde.edu.finance.service;

import java.io.Serializable;

/**
 * 
 * @ClassName: IFinanceBuyLog
 * @Description: TODO(处理前台用户下订单过程步骤记录的service接口)
 * @author shixiaofeng@sunland.org.cn
 * @date 2012-6-12 上午11:02:05
 * 
 */
public interface IFinanceBuyLog extends Serializable {

	/**
	 * 
	 * @Title: addFinanceBuyLog
	 * @Description: TODO(根据projectid,step,time记录用户下订单中各个步骤的情况)
	 * @param
	 * @param projectId
	 *            项目id
	 * @param
	 * @param status
	 *            用户单击步骤
	 * @param
	 * @return 设定文件
	 * @return int 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public int addFinanceBuyLog(int projectId, int status);
}
