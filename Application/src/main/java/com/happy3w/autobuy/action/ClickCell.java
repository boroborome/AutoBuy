/**
 * 
 */
package com.happy3w.autobuy.action;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happy3w.autobuy.action.strc.ActStruct;
import com.happy3w.autobuy.action.strc.CellStruct;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.util.LogUtil;

/**
 * 选择一行。
 * 
 * @version 2016年9月9日 下午1:28:49
 * @author Happy3W Cherry
 *
 */
public class ClickCell extends BaseAction {
	private int end;
	private int start;
	private CellStruct xpath;
	private String conditionName;

	public ClickCell(ActStruct struct) {
		this.setStruct(struct);
	}

	public ClickCell() {
		super();
	}

	@Override
	public void setStruct(ActStruct struct) {
		super.setStruct(struct);
		this.xpath = (CellStruct) struct;
	}

	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebElement table = driver.findElement(By.xpath(xpath.getTable()));
		boolean result = false;
		long time = Long.valueOf(param.getValue(xpath.getRefreshTime()));
		long starttime = System.currentTimeMillis();
		long span = 0;
		do {
			for (int i = start; i < end; i++) {
				String path = MessageFormat.format(xpath.getRow() + "[{0}]", i);
				WebElement row = table.findElement(By.xpath(path));
				WebElement product = row.findElement(By.xpath(xpath.getFilter()));
				if (product.getText().contains(param.getValue(conditionName))) {
					WebElement target = row.findElement(By.xpath(xpath.getTarget()));
					target.click();
					result = true;
					break;
				}
			}
			driver.navigate().refresh();
			span = System.currentTimeMillis() - starttime;
		} while (result == false && span < time);
		return null;
	}
}
