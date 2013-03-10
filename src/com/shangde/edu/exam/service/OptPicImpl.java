package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.OptPic;
import com.shangde.edu.exam.condition.QueryOptPicCondition;
import com.shangde.common.service.BaseService;

/**
 * OptPicï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Êµï¿½ï¿½ï¿½ï¿?
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
@SuppressWarnings("unchecked")
public class OptPicImpl extends BaseService implements IOptPic{
    public java.lang.Integer addOptPic(OptPic optPic) {
return simpleDao.createEntity("OptPic_NS.createOptPic",optPic);
    }

    public void delOptPicById(int picId){
        simpleDao.deleteEntity("OptPic_NS.deleteOptPicById",picId);
    }

    public void updateOptPic(OptPic optPic) {
        simpleDao.updateEntity("OptPic_NS.updateOptPic",optPic);
    }

    public OptPic getOptPicById(int picId) {
        return simpleDao.getEntity("OptPic_NS.getOptPicById",picId);
    }

    public List<OptPic> getOptPicList(QueryOptPicCondition queryOptPicCondition) {
        return simpleDao.getForList("OptPic_NS.getOptPicList",queryOptPicCondition);
    }
}
