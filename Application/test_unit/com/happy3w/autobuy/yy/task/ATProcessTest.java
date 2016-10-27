/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.happy3w.autobuy.driver.Context;
import com.happy3w.autobuy.driver.DownloadRunner;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.transfer.TransferProxy;

import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月27日上午10:07:48
 * @author happy3w
 */
public class ATProcessTest extends BaseTest{
	@Test
	public void startTest()
	{
		TransferProxy transfer  = Mockito.mock(TransferProxy.class);
		UserOrder order = new UserOrder();
		order.put(UserOrder.AMOUNT, "100");
		order.put(UserOrder.BUYTIME, "2016-10-2");
		order.put(UserOrder.ORDERID, "t01");
		order.put(UserOrder.PRODUCT, "YY-C");
		order.put(UserOrder.TASK, "yy");
		Mockito.when(transfer.download()).thenReturn(new UserOrder[]{order});
		DownloadRunner runner  =new DownloadRunner(transfer,Context.getInstance().getListeners());
		//Context.getInstance().getPool().schedule(runner,0, 1000, Context.getInstance().getThrdTimeUnit());
		Context.getInstance().getPool().execute(runner);
		System.out.println("ok");
	}
}
