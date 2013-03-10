package exp.vo;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;
import com.shangde.edu.cus.util.CusUtil;

public class TestSpring {
	private static ApplicationContext ctx = null;
	static {
		ctx = new FileSystemXmlApplicationContext(
				"classpath:config/spring/applicationContext.xml");
	}

	public static void main(String[] args) {
		/**
		 * memcachedClient服务
		 */
		 MemCachedClient memcachedClient=
				
		(MemCachedClient) ctx.getBean("memcachedClient");
//		 memcachedClient.set(CusUtil.getCusVideoKey(subjectId),
//					notBuyInfo, new Date(System.currentTimeMillis()+21600000));
		System.out.println(memcachedClient.get("VIDEO_7"));
		System.out.println(memcachedClient.get("VIDEO_8"));
		System.out.println(memcachedClient.get("VIDEO_8"));
		System.out.println(memcachedClient.get("VIDEO_10"));
		 
	}
}
