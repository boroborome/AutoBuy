/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 单步处理器。
 * 
 * @version 2016年9月8日 下午5:02:44
 * @author Happy3W Cherry
 *
 */
public interface IStepRunner {

	/**
	 * 处理订单。
	 * 
	 * @param order
	 */
	void process(PurchaseOrder order);

}
