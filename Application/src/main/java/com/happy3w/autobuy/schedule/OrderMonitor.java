/**
 * 
 */
package com.happy3w.autobuy.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.transfer.OrderManager;
import com.happy3w.autobuy.util.config.DataConfig;

/**
 * 监控中转站订单情况。
 * 
 * @version 2016年7月14日 下午8:28:00
 * @author Happy3W Cherry
 *
 */
public class OrderMonitor {
	private Timer timer = new Timer();

	public void schedule() {
		timer.schedule(new OrderFetch(), new Date(System.currentTimeMillis()), 5000);
	}

	public class OrderFetch extends TimerTask {

		@Override
		public void run() {
			DataConfig dataConfig = Context.getInstance().getDataConfig();
			OrderManager manager = new OrderManager(dataConfig.getWebServerUrl());
			PurchaseOrder[] pos = manager.getOrders4Buy();
			if (Context.getInstance().getOrders().size() >= dataConfig.getOrderSize()) {
				return;
			}
			for (PurchaseOrder po : pos) {
				Order order = new Order();
				order.setSite(dataConfig);
				order.setContent(po);
				Context.getInstance().getBuyscheduler().schedule(order);
				System.out.println(System.currentTimeMillis() + ":" + po.getProduct());
			}
		}

	}
}
