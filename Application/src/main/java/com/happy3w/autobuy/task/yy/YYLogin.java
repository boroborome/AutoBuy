/**
 * 
 */
package com.happy3w.autobuy.task.yy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
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
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 完成登录。
 * @version 2016年10月17日下午3:15:23
 * @author happy3w
 */
public class YYLogin {
	private List<IHandler> events =new ArrayList<IHandler>();
	private SysConfig config;
	/**
	 * 登录构造器。
	 * @param config  配置信息。
	 * @param verifycode 验证码处理器。
	 */
	public YYLogin(SysConfig config,IHandler verifycode)
	{
		this.config=config;
		events.add(new OpenPage("https://www.yyfax.com/user/login.html","友金所-用户登录",config.getTimeout()));
		events.add(new Input("//*[@id=\"accountName\"]",AtUser.USERID));
		events.add(new Input("//*[@id=\"password1\"]",AtUser.PASSWORD));
		events.add(verifycode);
		events.add(new Input("//*[@id=\"verifyCode\"]",VerifyCode.RETURNNAME));
		events.add(new Click("//*[@id=\"login\"]"));
	}
	public void handle(WebDriver driver,Param arg)
	{
		for(IHandler handler:events)
		{
			Result[] results=handler.handle(driver, arg);
			if(null!=results&&results.length>0)
			{
				arg.put(results);
			}
			try {
				Thread.sleep(config.getStepSpan());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
