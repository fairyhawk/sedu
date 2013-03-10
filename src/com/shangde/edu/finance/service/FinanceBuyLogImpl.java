package com.shangde.edu.finance.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import com.danga.MemCached.MemCachedClient;
import com.shangde.common.service.BaseService;

/**
 * 
 * @ClassName: FinanceBuyLogImpl
 * @Description: TODO(处理前台用户下订单过程步骤记录的service实现类)
 * @author shixiaofeng@sunland.org.cn
 * @date 2012-6-12 上午11:05:10
 * 
 */
public class FinanceBuyLogImpl extends BaseService implements IFinanceBuyLog {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(FinanceBuyLogImpl.class);
	/**
	 * memcachedClient服务
	 */
	private MemCachedClient memcachedClient;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: addFinanceBuyLog
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param projectId
	 * @param status
	 * @return
	 * @see com.shangde.edu.finance.service.IFinanceBuyLog#addFinanceBuyLog(int,
	 *      int)
	 */
	public int addFinanceBuyLog(int projectId, int status) {
		Integer count = 1;
		try {
			logger.debug("====FinanceBuyLogImpl.addFinanceBuyLog=项目id="
					+ projectId + ",触发状态status=" + status);
			String key = "Finance_" + projectId + "_" + status + "_"
					+ this.formatDate("yyyyMMddHH", new Date());
			logger
					.debug("====FinanceBuyLogImpl.addFinanceBuyLog=要去memcache中判断的key="
							+ key);
			Object obj = memcachedClient.get(key);
			if (obj == null) {
				memcachedClient.set(key, count,new Date(System.currentTimeMillis()+86400000));
			} else {
				count = (Integer) obj;
				memcachedClient.replace(key, count + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("====FinanceBuyLogImpl.addFinanceBuyLog=出现异常：message="
					+ e.getMessage());
			return -1;
		}
		return count;
	}

	public MemCachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * 
	 * @Title: formatDate
	 * @Description: TODO(将日期格式化为字符串)
	 * @param
	 * @param format
	 * @param
	 * @param date
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String formatDate(String format, Date date) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);

	}
}
