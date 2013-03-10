package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;

/**
 * TvdTvd管理接口
 * User: guoqiang.liu
 * Date: 2011-03-04
 */
public interface ITvdTvd {
    /**
     * 添加TvdTvd
     * @param tvdTvd 要添加的TvdTvd
     * @return id
     */
    public java.lang.Integer addTvdTvd(TvdTvd tvdTvd);

    /**
     * 根据id删除一个TvdTvd
     * @param id 要删除的id
     */
    public void delTvdTvdById(int id);

    /**
     * 修改TvdTvd
     * @param tvdTvd 要修改的TvdTvd
     */
    public void updateTvdTvd(TvdTvd tvdTvd);

    /**
     * 根据id获取单个TvdTvd对象
     * @param id 要查询的id
     * @return 年级
     */
    public TvdTvd getTvdTvdById(int id);

    /**
     * 根据条件获取TvdTvd列表
     * @param queryTvdTvdCondition 查询条件
     * @return 查询结果
     */
    public List<TvdTvd> getTvdTvdList(QueryTvdTvdCondition queryTvdTvdCondition);
}