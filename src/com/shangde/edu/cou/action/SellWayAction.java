package com.shangde.edu.cou.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryHistoryPriceCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.HistoryPrice;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.SellCouDTO;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.IHistoryPrice;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.sys.action.BackLoginAction;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;
import com.sun.org.apache.bcel.internal.generic.Select;

public class SellWayAction extends CommonAction {

	/**
	 * 专业集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	private Subject subject;
	/**
	 * 专业服务
	 */
	private ISubject subjectService;
	/**
	 * 售卖方式集合
	 */
	private List<SellWay> sellWayList = new ArrayList<SellWay>();
	private List<SellWayDTO> sellWayDTOList = new ArrayList<SellWayDTO>();
	/**
	 * 售卖方式查询条件
	 */
	private QuerySellWayCondition querySellWayCondition;
	/**
	 * 售卖方式服务
	 * 
	 */
	private ISellWay sellWayService;
	private ISellCou sellCouService;

	/**
	 * 售卖方式实体
	 */
	private SellWay sellWay;
	/**
	 * 售卖方式id
	 */
	private int sellId;
	/**
	 * 课程相关的服务等
	 */
	private List<Course> courseList = new ArrayList<Course>();
	private ICourse courseService;
	private QueryCourseCondition queryCourseCondition;
	private String courseIdList;
	private int[] courseIdLists;
	private List<Integer> courseIds = new ArrayList<Integer>();

	private List<SellCou> sellCouList = new ArrayList<SellCou>();
	private QuerySellCouCondition querySellCouCondition;
	private ICashRecord cashRecordService;
	private QueryCashRecordCondition queryCashRecordCondition;
	
	/**
	 * 历史价格服务
	 */
	private List<HistoryPrice> historyPriceList=new ArrayList<HistoryPrice>();
	private IHistoryPrice historyPriceService;
	private QueryHistoryPriceCondition queryHistoryPriceCondition;
	
