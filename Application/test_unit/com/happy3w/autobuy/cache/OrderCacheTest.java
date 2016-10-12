/**
 * 
 */
package com.happy3w.autobuy.cache;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.exe.AtExeSchedulor;
import com.happy3w.autobuy.model.PurchaseOrder;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 *
 * @version 2016年9月8日 下午9:55:46
 * @author Happy3W Cherry
 *
 */
public class OrderCacheTest extends BaseTest {
	@Mock
	private AtExeSchedulor schedulor;
	private OrderCache cache;

	@BeforeMethod
	public void setup() {
		cache = new OrderCache(schedulor);
	}

	@Test
	public void testPut() {
		PurchaseOrder[] orders = TestUtil.getOrders(3, 1);
		cache.put(orders);
		Mockito.verify(schedulor, new Times(1)).schedule(orders);
		Assert.assertEquals(cache.sizeOfNew(), 0);
		Assert.assertEquals(cache.sizeOfDoing(), 3);
	}
}
