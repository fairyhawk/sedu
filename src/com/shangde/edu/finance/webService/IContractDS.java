package com.shangde.edu.finance.webService;
/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:为ds商品订单提供接口
 *
 * @author		Yangning
 * @date		2012-3-5
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public interface IContractDS {
	
	/**
	 * 
	 * @param contractId 订单编号
	 * @param cusId		 用户ID
	 * @param cutSumMoney 支付金额
	 * @param sumMoney	原始金额
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-5
	 */
	public int genDsContract(String contractId,int cusId,float cutSumMoney,float sumMoney,String sign,Integer payment,String contractCDkey);
	
	
	/**
	 * 
	 * @param sellId 售卖方式ID
	 * @param totalMoney 原始价格
	 * @param cutSumMoney 真实价格
	 * @param cusId	用户ID
	 * @param ctId 订单Id
	 * @param crSubjectId 项目id
	 * @param shopPayType 支付类型
	 * @param crInfo	售卖方式说明
	 * @param contractId 订单编号
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-5
	 */
	public int genDsCashRecord(int status,int sellId,float totalMoney,float cutSumMoney,int cusId,int ctId,int crSubjectId,int shopPayType,String crInfo,String contractId,String sign);
	
	/**
	 * 得到访问令牌
	 * @param pid
	 * @param pkey
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-6
	 
	public String getAccessToken(String pid,String pkey);
	*/
	/**
	 * 刷新令牌
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-6
	 
	public String flushToken(String pid,String pkey);
	*/
}
