/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.happy3w.autobuy.config.ThreadConfig;
import com.happy3w.autobuy.model.UserOrder;

/**
 * 任务执行线程池管理。
 * 
 * @version 2016年9月9日 上午6:47:01
 * @author Happy3W Cherry
 *
 */
public class ThrdPool {
	private ScheduledExecutorService  service;

	/**
	 * 线程池构造函数。
	 * 
	 * @param config
	 */
	public ThrdPool(int coreSize) {
		service = Executors.newScheduledThreadPool(coreSize);
	}
	public void schedule(Runnable runner,long initdelay,long period,TimeUnit unit)
	{
		service.scheduleAtFixedRate(runner, initdelay,period,unit);
	}
	/**
	 * 执行订单。
	 * 
	 * @param order
	 */
	public void execute(Runnable runner) {
		service.execute(runner);
	}
}
