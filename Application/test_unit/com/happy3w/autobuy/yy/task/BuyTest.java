/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.yy.flow.Buy;
import com.happy3w.autobuy.yy.flow.YCode;

import driver.RemoteDriver;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * 下单购买。
 * @version 2016年10月21日下午4:11:09
 * @author happy3w
 */
public class BuyTest extends BaseTest {
	@Autowired
	private SysConfig config;
	private AtUser ycodeUser;
	private AtUser buyUser;
	private PurchaseOrder order;
	private Param arg;
	@BeforeTest
	public void beforeTest()
	{
		ycodeUser = new AtUser("chenjij@yonyou.com","yy2900");
		buyUser  =new AtUser("chjj402@sina.com","yy2900");
		order  =new PurchaseOrder();
		order.setAmount(100);
		order.setBuytime(new Date());
		order.setOrderid("t01");
		order.setProduct("YY-C");
		order.setRate("8.0%");

	}
	@Test
	public void testBuy()
	{
		arg = new Param();
		arg.put(ycodeUser);
		YCode ycode = new YCode(config);
		ycode.handle(RemoteDriver.getInstance().getDriver(config.getTimeout()), arg);
		Buy buy  =new Buy(config);
		arg.put(buyUser);
		buy.handle(RemoteDriver.getInstance().getDriver(config.getTimeout()), arg);
	}
}
