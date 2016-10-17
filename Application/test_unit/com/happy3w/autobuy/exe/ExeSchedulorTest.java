/**
 * 
 */
package com.happy3w.autobuy.exe;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.driver.AtExeSchedulor;
import com.happy3w.autobuy.driver.AtExeThrdPool;
import com.happy3w.autobuy.model.PurchaseOrder;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 *
 * @version 2016年9月8日 下午3:26:25
 * @author Happy3W Cherry
 *
 */
public class ExeSchedulorTest extends BaseTest {
	private AtExeSchedulor schedulor;
	@Mock
	private AtExeThrdPool pool;

	@BeforeMethod
	public void setup() {
		schedulor = new AtExeSchedulor(pool);
	}

	@Test
	public void testSchedule() {
		PurchaseOrder[] orders = TestUtil.getOrders(3, 1);
		schedulor.schedule(orders);
		Mockito.verify(pool, new Times(1)).execute(orders[0]);
		Mockito.verify(pool, new Times(1)).execute(orders[1]);
		Mockito.verify(pool, new Times(1)).execute(orders[2]);
	}
}
