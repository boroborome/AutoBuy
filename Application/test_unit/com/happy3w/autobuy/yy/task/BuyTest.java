/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.BaseAction;
import com.happy3w.autobuy.action.IAction;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.action.Result;
import com.happy3w.autobuy.action.strc.ActStruct;
import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.driver.Context;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.model.Stage;
import com.happy3w.autobuy.model.Task;
import com.happy3w.autobuy.model.TaskCache;

import driver.RemoteDriver;
import testkit.com.happy3w.autoby.BaseTest;

/**
 * @version 2016年10月25日上午10:38:57
 * @author happy3w
 */
public class BuyTest extends BaseTest {
	private WebDriver driver;
	private Param param;
	@Autowired
	private SysConfig config;
	@BeforeTest
	public void beforeTest() {
	
		param = new Param();
		UserOrder order = new UserOrder();
		order.setAmount(100);
		order.setBuytime(new Date());
		order.setOrderid("t01");
		order.setProduct("YY-A");
		order.setRate("8.0%");
		order.setTask("yy");
		param.put(order);
	}

	@Test
	public void testBuy() {
		param.put("srv",config.getWebServerUrl());
		Task task = TaskCache.getInstance().getTask("yy");
		ActionExe exe = new ActionExe();
		for (Stage stage : task.getStages()) {
			driver = RemoteDriver.getInstance().getDriver(10,Context.getInstance().getChrome());
			param.put(stage.getUser());
			for (ActStruct act : stage.getActions()) {
				param.put(exe.handle(driver,param,act));
			}
		} 
	}

}
