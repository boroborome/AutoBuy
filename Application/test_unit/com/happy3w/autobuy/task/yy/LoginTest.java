/**
 * 
 */
package com.happy3w.autobuy.task.yy;

import java.util.Calendar;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.operation.Click;
import com.happy3w.autobuy.task.operation.IHandler;
import com.happy3w.autobuy.task.operation.Input;
import com.happy3w.autobuy.task.operation.OpenPage;
import com.happy3w.autobuy.task.operation.Param;
import com.happy3w.autobuy.task.operation.Result;
import com.happy3w.autobuy.task.operation.VerifyCode;
import com.happy3w.autobuy.task.yy.YYLogin;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.WebDriverUtil;

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
	private TransferUrl transfer;
	@Autowired
	private HttpUtil http;
	@Test
	public void testLogin()
	{
		VerifyCode vc = Mockito.mock(VerifyCode.class);//new VerifyCode(config,transfer,http,"//*[@id=\"_verifyImg\"]",VerifyCode.RETURNNAME);
		YYLogin login = new YYLogin(config,vc);
		PurchaseOrder order  =new PurchaseOrder();
		order.setAmount(100);
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 17);
		order.setBuytime(c.getTime());
		order.setOrderid("test01");
		order.setProduct("YY-C");
		AtUser user = new AtUser("chenjij@yonyou.com","yy2900");
		Param arg  =new Param(order, user);
		WebDriver  driver  =WebDriverUtil.getWebDriver();
		Mockito.when(vc.handle(driver, arg)).thenReturn(new Result[]{new Result(VerifyCode.RETURNNAME,"11")});
		login.handle(driver,arg);
	}
}
