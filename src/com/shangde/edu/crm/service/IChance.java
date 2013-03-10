package com.shangde.edu.crm.service;

import java.util.List;

import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.sys.domain.Subject;

/**
 * Chance管理接口
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
public interface IChance {
    
    /**
     * 添加机会 谢金龙
     * @param customer
     * @param userId
     * @return
     */
    public void addChance(Customer customer,int crmUserId,int xiaoshouId,String type) ;
    
    /**
     * 根据学员手机获取座席id
     * @param phone
     * @return
     */
    public int getXSIdByChancePhone(String phone);
    
    /**
     * 获取指派机会
     * @param id
     * @return
     */
    public Chance getChanceById(int id);
    
    /**
	 * 获取未分配的机会 在过去的两小时中
	 * @return
	 */
	public List<Chance> getChanceNoAssign(String type,String begin,String end);
	
	 /**
     * 添加记录
     * @param userId
     * @param chanceId
     */
    public void addRecord(int xiaoshouId,int chanceId);
    
    /**
	 * 更新机会表
	 * @param chance
	 */
	public void updateChance(Chance chance);
	
	/**
	 * 添加机会表（针对Message）
	 * wd
	 */
	 public void addChc(Chance chance);
	//王超开始
	/**
     * @author 王超
     * 定时将过期机会转入机会库
     */
    public void updateCheckChance(QueryChanceCondition queryChanceCondition);
    
    /**
     * 检查已购买用户
     */
    public void updateCheckIsBuy();

	//王超结束
    
}