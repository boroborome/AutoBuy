/**
 * 
 */
package com.happy3w.autobuy.exe.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @version 2016年9月9日 下午1:24:47
 * @author Happy3W Cherry
 *
 */
public class Event_InputVal implements IEvent {

	private String text;
	private String xpath;
	private String paramName;

	/**
	 * @param driver
	 *            驱动器。
	 * @param xpath
	 *            输入框xpath。
	 * @param paramName
	 * @param text
	 *            要输入的值。
	 */
	public Event_InputVal(String xpath, String paramName) {
		this.xpath = xpath;
		this.paramName = paramName;
	}

	@Override
	public EventResult[] handle(WebDriver driver, EventParam param) {
		WebElement elementName = driver.findElement(By.xpath(xpath));
		elementName.sendKeys(param.getValue(paramName));
		return null;
	}

}
