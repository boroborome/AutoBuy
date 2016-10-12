/**
 * 
 */
package com.happy3w.autobuy.down;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.cache.OrderCache;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.transfer.TransferProxy;

/**
 * 下载订单的定时任务。
 * 
 * @version 2016年7月25日 下午11:55:30
 * @author Happy3W Cherry
 *
 */
@Component
public class AtDownloadTask extends TimerTask {
	private OrderCache orderCache;
	private TransferProxy transferProxy;

	@Autowired
	public AtDownloadTask(OrderCache cache, TransferProxy manager) {
		this.orderCache = cache;
		this.transferProxy = manager;
	}

	public OrderCache getOrderCache() {
		return orderCache;
	}

	public int count = 0;

	@Override
	public void run() {
		PurchaseOrder[] orders = transferProxy.download();
		orderCache.put(orders);
		count = count + 1;
	}

}
