/**
 * 
 */
package com.happy3w.autobuy.task.operation;

import java.util.HashMap;
import java.util.Map;

import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 操作处理中的参数。
 * @version 2016年9月9日 下午1:12:54
 * @author Happy3W Cherry
 *
 */
public class Param {
	private Map<String, String> mapParam = new HashMap<String, String>();
	public static String VERIFYCODE = "verifycode";

	public Param(PurchaseOrder order, AtUser user) {
		mapParam.put(order.AMOUNT, String.valueOf(order.getAmount()));
		mapParam.put(order.BUYTIME, order.BUYTIME);
		mapParam.put(order.ORDERID, order.getOrderid());
		mapParam.put(order.PRODUCT, order.getProduct());
		mapParam.put(AtUser.PASSWORD, user.getPassword());
		mapParam.put(AtUser.USERID, user.getUserId());
	}
	/**
	 * 获取值。
	 * @param paramName
	 * @return
	 */
	public String getValue(String key) {
		return mapParam.get(key);
	}

	/**
	 * 存储结果。
	 * @param results
	 */
	public void put(Result[] results) {
		if (null == results) {
			return;
		}
		for (Result result : results) {
			mapParam.put(result.getName(), result.getResult());
		}
	}

	/**
	 * 存储结果。
	 * @param results
	 */
	public void put(Result result) {
		if (null == result) {
			return;
		}
		mapParam.put(result.getName(), result.getResult());
	}
}
