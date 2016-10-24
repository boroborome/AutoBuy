/**
 * 
 */
package com.happy3w.autobuy.task.action;

import java.text.MessageFormat;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.util.LogUtil;


/**
 * 选择一行。
 * @version 2016年9月9日 下午1:28:49
 * @author Happy3W Cherry
 *
 */
public class ClickCell implements IAction{
	private int end;
	private int start;
	private TableXPath xpath;
	private String conditionName;
	public ClickCell(int start,int end,TableXPath xpath,String conditionName)
	{
		this.start=start;
		this.end=end;
		this.xpath=xpath;
		this.conditionName=conditionName;
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebElement table  =driver.findElement(By.xpath(xpath.getTable()));
		for (int i = start; i < end; i++) {
			String path = MessageFormat.format(xpath.getRow()+"[{0}]", i);
			WebElement row = table.findElement(By.xpath(path));
			WebElement product = row.findElement(By.xpath(xpath.getCondition()));
			if(null==product)
			{
				continue;
			}
			if(product.getText().contains(param.getValue(conditionName)))
			{
				WebElement target = row.findElement(By.xpath(xpath.getTarget()));
				target.click();
				break;
			}
		}
		return null;
	}

}
