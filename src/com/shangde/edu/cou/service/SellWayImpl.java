package com.shangde.edu.cou.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.common.service.BaseService;

/**
 * SellWay�������ʵ����?
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
@SuppressWarnings("unchecked")
public class SellWayImpl extends BaseService implements ISellWay{
    public java.lang.Integer addSellWay(SellWay sellWay) {
return simpleDao.createEntity("SellWay_NS.createSellWay",sellWay);
    }

    public void delSellWayById(int sellId){
        simpleDao.deleteEntity("SellWay_NS.deleteSellWayById",sellId);
    }

    public void updateSellWay(SellWay sellWay) {
        simpleDao.updateEntity("SellWay_NS.updateSellWay",sellWay);
    }

    public SellWay getSellWayById(int sellId) {
        return simpleDao.getEntity("SellWay_NS.getSellWayById",sellId);
    }
    
    /**
     * fanxin 2011-09-09
     * BuyNow() 方法  批量购买
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getSellWayByIdList(List sellIdList){
    	return simpleDao.getForList("SellWay_NS.getSellWayByIdList",sellIdList);
    }
    
    public List<SellWay> getSellWayList(QuerySellWayCondition querySellWayCondition) {
        return simpleDao.getForList("SellWay_NS.getSellWayList",querySellWayCondition);
    }
    public List<SellWayDTO> getSellWayInfoList(QuerySellWayCondition querySellWayCondition) {
    	
    	return simpleDao.getForList("SellWay_NS.getSellWayInfoList", querySellWayCondition);
    }


    /**
     * fanxin 2011--8-29
     * 获得售卖方式集合
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getSellWayDisproList(QuerySellWayCondition querySellWayCondition){
    	return simpleDao.getForList("SellWay_NS.getSellWayDisproList",querySellWayCondition);
    }
    /**
     * fanxin 2011-09-01
     * 获得相关课程下 "推荐" 售卖方式集合
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getTuiJianSellWay(QuerySellWayCondition querySellWayCondition){
    	return simpleDao.getForList("SellWay_NS.getTuiJianSellWay",querySellWayCondition);
    }
    
    //谢添加开始
    /**
	 * 获取优惠券id根据订单
	 */
	public String  getContractForCouponIdById(String contractId){
		String couponId=simpleDao.getEntity("FinanceCoupon_NS.getcontractForCouponIdById", contractId);
		return couponId;
	}
    /**
     * 更改支付状态
     * @param Id
     * @param couponTypeId
     */
    public void updateCouponForPayState(String Id,int couponTypeId){
		simpleDao.update("FinanceCoupon_NS.updateCouponForPayState", Id);
		simpleDao.update("FinanceCoupon_NS.updateCouponTypeForPaySumById", couponTypeId);
	}

    
  //根据couponId获取coupontypeid
  	public int GetParentIdBycouponId(String couponId){
  		int couponTypeId=simpleDao.getEntity("FinanceCoupon_NS.getCouponTypeIdByCouponId", couponId);
  		return couponTypeId;
  	}
  	//谢添加结束

	@Override
	public SellWay getSellWayByCusAndSellWayId(Map<String,Integer> paramMap) {
		return simpleDao.getEntity("SellWay_NS.getSellWayByCustomerAndSellWayId", paramMap);
	}
	
	public List<CourseDTO> getLearnCourseBySellWayId(int selId){
		return simpleDao.getForList("SellWay_NS.getLearnCourseBySellWayId", selId);
	}

	@Override
	public List<SellWay> getSellWayByCtractNO(String sellno) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("SellWay_NS.getSellWayByCtractNO", sellno);
	}

	@Override
	public int getPaidSellWayCountByCusId(int userId) {
		return simpleDao.getEntity("SellWay_NS.getPaidCountSellWayListByCusId", userId);
	}
}
