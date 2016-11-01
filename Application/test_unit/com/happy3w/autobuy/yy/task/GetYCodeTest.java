/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.BaseAction;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.action.Result;
import com.happy3w.autobuy.action.strc.ActStruct;
import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.User;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.util.HttpUtil;
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
		UserOrder order  =new UserOrder();
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
	@Test
	public void testGet()
	{
		login();
	}
	public void login()
	{
		ActionExe exe  =new ActionExe();
		for(ActStruct act:TaskCache.getInstance().getTask("yy").getStage("ycode").getActions())
		{
			param.put(exe.handle(driver, param, act));
		}
	}
}
