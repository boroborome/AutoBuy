/**
 * 
 */
package com.happy3w.autobuy.action;

import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.model.ActStruct;
import com.happy3w.autobuy.model.CellStruct;

/**
 * 获取单元格内容。
 * @version 2016年10月21日下午1:52:03
 * @author happy3w
 */
public class GetCell extends BaseAction {
	private int end;
	private int start;
	private String condition;
	private TableXPath xpath;
	private String returnName;
	public GetCell(int start,int end,TableXPath xpath,String condition)
	{
		this.start=start;
		this.end=end;
		this.xpath=xpath;
		this.condition=condition;
	}
	public GetCell()
	{
		super();
	}
	@Override
	public void setStruct(ActStruct struct)
	{
		super.setStruct(struct);
		CellStruct cellStruct = (CellStruct) struct;
		this.returnName=cellStruct.getReturnName();
		this.end=cellStruct.getEndRw();
		this.start=cellStruct.getStartRw();
		this.condition=cellStruct.getCondition();
		this.xpath=new TableXPath();
		this.xpath.setFilter(cellStruct.getFilter());
		this.xpath.setRow(cellStruct.getRow());
		this.xpath.setTable(cellStruct.getTable());
		this.xpath.setTarget(cellStruct.getTarget());
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		String val=null;
		WebElement table  =driver.findElement(By.xpath(xpath.getTable()));
		for (int i = start; i < end; i++) {
			String path = MessageFormat.format(xpath.getRow()+"[{0}]", i);
			WebElement row = table.findElement(By.xpath(path));
			WebElement enable = row.findElement(By.xpath(xpath.getFilter()));
			if(enable.getText().contains(this.condition))
			{
				WebElement target = row.findElement(By.xpath(xpath.getTarget()));
				val=target.getText();
				break;
			}
		}
		if(null!=val&&val.length()>0)
		{
			Result  result = new Result(this.returnName,val);
			return new Result[]{result};
		}
		return null;
	}

}
