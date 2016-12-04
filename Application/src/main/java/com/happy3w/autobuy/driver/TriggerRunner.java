/**
 * 
 */
package com.happy3w.autobuy.driver;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.Stage;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.TaskClock;

/**
 * @version 2016年11月1日下午2:17:34
 * @author happy3w
 */
public class TriggerRunner implements Runnable {
	private TaskClock task;
	private WebDriver driver;

	public TriggerRunner(TaskClock task, WebDriver driver) {
		this.task = task;
		this.driver = driver;
	}

	@Override
	public void run() {
		Stage stage = TaskCache.getInstance().getStage(task.getTaskid());
		// ActionExe exe =new ActionExe();
		// Param param =new Param();
		// param.put(stage.getUser());
		// for (ActStruct act : stage.getActions()) {
		// param.put(exe.handle(driver, param, act));
		// }
	}
}
