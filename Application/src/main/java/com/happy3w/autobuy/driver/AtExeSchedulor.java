/**
 * 
 */
package com.happy3w.autobuy.exe;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.svc.IOrderConsumer;

/**
 * 自动执行任务调度员。
 * 
 * @version 2016年7月21日 下午3:16:26
 * @author Happy3W Cherry
 *
 */
@Component
public class AtExeSchedulor implements IOrderConsumer {
	/**
	 * 任务定时器。
	 */
	private Timer timer = new Timer();
	private AtExeThrdPool pool;

	@Autowired
	public AtExeSchedulor(AtExeThrdPool pool) {
		this.pool = pool;
	}

	@Override
	public void schedule(PurchaseOrder[] orders) {
		for (PurchaseOrder order : orders) {
			timer.schedule(new AtExeScheduleTask(pool, order), order.getBuytime());
		}
	}

}
