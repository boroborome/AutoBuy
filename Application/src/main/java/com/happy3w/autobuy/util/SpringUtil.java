/**
 * 
 */
package com.happy3w.autobuy.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 关于Spring的工具
 * 
 * @version 2016年6月26日 上午7:52:33
 * @author Happy3W Cherry
 *
 */
public class SpringUtil {
	public static <T>T getBean(Class<T> t) {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new AnnotationConfigApplicationContext("com.happy3w.autobuy");
		T bean = appContext.getBean(t);
		return bean;
	}
}
