package com.shangde.edu.finance.domain;

public class WebCpsUrl{
	private String webFrom;
	private String url;
	private String param;
	private String createTimeFormat;
	private String payTimeFormat;
	public String getwebFrom() {
		return webFrom;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getWebFrom() {
		return webFrom;
	}
	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}
	public String getCreateTimeFormat() {
		return createTimeFormat;
	}
	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	public String getPayTimeFormat() {
		return payTimeFormat;
	}

	public void setPayTimeFormat(String payTimeFormat) {
		this.payTimeFormat = payTimeFormat;
	}
	
}