package com.shangde.edu.cou.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.sys.domain.Subject;

/**
 * SellWay����ӿ�
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
public interface ISellWay {
    /**
     * ���SellWay
     * @param sellWay Ҫ��ӵ�SellWay
     * @return id
     */
    public java.lang.Integer addSellWay(SellWay sellWay);

    /**
     * ���idɾ��һ��SellWay
     * @param saleId Ҫɾ���id
     */
    public void delSellWayById(int sellId);

    /**
     * �޸�SellWay
     * @param sellWay Ҫ�޸ĵ�SellWay
     */
    public void updateSellWay(SellWay sellWay);

    /**
     * ���id��ȡ����SellWay����
     * @param saleId Ҫ��ѯ��id
     * @return �꼶
     */
    public SellWay getSellWayById(int sellId);

    /**
     * fanxin 2011-09-09
     * BuyNow() 方法  批量购买
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getSellWayByIdList(List sellIdList);
    
    /**
     * ��������ȡSellWay�б�
     * @param querySellWayCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<SellWay> getSellWayList(QuerySellWayCondition querySellWayCondition);
    /**
     * 获得售卖方式信息，包括专业名
     * @param querySellWayCondition
     * @return
     */
    public List<SellWayDTO> getSellWayInfoList(QuerySellWayCondition querySellWayCondition);
    

    /**
     * fanxin 2011--8-29
     * 获得售卖方式集合
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getSellWayDisproList(QuerySellWayCondition querySellWayCondition);
    
    /**
     * fanxin 2011-09-01
     * 获得相关课程下 "推荐" 售卖方式集合
     * @param querySellWayCondition
     * @return
     */ 
    public List<SellWay> getTuiJianSellWay(QuerySellWayCondition querySellWayCondition);
    
	public String  getContractForCouponIdById(String contractId);
 
    public void updateCouponForPayState(String Id,int couponTypeId);
    
    public int GetParentIdBycouponId(String couponId);
    
    /**
     * 通过用户id与sellWayId 得到SellWay
     * @param paramMap
     * @return
     * Author:Yangning
     * CreateDate:2012-1-6
     */
    public SellWay getSellWayByCusAndSellWayId(Map<String,Integer> paramMap);
    
    /**
     * 功能:通过sellway得到所有知识点
     * @param condition
     * @return
     * Author:Yangning
     * CreateDate:2012-2-14
     */
    List<CourseDTO> getLearnCourseBySellWayId(int sellId);
    
    /**
     * 功能：根据订单号查找所有售卖方式
     * @param sellno
     * @return
     * Author:Yangning
     * CreateDate:2012-2-27
     */
   List<SellWay> getSellWayByCtractNO(String sellno);
   
   
   /**
    * 功能:根据用户id查找所有购买的售卖方式数量
    * @param userId
    * @return
    * Author:Yangning
    * CreateDate:2012-3-6
    */
   int getPaidSellWayCountByCusId(int userId);

}