package com.happy3w.autobuy.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import testkit.com.happy3w.autoby.BaseTest;

public class AutoBuySrvTest extends BaseTest {
	@Autowired
	private AutoBuySvc srv;

	@Test
	public void testStart() {
		srv.start();
		boolean expected = true;
		Assert.assertEquals(srv.isStarted(), expected);
	}
}
