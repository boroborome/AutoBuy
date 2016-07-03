package com.happy3w.autobuy.svc;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.LogUtil;
import com.happy3w.autobuy.util.ThreadUtil;
import com.happy3w.autobuy.util.WebDriverUtil;
import com.happy3w.autobuy.util.config.Command;

/**
 * Created by ysgao on 5/16/16.
 */
public class AutoBuyProcess implements Runnable {

	private String webServerUrl;
	private final String account;
	private final String password;

	public AutoBuyProcess(String account, String password, String webServerUrl) {
		this.account = account;
		this.password = password;
		this.webServerUrl = webServerUrl;
	}

	@Override
	public void run() {
		WebDriver wd = WebDriverUtil.getWebDriver();
		// 构造login。
		LoginManager login = new LoginManager();
		login.setWebDriver(wd);
		login.setHttpUrl(webServerUrl);
		VerifyCodeManager vc = new VerifyCodeManager();
		vc.setHttpUrl(webServerUrl);
		login.setVc(vc);
		Command command = new Command();
		boolean logined = login.login(command.getYcodeUser(), command.getYcodePsw());
		// 获取ycode.
		if (logined) {
			YcodeManager ycode = new YcodeManager();
			ycode.setWebDriver(wd);
			ycode.setLoginManager(login);
			command.setYcode(ycode.getYCode());
		}
		// 重新登录。
		if (logined) {
			if (!command.getYcodeUser().equals(account)) {
				login.loginout();
				if (null != account) {
					logined = login.login(account, password);
				} else {
					logined = false;
				}
			}
		}
		//购买。
		if (logined) {
			ProductManager product = new ProductManager();
			product.setVc(vc);
			product.setWebDriver(wd);
			product.setCommand(command);
			product.buy();
		}
		//签到。
		if(logined)
		{
			login.signature();
		}
	}
}
