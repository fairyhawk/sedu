package com.shangde.edu.card.service;

import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.card.domain.*;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;

public class CardMainServiceImpl extends BaseService implements CardMainService {
	private CardCourseService cardCourseService;//课程卡service
	private IContract contractService;//订单service
	private ISellWay sellWayService ;//商品service
	private ICashRecord cashRecordService;//订单流水service
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public IContract getContractService() {
		return contractService;
	}
	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}
	public CardCourseService getCardCourseService() {
		return cardCourseService;
	}
	public void setCardCourseService(CardCourseService cardCourseService) {
		this.cardCourseService = cardCourseService;
	}
	/**
	 * 激活课程卡
	 * @param cardCourse
	 * @throws Exception
	 */
	public String updateActivateCardCourse(CardCourse cardCourse,Integer cusId) throws Exception {
		//如果更新课程卡信息成功，生成订单信息
		Date date=new Date();
		//先生成订单编号，将其保存到课程卡中
		cardCourse.setOrderCode(String.valueOf(cusId) + date.getTime());
		Integer cardMainId=cardCourseService.updateCardCourse(cardCourse,cusId);
		if(cardMainId!=null){
			CardMain cardMain=simpleDao.getEntity("CardMain_NS.selectCardMain", cardMainId);
			//生成并保存订单信息
			return addOrder(cardMain,cardCourse.getOrderCode(),cusId);
		}
		return null;
	}
	/**
	 * 保存订单
	 * @param cardCourse
	 * @param cusId
	 */
	private String addOrder(CardMain cardMain,String orderCode,Integer cusId){
		//填充订单数据
		Contract contract=fillOrder(cardMain,orderCode,cusId);
		//保存订单信息并将生成的订单主键ID set到contract中，以被订单流水使用
		contract.setId(contractService.addContract(contract));
		//保存订单流水
		return addCashRecord(cardMain,contract);
	}
	private String addCashRecord(CardMain cardMain,Contract contract){
		//获取商品ID列表
		List<CardCourseSell> sellWayList=simpleDao.getForList("CardCourseSell_NS.selectSellId", cardMain.getCardMainId());
		//
		StringBuffer sellNameBuffer=new StringBuffer();
		for(CardCourseSell cardCourseSell : sellWayList){
			SellWay sellWay=sellWayService.getSellWayById(cardCourseSell.getSellId());
			//将商品名称拼成字符串，用于前台显示
			sellNameBuffer.append(sellWay.getSellName()+",");
			//填充订单流水数据
			CashRecord cashRecord=fillCashRecord(contract,sellWay,cardMain);
			//保存订单流水数据
			cashRecordService.addCashRecord(cashRecord);
		}
		return sellNameBuffer.deleteCharAt(sellNameBuffer.length()-1).toString();
	}
	 /**
	  * 填充订单数据
	  * @param cardMain
	  * @param cusId
	  * @return
	  */
	private Contract fillOrder(CardMain cardMain,String orderCode,Integer cusId){
		Contract contract=new Contract();
		Date date = new Date();
		contract.setContractId(orderCode);
		contract.setCusId(cusId);
		contract.setContractCutSumMoney(cardMain.getCardMoney());
		contract.setContractSumMoney(cardMain.getCardMoney());
		contract.setContractFrom("前台用户");
		contract.setCreateTime(date);
		contract.setPayType(9);//课程卡
		contract.setPayTime(date);
		contract.setStatus(1);//已激活
		contract.setRelief(0);
		return contract;
	}
	/**
	 * 填充订单流水数据
	 * @param contract
	 * @param sellWay
	 * @return
	 */
	private CashRecord  fillCashRecord(Contract contract,SellWay sellWay,CardMain cardMain){
		Date date=new Date();
		CashRecord cashRecord=new CashRecord();
		cashRecord.setCusId(contract.getCusId());
		cashRecord.setPackId(sellWay.getSellId());
		cashRecord.setPrice(sellWay.getSellPrice());
		cashRecord.setContractId(contract.getContractId());
		cashRecord.setType(1);//前台购买
		cashRecord.setCtId(contract.getId());
		cashRecord.setCourseId(0);
        cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》");      //该流水的课程名
        cashRecord.setCreateTime(date);                       //生成流水的时间
    /*    if (sellWay.getValidityNum() != null && sellWay.getValidityNum() > 0) {
        	Calendar now = Calendar.getInstance();
        	now.add(Calendar.DAY_OF_MONTH, sellWay.getValidityNum());
        	cashRecord.setValidityTime(now.getTime());
        } else {
        	cashRecord.setValidityTime(sellWay.getValidityEndTime());
        }*/
        cashRecord.setCRSubjectId(sellWay.getSubjectId()); 
        cashRecord.setStatus(1);         // 流水的支付状态 0 未支付，  1 已支付 ， 2 已取消
        cashRecord.setReliefPrice(0);	 // 减免价格默认为0
        cashRecord.setShopStatus(1);     // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
        cashRecord.setShopPayType(9);	 // 课程卡
        cashRecord.setShopType(1);       // 商品类型 1：课时  2：书籍 （默认是 1）
        //未使用时 优惠价格为0 ，流水成交价格 为 sellPrice
        cashRecord.setCashRecordPrice(sellWay.getSellPrice());//流水成交价格
        cashRecord.setShopPayTime(date);
        cashRecord.setValidityTime(cardMain.getValidEndTime());//有效期
		return cashRecord;
	}
}
