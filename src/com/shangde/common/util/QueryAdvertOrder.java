package com.shangde.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.IContract;

/**
 * 查询所有来源于亿起发的订单数据
 * 
 * @author 刘世豪
 * 
 */
public class QueryAdvertOrder extends CommonAction{
	/**
	 * @param src
	 *            广告主为亿起发指定的标识
	 * @param cid
	 *            广告主在亿起发推广的标识
	 * @param orderTime
	 *            订单日期
	 */
	private String src ;
	private Integer cid ;
	private Date orderTime ;
	private IContract contractService;
	private QueryContractCondition queryContractCondition ;
	/**
	 * 以流的方式输出订单列表
	 * @throws Exception
	 */
	public String queryOrder() throws Exception{
		if(null!=src||!"".equals(src)||null!=cid||null!=orderTime){
			this.getQueryContractCondition().setSrc(src) ;
			this.getQueryContractCondition().setCid(cid) ;
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(orderTime);
			this.getQueryContractCondition().setCreateTime(dateTime) ;
			List<Contract> contList = contractService.queryOrderByList(queryContractCondition) ;
			HttpServletRequest request = ServletActionContext.getRequest() ;
			HttpServletResponse response = ServletActionContext.getResponse() ;
			if(null==contList||contList.size()==0){
				response.setContentType("text/html;charset=utf-8") ;
				response.getWriter().print("Did not meet the conditions of the data") ;
			}else{
				String fileName = src+"-highso"+dateTime+".txt" ;
				//response.reset();
				//response.setContentType("text/plain;charset=UTF-8");
				response.setHeader("Pragma","No-cache");   
		        response.setHeader("Cache-Control","no-cache");
		        response.setDateHeader("Expires", 0);
				response.setContentType("application/x-download;charset=UTF-8");
				//response.setHeader("Content-Disposition",fileName);
				response.setHeader("Content-Disposition", "attachment; filename="+fileName);
				for(Contract ct : contList){
					response.getOutputStream().write((ct.getWi()+"||").getBytes());
					response.getOutputStream().write((dateFm2.format(ct.getCreateTime())+"||").getBytes());
					response.getOutputStream().write((ct.getContractId()+"||").getBytes());
					response.getOutputStream().write(("0"+"||").getBytes());//佣金类型0课程 1图书
					response.getOutputStream().write(("1"+"||").getBytes());//商品数量 固定1
					response.getOutputStream().write((ct.getContractId()+"||").getBytes());//商品编码
					response.getOutputStream().write((ct.getContractCutSumMoney().toString()+"\n").getBytes());
				}
				response.getOutputStream().flush();
				response.getOutputStream().close(); 
			}
		}
		return null ;
	}
	/**
	 * 订单列表显示在jsp中
	 * @return
	 * @throws IOException 
	 */
	public String queryOrderPage() throws IOException{
		if(null!=src||!"".equals(src)||null!=cid||null!=orderTime){
			this.getQueryContractCondition().setSrc(src) ;
			this.getQueryContractCondition().setCid(cid) ;
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(orderTime);
			this.getQueryContractCondition().setCreateTime(dateTime) ;
			List<Contract> contList = contractService.queryOrderByList(queryContractCondition) ;
			//ActionContext.getContext().put("orderList", contList) ;
			HttpServletResponse response = ServletActionContext.getResponse() ;
			response.setCharacterEncoding("utf-8") ;
			response.setContentType("text/plain;charset=utf-8");
			if(null!=contList&&contList.size()!=0){
				for(Contract ct : contList){
					response.getWriter().write(ct.getWi()+"||"+dateFm2.format(ct.getCreateTime())+"||"+ct.getContractId()+"||"+ct.getContractSumMoney()
					        +"||"+"0"+"||"+"1"+ct.getContractId()+"\n") ;
				}
			}
		}
		return null ;
	}
	
	public IContract getContractService() {
		return contractService;
	}
	
	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public QueryContractCondition getQueryContractCondition() {
		if (queryContractCondition == null) {
			queryContractCondition = new QueryContractCondition();
		}

		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
