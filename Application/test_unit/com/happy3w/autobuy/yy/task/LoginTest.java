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

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.BaseAction;
import com.happy3w.autobuy.action.Click;
import com.happy3w.autobuy.action.IAction;
import com.happy3w.autobuy.action.Input;
import com.happy3w.autobuy.action.OpenPage;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.action.Result;
import com.happy3w.autobuy.action.VerifyImg;
import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.ActStruct;
import com.happy3w.autobuy.model.User;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.WebDriverUtil;
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

	private WebDriver driver;

	@AfterTest
	public void afterTest()
	{
		//driver.quit();
	}
	@Test
	public void testLogin()
	{
		driver  =RemoteDriver.getInstance().getDriver(config.getTimeout());		
		this.login();
	}
	public void login()
	{
		UserOrder order  =new UserOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		User user = new User("chenjij@yonyou.com","***");
		Param param  =new Param();
		param.put(user);
		param.put(order);
		param.put("srv",config.getWebServerUrl());
		ActionExe exe  =new ActionExe();
		//Mockito.when(vc.handle(driver, arg)).thenReturn(new Result[]{new Result(VerifyCode.RETURNNAME,"11")});
		for(ActStruct act:TaskCache.getInstance().get("YY").getStage("ycode").getActions("login"))
		{
			param.put(exe.handle(driver, param, act));
		}
	}
}
