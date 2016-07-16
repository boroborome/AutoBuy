/**
 * 
 */
package com.happy3w.autobuy.process;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 获取Ycode优惠吗。
 * 
 * @version 2016年7月3日 下午4:38:52
 * @author Happy3W Cherry
 *
 */
public class YcodeProcess {
	WebDriver webDriver;
	private LoginProcess loginManager;
	private Order order;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public LoginProcess getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginProcess loginManager) {
		this.loginManager = loginManager;
	}

	/**
	 * 获取ycode
	 * 
	 * @return ycode
	 */
	public String getYCode() {
		String ycode = null;
		WebDriverUtil.get(webDriver,"https://www.yyfax.com/user/spread/wdycode.htm", "ycode", 5000);
		for (int i = 2; i < 4; i++) {
			WebElement row = webDriver
					.findElement(By.xpath("/html/body/div[2]/div[2]/form/div/div[1]/table/tbody/tr[" + i + "]"));
			WebElement enable = row.findElement(By.xpath("td[3]"));
			if ("可用".equalsIgnoreCase(enable.getText())) {
				WebElement ycodeElement = row.findElement(By.xpath("td[1]"));
				ycode = ycodeElement.getText();
				break;
			}
		}
		return ycode;
	}
}
