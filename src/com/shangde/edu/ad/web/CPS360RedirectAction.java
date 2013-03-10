package com.shangde.edu.ad.web;

import java.net.URL;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.MD5;
import com.shangde.common.util.StringUtil;

/**
 * 360CPS跳转接口的实现。
 * 
 * @author ZHENG QIANG
 */
public class CPS360RedirectAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CPS360RedirectAction.class);

	// 项目路径
	private static String SUBJECT_PATH = "/cpa";

	// 推广来源
	private static String WEB_FROM = "360cps";

	// 推广名称
	private static String WEB_AGENT = "";

	// 密钥 - 由360提供
	private static String CP_KEY = "8E8889EBB2DDD534E9E6BEE667FA9F77";

	// 合作编号 - 由360提供
	private static String BID = "20120458";

	// 默认跳转地址
	private static String DEFAULT_GO_URL = "http://hignso.cn" + SUBJECT_PATH + "?webFrom=" + WEB_FROM + "&webAgent=" + WEB_AGENT;

	// 网站域名
	private static String COOKIE_DOMAIN = "highso.cn";

	
	// Cookie的有效期长
	private static int RD = 30;

	// 错误报告地址（如果获取360端POST过来的参数中的签名验证失败，则反馈给360端）
	private static String FAILED_URL = "http://open.union.360.cn/gofailed";

	// 接受请求超时时间（单位秒）
	private static long TIMEOUT = 900;

	private String bid;
	private String qihoo_id;
	private String url;
	private String from_url;
	private String active_time;
	private String ext;
	private String qid;
	private String qmail;
	private String qname;
	private String sign;

	/** 实现跳转接口 */
	public String redirect() {
		try {
			// 360使用Unix时间戳，单位是秒
			long now = System.currentTimeMillis() / 1000;
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			// 获取360端POST过来的参数
			String bid = getBid();
			String qihooId = getQihoo_id();
			String url = getUrl();
			String fromUrl = getFrom_url();
			String activeTime = getActive_time();
			String ext = getExt();
			String qid = getQid();
			String qmail = getQmail();
			String qname = getQname();
			String sign = getSign();
			// 将参数存入Cookie中
			int aMonth = 24 * 60 * 60 * RD;// 单位是秒
			// 以下3个cookie的键沿用“亿起发”的规则
			Cookie qidCookie = initCookie("WI", qid, COOKIE_DOMAIN, "/", aMonth);
			Cookie qihooIdCookie = initCookie("CID", qihooId, COOKIE_DOMAIN, "/", aMonth);
			Cookie extCookie = initCookie("SRC", ext, COOKIE_DOMAIN, "/", aMonth);
			response.addCookie(qidCookie);
			response.addCookie(qihooIdCookie);
			response.addCookie(extCookie);
			// 签名验证
			String signCheck = MD5.getMD5(BID + "#" + activeTime + "#" + CP_KEY + "#" + qid + "#" + qmail + "#" + qname);
			if (!checkActiveTime(activeTime, now) || !signCheck.equals(sign)) {
				// 签名验证失败，则向360服务器发送信息
				String failedURL = FAILED_URL;
				failedURL += "?bid=" + BID;
				failedURL += "&active_time=" + now;
				failedURL += "&sign=" + MD5.getMD5(BID + "#" + now + "#" + CP_KEY);
				failedURL += "&pre_bid=" + bid;
				failedURL += "&pre_active_time=" + activeTime;
				failedURL += "&pre_sign=" + sign;
				failedURL += "&qid=" + qid;
				failedURL += "&qname=" + qname;
				failedURL += "&qmail=" + qmail;
				failedURL += "&from_url=" + fromUrl;
				failedURL += "&from_ip=" + getFromIp(request);
				URL u = new URL(failedURL);
				u.openConnection();
				logger.error("360CPS参数签名无效。");
			}
			if (StringUtil.isNullString(url)) {
				response.sendRedirect(DEFAULT_GO_URL);
			} else {
				response.sendRedirect(url);
			}
		} catch (Exception e) {
			logger.error("CPS360Action.redirect", e);
		}
		return null;
	}

	/**
	 * 实例化Cookie对象。
	 * 
	 * @param name 键
	 * @param value 值
	 * @param domain 域名
	 * @param path 路径
	 * @param maxAge 有效期（秒）
	 * 
	 * @return Cookie对象
	 */
	private Cookie initCookie(String name, String value, String domain, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	/** 有效时间验证 */
	private boolean checkActiveTime(String activeTime, long now) {
		long actTime = Long.parseLong(activeTime);
		if (Math.abs(now - actTime) > TIMEOUT) {
			return false;
		}
		return true;
	}

	/** 获取请求的IP地址 */
	private String getFromIp(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	// getters and setters.

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getQihoo_id() {
		return qihoo_id;
	}

	public void setQihoo_id(String qihoo_id) {
		this.qihoo_id = qihoo_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFrom_url() {
		return from_url;
	}

	public void setFrom_url(String from_url) {
		this.from_url = from_url;
	}

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQmail() {
		return qmail;
	}

	public void setQmail(String qmail) {
		this.qmail = qmail;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
