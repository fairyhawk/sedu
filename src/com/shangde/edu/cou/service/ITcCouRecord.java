package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.TcCouRecord;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;

/**
 * TcCouRecord管理接口
 * User: guoqiang.liu
 * Date: 2010-08-05
 */
public interface ITcCouRecord {
    /**
     * 添加TcCouRecord
     * @param tcCouRecord 要添加的TcCouRecord
     * @return id
     */
    public java.lang.Integer addTcCouRecord(TcCouRecord tcCouRecord);

    /**
     * 根据id删除一个TcCouRecord
     * @param id 要删除的id
     */
    public void delTcCouRecordById(int id);

    /**
     * 修改TcCouRecord
     * @param tcCouRecord 要修改的TcCouRecord
     */
    public void updateTcCouRecord(TcCouRecord tcCouRecord);

    /**
     * 根据id获取单个TcCouRecord对象
     * @param id 要查询的id
     * @return 年级
     */
    public TcCouRecord getTcCouRecordById(int id);

    /**
     * 根据条件获取TcCouRecord列表
     * @param queryTcCouRecordCondition 查询条件
     * @return 查询结果
     */
    public List<TcCouRecord> getTcCouRecordList(QueryTcCouRecordCondition queryTcCouRecordCondition);
}