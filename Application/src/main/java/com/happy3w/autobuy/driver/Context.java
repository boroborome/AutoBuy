/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

import driver.RemoteDriver;

/**
 * @version 2016年10月26日下午3:26:54
 * @author happy3w
 */
public class Context {
	private static Context instance;
	private WebDriver driver;
	private ThrdPool pool;
	private List<IDownloadListener> downloadListeners  =new ArrayList<IDownloadListener>();
	private TransferProxy transfer;
	public Context()
	{
		downloadListeners.add(new ExeSchedulor());
	}
	public static Context getInstance() {
		if (null == instance) {
			instance = new Context();
		}
		return instance;
	}
	public WebDriver getDriver() {
		if (null == driver) {
			driver = RemoteDriver.getInstance().getDriver(10);
		}
		return driver;
	}

	public ThrdPool getPool() {
		if (null == pool) {
			pool = new ThrdPool(10);
		}
		return pool;
	}
	public TimeUnit getThrdTimeUnit() {
		return TimeUnit.MILLISECONDS;
	}

	public long getThrdPeriod() {
		return 5 * 60 * 1000;
	}
	public IDownloadListener[] getListeners()
	{
		return downloadListeners.toArray(new IDownloadListener[0]);
	}
	public TransferProxy getTransfer()
	{
		if(null==this.transfer)
		{
			transfer  =new TransferProxy(new HttpUtil(),new TransferUrl("http://localhost:8190/autobuy/"));
		}
		return transfer;
	}
}
