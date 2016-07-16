package com.happy3w.autobuy.schedule;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.process.AutoBuyExecutor;
import com.happy3w.autobuy.util.LogUtil;
import com.happy3w.autobuy.util.config.DataConfig;

/**
 * 自动购买线程池。
 * 
 * @version 2016年7月14日 下午8:59:44
 * @author Happy3W Cherry
 *
 */
public class AutoBuyExecutorPool {

	private ThreadPoolExecutor pool;
	BlockingQueue<Runnable> queue;
	
	public void init() {
		DataConfig config = Context.getInstance().getDataConfig();
		RejectedHandler rejectedHandler = new RejectedHandler();
		queue = new ArrayBlockingQueue<Runnable>(config.getThread().getMaxSize(), true);
		pool = new ThreadPoolExecutor(config.getThread().getCoreSize(), config.getThread().getMaxSize(),
				config.getThread().getKeepTime(), TimeUnit.MILLISECONDS, queue, Executors.defaultThreadFactory(),
				rejectedHandler);
	}

	public void execute(Order order) {
		AutoBuyExecutor runner = new AutoBuyExecutor(order);
		pool.execute(runner);
	}

	public class RejectedHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			LogUtil.getLogger().info("rejected by " + AutoBuyExecutorPool.class.getName());
		}

	}
}
