package com.shangde.edu.card.action;

import org.apache.log4j.Logger;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardCourseCondition;
import com.shangde.edu.card.domain.*;
import com.shangde.edu.card.service.*;

/**
 * 优惠卡，充值卡，课程卡都通过该action来管理
 * @author dks
 *
 */
public class CardMainAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CardMainAction.class); 
	private CardMainService cardMainService;
	private CardMain cardMain;
	private CardCourseService cardCourseService;//课程卡service
	private Integer location;
	private CardCourse cardCourse;
	private QueryCardCourseCondition  queryCardCourseCondition;
	private PageResult cardCoursePage;//分页对象，用于课程卡记录显示，由于页面中已经存在了订单的列表pageResult，所以在此需重新定义，避免冲突
	public PageResult getCardCoursePage() {
		return cardCoursePage;
	}
	public void setCardCoursePage(PageResult cardCoursePage) {
		this.cardCoursePage = cardCoursePage;
	}
	public QueryCardCourseCondition getQueryCardCourseCondition() {
		return queryCardCourseCondition;
	}
	public void setQueryCardCourseCondition(
			QueryCardCourseCondition queryCardCourseCondition) {
		this.queryCardCourseCondition = queryCardCourseCondition;
	}
	public CardCourse getCardCourse() {
		return cardCourse;
	}
	public void setCardCourse(CardCourse cardCourse) {
		this.cardCourse = cardCourse;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	
	

	public CardCourseService getCardCourseService() {
		return cardCourseService;
	}
	public void setCardCourseService(CardCourseService cardCourseService) {
		this.cardCourseService = cardCourseService;
	}
	
	public CardMainService getCardMainService() {
		return cardMainService;
	}
	public CardMain getCardMain() {
		return cardMain;
	}
	public void setCardMain(CardMain cardMain) {
		this.cardMain = cardMain;
	}
	public void setCardMainService(CardMainService cardMainService) {
		this.cardMainService = cardMainService;
	}
	/**
	 * 进入新建课程卡页面
	 * @return
	 */
	public String getCourseCardPage(){
		return "courseCardInfo";
	}

	public String getCardCourseInfo(){
		//查询课程卡
		try{
			setCardCoursePage(cardCourseService.getCardCourseList(queryCardCourseCondition,getLoginUserId()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("CardMainAction.getCardInfo", e);
			return ERROR;
		}
		return "cardInfo";
	}
	/**
	 * 激活课程卡
	 * @return
	 */
	public String activateCardCourse(){
		try{
			setCardCourse(cardCourseService.checkCardCourse(cardCourse));
			//  如果密码和用户名正确,再进行激活
			if(cardCourse!=null&&cardCourse.getCardCourseStatus()==1&&cardCourse.getCardCourseUseStatus()==2){
				setResult(new Result<String>(false, "", "", cardMainService.updateActivateCardCourse(cardCourse,getLoginUserId())));
			}else{
				if(cardCourse==null){
					setResult(new Result<String>(false, "", "", "passwordError"));
					return "activateCardCourse";
				}
				if(cardCourse.getCardCourseStatus()==2){
					setResult(new Result<String>(false, "", "", "dontActivate"));
				}
				if(cardCourse.getCardCourseUseStatus()==1){
					setResult(new Result<String>(false, "", "", "alreadyUse"));
				}
				if(cardCourse.getCardCourseStatus()==3){
					setResult(new Result<String>(false, "", "", "outDate"));
				}
				if(cardCourse.getCardCourseStatus()==4){
					setResult(new Result<String>(false, "", "", "alreadyAbolish"));
				}
			}
		}catch(Exception e){
			logger.error("CardMainAction.activateCardCourse", e);
			return ERROR;
		}
		return "activateCardCourse";
	}
}
