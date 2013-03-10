package com.shangde.edu.finance.webService;
 
public interface OrderService {
	/**
	 * 将订单状态修改为支付成功！,嗨学网，货到货款激活时使用
	 */
	public int updateOrderSend(String contractid,int cusId);
}
