/**
 * 
 */
package com.happy3w.autobuy.util;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.happy3w.autobuy.transfer.TransferUrl;

/**
 * @author Administrator
 *
 */
public class HttpUtilTest {
	private String service = "http://192.168.3.7:8190/autobuy/";
	private HttpUtil http = new HttpUtil();
	private TransferUrl transfer = new TransferUrl();

	@Test
	public void testIsNew() {
		String result = http.sendPost(transfer.getIsNewUrl(), null);
		Assert.assertNotNull(result);
		boolean isnew = Boolean.valueOf(result);
		AssertJUnit.assertEquals("not equal", false, isnew);
	}

	@Test
	public void testIdentify() {
		String result = http.sendPost(transfer.getIdentifyUrl(), "verifycode=124");
		Assert.assertNotNull(result);
		AssertJUnit.assertEquals("not equal", "124", result);

	}

	@Test
	public void testResult() {
		String result = http.sendPost(transfer.getVerifyCodeResultUrl(), null);
		Assert.assertNotNull(result);
		AssertJUnit.assertEquals("not equal", "124", result);
	}
}
