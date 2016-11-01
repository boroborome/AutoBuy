/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.Timer;
import java.util.TimerTask;

import com.happy3w.autobuy.model.TaskClock;

/**
 * @version 2016年11月1日下午2:28:15
 * @author happy3w
 */
public class TriggerSchedulor {
	/**
	 * 任务定时器。
	 */
	private Timer timer = new Timer();

	public TriggerSchedulor() {
	}

	public void handle(TaskClock task) {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Context.getInstance().getPool()
						.execute(new TriggerRunner(task, Context.getInstance().getDriver(task.getTaskid())));
			}
		}, task.getStart(), task.getUnit().toMillis(task.getPeriod()));
	}
}
