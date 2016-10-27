/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.BaseAction;
import com.happy3w.autobuy.action.IAction;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.action.Result;
import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.ActStruct;
import com.happy3w.autobuy.model.User;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.util.HttpUtil;
import driver.RemoteDriver;
import junit.framework.Assert;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月19日下午2:21:40
 * @author happy3w
 */
public class ChoseProductTest  extends BaseTest{
	@Autowired
	private SysConfig config;
	private WebDriver driver;
	private UserOrder order;
	private Param param;
	@Autowired
	private HttpUtil http;
	private String srv="http://localhost:8190/autobuy/";
	@BeforeTest
	public void beforeTest()
	{
		driver  =RemoteDriver.getInstance().getDriver(10);
		order  =new UserOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		User user = new User("chenjij@yonyou.com","***");
		param  =new Param();
		param.put(user);
		param.put(order);
	}
	@AfterTest
	public void afterTest()
	{
		//driver.quit();
	}
	@Test
	public void testOpen(){
	   driver = RemoteDriver.getInstance().getDriver(10);
	   driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	   ActionExe exe  =new ActionExe();
	   for(ActStruct act:TaskCache.getInstance().get("YY").getStage("buy").getActions("choose"))
	   {
			param.put(exe.handle(driver, param, act));
	   }
	}
}
