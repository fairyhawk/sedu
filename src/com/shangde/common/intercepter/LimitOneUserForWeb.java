package com.shangde.common.intercepter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.danga.MemCached.MemCachedClient;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.StringUtil;
/**
 * 功能:限制用户同时只能与同一ip地址登录
 * @author Yangning
 *
 */

public class LimitOneUserForWeb extends MethodFilterInterceptor{
	
	private static final long serialVersionUID = 5622350756953568982L;
	//用户唯一ukey
	//public static final String COOKIE_USER_INFO="sedu.cookie.ukey"; 
	
	public static final String COOKIE_REMEMTER_ME = "remeberMe";
	
	private static final Logger logger = Logger.getLogger(LimitOneUserForWeb.class);
	private MemCachedClient memcachedClient;

	public MemCachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	protected String doIntercept(ActionInvocation appInvoke) throws Exception {
		ActionContext actionContext = appInvoke.getInvocationContext();   
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse respone = (HttpServletResponse)actionContext.get(StrutsStatics.HTTP_RESPONSE);
		if(memcachedClient == null){
			return appInvoke.invoke();
		}
		try{
			//通过uid得到当前请求用户的ukeyCookie
			String cookieKey = CookieHandler.getCookieValueByName(request,LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);  
			String uid = "";
			String uKey = "";
			if(!StringUtil.isNullString(cookieKey)){
				String str[]  = cookieKey.split(",");
				uid = str[0];
			    uKey = str[2];
			    if(uid != null && uid.trim().length() > 0 && uKey!= null && uKey.trim().length() > 0){
				    	String memUkey = (String)memcachedClient.get(uid);
				    	//ukey存在，cache中不存在允许通过,并写入memcached(cache服务器重启动)
				    	if(memUkey == null){
				    		//设置缓存时间为6小时
				    		memcachedClient.set(uid, uKey,new Date(System.currentTimeMillis()+21600000));
				    		return appInvoke.invoke();
				    	//cache中存在,与cookie中的uKey相同,允许通过
				    	}else if(memUkey.equals(uKey)){
				    		return appInvoke.invoke();
				    	}
				    	 //cookie中与cache中不同,删除用户cookie让其重新登录,被迫下线
						CookieHandler.deleteCookieByName(request, respone, LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
						CookieHandler.deleteCookieByName(request, respone, COOKIE_REMEMTER_ME);
						return "reLogin";
					}
			}
			return appInvoke.invoke();
		}catch(Exception e){
			logger.error("LimitOneUserForWeb.doIntercept");
		}
		return appInvoke.invoke();
	}
}
