/**
 * 
 */
package com.happy3w.autobuy.exe.step.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.exe.step.IStepRunner;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 获取YCode。
 * 
 * @version 2016年9月9日 上午11:24:29
 * @author Happy3W Cherry
 *
 */
public class GetYCodeStep implements IStepRunner {

	private WebDriver webDriver;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happy3w.autobuy.exe.step.IStepRunner#process(com.happy3w.autobuy.
	 * model.PurchaseOrder)
	 */
	@Override
	public void process(PurchaseOrder order) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取ycode
	 * 
	 * @return ycode
	 */
	public String getYCode() {
		String ycode = null;
		WebDriverUtil.get(webDriver, "https://www.yyfax.com/user/spread/wdycode.htm", "ycode", 5000);
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
