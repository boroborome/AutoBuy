/**
 * 
 */
package com.happy3w.autobuy.task.yy.action;

import com.happy3w.autobuy.task.action.TableXPath;
import com.happy3w.autobuy.task.action.ClickCell;
import com.happy3w.autobuy.task.action.GetCell;

/**
 * @version 2016年10月21日上午11:47:18
 * @author happy3w
 */
public class FilterYCode extends GetCell {

	private FilterYCode(int start, int end, TableXPath xpath, String enableFlag) {
		super(start, end, xpath, enableFlag);
		// TODO Auto-generated constructor stub
	}
	private static FilterYCode instance;
	public static FilterYCode getInstance()
	{
		if(null==instance)
		{
			TableXPath xpath = new TableXPath();
			xpath.setTable("/html/body/div[2]/div[2]/form/div/div[1]/table/tbody");
			xpath.setRow("tr");
			xpath.setTarget("td[1]");
			xpath.setCondition("td[3]");
			instance = new FilterYCode(2,10,xpath,"可用");
		}
		return instance;
	}
}
