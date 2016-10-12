/**
 * 
 */
package com.happy3w.autobuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * thread configuration.
 * 
 * @version 2016年9月3日 上午7:43:07
 * @author Happy3W Cherry
 *
 */
@Component
public class ThreadConfig {
	private Environment env;

	@Autowired
	public ThreadConfig(Environment env) {
		this.env = env;
	}

	public int getCoreSize() {
		return Integer.valueOf(env.getProperty("thread.coresize")).intValue();
	}

	public int getMaxSize() {
		return Integer.valueOf(env.getProperty("thread.maxsize")).intValue();
	}

	public long getKeepTime() {
		return Long.valueOf(env.getProperty("thread.keeptime")).longValue();
	}
}
