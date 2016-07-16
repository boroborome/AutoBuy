/**
 * 
 */
package com.happy3w.autobuy.schedule;

import java.util.Timer;
import java.util.TimerTask;

import com.happy3w.autobuy.model.Order;

/**
 *自动购买调度。
 * @version 2016年7月14日 下午11:46:41
 * @author Happy3W Cherry
 *
 */
public class AutoBuyScheduler {
	private Timer timer=new Timer();
	private Order order;
	public void schedule(Order order)
	{
		this.order=order;
		Context.getInstance().getOrders().put(order.getContent().getOrderid(), order);
		timer.schedule(new TimerTsk(), order.getContent().getBuytime());
	}
	public class TimerTsk extends TimerTask{

		@Override
		public void run() {
			Context.getInstance().getBuyExecutorPool().execute(order);
		}
		
	}
}
