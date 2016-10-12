/**
 * 
 */
package com.happy3w.autobuy.exe;

import java.util.TimerTask;

import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 自动执行订单的定时调度任务。
 * 
 * @version 2016年9月8日 下午3:40:59
 * @author Happy3W Cherry
 *
 */
public class AtExeScheduleTask extends TimerTask {

	private PurchaseOrder order;
	private AtExeThrdPool pool;

	public AtExeScheduleTask(AtExeThrdPool pool, PurchaseOrder order) {
		this.pool = pool;
		this.order = order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		this.pool.execute(order);
	}

}
