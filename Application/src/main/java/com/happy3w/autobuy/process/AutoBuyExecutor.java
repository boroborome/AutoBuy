package com.happy3w.autobuy.process;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.schedule.Context;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * Created by ysgao on 5/16/16.
 */
public class AutoBuyExecutor implements Runnable {

	private final String webServerUrl;
	private final Order order;

	public AutoBuyExecutor(Order order) {
		this.order = order;
		this.webServerUrl = order.getSite().getWebServerUrl();
	}

	@Override
	public void run() {
		process();
		TransferProxy manager = new TransferProxy();
		manager.finish(order);
		Context.getInstance().getOrders().remove(order.getContent().getOrderid());
	}

	private void process() {
		WebDriver wd = WebDriverUtil.getWebDriver();
		// 构造login。
		LoginProcess login = new LoginProcess();
		login.setWebDriver(wd);
		login.setHttpUrl(webServerUrl);
		VerifyCodeProcess vc = new VerifyCodeProcess();
		vc.setHttpUrl(webServerUrl);
		login.setVc(vc);
		boolean logined = login.login(order.getSite().getYcodeAccount(), order.getSite().getYcodePassword());
		// 获取ycode.
		if (logined) {
			YcodeProcess ycode = new YcodeProcess();
			ycode.setWebDriver(wd);
			ycode.setLoginManager(login);
			order.getSite().setYcode(ycode.getYCode());
		}
		// 重新登录。
		if (logined) {
			if (!order.getSite().getYcodeAccount().equals(order.getSite().getYyfaxAccount())) {
				login.loginout();
				if (null != order.getSite().getYyfaxAccount()) {
					logined = login.login(order.getSite().getYyfaxAccount(), order.getSite().getYyfaxPassword());
				} else {
					logined = false;
				}
			}
		}
		// 购买。
		if (logined) {
			BuyProcess product = new BuyProcess();
			product.setVc(vc);
			product.setWebDriver(wd);
			product.setOrder(order);
			product.buy();
		}
		// 签到。
		if (logined) {
			login.signature();
		}

	}
}
