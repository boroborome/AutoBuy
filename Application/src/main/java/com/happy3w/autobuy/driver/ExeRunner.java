/**
 * 
 */
package com.happy3w.autobuy.driver;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.action.strc.ActStruct;
import com.happy3w.autobuy.model.Stage;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.UserOrder;

/**
 * 单独立线程执行。
 * 
 * @version 2016年9月9日 上午9:59:19
 * @author Happy3W Cherry
 *
 */
public class ExeRunner implements Runnable {

	private WebDriver driver;
	private Stage stage;
	private Param param;

	public ExeRunner( WebDriver driver, Stage stage, Param param) {
		this.driver = driver;
	}

	@Override
	public void run() {
		ActionExe exe  =new ActionExe();
		param.put(stage.getUser());
		for (ActStruct act : stage.getActions()) {
			param.put(exe.handle(driver, param, act));
		}
	}

}