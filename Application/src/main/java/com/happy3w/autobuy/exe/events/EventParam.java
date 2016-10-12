/**
 * 
 */
package com.happy3w.autobuy.exe.events;

import java.util.HashMap;
import java.util.Map;

import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 *
 * @version 2016年9月9日 下午1:12:54
 * @author Happy3W Cherry
 *
 */
public class EventParam {
	private Map<String, String> mapParam = new HashMap<String, String>();
	public static String VERIFYCODE = "verifycode";

	public EventParam(PurchaseOrder order, AtUser user) {
		mapParam.put(order.AMOUNT, String.valueOf(order.getAmount()));
		mapParam.put(order.BUYTIME, order.BUYTIME);
		mapParam.put(order.ORDERID, order.getOrderid());
		mapParam.put(order.PRODUCT, order.getProduct());
		mapParam.put(user.PASSWORD, user.getPassword());
		mapParam.put(user.USERID, user.getUserId());
	}

	public String getValue(String paramName) {
		return mapParam.get(paramName);
	}

	public void put(EventResult[] results) {
		if (null == results) {
			return;
		}
		for (EventResult result : results) {
			mapParam.put(result.getName(), result.getResult());
		}
	}
}
