/**
 * 
 */
package com.happy3w.autobuy;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.happy3w.autobuy.util.HttpUtil;

/**
 * @author Administrator
 *
 */
public class HttpUtilTest {
	private String transfer="http://192.168.3.7:8190/autobuy/";

	@Test
	public void testIsNew() {
		String result =HttpUtil.sendPost(transfer+"isnew.php", null);
		Assert.assertNotNull(result);
		boolean isnew = Boolean.valueOf(result);
		Assert.assertEquals("not equal", true, isnew);
	}
	@Test
	public void testIdentify() {
		String result =HttpUtil.sendPost(transfer+"identify.php", "verifycode=124");
		Assert.assertNotNull(result);
		Assert.assertEquals("not equal", "124", result);
		
	}
	public void testUpload(){
		//String result = RequestUtil.formUpload(transfer+"upload.php", null, fileMap, fileStream);
	}
	@Test
	public void testRead()
	{
		String result = HttpUtil.sendPost(transfer+"read.php",null);
		Assert.assertNotNull(result);
		Assert.assertEquals("not equal", "124", result);
	}
}
