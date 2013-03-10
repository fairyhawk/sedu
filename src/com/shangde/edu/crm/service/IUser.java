package com.shangde.edu.crm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.shangde.edu.crm.condition.QueryUserCondition;
import com.shangde.edu.crm.domain.ChanceUser;
import com.shangde.edu.cus.domain.Customer;

/**
 * User管理接口
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
public interface IUser {
    /**
     * 添加User
     * @param user 要添加的User
     * @return id
     */
    public java.lang.Integer addUser(ChanceUser user);

    /**
     * 根据id删除一个User
     * @param id 要删除的id
     */
    public void delUserById(int id);

    /**
     * 修改User
     * @param user 要修改的User
     */
    public void updateUser(ChanceUser user);

    /**
     * 根据id获取单个User对象
     * @param id 要查询的id
     * @return 年级
     */
    public ChanceUser getUserById(int id);

    /**
     * 根据条件获取User列表
     * @param queryUserCondition 查询条件
     * @return 查询结果
     */
    public List<ChanceUser> getUserList(QueryUserCondition queryUserCondition);
    
    /**
     * 添加用户库记录
     * @param customer
     * @return
     */
    public int [] addUser(Customer customer);
    
    /**
     * 根据规则获取符合条件 销售人员
     * @return
     */
    public int getUserByGuiZe(int subjectId);
    
    /**
     *   //根据学员id获取crmUser
     * @param cusId
     * @return
     */
    public ChanceUser getUserByCusId(int cusId);
    
    /**
     * 更新用户库实体
     * @param customer
     * @return
     */
    public int updateChanceUser(Customer customer,ChanceUser user);
    
    /**
     * 根据手机号查询乐语用户
     * @param mobile
     * @return
     */
    public ChanceUser getUserByPhoneForLY(String mobile);
    
    /**
     * 获取意外 未分配的学员
     * @return
     */
   public List<Integer> getYiWaiCus();
   
   /**
    * 最终找不到坐席的补齐
    * @param subjectId
    * @return
    */
   public int getBuQi(int subjectId);
   
   /**
    * 获取一段时间内注册的学员
    * @return
    */
   public List<Integer> getCusTime(String all,String begin,String end);
   
   /**
    * 获取延时时间
    * @return
    */
   public java.util.HashMap  getTime();
   
   /**
    * 更新用户状态为已指派机会
    * @param cus_id
    */
   public void updateCusState(int cus_id);
   
   /**
    * 更新最新取的定时时间
    */
   public void  updateCrmTime();

   /**
    * 手机号验证
    * wd
    */
   public List<ChanceUser> getUserByPhone(String phone);
   
   /**
    * 更新message
    * wd
    */
   public void updateMes(Map<String,String> mes);
}