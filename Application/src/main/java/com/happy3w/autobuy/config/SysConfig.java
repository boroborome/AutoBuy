package com.happy3w.autobuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 配置系统环境。
 * 
 * @version 2016年9月1日 下午7:14:04
 * @author Happy3W Cherry
 *
 */

public class SysConfig {
	private Environment env;

	private DBConfig db;

	private ThreadConfig thread;

	@Autowired
	public SysConfig(Environment env) {
		this.env = env;
		this.db = new DBConfig(env);
		this.thread = new ThreadConfig(env);
	}

	/**
	 * 数据库相关配置。
	 * 
	 * @return
	 */
	@Bean
	public DBConfig getDB() {
		return this.db;
	}

	/**
	 * 线程池配置。
	 * 
	 * @return
	 */
	@Bean
	public ThreadConfig getThreadConfig() {
		return this.thread;
	}

	/**
	 * master要访问的服务器地址。
	 * <p>
	 * 如：http://localhost:80
	 * </p>
	 * 
	 * @return
	 */
	public String getWebServerUrl() {
		return env.getProperty("webServer.url");
	}

	/**
	 * 内存中允许缓存的订单数。
	 * <p>
	 * 如果超出此订单数，将等待系统处理完后，在从远程中转站中下载。
	 * </p>
	 * 
	 * @return
	 */
	public int getOrderSize() {
		return Integer.valueOf(env.getProperty("order.size"));
	}

	/**
	 * 从transfer服务器上获取返回结果尝试次数。
	 * 
	 * @return
	 */
	public int getTransferRetryTimes() {
		return Integer.valueOf(env.getProperty("transfer.retrytimes"));
	}

	/**
	 * 页面访问等待时长。
	 * 
	 * @return
	 */
	public long getTimeout() {
		// TODO Auto-generated method stub
		return Long.valueOf(env.getProperty("timeout"));
	}
	/**
	 * @return
	 * 步骤之间间隔时长。
	 */
	public long getStepSpan()
	{
		return 2*1000;
	}
}
