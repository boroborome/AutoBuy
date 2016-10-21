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
import com.happy3w.autobuy.task.action.ClickElement;
import com.happy3w.autobuy.task.action.IAction;
import com.happy3w.autobuy.task.action.Input;
import com.happy3w.autobuy.task.action.OpenPage;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.Result;
import com.happy3w.autobuy.task.action.VerifyImg;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 完成登录。
 * @version 2016年10月17日下午3:15:23
 * @author happy3w
 */
public class YYLogin extends TaskHandler{
	/**
	 * 登录构造器。
	 * @param config  配置信息。
	 * @param verifycode 验证码处理器。
	 */
	public YYLogin(SysConfig config,IAction verifycode)
	{
		super(config);
		this.getEvents().add(new OpenPage("https://www.yyfax.com/user/login.html","友金所-用户登录",config.getTimeout()));
		this.getEvents().add(new Input("//*[@id=\"accountName\"]",AtUser.USERID));
		this.getEvents().add(new Input("//*[@id=\"password1\"]",AtUser.PASSWORD));
		this.getEvents().add(verifycode);
		this.getEvents().add(new Input("//*[@id=\"verifyCode\"]",VerifyImg.RETURNNAME));
		this.getEvents().add(new ClickElement("//*[@id=\"login\"]"));
	}

}
