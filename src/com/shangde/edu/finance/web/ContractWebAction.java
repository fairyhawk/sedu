package com.shangde.edu.finance.web;


import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.core.task.TaskExecutor;


import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.GetHttpMessage;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.CusCashProtocal;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.ICusProtocalCash;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.finance.task.CrmSendMessageTask;
import com.shangde.edu.finance.task.ProtocalContractTask;
import com.shangde.edu.finance.webService.OrderService;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;


/**
 * 订单管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class ContractWebAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(ContractWebAction.class);
	private static final long serialVersionUID = 1L;
	/**
	 * 声名订单的PO对象
	 */
	private Contract contract=new Contract();
	/**
	 * 查询条件
	 */
	private String searchKey;

	/**
	 * 知识点查询条件
	 */
	private QueryContractCondition queryContractCondition;
	
	/**
	 * 售卖方式查询条件
	 * 
	 */
	private QuerySellWayCondition querySellWayCondition;
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	private ICashRecord cashRecordService;
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List <Customer>customerList=new ArrayList<Customer>(); 
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	private List<Course> courseList;
	
	/**
	 * 课程分类服务Service
	 */
	private ICoursesort coursesortService; 
	
	private Customer customer;
	
	/**
	 * 用户id
	 * 
	 */
	private int userid;
	/**
	 * 课程id
	 */
	private int cousid;
	/**
	 * 表单编号
	 */
	private String oderid;
	
	private List<CourseSortListDTO> courseSortListDTOList;
	/**
	 * 订单条数
	 */
	private int sumCount;
	/**
	 * 切换参数
	 */
	private int location;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 亿起发返回订单数据服务
	 */
	private GetHttpMessage getMsg ;
	
	/**
	 * 短信服务
	 */
	private IsmsService smsService;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;

	private Address address;
	
	private IAddress addressService;
	
	private boolean changeAddress = false;
	private int type;//初始化值
	
	//优惠券id
	private String  couponId;
	
	// 优惠编码
	private String couponCode;
	
	/**
	 * 应付价格
	 */
	private String paymoney;
	
	/**
	 * 货到付款激活码
	 */
	private String contractCDkey;
	
	private IUserMsg userMsgService;
	
	private IMessage messageService;
	
	private int addressId;
	private QuerySellCouCondition querySellCouCondition;
	private ISellCou sellCouService;
	private ISellWay sellWayService ;
	/**--发送手机订单号--**/
	private String contractno;
	/**--发送手机支付金额--**/
	private String paytotal;
	/**--用户选择银行信息--**/
	private Integer comType;
	
	private String mobileNO;
	
	/**保过协议售卖方式Id**/
	private String sellids;
	
	/**用户填写保过协议ID**/
	private int cusDetialId;
	
	/**用户保过协议流水关联**/
	private ICusProtocalCash  cusProtocalCashService;
	
	/**线程池对象,用户线程操作,预防服务器压力过大**/
	private TaskExecutor taskExecutor;
	
	/**流水ID与授卖方式关联容器,为方便保过协议写入**/
	private List<Map> cashSellMap = new ArrayList<Map>();
	
	private String message;
	
	private int paySum;//学员付款的订单
	
	private int cancelSum;//学员取消的数量 
	/**
	 * 根据用户id和包id查询订单
	 */
	@SuppressWarnings("unused")
	public String GetUseroder()
	{
		
		try{
			CashRecord cr=new CashRecord();
			Contract ct=new Contract();
			cr.setCusId(userid);
			cr.setPackId(cousid);
			String oderid1 = cashRecordService.getUseroderbyid(cr);//查询订单号
			ct.setCusId(userid);
			ct.setContractId(oderid1);
			
			
			Contract ctorder=new Contract();
			ctorder=contractService.getUserOderByid(ct);//查询订单整条记录
			
			String status = ctorder.getContractId()+","+ctorder.getContractCutSumMoney();
			setResult(new Result<String>(false,status, null, status));
			return "getuseroder";
		} catch (Exception e) {
		logger.error("前台/购物车/订单查询/Exception:"+e.getMessage());
		return ERROR;
		
		}
		
		
	}
	/**
	 * 查询表单是否成功
	 * 
	 */
	@SuppressWarnings("unused")
	public String getIsoder()
	{
		try{
			int userId = getLoginUserId() ;
			Contract ct=new Contract();
			ct.setCusId(userid);
			ct.setContractId(oderid);
			String status =new Integer(contractService.getIsoder(ct)).toString();
			if("1".equals(status)){
				if(userId!=0){
					getQueryContractCondition().setUserId(userId) ;
				}
				ct = contractService.getContractByCus_Id(userId) ;
				//String[] GA_result={ct.getContractId(),ct.getContractCutSumMoney().toString()} ;
				//List<CashRecord> cash_list = cashRecordService.getAllCash(ct.getContractId()) ;
				List<Integer> cashPackId = cashRecordService.getCashPackId(ct.getContractId()) ;
				StringBuffer msg = new StringBuffer();
				msg.append(ct.getContractId()+","+ct.getContractCutSumMoney()+";") ;
				for(Integer i : cashPackId){
					int sku = sellWayService.getSellWayById(i).getSellId() ;
					String info = sellWayService.getSellWayById(i).getSellName() ;
					float price = sellWayService.getSellWayById(i).getSellPrice() ;
					msg.append(sku+","+info+","+price+"#");
				}
				setResult(new Result(false, msg.toString(), null,"")) ;
			}else{
				setResult(new Result(false,"0", null,0)) ;
			}
			
			return "getoder";
		} catch (Exception e) {
		logger.error("前台/购物车/订单查询/Exception:"+e.getMessage());
		return ERROR;
		}
		
	}
	
	/**
	 * 根据课程id和用户id获取订单状态
	 * 
	 */
	public String getStatus()
	{
		try {
			CashRecord cr =new CashRecord();
			cr.setCourseId(cousid);
			cr.setCusId(userid);
			String status = contractService.getStatus(cr);
			setResult(new Result<String>(false, status, null, status));
			return "getstatus";
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("前台/购物车/getStatus/:"+e.getMessage());
			return ERROR;
		}
	}
	/**
	 * 对未支付的、非赠送的订单执行取消操作  货到付款的订单 3为取消，其他支付方式的订单 4为取消
	 * @return
	 */
	private void delContractAndCashRecord()
	{
		try{
			Contract ctTemp=contractService.getContractById(contract.getId());
			if(ctTemp.getPayType()!=0&&ctTemp.getStatus()!=1&&ctTemp.getCusId()==getLoginUserId())
			{	
				/*--取消订单设置订单状态为4---*/
				ctTemp.setStatus(4);
				ctTemp.setCancelTime(new Date());
				contractService.updateContract(ctTemp);			
				Map<String,String> mcash=new HashMap<String,String>();
				String contractId=ctTemp.getContractId();
				mcash.put("status", "2");
				mcash.put("contractid", contractId);
				cashRecordService.updateCashRecordStatus(mcash);
				//向crm发送消息
				taskExecutor.execute(new CrmSendMessageTask(ctTemp));
			}
		}catch (Exception e) {
			logger.error("delContractAndCashRecord:"+e.getMessage());
		}
	}
	private int nopay;
	
	public int getNopay() {
		return nopay;
	}
	public void setNopay(int nopay) {
		this.nopay = nopay;
	}
	
	/**
	 * yanbaixi  
	 * 统计支付方式为块钱、网银、支付宝的未付款数量
	 * 
	 */
	public String showNopayCountContract(){
		
		try{
			int userid = getLoginUserId();
			nopay = contractService.getNopayCountContract(userid);
			setResult(new Result(false,"success",null,nopay));
		}catch(Exception e){
			logger.error("未付款错误",e);
		}
		
		return "json";
	}
	
	
	
	/**
	 * 获得订单列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static final String COOKIE_REMEMBERME_KEY="sedu.cookie.ukey"; 
	
	public String getContractList() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			if(request.getParameter("ty") != null){
				String ty = request.getParameter("ty");
				if(ty.equals("0") || Integer.parseInt(ty.trim()) == 0){
					delContractAndCashRecord();
				}
			}
            String payStatus=request.getParameter("payStatus");	
            if(payStatus!=null&&!payStatus.equals("")){
            if(payStatus.equals("pay")){
            	payStatus="1";
            }else if(payStatus.equals("cancel"))
            {
            	payStatus="4";
            }else{
            	payStatus="0";
            }
            queryContractCondition.setNewStatus(payStatus);
            }
			if (searchKey != null && !"".equals(searchKey.trim())) {
				getQueryContractCondition().setSearchKey(searchKey.trim());
			}
			int userId = getLoginUserId();
			if(userId != 0){
				getQueryContractCondition().setUserId(userId);
			}
			//获得订单列表
			setPage(contractService.getContractAndSelledContractNumList(queryContractCondition));
			setPageUrlParms();
			//订单条数
			sumCount = contractService.getUserContractCount(userId);
			paySum=contractService.getPaySumByCusId(userId);
			cancelSum=contractService.getCancelSumByCusId(userId);
			//logger.error("我的订单数量  sumCount || paySum |cancelsum"+sumCount+","+paySum+","+cancelSum+"");
			//setCourseSortListDTOList(userId);
		} catch (Exception e) {
			logger.error("前台查看订单异常:",e);
			return ERROR;
		}
		return "listContract";
	}
	public String getContractById(){
		try{
		int userId = getLoginUserId() ;
		if(userId!=0){
			getQueryContractCondition().setUserId(userId) ;
		}
		Contract ct = contractService.getContractById(userId) ;
		String[] GA_result={ct.getContractId(),ct.getContractCutSumMoney().toString()} ;
		setResult(new Result(false, "success", null, GA_result)) ;
		}catch(Exception e){
			logger.error("前台/用户中心/我的订单/getContractById:"+e.getMessage());
			return ERROR;
		}
		return "json" ;
	}
	
	public String getCashByContractId(){
		try{
			int userId = getLoginUserId() ;
			if(userId!=0){
				getQueryContractCondition().setUserId(userId) ;
			}
			Contract ct = contractService.getContractById(userId) ;
			List<CashRecord> cash_list = cashRecordService.getAllCash(ct.getContractId()) ;
			String[] GA_Item = new String[cash_list.size()] ;
			for(int i =0 ;i<cash_list.size() ;i++){
				GA_Item[i] = cash_list.get(i).getCourseId()+","+ cash_list.get(i).getPrice()+","+cash_list.get(i).getCrInfo();
				
			}
			setResult(new Result(false, "success", null, GA_Item)) ;
		}catch(Exception e){
			logger.error("前台/用户中心/我的订单/getCashByContractId:"+e.getMessage());
			return ERROR;
		}
		return "json" ;
	}
	
	
	/**
	 * 货到付款生成订单和流水
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String makeContractAndCashRecord() {
		try {
			String courseOrder = URLDecoder.decode(CookieHandler.getCookieValueByName(servletRequest, "courseOrder"), "utf-8").trim();//存值形式[0]课程id,[1]每一本书的价格,[2]包id
			if(courseOrder != null && !courseOrder.trim().equals("")) {
				
				//如果是临时学员，那么删除他的临时信息，并置状态为注册学员
				Customer tempCus = customerService.getCustomerById(getLoginUserId());
				if(tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP||tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP) {
					customerService.recoverTempCustomer(getLoginUserId());
					tempCus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
					customerService.updateCustomer(tempCus);
				}
				
				//保存订单和流水
				String[] returnInfos = saveContractAndCashRecords(courseOrder);
				
				//清除生成订单所用的cookie中的数据
				clearCookieContractInfo();
				//下单成功发送短信给客户
//				try {
//				    sendSmsConfirmContract();
//                } catch (Exception e) {
//                    logger.error("货到付款订单错误"+e);    
//                }

				//设置返回数据
				setResult(new Result(false, "success", null, returnInfos));
			}
		} catch (Exception e) {
			logger.error("前台/用户中心/我的订单/makeContractAndCashRecord:"+e.getMessage());
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 给用户发送订单确认短信
	 */
	public void sendSmsConfirmContract() {
		try {
			Customer customer = customerService.getCustomerById(getLoginUserId());
			if (customer.getMobile() != null && !"".equals(customer.getMobile().trim())) {
				smsService.sendEx("【嗨学网】您的订单已成功提交，我们会在24小时内和您联系确认并发货，请保持手机开通", customer.getMobile(), "", "", "");
			}
		} catch(Exception e) {
			logger.error("前台/用户中心/我的订单/sendSmsConfirmContract:"+e.getMessage());
		}
	}
	
	/**
	 * 生成订单和流水
	 * @param courseOrder
	 * @param cusId
	 * @return
	 */
	private String[] saveContractAndCashRecords(String courseOrder) {
		Date date = new Date();
		String get_order = String.valueOf(getLoginUserId()) + date.getTime() + "";
		String webFrom = CookieHandler.getCookieValueByName(servletRequest, "webFrom");
		String webAgent = CookieHandler.getCookieValueByName(servletRequest, "webAgent");
		String src = null ;
		String wi = null ;
		String strCid = null ;
		Integer cid = null ;
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "SRC"))){
			src = CookieHandler.getCookieValueByName(servletRequest, "SRC") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "WI"))){
			wi = CookieHandler.getCookieValueByName(servletRequest, "WI") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "CID"))){
			strCid = CookieHandler.getCookieValueByName(servletRequest, "CID") ;
		}
		if(changeAddress) {
			addressId = saveAddress();
		}
		
		// 记录到订单表中
		Contract newcontract = new Contract();
		
		String contractFromUrl = servletRequest.getServerName();
		newcontract.setContractFromUrl(contractFromUrl);
		newcontract.setContractId(get_order);
		newcontract.setCusIdAddress(addressId);
		newcontract.setCusId(getLoginUserId());
		newcontract.setCreateTime(date);
		newcontract.setContractFrom("前台用户");
		newcontract.setPayType(2);// 1支付宝 2货到付款
		newcontract.setStatus(0);// 2情况下，如果　0为未激活　1为激活
		newcontract.setCouponMoney(0);
		newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);// 0代表没有使用 1代表使用
		newcontract.setContractCDkey(getRandomNum(16));//生成16位激活码
		
		newcontract.setFreight(10);//货到付款默认金额为10
        newcontract.setRelief(0);//特殊减免默认为0
		
		int ctId = contractService.addContract(newcontract);
		float totalPrice = saveCashRecords(courseOrder, get_order, ctId, getLoginUserId());
		//谢修改部分开始
		float totalPriceBefore = totalPrice;
		DecimalFormat df = new DecimalFormat("#.00 ");
		Coupon coupon=null;
		float youhuijuan = 0;
		newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals(""))// 优惠卷面值// 谢修改些句
		{
			try{
			 coupon = sellCouService.GetCouponByCode(couponCode);
			Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());
			float zhe = Float.parseFloat(couponType.getPreferentialPrice());
			if (couponType.getCType() == 1) { //如果是打折
				totalPrice = totalPrice * (zhe * 10 / 100);
				youhuijuan = totalPriceBefore-totalPrice;
			}
			if (couponType.getCType() == 2) {//如果是立减
				totalPrice = totalPrice - zhe;
				youhuijuan=zhe;
			}
			if( totalPrice==0)
			{
				totalPrice=totalPriceBefore;
				SimpleDateFormat matter = new SimpleDateFormat(
		                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
				logger.info(matter.format(new Date())+" 优惠券id是: "+couponCode+"  订单id是："+ctId+" 总金额是"+totalPriceBefore);
			}
			totalPrice = Float.parseFloat(df.format(totalPrice));
			youhuijuan = Float.parseFloat(df.format(youhuijuan));
			int couponTypeId = sellCouService
					.GetParentIdBycouponId(String.valueOf(coupon.getId()));// 谢添加
			sellCouService.updateCouponForState(String.valueOf(coupon.getId()),
					couponTypeId);// 谢添加更改优惠券的状态
			newcontract
			.setUseCoupon(Contract.CONTRACT_USE_COUPON_YES);
			newcontract.setCouponId(coupon.getId());
			}catch(Exception e)
			{
				SimpleDateFormat matter = new SimpleDateFormat(
		                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
				logger.error(matter.format(new Date())+" 订单id是："+ctId+" 总金额是"+totalPriceBefore);
			}
		}
		newcontract.setContractCutSumMoney(totalPrice);// 优惠后总金额
		newcontract.setContractSumMoney(totalPriceBefore);// 优惠前总金额
		newcontract.setCouponMoney(youhuijuan);// 优惠额
		//谢修改部分结束
		if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")) {
			newcontract.setWebFrom(webFrom);
		}
		
		if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null")) {
			newcontract.setWebAgent(webAgent);
		}
		
		
		/**
		 * 查询Cookies是否有亿起发的信息，有则添加到订单中
		 */
		if(null!=src&&!"".equals(src)){
			newcontract.setSrc(src);
			if(null!=wi&&!"".equals(wi)){
				newcontract.setWi(wi) ;
			}
			if(null!=strCid&&!"".equals(strCid.trim())){
				cid = Integer.parseInt(strCid) ;
				newcontract.setCid(cid) ;
			}
			//给亿起发返订单数据
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(date);
			String url = "cid="+cid+"&wi="+wi+"&on="+get_order+"&ta=1&pp="+totalPrice+"&sd="+dateTime
			+"||"+"0"+"||"+"1"+newcontract.getContractId()+"&encoding=utf-8" ;
			String flag = this.getGetMsg().getHttpMessageFromGet(url);
			if("0".equals(flag)){
				logger.info("成功发送亿起发订单数据") ;
			}else if("1".equals(flag)){
				logger.info("缺少必要参数") ;
			}else if("2".equals(flag)){
				logger.info("参数格式错误") ;
			}
		}
		
		/**
		 * 为第三方网站进行订单推送
		 * add by caowei
		 * 2011-07-18
		 */
		try{
			String sendData = "";
			int siteCode = 0;
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")){
				if(webFrom.trim().equals("51fanli")){
					siteCode = 1;
				}
			}
			if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && siteCode != 0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sendDate = sdf.format(date);
				HashMap detailMap = (HashMap) this.getContractDetail(courseOrder);
				String pks = (String) detailMap.get("pks");//售卖方式的字符串
				String prices = (String) detailMap.get("prices");//售卖方式价格的字符串
				String pksNum = (String) detailMap.get("pksNum");//售卖方式的个数
				String comm = (String)detailMap.get("comm");//佣金
				if(siteCode == 1){//返利网
					String m_id = "highso";//由返利网提供
					String k = "123456";//由返利网提供
					sendData = "otime=" + sendDate + "&o_cd=" + get_order
							+ "&m_id=" + m_id + "&k=" + k + "&u_id=" + webAgent
							+ "&p_cd=" + pks + "&c_cd="+comm+"&it_cnt=" + pksNum
							+ "&price=" + prices + "&comm="+comm+"&username=abcd";
					logger.info(sendData);
				}
				String res = this.getGetMsg().sendContractToOtherSiteFromGet(siteCode, sendData.trim());
			}
		}catch(Exception e){
			logger.info("推送订单异常！");
		}
		
		
		//对应网盟过来的订单
		String wmFlag="wm";
		if(null  != webAgent && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && webAgent.trim().equals(wmFlag)) {
			newcontract.setWebAgent(webAgent); 
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null") ) {
				newcontract.setWebFrom(webFrom);
			}
		}
		
		
		/**
		 * Yaning  查询是否为中视网盟订单如是则把cookie写入库中
		 * 
		 */
		try{
			if (webFrom != null && !webFrom.trim().equals("")
					&& !webFrom.trim().equals("null")) {
				if(webFrom.equals("zswm")){
					String zswmInfo = CookieHandler.getCookieValueByName(servletRequest,"ZSWMINFO");
					if(zswmInfo != null && zswmInfo.trim().length() > 0 && !zswmInfo.equals("null")){
						//cookie 编码|替换
						String codeZswmInfo = zswmInfo.replaceAll("%7C", "|");
						newcontract.setSrc(codeZswmInfo);
					}
				}
			}
			}catch(Exception e){
				logger.error("makeContract zswm",e);
				newcontract.setSrc("");
			}
		
		contractService.updateContract(newcontract);
		//在优惠券中添加订单 谢添加开始
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals("")) {
		Map map=new HashMap();
		map.put("contarctId", newcontract.getId());
		map.put("couponId", coupon.getId());
		sellCouService.updateCouponForContractId(map);
		sellCouService.updateCouponForPayState(couponId, coupon.getParentId());
		}
		
		
		/**
		 * 
		 * 杨宁 2012/04/25 加入此处，新起一个线程放入线程池内进行写入保过协议内容与同时新crm推送信息 --->保过协议与流水1对1关联,此操作将保过协议文本信息写入cus_cash_protocal_tbl关联表中
		 * 原因为:产品保过协议功能更改后台保过协议可各更改与crm业务需要
		 * 
		 * **/
		try{
			if(cusDetialId > 0 && sellids != null && sellids.trim().length() > 0){
				taskExecutor.execute(new ProtocalContractTask(cashSellMap,sellids,cusDetialId,sellWayService,cusProtocalCashService));
			}
			taskExecutor.execute(new CrmSendMessageTask(newcontract));
		}catch(Exception e){
			logger.error("ContractWebAction.taskExecutor.execute",e);
		}

		
		//谢添加结束
		logger.info("前台/用户中心/我的订单/" + "用户：" + getLoginUserId() + "订单号：" + get_order 
						+ "订单来源:前台用户　下单时间：" + date + "订单状态：等待付款/成功");
		return new String[]{get_order, totalPrice + ""};
	}
	
	/**
	 * 功能:银行付款生成订单流水
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	public String makeOffLineContractAndCashRecord() {
		try {
			logger.info("invoke___111");
			String courseOrder = URLDecoder.decode(CookieHandler.getCookieValueByName(servletRequest, "courseOrder"), "utf-8").trim();//存值形式[0]课程id,[1]每一本书的价格,[2]包id
			if(courseOrder != null && !courseOrder.trim().equals("")) {
				logger.info("invoke___222");
				//如果是临时学员，那么删除他的临时信息，并置状态为注册学员
				Customer tempCus = customerService.getCustomerById(getLoginUserId());
				if(tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP||tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP) {
					customerService.recoverTempCustomer(getLoginUserId());
					tempCus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
					customerService.updateCustomer(tempCus);
					logger.info("invoke___333");
				}
				//保存订单和流水
				String[] returnInfos = saveOffLineContractAndCashRecords(courseOrder);
				logger.info("invoke___444");
				//清除生成订单所用的cookie中的数据
				clearCookieContractInfo();
				//下单成功发送短信给客户
				//sendSmsOffLineContract("【嗨学网】您的订单已成功提交，我们会及时与您联系，请保持手机开通");
				//设置返回数据
				setResult(new Result(false, "success", null, returnInfos));
			}
		} catch (Exception e) {
			logger.error("ContractWebAction.makeOffLineContractAndCashRecord",e);
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 功能:发送订单信息与支付金额至用户手机
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	public String sendSmsOffLineContractInfo(){
		try{
			logger.info("【嗨学网】您的订单号码:"+this.contractno+"订单金额:"+this.paytotal +"mobileNO" + mobileNO);
			smsService.sendEx("【嗨学网】您的订单号码:"+this.contractno+"订单金额:"+this.paytotal,mobileNO,"","","");
			setResult(new Result(false, "success", null, null));
		}catch(Exception e){
			logger.error("ContractWebAction.sendSmsOffLineContractInfo",e);
			setResult(new Result(false, "error", null, null));
		}
		return "json";
	}
	
	/**
	 * 功能:发送公司银行汇款地址至用户至用户手机
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String sendSmsOffLineCompanyBank(){
		try{
			if(mobileNO != null && contractno != null ){
					smsService.sendEx("【嗨学网】亲爱的学员，您的订单编号为："+contractno+"，订单金额："+paymoney+"。您选择的汇款银行信息如下："+message+"。汇款时请务必填写您的用户名及订单号。",mobileNO,"","","");
					//smsService.sendEx("【嗨学网】银行名称:中国招商银行    卡号:1109 0623 0710 802    户名:北京尚仁睿智教育科技有限公司     开户行:招商银行股份有限公司北京首体支行",mobileNO,"","","");
					setResult(new Result(false, "success", null, null));
			}
		}catch(Exception e){
			logger.error("ContractWebAction.sendSmsOffLineContractInfo",e);
			setResult(new Result(false, "error", null, null));
		}
		return "json";
	}
	
	/**
	 * 
	 * 功能:银行汇款方式发送短信
	 * @param mesage  发送信息内容
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	public void sendSmsOffLineContract(String message) {
		try {
			Customer customer = customerService.getCustomerById(getLoginUserId());
			if (customer.getMobile() != null && !"".equals(customer.getMobile().trim())) {
				smsService.sendEx(message, customer.getMobile(), "", "", "");
			}
		} catch(Exception e) {
			logger.error("ContractWebAction.sendSmsOffLineContract",e);
		}
	}
	
	/**
	 * 功能：银行汇款操作生成订单流水
	 * @param courseOrder
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	private String[] saveOffLineContractAndCashRecords(String courseOrder) {
		Date date = new Date();
		String get_order = String.valueOf(getLoginUserId()) + date.getTime() + "";
		String webFrom = CookieHandler.getCookieValueByName(servletRequest, "webFrom");
		String webAgent = CookieHandler.getCookieValueByName(servletRequest, "webAgent");
		String src = null ;
		String wi = null ;
		String strCid = null ;
		Integer cid = null ;
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "SRC"))){
			src = CookieHandler.getCookieValueByName(servletRequest, "SRC") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "WI"))){
			wi = CookieHandler.getCookieValueByName(servletRequest, "WI") ;
		}
		if(!StringUtils.isEmpty(CookieHandler.getCookieValueByName(servletRequest, "CID"))){
			strCid = CookieHandler.getCookieValueByName(servletRequest, "CID") ;
		}
		if(changeAddress) {
			addressId = saveAddress();
		}
		
		// 记录到订单表中
		Contract newcontract = new Contract();
		
		String contractFromUrl = servletRequest.getServerName();
		newcontract.setContractFromUrl(contractFromUrl);
		newcontract.setContractId(get_order);
		newcontract.setCusIdAddress(addressId);
		newcontract.setCusId(getLoginUserId());
		newcontract.setCreateTime(date);
		newcontract.setContractFrom("前台用户");
		newcontract.setPayType(7);// 1支付宝 2货到付款7为银行汇款
		newcontract.setStatus(0);// 2情况下，如果　0为未激活　1为激活
		newcontract.setCouponMoney(0);
		newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);// 0代表没有使用 1代表使用
		//newcontract.setContractCDkey(getRandomNum(16));//生成16位激活码
		
		newcontract.setFreight(0);//货到付款默认金额为0
        newcontract.setRelief(0);//特殊减免默认为0
		int ctId = contractService.addContract(newcontract);
		float totalPrice = saveOffLineCashRecords(courseOrder, get_order, ctId, getLoginUserId());
		//谢修改部分开始
		float totalPriceBefore = totalPrice;
		DecimalFormat df = new DecimalFormat("#.00 ");
		Coupon coupon=null;
		float youhuijuan = 0;
		newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals(""))// 优惠卷面值// 谢修改些句
		{
			logger.info("9999999999");
			try{
			 coupon = sellCouService.GetCouponByCode(couponCode);
			Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());
			float zhe = Float.parseFloat(couponType.getPreferentialPrice());
			if (couponType.getCType() == 1) { //如果是打折
				totalPrice = totalPrice * (zhe * 10 / 100);
				youhuijuan = totalPriceBefore-totalPrice;
			}
			if (couponType.getCType() == 2) {//如果是立减
				totalPrice = totalPrice - zhe;
				youhuijuan=zhe;
			}
			if( totalPrice==0)
			{
				totalPrice=totalPriceBefore;
				SimpleDateFormat matter = new SimpleDateFormat(
		                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
				logger.info(matter.format(new Date())+" 优惠券id是: "+couponCode+"  订单id是："+ctId+" 总金额是"+totalPriceBefore);
			}
			totalPrice = Float.parseFloat(df.format(totalPrice));
			youhuijuan = Float.parseFloat(df.format(youhuijuan));
			int couponTypeId = sellCouService
					.GetParentIdBycouponId(String.valueOf(coupon.getId()));// 谢添加
			sellCouService.updateCouponForState(String.valueOf(coupon.getId()),
					couponTypeId);// 谢添加更改优惠券的状态
			newcontract
			.setUseCoupon(Contract.CONTRACT_USE_COUPON_YES);
			newcontract.setCouponId(coupon.getId());
			}catch(Exception e)
			{
				SimpleDateFormat matter = new SimpleDateFormat(
		                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
				logger.error(matter.format(new Date())+" 订单id是："+ctId+" 总金额是"+totalPriceBefore);
			}
		}
		newcontract.setContractCutSumMoney(totalPrice);// 优惠后总金额
		newcontract.setContractSumMoney(totalPriceBefore);// 优惠前总金额
		newcontract.setCouponMoney(youhuijuan);// 优惠额
		//谢修改部分结束
		if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")) {
			newcontract.setWebFrom(webFrom);
		}
		
		if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null")) {
			newcontract.setWebAgent(webAgent);
		}
		
		logger.info("444444444");
		/**
		 * 查询Cookies是否有亿起发的信息，有则添加到订单中
		 */
		if(null!=src&&!"".equals(src)){
			newcontract.setSrc(src);
			if(null!=wi&&!"".equals(wi)){
				newcontract.setWi(wi) ;
			}
			if(null!=strCid&&!"".equals(strCid.trim())){
				cid = Integer.parseInt(strCid) ;
				newcontract.setCid(cid) ;
			}
			//给亿起发返订单数据
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(date);
			String url = "cid="+cid+"&wi="+wi+"&on="+get_order+"&ta=1&pp="+totalPrice+"&sd="+dateTime+"&encoding=utf-8" ;
			String flag = this.getGetMsg().getHttpMessageFromGet(url);
			if("0".equals(flag)){
				logger.info("成功发送亿起发订单数据") ;
			}else if("1".equals(flag)){
				logger.info("缺少必要参数") ;
			}else if("2".equals(flag)){
				logger.info("参数格式错误") ;
			}
		}
		
		/**
		 * 为第三方网站进行订单推送
		 * add by caowei
		 * 2011-07-18
		 */
		try{
			String sendData = "";
			int siteCode = 0;
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null")){
				if(webFrom.trim().equals("51fanli")){
					siteCode = 1;
				}
			}
			if(webAgent != null && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && siteCode != 0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sendDate = sdf.format(date);
				HashMap detailMap = (HashMap) this.getContractDetail(courseOrder);
				String pks = (String) detailMap.get("pks");//售卖方式的字符串
				String prices = (String) detailMap.get("prices");//售卖方式价格的字符串
				String pksNum = (String) detailMap.get("pksNum");//售卖方式的个数
				String comm = (String)detailMap.get("comm");//佣金
				if(siteCode == 1){//返利网
					String m_id = "highso";//由返利网提供
					String k = "123456";//由返利网提供
					sendData = "otime=" + sendDate + "&o_cd=" + get_order
							+ "&m_id=" + m_id + "&k=" + k + "&u_id=" + webAgent
							+ "&p_cd=" + pks + "&c_cd="+comm+"&it_cnt=" + pksNum
							+ "&price=" + prices + "&comm="+comm+"&username=abcd";
					logger.info(sendData);
				}
				String res = this.getGetMsg().sendContractToOtherSiteFromGet(siteCode, sendData.trim());
			}
		}catch(Exception e){
			logger.info("推送订单异常！");
		}
		
		
		//对应网盟过来的订单
		String wmFlag="wm";
		if(null  != webAgent && !webAgent.trim().equals("") && !webAgent.trim().equals("null") && webAgent.trim().equals(wmFlag)) {
			newcontract.setWebAgent(webAgent); 
			if(webFrom != null && !webFrom.trim().equals("") && !webFrom.trim().equals("null") ) {
				newcontract.setWebFrom(webFrom);
			}
		}
		/**
		 * Yaning  查询是否为中视网盟订单如是则把cookie写入库中
		 * 
		 */
		try{
			if (webFrom != null && !webFrom.trim().equals("")
					&& !webFrom.trim().equals("null")) {
				if(webFrom.equals("zswm")){
					String zswmInfo = CookieHandler.getCookieValueByName(servletRequest,"ZSWMINFO");
					if(zswmInfo != null && zswmInfo.trim().length() > 0 && !zswmInfo.equals("null")){
						//cookie 编码|替换
						String codeZswmInfo = zswmInfo.replaceAll("%7C", "|");
						newcontract.setSrc(codeZswmInfo);
					}
				}
			}
			}catch(Exception e){
				logger.error("makeContract zswm",e);
				newcontract.setSrc("");
			}
		
		contractService.updateContract(newcontract);
		//在优惠券中添加订单 谢添加开始
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals("")) {
		Map map=new HashMap();
		map.put("contarctId", newcontract.getId());
		map.put("couponId", coupon.getId());
		sellCouService.updateCouponForContractId(map);
		sellCouService.updateCouponForPayState(couponId, coupon.getParentId());//改变优惠券状态
		}
		//谢添加结束
		logger.info("前台/用户中心/我的订单/" + "用户：" + getLoginUserId() + "订单号：" + get_order 
						+ "订单来源:前台用户　下单时间：" + date + "订单状态：等待付款/成功");
		
		/**
		 * 
		 * 杨宁 2012/04/25 加入此处，新起一个线程加入线程池进行写入保过协议内容与同时新crm推送信息 --->保过协议与流水1对1关联,此操作将保过协议文本信息写入cus_cash_protocal_tbl关联表中
		 * 原因为:产品保过协议功能更改后台保过协议可各更改与crm业务需要
		 * 
		 * **/
		try{
			if(cusDetialId > 0 && sellids != null && sellids.trim().length() > 0){
				taskExecutor.execute(new ProtocalContractTask(cashSellMap,sellids,cusDetialId,sellWayService,cusProtocalCashService));
			}
			taskExecutor.execute(new CrmSendMessageTask(newcontract));
		}catch(Exception e){
			logger.error("ContractWebAction.taskExecutor.execute",e);
		}

		return new String[]{get_order, totalPrice + ""};
	}
	
	/**
	 * 获取订单的明细，包含售卖方式、单价
	 * @param courseOrder
	 * @return
	 */
	public Map<String,String> getContractDetail(String courseOrder){
		
		Map<String,String> detailMap = new HashMap<String,String>();
		ArrayList packList = new ArrayList();
		String[] courses = courseOrder.split("#");
		for(int j=1; j<courses.length; j++){
			String[]crInfo=courses[j].split(",");
			int packId=new Integer(crInfo[2]);
			this.getQuerySellCouCondition().setSellId(packId);
			List<SellCou> sellCouList=sellCouService.getSellCouList(this.getQuerySellCouCondition());
			for(int i=0;i<sellCouList.size();i++){
				packList.add(sellCouList.get(i).getSellId());
			}
		}
		packList = (ArrayList) this.filterList(packList);
		String pks = "";
		String prices = "";
		String pksNum = "";
		String comm = "";
		for (int i = 0; i < packList.size(); i++) {
			pks += packList.get(i) + "|_|";
			SellWay sw = sellWayService.getSellWayById((Integer) packList.get(i));
			prices += sw.getSellPrice()+"|_|";
			pksNum += "1|_|";
			comm += "0|_|";
//			sellWayService.getSellWayById(38);
			//prices += sellWayService.getSellWayById(Integer.parseInt((String) packList.get(i))).getSellPrice() + "|_|";
		}
		detailMap.put("pks", pks);
		detailMap.put("prices", prices);
		detailMap.put("pksNum", pksNum);
		detailMap.put("comm", comm);
		return detailMap;
	}
	
	/**
	 * 记录流水
	 * @param ctId
	 * @return
	 */
	private float saveCashRecords(String courseOrder, String get_order, int ctId, int cusId) {
		//记录到流水表中
		CashRecord cashRecord = null;
		Date date = new Date();
		float totalPrice = 0;   //订单总价
		float total = 0;       //前(N-1)个流水总价
		Set<String> stmpset = new HashSet<String>();
		String[] body = courseOrder.split("#");
		int blength = body.length;
		List<SellWay> sellList = new ArrayList<SellWay>();
		//累计计算订单总金额
		for (int j = 1; j < blength; j++) {
			String[] crInfo = body[j].split(",");
			if (stmpset.contains(crInfo[2])) {
				continue;
			} else {
				stmpset.add(crInfo[2]);
			}
			int packId = new Integer(crInfo[2]);
			SellWay sellWay = sellWayService.getSellWayById(packId);
			totalPrice += sellWay.getSellPrice();
			sellList.add(sellWay);
		}
		float totalPriceBefore = totalPrice;
		
		//Cope  jinlaong code  获取优惠券面值 
        Coupon coupon = null;
		float youhuijuan = 0;
		//已使用优惠卷 时获得优惠劵金额
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals("")){
			coupon = sellCouService.GetCouponByCode(couponCode);
			Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());
			float zhe = Float.parseFloat(couponType.getPreferentialPrice());
			if (couponType.getCType() == 1) { //如果是打折
				float zheTotalPrice = totalPriceBefore * (zhe * 10 / 100);
				//做四舍五入操作，保留到小数点之后2位
    			BigDecimal bd = new BigDecimal(zheTotalPrice);   
    			float zTalPrice = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    			
				youhuijuan = totalPriceBefore - zTalPrice;
			}
			if (couponType.getCType() == 2) {//如果是立减
				youhuijuan = zhe;
			}
		}
		
		SellWay sellWay = null;
		for(int i=0; i<sellList.size(); i++){
			sellWay = sellList.get(i);
			if(sellWay!=null){
	            cashRecord = new CashRecord();
	            cashRecord.setCusId(getLoginUserId());                      //用户ID
	            cashRecord.setPrice(sellWay.getSellPrice());                      //课程原始价格
	            cashRecord.setContractId(get_order);                        //订单号
	            cashRecord.setType(1);                                      //购买类型 1前台购买，2后台赠送 3后台修复
	            cashRecord.setCtId(ctId);                                   //定单号
	            cashRecord.setPackId(sellWay.getSellId());                               //包ID
	            cashRecord.setCourseId(0);
	            cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》");      //该流水的课程名
	            cashRecord.setCreateTime(new Date());                       //生成流水的时间
    //--------开始-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------
	            cashRecord.setStopTime(sellWay.getStopServiceTime()); // 停止服务时间
	            if (sellWay.getValidityNum() != null && sellWay.getValidityNum() > 0) {
	            	Calendar now = Calendar.getInstance();
	            	now.add(Calendar.DAY_OF_MONTH, sellWay.getValidityNum());
	            	cashRecord.setValidityTime(now.getTime());
	            } else {
	            	cashRecord.setValidityTime(sellWay.getValidityEndTime());
	            }
    //--------结束-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------

 //------start---------在此添加流水表的新字段--------fanxin----------------------------------------------
	            cashRecord.setCRSubjectId(sellWay.getSubjectId()); 
	            cashRecord.setStatus(0);         // 流水的支付状态 0 未支付，  1 已支付 ， 2 已取消
	            cashRecord.setReliefPrice(0);	 // 减免价格默认为0
	            cashRecord.setShopStatus(0);     // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
	            cashRecord.setShopPayType(2);	 // 0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通/7:银行汇款
	            //cashRecord.setDelayTime(0);		 // 延期时间
	            cashRecord.setShopType(1);       // 商品类型 1：课时  2：书籍 （默认是 1）
	            
	            float sellPrice = sellWay.getSellPrice();
	            //未使用时 优惠价格为0 ，流水成交价格 为 sellPrice
	            cashRecord.setCashRecordPrice(sellPrice);//流水成交价格
	            if (youhuijuan == 0 ) {
	            	cashRecord.setCouponMoney(0);  
	    		} else { //已使用优惠卷 
	    			if(i != (sellList.size()-1)){    //前n个流水的 优惠金额 = 单个商品金额 / 订单总价 * 优惠劵金额
	        			float couponMoney = sellPrice/totalPriceBefore * youhuijuan;
	        			//做四舍五入操作，保留到小数点之后2位
	        			BigDecimal bd = new BigDecimal(couponMoney);   
	        			float cMoney = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
	        			cashRecord.setCouponMoney(cMoney);  //优惠价格
	        			cashRecord.setCashRecordPrice(sellPrice - cMoney);//流水成交价格
	        			total += cMoney;
	    			}else{    //最后一个流水的优惠金额 = 有汇总金额 - 前N个流水的优惠金额
	    				cashRecord.setCouponMoney(youhuijuan - total);  //优惠价格
	    				cashRecord.setCashRecordPrice(sellPrice - (youhuijuan - total));//流水成交价格
	    			}
	    		}
//-----------------end----------------在此添加流水表的新字段-------fanxin----------------------------------------
			} 
            int cashId = cashRecordService.addCashRecord(cashRecord);                //添加到流水表中
            
            //Yangning 查看用户购买课程是否有保过协议如有写入
            try{
                /**-------杨宁  2012/04/25 加入保过协议,生成如下MapList<sellId,cashId> 为方便起线程写入**/
                if(cusDetialId > 0 && sellids !=null && sellids.trim().length() > 0){
           		 Map sellMap = new HashMap<Integer,String>();
                    sellMap.put(cashRecord.getPackId(),cashId);
                    this.cashSellMap.add(sellMap);
                }
            }catch(Exception e){
            	logger.error("ContractWebAction.saveCashRecords____1121",e);
            }
            
            logger.info("前台/个人中心/消费记录/" + "用户:" + getLoginUserId() + "购买:" + sellWay.getSellName() + "金额：" 
                    + sellWay.getSellPrice() + "订单号为:" + get_order + "创建时间" + date + "/成功");
		}
		
		// fanxin 增加 cashRecordYF运费的流水 开始
		CashRecord cashRecordYF = new CashRecord();
		cashRecordYF.setCusId(getLoginUserId());                      //用户ID
		cashRecordYF.setPrice(10);                                    //运费价格为 10
		cashRecordYF.setContractId(get_order);                        //订单号
		cashRecordYF.setType(1);                                      //购买类型 1前台购买，2后台赠送 3后台修复
		cashRecordYF.setCtId(ctId);                                   //定单号
		cashRecordYF.setPackId(0);                                    //包ID
		cashRecordYF.setCourseId(0);
		cashRecordYF.setCrInfo("运费");   //该流水的课程名
		cashRecordYF.setCreateTime(new Date());                       //生成流水的时间
		
		cashRecordYF.setCRSubjectId(0); 
		cashRecordYF.setStatus(0);               // 流水的支付状态 0 未支付，  1 已支付 ， 2 已取消
		cashRecordYF.setReliefPrice(0);	         // 减免价格默认为0
		cashRecordYF.setShopStatus(0);           // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
		cashRecordYF.setShopPayType(2);	         // 0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通/7:银行汇款
        //cashRecordYF.setDelayTime(0);		     // 延期时间
		cashRecordYF.setShopType(3);             // 商品类型 1：课时  2：书籍 （默认是 1）， 3：运费
		cashRecordYF.setCashRecordPrice(10);     // 流水成交价格
		cashRecordYF.setCouponMoney(0);		     // 优惠价格为0
        cashRecordService.addCashRecord(cashRecordYF);                //添加到流水表中
        logger.info("前台/个人中心/消费记录/" + "用户:" + getLoginUserId() + "订单号为:" + get_order + "的订单运费流水金额：10元" + "创建时间" + date + "/成功");
     // fanxin 增加 cashRecordYF运费的流水 结束
        
		return totalPriceBefore;
	}
	
	/**
	 * 功能：银行汇款方式生成流水
	 * @param courseOrder
	 * @param get_order
	 * @param ctId
	 * @param cusId
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-7
	 */
	private float saveOffLineCashRecords(String courseOrder, String get_order, int ctId, int cusId) {
		//记录到流水表中
				CashRecord cashRecord = null;
				Date date = new Date();
				float totalPrice = 0;   //订单总价
				float total = 0;       //前(N-1)个流水总价
				Set<String> stmpset = new HashSet<String>();
				String[] body = courseOrder.split("#");
				int blength = body.length;
				List<SellWay> sellList = new ArrayList<SellWay>();
				//累计计算订单总金额
				for (int j = 1; j < blength; j++) {
					String[] crInfo = body[j].split(",");
					if (stmpset.contains(crInfo[2])) {
						continue;
					} else {
						stmpset.add(crInfo[2]);
					}
					int packId = new Integer(crInfo[2]);
					SellWay sellWay = sellWayService.getSellWayById(packId);
					totalPrice += sellWay.getSellPrice();
					sellList.add(sellWay);
				}
				float totalPriceBefore = totalPrice;
				
				//Cope  jinlaong code  获取优惠券面值 
		        Coupon coupon = null;
				float youhuijuan = 0;
				//已使用优惠卷 时获得优惠劵金额
				if (couponCode != null && !couponCode.equals("0") && !couponCode.equals("")){
					coupon = sellCouService.GetCouponByCode(couponCode);
					Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());
					float zhe = Float.parseFloat(couponType.getPreferentialPrice());
					if (couponType.getCType() == 1) { //如果是打折
						float zheTotalPrice = totalPriceBefore * (zhe * 10 / 100);
						//做四舍五入操作，保留到小数点之后2位
		    			BigDecimal bd = new BigDecimal(zheTotalPrice);   
		    			float zTalPrice = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		    			
						youhuijuan = totalPriceBefore - zTalPrice;
					}
					if (couponType.getCType() == 2) {//如果是立减
						youhuijuan = zhe;
					}
				}
				
				SellWay sellWay = null;
				for(int i=0; i<sellList.size(); i++){
					sellWay = sellList.get(i);
					if(sellWay!=null){
			            cashRecord = new CashRecord();
			            cashRecord.setCusId(getLoginUserId());                      //用户ID
			            cashRecord.setPrice(sellWay.getSellPrice());                      //课程原始价格
			            cashRecord.setContractId(get_order);                        //订单号
			            cashRecord.setType(1);                                      //购买类型 1前台购买，2后台赠送 3后台修复
			            cashRecord.setCtId(ctId);                                   //定单号
			            cashRecord.setPackId(sellWay.getSellId());                               //包ID
			            cashRecord.setCourseId(0);
			            cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》");      //该流水的课程名
			            cashRecord.setCreateTime(new Date());                       //生成流水的时间
		//--------开始-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------
			            // 根据商品的有效期计算流水的有效期
			            // 有效期以天数形式时，则有效期为当前时间 + 商品有效期天数
			            if (sellWay.getValidityNum() != null && sellWay.getValidityNum() > 0) {
			            	Calendar now = Calendar.getInstance();
			            	now.add(Calendar.DAY_OF_MONTH, sellWay.getValidityNum());
			            	cashRecord.setValidityTime(now.getTime());
			            }
			            // 有效期不以天数形式时，则直接取商品的有效期。
			            else {
			            	cashRecord.setValidityTime(sellWay.getValidityEndTime());
			            }
			            // 设置流水的服务终止时间为商品的服务终止时间。
			            cashRecord.setStopTime(sellWay.getStopServiceTime());
		//--------结束-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------

		 //------start---------在此添加流水表的新字段--------fanxin----------------------------------------------
			            cashRecord.setCRSubjectId(sellWay.getSubjectId()); 
			            cashRecord.setStatus(0);         // 流水的支付状态 0 未支付，  1 已支付 ， 2 已取消
			            cashRecord.setReliefPrice(0);	 // 减免价格默认为0
			            cashRecord.setShopStatus(0);     // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
			            cashRecord.setShopPayType(7);	 // 0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通/7:银行汇款
			            //cashRecord.setDelayTime(0);		 // 延期时间
			            cashRecord.setShopType(1);       // 商品类型 1：课时  2：书籍 （默认是 1）
			            
			            float sellPrice = sellWay.getSellPrice();
			            //未使用时 优惠价格为0 ，流水成交价格 为 sellPrice
			            cashRecord.setCashRecordPrice(sellPrice);//流水成交价格
			            if (youhuijuan == 0 ) {
			            	cashRecord.setCouponMoney(0);  
			    		} else { //已使用优惠卷 
			    			if(i != (sellList.size()-1)){    //前n个流水的 优惠金额 = 单个商品金额 / 订单总价 * 优惠劵金额
			        			float couponMoney = sellPrice/totalPriceBefore * youhuijuan;
			        			//做四舍五入操作，保留到小数点之后2位
			        			BigDecimal bd = new BigDecimal(couponMoney);   
			        			float cMoney = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			        			cashRecord.setCouponMoney(cMoney);  //优惠价格
			        			cashRecord.setCashRecordPrice(sellPrice - cMoney);//流水成交价格
			        			total += cMoney;
			    			}else{    //最后一个流水的优惠金额 = 有汇总金额 - 前N个流水的优惠金额
			    				cashRecord.setCouponMoney(youhuijuan - total);  //优惠价格
			    				cashRecord.setCashRecordPrice(sellPrice - (youhuijuan - total));//流水成交价格
			    			}
			    		}
		//-----------------end----------------在此添加流水表的新字段-------fanxin----------------------------------------
					} 
		            int cashId = cashRecordService.addCashRecord(cashRecord);  //添加到流水表中
		            try{
		                /**-------杨宁  2012/04/25 加入保过协议,生成如下MapList<sellId,cashId> 为方便起线程写入**/
		                if(cusDetialId > 0 && sellids !=null && sellids.trim().length() > 0){
		           		 Map sellMap = new HashMap<Integer,String>();
		                    sellMap.put(cashRecord.getPackId(),cashId);
		                    this.cashSellMap.add(sellMap);
		                }
		            }catch(Exception e){
		            	logger.error("ContractWebAction.saveOffLineCashRecords____1274",e);
		            }
		            logger.info("前台/个人中心/消费记录/" + "用户:" + getLoginUserId() + "购买:" + sellWay.getSellName() + "金额：" 
		                    + sellWay.getSellPrice() + "订单号为:" + get_order + "创建时间" + date + "/成功");
				}
				return totalPriceBefore;
	}
	
	/**
	 * 清除生成订单所用的cookie中的数据
	 */
	private void clearCookieContractInfo() {
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "courses");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "totalPrice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "courseOrder");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "payprice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "totalPrice1");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "zongjia");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "coursesdan");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(), "coursesbao");
	}
	
	/**
	 * 保存地址
	 * @return
	 */
	private int saveAddress() {
		address.setCusId(getLoginUserId());
		return addressService.addAddress(address);
	} 
	
	   /**
     * 保存地址
     * @return
     */
    public String saveAddressSF() {
        try {
            address.setCusId(getLoginUserId());
            int t=addressService.addAddress(address);
            this.setResult(new Result<String>(false,"success",null,t+""));
        } catch (Exception e) {
            this.setResult(new Result<String>(false,"error",null,""));
            logger.error("更新学员收获地址错误！", e);
        }
      return "json";
 } 
	/**
	 * 随机生成激活码
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 62;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
	
	/**
	 * 查询货到付款的订单
	 */
    public String getContractCount(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	int cusId=this.getLoginUserId();
    	List<Contract> contractList=this.contractService.getContractCount(cusId);
    	String contractIdStr = "";
    	for(int i=contractList.size()-1; i>-1; i--) {
    		Contract ct = contractList.get(i);
    		if(ct.getCdkeySum() != null) {
    			String[] cdkeySumStrs = ct.getCdkeySum().split(",");
    			if(cdkeySumStrs.length > 0 && cdkeySumStrs[0].equals(format.format(new Date())) && Integer.valueOf(cdkeySumStrs[1]) > 4) {
    				continue;
    				//contractList.remove(ct);
    			}
    		}
    		contractIdStr += ct.getContractId();
    		if(i != 0) {
    			contractIdStr += ",";
    		}
    	}
		this.setResult(new Result<Object>(true, contractIdStr, null, null));
    	return "json";
    }
    
    /**
     * 初始化课程　货到付款的方式
     */
    public String gotoCOD(){
    	try{
	    	Date date = new Date();
	    	int userId=this.getLoginUserId();
	    	getQueryContractCondition().setUserId(userId);
	    	PageResult  ctList = contractService.getContractList(getQueryContractCondition());
	    	if(ctList == null || ctList.getPageResult() == null || ctList.getPageResult().size() == 0) {
	    		setResult(new Result(false, "fail", null, null));
	    		return "json";
	    	}
	    	
	    	String[] cdkeySumStrs = null;
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	Contract ct = (Contract)ctList.getPageResult().get(0);
	    	if(ct.getCdkeySum() != null && ct.getCdkeySum().split(",").length > 0) {
		    	cdkeySumStrs = ct.getCdkeySum().split(",");
		    	if(cdkeySumStrs[0].equals(format.format(new Date())) && Integer.valueOf(cdkeySumStrs[1]) >= 5) {
		    		setResult(new Result(false, "moreTimes", null, null));
		    		return "json";
		    	}
	    	}
	    	
	    	getQueryContractCondition().setContractCDkey(contractCDkey);
	    	ctList = contractService.getContractList(getQueryContractCondition());
			if(ctList!=null && ctList.getPageResult().size()>0){
				ct=(Contract)ctList.getPageResult().get(0);
				if(ct.getStatus()!=1){
					ct.setStatus(1);//已激活
					ct.setPayTime(date);
					this.contractService.updateContract(ct);
					
					int cashRecirdId = ct.getId();
					
					CashRecord cr = new CashRecord();
					cr.setStatus(1);      //状态设为：已付款
					cr.setShopStatus(1);    //商品设为：已激活
					cr.setCtId(cashRecirdId);
					cr.setCusId(userId);
					
					this.getCashRecordService().updateCashReocrdStatusByIds(cr);
					
					//发送购买成功消息
					Message msg = messageService.getMessageByKey("buy");
					if(msg != null && msg.getMsgId() > 0){
						User sender = new User();
						sender.setUserId(1);
						sender.setUserName("超级管理员");
						Customer cus = customerService.getCustomerById(userId);
						if(cus != null){
							userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(), cus);
						}
					}
					//支付成功发送短信给客户
//					Customer cusTemp=this.customerService.getCustomerById(userId);
//					if(cusTemp.getMobile()!=null&&!"".equals(cusTemp.getMobile().trim())){
//						try {
//							this.smsService.sendEx("【嗨学网】您的课程已激活成功，点击我的课程，即可开始学习！", cusTemp.getMobile(), "", "", "");
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
					//谢添加修改支付状态
					try{
					if(ct.getUseCoupon()==Contract.CONTRACT_USE_COUPON_YES&&ct.getStatus() == Contract.CONTRACT_STATUS_PAIED)
					{
					String couponId=sellWayService.getContractForCouponIdById(String.valueOf(ct.getId()));
					if(couponId!=null&&!couponId.equals(""))
					{
					int couponTypeId=sellWayService.GetParentIdBycouponId(couponId);
					sellWayService.updateCouponForPayState(couponId, couponTypeId);
					}
					}
					}catch(Exception e){
						System.out.println(e.toString());
					}
					//谢添加结束
					try{
						//addby liuqg 20120427 激活时需要将信息同时同步到SS
						if("ssStore".equals(ct.getWebFrom())){
							XFireProxyFactory factoryInstance = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		        			Service srvcModel = new ObjectServiceFactory().create(OrderService.class);
		        			OrderService iWebService = (OrderService)factoryInstance.create(srvcModel,"http://haixue.com/ssgethighsosendkey.ws");
		        			iWebService.updateOrderSend(ct.getContractId(), userId);
						}
					}catch(Exception e ){
						logger.error("同步订单数据错误-sstore:",e);
					}
					
					setResult(new Result(false, "success", null, null));
				} else {
					setResult(new Result(false, "hasAct", null, null));
				}
			}else{
				if(cdkeySumStrs == null || cdkeySumStrs.length<=1 || !cdkeySumStrs[0].equals(format.format(new Date()))) {
					ct.setCdkeySum(format.format(new Date()) + ",1");
				} else {
					ct.setCdkeySum(cdkeySumStrs[0] + "," + (Integer.valueOf(cdkeySumStrs[1]) + 1));
				}
				contractService.updateContract(ct);
				setResult(new Result(false, "fail", null, null));
			}
    	}catch(Exception e){
    		logger.error("ContractWebAction.gotoCOD", e);
    		setResult(new Result(false, "fail", null, null));
    	}
		return "json";
	}
    
	/**
	 * 设置左侧工具栏
	 * 
	 * @param userId
	 */
	protected void setCourseSortListDTOList(int userId){
		Customer customer = customerService.getCustomerById(userId);
		
		if(customer.getIsComplete() != 1 ||(customer.getIsComplete() == 1 && customerService.isComplete(userId) >= 0)){
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}
		
		if(userId != 0){
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}
	}
	
	/**
	 * 
	 * 功能:用户下单完成后异步发送短信
	 * Author:Yangning
	 * CreateDate:2011-12-22
	 */
	public String sendAsyncMsgToUserMobile() {
		try {
			String type = this.getServletRequest().getParameter("type");
			if(type != null){
				Customer customer = customerService.getCustomerById(getLoginUserId());
				if (customer != null && customer.getMobile() != null && !"".equals(customer.getMobile().trim())) {
					if(type.equals("1")){
						smsService.sendEx("【嗨学网】您的订单已成功提交，我们会在24小时内和您联系确认并发货，请保持手机开通", customer.getMobile(), "", "", "");
					}
					if(type.equals("2")){
						smsService.sendEx("【嗨学网】您的订单已成功提交，我们会及时与您联系，请保持手机开通", customer.getMobile(), "", "", "");
					}
					setResult(new Result(true, "success", null, null));
				}
			}
		} catch(Exception e) {
			logger.error("前台/用户中心/我的订单/sendSmsConfirmContract:"+e.getMessage());
			setResult(new Result(true, "error", null, null));
		}
		return "json";
	}
	
	
	public String openContract(){
		
		return "success";
	}

	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}
	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}
	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCousid() {
		return cousid;
	}

	public void setCousid(int cousid) {
		this.cousid = cousid;
	}

	public String getOderid() {
		return oderid;
	}

	public void setOderid(String oderid) {
		this.oderid = oderid;
	}
	
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public ICourse getCourseService() {
		return courseService;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	public IsmsService getSmsService() {
		return smsService;
	}
	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}
	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}
	public IKpoint getKpointService() {
		return kpointService;
	}
	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public IAddress getAddressService() {
		return addressService;
	}
	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static String getCOOKIE_REMEMBERME_KEY() {
		return COOKIE_REMEMBERME_KEY;
	}
	public boolean isChangeAddress() {
		return changeAddress;
	}
	public void setChangeAddress(boolean changeAddress) {
		this.changeAddress = changeAddress;
	}
	public String getContractCDkey() {
		return contractCDkey;
	}
	public void setContractCDkey(String contractCDkey) {
		this.contractCDkey = contractCDkey;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public IUserMsg getUserMsgService() {
		return userMsgService;
	}
	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}
	public IMessage getMessageService() {
		return messageService;
	}
	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}
	public QuerySellCouCondition getQuerySellCouCondition() {
		if(querySellCouCondition==null)
		{
			querySellCouCondition=new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}
	public void setQuerySellCouCondition(QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}
	public ISellCou getSellCouService() {
		return sellCouService;
	}
	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}
	public GetHttpMessage getGetMsg() {
		return getMsg;
	}
	public void setGetMsg(GetHttpMessage getMsg) {
		this.getMsg = getMsg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 工具方法-用于对List中的元素进行剔重
	 * @param list
	 * @return
	 */
	public List filterList(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}	

	public QuerySellWayCondition getQuerySellWayCondition() {
		return querySellWayCondition;
	}
	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
  
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getPaytotal() {
		return paytotal;
	}
	public void setPaytotal(String paytotal) {
		this.paytotal = paytotal;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public Integer getComType() {
		return comType;
	}
	public void setComType(Integer comType) {
		this.comType = comType;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getSellids() {
		return sellids;
	}
	public void setSellids(String sellids) {
		this.sellids = sellids;
	}
	public int getCusDetialId() {
		return cusDetialId;
	}
	public void setCusDetialId(int cusDetialId) {
		this.cusDetialId = cusDetialId;
	}
	public ICusProtocalCash getCusProtocalCashService() {
		return cusProtocalCashService;
	}
	public void setCusProtocalCashService(ICusProtocalCash cusProtocalCashService) {
		this.cusProtocalCashService = cusProtocalCashService;
	}
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getPaySum() {
		return paySum;
	}
	public void setPaySum(int paySum) {
		this.paySum = paySum;
	}
	public int getCancelSum() {
		return cancelSum;
	}
	public void setCancelSum(int cancelSum) {
		this.cancelSum = cancelSum;
	}
	
	
}
