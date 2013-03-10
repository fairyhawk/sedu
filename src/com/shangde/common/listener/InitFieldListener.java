package com.shangde.common.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.shangde.common.util.SpringContextHolder;
import com.shangde.edu.sys.service.DicCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InitFieldListener implements ServletContextListener {
	 private static final Logger logger = LoggerFactory.getLogger(InitFieldListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		DicCodeService dicCodeService =SpringContextHolder.getBean("dicCodeService");
		dicCodeService.getFields();
		
	}
	

}
