/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.yy.task.GetYCode;
import com.happy3w.autobuy.yy.task.YYLoginInner;
import com.happy3w.autobuy.yy.task.action.YYVerifyImg;

import driver.RemoteDriver;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月21日上午11:39:34
 * @author happy3w
 */
public class GetYCodeTest extends BaseTest{
	@Autowired
	private SysConfig config;
	private WebDriver driver;
	private Param param;
	private String srv="http://localhost:8190/autobuy/";
	@Autowired
	private HttpUtil http;
	@BeforeTest
	public void beforeTest()
	{
		driver  =RemoteDriver.getInstance().getDriver(10);
		PurchaseOrder order  =new PurchaseOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		AtUser user = new AtUser("chenjij@yonyou.com","yy2900");
		param  =new Param();
		param.put(user);
		param.put(order);
	}
	@Test
	public void testGet()
	{
		login();
		GetYCode ycode=new GetYCode(config);
		ycode.handle(driver, param);
	}
	public void login()
	{
		YYVerifyImg vc=new YYVerifyImg(config,http,srv);
		YYLoginInner login = new YYLoginInner(config,vc);
		PurchaseOrder order  =new PurchaseOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		AtUser user = new AtUser("chenjij@yonyou.com","yy2900");
		Param arg  =new Param();
		arg.put(user);
		arg.put(order);
		//Mockito.when(vc.handle(driver, arg)).thenReturn(new Result[]{new Result(VerifyCode.RETURNNAME,"11")});
		login.handle(driver,arg);
	}
}
