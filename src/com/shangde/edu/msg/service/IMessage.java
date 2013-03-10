package com.shangde.edu.msg.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.condition.QueryMessageCondition;

/**
 * Message����ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-29
 */
public interface IMessage {
    /**
     * ���Message
     * @param message Ҫ��ӵ�Message
     * @return id
     */
    public java.lang.Integer addMessage(Message message);

    /**
     * ���idɾ��һ��Message
     * @param msgId Ҫɾ���id
     */
    public void delMessageById(int msgId);

    /**
     * �޸�Message
     * @param message Ҫ�޸ĵ�Message
     */
    public void updateMessage(Message message);

    /**
     * ���id��ȡ����Message����
     * @param msgId Ҫ��ѯ��id
     * @return �꼶
     */
    public Message getMessageById(int msgId);

    /**
     * ��������ȡMessage�б�
     * @param queryMessageCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Message> getMessageList(QueryMessageCondition queryMessageCondition);
    
    /**
     * @author chenshuai
     * 功能：消息分页管理
     * @param args
     * @param queryMessageCondition
     * @return
     */
    public PageResult getMsgListByConditon(QueryMessageCondition queryMessageCondition);
    
    /**
     * @author cxs
     * 功能：按关键字查询消息
     * @param args
     * @param key
     * @return
     */
    public Message getMessageByKey(String key);
    /**
     * @author zhangjuqiang
     * 功能：按用戶意見ID刪除信息
     * @prama int
     * @prama id
     */
    public void deleteMessageByCmtIdAndType(Map args);
    /**
     * @author zhangjuqiang
     * 功能：根據內容ID查找消息ID,查詢用戶提示信息表時用。
     * 
     */
    public List<Message> getMsgIdByCmtIdAndType(Map args);
    
    public int getMsgIdByRepIdAndType(Map args);
    /**
     * @author zhangjuqiang
     * 功能：根据用户ID和版块来源查询信息总数
     * @param receiverId:接收用户的ID
     * @param type:来源版块标识
     */
    public int getMsgCountByUserIdAndType(Map args);
}