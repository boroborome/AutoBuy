package com.happy3w.autobuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import testkit.com.happy3w.autoby.BaseTest;

/**
 * 系统测试。
 *
 * @version 2016年9月1日 下午4:39:27
 * @author Happy3W Cherry
 *
 */
public class SysConfigTest extends BaseTest {
	@Autowired
	private SysConfig config;

	@Test
	public void testLoadConfig() {
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getOrderSize(), 10);
		Assert.assertEquals(config.getWebServerUrl(), "http://192.168.3.7:8190/autobuy/");
		Assert.assertEquals(config.getDB().getDbDriver(), "com.mysql.jdbc.Driver");
		Assert.assertEquals(config.getDB().getDbPwd(), "123456");
		Assert.assertEquals(config.getDB().getDbUrl(),
				"jdbc:mysql://localhost:3306/ResMgr?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
		Assert.assertEquals(config.getDB().getDbUser(), "ResMgr");
		Assert.assertEquals(config.getThreadConfig().getCoreSize(), 10);
		Assert.assertEquals(config.getThreadConfig().getKeepTime(), 60000);
		Assert.assertEquals(config.getThreadConfig().getMaxSize(), 12);

	}
}
