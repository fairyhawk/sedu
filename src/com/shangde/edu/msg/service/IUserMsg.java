package com.shangde.edu.msg.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseBeforeTodayCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.VideoCusInfo;
import com.shangde.edu.msg.condition.QueryUserMsgCondition;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.sys.domain.User;

/**
 * UserMsg����ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-29
 */
public interface IUserMsg {
    /**
     * ���UserMsg
     * @param userMsg Ҫ��ӵ�UserMsg
     * @return id
     */
    public java.lang.Integer addUserMsg(UserMsg userMsg);

    /**
     * ���idɾ��һ��UserMsg
     * @param id Ҫɾ���id
     */
    public void delUserMsgById(int id);
    
    /**
     * @author zhangjuqiang
     * 功能：根據消息ID,刪除發給用戶的提示信息
     */
    public void delUserMsgByMsgId(List<Integer> ids);

    /**
     * �޸�UserMsg
     * @param userMsg Ҫ�޸ĵ�UserMsg
     */
    public void updateUserMsg(UserMsg userMsg);

    /**
     * ���id��ȡ����UserMsg����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public UserMsg getUserMsgById(int id);

    /**
     * ��������ȡUserMsg�б�
     * @param queryUserMsgCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<UserMsg> getUserMsgList(QueryUserMsgCondition queryUserMsgCondition);
    
    /**
     * @author cxs
     * 功能：管理员群发信息
     * @param args
     * @param userList
     * @param msgId
     * @param senderId
     * @param senderType
     */
    public void sendMsgs(List<Customer> userList,final int msgId,final int senderId, final String senderName);
    
    /**
     * @author chenshuai
     * 功能：网站管理员发送信息给指定网站用户
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void adminerSendMsgToCutomer(User sender,int msgId,Customer receiver);
    
    /**
     * 根据用户相关信息判断是否已发送信息
     * @param cusId 用户ID
     * @param msgType 消息类型
     * @param sendTime 发送时间
     * @return
     */
    public boolean WhetherHaveCustomerMsg(int cusId,int msgType);
    
    /**
     * @author chenshuai
     * 功能：网站用户发送信息给指定网站用户
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void customerSendMsgToCutomer(Customer sender,int msgId,Customer receiver);
    
    /**
     * @author chenshuai
     * 功能：网站用户发送信息给网站管理员
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void customerSendMsgToAdminer(Customer sender,int msgId,User receiver);
    
    /**
     * @author chenshuai
     * 功能：消息分页管理
     * @param args
     * @param queryMessageCondition
     * @return
     */
    public PageResult getUserMsgListByConditon(QueryUserMsgCondition queryUserMsgCondition);
    
    /**
     * @author chenshuai
     * 功能：根据发送用户ID及用户信息ID获取消息数量
     * @param args
     * @param queryUserMsgCondition
     * @return
     */
    public int getMsgSizeByUserIdAndUserMsgId(QueryUserMsgCondition queryUserMsgCondition);
    
    /**
     * @author chenshuai
     * 功能：获取用户未读信息数量
     * @param args
     * @param userId
     * @return
     */
    public int getCountUnreadMsgs(int userId);
    
    /**
     * @author chenshuai
     * 功能：更新用户已读信息
     * @param args
     * @param userId
     */
    public void updateReadStatus(int userId);
    
    /**
     * @author chenshuai
     * 功能：获取第一条平台用户信息
     * @param args
     * @param queryUserMsgCondition
     * @return
     */
    public UserMsg getApplicationUserMsgByConditon(QueryUserMsgCondition queryUserMsgCondition);
    
    /**
	 * <br>
	 * <b>功能：插入课程更新的消息</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.019 <br>
	 */
    public void insertCourseMsgs(QueryCourseBeforeTodayCondition query);
    /**
	 * <br>
	 * <b>功能：查询当天发送的新增课程的消息</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.19 <br>
	 */
	public int searchAddCourseMsg(QueryCourseBeforeTodayCondition query);
	/**
     * 获取用户购买包下Top数据
     * @param packAgeId
     * @return
     */
    public List<VideoCusInfo> getPackageCourseTop(List<Integer> packAgeId);
	/**
	 * <br>
	 * <b>功能：查询当天新增的课程数</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.19 <br>
	 */
	public int getNewCourseNumInDay(int cusId);
}