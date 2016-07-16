/**
 * 
 */
package com.happy3w.autobuy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happy3w.autobuy.process.AutoBuyExecutor;

/**
 *存储环境信息。如日志等。
 * @version 2016年6月25日 下午12:15:06
 * @author Happy3W Chen
 *
 */
public class LogUtil{
	private static final Logger logger = LoggerFactory.getLogger(AutoBuyExecutor.class);

	public static Logger getLogger() {
		return logger;
	}
	
}
