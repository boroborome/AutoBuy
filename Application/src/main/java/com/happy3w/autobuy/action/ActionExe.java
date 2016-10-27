/**
 * 
 */
package com.happy3w.autobuy.action;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.ActStruct;

/**
 * @version 2016年10月26日下午2:28:31
 * @author happy3w
 */
public class ActionExe {

	public  Result[] handle(WebDriver driver,Param param,ActStruct act) {
		BaseAction action = null;
		try {
			action = (BaseAction) Class.forName(act.getClassName()).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.setStruct(act);
		Result[] results=action.handle(driver, param);
		return results;
	}
}
