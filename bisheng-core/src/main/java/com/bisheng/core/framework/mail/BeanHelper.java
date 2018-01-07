/**
 * 
 */
package com.bisheng.core.framework.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author bingfeng
 *
 */
public class BeanHelper {
	
	/**
	 * spring容器类
	 */
	private static ApplicationContext context = null;
	
	/**
	 * 获取spring容器并缓存
	 * @return
	 */
	private static ApplicationContext getApplicationContext(){
		if(context == null){
			context = new ClassPathXmlApplicationContext("applicationContext/applicationContext.xml");
		}
		return context;
	}
	

	/**
	 * 获取spring容器中的bean
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T)getApplicationContext().getBean(beanName);
	}
	
}
