/**
 * 
 */
package com.happy3w.autobuy.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.model.ActStruct;

/**
 * 点击按钮。
 * @version 2016年10月17日下午2:19:54
 * @author happy3w
 */
public class Click extends BaseAction{
	private String xpath;
	public Click(String xpath)
	{
		this.xpath=xpath;
	}
	public Click()
	{
		super();
	}
	@Override
	public void setStruct(ActStruct struct)
	{
		super.setStruct(struct);
		this.xpath=struct.getTarget();
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebElement elementName = driver.findElement(By.xpath(this.xpath));
		elementName.click();
		return null;
	}

}
