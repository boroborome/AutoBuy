/**
 * 
 */
package com.happy3w.autobuy.transfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.HttpUtil;

/**
 * 对中转站的访问。
 * 
 * @version 2016年7月14日 下午4:09:32
 * @author Happy3W Cherry
 *
 */
@Component
public class TransferProxy {

	private HttpUtil http;

	private TransferUrl transfer;

	@Autowired
	public TransferProxy(HttpUtil http, TransferUrl transfer) {
		this.http = http;
		this.transfer = transfer;
	}

	/**
	 * 下载最新订单。
	 * 
	 * @param service
	 * @return
	 */
	public PurchaseOrder[] download() {
		String result = http.sendPost(transfer.getDownloadOrderUrl(), null);
		Gson gson = new Gson();
		List<PurchaseOrder> orders = gson.fromJson(result, new TypeToken<List<PurchaseOrder>>() {
		}.getType());
		if (null == orders) {
			return new PurchaseOrder[0];
		}
		return orders.toArray(new PurchaseOrder[0]);
	}

	public String finish(Order order) {
		return http.sendPost(transfer.getOrdeFinishUrl(), "orderid=" + order.getContent().getOrderid());
	}
}
