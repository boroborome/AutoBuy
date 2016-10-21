/**
 * 
 */
package com.happy3w.autobuy.task.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 输入内容。
 * @version 2016年9月9日 下午1:24:47
 * @author Happy3W Cherry
 *
 */
public class Input implements IAction {

	private String xpath;
	private String paramName;

	/**
	 * @param xpath
	 *            输入框xpath。
	 * @param paramName
	 * @param text
	 *            要输入的值。
	 */
	public Input(String xpath, String paramName) {
		this.xpath = xpath;
		this.paramName = paramName;
	}

	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebElement elementName = driver.findElement(By.xpath(xpath));
		elementName.sendKeys(param.getValue(paramName));
		return null;
	}

}
