/**
 * 
 */
package com.happy3w.auobuy.down;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.cache.OrderCache;
import com.happy3w.autobuy.config.AppConfig;
import com.happy3w.autobuy.down.AtDownloadTask;
import com.happy3w.autobuy.down.AtDownloader;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 * 完整下载流程特性测试。
 * 
 * @version 2016年9月8日 上午10:17:58
 * @author Happy3W Cherry
 *
 */
public class DownloaderTest extends BaseTest {
	@Autowired
	private OrderCache cache;
	@Autowired
	private TransferUrl transferUrl;
	@Mock
	private HttpUtil http;
	private AtDownloader loader;
	private AtDownloadTask task;
	private TransferProxy transferProxy;

	@BeforeMethod
	public void setup() {
		Mockito.when(http.sendPost(transferUrl.getDownloadOrderUrl(), null)).thenReturn(TestUtil.getJson(3, 1));
		transferProxy = new TransferProxy(http, transferUrl);
		task = new AtDownloadTask(cache, transferProxy);
		loader = new AtDownloader(task, AppConfig.getInstance().getFirstTime(), 10);
	}

	@Test
	public void testStart() {
		loader.start();
		Assert.assertTrue(loader.isStarted());
	}

	@Test
	public void testDownload() {
		loader.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mockito.verify(http, new Times(12)).sendPost(transferUrl.getDownloadOrderUrl(), null);
		Assert.assertEquals(cache.sizeOfNew(), 3);

	}
}
