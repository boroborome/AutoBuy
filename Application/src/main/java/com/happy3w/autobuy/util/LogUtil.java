/**
 * 
 */
package com.happy3w.autobuy.util;

import java.util.logging.Logger;

/**
 * 存储环境信息。如日志等。
 * 
 * @version 2016年6月25日 下午12:15:06
 * @author Happy3W Chen
 *
 */
public class LogUtil {
	private static Logger log;

	public static Logger log() {
		if (null == log) {
			log = Logger.getLogger("com.happy3w.autobuy");
		}
		return log;
	}
}
