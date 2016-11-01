/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.TimerTask;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.model.Stage;
import com.happy3w.autobuy.model.UserOrder;

/**
 * 自动执行订单的定时调度任务。
 * 
 * @version 2016年9月8日 下午3:40:59
 * @author Happy3W Cherry
 *
 */
public class ExeTask extends TimerTask {
	private WebDriver driver;
	private Stage stage;
	private Param param;

	public ExeTask(WebDriver driver, Stage stage, Param param) {
		this.driver=driver;
		this.stage=stage;
		this.param=param;
	}

	@Override
	public void run() {
		Context.getInstance().getPool().execute(new ExeRunner(driver,stage,param));
	}

}
