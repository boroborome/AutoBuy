package com.happy3w.autobuy.down;

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

public class DownloadTaskTest extends BaseTest {
	@Autowired
	private OrderCache cache;
	@Mock
	private TransferProxy manager;
	private AtDownloadTask task;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		task = new AtDownloadTask(cache, manager);
	}

	@Test
	public void testRun() {
		PurchaseOrder order = new PurchaseOrder();
		PurchaseOrder[] orders = new PurchaseOrder[] { order };
		Mockito.when(manager.download()).thenReturn(orders);
		task.run();
		Assert.assertEquals(cache.sizeOfNew(), 1);
	}
}
