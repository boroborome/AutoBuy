/**
 * 
 */
package com.happy3w.autobuy.transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.HttpUtil;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 * 订单管理。
 * 
 * @version 2016年9月1日 下午3:38:22
 * @author Happy3W Cherry
 *
 */
public class TranferProxyTest extends BaseTest {
	@Autowired
	private TransferUrl transfer;
	@Mock
	private HttpUtil http;
	private TransferProxy manager;

	@BeforeMethod
	public void setup() {
		manager = new TransferProxy(http, transfer);
	}

	/**
	 * 测试返回值为0单。
	 */
	@Test
	public void testDownload_0() {
		Mockito.when(http.sendPost(transfer.getDownloadOrderUrl(), null)).thenReturn(TestUtil.getJson(0, 0));
		PurchaseOrder[] orders = manager.download();
		Assert.assertNotNull(orders);
		Assert.assertEquals(orders.length, 0);
	}

	/**
	 * 测试返回值为1单。
	 */
	@Test
	public void testDownload_1() {
		Mockito.when(http.sendPost(transfer.getDownloadOrderUrl(), null)).thenReturn(TestUtil.getJson(1, 1));
		PurchaseOrder[] orders = manager.download();
		Assert.assertNotNull(orders);
		Assert.assertEquals(orders.length, 1);
		Assert.assertEquals(orders[0].getAmount(), new Double(100));
		Assert.assertEquals(orders[0].getOrderid(), "T01");
		Assert.assertEquals(orders[0].getProduct(), "YY-A");
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Assert.assertEquals(orders[0].getBuytime(), date.parse("2016/09/08 09:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 测试返回2单。
	 */
	@Test
	public void testDownload_2() {
		Mockito.when(http.sendPost(transfer.getDownloadOrderUrl(), null)).thenReturn(TestUtil.getJson(2, 1));
		PurchaseOrder[] orders = manager.download();
		Assert.assertNotNull(orders);
		Assert.assertEquals(orders.length, 2);
		Assert.assertEquals(orders[0].getAmount(), new Double(100));
		Assert.assertEquals(orders[0].getOrderid(), "T01");
		Assert.assertEquals(orders[0].getProduct(), "YY-A");
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Assert.assertEquals(orders[0].getBuytime(), date.parse("2016/09/08 09:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(orders[1].getAmount(), new Double(100));
		Assert.assertEquals(orders[1].getOrderid(), "T02");
		Assert.assertEquals(orders[1].getProduct(), "YY-A");
		SimpleDateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Assert.assertEquals(orders[1].getBuytime(), date2.parse("2016/09/08 09:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
