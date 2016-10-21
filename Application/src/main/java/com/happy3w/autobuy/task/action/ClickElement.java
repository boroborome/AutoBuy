/**
 * 
 */
package com.happy3w.autobuy.task.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 点击按钮。
 * @version 2016年10月17日下午2:19:54
 * @author happy3w
 */
public class ClickElement implements IAction{
	private String xpath;
	public ClickElement(String xpath)
	{
		this.xpath=xpath;
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebElement elementName = driver.findElement(By.xpath(xpath));
		elementName.click();
		return null;
	}

}
