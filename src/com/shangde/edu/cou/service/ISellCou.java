package com.shangde.edu.cou.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.dto.SellCouDTO;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.Coupontype;

/**
 * SellCou����ӿ�
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
public interface ISellCou {
    /**
     * ���SellCou
     * @param sellCou Ҫ��ӵ�SellCou
     * @return id
     */
    public java.lang.Integer addSellCou(SellCou sellCou);

    /**
     * ���idɾ��һ��SellCou
     * @param id Ҫɾ���id
     */
    public void delSellCouById(int id);

    /**
     * 通过售卖方式删除关联
     * @param sellId
     */
    public void delSellCouBySellId(int sellId);
    /**
     * �޸�SellCou
     * @param sellCou Ҫ�޸ĵ�SellCou
     */
    public void updateSellCou(SellCou sellCou);

    /**
     * ���id��ȡ����SellCou����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public SellCou getSellCouById(int id);

    /**
     * ��������ȡSellCou�б�
     * @param querySellCouCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<SellCou> getSellCouList(QuerySellCouCondition querySellCouCondition);
    /**
     * 通过courseID查找售卖方式下的课程详细信息
     * @param courseId
     * @return
     */
    public SellCouDTO getSellCouDTOByCourseId(QuerySellCouCondition querySellCouCondition);
    
    //谢添加
    /**
     * 获取优惠券
     * @param code
     * @return
     */
    public Coupon GetCouponByCode(String code);
    
    /**
     * 根据项目和售卖方式 判断是否存在
     * @param subjectId
     * @param sellId
     * @return
     */
    public boolean GetSellWayByCode(String subjectId, String sellId) ;
    
    /**
     * 根据id判断优惠券状态
     * @param Id
     * @return
     */
    public int getCouponState(String Id) ;
    
    /**
     * 更新优惠券状态
     * @param Id
     */
    public void updateCouponForState(String Id,int couponTypeId);
    
  //根据couponId获取coupontypeid
  	public int GetParentIdBycouponId(String couponId);
  	
  	 /**
     * 更改支付状态
     * @param Id
     * @param couponTypeId
     */
  	 public void updateCouponForPayState(String Id,int couponTypeId);
  	/**
	 * 在优惠券中加入订单
	 * @param map
	 */
     public void updateCouponForContractId(Map map);
     
     /**
      * 重置支付状态
      * @param Id
      * @param couponTypeId
      */
     public void updateCouponForStateReset(String Id,int couponTypeId);
     
     /**
 	 * 获取优惠券id根据订单
 	 */
 	public String  getContractForCouponIdById(String contractId);
 	/**
 	 * 获取优惠券
 	 * @param id
 	 * @return
 	 */
 	public Coupon getCouponById(int id);
 	
	/**
 	 * 根据售卖方式id获取项目id
 	 * @param sellId
 	 * @return
 	 */
 	public int getSubjectIdBySellId(int sellId);
 	
 	/**
 	 * 根据优惠券id获取类别
 	 * @param couponId
 	 * @return
 	 */
 	public Coupontype getCouponTypeByCouponId(int couponId);
 	//谢添加结束
    
}