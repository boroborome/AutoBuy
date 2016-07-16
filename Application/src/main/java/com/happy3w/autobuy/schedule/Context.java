/**
 * 
 */
package com.happy3w.autobuy.schedule;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.util.config.DataConfig;

/**
 *
 * @version 2016年7月14日 下午11:54:20
 * @author Happy3W Cherry
 *
 */
@Component
public class Context {
	private static Context instance = new Context();
	public static Context getInstance()
	{
		return instance;
	}
	private DataConfig dataConfig;
	private OrderMonitor transfer =new OrderMonitor();
	private AutoBuyScheduler buyscheduler = new AutoBuyScheduler();
	private AutoBuyExecutorPool buyExecutorPool = new AutoBuyExecutorPool();
	private ConcurrentHashMap<String,Order> orders = new ConcurrentHashMap<String,Order>();
	
	public DataConfig getDataConfig() {
		return dataConfig;
	}

	public OrderMonitor getTransfer() {
		return transfer;
	}

	public AutoBuyScheduler getBuyscheduler() {
		return buyscheduler;
	}

	public AutoBuyExecutorPool getBuyExecutorPool() {
		return buyExecutorPool;
	}

	public ConcurrentHashMap<String, Order> getOrders() {
		return orders;
	}
	public void init(DataConfig config){
		this.dataConfig=config;
		this.buyExecutorPool.init();
	}
}