	private List<SellCouDTO> sellCouDTOList=new ArrayList<SellCouDTO>();
	private SellCouDTO sellCouDTO;
	private HistoryPrice historyPrice;
	/**
	 * 获得售卖方式列表
	 * 
	 * @return
	 */
	public String showSellWayList() {
		try {
			subjectList = subjectService.getAllSubject();
			this.getQuerySellWayCondition()
					.setSubjectId(subject.getSubjectId());
			sellWayDTOList = sellWayService.getSellWayInfoList(this
					.getQuerySellWayCondition());
			return "listSellWay";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 跳转到添加售卖方式页面
	 * 
	 * @return
	 */
	public String toAddSellWay() {
		try {
			subjectList = subjectService.getAllSubject();
			return "addSellWay";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 添加售卖方式
	 * 
	 * @return
	 */
	public String doAddSellWay() {
		try {
			sellWay.setAddTime(new Date());
			sellWayService.addSellWay(sellWay);
			return "changeSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 删除售卖方式 根据传来的ID删除 并且要删除该售卖方式于课程的关联表
	 * 
	 * @return
	 */
	public String delSellWay() {
		try {
			// 删除关联
			this.sellCouService.delSellCouBySellId(sellWay.getSellId());
			// 删除售卖方式
			this.sellWayService.delSellWayById(sellWay.getSellId());
			// 返回列表
			showSellWayList();
			return "listSellWay";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}
	/**
	 * 
	 * @return
	 */
	public String BuyNow(){
		try {
			this.getQuerySellWayCondition().setSaleId(sellId);
			sellWay=sellWayService.getSellWayById(sellId);
			this.setResult(new Result(true,null,null,sellWay));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	/**
	 * 跳转到为售卖方式添加课程的页面，并传递过去当前售卖方式，和当前专业下的课程列表
	 * 
	 * @return
	 */
	public String toAddCourseForSellWay() {
		try {
			// 获得当前的售卖方式对象
			sellWay = sellWayService.getSellWayById(sellWay.getSellId());
			// 查询当前售卖方式所属的专业下的所有课程
			subject = subjectService.getSubjectById(sellWay.getSubjectId());
			this.getQueryCourseCondition().setSubjectId(sellWay.getSubjectId());
			courseList = courseService.getCourseList(queryCourseCondition);
			for (int i = 0; courseList != null && i < courseList.size(); i++) {
				courseList.get(i).setTitle(
						courseList.get(i).getCourseId() + "-"
								+ courseList.get(i).getTitle());
			}
			return "toAddCourse";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 删除该售卖方式下的所选课程
	 * 
	 * @return
	 */
	public String delCourseForSellWay() {
		try {
			int tempCourseId = 0;
			for (int i = 0; courseIdLists != null && courseIdLists.length != 0
					&& i < courseIdLists.length; i++) {
				tempCourseId = courseIdLists[i];
				List<SellCou> sellCouTempList = new ArrayList<SellCou>();
				this.getQuerySellCouCondition().setSellId(sellWay.getSellId());
				this.getQuerySellCouCondition().setCourseId(tempCourseId);
				sellCouTempList = this.getSellCouService().getSellCouList(
						this.getQuerySellCouCondition());
				if (sellCouTempList != null && sellCouTempList.size() != 0) {
					sellCouService.delSellCouById(sellCouTempList.get(0)
							.getId());
				}
				toAddCourseForSellWay();
			}
			return "toAddCourse";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 为售卖方式添加课程
	 * 
	 * @return
	 */
	public String addCourseForSellWay() {
		try {
			SellCou sellCou = null;
			int tempCourseId = 0;
			for (int i = 0; courseIdLists != null && courseIdLists.length != 0
					&& i < courseIdLists.length; i++) {
				tempCourseId = courseIdLists[i];
				List<SellCou> sellCouTempList = new ArrayList<SellCou>();
				// 查看这个课程是否已经添加过，如果能查询出结果，说明添加过
				this.getQuerySellCouCondition().setSellId(sellWay.getSellId());
				this.getQuerySellCouCondition().setCourseId(tempCourseId);
				sellCouTempList = this.getSellCouService().getSellCouList(
						this.getQuerySellCouCondition());
				if (sellCouTempList == null || sellCouTempList.size() == 0) {
					// 如果在该售卖方式下没添加过该课程则添加这条记录
					sellCou = new SellCou();
					sellCou.setCourseId(tempCourseId);
					sellCou.setSellId(sellWay.getSellId());
					sellCou.setSellCouPrice(0f);
					this.getSellCouService().addSellCou(sellCou);
				}
			}

			// 转换课程ID列表

			/*
			 * do{ if(courseIdList.indexOf(",")!=-1){
			 * 
			 * tempCourseId=Integer.parseInt(courseIdList.substring(0,courseIdList.trim().indexOf(",")));
			 * //把这个id的课程添加到售卖方式中 sellCou=new SellCou();
			 * sellCou.setCourseId(tempCourseId);
			 * sellCou.setSellId(sellWay.getSellId());
			 * 
			 * this.getQuerySellCouCondition().setSellId(sellCou.getSellId());
			 * this.getQuerySellCouCondition().setCourseId(sellCou.getCourseId());
			 * sellCouTempList=this.getSellCouService().getSellCouList(this.getQuerySellCouCondition());
			 * if(sellCouTempList==null||sellCouTempList.size()==0) {
			 * //如果在该售卖方式下没添加过该课程则添加这条记录
			 * this.getSellCouService().addSellCou(sellCou); } //把添加过的课程ID截掉
			 * courseIdList=courseIdList.substring(courseIdList.trim().indexOf(",")+1); }
			 * //如果只有一个课程
			 * if(courseIdList.indexOf(",")==-1&&courseIdList.length()!=0) {
			 * List<SellCou> sellCouTempList=new ArrayList<SellCou>();
			 * tempCourseId=Integer.parseInt(courseIdList.trim()); sellCou=new
			 * SellCou(); sellCou.setCourseId(tempCourseId);
			 * sellCou.setSellId(sellWay.getSellId());
			 * //先判断该课程是否已经添加过如果添加过该课程，则不再添加
			 * this.getQuerySellCouCondition().setSellId(sellCou.getSellId());
			 * this.getQuerySellCouCondition().setCourseId(sellCou.getCourseId());
			 * sellCouTempList=this.getSellCouService().getSellCouList(this.getQuerySellCouCondition());
			 * 
			 * //如果在该售卖方式下没添加过该课程则添加这条记录
			 * if(sellCouTempList==null||sellCouTempList.size()==0) {
			 * //如果在该售卖方式下没添加过该课程则添加这条记录
			 * this.getSellCouService().addSellCou(sellCou); } }
			 * }while(courseIdList.trim().indexOf(",")!=-1);
			 */
			return "changeSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 跳转到修改售卖方式页面
	 * 
	 * @return
	 */
	public String toUpdateSellWay() {
		try {
			sellWay = sellWayService.getSellWayById(sellWay.getSellId());
			return "toUpdateSellWay";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改售卖方式
	 * 
	 * @return
	 */
	public String updateSellWay() {
		try {
			sellWay.setAddTime(new Date());
			sellWayService.updateSellWay(sellWay);
			return "changeSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 跳转到显示售卖方式详细信息页面
	 * 
	 * @return
	 */
	public String toSellWayInfo() {
		try {
			sellWayList = sellWayService.getSellWayList(this
					.getQuerySellWayCondition());
			return showSellWayInfo();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 查看售卖方式详细信息
	 * 
	 * @return
	 */
	public String showSellWayInfo() {
		sellWayList = sellWayService.getSellWayList(this
				.getQuerySellWayCondition());
		// 查找这个售卖方式关联的课程
		this.getQuerySellCouCondition().setCourseId(0);
		this.getQuerySellCouCondition().setSellId(sellWay.getSellId());
		sellCouList =this.getSellCouService().getSellCouList(querySellCouCondition);
		for(int i=0;sellCouList!=null&&sellCouList.size()!=0&&i<sellCouList.size();i++)
		{
			this.getQuerySellCouCondition().setCourseId(sellCouList.get(i).getCourseId());
			SellCouDTO sellCouDTOTemp=this.getSellCouService().getSellCouDTOByCourseId(this.getQuerySellCouCondition());
			sellCouDTOList.add(sellCouDTOTemp);
		}		
		
		return "toSellWayInfo";
	}
	
	/**
	 * 修改售卖方式下的课程的信息，用来添加当前售卖方式下的课程的价格等操作
	 * 以及添加历史价格  
	 */
	public String updateSellWayCourse()
	{
		try{
			//sellCouDTO
			this.getQueryHistoryPriceCondition().setCourseId(sellCouDTO.getCourseId());
			List<HistoryPrice> historyPriceList = this.historyPriceService.getHistoryPriceList(this.getQueryHistoryPriceCondition());
			Date nowDate = new Date();
			if(historyPriceList.size()==0){ //判断当前课程是否有历史价格 如果没有历史记录 则添加一条新历史价格记录
				HistoryPrice historyPrice2 = new HistoryPrice();
				historyPrice2.setCreadeTime(nowDate);   //课程价格开始时间
				historyPrice2.setCourseId(sellCouDTO.getCourseId()); //课程ID
				historyPrice2.setPrice(sellCouDTO.getSellCouPrice()); //课程价格
				historyPrice2.setSellId(sellCouDTO.getSellId());  //售卖方式ID
				this.historyPriceService.addHistoryPrice(historyPrice2);   //如果当前课程没有历史价格 则添加一条新的价格
				sellCouProce();//调用修改课程价格方法
			}
			//该课程有历史价格时的操作
			if(historyPriceList!=null&&historyPriceList.size()!=0&&Float.parseFloat(historyPriceList.get(0).getPrice().toString())!=sellCouDTO.getSellCouPrice()){
				HistoryPrice historyPrice = new HistoryPrice();
				historyPrice = this.historyPriceService.getHistoryPriceById(historyPriceList.get(0).getId());  //获取该课程历史价格 倒叙取最进的价格、
				historyPrice.setStopTime(nowDate);  //历史价格结束时间
				this.historyPriceService.updateHistoryPrice(historyPrice);  //更新当前历史价格结束时间
				HistoryPrice historyPrice2 = new HistoryPrice();  //插入一条新的历史价格  只有开始时间，没有结束时间
				historyPrice2.setCreadeTime(nowDate);
				historyPrice2.setCourseId(sellCouDTO.getCourseId());
				historyPrice2.setPrice(sellCouDTO.getSellCouPrice());
				historyPrice2.setSellId(sellCouDTO.getSellId());
				this.historyPriceService.addHistoryPrice(historyPrice2);//执行添加操作
				sellCouProce();//调用修改课程价格方法
			}
			 return toSellWayInfo();

		}catch (Exception e) {
			e.getMessage();
			return ERROR;
		}
	}
	
	/**
	 * 计算售卖方式下课程价格总和
	 */
	public void sellWayPriceSum(int sellWayId) {
		sellWay = this.sellWayService.getSellWayById(sellWayId); // 得到当前售卖方式
		this.getQuerySellCouCondition().setSellId(sellCouDTO.getSellId()); // 附加查询当前售卖方式下课程条件
		this.getQuerySellCouCondition().setCourseId(0);
		List<SellCou> sellCouList = this.sellCouService.getSellCouList(this
				.getQuerySellCouCondition()); // 得到售卖方式下所有课程
		if (sellCouList.size() != 0) { // 遍历该售卖方式下所有课程
			float price = 0;
			for (int i = 0; i < sellCouList.size(); i++) {
				price += sellCouList.get(i).getSellCouPrice();
			}
			sellWay.setSellPrice(price); // 所有课程价格总和为当前售卖方式价格
		}
	}
	
	/**
	 * 修改售卖方式下课程价格
	 * @return
	 */
	public void sellCouProce(){
		this.getQuerySellCouCondition().setSellId(sellCouDTO.getSellId());   //更新售卖方式下课程价格
		this.getQuerySellCouCondition().setCourseId(sellCouDTO.getCourseId());
		List<SellCou> sellCouTemp=this.getSellCouService().getSellCouList(this.getQuerySellCouCondition());
		if(sellCouTemp!=null&&sellCouTemp.size()!=0)
		{
			SellCou sellCou=sellCouTemp.get(0);
			sellCou.setSellCouPrice(sellCouDTO.getSellCouPrice());
			this.getSellCouService().updateSellCou(sellCou);
			
			sellWayPriceSum(sellWay.getSellId());  //调用售卖方式价格方法
			this.sellWayService.updateSellWay(sellWay);
		}
	}
	
	
	
	/**
	 * 修复历史价格
	 * @return
	 */
	public String repairHistoryPrice()
	{
		historyPriceList=this.getHistoryPriceService().getHistoryPriceList(queryHistoryPriceCondition);
		for(int i=0;i<historyPriceList.size();i++)
		{
			HistoryPrice historyPriceTemp=historyPriceList.get(i);
			if(historyPriceTemp.getCourseId()==67||historyPriceTemp.getCourseId()==68)//人力二级
			{
				historyPriceTemp.setSellId(1);
			}else if(historyPriceTemp.getCourseId()==69||historyPriceTemp.getCourseId()==70)//人力三级
			{
				historyPriceTemp.setSellId(2);
			}else if(historyPriceTemp.getCourseId()==15||historyPriceTemp.getCourseId()==17||historyPriceTemp.getCourseId()==20)//会计证
			{
				historyPriceTemp.setSellId(3);
			}else if(historyPriceTemp.getCourseId()==88)//司法重点
			{
				historyPriceTemp.setSellId(4);
			}else if(historyPriceTemp.getCourseId()==75)//司法精品
			{
				historyPriceTemp.setSellId(5);
			}else if(historyPriceTemp.getCourseId()==82||historyPriceTemp.getCourseId()==83||historyPriceTemp.getCourseId()==84
					||historyPriceTemp.getCourseId()==76||historyPriceTemp.getCourseId()==85)//证券专题
			{
				historyPriceTemp.setSellId(6);
			}else if(historyPriceTemp.getCourseId()==77||historyPriceTemp.getCourseId()==78||historyPriceTemp.getCourseId()==79
					||historyPriceTemp.getCourseId()==80
					||historyPriceTemp.getCourseId()==81||historyPriceTemp.getCourseId()==86)//CPA 有赠送全科
			{
				if(historyPriceTemp.getSellId()<9||historyPriceTemp.getSellId()>14)
				{
					historyPriceTemp.setSellId(7);
				}
				
				historyPriceTemp.setSellId(7);
			}else if(historyPriceTemp.getCourseId()==101||historyPriceTemp.getCourseId()==102||historyPriceTemp.getCourseId()==103
					||historyPriceTemp.getCourseId()==104
					||historyPriceTemp.getCourseId()==105||historyPriceTemp.getCourseId()==106)//CPA 无赠送全科
			{
				if(historyPriceTemp.getSellId()<20||historyPriceTemp.getSellId()>25)
				{
					historyPriceTemp.setSellId(8);
				}
			}else if(historyPriceTemp.getCourseId()==94)	//单科有赠送审计
			{
				historyPriceTemp.setCourseId(77);
				historyPriceTemp.setSellId(9);
			}else if(historyPriceTemp.getCourseId()==93)	//单科有赠送经济法
			{
				historyPriceTemp.setCourseId(78);
				historyPriceTemp.setSellId(10);
			}else if(historyPriceTemp.getCourseId()==92)	//单科有赠送战略专题
			{
				historyPriceTemp.setCourseId(79);
				historyPriceTemp.setSellId(11);
			}else if(historyPriceTemp.getCourseId()==91)	//单科有赠送税法
			{
				historyPriceTemp.setCourseId(80);
				historyPriceTemp.setSellId(12);
			}else if(historyPriceTemp.getCourseId()==89)	//单科有赠送财管
			{
				historyPriceTemp.setCourseId(86);
				historyPriceTemp.setSellId(13);
			}else if(historyPriceTemp.getCourseId()==90)	//单科有赠送会计
			{
				historyPriceTemp.setCourseId(81);
				historyPriceTemp.setSellId(14);
			}else if(historyPriceTemp.getCourseId()==107||historyPriceTemp.getCourseId()==108||historyPriceTemp.getCourseId()==109
					||historyPriceTemp.getCourseId()==110
					||historyPriceTemp.getCourseId()==111||historyPriceTemp.getCourseId()==112
					||historyPriceTemp.getCourseId()==113)	//一级建造师课程
			{
				if(historyPriceTemp.getSellId()<16||historyPriceTemp.getSellId()>19)
				{
					historyPriceTemp.setSellId(15);
				}
			}else if(historyPriceTemp.getCourseId()==114)//一级实务
			{
				historyPriceTemp.setCourseId(113);
				historyPriceTemp.setSellId(16);
			}else if(historyPriceTemp.getCourseId()==115)//一级实务
			{
				historyPriceTemp.setCourseId(112);
				historyPriceTemp.setSellId(16);
			}else if(historyPriceTemp.getCourseId()==116)//一级实务
			{
				historyPriceTemp.setCourseId(111);
				historyPriceTemp.setSellId(16);
			}else if(historyPriceTemp.getCourseId()==117)//一级实务
			{
				historyPriceTemp.setCourseId(110);
				historyPriceTemp.setSellId(16);
			}else if(historyPriceTemp.getCourseId()==119)//一级建设工程经济
			{
				historyPriceTemp.setCourseId(108);
				historyPriceTemp.setSellId(17);
			}else if(historyPriceTemp.getCourseId()==118)//一级建设工程法规及相关知识
			{
				historyPriceTemp.setCourseId(109);
				historyPriceTemp.setSellId(18);
			}else if(historyPriceTemp.getCourseId()==120)//一级建设工程法规及相关知识
			{
				historyPriceTemp.setCourseId(107);
				historyPriceTemp.setSellId(19);
			}else if(historyPriceTemp.getCourseId()==100)//CPA审计专题课程(无赠送)
			{
				historyPriceTemp.setSellId(20);
			}else if(historyPriceTemp.getCourseId()==99)//CPA经济法专题课程(无赠送)
			{
				historyPriceTemp.setSellId(21);
			}else if(historyPriceTemp.getCourseId()==98)//CPA战略专题课程(无赠送)
			{
				historyPriceTemp.setSellId(22);
			}else if(historyPriceTemp.getCourseId()==97)//CPA税法专题课程(无赠送) 
			{
				historyPriceTemp.setSellId(23);
			}else if(historyPriceTemp.getCourseId()==95)//CPA财管专题课程(无赠送) 
			{
				historyPriceTemp.setSellId(24);
			}else if(historyPriceTemp.getCourseId()==96)//CPA会计专题课程(无赠送) 
			{
				historyPriceTemp.setSellId(25);
			}else if(historyPriceTemp.getCourseId()==43
					||historyPriceTemp.getCourseId()==46
					||historyPriceTemp.getCourseId()==49)//会计证轻松通关-------------------
			{
				historyPriceTemp.setSellId(26);
			}else if(historyPriceTemp.getCourseId()==44
					||historyPriceTemp.getCourseId()==47
					||historyPriceTemp.getCourseId()==50)//会计证从业上岗
			{
				historyPriceTemp.setSellId(27);
			}else if(historyPriceTemp.getCourseId()==45
					||historyPriceTemp.getCourseId()==48
					||historyPriceTemp.getCourseId()==51)//会计证保过实验
			{
				historyPriceTemp.setSellId(28);
			}else if(historyPriceTemp.getCourseId()==71
					||historyPriceTemp.getCourseId()==72
					||historyPriceTemp.getCourseId()==73
					||historyPriceTemp.getCourseId()==74)//CPA-内部课程
			{
				historyPriceTemp.setSellId(29);
			}else if(historyPriceTemp.getCourseId()==87)//内部-司法考试
			{
				historyPriceTemp.setSellId(30);
			}
			this.getHistoryPriceService().updateHistoryPrice(historyPriceTemp);
		}
		return null;
	}
	public String repairCashReocrd() {
		List<CashRecord> cashRecordList = cashRecordService
				.getSimpleCashRecordByList(queryCashRecordCondition);
		for (int i = 0; i < cashRecordList.size(); i++) {
			CashRecord cashRecordTemp = cashRecordList.get(i);
			// 会计取证专业课程 课程ID15,,17,20
			// 人力资源师二级课程 课程ID67,68
			if (cashRecordTemp.getCourseId() == 67
					|| cashRecordTemp.getCourseId() == 68) {
				//
				cashRecordTemp.setPackId(1);
			} else
			// 人力资源师三级课程 课程ID69,70
			if (cashRecordTemp.getCourseId() == 69
					|| cashRecordTemp.getCourseId() == 70) {
				//
				cashRecordTemp.setPackId(2);
			} else if (cashRecordTemp.getCourseId() == 15
					|| cashRecordTemp.getCourseId() == 17
					|| cashRecordTemp.getCourseId() == 20) {
				cashRecordTemp.setPackId(3);
			} else if (cashRecordTemp.getCourseId() == 88) {
				//
				cashRecordTemp.setPackId(4);
			} else
			// 司法专业精品课程
			if (cashRecordTemp.getCourseId() == 75) {
				cashRecordTemp.setPackId(5);
			} else
			// 证券专题课程
			if (cashRecordTemp.getCourseId() == 76
					|| cashRecordTemp.getCourseId() == 82
					|| cashRecordTemp.getCourseId() == 83
					|| cashRecordTemp.getCourseId() == 84
					|| cashRecordTemp.getCourseId() == 85) {
				cashRecordTemp.setPackId(6);
			} else
			// CPA专题课程全科 有赠送的（最全的）
			if (cashRecordTemp.getCourseId() == 77 
					|| cashRecordTemp.getCourseId() == 78
					|| cashRecordTemp.getCourseId() == 79
					|| cashRecordTemp.getCourseId() == 80
					|| cashRecordTemp.getCourseId() == 81
					|| cashRecordTemp.getCourseId() == 86) {
				if(cashRecordTemp.getPackId()<9||cashRecordTemp.getPackId()>14)
				{
					cashRecordTemp.setPackId(7);
				}
			} else
			// -------
			if (cashRecordTemp.getCourseId() == 101 
					|| cashRecordTemp.getCourseId() == 102
					|| cashRecordTemp.getCourseId() == 103
					|| cashRecordTemp.getCourseId() == 104
					|| cashRecordTemp.getCourseId() == 105
					|| cashRecordTemp.getCourseId() == 106) {
				if(cashRecordTemp.getPackId()<20||cashRecordTemp.getPackId()>25)
				{
					cashRecordTemp.setPackId(8);
				}
			} else if (cashRecordTemp.getCourseId() == 94) // 单科有赠送 审计
			{
				cashRecordTemp.setPackId(9);
				cashRecordTemp.setCourseId(77);
			} else
			// CPA单科有赠送
			if (cashRecordTemp.getCourseId() == 93) // 单科有赠送 经济法
			{
				cashRecordTemp.setPackId(10);
				cashRecordTemp.setCourseId(78);
			} else if (cashRecordTemp.getCourseId() == 92) // 单科有赠送 战略
			{
				cashRecordTemp.setPackId(11);
				cashRecordTemp.setCourseId(79);
			} else if (cashRecordTemp.getCourseId() == 91) // 单科有赠送 税法
			{
				cashRecordTemp.setPackId(12);
				cashRecordTemp.setCourseId(80);
			} else if (cashRecordTemp.getCourseId() == 89)// 单科有赠送 财管
			{
				cashRecordTemp.setPackId(13);
				cashRecordTemp.setCourseId(86);
			} else if (cashRecordTemp.getCourseId() == 90) // 单科有赠送 会计
			{
				cashRecordTemp.setPackId(14);
				cashRecordTemp.setCourseId(81);
			}else if (cashRecordTemp.getCourseId() == 107  //一建全科
					|| cashRecordTemp.getCourseId() == 108
					|| cashRecordTemp.getCourseId() == 109
					|| cashRecordTemp.getCourseId() == 110
					|| cashRecordTemp.getCourseId() == 111
					|| cashRecordTemp.getCourseId() == 112
					|| cashRecordTemp.getCourseId() == 113) {
				if(cashRecordTemp.getPackId()<16||cashRecordTemp.getPackId()>19)
				{
					cashRecordTemp.setPackId(15);
				}
			}else if (cashRecordTemp.getCourseId() == 115  //一级实务
						|| cashRecordTemp.getCourseId() == 116
						|| cashRecordTemp.getCourseId() == 117||cashRecordTemp.getCourseId()==114) {
					if (cashRecordTemp.getCourseId() == 115) {
						cashRecordTemp.setCourseId(112);
					}
					if (cashRecordTemp.getCourseId() == 116) {
						cashRecordTemp.setCourseId(111);
					}
					if (cashRecordTemp.getCourseId() == 117) {
						cashRecordTemp.setCourseId(110);
					}
					if(cashRecordTemp.getCourseId()==114){
						cashRecordTemp.setCourseId(113);
					}
					cashRecordTemp.setPackId(16);
			} else if (cashRecordTemp.getCourseId() == 119) {  //一级建设工程经济
				cashRecordTemp.setCourseId(108);
				cashRecordTemp.setPackId(17);
			} else if (cashRecordTemp.getCourseId() == 118) { //一级建设工程法规及相关知识
				cashRecordTemp.setCourseId(109);
				cashRecordTemp.setPackId(18);
			} else if (cashRecordTemp.getCourseId() == 120) { //一级建设工程项目管理
				cashRecordTemp.setCourseId(107);
				cashRecordTemp.setPackId(19);
			}else if (cashRecordTemp.getCourseId() == 100) // 单科无赠送 审计
			{
				cashRecordTemp.setPackId(20);
				cashRecordTemp.setCourseId(105);
			}else if (cashRecordTemp.getCourseId() == 99)// 单科无赠送 经济法
			{
				cashRecordTemp.setPackId(21);
				cashRecordTemp.setCourseId(104);
			} else if (cashRecordTemp.getCourseId() == 98) // 单科无赠送 战略
			{
				cashRecordTemp.setPackId(22);
				cashRecordTemp.setCourseId(103);
			} else if (cashRecordTemp.getCourseId() == 97) // 单科无赠送 税法
			{
				cashRecordTemp.setPackId(23);
				cashRecordTemp.setCourseId(102);
			} else if (cashRecordTemp.getCourseId() == 95) // 单科无赠送 财管
			{
				cashRecordTemp.setPackId(24);
				cashRecordTemp.setCourseId(106);
			}else if (cashRecordTemp.getCourseId() == 96) // 单科无赠送 会计
			{
				cashRecordTemp.setPackId(25);
				cashRecordTemp.setCourseId(101);
			}else if(cashRecordTemp.getCourseId() == 43
					||cashRecordTemp.getCourseId() == 46
					||cashRecordTemp.getCourseId() == 49)//会计基础轻松通关
			{
				cashRecordTemp.setPackId(26);
			}else if(cashRecordTemp.getCourseId() == 44
					||cashRecordTemp.getCourseId() == 47
					||cashRecordTemp.getCourseId() == 50)//拿证上岗从业
			{
				cashRecordTemp.setPackId(27);
			}
			else if(cashRecordTemp.getCourseId() == 45
					||cashRecordTemp.getCourseId() == 48
					||cashRecordTemp.getCourseId() == 51)//保过实验
			{
				cashRecordTemp.setPackId(28);
			}
			else if(cashRecordTemp.getCourseId() == 71
					||cashRecordTemp.getCourseId() == 72
					||cashRecordTemp.getCourseId() == 73
					||cashRecordTemp.getCourseId() == 74)//CPA内部课程
			{
				cashRecordTemp.setPackId(29);
			}
			else if(cashRecordTemp.getCourseId() == 87)//司法内部
			{
				cashRecordTemp.setPackId(30);
			}
			// CPA单科无赠送
			cashRecordService.updateCashRecord(cashRecordTemp);

		}
		return null;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public QuerySellWayCondition getQuerySellWayCondition() {
		if (querySellWayCondition == null) {
			querySellWayCondition = new QuerySellWayCondition();
		}
		return querySellWayCondition;
	}

	public void setQuerySellWayCondition(
			QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public List<SellWay> getSellWayList() {
		return sellWayList;
	}

	public void setSellWayList(List<SellWay> sellWayList) {
		this.sellWayList = sellWayList;
	}

	public List<SellWayDTO> getSellWayDTOList() {
		return sellWayDTOList;
	}

	public void setSellWayDTOList(List<SellWayDTO> sellWayDTOList) {
		this.sellWayDTOList = sellWayDTOList;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public QueryCourseCondition getQueryCourseCondition() {
		if (queryCourseCondition == null) {
			queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(
			QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getCourseIdList() {
		return courseIdList;
	}

	public void setCourseIdList(String courseIdList) {
		this.courseIdList = courseIdList;
	}

	public List<Integer> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<Integer> courseIds) {
		this.courseIds = courseIds;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public QuerySellCouCondition getQuerySellCouCondition() {
		if (querySellCouCondition == null) {
			querySellCouCondition = new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(
			QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}

	public List<SellCou> getSellCouList() {
		return sellCouList;
	}

	public void setSellCouList(List<SellCou> sellCouList) {
		this.sellCouList = sellCouList;
	}

	public int[] getCourseIdLists() {
		return courseIdLists;
	}

	public void setCourseIdLists(int[] courseIdLists) {
		this.courseIdLists = courseIdLists;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if (queryCashRecordCondition == null) {
			queryCashRecordCondition = new QueryCashRecordCondition();
		}
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public IHistoryPrice getHistoryPriceService() {
		return historyPriceService;
	}

	public void setHistoryPriceService(IHistoryPrice historyPriceService) {
		this.historyPriceService = historyPriceService;
	}

	public List<HistoryPrice> getHistoryPriceList() {
		return historyPriceList;
	}

	public void setHistoryPriceList(List<HistoryPrice> historyPriceList) {
		this.historyPriceList = historyPriceList;
	}

	public QueryHistoryPriceCondition getQueryHistoryPriceCondition() {
		if(queryHistoryPriceCondition==null)
		{
			queryHistoryPriceCondition=new QueryHistoryPriceCondition();
		}
		return queryHistoryPriceCondition;
	}

	public void setQueryHistoryPriceCondition(
			QueryHistoryPriceCondition queryHistoryPriceCondition) {
		this.queryHistoryPriceCondition = queryHistoryPriceCondition;
	}

	public List<SellCouDTO> getSellCouDTOList() {
		return sellCouDTOList;
	}

	public void setSellCouDTOList(List<SellCouDTO> sellCouDTOList) {
		this.sellCouDTOList = sellCouDTOList;
	}

	public SellCouDTO getSellCouDTO() {
		return sellCouDTO;
	}

	public void setSellCouDTO(SellCouDTO sellCouDTO) {
		this.sellCouDTO = sellCouDTO;
	}

	public HistoryPrice getHistoryPrice() {
		return historyPrice;
	}

	public void setHistoryPrice(HistoryPrice historyPrice) {
		this.historyPrice = historyPrice;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

}
