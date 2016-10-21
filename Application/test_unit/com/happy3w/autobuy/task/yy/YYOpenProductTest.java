/**
 * 
 */
package com.happy3w.autobuy.task.yy;

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

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.yy.action.YYVerifyImg;
import com.happy3w.autobuy.util.HttpUtil;

import driver.RemoteDriver;
import junit.framework.Assert;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月19日下午2:21:40
 * @author happy3w
 */
public class YYOpenProductTest  extends BaseTest{
	@Autowired
	private SysConfig config;
	private WebDriver driver;
	private PurchaseOrder order;
	private Param param;
	@Autowired
	private HttpUtil http;
	private String srv="http://localhost:8190/autobuy/";
	@BeforeTest
	public void beforeTest()
	{
		driver  =RemoteDriver.getInstance().getDriver();
		order  =new PurchaseOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		AtUser user = new AtUser("chenjij@yonyou.com","yy2900");
		param  =new Param(order,user);
	}
	@AfterTest
	public void afterTest()
	{
		//driver.quit();
	}
	@Test
	public void testOpen(){
	   driver = RemoteDriver.getInstance().getDriver();
	   driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	   YYVerifyImg vc=new YYVerifyImg(config,http,srv);
	   YYLogin login  =new YYLogin(config,vc);
	   login.handle(driver, param);
	   YYOpenProduct finder = new YYOpenProduct(config);
	   finder.handle(driver, param);
	   System.out.println("end");
	}
}
