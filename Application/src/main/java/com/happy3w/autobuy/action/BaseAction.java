/**
 * 
 */
package com.happy3w.autobuy.action;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.ActStruct;

/**
 * @version 2016年10月25日上午11:17:46
 * @author happy3w
 */
public abstract class BaseAction implements IAction {
	private ActStruct struct;
	
	public ActStruct getStruct() {
		return struct;
	}

	public void setStruct(ActStruct struct) {
		this.struct = struct;
	}

}
