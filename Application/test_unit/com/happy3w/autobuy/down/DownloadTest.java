/**
 * 
 */
package com.happy3w.autobuy.down;

import java.util.Date;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.cache.OrderCache;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.transfer.TransferProxy;

import testkit.com.happy3w.autoby.BaseTest;

/**
 *
 * @version 2016年7月21日 下午4:26:44
 * @author Happy3W Cherry
 *
 */

public class DownloadTest extends BaseTest {
	@Mock
	private TransferProxy manager;
	@Autowired
	private OrderCache cache;
	private AtDownloadTask task;
	private AtDownloader provider;

	@BeforeMethod
	public void setup() {
		task = new AtDownloadTask(cache, manager);
		provider = new AtDownloader(task, new Date(), 100);
	}

	@Test
	public void testRun() {
		PurchaseOrder order = new PurchaseOrder();
		PurchaseOrder[] orders = new PurchaseOrder[] { order };
		Mockito.when(manager.download()).thenReturn(orders);
		provider.start();
		Assert.assertTrue(provider.isStarted());
		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(cache.sizeOfNew() >= 10);
	}
}
