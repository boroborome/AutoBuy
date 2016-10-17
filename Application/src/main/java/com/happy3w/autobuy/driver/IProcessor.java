/**
 * 
 */
package com.happy3w.autobuy.driver;

import com.happy3w.autobuy.model.PurchaseOrder;

/**
 *
 * @version 2016年9月8日 下午4:58:07
 * @author Happy3W Cherry
 *
 */
public interface IProcessor {

	/**
	 * 处理订单。
	 * 
	 * @param order
	 */
	void process(PurchaseOrder order);

}
