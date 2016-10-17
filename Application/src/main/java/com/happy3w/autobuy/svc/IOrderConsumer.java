/**
 * 
 */
package com.happy3w.autobuy.svc;

import com.happy3w.autobuy.model.PurchaseOrder;

/**
 *
 * @version 2016年7月21日 下午3:55:52
 * @author Happy3W Cherry
 *
 */
public interface IOrderConsumer extends IService{
	/**
	 * 调度待执行订单。
	 * 
	 * @param orders
	 */
	public void schedule(PurchaseOrder[] orders);
}
