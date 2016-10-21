/**
 * 
 */
package com.happy3w.autobuy.task.action;

import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 获取单元格内容。
 * @version 2016年10月21日下午1:52:03
 * @author happy3w
 */
public class GetCell implements IAction {
	public static String RETURNNAME="getcell";
	private int end;
	private int start;
	private String enableFlag;
	private TableXPath xpath;
	public GetCell(int start,int end,TableXPath xpath,String enableFlag)
	{
		this.start=start;
		this.end=end;
		this.xpath=xpath;
		this.enableFlag=enableFlag;
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		String val=null;
		WebElement table  =driver.findElement(By.xpath(xpath.getTable()));
		for (int i = start; i < end; i++) {
			String path = MessageFormat.format(xpath.getRow()+"[{0}]", i);
			WebElement row = table.findElement(By.xpath(path));
			WebElement enable = row.findElement(By.xpath(xpath.getCondition()));
			if(null==enable)
			{
				continue;
			}
			if(enable.getText().contains(this.enableFlag))
			{
				WebElement target = row.findElement(By.xpath(xpath.getTarget()));
				val=target.getText();
				break;
			}
		}
		if(null!=val&&val.length()>0)
		{
			Result  result = new Result(RETURNNAME,val);
			return new Result[]{result};
		}
		return null;
	}

}
