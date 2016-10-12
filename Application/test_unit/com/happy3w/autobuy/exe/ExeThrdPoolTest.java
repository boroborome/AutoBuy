/**
 * 
 */
package com.happy3w.autobuy.exe;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exe.step.StepManager;
import com.happy3w.autobuy.model.PurchaseOrder;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 *
 * @version 2016年9月9日 上午10:40:18
 * @author Happy3W Cherry
 *
 */
public class ExeThrdPoolTest extends BaseTest {
	private AtExeThrdPool pool;
	@Autowired
	private SysConfig config;
	@Mock
	private StepManager stepManager;

	@BeforeMethod
	public void setup() {
		pool = new AtExeThrdPool(config.getThreadConfig(), stepManager);
	}

	@Test
	public void testExe() {
		PurchaseOrder[] orders = TestUtil.getOrders(1, 1);
		pool.execute(orders[0]);
		Mockito.verify(stepManager).getSteps(orders[0]);
	}
}
