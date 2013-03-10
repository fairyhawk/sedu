package com.shangde.edu.sys.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.edu.sys.condition.QueryWebFromAgentCondition;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.domain.WebFromAgent;
import com.shangde.edu.sys.service.IWebFromAgent;

@SuppressWarnings("serial")
public class WebFromAgentAction extends CommonAction {
	/**
	 * WebFromAgent主键
	 */
	private int id;
	private String webFrom;
	private String webAgent;
	private int typeId;
	private String webFromInfo;
	private String webAgentInfo;
	private Date createTime;
	private Date updateTime;
	private String loginName;
	private WebFromAgent webFromAgent;
	private List<WebFromAgent> useridWebList;
	private QueryWebFromAgentCondition queryWebFromAgentCondition;
	private int isNull=0;

	
	public int getIsNull() {
		return isNull;
	}

	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}
	/**
	 * 接口类实体
	 */
	private IWebFromAgent webFromAgentService;
	/** log对象 */
	private Log logger = LogFactory.getLog(getClass());
	/**
	 *  Author:yanbaixi
	 * 添加webFromAgent时根据webFrom和webAgent查询判断
	 */
	public String toAddFromAgentCheck(){
		try{
			this.getQueryWebFromAgentCondition().setWebFrom(getWebFrom());
			this.getQueryWebFromAgentCondition().setWebAgent(getWebAgent());
			webFromAgent = webFromAgentService.getWebFromAgentBywebFromAndwebAgent(this.getQueryWebFromAgentCondition());
			JSONArray jsAss = JSONArray.fromObject(webFromAgent);  //json处理
			this.setResult(new Result<Object>(true, jsAss.toString(), null,null));

		}catch(Exception e){
			logger.error("查询判断出错");
		}
			return "json";
	}
	/**
	 * Author:yanbaixi
	 * 修改webFromAgent时根据webFrom和webAgent查询判断
	 * @return
	 */
	public String toUpdateFromAgentCheck(){
		try{
			this.getQueryWebFromAgentCondition().setWebFrom(getWebFrom());
			this.getQueryWebFromAgentCondition().setWebAgent(getWebAgent());
			this.getQueryWebFromAgentCondition().setId(getId());
			webFromAgent = webFromAgentService.getWebFromAgentBywebFromAndwebAgentNotid(this.getQueryWebFromAgentCondition());
			JSONArray jsAss = JSONArray.fromObject(webFromAgent);  //json处理
			this.setResult(new Result<Object>(true, jsAss.toString(), null,null));

		}catch(Exception e){
			logger.error("查询判断出错");
		}
			return "json";
	}
	
	/**
	 * 添加webFromAgent
	 * Author:yanbaixi
	 */
	public String createWebFromAgent(){
		try{
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			WebFromAgent newwebFromAgent = new WebFromAgent();
			newwebFromAgent.setWebFrom(getWebFrom());
			newwebFromAgent.setWebFromInfo(getWebFromInfo());
			newwebFromAgent.setWebAgent(getWebAgent());
			newwebFromAgent.setWebAgentInfo(getWebAgentInfo());
			newwebFromAgent.setTypeId(getTypeId());
			newwebFromAgent.setLoginName(users.getLoginName());
			webFromAgentService.addWebFromAgent(newwebFromAgent);
		}catch(Exception e){
			logger.error(e);
		}
		return "addFromSuccess";
	}
	/**
	 * Author:yanbaixi
	 * 删除webfromAgent
	 * @return
	 */
	public String deleteWebFromAgentById(){
		try{
			int webFromId = getId();
			webFromAgentService.deleteWebFromAgentById(webFromId);
		}catch(Exception e){
			logger.error("没有删除");
		}
		return "deleteSucces";
	}
	/**
	 * Author:yanbaixi
	 * 根据id查询webfromAgent信息
	 * @return
	 */
	public String getWebFromAgentById(){
		try{
			int webFromId = getId();
		    webFromAgent = webFromAgentService.getWebFromAgentById(webFromId);
		}catch(Exception e){
			logger.error("id查询失败");
			e.printStackTrace();
		}
		return "getWebFromAgentById";
	}
	/**
	 * Author:yanbaixi
	 * 分页显示查询全部的WebfromAgent信息
	 * @return
	 */
	public String getAllListCountPage() throws ParseException{
		try{
			//webFromAgentTJ();
			this.getQueryWebFromAgentCondition().setPageSize(20);
			PageResult pageResult = webFromAgentService.getCountWebFromAgentList(this.getQueryWebFromAgentCondition());
			setPage(pageResult);
			getPage().setPageSize(20);
			setPageUrlParms();
		}catch(Exception e){
			logger.error("分页出错");
			e.printStackTrace();
		}
		return "getAllList";
	}
	/**
	 * Author:yanbaixi
	 * 判断修改后的webFromAgent信息
	 * @return
	 */
	public String updateWebFromAgent(){
		try{
			WebFromAgent updateWebFromAgent = new WebFromAgent();
			updateWebFromAgent.setId(getId());
			updateWebFromAgent.setWebFrom(getWebFrom());
			updateWebFromAgent.setWebFromInfo(getWebFromInfo());
			updateWebFromAgent.setWebAgent(getWebAgent());
			updateWebFromAgent.setWebAgentInfo(getWebAgentInfo());
			updateWebFromAgent.setTypeId(getTypeId());
			updateWebFromAgent.setUpdateTime(new Date());
			webFromAgentService.updateWebFromAgentById(updateWebFromAgent);
		}catch(Exception e){
			logger.error("修改出错");
		}
		return "updateSuccess";
	}
	/**
	 * Author:yanbaixi
	 * 组合条件查询结果
	 * @return
	 */
	public String getWebFromAgentByTiaoJian(){
		try{
			webFromAgentTJ();
			PageResult pageResult = webFromAgentService.getWebFromAgentCountByTiaoJian(this.getQueryWebFromAgentCondition());
			setPage(pageResult);
			getPage().setPageSize(20);
			setPageUrlParms();
		}catch(Exception e){
			logger.error("条件查询出错",e);
		}
		
		return "getWebFromAgentByTiaoJian";
	}
	/**
	 * Author:yanbaixi
	 * 组合条件
	 * @return
	 */
	private void webFromAgentTJ(){
		try{
			if(getWebFrom() !=null&&!"".equals(getWebFrom())){
				this.getQueryWebFromAgentCondition().setWebFrom(getWebFrom());
			}
			if(getWebAgent() != null&&!"".equals(getWebAgent())){
				this.getQueryWebFromAgentCondition().setWebAgent(getWebAgent());
			}
			if(getTypeId()!=0){
				this.getQueryWebFromAgentCondition().setTypeId(getTypeId());
			}
		}catch(Exception e){
			logger.error("组合条件查询出错",e);
		}
		
	}
	
	public IWebFromAgent getWebFromAgentService() {
		return webFromAgentService;
	}

	public void setWebFromAgentService(IWebFromAgent webFromAgentService) {
		this.webFromAgentService = webFromAgentService;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public List<WebFromAgent> getUseridWebList() {
		return useridWebList;
	}
	public void setUseridWebList(List<WebFromAgent> useridWebList) {
		this.useridWebList = useridWebList;
	}
	
	public QueryWebFromAgentCondition getQueryWebFromAgentCondition() {
		if (queryWebFromAgentCondition ==null){
			queryWebFromAgentCondition = new QueryWebFromAgentCondition();
		}
		return queryWebFromAgentCondition;
	}
	public void setQueryWebFromAgentCondition(
			QueryWebFromAgentCondition queryWebFromAgentCondition) {
		this.queryWebFromAgentCondition = queryWebFromAgentCondition;
	}
	public void setWebFromInfo(String webFromInfo) {
		this.webFromInfo = webFromInfo;
	}
	public String getWebFromInfo() {
		return webFromInfo;
	}
	public void setWebAgentInfo(String webAgentInfo) {
		this.webAgentInfo = webAgentInfo;
	}
	public String getWebAgentInfo() {
		return webAgentInfo;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setWebFromAgent(WebFromAgent webFromAgent) {
		this.webFromAgent = webFromAgent;
	}
	public WebFromAgent getWebFromAgent() {
		return webFromAgent;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginName() {
		return loginName;
	}
	
	
}
