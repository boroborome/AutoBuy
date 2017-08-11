/**
 * 
 */
package com.happy3w.autobuy.driver;

import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.TaskSchedulor;
import com.happy3w.autobuy.model.User;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 2016年10月26日下午3:26:54
 * @author happy3w
 */
public class Context {
	private static Context instance;
	private ThrdPool pool;
	private List<IDownloadListener> downloadListeners  =new ArrayList<IDownloadListener>();
	private TransferProxy transfer;
	private User ycodeUser = new User("chenjij@yonyou.com","yy2900");
	private User loginUser = new User("chjj402@sina.com","yy2900");
	private String srv = "http://localhost:8190/autobuy/";
	private String chrome="http://localhost:4172";
	private TaskSchedulor driverContainer;
	private TriggerSchedulor trigger;
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
	private Map<String,WebDriver> drivers = new HashMap<String,WebDriver>();
	public WebDriver getDriver(String key) {
		if(!drivers.containsKey(key))
		{
			drivers.put(key, RemoteDriver.getInstance().getDriver(10,chrome));
		}
		return drivers.get(key);
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
			transfer  =new TransferProxy(new HttpUtil(),new TransferUrl(srv));
		}
		return transfer;
	}
	public TaskCache getTaskCache()
	{
		return TaskCache.getInstance();
	}

	public User getYCodeUser()
	{
		return ycodeUser;
	}

	public User getLoginUser()
	{
		return loginUser;
	}

	public String getServiceUrl()
	{
		return srv;
	}

	public TaskSchedulor getTaskSchedulor()
	{
		if(null==driverContainer)
		{
			driverContainer = new TaskSchedulor();
		}
		return driverContainer;
	}

	public TriggerSchedulor getTriggerSchedulor() {
		if(null==trigger)
		{
			trigger = new TriggerSchedulor();
		}
		return trigger;
	}
	public String getChrome() {
		return this.chrome;
	}
}
