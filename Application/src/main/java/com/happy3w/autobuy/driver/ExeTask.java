/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.TimerTask;

import com.happy3w.autobuy.model.UserOrder;

/**
 * 自动执行订单的定时调度任务。
 * 
 * @version 2016年9月8日 下午3:40:59
 * @author Happy3W Cherry
 *
 */
public class ExeTask extends TimerTask {

	private UserOrder order;

	public ExeTask(UserOrder order) {
		this.order = order;
	}

	@Override
	public void run() {
		Context.getInstance().getPool().execute(new ExeRunner(order));
	}

}
