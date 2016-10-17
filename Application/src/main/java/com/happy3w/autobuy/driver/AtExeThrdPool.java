/**
 * 
 */
package com.happy3w.autobuy.exe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.happy3w.autobuy.config.ThreadConfig;
import com.happy3w.autobuy.exe.step.StepManager;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 任务执行线程池管理。
 * 
 * @version 2016年9月9日 上午6:47:01
 * @author Happy3W Cherry
 *
 */
public class AtExeThrdPool {
	private ExecutorService service;
	private StepManager stepManager;

	/**
	 * 线程池构造函数。
	 * 
	 * @param config
	 */
	public AtExeThrdPool(ThreadConfig config, StepManager stepManager) {
		service = Executors.newFixedThreadPool(config.getCoreSize());
		this.stepManager = stepManager;
	}

	/**
	 * 执行订单。
	 * 
	 * @param order
	 */
	public void execute(PurchaseOrder order) {
		service.execute(new AtExeThrdRunner(order, stepManager));
	}
}
