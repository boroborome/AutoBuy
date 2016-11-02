/**
 * 
 */
package com.happy3w.autobuy.config;

import java.util.Date;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.happy3w.autobuy.driver.Context;
import com.happy3w.autobuy.driver.ExeSchedulor;
import com.happy3w.autobuy.driver.ThrdPool;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.WebDriverUtil;

import driver.RemoteDriver;

/**
 * spring configuration file.
 * 
 * @version 2016年9月3日 上午6:42:48
 * @author Happy3W Cherry
 *
 */
@Configuration
@ComponentScan({ "com.happy3w.autobuy.config" })
// @ComponentScan({ "com.happy3w.autobuy.cache", "com.happy3w.autobuy.config",
// "com.happy3w.autobuy.util",
// "com.happy3w.autobuy.down", "com.happy3w.autobuy.exe",
// "com.happy3w.autobuy.transfer",
// "com.happy3w.autobuy.svc" })
public class AppConfig {
	private static AppConfig config;

	public static AppConfig getInstance() {
		if (null == config) {
			return new AppConfig();
		} else {
			return config;
		}
	}

	@Autowired
	protected Environment env;

	@Bean
	public SysConfig getSysConfig() {
		return new SysConfig(env);
	}

	/**
	 * 系统执行任务开始时间。
	 */
	@Bean
	public Date getFirstTime() {
		return new Date();

	}

	/**
	 * 任务处理周期。 20*1000
	 */
	@Bean
	public long getPeriod() {
		return 20 * 1000;
	}

	@Bean
	public TransferProxy getTransferProxy() {
		return new TransferProxy(getHttp(), getTransferUrl());
	}

	@Bean
	public HttpUtil getHttp() {
		return new HttpUtil();
	}

	@Bean
	public TransferUrl getTransferUrl() {
		return new TransferUrl(this.getSysConfig().getWebServerUrl());
	}

	@Bean
	public ExeSchedulor getExecutor() {
		return new ExeSchedulor();
	}

	@Bean
	public ThrdPool getPool() {
		return new ThrdPool(getSysConfig().getThreadConfig().getCoreSize());
	}

	@Bean
	public WebDriver getWebDriver() {
		return RemoteDriver.getInstance().getDriver(10,Context.getInstance().getChrome());
	}
}
