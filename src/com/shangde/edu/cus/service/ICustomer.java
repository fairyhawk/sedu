package com.shangde.edu.cus.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.CusRankInfo;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.SubjectCustomerDTO;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.SimpleCustomerDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * Customer管理接口
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
public interface ICustomer extends Serializable {
    /**
     * 添加Customer
     * @param customer 要添加的Customer
     * @return id
     */
    public java.lang.Integer addCustomer(Customer customer);

    /**
     * 根据id删除一个Customer
     * @param cusId 要删除的id
     */
    public void delCustomerById(int cusId);

    /**
     * 修改Customer
     * @param customer 要修改的Customer
     */
    public void updateCustomer(Customer customer);
    /**
     * 更新用户签名信息
     * @param cusId
     * @param content
     */
    public void updateCusSignature(int cusId,String content);

    /**
     * 根据id获取单个Customer对象
     * @param cusId 要查询的id
     * @return 年级
     */
    public Customer getCustomerById(int cusId);

    /**
     * 根据条件获取Customer列表
     * @param queryCustomerCondition 查询条件
     * @return 查询结果
     */
    public List<Customer> getCustomerList(QueryCustomerCondition queryCustomerCondition);

    /**
     * 分页查询
     * @param queryCustomerCondition 查询条件
     * @return 分页结果
     */
	public PageResult getCustomerListByCondition(
			PageQuery queryCustomerCondition);

	/**
	 * 检查原密码是否正确
	 * @param customer 学员
	 * @return 是否正确
	 */
	public boolean checkOldPwd(Customer customer);

	/**
	 * 登录并返回学员id
	 * @param customer 用户名，密码
	 * @return 学员id
	 */
	public Integer getUIDByLogin(Customer customer);

	/**
	 * 检查电子邮件是否可用
	 * @param email 邮件
	 * @return 是否可用
	 */
	public boolean checkEmail(String email);
	/**
	 * 验证手机号是否已经注册过
	 * @param moblie
	 * @return
	 */
	public boolean checkMoblie(String moblie);
	/**
	 * 根据邮箱查询学员
	 * @param email 邮箱
	 */
	public Customer getCustomerByEmail(String email);
	
	/*
	 * 根据手机号查询学员
	 * @param moblie 手机号
	 */
	public List<Customer> getCustomerListByMobile(QueryCustomerCondition queryCustomerCondition);
	
	
	/**
	 * 根据用户id获取用户购买的课程与哪些subjectid对应
	 * @param userId
	 * @return
	 */
	public List<Subject> getSubjectListByCus(int userId);
	
	/**
	 * 判断是否可以试听
	 * @param userId
	 * @return
	 */
	public int isComplete(int userId);

	/**
	 * 发送注册成功email通知
	 * @param customer
	 */
	public void sendRegEmail(Customer customer) throws IOException ;

	/**
	 * 生成9位随机数字密码
	 * @return
	 */
	public String genericRandomPwd();

	/**
	 * 发送邮件通知新密码为多少（找回秘密）
	 * @param customer
	 */
	public void sendForgotPwdEmail(Customer customer) throws IOException;

	/**
	 * 生成6位随机验证码
	 * @return
	 */
	public String genericConfirmCode();
	
	/**
	 * 统计每日注册量
	 * @return
	 */
	public int getRegistNumber(QueryCustomerCondition queryCustomerCondition);
	public List<SubjectCustomerDTO> getRegistNumberList(QueryCustomerCondition queryCustomerCondition);
	/**
	 * 统计有几个月不重复
	 * @return
	 */
	public List getMonthList();
	/**
	 * 统计每个月的注册量
	 * @return
	 */
	public int getMonthRegistNumber(String month);
	/**
	 * 统计每天的注册量,月下面的日期
	 * @return
	 */
	public List getMonthDay(String month);
	
	public List getDay(String dateDay);
	
	public List addBathCustomer(List cusList) throws SQLException;

	/**
	 * 查看学员
	 * @param cusId
	 * @return
	 */
	public Customer getCustomerByView(int cusId);
	
	/**
	 * @author chenshuai
	 * 功能：查询简单的用户DTO信息集合
	 * @param args
	 * @param queryCustomerCondition
	 * @return
	 */
	public List<SimpleCustomerDTO> getSimpleCustomerDTOListByCondition(QueryCustomerCondition queryCustomerCondition);
	
	//修复订单
	public void fixFinance();
	//修复订单
	public void cusTypeFix();
		
	/**
	 * 删除体验（临时）学员的体验（临时）数据
	 * @param cusId
	 */
	public void recoverTempCustomer(int cusId);

	/**
	 * 删除体验（临时）学员除了传入订单的的其他体验（临时）数据
	 * @param cusId
	 * @param ctId
	 */
	public void recoverTempCustomer(int cusId, int ctId);
	
	/**
	 * @author wangzheng
	 * 功能：查询学员统计
	 * @param subjectId 专业ID
	 * @param cusType 学员注册类型
	 */
	public Integer getCustomerCount(QueryCustomerCondition queryCustomerCondition);
	
	
	/**
     * 修改Customer 的newerflag 新手引导用
     * @param customer 要修改的Customer
     */
    public void updateCustomerNewerflag(Customer customer);

	/**
	 * 范昕 2011-08-03
     * 修改Customer 的昵称
     * @param customer 要修改的Customer
     */
	public void updateCustomerName(Customer customer);
    
    /**
     * 获取收件人地址
     * @param cusId
     * @return
     */
    public List<AddressDTO> GetAddrByCusId(int cusId);
    
    
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-10 16:49:16
     */
    public int getCustomerRegNum(QueryCustomerCondition queryCustomerCondition);
    
    /**
     *   张聚强
     * 导出EXCEL时用
     * Date : 2011-10-9 16:49:16
     */
    public List<CustomerWithConSizeDTO> getCustomerWithConSizeDTOList(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * 修改学员的所属项目，注册时未选择专业的学员用
     * @param customer
     * @return
     */
    public boolean updateCustomerSubject(Customer customer);
    
    /**
     * 根据项目名称获得同班同学
     * @param subjectId 项目ID
     * @return 总和
     */
    public int getCustomerClassmate(int subjectId);
    
    public Date getCustomerRegTimeById(int cusId);
    
    public Integer checkFreezeStatus(int cusId);
    
    /**
	 * 根CustomerId查询subject信息
	 */
	public Subject getSubjectByCusId(int cusId);
    /**
     * 根据用户ID和获取数据
     * @param cusId 
     * @param type
     * @return 
     */
    public List<CusRankInfo> getCusRandByCusId(int cusId);
    
}