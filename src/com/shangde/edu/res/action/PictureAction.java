package com.shangde.edu.res.action;

import com.shangde.common.action.CommonAction;
import com.shangde.common.exception.CommException;
import com.shangde.edu.res.condition.QueryPictureCondition;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.service.IPicture;

/**  
 * 图片管理action
 * @author miaoshusen
 * @version 1.0
*/
public class PictureAction extends CommonAction {

	private Picture picture;
	private IPicture pictureService;
	private QueryPictureCondition queryPictureCondition;

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public IPicture getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPicture pictureService) {
		this.pictureService = pictureService;
	}

	public QueryPictureCondition getQueryPictureCondition() {
		if (queryPictureCondition == null) {
			queryPictureCondition = new QueryPictureCondition();
			
		}
		return queryPictureCondition;
	}

	public void setQueryPictureCondition(
			QueryPictureCondition queryPictureCondition) {
		this.queryPictureCondition = queryPictureCondition;
	}

	public String getPictureList() throws CommException {
		setPage(this.pictureService.getPictureList(getQueryPictureCondition()));
		setPageUrlParms();
		return "listPicture";

	}

}
