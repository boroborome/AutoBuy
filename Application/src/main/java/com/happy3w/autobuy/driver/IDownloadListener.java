/**
 * 
 */
package com.happy3w.autobuy.driver;

import com.happy3w.autobuy.model.UserOrder;

/**
 * 下载监听。
 * @version 2016年10月27日上午9:25:24
 * @author happy3w
 */
public interface IDownloadListener {
	/**
	 * 处理订单。
	 * @param orders
	 */
	public void handle(UserOrder[] orders);
}
