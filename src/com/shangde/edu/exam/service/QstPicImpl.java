package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.QstPic;
import com.shangde.edu.exam.condition.QueryQstPicCondition;
import com.shangde.common.service.BaseService;

/**
 * QstPic�������ʵ����?
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class QstPicImpl extends BaseService implements IQstPic{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addQstPic(QstPic qstPic) {
		return simpleDao.createEntity("QstPic_NS.createQstPic",qstPic);
    }

    public void delQstPicById(int picId){
        simpleDao.deleteEntity("QstPic_NS.deleteQstPicById",picId);
    }

    public void updateQstPic(QstPic qstPic) {
        simpleDao.updateEntity("QstPic_NS.updateQstPic",qstPic);
    }

    public QstPic getQstPicById(int picId) {
        return simpleDao.getEntity("QstPic_NS.getQstPicById",picId);
    }

    public List<QstPic> getQstPicList(QueryQstPicCondition queryQstPicCondition) {
        return simpleDao.getForList("QstPic_NS.getQstPicList",queryQstPicCondition);
    }
}
