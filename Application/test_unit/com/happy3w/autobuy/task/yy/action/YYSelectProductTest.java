/**
 * 
 */
package com.happy3w.autobuy.task.yy.action;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.yy.action.YYFilterProduct;

import driver.RemoteDriver;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月21日上午10:53:07
 * @author happy3w
 */
public class YYSelectProductTest extends BaseTest{
	private WebDriver driver;
	private Param param;
	@BeforeTest
	public void beforeTest()
	{
		driver  =RemoteDriver.getInstance().getDriver();
		PurchaseOrder order = new PurchaseOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		AtUser user = new AtUser("chenjij@yonyou.com","yy2900");
		param  =new Param(order,user);
	}
	@Test
	public void testClick()
	{	driver.get("https://www.yyfax.com/financing/yxlc/yxlb.html");
		YYFilterProduct select  =YYFilterProduct.getInstance();
		select.handle(driver, param);
		
	}
}
