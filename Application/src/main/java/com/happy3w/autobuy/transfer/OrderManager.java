/**
 * 
 */
package com.happy3w.autobuy.transfer;

import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.HttpUtil;

/**
 * 
 * @version 2016年7月14日 下午4:09:32
 * @author Happy3W Cherry
 *
 */
public class OrderManager {
	private String service;

	public OrderManager(String service) {
		this.service = service;
	}
	public PurchaseOrder[] getOrders4Buy() {
		String result = HttpUtil.sendPost(TransferUrl.getBuyUrl(this.service), null);
		Gson gson = new Gson();
		List<PurchaseOrder> orders = gson.fromJson(result, new TypeToken<List<PurchaseOrder>>() {
		}.getType());
		if (null == orders) {
			return new PurchaseOrder[0];
		}
		return orders.toArray(new PurchaseOrder[0]);
	}

	public String finish(Order order) {
		return HttpUtil.sendPost(TransferUrl.getOrdeFinishUrl(this.service),
				"orderid=" + order.getContent().getOrderid());
	}
}
