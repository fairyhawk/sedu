package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class VedioImpl extends BaseService implements IVedio{
    public java.lang.Integer addVedio(Vedio vedio) {
        return simpleDao.createEntity("Vedio_NS.createVedio",vedio);
    }

    public void delVedioById(int voId){
        simpleDao.deleteEntity("Vedio_NS.deleteVedioById",voId);
    }

    public void updateVedio(Vedio vedio) {
        simpleDao.updateEntity("Vedio_NS.updateVedio",vedio);
    }

    public Vedio getVedioById(int voId) {
        return simpleDao.getEntity("Vedio_NS.getVedioById",voId);
    }

    public PageResult getVedioList(QueryVedioCondition queryVedioCondition) {
         return simpleDao.getPageResult("Vedio_NS.getVedioList", "Vedio_NS.getVedioListCount", queryVedioCondition);
    	}

	public <Vedio>List getVedioByVgId(int vgId) {
		return simpleDao.getForList("Vedio_NS.getVedioByVgId", vgId);
		
	}
	
	public void deleteVedioByPointid(int pointId) {
		simpleDao.deleteEntity("Vedio_NS.deleteVedioByPointid", pointId);
	}
	
	public Vedio getVedioByPointid(int pointId){
		return simpleDao.getEntity("Vedio_NS.getVedioByPointid", pointId);
	}
}
