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
	public void schedule(Order order)
	{
		Context.getInstance().getOrders().put(order.getContent().getOrderid(), order);
		timer.schedule(new OrderTimerTask(order), order.getContent().getBuytime());
	}
	public class OrderTimerTask extends TimerTask{

		private Order order;
		
		public OrderTimerTask(Order order) {
			this.order = order;
		}
		@Override
		public void run() {
			Context.getInstance().getBuyExecutorPool().execute(order);
		}
		
	}
}
