/**
 * 
 */
package com.happy3w.autobuy.exe;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.happy3w.autobuy.driver.AtExeScheduleTask;
import com.happy3w.autobuy.driver.AtExeThrdPool;
import com.happy3w.autobuy.model.PurchaseOrder;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 *
 * @version 2016年9月8日 下午4:02:03
 * @author Happy3W Cherry
 *
 */
public class ExeScheduleTaskTest extends BaseTest {

	private AtExeScheduleTask task;
	@Mock
	private AtExeThrdPool pool;

	@Test
	public void testRun() {
		PurchaseOrder[] orders = TestUtil.getOrders(3, 1);
		task = new AtExeScheduleTask(pool, orders[0]);
		task.run();
		Mockito.verify(pool).execute(orders[0]);
	}
}
