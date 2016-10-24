/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.util.Calendar;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.ClickElement;
import com.happy3w.autobuy.task.action.IAction;
import com.happy3w.autobuy.task.action.Input;
import com.happy3w.autobuy.task.action.OpenPage;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.Result;
import com.happy3w.autobuy.task.action.VerifyImg;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.WebDriverUtil;
import com.happy3w.autobuy.yy.task.YYLoginInner;
import com.happy3w.autobuy.yy.task.action.YYVerifyImg;

import driver.RemoteDriver;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * 完成登录。
 * @version 2016年10月17日下午2:24:40
 * @author happy3w
 */
public class LoginTest extends BaseTest{
	@Autowired
	private SysConfig config;
	@Autowired
	private HttpUtil http;
	private String srv="http://localhost:8190/autobuy/";
	private WebDriver driver;
	@BeforeTest
	public void beforeTest()
	{
		driver  =RemoteDriver.getInstance().getDriver(config.getTimeout());
	}
	@AfterTest
	public void afterTest()
	{
		//driver.quit();
	}
	@Test
	public void testLogin()
	{
		this.login();
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
