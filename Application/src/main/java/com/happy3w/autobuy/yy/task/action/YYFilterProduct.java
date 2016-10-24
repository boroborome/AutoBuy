/**
 * 
 */
package com.happy3w.autobuy.yy.task.action;

import com.happy3w.autobuy.task.action.TableXPath;
import com.happy3w.autobuy.task.action.ClickCell;

/**
 * 过滤需要的产品。
 * @version 2016年10月21日上午11:12:00
 * @author happy3w
 */
public class YYFilterProduct extends ClickCell {
	private YYFilterProduct(int start, int end, TableXPath xpath, String enableFlag) {
		super(start, end, xpath, enableFlag);
	}
	private static YYFilterProduct instance;
	public static YYFilterProduct getInstance()
	{
		if(null==instance)
		{
			TableXPath xpath  =new TableXPath(); 
			xpath.setTable("/html/body/div[2]/div/div[2]/div[2]/table/tbody");
			xpath.setTarget("td[7]");
			xpath.setCondition("td[1]");
			xpath.setRow("tr");
			instance  =new YYFilterProduct(2,10,xpath,"100元起投");
		}
		return instance;
	}
	
}
