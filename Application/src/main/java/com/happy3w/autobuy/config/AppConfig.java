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

import com.happy3w.autobuy.cache.OrderCache;
import com.happy3w.autobuy.down.AtDownloadTask;
import com.happy3w.autobuy.down.AtDownloader;
import com.happy3w.autobuy.exe.AtExeSchedulor;
import com.happy3w.autobuy.exe.AtExeThrdPool;
import com.happy3w.autobuy.exe.events.EventRegister;
import com.happy3w.autobuy.exe.step.StepManager;
import com.happy3w.autobuy.exe.step.StepRegister;
import com.happy3w.autobuy.exe.step.UserRegister;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.WebDriverUtil;

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
	public AtDownloader provider() {
		return new AtDownloader(getDownloadTask(), getFirstTime(), getPeriod());
	}

	@Bean
	public TimerTask getDownloadTask() {
		return new AtDownloadTask(getCache(), getTransferProxy());
	}

	@Bean
	public OrderCache getCache() {
		return new OrderCache(getExecutor());
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
		return new TransferUrl();
	}

	@Bean
	public AtExeSchedulor getExecutor() {
		return new AtExeSchedulor(getPool());
	}

	@Bean
	public AtExeThrdPool getPool() {
		return new AtExeThrdPool(getSysConfig().getThreadConfig(), getStepManager());
	}

	@Bean
	public StepManager getStepManager() {
		return new StepManager(getWebDriver(), getUserRegister(), getStepRegister(), getEventRegister());
	}

	@Bean
	public WebDriver getWebDriver() {
		return WebDriverUtil.getWebDriver();
	}

	@Bean
	public UserRegister getUserRegister() {
		return new UserRegister();
	}

	@Bean
	public EventRegister getEventRegister() {
		return new EventRegister(getHttp(), getTransferUrl(), getSysConfig());
	}

	@Bean
	public StepRegister getStepRegister() {
		return new StepRegister();
	}
}
