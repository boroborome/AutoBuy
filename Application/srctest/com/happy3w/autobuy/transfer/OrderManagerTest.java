/**
 * 
 */
package com.happy3w.autobuy.transfer;

import org.junit.Assert;
import org.junit.Test;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.transfer.OrderManager;

/**
 *
 * @version 2016年7月14日 下午4:41:49
 * @author Happy3W Cherry
 *
 */
public class OrderManagerTest {
	private String transfer="http://192.168.3.7:8190/autobuy/";
	@Test
	public void testBuyOrders() {
		OrderManager mng=new OrderManager(transfer);
		PurchaseOrder[] orders=mng.getOrders4Buy();
		Assert.assertNotNull(orders);
	}
	@Test
	public void testFinishOrder()
	{
		OrderManager mng=new OrderManager(transfer);
		Order order = new Order();
		PurchaseOrder po = new PurchaseOrder();
		po.setOrderid("14");
		order.setContent(po);
		String result=mng.finish(order);
		Assert.assertTrue(result.contains("ok"));
	}
}
