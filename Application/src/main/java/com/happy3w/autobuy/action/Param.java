/**
 * 
 */
package com.happy3w.autobuy.action;

import java.util.HashMap;
import java.util.Map;

import com.happy3w.autobuy.model.User;
import com.happy3w.autobuy.model.UserOrder;

/**
 * 操作处理中的参数。
 * @version 2016年9月9日 下午1:12:54
 * @author Happy3W Cherry
 *
 */
public class Param {
	private Map<String, String> mapParam = new HashMap<String, String>();
	public static String VERIFYCODE = "verifycode";
	public Param() {
	}
	public void put(UserOrder order)
	{
		if(order.getItems().size()>0)
		{
		mapParam.putAll(order.getItems());
		}
		else
		{
			mapParam.put(UserOrder.AMOUNT, String.valueOf(order.getAmount()));
			mapParam.put(UserOrder.BUYTIME, String.valueOf(order.getBuytime()));
			mapParam.put(UserOrder.ORDERID, String.valueOf(order.getOrderid()));
			mapParam.put(UserOrder.PRODUCT, String.valueOf(order.getProduct()));
			mapParam.put(UserOrder.TASK, String.valueOf(order.getTask()));
		}
	}
	public void put(User user)
	{
		mapParam.put(User.PASSWORD, user.getPassword());
		mapParam.put(User.USERID, user.getUserId());
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
	public void put(String key,String value)
	{
		mapParam.put(key, value);
	}
}
